package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import model.FileInfo;

@SuppressWarnings("serial")
public class BarcodeControls extends JPanel {
	private FileInfo fileInfo;
	private SpinnerNumberModel spinnerModel;
	private BarcodePrinter view;

	public BarcodeControls(FileInfo fileInfo, BarcodePrinter view) {

		this.fileInfo = fileInfo;
		this.view = view;
		spinnerModel = new SpinnerNumberModel(fileInfo.getAmount(), 0, null, 1);
		spinnerModel.addChangeListener(e -> valueChanged(e));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 50, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 0, 0, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel(fileInfo.getFile().getName());
		add(label, gbc);
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_1.anchor = GridBagConstraints.NORTH;
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		CustomSpinner spinner = new CustomSpinner(spinnerModel);
		add(spinner, gbc_1);

	}

	protected void valueChanged(ChangeEvent e) {
		fileInfo.setAmount((int) spinnerModel.getValue());
		view.notifyAmountChanged();
	}

}
