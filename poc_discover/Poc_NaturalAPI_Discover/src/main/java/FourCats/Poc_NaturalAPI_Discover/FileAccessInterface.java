package FourCats.Poc_NaturalAPI_Discover;

import java.io.FileNotFoundException;

public interface FileAccessInterface {
	public String readDocument(String filename) throws FileNotFoundException;
	
	public BDL readBDL(String project) throws FileNotFoundException;
}
