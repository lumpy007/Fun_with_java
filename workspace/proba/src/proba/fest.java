package proba;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JComponent;


public class fest extends JComponent 
{
	public BufferedImage image;
	public fest()
	{
		Robot robot;
		try {
			
			robot = new Robot();
			Rectangle capturesize = new Rectangle (880,524,300,300);
			image = robot.createScreenCapture(capturesize);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paint (Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		
		
		//Displayer.cs1.getValue();
		//int x1 = cs1.getValue();
		int x1 = (int) Displayer.cs1.cucc();
		int y1 = 150;
		int x2 = 265;
		int y2 = (int) Displayer.cs3.cucc();
		int x3 = (int) Displayer.cs2.cucc();
		int y3 = 300;
		
		int d1 = 0;
		int d2 = 0;
		int d3 = 0;
		g2d.drawImage(image, x1, y2, null);
		
		g2d.setColor(new Color(123,240,90,255));
		for(int x = 0; x < getWidth(); x++)
		{
			for (int y = 0; y < getHeight(); y++)
			{
				d1 = (int)(Math.sqrt((x1-x)*(x1-x)+(y1-y)*(y1-y)));
				d2 = (int)(Math.sqrt((x2-x)*(x2-x)+(y2-y)*(y2-y)));
				d3 = (int)(Math.sqrt((x3-x)*(x3-x)+(y3-y)*(y3-y)));
				
				
					g2d.setColor(new Color((int)Math.abs((d1*d3/(d2+1))/2)%255,(int)Math.abs((d3*d2/(d1+1))/2)%255,(int)Math.abs((d1*d2/(d3+1))/2)%255,255));
					putpixel(x,y,g2d);
					
				
			}
		}
		g2d.setColor(new Color(255,0,0,255));
		g2d.fillOval(x1, y1, 3, 3);
		g2d.fillOval(x2, y2, 3, 3);
		g2d.fillOval(x3, y3, 3, 3);
		
		/*
		double fi = 0;
		int p = 0;
		int r = 100;
		int x = 0;
		int y = 0;
		//g2d.setColor(Color.WHITE);
		//g2d.fillRect(0, 0, getWidth(), getHeight());
		while(fi < 100)
		{
		
			g2d.setColor(new Color((p*10)%255,(p*10)%255,(p*10)%255,255));
			x = (int)(Math.sin(fi)*r+getWidth()/2);
			y = (int)(Math.cos(fi)*r+getHeight()/2);
			putpixel(x, y, g2d);
			p++;
			fi+=0.01;
		}
		*/
		
		/*
		for(int x = 0; x < getWidth(); x++)
		{
			for(int y = 0; y < getHeight(); y++)
			{
				g2d.setColor(new Color(Math.abs((x+x+x-y-y))%255,(int)Math.abs((y+y-x))%255,(int)Math.abs(Math.sqrt(y*y*y))%255,255));
				g2d.drawLine(Math.abs(getWidth()-y), Math.abs(x-y), Math.abs(x-2*y), Math.abs(getHeight()-x));
			}
		}*/
	}

	private void putpixel(int x, int y, Graphics2D g2d) {
		g2d.drawLine(x, y, x, y);
	}

	

	
}

