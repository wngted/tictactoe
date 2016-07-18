package tictactoe;

public abstract class Player {
	public abstract void mark();
	String myName;
	Mark myMark;
	TicTacToe game;
	
	Player(TicTacToe game)
	{
		this.game = game;
	}
	
	void placeMark(int pos){
		game.markCell(myMark, pos);
	}
}
