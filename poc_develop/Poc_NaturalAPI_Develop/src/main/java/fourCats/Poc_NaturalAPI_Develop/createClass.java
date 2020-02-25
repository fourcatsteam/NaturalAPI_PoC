package fourCats.Poc_NaturalAPI_Develop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class createClass {
	private String name;
	private List<String> methods;
	private Map<String,ArrayList<String>> parameters;
	private jsonToJava json = new jsonToJava();
	
	createClass(){
		String[] names = json.getScenario().split(" ");
		names[0] = names[0].substring(0,1).toUpperCase() + names[0].substring(1);
		for (int i=1; i<names.length; i++) {
			names[i] = names[i].substring(0,1).toUpperCase() + names[i].substring(1);
			name = names[i-1] + names[i];
		}
		methods = json.getOperations();
		parameters = json.getOperationParameters();
	}
	
	public void create() {
		Scanner scanner = new Scanner(System.in);
		try {
			for(String method : methods) {
				File file = new File("./java_classes/" + method +".java");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write("public class " + method + " {\n\n");
				ArrayList<String>  parList = parameters.get(method);
				String par = new String();
				for(String s : parList) {
					par += s;
					if(parList.get(parList.size()-1) != s) {
						par += ",";
					}
				}
				System.out.println("Inserisci il tipo di ritorno del metodo " + method);
				fileWriter.write("	public " + scanner.next() + " "+ method + "(" + par + ") {\n\n	}\n");
				fileWriter.write("\n}");
				fileWriter.close();
			}
			scanner.close();
			System.out.println("Classe java creata!");
		}
		catch(IOException ioe) {
			System.out.println("error");
		}
	}
}
