import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGUI{
	
	private JFrame gameWindow=new JFrame("Cowboy Checkers");
	
	//private JPanel gameMenu=new JPanel();
	private JPanel gameBoard=new JPanel();
	
	private JPanel [][] gridPoints=new JPanel[7][7];
	
	public GameGUI(){
		
		makeFrame();
		//makeMenu();
		makeNewBoard();
	}
	private void makeFrame() {
		
		gameWindow.setSize(900,600);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(true);
		
		refreshGUI();
		
	}
	private void refreshGUI() {
		
		
		gameWindow.setVisible(true);
	}
	private void clearBoard() {
		
		gameBoard.removeAll();
	}
	private void makeNewBoard() {
		
		
			addBoardBackground();
			gameBoard.setVisible(true);
			
			gameWindow.add(gameBoard);
			
			refreshGUI();
		
		
	}
	private void addBoardBackground() {
		
		BufferedImage image;
		try {
			image = ImageIO.read(new File("img\\BlankBoard.png"));
			JLabel bgLabel=new JLabel(new ImageIcon(image));
			gameBoard.add(bgLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Invalid background file");
			e.printStackTrace();
		}
		
		
	}
	
}