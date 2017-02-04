package code_optim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class CompilerSwitch implements ActionListener {


	public void actionPerformed(ActionEvent e)
    {
        //Execute when button is pressed
		String s="";
		String key = "";
		//String[] usedSwitches = new String[200];
		String[] csparts;
		int sor = 1;
		int depth = 0;
		String[] switchMap = new String[10];
		String path = "";
		String[] pathparts = control.filenametext.gfile.get(0).getAbsolutePath().split(Pattern.quote(File.separator));
		for (int i = 0;i < pathparts.length - 1;i++)
		{
			 path = path + pathparts[i] + File.separator;
		}
		
		
		try {
			PrintWriter pw = new PrintWriter (new FileWriter(path + control.text3.getText()));
			//pw.println(control.text2.getText()+" is the key switch");
			for (File file : control.filenametext.gfile)
			{
                sor = 1;
                String[] usedSwitches = new String[200];
                boolean comment = false;
				BufferedReader br = new BufferedReader( new FileReader(file) );
				pw.println("\n"+file.getName()+" file is procesed below");
				
				while ((s = br.readLine()) != null)
				{
					if (comment && !(s.contains("*/")))
						{
						// in this case we can go to the next line because the whole line is comment
						}
					else
						{
							if (comment) s = s.replaceAll(".*\\*/", " ");
							s = s.replaceAll("/\\*.*\\*/", " "); /* removes whole comments from the line */
							comment = s.contains("/*");          /* check if the next line is starting whit comment */
							s = s.replaceAll("/\\*.*", " ");     /* removes partial comment */
							s = s.replaceAll(".*\\*/", " ");     /* removes partial comment */
							if (s.matches("^#endif.*"))
								{
									depth--;
									switchMap[depth] = null;
									if (depth !=0) key = switchMap[depth -1];
								}
							else
								{
									if (s.matches("^#if.*")) 
										{
										    csparts = s.split("[()]");
											csparts[1] = csparts[1].trim();
											key = getSwitchFromString(s);
											int i = 0;
											while ((usedSwitches[i] != null)&&!(usedSwitches[i].matches(key)))
											{
												 i++;
											}
											usedSwitches[i] = key;
											switchMap[depth] = key;
											depth++;
										}
									if (switchIsPresentMoreThanOnce(key,switchMap)) pw.println("in line "+sor+" there is more "+key+" switch open");
								}
						}
					
					
					//if (adottSwitchMelyseg > 1) pw.println("in line "+sor+" there is more "+control.text2.getText()+" switch open");
					//pw.println("melyseg="+melyseg);
					//pw.println("adottSwitchMelyseg="+adottSwitchMelyseg);
					sor++;
				}
				
				br.close();
				pw.println("The following compiler switches are used in the file:\n");
				for (int i = 0;i < 100;i++)
				{
					 if(usedSwitches[i] != null) pw.println(usedSwitches[i]);
				}
				
			}
			//pw.println(path);
			pw.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
    }
	
	

	public boolean switchIsPresentMoreThanOnce (String key, String[] terkep)
	{
		int i = 0;
		int number = 0;
		while ((terkep[i] != null) && (i < terkep.length))
		{
			if (terkep[i].matches(key))number++;
			i++;
		}
		if (number > 1) return true;
		else return false;
	}
	
	public String getSwitchFromString(String input)
	{
		String output = "";
		if(input.indexOf("/") != -1)  input = input.substring(0, input.indexOf("/"));
		int startIndex = input.indexOf("(");
		int endIndex = input.indexOf(")");
		if (endIndex == -1) endIndex = input.length(); //todo a tobb soros switcheket nem kezeli
		int i = endIndex + 1; 
		while (input.indexOf(")",i) != -1)
		{
			endIndex = input.indexOf(")",i);
			i = endIndex + 1;
		}
		output = input.substring(startIndex + 1, endIndex);
		return output.trim();
	}
}
