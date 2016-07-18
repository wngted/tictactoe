package tictactoe;

/** This Marker is a winning seeker.
 * @see Marker */
public class Make3Marker extends Marker{

	@Override
	protected int[] handle(TicTacToe game) {
		Integer next = BoardSupport.getWinningPos(game.board, Computer.MARK);
		if(next!=null)
			return new int[]{next/3, next%3};
		else
			return null;
	}

}
