package code_optim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class button2Action implements ActionListener, Runnable{
	
	public static String path = "";
	
	public void actionPerformed(ActionEvent e)
    {
		String path = "";
		control.filenametext.readFiles();
		String[] pathparts = control.filenametext.gfile.get(0).getAbsolutePath().split(Pattern.quote(File.separator));
		for (int i = 0;i < pathparts.length - 1;i++)
		{
			 path = path + pathparts[i] + File.separator;
		}
		this.path = path;
		patterns pattern = new patterns();
		pattern.initVariables();
		control.setWorkingState(true);
		
			//String[] proba = patterns.decision(Integer.parseInt(control.text2.getText()) , control.text1.gfile.get(0));
			//String[] proba = patterns.funcionFinder(control.text2.getText() , control.text1.gfile.get(0));
			//patterns.resolve(proba);
			pattern.setMyfile(control.filenametext.gfile.get(0));
			pattern.setMyline(Integer.parseInt(control.datatext.getText()));
			new Thread(pattern).start();
			
			//patterns.resolveLine(Integer.parseInt(control.datatext.getText()), control.filenametext.gfile.get(0));
			//patterns.getEndpoints();

		
		
		
    }

	public void run() {
		
		
	}

}
