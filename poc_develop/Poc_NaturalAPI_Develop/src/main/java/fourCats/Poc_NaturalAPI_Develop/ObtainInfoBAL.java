package fourCats.Poc_NaturalAPI_Develop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObtainInfoBAL {
	
	private Map<String,String> operationsMap;
	private Map<String,ArrayList<String>> operationParametersMap;
	
	ObtainInfoBAL(){
		
		ObjectMapper objectMapper = new ObjectMapper();

		File file = new File("bal.json");

		try {
			JsonNode parent = objectMapper.readValue(file, JsonNode.class);
			
			JsonNode actors = parent.get("actors");
			
	        operationsMap = new HashMap<String,String>();
	        operationParametersMap = new HashMap<String,ArrayList<String>>();
			
			for(JsonNode actor : actors) {
				JsonNode operations = actor.get("operations");
				
				for(JsonNode op : operations) {
					String operation = op.get("name").asText();
					String type = op.get("type").asText();
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
		        	JsonNode par = op.get("parameters");
		        	for(JsonNode parameter : par) {
		        		String name = parameter.get("name").asText();
		        		String typePar = parameter.get("type").asText();
		        		parameters.add(typePar + " " + name);
		        	}
		        	operationParametersMap.put(operation, parameters);
				}
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
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
