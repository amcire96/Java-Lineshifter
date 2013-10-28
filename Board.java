
public class Board {
	private int[][] board;
	private final int length;
	private final int width;
	
	public Board(int length, int width){
		this.length = length;
		this.width = width;
		board = new int[length][width];
		for(int i = 0; i < length; i ++){
			for(int j = 0; j < width; j++){
				board[i][j] = -2;
			}
		}
	}
	
	public boolean isEmpty(){
		for(int i = 0; i < length; i ++){
			for(int j = 0; j < width; j++){
				if(board[i][j] != -2){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isValid(int rowNum, int colNum){
		boolean inBounds = true;
		if(rowNum >= board.length || colNum >= board[0].length){
			inBounds = false;
		}
		else if(rowNum < 0 || colNum < 0){
			inBounds = false;
		}
		return inBounds;
	}
	
//	public void shiftDown(int upToRowNum){
//		for(int i = upToRowNum-1; i >-1; i--){
//			for(int j = 0; j < width; j++){
//				if(!board[i][j]){
//					board[i+1][j] = board[i][j];
//					board[i][j] = 0;
//					
//				}
//				
//			}
//		}
//	}
	
//	public boolean checkTetris(int rowNum){
//		for(int j = 0; j < width; j++){
//			if(board[rowNum][j] == 0){
//				return false;
//			}
//		}
//		return true;
//	}
	
	public int getLength(){
		return length;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setValue(int row, int col, int colorNum){
		board[row][col] = colorNum;
	}
	
	public int getValue(int row, int col){
		return board[row][col];
	}
	
	public String toString(){
		String print = new String();
		for (int i = 0; i < length; i++) {
	 		for (int j = 0; j < width; j++) {
	 			if(board[i][j] == -2){
	 				print += "f ";
	 			}
	 			else{
	 				print += board[i][j] + " ";
	 			}
        //	   print += board[i][j] + " ";
     		}
     	print += "\n";
		}
		return print;
	}
	
	public static void main(String[] args){
		Board board = new Board(15,15);
		
		board.setValue(0,4,1);
		board.setValue(10,3,1);
		for(int i = 0; i < board.length; i++){
			board.setValue(i,0,1);
		}
		for(int i = 0; i < board.length; i++){
			board.setValue(i,1,1);
		}
		System.out.println(board);
	}
}
