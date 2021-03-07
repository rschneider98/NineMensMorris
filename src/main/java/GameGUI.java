import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;

public class GameGUI{
	
	private JFrame gameWindow=new JFrame("Cowboy Checkers");
	
	private JPanel gameMenu; //Menu and board are our only two panels so far
	private GamePanel gameBoard;
	private JTextField statusText;
	
	private GridPoint [] gridPoints=new GridPoint[24]; //Will be the 24 labled locations to place pieces
	
	private static final int BOARD_SIZE=450;
	private static final int PLACE_SIZE=20;
	
	public GameGUI(){ //creates the gui frame and containers
		
		makeFrame();
		makeMenu();
		makeNewBoard();
		makeStatusField();
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
	private void makeStatusField() {
		statusText=new JTextField("This is where status and stuff will go");
		
		gameWindow.getContentPane().add(statusText,BorderLayout.EAST);
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
		
		
		makeClickablePoints();
			
		gameBoard=new GamePanel();	
		gameBoard.setPreferredSize(new Dimension(450,450));	
		
		
		gameWindow.getContentPane().add(gameBoard,BorderLayout.CENTER); //Adds gameboard to EAST side of the frame
		
		//refreshGUI();
		
		
	}
	
	private void makeClickablePoints() { 
		
		final int BORDER=(int)(BOARD_SIZE/13.6); 
		final int STEP=(int)(BOARD_SIZE/6.98);
		
		/*^ These are the ratios between the distance between dots and the board size*/
		
		gridPoints[21]=new GridPoint(22,BORDER,BOARD_SIZE-BORDER);
		gridPoints[22]=new GridPoint(23,BORDER+3*STEP,BOARD_SIZE-BORDER);
		gridPoints[23]=new GridPoint(24,BORDER+6*STEP,BOARD_SIZE-BORDER);
		
		gridPoints[18]=new GridPoint(19,BORDER+STEP,BOARD_SIZE-(BORDER+STEP));
		gridPoints[19]=new GridPoint(20,BORDER+3*STEP,BOARD_SIZE-(BORDER+STEP));
		gridPoints[20]=new GridPoint(21,BORDER+5*STEP,BOARD_SIZE-(BORDER+STEP));
		
		gridPoints[15]=new GridPoint(16,BORDER+2*STEP,BOARD_SIZE-(BORDER+2*STEP));
		gridPoints[16]=new GridPoint(17,BORDER+3*STEP,BOARD_SIZE-(BORDER+2*STEP));
		gridPoints[17]=new GridPoint(18,BORDER+4*STEP,BOARD_SIZE-(BORDER+2*STEP));
		
		gridPoints[9]=new GridPoint(10,BORDER,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[10]=new GridPoint(11,BORDER+STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[11]=new GridPoint(12,BORDER+2*STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[12]=new GridPoint(13,BORDER+4*STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[13]=new GridPoint(14,BORDER+5*STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[14]=new GridPoint(15,BORDER+6*STEP,BOARD_SIZE-(BORDER+3*STEP));
		
		gridPoints[6]=new GridPoint(7,BORDER+2*STEP,BORDER+2*STEP);
		gridPoints[7]=new GridPoint(8,BORDER+3*STEP,BORDER+2*STEP);
		gridPoints[8]=new GridPoint(9,BORDER+4*STEP,BORDER+2*STEP);
		
		gridPoints[3]=new GridPoint(4,BORDER+STEP,BORDER+STEP);
		gridPoints[4]=new GridPoint(5,BORDER+3*STEP,BORDER+STEP);
		gridPoints[5]=new GridPoint(6,BORDER+5*STEP,BORDER+STEP);
		
		gridPoints[0]=new GridPoint(1,BORDER,BORDER);
		gridPoints[1]=new GridPoint(2,BORDER+3*STEP,BORDER);
		gridPoints[2]=new GridPoint(3,BORDER+6*STEP,BORDER);
		
		
			
		
						
		
	}
	
	private class GamePanel extends JPanel{
		
		
		private static final long serialVersionUID = 1L;
		private Image boardImage;
		
		
		public GamePanel() {
			addBoardBackground();
			addClickable();
		}
		private void addClickable() {
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					System.out.println("X: "+e.getX()+" Y: "+e.getY());
					GridPoint clickedPoint=getClickedPoint(e.getX(),e.getY());
					if(clickedPoint != null) {
						System.out.println("You have clicked point number "+clickedPoint.getID());
					}
				}
			});
		}
		private GridPoint getClickedPoint(int clickX, int clickY) {
			
			for(int x=0;x<gridPoints.length;x++) {
				
				GridPoint currPoint=gridPoints[x];
				
				if(Math.abs(currPoint.retX()-clickX)<=10 && Math.abs(currPoint.retY()-clickY)<=10) {
					return currPoint;
				}
			}
			
			return null;
		}
		private void setBackgroundImage(Image boardImage) {
			this.boardImage=boardImage;
			
			
		}
		private void addBoardBackground() { //adds the background image of the board
			
				
				try {
					
					Image image= ImageIO.read(new File("img\\BlankBoard.png"));
					
					Image scaledImage=image.getScaledInstance(BOARD_SIZE, BOARD_SIZE, Image.SCALE_DEFAULT); //Resizes the image so it isn't MASSIVE
					setBackgroundImage(scaledImage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
		}
		
		@Override
		protected void paintComponent(Graphics g) { //Draws the background image of the board
			super.paintComponent(g);
			
			g.drawImage(boardImage,0,0,null);
			
			setGridPoints();
			
			
		}
		
		private void setGridPoints() { //Places the gridpoints on the board
			
			for(int x=0;x<gridPoints.length;x++) {
				
				GridPoint currPoint = gridPoints[x];
				
				gameBoard.add(currPoint);
				
				currPoint.setLocation(currPoint.retX()-10,currPoint.retY()-10);
				
				
			}
			
		}
	}
	
}