package code_optim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTree;
import javax.swing.filechooser.FileFilter;


public class patterns implements Runnable {
	
	private enum Type { INPUT, PROCEDURE, DECISION, START, SEMICOLON, COLON };
	private final int decisionDepth = 30;
	private final int maxProcedures = 50000; 
	private int debth = 0;
	private int darab = 0;
	private tree_element[] tree = new tree_element[maxProcedures];
	private int nextFreeElementIndex = 0;
	private TNSDLspecificPatterns TNSDL = new TNSDLspecificPatterns();
	private File myfile;
	private int myline;
	
	
	public File getMyfile() {
		return myfile;
	}

	public void setMyfile(File myfile) {
		this.myfile = myfile;
	}

	public int getMyline() {
		return myline;
	}

	public void setMyline(int myline) {
		this.myline = myline;
	}

	public String[] decision(int line, File file) throws IOException
	{
		Type type = Type.START;
		boolean comment = false;
		String s = "";
		int sor = 1;
		String string_sor = "";
		int depth = 0;
		String indentText = "-->";
		String[] decisionMap = new String[decisionDepth];
		BufferedReader br = new BufferedReader( new FileReader(file) );
		
		while (((s = br.readLine()) != null) && sor < line)
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
				
				switch (type)
				{
					case START:
					{
						
						if (TNSDL.INPUT.matcher(s).find())
						{
							Matcher matcher = TNSDL.INPUT.matcher(s);
							matcher.matches();
							decisionMap[0] = string_sor + indentText + matcher.group(1);
							depth++;
							type = Type.INPUT;
							break;
						}
						if (TNSDL.PROCEDURE.matcher(s).find())
						{
							Matcher matcher = TNSDL.PROCEDURE.matcher(s);
							matcher.matches();
							decisionMap[0] = string_sor + indentText + matcher.group(1);
							depth++;
							type = Type.PROCEDURE;
							break;
						}
						break;
					}
					case INPUT:
					{
						if (TNSDL.PROCEDURE.matcher(s).find())
						{
							Matcher matcher = TNSDL.PROCEDURE.matcher(s);
							matcher.matches();
							decisionMap[0] = string_sor + indentText + matcher.group(1);
							type = Type.PROCEDURE;
							break;
						}
						if (TNSDL.DECISION.matcher(s).find())
						{
							Matcher matcher = TNSDL.DECISION.matcher(s);
							matcher.matches();
							decisionMap[depth] = string_sor + indentText + matcher.group(1);
							if (s.contains(";"))
							{
								depth++;
								type = Type.DECISION;
							}
							else
							{
								type = Type.SEMICOLON;
							}
							break;
						}
						if (TNSDL.INPUT.matcher(s).find())
						{
							Matcher matcher = TNSDL.INPUT.matcher(s);
							matcher.matches();
							decisionMap[0] = string_sor + indentText + matcher.group(1);
							type = Type.INPUT;
							break;
						}
						break;
					}
					case PROCEDURE:
					{
						if (TNSDL.DECISION.matcher(s).find())
						{
							Matcher matcher = TNSDL.DECISION.matcher(s);
							matcher.matches();
							decisionMap[depth] = string_sor + indentText + matcher.group(1);
							if (s.contains(";"))
							{
								depth++;
								type = Type.DECISION;
							}
							else
							{
								type = Type.SEMICOLON;
							}
							break;
						}
						if (TNSDL.ENDPROCEDURE.matcher(s).find())
						{
							for (int i = 0 ;i < decisionMap.length;i++)
							{
								decisionMap[i] = null;
							}
							depth = 0;
							type = Type.START;
							break;
						}
						break;
					}
					case DECISION:
					{
						if (TNSDL.DECISIONbranch.matcher(s).find())
						{
							Matcher matcher = TNSDL.DECISIONbranch.matcher(s);
							matcher.matches();
							decisionMap[depth-1] = decisionMap[depth-1] + ";";
							decisionMap[depth-1] = decisionMap[depth-1].substring(0, decisionMap[depth-1].indexOf(";")+1);
							decisionMap[depth-1] = decisionMap[depth-1] +"\n"+ string_sor + indentText + matcher.group(1);
							/*if (s.contains(":")) 
							{
								break;
							}
							else
							{
								type = Type.COLON;
							}*/ /* this feature tries to accept multiple line decision branches */
							break;
						}
						if (TNSDL.ELSE.matcher(s).find())
						{
							Matcher matcher = TNSDL.ELSE.matcher(s);
							matcher.matches();
							decisionMap[depth-1] = decisionMap[depth-1] + ";";
							decisionMap[depth-1] = decisionMap[depth-1].substring(0, decisionMap[depth-1].indexOf(";")+1);
							decisionMap[depth-1] = decisionMap[depth-1] +"\n"+ string_sor + indentText + matcher.group(1);
							break;
						}
						if (TNSDL.DECISION.matcher(s).find())
						{
							Matcher matcher = TNSDL.DECISION.matcher(s);
							matcher.matches();
							try {
								decisionMap[depth] = string_sor + indentText + matcher.group(1);
							} catch (Exception e) {
								System.out.println("file=" + file);
								System.out.println("sor=" + sor);
								e.printStackTrace();
							}
							if (s.contains(";"))
							{
								depth++;
							}
							else
							{
								type = Type.SEMICOLON;
							}
							break;
						}
						if (TNSDL.ENDDECISION.matcher(s).find())
						{
							depth--;
							decisionMap[depth] = null;
							if (depth == 1)
							{
								if (decisionMap[0].contains("INPUT"))
								{
									type = Type.INPUT;
								}
								else
								{
									type = Type.PROCEDURE;
								}
							}
							break;
						}
						if (TNSDL.ENDPROCEDURE.matcher(s).find()) /* mintha ilyen nem is lehetne *//* ilyen van, mert elcsesztem a pontosvesszo keresest a procedure agban */
						{
							for (int i = 0 ;i < decisionMap.length;i++)
							{
								decisionMap[i] = null;
							}
							depth = 0;
							type = Type.START;
							break;
						}
						if (TNSDL.INPUT.matcher(s).find() ) /* mintha ilyen nem is lehetne */
						{
							for (int i = 0 ;i < decisionMap.length;i++)
							{
								decisionMap[i] = null;
							}
							Matcher matcher = TNSDL.INPUT.matcher(s);
							matcher.matches();
							decisionMap[0] = string_sor + indentText + matcher.group(1);
							depth = 1;
							type = Type.INPUT;
							break;
						}
						break;
					}
					case SEMICOLON:
					{
						if (Pattern.compile("\\s*(.*;).*").matcher(s).find())
						{
							Matcher matcher = Pattern.compile("\\s*(.*;).*").matcher(s);
							matcher.matches();
							decisionMap[depth] = decisionMap[depth] + "\n" + string_sor + indentText + matcher.group(1);
							if (decisionMap[depth].contains("DECISION"))
							{
								type = Type.DECISION;
							}
							else if (decisionMap[depth].contains("PROCEDURE"))
							{
								type = Type.PROCEDURE;
							}
							else
							{
								type = Type.INPUT;
							}
							depth++;
							break;
						}
						else
						{
							Matcher matcher = Pattern.compile("\\s*(.*)\\s*").matcher(s);
							matcher.matches();
							decisionMap[depth] = decisionMap[depth] + "\n" + string_sor + indentText + matcher.group(1);
							break;
						}
					}
					case COLON: /* only experimental */
					{
						if (Pattern.compile("\\s*(.*:).*").matcher(s).find())
						{
							Matcher matcher = Pattern.compile("\\s*(.*:).*").matcher(s);
							matcher.matches();
							decisionMap[depth-1] = decisionMap[depth-1] + "\n" + string_sor + indentText + matcher.group(1);
							type = Type.DECISION;
							break;
						}
						else
						{
							Matcher matcher = Pattern.compile("\\s*(.*)\\s*").matcher(s);
							matcher.matches();
							decisionMap[depth-1] = decisionMap[depth-1] + "\n" + string_sor + indentText + matcher.group(1);
							break;
						}
					}
				}
				
				
				
			}
			
			sor++;
			string_sor = String.format("%6s", sor);
		}
		try 
		{
			decisionMap[depth] = string_sor + indentText + s.trim();
		} catch (NullPointerException e1) {
			decisionMap[depth] ="";
			decisionMap[depth+1] ="_|_|_|_|    _|_|    _|_|_|  _|        _|_|_|_|  _|_|_|" ;       
			decisionMap[depth+2] ="_|        _|    _|    _|    _|        _|        _|    _|";      
			decisionMap[depth+3] ="_|_|_|    _|_|_|_|    _|    _|        _|_|_|    _|    _|";      
			decisionMap[depth+4] ="_|        _|    _|    _|    _|        _|        _|    _|";      
			decisionMap[depth+5] ="_|        _|    _|  _|_|_|  _|_|_|_|  _|_|_|_|  _|_|_| ";
			decisionMap[depth+6] ="";
			decisionMap[depth+7] = "The file contains only "+string_sor+" lines";
		}
		
		br.close();
		align(decisionMap,indentText);
		
		return decisionMap;
	}

	private void align(String[] decisionMap, String indentText)
	{

		for (int i = 0; (i < decisionMap.length && decisionMap[i] != null); i++)
		{
			String indent = "  ";
			for(int j = 0; j < i; j++)
				indent = indent + "  ";
			
			decisionMap[i] = decisionMap[i].replaceAll(indentText, indent);
		}
		
	}
	
	public String[] funcionFinder(String functionName, File origFile) throws IOException
	{
		String[] filesAndLines = new String[maxProcedures];
		int index = 0;
		File directory = origFile.getParentFile();
		File[] codeFiles = directory.listFiles(new FilenameFilter() 
		{
			public boolean accept(File directory, String name) 
			{
				return name.toLowerCase().endsWith(".sdl");
			}
		});
		
		Pattern pattern = Pattern.compile("^\\s*(TASK|CALL|DECISION)[^@]*(\\s+|\\(+|=)" + functionName + "\\s*(\\(.*|;|\\s*$)");
		for (File file : codeFiles)
		{
			int sor = 1;
			//Scanner scan = new Scanner(file);
			String s = null;
			BufferedReader br = new BufferedReader( new FileReader(file) );
			while ( (s = br.readLine()) != null)
			{
				//s = scan.findWithinHorizon(pattern, 0);
				if (pattern.matcher(s).find())
				{
					filesAndLines[index] = file.getAbsolutePath();
					filesAndLines[index+1] = ""+sor;
					index = index + 2;
				}
				sor++;
			}
			br.close();
		}
		return filesAndLines;
	}
	
	public void resolveLine () throws IOException
	{
		String[] decisionMap = new String[decisionDepth];
		decisionMap = decision(myline,myfile);
		handleResult(decisionMap, myfile.getAbsolutePath());
		if (Pattern.compile(".*PROCEDURE\\s+(.*)").matcher(decisionMap[0]).find())
		{
			Matcher matcher = Pattern.compile(".*PROCEDURE\\s+(.*)").matcher(decisionMap[0]);
			matcher.matches();
			debth++;
			recursion (matcher.group(1), myfile);
			
		}
			
	}

	public void recursion (String functionName, File file) throws IOException

	{
		int sameElementIndex = findSameElement(functionName, file.getName());
		if (sameElementIndex < nextFreeElementIndex)
		{
			copyTree(sameElementIndex);
			System.out.println("talaltam hasonlot");
			System.out.println("nextFreeElementIndex=" + nextFreeElementIndex);
			System.out.println("sameElementIndex=" + sameElementIndex);
			debth--;
		}
		else
		{
			String[] decisionMap = new String[decisionDepth];
			String[] filesAndLines = new String[maxProcedures];
			filesAndLines = funcionFinder(functionName, file);

			for(int i = 0; i < maxProcedures - 3; i = i + 2)
			{

				if (filesAndLines[i] != null)
				{
					decisionMap = decision(Integer.parseInt(filesAndLines[i+1]), new File(filesAndLines[i]));
					handleResult(decisionMap, filesAndLines[i]);

					if (Pattern.compile(".*PROCEDURE\\s+(.*)").matcher(decisionMap[0]).find())
					{
						Matcher matcher = Pattern.compile(".*PROCEDURE\\s+(.*)").matcher(decisionMap[0]);
						matcher.matches();
						debth++;
						System.out.println("depth++ " + debth);
						System.out.println(filesAndLines[i] + " " + filesAndLines[i+1]);

						recursion (matcher.group(1), new File(filesAndLines[i]));

					}
				}
				else
				{
					debth--;
					System.out.println("depth-- " + debth);
					System.out.println("files and lines length " + i);
					break;
				}
			}
		}
	}

	public void handleResult (String[] decisionMap, String fileName) throws IOException
	{
		String element = ""+debth + " " +  nextFreeElementIndex +  "  " + fileName + "\r\n";
	
		
		
		for (int i = 0; i < decisionMap.length; i++ )
		{
			if (decisionMap[i] != null) /*pw.println(decisionMap[i]);*/
			{
				element = element + decisionMap[i] + "\r\n";
			}
				
		}
		
		tree[nextFreeElementIndex] = new tree_element();
		
		Matcher matcher = Pattern.compile(".*(PROCEDURE|INPUT)\\s*(.*)").matcher(decisionMap[0]);
		matcher.matches();
		try
		{
			tree[nextFreeElementIndex].setFuncionName(matcher.group(2));
		}
		catch (IndexOutOfBoundsException e1)
		{
			System.out.println("Nem sikerult parsolni a PROCEDURE/INPUT sorat");
			tree[nextFreeElementIndex].setFuncionName("Nem sikerult parsolni a PROCEDURE/INPUT sorat");
		}
		catch (IllegalStateException e1)
		{
			System.out.println("Nem sikerult megtalalni a fuggveny nevet");
			tree[nextFreeElementIndex].setFuncionName("Nem sikerult megtalalni a fuggveny nevet");
		}
		
		tree[nextFreeElementIndex].setFilename(new File(fileName).getName());
		tree[nextFreeElementIndex].setElement(element);
		tree[nextFreeElementIndex].setDebth(debth);
		tree[nextFreeElementIndex].setUp_index(getMotherIndex(nextFreeElementIndex));
		
		
		nextFreeElementIndex++;
		
		
		
	}
	
	private int getMotherIndex (int currentIndex)
	{
		if (currentIndex == 0) return 0;
		
		for (int i = currentIndex;i >= 0; i--)
		{
			if (tree[i].getDebth() < tree[currentIndex].getDebth() ) return i;
		}
		System.out.println("elszamoltad");
		return -1;
		
		
	}

	public void getEndpoints() throws IOException
	{
		System.out.println("tree hossz "+tree.length);
		System.out.println("nextFreeElementIndex "+nextFreeElementIndex);
		for (int i = 0;i < nextFreeElementIndex-1;i++)
		{
			
			if (tree[i].getDebth() >= tree[i+1].getDebth())
			{
				printResults(i);
				//System.out.println("endpoints "+ i);
			}
			
		}
		printResults(nextFreeElementIndex-1);
	}
	
	public void printResults(int index) throws IOException
	{
		PrintWriter pw = new PrintWriter (new FileWriter(button2Action.path + control.text3.getText(), true));
		
		
		pw.println(tree[index].getElement());
		while (index != 0)
		{
			index = tree[index].getUp_index();
			pw.println(tree[index].getElement());
		}
		/*pw.println(patterns.tree[index].getElement());
		if (index != 0) printResults(patterns.tree[index].getUp_index());
		else*/ pw.println("--------------------------------------------------------------------------------------- "+darab);
		darab++;
		//pw.println(patterns.tree[index].getDebth());
			
		
		pw.close();
	}
	
	private int findSameElement (String funcionName, String fileName)
	{
		int treeIndex = 0;
		while(treeIndex <= nextFreeElementIndex)
		{
			if (tree[treeIndex].getFuncionName().equals(funcionName) && tree[treeIndex].getFilename().equals(fileName)) return treeIndex + 1;
			treeIndex++;
		}
	
		return treeIndex + 1;
	}
	
	private void copyTree (int sameElementIndex)
	{
		int index = sameElementIndex;
		int relativeDebth = debth - tree[sameElementIndex].getDebth();
		
		while(tree[sameElementIndex].getDebth() <= tree[index].getDebth())
		{
			tree[nextFreeElementIndex] = new tree_element();
			tree[nextFreeElementIndex].setElement(tree[index].getElement());
			tree[nextFreeElementIndex].setFilename(tree[index].getFilename());
			tree[nextFreeElementIndex].setFuncionName(tree[index].getFuncionName());
			tree[nextFreeElementIndex].setDebth(tree[index].getDebth() + relativeDebth);
			tree[nextFreeElementIndex].setUp_index(getMotherIndex(nextFreeElementIndex));
			nextFreeElementIndex++;
			index++;
		}
		
		
	}
	
	public void initVariables() /* ideje lenne peldanyositani es akkor ez nem kellene :) */
	{
		darab = 0;
		debth = 0;
		tree = new tree_element[maxProcedures];
		nextFreeElementIndex = 0;
	}

	public void run() {
		try {
			resolveLine();
			getEndpoints();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			control.setWorkingState(false);
		}
		
		
	}
}



