package col106.assignment4.Map;
import col106.assignment4.HashMap.HashMap;
import col106.assignment4.WeakAVLMap.Node;
import col106.assignment4.WeakAVLMap.WeakAVLMap;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;

public class Map<V> {
	static PrintStream out;
	public PrintStream fileout() {
		return out;
	}
	
	public Map() {
		// write your code here	
	}

	public void eval(String inputFileName, String outputFileName) throws IOException
	{
		// write your code here
		out = new PrintStream(new FileOutputStream(Map.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/col106/assignment4/Map/"+outputFileName, false), true);
		System.setOut(out);
		final File f = new File(Map.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/col106/assignment4/Map/"+inputFileName);
//		System.out.println(System.getProperty("cur.dir"));
//		URL url = Map.class.getResource(inputFileName);
//		System.out.println(url.getPath());
		//file = new File(url.getPath());
		//file = new File("C:/Users/Ayush/Desktop/Assignment 4/col106A4/src/col106/assignment4/Map/map_input");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String st;
			long insert_Tree = 0;
			long insert_Map = 0;
			long delete_Tree = 0;
			long delete_Map = 0;
			long count = 0;
			st = br.readLine();
			long start;
			long end;
			// Node<String,Integer> c;
			int size = Integer.parseInt(st.trim());
			HashMap<String> H = new HashMap<>(size);
			WeakAVLMap<String,String> T = new WeakAVLMap<>();
			while ((st = br.readLine()) != null) {
//				count++;
//				if(count%14000==0 && count>=14000)
//				{
//					System.out.println(count);
//				}
				String[] cmd = st.split(" ");
				if (cmd.length == 0) {
					System.err.println("Error parsing: " + st);
					return;
				}
				String operation = cmd[0].trim();
				String key;
				String value;
				switch (operation) {
					case "I":
						key = cmd[1].trim();
						key = key.substring(0,key.length()-1);
						value = (cmd[2].trim());
						start = System.currentTimeMillis();
						H.put(key,value);
						end = System.currentTimeMillis();
						insert_Map += -start+end;
						start = System.currentTimeMillis();
						T.put(key,value);
						//c = T.getNode(T.root,"vzkdl");
						end = System.currentTimeMillis();
						insert_Tree += -start+end;
						break;
					case "D":
						key = cmd[1].trim();
						start = System.currentTimeMillis();
						H.remove(key);
						end = System.currentTimeMillis();
						delete_Map += -start+end;
						start = System.currentTimeMillis();
						T.remove(key);
						//c = T.getNode(T.root,"vzkdl");
						end = System.currentTimeMillis();
						delete_Tree += -start+end;
						break;
					default:
						System.err.println("Unknown command: " + st);
				}
			}
			System.out.println("Operations WAVL HashMap");
			System.out.println("Insertions "+insert_Tree+" "+insert_Map);
			System.out.println("Deletions "+delete_Tree+" "+delete_Map);
		} catch (FileNotFoundException e) {
			System.err.println("Input file Not found. " + f.getAbsolutePath());
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
	}
	// public static void main(String args[]) throws IOException
	// {
	// 	Map<String> M = new Map<>();
	// 	String input = "map_input";
	// 	String output = "map_output";
	// 	M.eval(input,output);
	// }
}

