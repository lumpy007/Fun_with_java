package proba;

import javax.swing.JSlider;


public class csuszka1 extends JSlider {
	
	public csuszka1()
	{
		setPaintLabels(true);
		setMajorTickSpacing(100);
		setMinorTickSpacing(50);
		setPaintTicks(true);
	
	}

	public int cucc()
	{
		
		Displayer.f.repaint();
		return getValue();
	}
	
}
