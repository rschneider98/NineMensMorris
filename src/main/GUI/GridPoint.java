package main.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.game.Board;

public class GridPoint extends JPanel{
	
	private int id; //0-23
	private int x,y;
	
	private static final int SIZE=20;
	private static final int PIECE_SIZE=45;	
	
	public GridPoint(int id, int x, int y) {		
		this.id=id;
		//this.setSize(GameGUI.PLACE_SIZE,GameGUI.PLACE_SIZE);
		
		this.x=x;
		this.y=y;
		this.setVisible(false);		
	}

	public int retX() { //Had to change name from getX() to retX() because getX() is inherited from JPanel
		return x;
	}

	public int retY() {
		return y;
	}

	public int getID() { //returns user friendly ID from the labeled board
		return id;
	}

	public void drawPiece(Graphics g, Board currentGame) {
		/* get called by game board object to draw the piece 
		 * in the graphics context in the correct location */

		if(!currentGame.IsEmpty(id)) {			
			Image image;
			try {
				// reads in image
				if (currentGame.IsPlayersPiece(0, id)) {
					g.setColor(Color.white);
					image = ImageIO.read(new File("img\\whitePiece.png"));
				} else {
					g.setColor(Color.black);
					image = ImageIO.read(new File("img\\blackPiece.png"));
				}
				Image scaledImage = image.getScaledInstance(PIECE_SIZE, PIECE_SIZE, Image.SCALE_DEFAULT); 
				g.drawImage(scaledImage, (x - SIZE), (y - SIZE), null);		
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
}

}
