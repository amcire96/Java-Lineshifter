import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
public class Comp {
	private Robot robot;
	private Game game;
	
	public Comp(){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		game = GUI.getGame();
	}
	
	public void pressUpKey(){
		robot.keyPress(KeyEvent.VK_UP);
	}
	
	public void pressDownKey(){
		robot.keyPress(KeyEvent.VK_DOWN);
	}
	

	
	public void chooseAction(){
		if(game.findRowNumOfBottomFence()>(game.getBoard().getLength()-1)/2 +1
				&& game.findRowNumOfTopFence()<(game.getBoard().getLength()-1)/2 -1){
			int topGap = game.findRowNumOfTopFence()-0;
			int bottomGap = game.getBoard().getLength() - game.findRowNumOfBottomFence();
			if(topGap > bottomGap){
				pressDownKey();
			}
			else{
				pressUpKey();
			}

		}
		else{
			int middle = (game.findRowNumOfBottomFence()+game.findRowNumOfTopFence())/2;
			if(middle == (game.getBoard().getLength()-1)/2
				|| middle == (game.getBoard().getLength()-1)/2 +1){
					return;
				}
			if(middle > (game.getBoard().getLength()-1)/2){
				pressUpKey();
			}
			else if(middle < (game.getBoard().getLength()-1)/2){
				pressDownKey();
			}
		}
	}
	
}
