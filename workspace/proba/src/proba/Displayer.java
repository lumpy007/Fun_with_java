package proba;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.Canvas;
import java.awt.image.BufferedImage;

public class Displayer 
{
	public static Dimension size = new Dimension(500,500); 
	
	public static csuszka1 cs1 = new csuszka1();
	public static csuszka1 cs2 = new csuszka1();
	public static csuszka1 cs3 = new csuszka1();
	public static fest f = new fest();
	public static void main (String args[]) 
	{
		
		
		JFrame frame = new JFrame();
		JFrame control = new JFrame();
		JPanel panel = new JPanel();
		control.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		control.setVisible(true);
		control.setSize(size);
		control.setLocation(1600, 600);
		control.add(panel);
		cs1.setName("x1 erteke");
		cs1.setMinimum(100);
		cs1.setMaximum(600);
		cs1.setSize(200, 60);
		cs1.setBounds(0, 0, 300, 60);
		panel.add(cs1);
		cs2.setName("x3 erteke");
		cs2.setMinimum(100);
		cs2.setMaximum(600);
		cs2.setSize(200, 60);
		cs2.setBounds(0, 60, 300, 60);
		panel.add(cs2);
		cs3.setName("y2 erteke");
		cs3.setMinimum(0);
		cs3.setMaximum(600);
		cs3.setSize(200, 60);
		cs3.setBounds(0, 120, 300, 60);
		panel.add(cs3);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(size);
		frame.setMinimumSize(size);
		frame.add(f);
		//f.paint(f.getGraphics());
		
		
		
		
		
    
	}
}

