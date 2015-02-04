/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */


import acm.program.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		}

	//Run method calls HangmanLexicon, and starts the game
	public void run() {
		word = new HangmanLexicon();
		println("Welcome to Hangman!");
		
		while(lives != 0) {
			turn = 8; 
			correctGuess = 0;
			wrongGuessTillNow = "";
			println("You have " + lives + " lives left");
			canvas.reset();
			startGame();
			pause(3);
			canvas.removeAll();
			
		}
		
		//println("GAME OVER");
	}
	
	/*
	 * secretWord gets a word from the HangmanLexicon using the method selectWord.
	 * Displays Dashes for the player to guess.
	 * while the player have'nt guessed all the letters correctly and still have LIFE left
	 * game will keep on running.
	 * It simply takes user entry and checks it with all the letter inside the secretWord.
	 * And prints out Win or Lose accordingly. 
	 */
	private void startGame() {

		String secretWord = selectWord();
		//println(secretWord);
		displayDash(secretWord.length(), secretWord);
		while (correctGuess != secretWord.length() && turn != 0){

			println("The word now looks like this: " + dashAndCorrectGuessedWord);
			canvas.displayWord(dashAndCorrectGuessedWord);
			println("You have " + turn + " guesses left");
			playerGuess = userEntry();
			checkForRightGuess(secretWord, playerGuess);
					
		}
		
		if (correctGuess == secretWord.length()) {
		
			println("You guessed the word: " + dashAndCorrectGuessedWord);
			canvas.displayWord(dashAndCorrectGuessedWord);
			println("You win.");
		
		}
		
		if (turn == 0) {
			
			println("you'er completely hang.");
			println("Correct word is " + secretWord);
			println("You lose.");
			canvas.displayWord(secretWord);
			lives--;
			
	
		}
	}
	
	
	
	/*
	 * It simply checks if the user made a right guess or not.
	 * if he did, then simply puts in the Guessed letter on display with other hidden/shown letters of
	 * the word.
	 * if the user made a wrong guess, it simply takes away one life point.
	 */
	private void checkForRightGuess(String secretWord, String playerGuess) {

		playerGuess = playerGuess.toUpperCase();
		wrongGuess = true;
		for (int i = 0; i < secretWord.length(); i++){
			if (playerGuess.charAt(0) == secretWord.charAt(i)) {
				dashAndCorrectGuessedWord = dashAndCorrectGuessedWord.substring(0, i) +
						secretWord.charAt(i) + dashAndCorrectGuessedWord.substring(i+1);
				correctGuess++; 
				wrongGuess = false; 
				
			}

		}
		
		if (wrongGuess == false) 
			println("That guess is correct");
			
				
		if (wrongGuess == true) {
			println("There are no " + playerGuess + "'s in this word");
			wrongGuessLetters(playerGuess);
			turn--;
			canvas.noteIncorrectGuess(wrongGuessTillNow, turn);
		}
	}
	
	private void wrongGuessLetters(String wrongGuesses) {
		
		wrongGuessTillNow += wrongGuesses;
		
	}
	
	/*
	 * Simply displays dashes according to the secretWord size.
	 */
	private String displayDash(int length, String str) {
		String temp = "";
		for (int i = 0; i < length; i++){
			char ch = str.charAt(i);
			ch = '-';
			temp += ch;
		}
		return dashAndCorrectGuessedWord = temp;
	} 
	
	/*
	 * It takes user input. 
	 * if the user gives more than one letter as input. it will ask to enter again.
	 */
	private String userEntry() {

		String playerGuess = readLine("Your Guess : ");
		while (playerGuess.length() > 1 || playerGuess.length() == 0) {
			
			if (playerGuess.length() > 1)
			playerGuess = readLine("You entered more than one char. Please Re-Enter = ");
			
			if (playerGuess.length() == 0)
				playerGuess = readLine("You didnt enter any char. Please try again = ");
		}

		return playerGuess;

	}

	/*
	 * Simply selects a word randomly from the HangmanLexicon.
	 */
	private String selectWord() {

		int wordNumber = rgen.nextInt(0, (word.getWordCount() - 1));	
		return word.getWord(wordNumber);

	}




	/* Private instance variable */
	private int turn, correctGuess, lives = 3;
	private boolean wrongGuess;
	private String dashAndCorrectGuessedWord, playerGuess, wrongGuessTillNow;
	private HangmanLexicon word;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;

}
