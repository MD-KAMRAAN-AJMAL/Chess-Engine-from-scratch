package models;

import java.awt.Image;
import javax.swing.ImageIcon;

public enum PieceType {
    PAWN("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-pawn.png"),
    ROOK("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-rook.png"),
    BISHOP(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-bishop.png"
    ),
    KNIGHT(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-knight.png"
    ),
    QUEEN("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-queen.png"),
    KING("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-king.png"),
    pawn("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-pawn.png"),
    rook("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-rook.png"),
    bishop(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-bishop.png"
    ),
    knight(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-knight.png"
    ),
    queen("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-queen.png"),
    king("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-king.png");

    private Image pImage = null;
    private int row;
    private int col;

    private PieceType(String imagePath) {
        pImage = new ImageIcon(imagePath).getImage();
    }

    public Image getImage() {
        return pImage;
    }

    public int getRow() {
        return row;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }
}
