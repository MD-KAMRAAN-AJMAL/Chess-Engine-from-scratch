package models;

public class Piece {

    private PieceType type;
    private int row;
    private int col;
    private int prevRow;
    private int prevCol;

    public Piece(PieceType type) {
        this.type = type;
    }

    public Piece(PieceType type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    public Piece(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public PieceType getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getPrevRow() {
        return prevRow;
    }

    public int getPrevCol() {
        return prevCol;
    }

    public void setPrevPos(int prevRow, int prevCol) {
        this.prevRow = prevRow;
        this.prevCol = prevCol;
    }
}
