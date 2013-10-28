
public class Piece {
	private int gapSize;
	private Board board;
	private int colorNum;
	public Piece(int gapSize, Board board, int colorNum){
		this.gapSize = gapSize;
		this.board = board;
		this.colorNum = colorNum;
	}
	public void setColorNum(int newColorNum){
		colorNum = newColorNum;
	}
	public int getColorNum(){
		return colorNum;
	}
	public void setGapSize(int newGapSize){
		gapSize = newGapSize;
	}
	public int getGapSize(){
		return gapSize;
	}
	public int possibleGapStartIndex(){
		int randomInt = (int)(Math.random() * (board.getLength()-gapSize));
		return randomInt;
	}
	public void removeGap(int colNum){
		int possibleGapStartIndex = possibleGapStartIndex();
		for(int i = possibleGapStartIndex; i<possibleGapStartIndex+gapSize; i++){
			board.setValue(i, colNum, -2);
		}
	}
	public void placeInBoard(int colNum){
		for(int i = 0; i < board.getLength(); i++){
			board.setValue(i, colNum, colorNum);
		}
		removeGap(colNum);
	}
	
	public static void main(String[] args){
	}
}
