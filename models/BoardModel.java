package models;

public class BoardModel {

    public boolean isWhiteTurn;

    private Piece[][] board = {
        {
            new Piece(PieceType.ROOK),
            new Piece(PieceType.KNIGHT),
            new Piece(PieceType.BISHOP),
            new Piece(PieceType.QUEEN),
            new Piece(PieceType.KING),
            new Piece(PieceType.BISHOP),
            new Piece(PieceType.KNIGHT),
            new Piece(PieceType.ROOK),
        },
        {
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
            new Piece(PieceType.PAWN),
        },
        { null, null, null, null, null, null, null, null },
        { null, null, null, null, null, null, null, null },
        { null, null, null, null, null, null, null, null },
        { null, null, null, null, null, null, null, null },
        {
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
            new Piece(PieceType.pawn),
        },
        {
            new Piece(PieceType.rook),
            new Piece(PieceType.knight),
            new Piece(PieceType.bishop),
            new Piece(PieceType.queen),
            new Piece(PieceType.king),
            new Piece(PieceType.bishop),
            new Piece(PieceType.knight),
            new Piece(PieceType.rook),
        },
    };

    public BoardModel() {
        isWhiteTurn = true;
        syncPiecePositions();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(Piece piece, int row, int col) {
        board[row][col] = piece;
    }

    public boolean isInside(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }

    public BoardModel copy() {
        BoardModel copy = new BoardModel();
        copy.isWhiteTurn = this.isWhiteTurn;
        Piece[][] copiedBoard = new Piece[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = this.board[row][col];
                copiedBoard[row][col] = piece == null ? null : new Piece(piece);
            }
        }

        copy.board = copiedBoard;
        return copy;
    }

    public void makeMove(Move move) {
        int tr = move.getToRow(),
            tc = move.getToCol();
        int fr = move.getFromRow(),
            fc = move.getFromCol();

        Piece movingPiece = board[fr][fc];
        if (movingPiece == null) {
            return;
        }

        movingPiece.setPrevPos(fr, fc);
        movingPiece.setPos(tr, tc);
        board[tr][tc] = movingPiece;
        board[fr][fc] = null;
        isWhiteTurn = !isWhiteTurn;
    }

    private void syncPiecePositions() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
                if (piece != null) {
                    piece.setPos(row, col);
                }
            }
        }
    }
}
