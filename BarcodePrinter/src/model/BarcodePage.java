package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BarcodePage implements Printable {
	private Model model;

	public BarcodePage(Model model) {
		this.model = model;
	}

	public int print(Graphics g1, PageFormat pf, int page)
			throws PrinterException {

		if (page > 0) { /* We have only one page */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g = (Graphics2D) g1;
		g.translate(pf.getImageableX(), pf.getImageableY());

		double boxWidth = pf.getImageableWidth() / model.getCols();
		double boxHeight = pf.getImageableHeight() / model.getRows();

		int pageMax = model.getPageMax();
		int i = 0;

		for (FileInfo fi : model.getFiles()) {

			try {
				Image img = ImageIO.read(fi.getFile());

				for (int count = 0; count < fi.getAmount(); count++) {

					if (i < pageMax) {
						// go ahead with print
						int row = i / model.getCols();
						int col = i % model.getCols();

						double imgX = col * pf.getImageableWidth()
								/ model.getCols();
						double imgY = row * pf.getImageableHeight()
								/ model.getRows();

						int paddingX = model.getPaddingX();
						int paddingY = model.getPaddingY();

						// padding delta
						imgX += col * paddingX / model.getCols();
						imgY += row * paddingY / model.getRows();

						g.drawImage(img, (int) imgX, (int) imgY, (int) boxWidth
								- paddingX, (int) boxHeight - paddingY, null);

						// increment for next round
						i++;
					} else {

						break;
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}
}
