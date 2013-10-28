import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GUI extends JComponent{
	private static Comp comp;
	private static JFrame frame;
	private static JPanel labelPanel;
	private static int score;
	private static JLabel scoreLabel = new JLabel("Score: " + 0);
	private static JLabel levelLabel = new JLabel("Level: " + 1);
	private static int levelCount = 1;
	private static Game game = new Game();
	private static Timer compTimer;
	private static Timer timer;
	private static int cellSize = 6;
	public static Timer getTimer() {
		return timer;
	}
	
	public static Game getGame(){
		return game;
	}

	public static void setTimer(Timer timer) {
		GUI.timer = timer;
	}
	
	public static JLabel getLevelLabel() {
		return levelLabel;
	}

	public static void setLevelLabel(JLabel levelLabel) {
		GUI.levelLabel = levelLabel;
	}

	public static int getLevelCount() {
		return levelCount;
	}

	public static void setLevelCount(int levelCount) {
		GUI.levelCount = levelCount;
	}
	
	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GUI.score = score;
	}
	
	public static JLabel getScoreLabel() {
		return scoreLabel;
	}

	public static void setScoreLabel(JLabel scoreLabel) {
		GUI.scoreLabel = scoreLabel;
	}
	
	
	public static void reset(){
		resetBoard();
		game.setCycleNumber(1);
		score = 0;
		scoreLabel.setText("Score: " + score);
		levelCount = 1;
		levelLabel.setText("Level: " + levelCount);
		timer.setDelay(timer.getInitialDelay());
		game.playGame();
	}
	
	public static void resetBoard(){
		Board gameBoard = game.getBoard();
		for(int i = 0; i < gameBoard.getLength(); i ++){
			for(int j = 0; j < gameBoard.getWidth(); j++){
				gameBoard.setValue(i, j, -2);
			}
		}
	}
	
	public static void loseGameMessage(){
		String[] playAgain = {"Yes", "No"};
		int choiceNumber =  JOptionPane.showOptionDialog(frame, "You Lost \n" +
				"Play Again?", "YOU LOSE!",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, playAgain, playAgain[0]);
		if(choiceNumber == 0){
			reset();
		}
		if(choiceNumber == 1){
			System.exit(0);
		}
	}
	
	public static void frameMaker(){
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Line Shifter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelPanel = new JPanel();
		frame.add(labelPanel,BorderLayout.SOUTH);
		labelPanel.add(scoreLabel,BorderLayout.EAST);
		labelPanel.add(levelLabel,BorderLayout.WEST);
	}
	
	public static void boardMaker(){
		GUI component = new GUI();
		frame.add(component);
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                arrowDetection(evt);
            }
        });
	}
	
	public static void arrowDetection(KeyEvent evt){
		  switch (evt.getKeyCode()) {
        case KeyEvent.VK_DOWN:
          	game.descend();
            
            break;
        case KeyEvent.VK_UP:
        	game.ascend();
        	
            break;
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_RIGHT:

    }
	}
	
	private static ActionListener actionListener1 = new ActionListener() {
	   	public void actionPerformed(ActionEvent actionEvent) {
	   			game.cycle();
	   	}
	 };
	public static void timerMaker(){
		timer = new Timer(50,actionListener1);
		timer.start();
	}
	
	public static void compTimerMaker(){
		compTimer = new Timer(10,actionListener2);
		compTimer.start();
	}
	private static ActionListener actionListener2 = new ActionListener(){
	   	public void actionPerformed(ActionEvent actionEvent) {
   			comp.chooseAction();
   	}
	};
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		for(int row = 0; row < game.getBoard().getLength(); row++){
			for(int col = 0; col < game.getBoard().getWidth(); col++){
				Rectangle rec = new Rectangle(col *cellSize,row*cellSize,cellSize,cellSize);
				if(game.getBoard().getValue(row, col) != -2){

					if(levelCount%5 == 0){
						g2.setColor(Color.BLUE);
					}
					else if(levelCount%5 == 1){
						g2.setColor(Color.GREEN);
					}
					else if(levelCount%5 == 2){
						g2.setColor(Color.MAGENTA);
					}
					else if(levelCount%5 == 3){
						g2.setColor(Color.RED);
					}
					else if(levelCount%5 == 4){
						g2.setColor(Color.ORANGE);
					}
	
					
					if(game.getBoard().getValue(row, col) == -1){
						g2.setColor(Color.PINK);
						
					}	
					g2.fill(rec);
				}
				g2.setColor(Color.black);
				
				
				repaint();
			}
		}
	}
	
	public static void compGenerator(){
		comp = new Comp();
		
	}
	
	public static void startGame(){
		frameMaker();
		boardMaker();
		timerMaker();
		game.playGame();
		compGenerator();
		compTimerMaker();
		SoundEffect.music().loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public static void main(String [] args){
		startGame();
	}
}
