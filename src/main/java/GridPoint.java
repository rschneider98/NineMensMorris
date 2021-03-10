
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPoint extends JPanel{
	
	private int id; //1-24 Will have to remember that the numbering starts at 1 and not 0
	private String occupyingPiece; //TODO: Maybe change to A "Piece Object"?
	private int x,y;
	private boolean empty=true;
	
	private static final int SIZE=20;
	private static final int PIECE_SIZE=45;
	
	private Color pieceColor;
	
	
	public GridPoint(int id,int x,int y) {
		
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
	public boolean isEmpty() {
		
		return empty;
	}
	public int getID() { //returns user friendly ID from the labeled board
	
		return id;
	}
	public String getCurrentPiece() {
		return occupyingPiece;
	}
	public void acceptPiece(String color) { //TODO: Maybe change to A "Piece Object"?
		
		/*This method determines what piece is being received*/
		
		occupyingPiece=color;
		empty=false;
		
		if(color=="White") {
			
			pieceColor=Color.white;
		}else {
			pieceColor=Color.BLACK;
		}
		
		
		
		
	}
	public void drawPiece(Graphics g) {
		
		if(!empty) {
		
			g.setColor(pieceColor);
			
			Image image;
			try {
				if (pieceColor == Color.white) {
					image = ImageIO.read(new File("img\\whitePiece.png"));
				} else {
					image = ImageIO.read(new File("img\\blackPiece.png"));
				}
				Image scaledImage = image.getScaledInstance(PIECE_SIZE, PIECE_SIZE, Image.SCALE_DEFAULT); 
				g.drawImage(scaledImage, (x - SIZE), (y - SIZE), null);

			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // reads in image

			
		}
	
}

	
	
}
