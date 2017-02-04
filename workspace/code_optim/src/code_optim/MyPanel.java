package code_optim;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyPanel extends JPanel implements ChangeListener {

	
	protected JColorChooser colorPalete;
    protected JLabel banner;
    protected JLabel szoveg;
    private GifPanel gif;
    
    public MyPanel() {
        super(new BorderLayout());
 
        //Set up the banner at the top of the window
       
 
        gif = new GifPanel();
        szoveg = new JLabel("NOKIA");
        
        /*try {
        	URL resourceurl = getClass().getResource("/NokiaKokia.ttf");
        	
        	//InputStream in = getClass().getResourceAsStream("/NokiaKokia.ttf");
			Font nokiaFont = Font.createFont(Font.TRUETYPE_FONT, new File( getClass().getResourceAsStream("/NokiaKokia.ttf").toString()  )).deriveFont(56.0f);
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			boolean bool = ge.registerFont(nokiaFont);
			System.out.println(bool);
			
			szoveg.setFont(nokiaFont);
		    
        } catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
 

        
        
        //Set up color chooser for setting text color
        colorPalete = new JColorChooser(this.getForeground());
        colorPalete.getSelectionModel().addChangeListener(this);
        //tcc.setDragEnabled(true);
        Dimension meret;
        meret = colorPalete.getPreferredSize();
        colorPalete.setBounds(250, 600, (int)meret.getWidth(), (int)meret.getHeight());
        
        szoveg.setForeground(new Color(255,255,255));
        szoveg.setSize(200, 100);
        
        szoveg.setFont(new Font("NokiaKokia", Font.PLAIN,56) );
        meret = szoveg.getPreferredSize();
        szoveg.setBounds(300 , 510 ,(int)meret.getWidth(), (int)meret.getHeight());
        
        
        gif.setBounds(200, 280, 200, 200);
        gif.setVisible(false);
        setLayout(null);
        add(colorPalete);
        add(szoveg);
        add(gif);
        
        
    }
 
    public void stateChanged(ChangeEvent e) {
        Color newColor = colorPalete.getColor();
        this.setBackground(newColor);
        //szoveg.setForeground(getContrastColor(newColor));
        szoveg.setForeground(invertColor(newColor));
        
    }
    
    public Color getContrastColor(Color color) {
    	  double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
    	  return y >= 128 ? Color.black : Color.white;
    	}
    public Color invertColor(Color bgColor)
    {
    	return new Color(255-bgColor.getRed(),
                255-bgColor.getGreen(),
                255-bgColor.getBlue());
     
    }
    
    public void setGifVisibility(boolean kapcsolo)
    {
    	this.gif.setVisible(kapcsolo);
    }

}
