import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGUI{
	
	private JFrame gameWindow=new JFrame("Cowboy Checkers");
	
	private JPanel gameMenu=new JPanel();
	private JPanel gameBoard=new JPanel();
	
	private JPanel [][] gridPoints=new JPanel[7][7];
	
	public GameGUI(){
		
		makeFrame();
		makeMenu();
		makeNewBoard();
	}
	private void makeMenu() {
		
		/*THIS METHOD IS JUST A PLACEHOLDER TO TEST PANEL PLACEMENT!!!*/
		gameWindow.getContentPane().add(gameMenu,BorderLayout.WEST);
		
		
		
		//layout.setVgap(200);
		
		gameMenu.setLayout(new GridLayout(4,0,0,100));
		
		
	
		JButton button1=new JButton("button1");
		JButton button2=new JButton("button2");
		JButton button3=new JButton("button3");
		JButton button4=new JButton("button4");
		
		gameMenu.add(button1);
		gameMenu.add(button2);
		gameMenu.add(button3);
		gameMenu.add(button4);
		
		
		
		
		
		refreshGUI();
		/*THIS METHOD IS JUST A PLACEHOLDER TO TEST PANEL PLACEMENT!!!*/
		
		
	}
	private void makeFrame() {
		
		gameWindow.setSize(600,600);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false);
		
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
			
			gameWindow.getContentPane().add(gameBoard,BorderLayout.EAST); //Adds gameboard to center of the frame
			
			refreshGUI();
		
		
	}
	private void addBoardBackground() {
		
		BufferedImage image;
		try {
			
			image = ImageIO.read(new File("img\\BlankBoard.png"));
			
			Image scaledImage=image.getScaledInstance(450, 450, Image.SCALE_DEFAULT); //Resizes the image so it isn't MASSIVE
			
			JLabel bgLabel=new JLabel(new ImageIcon(scaledImage));
			gameBoard.add(bgLabel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Invalid background file");
			e.printStackTrace();
		}
		
		
	}
	
}