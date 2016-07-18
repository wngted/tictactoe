package tictactoe;

/** This Marker tries to block Human player to make a 3.
 * @see Marker */
public class BlockMarker extends Marker {
	private TrackingAndValidationService losingTrackingService = new TrackingAndValidationService();
	
	@Override
	protected int[] handle(TicTacToe game) {
		Integer w = BoardSupport.getWinningPos(game.board, Human.MARK);
		if( w==null )  // failed to use this strategy
			return null;
		
		if( BoardSupport.computerWillLose(game.board, w) ){
			losingTrackingService.addLosingTrack(game.board);
		}
		return new int[]{w/3, w%3};
	}

}
