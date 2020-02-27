package fourCats.Poc_NaturalAPI_Develop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CreateJava {
	
	private ObtainInfoBAL infoBAL;
	private Map<String,String> methods;
	private Map<String,ArrayList<String>> parameters;
	
	CreateJava(){
		infoBAL = new ObtainInfoBAL();
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
				
				System.out.println(className + ": interface or class? Press 1 for interface, 2 for class");
				int choice = scanner.nextInt();

				if(choice == 1) {
					File file = new File("./java_classes/" + className +"Interface.java");
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write("public interface " + className + "Interface{\n\n");
					fileWriter.write("	public " + method.getValue() + " "+ method.getKey() + "(" + par + ");\n}");
					fileWriter.close();
				}
				else if(choice == 2) {
					File file = new File("./java_classes/" + className +".java");
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write("public class " + className + " {\n\n");
					fileWriter.write("	public " + method.getValue() + " "+ method.getKey() + "(" + par + ") {\n\n	}\n}");
					fileWriter.close();
				}
				else {
					scanner.close();
					throw new InputMismatchException("Errore: input non valido\nInput ammessi: 1 per interfaccia, 2 per classe");
				}
			}
			scanner.close();
			System.out.println("Classi java create!");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
