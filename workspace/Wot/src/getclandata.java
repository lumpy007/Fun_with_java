import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;






public class getclandata implements ActionListener {
	
	public static String[] playerAndId = new String[100];
	
	public void actionPerformed(ActionEvent e)
    {
        //Execute when button is pressed
		
		
		try {
			
				
			loadClanData();
			
			
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
    }
	
	public static String getclanid (String clan) throws IOException
	{
		String id = "";
		
		String urlstring = "https://api.worldoftanks.eu/wot/clan/list/?application_id=c2ad56d11eb6ab8797fa81fbebd57308&language=en&fields=clan_id,members_count&search="+control.text1.getText()+"&order_by=name";
		URL url = new URL(urlstring);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine = in.readLine();
        in.close();
        String[] parts = inputLine.split("\"");
        if (parts[6].matches(":0,")) return "could not find";
        id = parts[10];
        id = id.substring(1, 10);
		return id;
	}
	
	public static void loadClanData () throws IOException
	{
		File file = new File(control.text2.getText()+"\\" + control.text1.getText()+".txt");
		if (file.exists())
		{
			BufferedReader br = new BufferedReader( new FileReader(file) );
			br.readLine(); /* just to consume the first information line */
			for (int i = 0; i < 100; i++)
			{
				playerAndId[i] = br.readLine();
			}
			br.close();
		}
		else
		{
			createClanFile();
		}
	}
	
	public static void createClanFile() throws IOException
	{
		int index = 0;
		String[] parts;
		String[] clan = new String[2];
		clan[0] = control.text1.getText();
		
		PrintWriter pw = new PrintWriter (new FileWriter(control.text2.getText()+"\\" + control.text1.getText()+".txt"));
		pw.println(control.text1.getText()+" is the searched clan");
		clan[1] = getclanid(control.text1.getText());
		if (clan[1].matches("could not find"))
		{
			pw.println("could not find the clan: "+control.text1.getText());
			pw.close();
			return;
		}
		String urlstring = "https://api.worldoftanks.eu/wot/clan/info/?application_id=c2ad56d11eb6ab8797fa81fbebd57308&language=en&fields=members.account_name,members.account_id&clan_id="+clan[1];
		URL url = new URL(urlstring);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine = in.readLine();
        in.close();
		parts = inputLine.split("\"");
		for (int i = 0;i<parts.length;i++)
		{
			if (parts[i].matches("account_name"))
			{
				pw.println(parts[i]+":"+parts[i+2]+":"+parts[i+4]+":"+parts[i+5].substring(1, 10));
				playerAndId[index] = parts[i]+":"+parts[i+2]+":"+parts[i+4]+":"+parts[i+5].substring(1, 10);
				index++;
			}
		}
			
		
		
		//pw.println(" for testing >"+clan[1]+"<");
		pw.close();
	}
	
	public static void readClanFile() throws IOException
	{
		File file = new File(control.text2.getText()+"\\" + control.text1.getText()+".txt");
		if (file.exists())
		{
			BufferedReader br = new BufferedReader( new FileReader(file) );
			br.readLine(); /* just to consume the first information line */
			for (int i = 0; i < 100; i++)
			{
				playerAndId[i] = br.readLine();
			}
			br.close();
		}
	}
	
	public static String getIdFromName(String name)
	{
		for (int i = 0; i < 100; i++)
		{
			//String test = playerAndId[i];
			if (playerAndId[i].contains(name))
			{

				String[] parts = playerAndId[i].split(":");
				return parts[3];
			}
		}
		return "could not find the given name";
	}
	
}


