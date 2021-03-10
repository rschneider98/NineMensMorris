
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Piece extends JPanel {

	private int x, y;
	private boolean used;

	private static final int SIZE = 20;
	private static final int PIECE_SIZE = 35;

	private Color pieceColor;

	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Color getPieceColor() {
		return pieceColor;
	}

	public void setPieceColor(Color pieceColor) {
		this.pieceColor = pieceColor;
	}

	public void drawPiece(Graphics g) {

		if (!used) {

			g.setColor(pieceColor);
			Image image;
			try {
				if (pieceColor == Color.white) {
					image = ImageIO.read(new File("img\\whitePiece.png"));
				} else {
					image = ImageIO.read(new File("img\\blackPiece.png"));
				}
				Image scaledImage = image.getScaledInstance(PIECE_SIZE, PIECE_SIZE, Image.SCALE_DEFAULT); 
				g.drawImage(scaledImage, (x - PIECE_SIZE), (y - PIECE_SIZE), null);

			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // reads in image

		}

	}

}
