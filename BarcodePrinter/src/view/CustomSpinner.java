package view;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class CustomSpinner extends JSpinner {

	public CustomSpinner(SpinnerNumberModel spinnerModel) {
		super(spinnerModel);
		addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				rotated(e.getWheelRotation());
			}
		});
	}

	private void rotated(int wheelRotation) {
		Object newValue = (wheelRotation == -1) ? getModel().getNextValue()
				: getModel().getPreviousValue();

		if (newValue != null)
			getModel().setValue(newValue);
	}

}
