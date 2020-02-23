package FourCats.Poc_NaturalAPI_Discover;

import java.io.IOException;

import fourCars.Poc_NaturalAPI_Design.Feature;

public interface ParserAccessInterface {
	public Feature parseSentence(String sentence) throws IOException;
}
