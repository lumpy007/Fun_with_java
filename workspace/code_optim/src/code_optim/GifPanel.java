package code_optim;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GifPanel  extends JPanel{

	Image image;
    
	public GifPanel() {
		
		//ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		//ImageIcon myIcon = new ImageIcon(getClass().getClassLoader().getResource(pathToImage));
		//image = new Image(getClass().getClassLoader().getResource("recources/gears.gif"));
		//image = new ImageView(new Image (Toolkit.getDefaultToolkit().createImage("src/code_optim/gears.gif")));
		image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/gears.gif") );     
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}

}
