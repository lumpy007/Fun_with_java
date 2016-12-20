package mouse_draw;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

import javax.swing.JComponent;

public class MouseMove implements Runnable{

	
	private Point pont_prev = MouseInfo.getPointerInfo().getLocation(); 
	public ScreenCapture myScreen = new ScreenCapture();
	
	public MouseMove() {
		new Thread(this).start();
	}
		
	

	@Override
	public void run()
	{
		while (true)
		{
			Point pont =  MouseInfo.getPointerInfo().getLocation();
		    
		    
			if (pont_prev.x != pont.x || pont_prev.y != pont.y  )
			{
			   
			   pont.x = (int) (pont.x - myScreen.screenSize.getMinX());
			   pont.y = (int) (pont.y - myScreen.screenSize.getMinY());
			   myScreen.matrix[(int) (pont.x)][(int) (pont.y)]+=1;
			   fillBetween ( pont_prev, pont);
			   pont_prev.x = pont.x;
			   pont_prev.y = pont.y;
			   myScreen.max = myScreen.getMax(myScreen.matrix);
			   control.text1.setText("mouse coordinate x=" + pont.x + " y=" + pont.y + "MaxValue = " + myScreen.max);
			   
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				System.out.println("megtortent");
				e.printStackTrace();
			}
		}
		
	}



	private void fillBetween(Point prev, Point curr) 
	{
		double tavolsag = Math.hypot(curr.x - prev.x, curr.y - prev.y);
		double[] iranyvektor = {0,0};
		iranyvektor[0] = (curr.x - prev.x) / tavolsag;
		iranyvektor[1] = (curr.y - prev.y) / tavolsag;
		for (int i = 1; i < (int)tavolsag; i++)
		{
			myScreen.matrix[(int)Math.round(prev.x + (i) * iranyvektor[0])][(int) Math.round(prev.y + (i) * iranyvektor[1])]+=1;
			
		}
		
		
	}



}
