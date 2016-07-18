package tictactoe;

public class Board {
	private Mark[][] grid;
	
	{
		grid = new Mark [3][3];
		for( int i=0; i<3; i++ ){
			grid[i] = new Mark[3];
			for( int j=0; j<3; j++ )
				grid[i][j] = Mark.EMPTY;
		}
	}
	
	public void setMark(Mark mark, int[] pos){
		grid[pos[0]][pos[1]] = mark;
	}
	
	public Mark getMark(int row, int col){
		return grid[row][col];
	}
}
