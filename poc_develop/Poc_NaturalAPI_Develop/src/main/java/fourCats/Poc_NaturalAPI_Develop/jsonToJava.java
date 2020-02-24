package fourCats.Poc_NaturalAPI_Develop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonToJava {
	
	private String feature;
	private String scenario;
	private List<String> operations;
	
	jsonToJava(){
		JSONParser parser = new JSONParser();
	    
        Object obj;
		try {
			obj = parser.parse(new FileReader("output.json"));
	        JSONObject jsonObject =  (JSONObject) obj;	        
	        
	        JSONArray results = (JSONArray) jsonObject.get("features");

	        JSONObject resultObject = (JSONObject) results.get(0);
	        
	        String features = (String) resultObject.get("name").toString();
	        setFeature(features);
	        
	        results = (JSONArray) resultObject.get("scenarios");
	        resultObject = (JSONObject) results.get(0);
	        
	        String scenarios = (String) resultObject.get("name").toString();
	        setScenario(scenarios);
	        
	        results = (JSONArray) resultObject.get("operations");
	        operations = new ArrayList<String>();
	        for(Object result : results) {
	        	JSONObject jo = (JSONObject) result;
	        	String operation = (String) jo.get("name").toString();
	        	operations.add(operation);
	        }
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public List<String> getOperations() {
		return operations;
	}
	public void setOperations(List<String> operations) {
		this.operations = operations;
	}
}
