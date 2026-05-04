package models;

public class BoardModel {

    public static Piece[][] board = {
        {
            Piece.ROOK,
            Piece.KNIGHT,
            Piece.BISHOP,
            Piece.QUEEN,
            Piece.KING,
            Piece.BISHOP,
            Piece.KNIGHT,
            Piece.ROOK,
        },
        {
            Piece.PAWN,
            Piece.PAWN,
            Piece.PAWN,
            Piece.PAWN,
            Piece.PAWN,
            Piece.PAWN,
            Piece.PAWN,
            Piece.PAWN,
        },
        { null, null, null, null, null, null, null, null },
        { null, null, null, null, null, null, null, null },
        { null, null, null, null, null, null, null, null },
        { null, null, null, null, null, null, null, null },
        {
            Piece.pawn,
            Piece.pawn,
            Piece.pawn,
            Piece.pawn,
            Piece.pawn,
            Piece.pawn,
            Piece.pawn,
            Piece.pawn,
        },
        {
            Piece.rook,
            Piece.knight,
            Piece.bishop,
            Piece.queen,
            Piece.king,
            Piece.bishop,
            Piece.knight,
            Piece.rook,
        },
    };
}
