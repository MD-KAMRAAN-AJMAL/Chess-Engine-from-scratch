package models;

public class Move {

    int fromRow, fromCol;
    int toRow, toCol;
    PieceType promotionPieceType;

    public Move(int fr, int fc, int tr, int tc) {
        this.fromRow = fr;
        this.fromCol = fc;
        this.toRow = tr;
        this.toCol = tc;
        this.promotionPieceType = null;
    }

    public Move(int fr, int fc, int tr, int tc, PieceType promotionPieceType) {
        this.fromRow = fr;
        this.fromCol = fc;
        this.toRow = tr;
        this.toCol = tc;
        this.promotionPieceType = promotionPieceType;
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

    public PieceType getPromotionPieceType() {
        return promotionPieceType;
    }

    public boolean isPromotion() {
        return promotionPieceType != null;
    }
}
