package mouse_draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;

import javax.swing.JComponent;

public class canvas extends JComponent implements ActionListener {
	
	public BufferedImage image = new BufferedImage(control.mouse.myScreen.screenSize.width, control.mouse.myScreen.screenSize.height, BufferedImage.TYPE_INT_RGB);
	public BufferedImage scaledImage = scale(image,control.frame2.getHeight(), control.frame2.getWidth(), (double)control.frame2.getHeight() / (double)control.mouse.myScreen.screenSize.height, (double)control.frame2.getWidth() / (double)control.mouse.myScreen.screenSize.width);
	public canvas()
	{
		
		
	}
	
	
	public void paint (Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(scaledImage, 0, 0,null);
		//g2d.setColor(new Color(123,40,20,255));
		//g2d.drawLine(0, 0, 100, 100);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		int r,g,b;
		for (int i = 0; i < image.getWidth(); i++)
		{
		
			for (int j = 0; j < image.getHeight(); j++)
			{
				r = (int) (((double)control.mouse.myScreen.matrix[i][j] / (double)control.mouse.myScreen.max)*255);
				g = (int) (((double)control.mouse.myScreen.matrix[i][j] / (double)control.mouse.myScreen.max)*250);
				b = (int) (((double)control.mouse.myScreen.matrix[i][j] / (double)control.mouse.myScreen.max)*245);
				int rgb = (r << 16) | (g << 8) | b;
				image.setRGB(i, j, rgb);
				
			}
		}
		this.repaint();
		scaledImage = scale(image,control.frame2.getHeight(), control.frame2.getWidth(), (double)control.frame2.getHeight() / (double)control.mouse.myScreen.screenSize.height, (double)control.frame2.getWidth() / (double)control.mouse.myScreen.screenSize.width);
		
	}
	
	public static BufferedImage scale(BufferedImage sbi, int dHeight, int dWidth, double fHeight, double fWidth) {
	    BufferedImage dbi = null;
	    if(sbi != null)
	    {
	        dbi = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}

}
