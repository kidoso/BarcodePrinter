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

		
		Graphics2D g = (Graphics2D) g1;
//		g.translate(pf.getImageableX(), pf.getImageableY());
		
//		double paperMarginW = 
		
		// 10mm margin left (and right)
		double myImageableX = 10d*pf.getWidth()/210d;
		// 12mm margin top (and bottom)
		double myImageableY = 12d*pf.getHeight()/297d;
		
		double myImageableWidth = pf.getWidth()-2.0*myImageableX;
		double myImageableHeight = pf.getHeight()-2.0*myImageableY;

		double boxWidth = myImageableWidth / model.getCols();
		double boxHeight = myImageableHeight / model.getRows();

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

						double imgX = (double)col * myImageableWidth
								/ (double)model.getCols();
						double imgY = (double)row * myImageableHeight
								/ (double)model.getRows();
						
						imgX += myImageableX;
						imgY += myImageableY;

						double paddingX = model.getPaddingX();
						double paddingY = model.getPaddingY();

						// padding delta
						imgX += paddingX;
						imgY += paddingY;
						
						// margin
						imgX+=model.getMarginLeft();
						
						
						int iImgX = (int) Math.round(imgX);
						int iImgY = (int) Math.round(imgY);
						
						double dImgWidth = boxWidth-2.0*paddingX;
						double dImgHeight = boxHeight-2.0*paddingY;

						g.drawImage(img, iImgX, iImgY, (int) dImgWidth, (int) dImgHeight, null);

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
