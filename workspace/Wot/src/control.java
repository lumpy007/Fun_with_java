import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

import javax.print.DocFlavor.URL;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class control 
{
	public static JTextField text1 = new JTextField("tes-x",20);
	public static JTextField text2 = new JTextField("f:\\temp",20);
	public static JTextField text3 = new JTextField("",20);
	public static JButton readTankstats = new JButton("Read Tank Stats");
	public static JComboBox<String> box = new JComboBox<String>();
	public static JTable table;
	public static void createAndShowGUI() throws IOException
	{
		JLabel szoveg = new JLabel("TEST, this is only a test version contact Lumpy007 for feature requests");
		JFrame frame1 = new JFrame("Wot manager");
        JPanel panel = new JPanel();
        szoveg.setSize(200, 300);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(600,600);
        frame1.add(panel);
        frame1.setResizable(false);
        frame1.setVisible(true);
       
        initTable();
        initBox();
        final Class<?> referenceClass = control.class;
        final java.net.URL url =
            referenceClass.getProtectionDomain().getCodeSource().getLocation();

        try{
            final File jarPath = new File(url.toURI()).getParentFile();
            System.out.println(jarPath); // this is the path you want 
            text2.setText(jarPath.getAbsolutePath());
        } catch(final URISyntaxException e){
            // etc.
        }
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        text2.setEditable(false);
        text3.setText("113");
        System.out.println(control.box.getSelectedItem());
        JButton button1 = new JButton(" Load clan members ");
        button1.addActionListener(new getclandata());
        readTankstats.addActionListener(new getTankdata());
        panel.setLayout(null);
        //panel.add(button1);
        panel.add(readTankstats);
        panel.add(text1);
        panel.add(text2);
        //panel.add(text3);
        panel.add(szoveg);
        panel.add(scrollPane);
        panel.add(box);
        Dimension meret = button1.getPreferredSize();
        button1.setBounds(30 , 30 ,(int)meret.getWidth(), (int)meret.getHeight());
        readTankstats.setBounds(30, 160,(int) readTankstats.getPreferredSize().getWidth(),(int) readTankstats.getPreferredSize().getHeight());
        meret = text1.getPreferredSize();
        text1.setBounds(250  , 30 ,(int)meret.getWidth(), (int)meret.getHeight());
        meret = text2.getPreferredSize();
        text2.setBounds(250  , 60 ,(int)meret.getWidth(), (int)meret.getHeight());
        meret = text3.getPreferredSize();
        text3.setBounds(250  , 90 ,(int)meret.getWidth(), (int)meret.getHeight());
        meret = szoveg.getPreferredSize();
        szoveg.setBounds(100 , 510 ,(int)meret.getWidth(), (int)meret.getHeight());
        scrollPane.setBounds(30, 200, 400, 300);
        box.setBounds(450, 160, 100, 30);
        System.setProperty("http.proxyHost", "fiesprx000.nsn-net.net");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "fiesprx000.nsn-net.net");
        System.setProperty("https.proxyPort", "8080");
        
        getclandata.readClanFile();
        
	}


	public static void initBox() {
		Arrays.sort(tankTable.table);
		//DefaultComboBoxModel model = new DefaultComboBoxModel();
		for (int i = 0;i < tankTable.table.length;i++)
        {
        	 control.box.addItem(tankTable.table[i].getName());
        }
		control.box.setSelectedItem("113");
		control.box.addActionListener(new ActionListener() 
		{

			
			public void actionPerformed(ActionEvent event) 
			{
				//System.out.println(control.box.getSelectedItem());
				control.text3.setText((String) control.box.getSelectedItem());
				getTankdata.setTank_id(tankTable.giveId((String) control.box.getSelectedItem()));
			}
		});
	}


	public static void initTable() {
		String[] colums ={"Name","Damage/Battles","Battles"};
		Object[][] data = new Object[100][3];
		for (int i=0;i<100;i++)
		{
			data[i][0] = "";
			data[i][1] = "";
			data[i][2] = "";
		}
		DefaultTableModel model;
		model = new DefaultTableModel(data,colums);
        table = new JTable(model);
	}
 
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() {
                try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
    
}

