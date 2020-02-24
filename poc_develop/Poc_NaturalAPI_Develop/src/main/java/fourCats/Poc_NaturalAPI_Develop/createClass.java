package fourCats.Poc_NaturalAPI_Develop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class createClass {
	private String name;
	private List<String> methods;
	private jsonToJava json = new jsonToJava();
	
	createClass(){
		String[] names = json.getScenario().split(" ");
		names[0] = names[0].substring(0,1).toLowerCase() + names[0].substring(1);
		for (int i=1; i<names.length; i++) {
			names[i] = names[i].substring(0,1).toUpperCase() + names[i].substring(1);
			name = names[i-1] + names[i];
		}
		methods = json.getOperations();
	}
	
	public void create() {
		File file = new File("test.class");
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("public class " + name + " {\n\n");
			for(String method : methods) {
				fileWriter.write("	public void " + method + "() {\n\n	}\n");
			}
			fileWriter.write("\n}");
			fileWriter.close();
		}
		catch(IOException ioe) {
			System.out.println("error");
		}
	}
}
