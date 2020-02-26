package fourCats.Poc_NaturalAPI_Develop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ObtainInfoBAL {
	
	private Map<String,String> operationsMap;
	private Map<String,ArrayList<String>> operationParametersMap;
	
	ObtainInfoBAL(){
		JSONParser parser = new JSONParser();
	    
        Object obj;
		try {
			obj = parser.parse(new FileReader("output.json"));
	        JSONObject jsonObject =  (JSONObject) obj;	        
	        
	        JSONArray users = (JSONArray) jsonObject.get("users");
	        
	        operationsMap = new HashMap<String,String>();
	        operationParametersMap = new HashMap<String,ArrayList<String>>();
	        
	        for(Object user : users) {
	        	JSONObject jsonUser = (JSONObject) user;
	        	
	        	JSONArray operations = (JSONArray) jsonUser.get("operations");
	        	
	        	for(Object op : operations) {
		        	JSONObject jsonOp = (JSONObject) op;
		        	String operation = (String) jsonOp.get("name").toString();
	        		String type = (String) jsonOp.get("type");
	        		if(type == null) {
	        			type = "void";
	        		}
		        	
		        	//camelCase metodi
		        	String[] split = operation.split("_");
		        	operation = "";
		        	for(int i=0; i<split.length; i++) {
		        		if(i>0) {
		        			split[i] = split[i].substring(0,1).toUpperCase() + split[i].substring(1);
		        		}
		        		operation += split[i];
		        	}
		        	
		        	operationsMap.put(operation,type);
		        	ArrayList<String> parameters = new ArrayList<String>();
		        	JSONArray par = (JSONArray) jsonOp.get("parameters");
		        	for(Object parameter : par) {
		        		JSONObject jsonParameter = (JSONObject) parameter;
		        		String name = (String) jsonParameter.get("name");
		        		String typePar = (String) jsonParameter.get("type");
		        		if(typePar == null) {
		        			typePar = "Object";
		        		}
		        		parameters.add(typePar + " " + name);
		        	}
		        	operationParametersMap.put(operation, parameters);
		        }
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
	
	public Map<String,String> getOperationsMap() {
		return operationsMap;
	}
	public void setOperationsMap(Map<String,String> operationsMap) {
		this.operationsMap = operationsMap;
	}
	public Map<String,ArrayList<String>> getOperationParametersMap() {
		return operationParametersMap;
	}
	public void setOperationParametersMap(Map<String,ArrayList<String>> operationParametersMap) {
		this.operationParametersMap = operationParametersMap;
	}
}
