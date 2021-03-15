package main.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameGUI{
	
	private JFrame gameWindow=new JFrame("Cowboy Checkers");
	
	private JPanel gameMenu; //Menu and board are our only two panels so far
	private GamePanel gameBoard;
	private JTextArea statusText;
	
	private GridPoint [] gridPoints=new GridPoint[24]; //Will be the 24 labeled locations to place pieces
	List<Piece> blackPieces=new ArrayList<Piece>();
	List<Piece> whitePieces=new ArrayList<Piece>();
	int blackRemainPieces=9;	
	int whiteRemainPieces=9;

	JPanel playerPanel = new JPanel(new BorderLayout()); 
	
	public static final int PLAYER_PIECES=9;
	public static final int BOARD_SIZE=450;
	public static final int PLACE_SIZE=20;
	public static final int OFFSET=PLACE_SIZE/2;
	
	private boolean whiteTurn=true;
	
	public GameGUI(){ //creates the gui frame and containers
		
		makeFrame();
		makeMenu();
		makeNewBoard();
		makeStatusField();
		makePlayersPanel();
		gameWindow.pack();
		gameWindow.setVisible(true);
	}
	
	private void makeMenu() {
		
		/*THIS METHOD IS JUST A PLACEHOLDER TO TEST PANEL PLACEMENT!!!*/
		
		gameMenu=new JPanel();
		
		gameWindow.getContentPane().add(gameMenu,BorderLayout.WEST);
		
		
		
		//layout.setVgap(200);
		
		gameMenu.setLayout(new GridLayout(4,0,0,100));
		
		
	
		JButton newGameButton=new JButton("New Game");		
		JButton aboutButton=new JButton("About");
		JButton optionsButton=new JButton("Options");
		JButton quitButton=new JButton("Quit");
		
		gameMenu.add(newGameButton);
		gameMenu.add(aboutButton);
		gameMenu.add(optionsButton);
		gameMenu.add(quitButton);
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGameClick();
			}
		});
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutClick();
			}
		});
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsClick();
			}
		});
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitClick();
			}
		});
		
		
		
		
		
		
		/*THIS METHOD IS JUST A PLACEHOLDER TO TEST PANEL PLACEMENT!!!*/
		
		
	}
	private void newGameClick() {
		
		
		
	}
	private void aboutClick() {
		String aboutMessage="Created by:\nMatt Miller\nPaula Salazar Castano\nWilliam Scheer\nRichard Scheider\nJashandeep Singh";
		/*^We can totally add more stuff to this, it is just a placeholder for now*/
		
		JOptionPane.showMessageDialog(gameWindow, aboutMessage);
		
	}
	private void optionsClick() {
		String[] values= {"Human","Computer"};
		
		JOptionPane.showInputDialog(gameWindow,"What kind of opponent will you face today? ","Selection", JOptionPane.DEFAULT_OPTION, null, values, "0");
		
		
	}
	private void quitClick() {
		
		int confirmed = JOptionPane.showConfirmDialog(gameWindow, 
		        "Are you sure you want to exit the program?", "Exit Program Message Box",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		    	gameWindow.dispose();
		    }
		      
		
	}
	private void makeStatusField() {
		statusText=new JTextArea("Welcome to Cowboy Checkers!!\n",10,25);
		statusText.setWrapStyleWord(true);
		
		gameWindow.getContentPane().add(statusText,BorderLayout.EAST);
	}
	private void makeFrame() {
		
		gameWindow.setSize(1100,600); //We can decide on size later. Will write code so that it does not matter
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setResizable(false); //We need to revisit this and see what we prefer
		
		
		
	}
	private void clearBoard() { //Unused method but will later be used to clear the board and start new game
		
		gameBoard.removeAll();
	}
	private void makeNewBoard() {
		
		
		makeClickablePoints();
			
		gameBoard=new GamePanel();
		gameBoard.setPreferredSize(new Dimension(450,450));	
		
		
		gameWindow.getContentPane().add(gameBoard,BorderLayout.CENTER); //Adds gameboard to EAST side of the frame
		
		//refreshGUI();
		
		
	}
	public class PlayersPanel extends JComponent {

        private static final long serialVersionUID = 1L;
    	private static final int SIZE=20;
    	private static final int PIECE_SIZE=15;
    	List<Piece> p1Pieces;
    	List<Piece> p2Pieces;

        PlayersPanel(List<Piece> p1Pieces,List<Piece> p2Pieces) {
            setPreferredSize(new Dimension(500, 100));
            this.p1Pieces=p1Pieces;
            this.p2Pieces=p2Pieces;

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Piece piece : p1Pieces) {
		
            	piece.drawPiece(g);
            }
            for (Piece piece : p2Pieces) {
        		
            	piece.drawPiece(g);
            }
            Font font = g.getFont().deriveFont( 30.0f );
            g.setColor(Color.white);
            g.setFont( font );

            if(whiteTurn) {
            	g.drawString("Player 1 Turn", 300, 80);
            }else {
            	g.drawString("Player 2 Turn", 300, 80);
            }

        }
    }
	
	private void makePlayersPanel() {
		// Player panel with white and black pieces
        
        
    int x1=0;
	for(int i=1; i<=PLAYER_PIECES;i++) {
	   Piece piece=new Piece( 40+x1, 50);
       piece.setPieceColor(Color.BLACK);		       
       blackPieces.add(piece);
       x1+=35;

    }
    x1=400;
	for(int i=1; i<=PLAYER_PIECES;i++) {
		   Piece piece=new Piece( 40+x1, 50);
	       piece.setPieceColor(Color.WHITE);		       
	       whitePieces.add(piece);
	       x1+=35;

	    }
	playerPanel.setBackground(Color.DARK_GRAY);
	playerPanel.add(new PlayersPanel(blackPieces,whitePieces));

	gameWindow.getContentPane().add(playerPanel,BorderLayout.NORTH);
		
	}
	
	private void makeClickablePoints() { 
		
		final int BORDER=(int)(BOARD_SIZE/13.6); //distance from border
		final int STEP=(int)(BOARD_SIZE/6.98);	//distance between each point on 7x7 grid
		
		/*^ These are the ratios between the distance between dots and the board size*/
		
		gridPoints[21]=new GridPoint(21,BORDER,BOARD_SIZE-BORDER);
		gridPoints[22]=new GridPoint(22,BORDER+3*STEP,BOARD_SIZE-BORDER);
		gridPoints[23]=new GridPoint(23,BORDER+6*STEP,BOARD_SIZE-BORDER);
		
		gridPoints[18]=new GridPoint(18,BORDER+STEP,BOARD_SIZE-(BORDER+STEP));
		gridPoints[19]=new GridPoint(19,BORDER+3*STEP,BOARD_SIZE-(BORDER+STEP));
		gridPoints[20]=new GridPoint(20,BORDER+5*STEP,BOARD_SIZE-(BORDER+STEP));
		
		gridPoints[15]=new GridPoint(15,BORDER+2*STEP,BOARD_SIZE-(BORDER+2*STEP));
		gridPoints[16]=new GridPoint(16,BORDER+3*STEP,BOARD_SIZE-(BORDER+2*STEP));
		gridPoints[17]=new GridPoint(17,BORDER+4*STEP,BOARD_SIZE-(BORDER+2*STEP));
		
		gridPoints[9]=new GridPoint(9,BORDER,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[10]=new GridPoint(10,BORDER+STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[11]=new GridPoint(11,BORDER+2*STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[12]=new GridPoint(12,BORDER+4*STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[13]=new GridPoint(13,BORDER+5*STEP,BOARD_SIZE-(BORDER+3*STEP));
		gridPoints[14]=new GridPoint(14,BORDER+6*STEP,BOARD_SIZE-(BORDER+3*STEP));
		
		gridPoints[6]=new GridPoint(6,BORDER+2*STEP,BORDER+2*STEP);
		gridPoints[7]=new GridPoint(7,BORDER+3*STEP,BORDER+2*STEP);
		gridPoints[8]=new GridPoint(8,BORDER+4*STEP,BORDER+2*STEP);
		
		gridPoints[3]=new GridPoint(3,BORDER+STEP,BORDER+STEP);
		gridPoints[4]=new GridPoint(4,BORDER+3*STEP,BORDER+STEP);
		gridPoints[5]=new GridPoint(5,BORDER+5*STEP,BORDER+STEP);
		
		gridPoints[0]=new GridPoint(0,BORDER,BORDER);
		gridPoints[1]=new GridPoint(1,BORDER+3*STEP,BORDER);
		gridPoints[2]=new GridPoint(2,BORDER+6*STEP,BORDER);
		
		
		
		
		/*Remember that since we started counted at 1, the array index is 1 less than id*/	
		
						
		
	}
	
	private class GamePanel extends JPanel{
		
		
		private static final long serialVersionUID = 1L;
		private Image boardImage;
		private boolean firstSetup=true;
		
		
		public GamePanel() {
			
			
			addBoardBackground();
			addClickable();
			
			
		}
		private void addClickable() { //Adds mouse listener
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					
					GridPoint clickedPoint=getClickedPoint(e.getX(),e.getY());
					if(clickedPoint != null) {
						//System.out.println("You have clicked point number "+clickedPoint.getID());
						
						makeMove(clickedPoint); //TODO: will switch to 
					}
					
					repaint();
				}
			});
		}
		private void makeMove(GridPoint clickedPoint) {
			
			/*THIS IS A TEMPORARY PLACE HOLDER FOR DETERMINING A VALID MOVE, EVENTUALLY THIS WILL BE MOVED UP TO THE GAME BOARD CLASS!!*/
			
			if(!clickedPoint.isEmpty()) {
				statusText.append("Point number "+clickedPoint.getID()+" already has a "+clickedPoint.getCurrentPiece()+" piece\n");
				
			}else {
				if(whiteTurn) {
					if(whiteRemainPieces>0) {
						
						clickedPoint.acceptPiece("White");
						whitePieces.get(whiteRemainPieces-1).setUsed(true);
						playerPanel.repaint();
						whiteRemainPieces--;
						
					}else {
						statusText.append("White doesn't have any more pieces left\n");
						return;
					}
					
					
				}else {
					if(blackRemainPieces>0) {
											
						clickedPoint.acceptPiece("Black");
						blackPieces.get(blackRemainPieces-1).setUsed(true);
						playerPanel.repaint();
						blackRemainPieces--;
						
					}else {
						statusText.append("Black doesn't have any more pieces left\n");
						return;
					}
					
				}
				
				statusText.append("A "+clickedPoint.getCurrentPiece()+" piece was moved "+
				" to point number"+clickedPoint.getID()+"\n");
				
				whiteTurn=!whiteTurn;
			}
			
			/*THIS IS A TEMPORARY PLACE HOLDER FOR DETERMINING A VALID MOVE, EVENTUALLY THIS WILL BE MOVED UP TO THE GAME BOARD CLASS!!*/
		}
		private GridPoint getClickedPoint(int clickX, int clickY) {
			/*Checks to see which grid point was clicked. Returns NULL if no valid grid point is clicked*/
			
			for(int x=0;x<gridPoints.length;x++) {
				
				GridPoint currPoint=gridPoints[x];
				
				if(Math.abs(currPoint.retX()-clickX)<=OFFSET && Math.abs(currPoint.retY()-clickY)<=OFFSET) {
					/*OFFSET is used to allow some room for error on the click*/
					return currPoint;
				}
			}
			
			return null; //If they did not click on the square
		}
		private void setBackgroundImage(Image boardImage) {
			this.boardImage=boardImage;
			
			
		}
		private void addBoardBackground() { //adds the background image of the board
			
				
				try {
					
					Image image= ImageIO.read(new File("img\\BlankBoard.png")); //reads in image
					
					Image scaledImage=image.getScaledInstance(BOARD_SIZE, BOARD_SIZE, Image.SCALE_DEFAULT); //Resizes the image so it isn't MASSIVE
					setBackgroundImage(scaledImage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Invalid background selected");
					e.printStackTrace();
				}
				
				
				
		}
		
		@Override
		protected void paintComponent(Graphics g) { //Draws the background image of the board
			super.paintComponent(g); 
			
			g.drawImage(boardImage,0,0,null); //Draws image at location 0,0
		
			
			
			for(int x=0;x<gridPoints.length;x++) { //Draws the current occupying piece for each of the grid points
				gridPoints[x].drawPiece(g);
			}
			
			if(firstSetup) {	
				setGridPoints();
				firstSetup=false;
			}
			
			
		}
		
		private void setGridPoints() { //Places the gridpoints on the board
			
			
			
			for(int x=0;x<gridPoints.length;x++) {
				
				
				
				GridPoint currPoint = gridPoints[x];
				
				
				this.add(currPoint);
				
				
				currPoint.setLocation(currPoint.retX()-OFFSET,currPoint.retY()-OFFSET); //Makes sure the gridpoints cover the space entirely
				
				
				
				
			}
			
		}
	}
	
}