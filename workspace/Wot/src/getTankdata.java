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
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class getTankdata implements ActionListener{
	
	private static String tank_id = "5425";
	//private List<playerTankStats> stats = new ArrayList<playerTankStats>(100);
	static playerTankStats[] stats = new playerTankStats[100];
	public String getTank_id() {
		return tank_id;
	}

	public static void setTank_id(String tank_id) {
		getTankdata.tank_id = tank_id;
	}

	public getTankdata ()
	{
		super();
		//this.tank_id = id;
	}
	
	
	
	public void actionPerformed(ActionEvent e)
    {
        //Execute when button is pressed
		

		
		try {
			getclandata.loadClanData();
			File file = new File(control.text2.getText()+"\\" + control.text1.getText() +tankTable.giveName(tank_id)+".txt");
			if (file.exists())
			{
				readPlayerData(file,tank_id);
			}
			else
			{
				writePlayerData();
			}
			String[] colums ={"Name","Damage/Battles","Battles"};
			Object[][] data = new Object[100][3];
			for (int i=0;i<100;i++)
			{
				data[i][0] = stats[i].getAccount_name();
				data[i][1] = stats[i].getDamage_dealt();
				data[i][2] = stats[i].getBattles();
			}
			DefaultTableModel model;
			model = new DefaultTableModel(data,colums);
			//control.table = new JTable(data,colums);
	        control.table.setModel(model);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
    }

	public static void readPlayerData(File file, String tankid) throws FileNotFoundException,
			IOException {
		BufferedReader br = new BufferedReader( new FileReader(file) );
		br.readLine(); /* consuming the first information line */
		for (int i = 0; i < 100; i++)
		{
			String inputLine = br.readLine();
			String[] parts = inputLine.split(":");
			stats[i] = new playerTankStats(((String) parts[1].subSequence(1, 20)).trim(),getclandata.getIdFromName(((String) parts[1].subSequence(1, 20)).trim()),tankid,Integer.parseInt(((String) parts[2].subSequence(1, 5)).trim()),Integer.parseInt(parts[3].trim()));
		}
		br.close();
	}
	
	public playerTankStats getPlayerData (String playername, String playerid, String tankid) throws IOException
	{
		playerTankStats player ;
		
		String urlstring = "https://api.worldoftanks.eu/wot/tanks/stats/?application_id=c2ad56d11eb6ab8797fa81fbebd57308&fields=tank_id,all.battles,all.damage_dealt,all.hits_percents&account_id="+playerid+"&tank_id="+tankid;
		URL url = new URL(urlstring);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine = in.readLine();
        in.close();
        if (inputLine.matches(".*null.*"))
        {
        	player = new playerTankStats(playername,playerid,tankid,0,0);
        }
        else
        {
        	String[] parts = inputLine.split("\"");
        	player = new playerTankStats(playername,playerid,tankid,(int) Integer.parseInt(parts[14].replaceAll("\\D+",""))/Integer.parseInt(parts[18].replaceAll("\\D+","")),Integer.parseInt(parts[18].replaceAll("\\D+","")));
        }
		return player;
	}
	
	public String[] findNameAndId (int index) throws IOException
	{
		String inputLine = "";
		String[] output = new String[2];
		inputLine = getclandata.playerAndId[index];
		if (inputLine == null || inputLine.matches("^could not find"))
		{
			output[0] = "";
			output[1] = "";
			return output;
		}
		String[] parts = inputLine.split(":");
		output[0] = parts[1];
		output[1] = parts[3];
		return output;
	}
	
	public void writePlayerData() throws IOException
	{
		for (int i = 0;i < 100;i++)
		{
			String[] nameAndId = findNameAndId(i);
			stats[i] = getPlayerData(nameAndId[0],nameAndId[1],tank_id);
		}
		Arrays.sort(stats);
		PrintWriter pw = new PrintWriter (new FileWriter(control.text2.getText()+"\\" + control.text1.getText() +tankTable.giveName(tank_id)+".txt"));
		pw.println(tankTable.giveName(tank_id)+" is the searched tank");
		for (int i = 0;i < 100;i++)
		{
	
			pw.format("Name: %-20s Damage dealt: %-4s Battles with the tank: %-6s\n",stats[i].getAccount_name(),stats[i].getDamage_dealt(),stats[i].getBattles());
		}
		//pw.println(" for testing >"+clan[1]+"<");
		pw.close();
	}
	
	public static void savePath() throws IOException
	{
		PrintWriter pw = new PrintWriter (new FileWriter(control.text2.getText()+"\\" + "control.txt"));
		pw.println(control.text2.getText());
	}
}


