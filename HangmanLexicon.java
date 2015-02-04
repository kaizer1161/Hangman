/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import acm.util.*;
import java.util.*;

public class HangmanLexicon {

	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
		hangmanWordsList = new ArrayList<String>();
		try {
			hangmanLexicon = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true) {
				String lexiconWord = hangmanLexicon.readLine();
				if (lexiconWord == null) break;
				hangmanWordsList.add(lexiconWord);
			}
			hangmanLexicon.close();
		}	
		catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}	

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return hangmanWordsList.size();
		//return 10;
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		
		return hangmanWordsList.get(index);
		
		/*switch (index) {
		case 0: return "BUOY";
		case 1: return "COMPUTER";
		case 2: return "CONNOISSEUR";
		case 3: return "DEHYDRATE";
		case 4: return "FUZZY";
		case 5: return "HUBBUB";
		case 6: return "KEYHOLE";
		case 7: return "QUAGMIRE";
		case 8: return "SLITHER";
		case 9: return "ZIRCON";
		default: throw new ErrorException("getWord: Illegal index");
		} 
*/
		
	};

	/* Private instance variable */
	private ArrayList<String> hangmanWordsList;
	private BufferedReader hangmanLexicon;
	
}
