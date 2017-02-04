package code_optim;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

public class MyColorChooser extends JColorChooser{
	
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private JPanel panel;

	
	
	public void stateChanged(ChangeEvent e)
	{
		System.out.println("color");
		panel.setBackground(this.getColor());
	}
}
