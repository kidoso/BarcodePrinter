package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import model.FileInfo;
import model.Model;
import controller.FilesController;
import controller.PrintController;

@SuppressWarnings("serial")
public class BarcodePrinter extends JFrame {
	private Model model;
	private FilesController filesController;
	private PrintController printController;
	private JPanel filesView;

	private SpinnerNumberModel rowsModel;
	private SpinnerNumberModel colsModel;
	private SpinnerNumberModel paddingModelX;
	private SpinnerNumberModel paddingModelY;
	private JLabel counter;

	public BarcodePrinter() {
		setTitle("BarcodePrinter");
		setBounds(200, 100, 450, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(new File("icon.png")));
		} catch (IOException e) {
		}

		setVisible(true);

		model = new Model();
		filesController = new FilesController(model, this);
		printController = new PrintController(model, this);

		colsModel = new SpinnerNumberModel(new Integer(5), new Integer(1),
				null, new Integer(1));
		rowsModel = new SpinnerNumberModel(new Integer(16), new Integer(1),
				null, new Integer(1));

		paddingModelX = new SpinnerNumberModel(new Integer(8), new Integer(0),
				null, new Integer(1));
		paddingModelY = new SpinnerNumberModel(new Integer(7), new Integer(0),
				null, new Integer(1));

		initializeComponents();

		// wiring
		rowsModel.addChangeListener(e -> {
			model.setRows((int) rowsModel.getValue());
			notifyAmountChanged();
		});
		colsModel.addChangeListener(e -> {
			model.setCols((int) colsModel.getValue());
			notifyAmountChanged();
		});
		paddingModelX.addChangeListener(e -> model
				.setPaddingX((int) paddingModelX.getValue()));
		paddingModelY.addChangeListener(e -> model
				.setPaddingY((int) paddingModelY.getValue()));

		notifyAmountChanged();
	}

	private void initializeComponents() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 43, 0, 52, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		btnHinzufgen.addActionListener(e -> filesController.addFiles());
		GridBagConstraints gbc_btnHinzufgen = new GridBagConstraints();
		gbc_btnHinzufgen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHinzufgen.insets = new Insets(0, 0, 5, 5);
		gbc_btnHinzufgen.gridx = 0;
		gbc_btnHinzufgen.gridy = 0;
		panel.add(btnHinzufgen, gbc_btnHinzufgen);

		JLabel lblSpalten = new JLabel("Spalten");
		GridBagConstraints gbc_lblSpalten = new GridBagConstraints();
		gbc_lblSpalten.anchor = GridBagConstraints.WEST;
		gbc_lblSpalten.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpalten.gridx = 1;
		gbc_lblSpalten.gridy = 0;
		panel.add(lblSpalten, gbc_lblSpalten);

		JLabel lblReihen = new JLabel("Reihen");
		GridBagConstraints gbc_lblReihen = new GridBagConstraints();
		gbc_lblReihen.anchor = GridBagConstraints.WEST;
		gbc_lblReihen.insets = new Insets(0, 0, 5, 5);
		gbc_lblReihen.gridx = 2;
		gbc_lblReihen.gridy = 0;
		panel.add(lblReihen, gbc_lblReihen);

		JButton btnDrucken = new JButton("Drucken");
		btnDrucken.addActionListener(e -> printController.doPrint());

		JLabel lblPadding = new JLabel("Padding Breite");
		GridBagConstraints gbc_lblPadding = new GridBagConstraints();
		gbc_lblPadding.anchor = GridBagConstraints.WEST;
		gbc_lblPadding.insets = new Insets(0, 0, 5, 5);
		gbc_lblPadding.gridx = 3;
		gbc_lblPadding.gridy = 0;
		panel.add(lblPadding, gbc_lblPadding);

		JLabel lblPaddingHhe = new JLabel("Padding H\u00F6he");
		GridBagConstraints gbc_lblPaddingHhe = new GridBagConstraints();
		gbc_lblPaddingHhe.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaddingHhe.gridx = 4;
		gbc_lblPaddingHhe.gridy = 0;
		panel.add(lblPaddingHhe, gbc_lblPaddingHhe);
		GridBagConstraints gbc_btnDrucken = new GridBagConstraints();
		gbc_btnDrucken.insets = new Insets(0, 0, 5, 0);
		gbc_btnDrucken.gridx = 6;
		gbc_btnDrucken.gridy = 0;
		panel.add(btnDrucken, gbc_btnDrucken);

		JButton btnAlleEntfernen = new JButton("Alle entfernen");
		btnAlleEntfernen.addActionListener(e -> filesController.clearFiles());
		GridBagConstraints gbc_btnAlleEntfernen = new GridBagConstraints();
		gbc_btnAlleEntfernen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAlleEntfernen.insets = new Insets(0, 0, 5, 5);
		gbc_btnAlleEntfernen.gridx = 0;
		gbc_btnAlleEntfernen.gridy = 1;
		panel.add(btnAlleEntfernen, gbc_btnAlleEntfernen);

		CustomSpinner cols = new CustomSpinner(colsModel);
		GridBagConstraints gbc_cols = new GridBagConstraints();
		gbc_cols.fill = GridBagConstraints.HORIZONTAL;
		gbc_cols.insets = new Insets(0, 0, 5, 5);
		gbc_cols.gridx = 1;
		gbc_cols.gridy = 1;
		panel.add(cols, gbc_cols);

		CustomSpinner rows = new CustomSpinner(rowsModel);
		GridBagConstraints gbc_rows = new GridBagConstraints();
		gbc_rows.fill = GridBagConstraints.HORIZONTAL;
		gbc_rows.insets = new Insets(0, 0, 5, 5);
		gbc_rows.gridx = 2;
		gbc_rows.gridy = 1;
		panel.add(rows, gbc_rows);

		CustomSpinner paddingXSpinner = new CustomSpinner(paddingModelX);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 3;
		gbc_spinner.gridy = 1;
		panel.add(paddingXSpinner, gbc_spinner);

		CustomSpinner paddingYSpinner = new CustomSpinner(paddingModelY);
		GridBagConstraints gbc_spinner1 = new GridBagConstraints();
		gbc_spinner1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner1.gridx = 4;
		gbc_spinner1.gridy = 1;
		panel.add(paddingYSpinner, gbc_spinner1);

		counter = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 6;
		gbc_label.gridy = 1;
		panel.add(counter, gbc_label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);

		filesView = new JPanel();
		scrollPane.setViewportView(filesView);
		filesView.setLayout(new BoxLayout(filesView, BoxLayout.Y_AXIS));
	}

	public void notifyFilesChanged() {
		System.out.println("rebuilding");
		filesView.removeAll();

		for (FileInfo fi : model.getFiles()) {
			filesView.add(new BarcodeControls(fi, this));
		}

		filesView.revalidate();
		filesView.repaint();

		notifyAmountChanged();
	}

	public void notifyAmountChanged() {
		counter.setText(model.getAmount() + "/" + model.getPageMax());
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new BarcodePrinter();

			}
		});

	}
}