package models;

import java.awt.Image;
import javax.swing.ImageIcon;

public enum PieceType {
    PAWN("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-pawn.png", 1),
    ROOK("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-rook.png", 5),
    BISHOP(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-bishop.png",
        3
    ),
    KNIGHT(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-knight.png",
        3
    ),
    QUEEN(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-queen.png",
        9
    ),
    KING("/Users/kamraan/Desktop/Chess Engine/pieces_images/black-king.png", 0),
    pawn("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-pawn.png", 1),
    rook("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-rook.png", 5),
    bishop(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-bishop.png",
        3
    ),
    knight(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-knight.png",
        3
    ),
    queen(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-queen.png",
        9
    ),
    king("/Users/kamraan/Desktop/Chess Engine/pieces_images/white-king.png", 0);

    private Image pImage = null;

    private int value;

    private PieceType(String imagePath, int value) {
        this.pImage = new ImageIcon(imagePath).getImage();
        this.value = value;
    }

    public Image getImage() {
        return pImage;
    }

    public int value() {
        return value;
    }
}
