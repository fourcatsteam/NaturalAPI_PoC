package FourCats.Poc_NaturalAPI_Discover;

import java.io.IOException;

import fourCars.Poc_NaturalAPI_Design.User;

public interface ParserAccessInterface {
	public User parseSentence(String sentence) throws IOException;
}
