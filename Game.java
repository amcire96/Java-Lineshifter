import javax.sound.sampled.Clip;

/*
 * Eric Ma
 * This class creates the game of LineShifter. The game involves shifting the moving lines so that the square doesn't hit the lines
 * The Board class creates a grid where the game is played
 * The Comp class implements a simple algorithm so that the computer can play the game
 * The GUI class creates the graphics for the game
 * The Piece class creates the moving lines that move towards the left side of the screen
 * The SoundEffect class loads the sound effect clips
 */
public class Game {
	private Board board;
	private Piece piece1;
	private Piece piece2;
	private final int boardLength = 100;
	private final int boardWidth = 100;
	private int cycleNumber = 0;


	public Game(){
		board = new Board(boardLength,boardWidth);
	}
	
	public int getCycleNumber() {
		return cycleNumber;
	}

	public void setCycleNumber(int cycleNumber) {
		this.cycleNumber = cycleNumber;
	}
	public Board getBoard(){
		return board;
	}
	
	public Piece getPiece1(){
		return piece1;
	}
	
	public Piece getPiece2(){
		return piece2;
	}
	
	public int findRowNumOfTopFence(){
		eraseCenter();
		for(int col = 0; col <= (boardWidth-1)/2; col++){
			for(int row = 0; row < boardLength; row++){
				if(board.getValue(row, col) != -2)
					if(board.isValid(row-1, col)){
						if(board.getValue(row-1,col) == -2){
							markCenter();
							return row;
						}
					}
			}
		}
		markCenter();
		return -1;
		
	}
	
	public int findRowNumOfBottomFence(){
		eraseCenter();
		for(int col = 0; col <= (boardWidth-1)/2; col++){
			for(int row = 0; row < boardLength; row++){
				if(board.getValue(row, col) != -2)
					if(board.isValid(row+1, col)){
						if(board.getValue(row+1,col) == -2){
							markCenter();
							return row;
						}
					}
			}
		}
		markCenter();
		return -1;
		
	}
	
	public void ascend(){
		eraseCenter();

		for(int col = 0; col < board.getWidth(); col++){
			int[] values = new int[board.getLength()];			
			for(int i = 0; i < values.length; i++){
				values[i] = -2;
			}
			for(int row = 0; row < board.getLength();row++){
				if(board.getValue(row, col) != -2){
					if(board.isValid(row-2, col)){
						values[row-2] = board.getValue(row, col);
					}
					else if(board.isValid(row-1,col)){
						values[values.length-1] = board.getValue(row, col);
					} 
					else{
						values[values.length -2] = board.getValue(row,col);
					}
				}
				

			}
			for(int i = 0; i < values.length; i++){
				board.setValue(i, col, values[i]);
			}
		}
		markCenter();
	}
	
	public void descend(){
		eraseCenter();

		for(int col = 0; col < board.getWidth(); col++){
			int[] values = new int[board.getLength()];	
			for(int i = 0; i < values.length; i++){
				values[i] = -2;
			}
			for(int row = board.getLength()-1; row >= 0;row--){
				if(board.getValue(row, col) != -2){
					if(board.isValid(row+2, col)){
						values[row+2] = board.getValue(row, col);
					}
					else if(board.isValid(row+1,col)){
						values[0] = board.getValue(row, col);
					} 
					else{
						values[1] = board.getValue(row,col);
					}
				}
				

			}
			for(int i = 0; i < values.length; i++){
					
				board.setValue(i, col, values[i]);
			}
		}
		markCenter();
	}
	

	public void markCenter(){
		board.setValue((boardLength-1)/2, 0, -1);
		board.setValue((boardLength-1)/2 - 1, 0, -1);
		board.setValue((boardLength-1)/2 + 1, 0, -1);
		board.setValue((boardLength-1)/2, 1, -1);
		board.setValue((boardLength-1)/2 - 1, 1, -1);
		board.setValue((boardLength-1)/2 + 1, 1, -1);
		board.setValue((boardLength-1)/2, 2, -1);
		board.setValue((boardLength-1)/2 - 1, 2, -1);
		board.setValue((boardLength-1)/2 + 1, 2, -1);
	}
	
	public void eraseCenter(){
		board.setValue((boardLength-1)/2, 0, -2);
		board.setValue((boardLength-1)/2 - 1, 0, -2);
		board.setValue((boardLength-1)/2 + 1, 0, -2);
		board.setValue((boardLength-1)/2, 1, -2);
		board.setValue((boardLength-1)/2 - 1, 1, -2);
		board.setValue((boardLength-1)/2 + 1, 1, -2);
		board.setValue((boardLength-1)/2, 2, -2);
		board.setValue((boardLength-1)/2 - 1, 2, -2);
		board.setValue((boardLength-1)/2 + 1, 2, -2);
	}

	public void shiftLeft(){
		for(int i = 0; i < board.getLength(); i++){
			for(int j = 0; j < board.getWidth();j++){
				if(board.getValue(i, j) != -2){
					if(board.isValid(i, j-1)){
						board.setValue(i, j-1, board.getValue(i, j));
					}
					board.setValue(i, j, -2);
				}
			}
		}
	}
	
	public void placeInitialPiece1(int gapSize, int colorNum){
		piece1 = new Piece(gapSize,board,colorNum);
		piece1.placeInBoard((boardWidth-1)/2);
	}
	
	public void placeInitialPiece2(int gapSize, int colorNum){
		piece2 = new Piece(gapSize,board,colorNum);
		piece2.placeInBoard((boardWidth-1));
	}
	
	public void placePiece(int gapSize, int colorNum){
		piece2 = new Piece(gapSize,board,colorNum);
		piece2.placeInBoard(boardWidth-1);
	}
	
	public void placeNewPiece(int gapSize, int levelNum){
		piece1 = piece2;
		piece2 = new Piece(gapSize,board,levelNum);
		placePiece(gapSize, levelNum);
	}
	
	public void cycle(){
		cycleNumber++;
		eraseCenter();
		shiftLeft();
		if(board.getValue((boardLength-1)/2, 0) != -2
				|| board.getValue((boardLength-1)/2 - 1, 0) != -2
				|| board.getValue((boardLength-1)/2 + 1, 0) != -2
				|| board.getValue((boardLength-1)/2, 1) != -2
				|| board.getValue((boardLength-1)/2 - 1, 1) != -2
				|| board.getValue((boardLength-1)/2 + 1, 1) != -2
				|| board.getValue((boardLength-1)/2, 2) != -2
				|| board.getValue((boardLength-1)/2 - 1,2) != -2
				|| board.getValue((boardLength-1)/2 + 1, 2) != -2){
			SoundEffect.getExplosionClip().start();
			GUI.loseGameMessage();
		}
		if(cycleNumber%((boardWidth-1)/2 + 1) == 0){
			GUI.setScore(GUI.getScore() + 1);
			GUI.getScoreLabel().setText("Score: " + GUI.getScore());
			GUI.setLevelCount((int)(GUI.getScore() / 10) + 1);
			GUI.getLevelLabel().setText("Level: " + GUI.getLevelCount());
			if(GUI.getScore()%10 == 0){
				SoundEffect.getNextLevelClip().start();
				GUI.getTimer().setDelay((int)(GUI.getTimer().getDelay() * .8));
			}
			int gapSize = (int)(Math.random()*10) + boardLength/6;
			placeNewPiece(gapSize,GUI.getLevelCount());			
			
		}
		markCenter();

	}
	
	public String toString(){
		return board.toString();
	}
	
	public void playGame(){
		markCenter();
		int gapSize = (int)(Math.random()*10) + boardLength/6;
		placeInitialPiece1(gapSize,GUI.getLevelCount());
		placeInitialPiece2(gapSize,GUI.getLevelCount());
//		GUI.getTimer().start();
		
	}
	
}
