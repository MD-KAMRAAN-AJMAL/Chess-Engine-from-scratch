package models;

public class Move {

    int fromRow, fromCol;
    int toRow, toCol;

    public Move(int fr, int fc, int tr, int tc) {
        this.fromRow = fr;
        this.fromCol = fc;
        this.toRow = tr;
        this.toCol = tc;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }
}
