package models;

import java.awt.Image;
import javax.swing.ImageIcon;

public enum PieceType {
    PAWN(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-pawn.png",
        1,
        false
    ),
    ROOK(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-rook.png",
        5,
        false
    ),
    BISHOP(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-bishop.png",
        3,
        false
    ),
    KNIGHT(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-knight.png",
        3,
        false
    ),
    QUEEN(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-queen.png",
        9,
        false
    ),
    KING(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/black-king.png",
        0,
        false
    ),
    pawn(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-pawn.png",
        1,
        true
    ),
    rook(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-rook.png",
        5,
        true
    ),
    bishop(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-bishop.png",
        3,
        true
    ),
    knight(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-knight.png",
        3,
        true
    ),
    queen(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-queen.png",
        9,
        true
    ),
    king(
        "/Users/kamraan/Desktop/Chess Engine/pieces_images/white-king.png",
        0,
        true
    );

    private Image pImage = null;

    private int value;
    private boolean isWhite;

    private PieceType(String imagePath, int value, Boolean isWhite) {
        this.pImage = new ImageIcon(imagePath).getImage();
        this.value = value;
        this.isWhite = isWhite;
    }

    public Image getImage() {
        return pImage;
    }

    public int value() {
        return value;
    }

    public boolean isWhite() {
        return isWhite;
    }
}
