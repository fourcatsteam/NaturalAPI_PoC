package fouCats.Poc_NaturalAPI_Design;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
//import org.json.JSONObject;

public class createJSON {
	private JSONArray ja = new JSONArray();
	//private JSONObject jo = new JSONObject();
	createJSON(){
		//jo.put("padre","prova");
		//ja.put("figlio 1");
		//ja.put("figlio 2");
		//jo.put("lista figli",ja);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			
			String line = br.readLine();
			while(line != null) {
				line = line.replace("dobj ", "");
				String[] results = line.split(" "); //per mettere la prima lettera della seconda parola del metodo maiuscola
				line = results[0] + results[1].substring(0,1).toUpperCase() + results[1].substring(1);
				ja.put(line);
				line = br.readLine();
			}
			br.close();
		}
		catch(IOException ioe) {
			System.out.println("errore");
		}
	}
	/*public JSONObject getJo() {
		return jo;
	}*/
	public JSONArray getJa() {
		return ja;
	}
}
