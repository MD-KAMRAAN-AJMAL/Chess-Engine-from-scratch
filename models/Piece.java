package models;

public class Piece {

    private PieceType type;
    private int posX;
    private int posY;

    public Piece(PieceType type) {
        this.type = type;
    }

    public Piece(PieceType type, int posX, int posY) {
        this.type = type;
        this.posX = posX;
        this.posY = posY;
    }

    public Piece(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public PieceType getType() {
        return type;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
