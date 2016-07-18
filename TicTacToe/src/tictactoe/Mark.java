package tictactoe;

public enum Mark {
	EMPTY (' '), CROSS('X'), NOUGHT('O');
	
	private char mark;
	
	Mark(char mark){
		this.mark = mark;
	}
	
	public char mark(){
		return mark;
	}
}
