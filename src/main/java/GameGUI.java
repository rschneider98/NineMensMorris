import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGUI{
	
	private JFrame gameWindow=new JFrame("Cowboy Checkers");
	
	private JPanel gameMenu; //Menu and board are our only two panels so far
	private GamePanel gameBoard;
	
	private GridPoint [] gridPoints=new GridPoint[24]; //Will be the 24 labled locations to place pieces
	
	//private static final int BOARD_SIZE=450;
	
	//private BufferedImage boardImage=new BufferedImage();
	
	public GameGUI(){ //creates the gui frame and containers
		
		makeFrame();
		makeMenu();
		makeNewBoard();
		gameWindow.pack();
		gameWindow.setVisible(true);
	}
	private void makeMenu() {
		
		/*THIS METHOD IS JUST A PLACEHOLDER TO TEST PANEL PLACEMENT!!!*/
		
		gameMenu=new JPanel();
		
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
		
		
		
		
		
		
		/*THIS METHOD IS JUST A PLACEHOLDER TO TEST PANEL PLACEMENT!!!*/
		
		
	}
	private void makeFrame() {
		
		gameWindow.setSize(800,600); //We can decide on size later. Will write code so that it does not matter
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(true); //We need to revisit this and see what we prefer
		
		
		
	}
	private void refreshGUI() {
		
		gameWindow.setVisible(true);
	}
	private void clearBoard() {
		
		gameBoard.removeAll();
	}
	private void makeNewBoard() {
		
		
		//	makeClickablePoints();
			
		gameBoard=new GamePanel();	
		gameBoard.setPreferredSize(new Dimension(450,450));	
		
		
		gameWindow.getContentPane().add(gameBoard,BorderLayout.EAST); //Adds gameboard to EAST side of the frame
		
		//refreshGUI();
		
		
	}
	
	private void makeClickablePoints() { 
		
		GridPoint myPoint=new GridPoint(1);
		
		if(myPoint.isEmpty()) {
			myPoint.acceptPiece("White");
		}
		
		gameBoard.add(myPoint);
		
		myPoint.setVisible(true);
		
	}
	
}