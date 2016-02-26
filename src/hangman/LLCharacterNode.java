package hangman;

public class LLCharacterNode {

	private char aChar;
	private LLCharacterNode nChar;
	private LLCharacterNode pChar;

	public LLCharacterNode(char c) {
		this.aChar = c;
		this.nChar = null;
	}

	public void setLink(LLCharacterNode b) {
		this.nChar = b;
		// nChar.setPrev(this);

	}

	public LLCharacterNode getLink() {
		return nChar;
	}

	public char getInfo() {
		return aChar;
	}

	public void setInfo(char a) {
		aChar = a;
	}

	public void setPrev(LLCharacterNode p) {
		this.pChar = p;
	}

	public LLCharacterNode getPrev() {
		return pChar;
	}

}

/*
 * It must have a constructor that takes a single char argument, a getter named
 * getInfo that returns the stored char, and a pair of getters/setters named
 * getLink and setLink that allow creation and traversal of the list. Use the
 * LLCharacterNode in your implementation of the LinkedListGameModel class.
 */