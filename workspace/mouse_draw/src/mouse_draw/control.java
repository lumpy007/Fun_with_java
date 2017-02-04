package mouse_draw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class control{

	public static JTextArea text1 = new JTextArea("Hello Kitti");
	public static JTextArea text2 = new JTextArea("");
	public static JTextArea text3 = new JTextArea(" felbontas ");
	public static MouseMove mouse = new MouseMove();
	static GraphicsDevice[] gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
	public static JFrame frame = new JFrame();
	public static JFrame frame2 = new JFrame();
	
	
	
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	protected static void createAndShowGUI() 
	{
		frame2.setSize(mouse.myScreen.screenSize.width / 5, mouse.myScreen.screenSize.height / 5);
		JPanel panel = new JPanel();
		canvas vaszon = new canvas();
		JButton button1 = new JButton("show result");
		JButton testbutton = new JButton("test");
		
		testbutton.addActionListener(new testFunctions());
		button1.addActionListener(vaszon);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		
		frame2.add(vaszon);
		frame.setVisible(true);
		frame2.setVisible(true);
		frame2.setLocationRelativeTo(null);

		
		panel.setBackground(Color.BLUE);
		panel.add(text1);
		panel.add(text2);
		panel.add(text3);
		panel.add(button1);
		panel.add(testbutton);
		
		
		
		
		
		
		text2.setText("maxX= " + mouse.myScreen.screenSize.getMaxX() + "minX= " + mouse.myScreen.screenSize.getMinX() + "maxY= " + mouse.myScreen.screenSize.getMaxY() + "minY= " + mouse.myScreen.screenSize.getMinY());
		text3.setText("width= " + mouse.myScreen.screenSize.width + "highth= " + mouse.myScreen.screenSize.height);
		//System.out.print(0%2);
		
	}
	
	public static void proba(int number)
	{
		int x,y;
		for ( y = 0; y < number; y++)
		{
			for ( x = 0; x <= y; x++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		for ( y = number - 1 ; y > 0; y--)
		{
			for ( x = 0; x < y; x++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public static long fib(int n)
	{
		if (n<=1)return n;
		return fib(n-1)+ fib (n-2);
	}
}
