
public class tankTable {

	static tankNameAndId[] table = new tankNameAndId[]
			{
				new tankNameAndId ("Obj. 263","14337"),
				new tankNameAndId ("AMX 50 B","6209"),
				new tankNameAndId ("IS-4","6145"),
				new tankNameAndId ("E 100","9489"),
				new tankNameAndId ("T110E5","10785"),
				new tankNameAndId ("T110E3","13857"),
				new tankNameAndId ("T57 Heavy","14881"),
				new tankNameAndId ("Leopard 1","14609"),
				new tankNameAndId ("M60","15905"),
				new tankNameAndId ("WT auf E 100","16913"),
			    new tankNameAndId ("FV215b","6225"),
				new tankNameAndId ("Maus","6929"),
				new tankNameAndId ("FV215b 183","9297"),
				new tankNameAndId ("T92","8481"),
				new tankNameAndId ("ConquerorGC","12369"),
				new tankNameAndId ("G.W. E 100","9233"),
				new tankNameAndId ("E 50 M","12305"),
				new tankNameAndId ("B-C 25 t","3649"),
				new tankNameAndId ("FV4202","7249"),
				new tankNameAndId ("B-C 155 58","11841"),
				new tankNameAndId ("IS-7","7169"),
				new tankNameAndId ("113","5425"),
				new tankNameAndId ("Obj. 261","8705"),
				new tankNameAndId ("Obj. 430","17153"),
				new tankNameAndId ("Jg.Pz. E 100","12049"),
				new tankNameAndId ("Foch 155","13889"),
				new tankNameAndId ("T-62A","13825"),
				new tankNameAndId ("Obj. 140","16897"),
				new tankNameAndId ("121","4145"),
				new tankNameAndId ("VK 72.01 K","58641"),
				new tankNameAndId ("T110E4","13089"),
				new tankNameAndId ("M48 Patton","14113"),
				new tankNameAndId ("Obj. 268","13569"),
				new tankNameAndId ("STB-1","3681"),
				new tankNameAndId ("IS-3","5377"),
				new tankNameAndId ("AMX 13 90","4929"),
				new tankNameAndId ("AMX 13 100","3137"),
				new tankNameAndId ("T32","4385"),
				new tankNameAndId ("WZ-132","3889")
			};
	
	public tankTable()
	{
		/*table[0] = new tankNameAndId("Obj. 263","14337");
		table[1] = new tankNameAndId ("AMX 50 B","6209");
		table[2] = new tankNameAndId ("IS-4","6145");
		table[3] = new tankNameAndId ("E 100","9489");
		table[4] = new tankNameAndId ("T110E5","10785");
		table[5] = new tankNameAndId ("T110E3","13857");
		table[6] = new tankNameAndId ("T57 Heavy","14881");
		table[7] = new tankNameAndId ("Leopard 1","14609");
		table[8] = new tankNameAndId ("M60","15905");
		table[9] = new tankNameAndId ("WT auf E 100","16913");
		table[10] = new tankNameAndId ("FV215b","6225");
		table[11] = new tankNameAndId ("Maus","6929");
		table[12] = new tankNameAndId ("FV215b 183","9297");
		table[13] = new tankNameAndId ("T92","8481");
		table[14] = new tankNameAndId ("ConquerorGC","12369");
		table[15] = new tankNameAndId ("G.W. E 100","9233");
		table[16] = new tankNameAndId ("E 50 M","12305");
		table[17] = new tankNameAndId ("B-C 25 t","3649");
		table[18] = new tankNameAndId ("FV4202","7249");
		table[19] = new tankNameAndId ("B-C 155 58","11841");
		table[20] = new tankNameAndId ("IS-7","7169");
		table[21] = new tankNameAndId ("113","5425");
		table[22] = new tankNameAndId ("Obj. 261","8705");
		table[23] = new tankNameAndId ("Obj. 430","17153");
		table[24] = new tankNameAndId ("Jg.Pz. E 100","12049");
		table[25] = new tankNameAndId ("Foch 155","13889");
		table[26] = new tankNameAndId ("T-62A","13825");
		table[27] = new tankNameAndId ("Obj. 140","16897");
		table[28] = new tankNameAndId ("121","4145");
		table[29] = new tankNameAndId ("VK 72.01 K","58641");
		table[30] = new tankNameAndId ("T110E4","13089");
		table[31] = new tankNameAndId ("M48 Patton","14113");
		table[32] = new tankNameAndId ("Obj. 268","13569");
		table[33] = new tankNameAndId ("STB-1","3681");*/
	}
	
	public static String giveName (String id)
	{
		String name = "";
	
		
		for (int i = 0; i < tankTable.table.length; i++)
		{
			if (table[i].getId().matches(id)) return table[i].getName();
			
		}
		
		return name;
	}
	
	public static String giveId (String name)
	{
		String id = "";
	
		
		for (int i = 0; i < tankTable.table.length; i++)
		{
			if (table[i].getName().matches(name)) return table[i].getId();
			
		}
		
		return id;
	}
}

