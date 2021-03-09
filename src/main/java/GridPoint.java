
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPoint extends JPanel{
	
	private int id; //1-24 Will have to remember that the numbering starts at 1 and not 0
	private String occupyingPiece; //TODO: Maybe change to A "Piece Object"?
	private int x,y;
	private boolean empty=true;
	
	private static final int SIZE=20;
	private static final int PIECE_SIZE=15;
	
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
		
		
		
		//repaint();
		
	}
	public void drawPiece(Graphics g) {
		
		if(!empty) {
		
			g.setColor(pieceColor);
			
			//System.out.println(pieceColor);
			
			g.fillOval((x-PIECE_SIZE),(y-PIECE_SIZE),2*PIECE_SIZE,2*PIECE_SIZE);
			
		}
	
}
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		//g.setColor(Color.BLACK);
//		//g.drawRect(x-GameGUI.OFFSET, y-GameGUI.OFFSET,GameGUI.PLACE_SIZE,GameGUI.PLACE_SIZE);
//		
//		if(!empty) {
//			
//			g.setColor(pieceColor);
//			
//			System.out.println(pieceColor);
//			g.fillOval(x,y,20,20);
//			
//		}
//	}
	
	
}
