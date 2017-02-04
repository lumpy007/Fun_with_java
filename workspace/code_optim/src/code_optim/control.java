package code_optim;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Robot;
import java.util.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class control
{
	public static Text1 filenametext = new Text1(" drag files here ",7,30);
	public static JTextField datatext = new JTextField("",30);
	public static JTextField text3 = new JTextField("output.txt",30);
	public static MyPanel panel; 
	public static JButton button2;
	public static JButton button1;
	
	public static void createAndShowGUI()  
	{
		
		
        JFrame frame1 = new JFrame("Code optimiser");
		panel = new MyPanel();
		//panel.setOpaque(true);
		
        
        datatext.setBackground(new Color(168,187,192));
        datatext.setForeground(new Color(0,0,0));
        datatext.setFont(new Font("Nokia Pure Text", Font.PLAIN,16) );
        //text2.setVisible(false);
        text3.setBackground(new Color(168,187,192));
        text3.setForeground(new Color(0,0,0));
        text3.setFont(new Font("Nokia Pure Text", Font.PLAIN,16) );
        JScrollPane scroll = new JScrollPane(filenametext);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame1.setSize(600,600);
        panel.setBackground(new Color(18,65,145));
        frame1.add(panel);
        
        frame1.setResizable(true);
        frame1.setVisible(true);
        
        button1 = new JButton(" CS check ");
        button1.addActionListener(new CompilerSwitch());
        button1.setBackground(new Color(0,201,255));
        button1.setForeground(new Color(18,65,145));
        button2 = new JButton("Explore");
        button2.addActionListener(new button2Action());
        button2.setBackground(new Color(0,201,255));
        button2.setForeground(new Color(18,65,145));
        //button2.setVisible(false);
        JButton testbutton = new JButton(" test ");
        testbutton.addActionListener(new test());
        
      
        testbutton.setBackground(new Color(0,201,255));
        testbutton.setForeground(new Color(18,65,145));
        //testbutton.setVisible(false);
        panel.setLayout(null);
        panel.add(button1);
        panel.add(button2);
        //panel.add(testbutton);

        panel.add(scroll);
        panel.add(datatext);
        panel.add(text3);
        
        

        
        
        Dimension meret = button1.getPreferredSize();
        button1.setBounds(30 , 30 ,(int)meret.getWidth(), (int)meret.getHeight());
        button2.setBounds(30 , 60 ,(int)meret.getWidth(), (int)meret.getHeight());
        testbutton.setBounds(30 , 90 ,(int)meret.getWidth(), (int)meret.getHeight());

        meret = datatext.getPreferredSize();
        datatext.setBounds(150  , 60 ,(int)meret.getWidth(), (int)meret.getHeight());
        meret = text3.getPreferredSize();
        text3.setBounds(150  , 30 ,(int)meret.getWidth(), (int)meret.getHeight());
        meret = scroll.getPreferredSize();
        scroll.setBounds(150  , 90 ,(int)meret.getWidth(), (int)meret.getHeight());
        
   
	}
 
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void setWorkingState(boolean kapcsolo)
    {
    	panel.setGifVisibility(kapcsolo);
    	control.button2.setEnabled(! kapcsolo);
    	control.button1.setEnabled(! kapcsolo);
    }
	

}
