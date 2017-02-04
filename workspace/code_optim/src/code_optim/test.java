package code_optim;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

public class test implements ActionListener{
	
	public void actionPerformed(ActionEvent e)
    {
		control.panel.setGifVisibility(true);
		control.panel.repaint();
        
		/*
		String path = "";
		String s = "";
		
		String[] pathparts = control.text1.gfile.get(0).getAbsolutePath().split(Pattern.quote(File.separator));
		for (int i = 0;i < pathparts.length - 1;i++)
		{
			 path = path + pathparts[i] + File.separator;
		}
		
		try 
		{
			PrintWriter pw = new PrintWriter (new FileWriter(path + control.text3.getText()));
			for (File file : control.text1.gfile)
			{
				//megeszi_a_cnr_filet_es_kikopi_a_prb_listat(pw,file);
				//egyedi_prb_lista_to_file(pw, file);
				//cucc(pw,file);
			}
			
			
			
			pw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
    }

	private void megeszi_a_cnr_filet_es_kikopi_a_prb_listat(PrintWriter pw,
			File file) throws FileNotFoundException, IOException {
		String s;
		int number = 0;
		String[] output = new String[50];
		BufferedReader br = new BufferedReader( new FileReader(file) );
		pw.println(file.getName());
		while ((s = br.readLine()) != null)
		{
			if (s.matches(".*<Type>.*")) pw.print(br.readLine() +"   ");
			if (s.matches(".*<SW-build>.*")) pw.println(br.readLine());
			if (s.matches(".*<Component>.*"))
			{
				while ((s = br.readLine()) != null && Pattern.compile("........").matcher(s).find())
				{
					output[number] = s;
					number++;
				}
			}
			if (s.matches(".*<ComponentVersion>.*"))
			{
				
				for(int i = 0; i < number; i++)
				{
					output[i + number] = br.readLine();
				}
			}
		}
		br.close();
		for (int i = 0;i < number; i++)
		{
			pw.format("%s.PAC--->%s\n",output[i],output[i+number]);
		}
		pw.println();
	}
	
	
	private void egyedi_prb_lista_to_file (PrintWriter pw,File file) throws IOException
	{
		String s;
		String test = "";
		int number = 0;
		String[] output = new String[1000];
		output[0] = "";
		BufferedReader br = new BufferedReader( new FileReader(file) );
		while ((s = br.readLine()) != null)
		{
			if (Pattern.compile("^(........)\\..*").matcher(s).find())
			{
				Matcher matcher = Pattern.compile("^(........)\\..*").matcher(s);
				matcher.matches();
				test = matcher.group(1);
				if (isStringMoreThanOnce(matcher.group(1),output))
				{

				}
				else
				{
					output[number] = matcher.group(1);
					number++;
				}
	
			}
			
		}
		br.close();
		for (int i = 0;i < number; i++)
		{
			pw.format("%s.PAC\n",output[i]);
		}
		pw.println();
	
	}
	
	private boolean isStringMoreThanOnce (String key, String[] terkep)
	{
		int i = 0;
		int number = 0;
		while ((terkep[i] != null) && (i < terkep.length))
		{
			if (terkep[i].matches(key))number++;
			i++;
		}
		if (number >= 1) return true;
		else return false;
	}
	
	private void trim_trailing_spaces (File file)
	{
		//nor started yet
	}
	
	private void col_content_szupurtyolo (PrintWriter pw, File prblist)
	{
		File directory = prblist.getParentFile();
		File[] colFiles = directory.listFiles(new FilenameFilter() 
		{
			public boolean accept(File directory, String name) 
			{
				return name.toLowerCase().endsWith(".col");
			}
		});
		
		
		
	}

	private void cucc(PrintWriter pw, File file) throws IOException
	{
		String reqid = null;
		String s;
		BufferedReader br = new BufferedReader( new FileReader(file) );
		while ((s = br.readLine()) != null)
		{
			if (Pattern.compile(".*Req ID\\s\\((\\d*)\\).*").matcher(s).find())
			{
				Matcher matcher = Pattern.compile(".*Req ID\\s\\((\\d*)\\).*").matcher(s);
				matcher.matches();
				reqid = matcher.group(1);
				pw.println();
				pw.print(reqid + "-->");
			}
			if (Pattern.compile(".*Test case ID:\\s(.*)").matcher(s).find())
			{
				Matcher matcher = Pattern.compile(".*Test case ID:\\s(.*)").matcher(s);
				matcher.matches();
				pw.print(matcher.group(1)+",");
			}
		}
		br.close();
	}
	
	public static void gombLenyom (int key) throws AWTException, InterruptedException
	{
		Robot automata = new Robot();
		automata.keyPress(key);
	}
	
	public static void gombElenged (int key) throws AWTException
	{
		Robot automata = new Robot();
		automata.keyRelease(key);
	}
}
