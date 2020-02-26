package fourCats.Poc_NaturalAPI_Develop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CreateCPP {
	private Map<String,String> methods;
	private Map<String,ArrayList<String>> parameters;
	private ObtainInfoBAL infoBAL = new ObtainInfoBAL();
	
	CreateCPP(){
		methods = infoBAL.getOperationsMap();
		parameters = infoBAL.getOperationParametersMap();
	}
	
	public void create() throws InputMismatchException{
		Scanner scanner = new Scanner(System.in);
		try {
			for(Map.Entry<String,String> method : methods.entrySet()) {
				
				String className = method.getKey().substring(0,1).toUpperCase() + method.getKey().substring(1); //CamelCase
 				ArrayList<String> parList = parameters.get(method.getKey());
				String par = new String();
				for(String s : parList) {
					par += s;
					if(parList.get(parList.size()-1) != s) {
						par += ",";
					}
				}

				File file = new File("./cpp_classes/" + className +".h");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write("class " + className + " {\n\n");
				fileWriter.write("public:\n");
				fileWriter.write("	" + method.getValue() + " "+ method.getKey() + "(" + par + "); \n\n}");
				fileWriter.close();
				
			}
			
			System.out.println("Classi C++ create!");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
