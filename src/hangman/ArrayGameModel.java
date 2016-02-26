package hangman;

/**
 * The Array implementation of the GameModel interface.
 */
public class ArrayGameModel implements GameModel {
	/** The number of characters (lower/upper). */
	private static final int ALPHABET_COUNT = 26*2;
	
	/** hung state */
	private int	state;
	private char[] wGuessState;
	private String solution;
	private char[] PrevGuesses = new char[0];
	
	/**
	 * Creates a new ArrayGameModel object.
	 * 
	 *  guessWord the word to guess
	 */
	public ArrayGameModel(String guessWord) {
		// TODO (1)
		solution = guessWord;
		wGuessState = new char[guessWord.length()];
		for (int i = 0; i < wGuessState.length; i++){
			wGuessState[i] = '_';
		}
	}
		
	public boolean isPriorGuess(char guess) {
		boolean rval = false;
		for (int i = 0; i < PrevGuesses.length; i++){
			if (PrevGuesses[i] == guess){
				rval = true;
			}
		}
		return rval;
		//This method returns true if the character guess has been guessed previously.
	}
	
	public int numberOfGuesses() {
		
		return PrevGuesses.length;
		//This method returns the number of guesses already guessed (excluding repeated guesses)
	}
	
	public boolean isCorrectGuess(char guess) {
		boolean rval = false;
		if (isPriorGuess(guess) == false){
			for (int i = 0; i < solution.length(); i++){
				if (solution.charAt(i) == guess){
					rval = true;
				}
			}
		}
		return rval;
		/*This method returns true if the character guess is a guess that has not been guessed before and is a character
that is in the word to be guessed.*/
	}
	
	
	public boolean doMove(char guess) {
		boolean rval = false;
		if(!isPriorGuess(guess)){
			if (!isCorrectGuess(guess)){
				state++;
			}
			for (int i = 0; i < solution.length(); i++){
				if (solution.charAt(i) == guess){
					wGuessState[i] = guess;
					rval = true;
				}
			}
			char[] temp = new char[PrevGuesses.length + 1];
			for (int j = 0; j < PrevGuesses.length; j++){
				temp[j] = PrevGuesses[j];
			}
			PrevGuesses = temp;
			PrevGuesses[PrevGuesses.length-1] = guess;
		}
		return rval;
		//This method will play the character guess on the game board.
	}

	public boolean inWinningState() {
		boolean rval = false;
		for (int i = 0; i < solution.length(); i++){
			if (solution.charAt(i) == wGuessState[i]){
				rval = true;
			}
			else{
				rval = false;
				break;
			}
		}
		return rval;
		//This method returns true if the game is in a winning state.
	}

	public boolean inLosingState() {
		boolean rval = false;
		if (state == NUMBER_OF_STATES){
			rval = true;
		}
		return rval;
		//This method returns true if the game is in a losing state.
	}
	
	public String toString() {
		String s = "";
		
		for (int i = 0; i < wGuessState.length; i++){
			if(i == wGuessState.length - 1){
				s += wGuessState[i];
			}
			else{
				s += wGuessState[i] + " ";
			}
		}
		
		return s;
		//This method returns a string representation of the game. For example: L L
	}

	public String previousGuessString() {
		String s = "[";
		for (int i = 0; i < PrevGuesses.length; i++){
			if (i == PrevGuesses.length - 1){
				s += PrevGuesses[i] + "]";
			}
			else{
				s += PrevGuesses[i] + "," + " ";
			}

		}
		return s;
		/*This method returns a string representation of the previous guesses. For example: [L, a, b, C, x, g,
P, k, z, m, N, q, w, i] */
	}
	
	public int getState() {
		return state;
		//This method returns the current hung state (in the interval).
	}

	public String getWord() {

		return solution;
		//This method returns the word that the player is trying to guess.
	}
  
}
