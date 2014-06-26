package controller;

import java.io.File;
import java.util.Collections;

import javax.swing.JFileChooser;

import model.FileInfo;
import model.Model;
import view.BarcodePrinter;

public class FilesController {

	private BarcodePrinter view;
	private Model model;

	private JFileChooser fc;

	public FilesController(Model model, BarcodePrinter barcodePrinter) {
		this.model = model;
		this.view = barcodePrinter;

		fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);

	}

	public void addFiles() {
		int result = fc.showOpenDialog(view);
		if (result == JFileChooser.APPROVE_OPTION) {

			for (File file : fc.getSelectedFiles()) {
				model.getFiles().add(new FileInfo(file));
			}
		}
		Collections.sort(model.getFiles());
		view.notifyFilesChanged();
		
	}

	public void clearFiles() {
		model.getFiles().clear();
		view.notifyFilesChanged();
	}

}
