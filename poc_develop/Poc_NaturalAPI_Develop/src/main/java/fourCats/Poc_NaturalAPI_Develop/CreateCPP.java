package fourCats.Poc_NaturalAPI_Develop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CreateCPP {
	private ObtainInfoBAL infoBAL;
	private Map<String,String> methods;
	private Map<String,ArrayList<String>> parameters;
	
	CreateCPP(){
		infoBAL = new ObtainInfoBAL();
		methods = infoBAL.getOperationsMap();
		parameters = infoBAL.getOperationParametersMap();
	}
	
	public void create() throws InputMismatchException{
		Scanner scanner = new Scanner(System.in);
		try {
			for(Map.Entry<String,String> method : methods.entrySet()) {
				
				String className = method.getKey().substring(0,1).toUpperCase() + method.getKey().substring(1); //CamelCase

				File file = new File("./cpp_classes/" + className +".h");
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write("class " + className + " {\n\n");
				fileWriter.write("public:\n");
				
				System.out.println("Premere 1 se il metodo " + method.getKey() + " è costante, 2 se non lo è");
				int isConst = scanner.nextInt();
				System.out.println("Come deve essere creato il metodo " + method.getKey() + ": premere 1 per normale, 2 per virtuale, "
						+ "3 per virtule puro");
				int choice = scanner.nextInt();
				
 				ArrayList<String> parList = parameters.get(method.getKey());
				String par = new String();
				for(String s : parList) {
					/*
					 * 
					 * PER AGGIUNGERE I CONST AI PARAMETRI
					System.out.println("Premere 1 se il parametro " + s + "è costante, 2 se non lo è");
					int isParConst = scanner.nextInt();
					if(isParConst == 1) {
						s = "const " + s;
					}
					else if(isParConst != 2) {
						fileWriter.close();
						throw new InputMismatchException("Input validi 1 e 2");
					}
					*/
					
					par += s;
					if(parList.get(parList.size()-1) != s) {
						par += ",";
					}
				}
				
				if(isConst == 1) {
					if(choice == 1) {
						fileWriter.write("	" + method.getValue() + " "+ method.getKey() + "(" + par + ") const; \n\n}");
					}
					else if(choice == 2) {
						fileWriter.write("	virtual " + method.getValue() + " "+ method.getKey() + "(" + par + ") const; \n\n}");
					}
					else if(choice == 3) {
						fileWriter.write("	virtual " + method.getValue() + " "+ method.getKey() + "(" + par + ") const = 0; \n\n}");
					}
					else {
						fileWriter.close();
						throw new InputMismatchException("Input validi 1, 2 e 3");
					}
				}
				else if(isConst == 2) {
					if(choice == 1) {
						fileWriter.write("	" + method.getValue() + " "+ method.getKey() + "(" + par + "); \n\n}");
					}
					else if(choice == 2) {
						fileWriter.write("	virtual " + method.getValue() + " "+ method.getKey() + "(" + par + "); \n\n}");
					}
					else if(choice == 3) {
						fileWriter.write("	virtual " + method.getValue() + " "+ method.getKey() + "(" + par + ") = 0; \n\n}");
					}
					else {
						fileWriter.close();
						throw new InputMismatchException("Input validi 1, 2 e 3");
					}
				}
				else {
					fileWriter.close();
					throw new InputMismatchException("Input validi 1 e 2 ");
				}
				
				fileWriter.close();
			}
			scanner.close();
			System.out.println("Classi C++ create!");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
