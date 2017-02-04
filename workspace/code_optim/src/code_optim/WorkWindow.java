package code_optim;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WorkWindow extends JFrame implements Runnable {


	
	
	
	public WorkWindow() 
	{
		super("Working");
		run();
	}

	public void run() 
	{
		JPanel panel = new GifPanel();
		
		this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(panel.getBounds());
	}
	

}
