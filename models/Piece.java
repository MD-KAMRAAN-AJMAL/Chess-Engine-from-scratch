package models;

public class Piece {

    private PieceType type;
    private int row;
    private int col;
    private int prevRow;
    private int prevCol;
    private boolean hasMoved;

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

    public Piece(Piece other) {
        this.type = other.type;
        this.row = other.row;
        this.col = other.col;
        this.prevRow = other.prevRow;
        this.prevCol = other.prevCol;
        this.hasMoved = other.hasMoved;
    }

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
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

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
