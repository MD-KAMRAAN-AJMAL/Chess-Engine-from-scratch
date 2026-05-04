package models;

public class BoardModel {

    public static Piece[][] board = {
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
}
