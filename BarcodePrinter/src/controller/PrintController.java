package controller;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import model.BarcodePage;
import model.Model;
import view.BarcodePrinter;

public class PrintController {
	// private BarcodePrinter view;
	private Model model;

	public PrintController(Model model, BarcodePrinter barcodePrinter) {
		this.model = model;
		// this.view = barcodePrinter;
	}

	public void doPrint() {
		BarcodePage page = new BarcodePage(model);

		// PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
		// attr.add(MediaSizeName.ISO_A4);
		// attr.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
		// attr.add(new MediaPrintableArea(2, 2, 210 - 4, 297 - 4,
		// MediaPrintableArea.MM));

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setJobName("BarcodePrinter printout");
		job.setPrintable(page);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}
}
