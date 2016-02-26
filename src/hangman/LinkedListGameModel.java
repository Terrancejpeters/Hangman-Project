package hangman;

public class LinkedListGameModel implements GameModel {

	private int state = STARTING_STATE;
	private String solution;
	private LLCharacterNode wGuessState;
	private LLCharacterNode PrevGuesses;
	private int numGuesses = 0;

	public LinkedListGameModel(String guessWord) {
		solution = guessWord;
		wGuessState = new LLCharacterNode('_');
		LLCharacterNode temp = wGuessState;
		for (int i = 1; i < guessWord.length(); i++) {
			temp.setLink(new LLCharacterNode('_'));
			temp = temp.getLink();
		}
	}

	@Override
	public boolean isPriorGuess(char guess) {
		boolean rval = false;
		if (PrevGuesses != null) {
			LLCharacterNode temp = PrevGuesses;
			while (temp != null) {
				if (temp.getInfo() == guess) {
					rval = true;
				}
				temp = temp.getLink();
			}
		}
		return rval;
		// This method returns true if the character guess has been guessed
		// previously.
	}

	@Override
	public int numberOfGuesses() {
		// TODO Auto-generated method stub
		return numGuesses;
		// This method returns the number of guesses already guessed (excluding
		// repeated guesses)
	}

	@Override
	public boolean isCorrectGuess(char guess) {
		boolean rval = false;
		LLCharacterNode temp = wGuessState;
		for (int i = 0; i < solution.length(); i++) {
			if (solution.charAt(i) == guess && temp.getInfo() != solution.charAt(i)) {
				// temp.setInfo(guess);
				rval = true;
			}

			if (temp.getLink() != null) {
				temp = temp.getLink();
			} else {
				break;
			}
		}
		for (int i = solution.length() - 1; i <= 0; i--) {
			if (solution.charAt(i) == guess && temp.getInfo() != solution.charAt(i)) {
				// temp.setInfo(guess);
				rval = true;
			}
			if (temp.getPrev() != null) {
				temp = temp.getPrev();
			}
		}

		return rval;
		/*
		 * This method returns true if the character guess is a guess that has
		 * not been guessed before and is a character that is in the word to be
		 * guessed.
		 */
	}

	@Override
	public boolean doMove(char guess) {
		boolean rval = false;
		if (numGuesses == 0) {
			numGuesses++;
			PrevGuesses = new LLCharacterNode(guess);
			if (isCorrectGuess(guess)) {
				rval = true;
				// so the tests DO NOT like if you assign chars to wGuessState
				// in isCorrectGuess, so I cut and pasted the code that assigns
				// the values here
				LLCharacterNode temp = wGuessState;
				for (int i = 0; i < solution.length(); i++) {
					if (solution.charAt(i) == guess && temp.getInfo() != solution.charAt(i)) {
						temp.setInfo(guess);
						// rval = true;
					}

					if (temp.getLink() != null) {
						temp = temp.getLink();
					} else {
						break;
					}
				}
				for (int i = solution.length() - 1; i <= 0; i--) {
					
					if (solution.charAt(i) == guess && temp.getInfo() != solution.charAt(i)) {
						temp.setInfo(guess);
						// rval = true;
					}
					if (temp.getPrev() != null) {
						temp = temp.getPrev();
					}
				}
			} else {
				rval = false;
				state++;
			}
		} else {
			if (!isPriorGuess(guess)){
				numGuesses++;
			}
			if (isCorrectGuess(guess)) {
				rval = true;
				LLCharacterNode temp = wGuessState;
				for (int i = 0; i < solution.length(); i++) {
					if (solution.charAt(i) == guess && temp.getInfo() != solution.charAt(i)) {
						temp.setInfo(guess);
						// rval = true;
					}

					if (temp.getLink() != null) {
						temp = temp.getLink();
					} else {
						break;
					}
				}
				for (int i = solution.length() - 1; i <= 0; i--) {
					if (solution.charAt(i) == guess && temp.getInfo() != solution.charAt(i)) {
						temp.setInfo(guess);
						// rval = true;
					}
					if (temp.getPrev() != null) {
						temp = temp.getPrev();
					}
				}
				//add correct guess to PrevGuesses
				LLCharacterNode tempPrev = PrevGuesses;
				LLCharacterNode last = tempPrev;
				LLCharacterNode slast = last;
				while (last != null) {
					slast = last;
					last = last.getLink();
				}
				LLCharacterNode guessnode = new LLCharacterNode(guess);
				slast.setLink(guessnode);
			} else {
				if (!isPriorGuess(guess)) {
					LLCharacterNode tempPrev = PrevGuesses;
					LLCharacterNode last = tempPrev;
					LLCharacterNode slast = last;
					while (last != null) {
						slast = last;
						last = last.getLink();
					}
					LLCharacterNode guessnode = new LLCharacterNode(guess);
					slast.setLink(guessnode);
					state++;
				}
			}
		}
		
		return rval;
		// This method will play the character guess on the game board.
	}

	@Override
	public boolean inWinningState() {
		boolean rval = true;
		LLCharacterNode temp = wGuessState;
		for (int i = 0; i < solution.length(); i++) {
			if (solution.charAt(i) != temp.getInfo()) {
				rval = false;
				break;
			}
			temp = temp.getLink();
		}
		return rval;
		// This method returns true if the game is in a winning state.
	}

	@Override
	public boolean inLosingState() {
		boolean rval = false;
		if (state == 10) {
			rval = true;
		}
		return rval;
		// This method returns true if the game is in a losing state.
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return state;
		// This method returns the current hung state (in the interval).
	}

	@Override
	public String previousGuessString() {
		String rval = "[";
		LLCharacterNode temp = PrevGuesses;
		while (temp != null) {
			if (temp.getLink() != null) {
				rval = rval + temp.getInfo() + ", ";
			} else {
				rval = rval + temp.getInfo();
			}
			temp = temp.getLink();
		}
		rval += ']';
		return rval;
		/*
		 * This method returns a string representation of the previous guesses.
		 * For example: [L, a, b, C, x, g, P, k, z, m, N, q, w, i]
		 */
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return "";
		// This method returns the word that the player is trying to guess.
	}

	public String toString() {
		String s = "";
		int i = 0;
		LLCharacterNode temp = wGuessState;
		while (temp != null) {
			if (i != solution.length() - 1) {
				s = s + temp.getInfo() + " ";
				temp = temp.getLink();
				i++;
			} else {
				s = s + temp.getInfo();
				temp = temp.getLink();
			}

		}

		// TODO (8)

		return s;
		// This method returns a string representation of the game. For example:
		// L L
	}

}
