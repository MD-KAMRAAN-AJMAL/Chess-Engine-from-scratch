package engine;

import java.util.ArrayList;
import java.util.List;
import models.BoardModel;
import models.Move;
import models.Piece;

public class MoveGenerator {

    public List<Move> getLegalMoves(BoardModel position, Piece piece) {
        if (piece == null) return new ArrayList<Move>();

        switch (piece.getType()) {
            case PAWN:
                return getPawnMoves(position, piece);
            case ROOK:
                return getRookMoves(position, piece);
            case KNIGHT:
                return getKnightMoves(position, piece);
            case BISHOP:
                return getBishopMoves(position, piece);
            case QUEEN:
                return getQueenMoves(position, piece);
            case KING:
                return getKingMoves(position, piece);
            case pawn:
                return getPawnMoves(position, piece);
            case rook:
                return getRookMoves(position, piece);
            case knight:
                return getKnightMoves(position, piece);
            case bishop:
                return getBishopMoves(position, piece);
            case queen:
                return getQueenMoves(position, piece);
            case king:
                return getKingMoves(position, piece);
        }

        return new ArrayList<Move>();
    }

    private List<Move> isLegalMove(
        BoardModel position,
        Piece piece,
        List<Move> moves,
        int tr,
        int tc
    ) {
        if (!position.isInside(tr, tc)) return moves;

        int fr = piece.getRow();
        int fc = piece.getCol();

        Piece target = position.getPiece(tr, tc);
        if (
            target == null ||
            target.getType().isWhite() != piece.getType().isWhite()
        ) moves.add(new Move(fr, fc, tr, tc));

        return moves;
    }

    private List<Move> getKingMoves(BoardModel position, Piece piece) {
        int r = piece.getRow();
        int c = piece.getCol();

        int dir = piece.getType().isWhite() ? 1 : -1;

        List<Move> moves = new ArrayList<Move>();
        // forward
        isLegalMove(position, piece, moves, r + dir, c);

        // backward
        isLegalMove(position, piece, moves, r - dir, c);

        // left
        isLegalMove(position, piece, moves, r, c - dir);

        // right
        isLegalMove(position, piece, moves, r, c + dir);

        // left top
        isLegalMove(position, piece, moves, r + dir, c - dir);

        // left bottom
        isLegalMove(position, piece, moves, r - dir, c - dir);

        // right top
        isLegalMove(position, piece, moves, r + dir, c + dir);

        // right bottom
        isLegalMove(position, piece, moves, r - dir, c + dir);

        return moves;
    }

    private List<Move> getQueenMoves(BoardModel position, Piece piece) {
        List<Move> moves = new ArrayList<Move>();

        moves.addAll(getRookMoves(position, piece));

        moves.addAll(getBishopMoves(position, piece));

        return moves;
    }

    private List<Move> getBishopMoves(BoardModel position, Piece piece) {
        List<Move> moves = new ArrayList<Move>();
        addRayMoves(position, piece, moves, -1, -1);
        addRayMoves(position, piece, moves, -1, 1);
        addRayMoves(position, piece, moves, 1, -1);
        addRayMoves(position, piece, moves, 1, 1);

        return moves;
    }

    private List<Move> getKnightMoves(BoardModel position, Piece piece) {
        int r = piece.getRow();
        int c = piece.getCol();

        int dir = piece.getType().isWhite() ? 1 : -1;

        List<Move> moves = new ArrayList<Move>();

        // right top forward
        isLegalMove(position, piece, moves, r + 2 * dir, c + dir);

        // right bottom forward
        isLegalMove(position, piece, moves, r + dir, c + 2 * dir);

        // left top forward
        isLegalMove(position, piece, moves, r + 2 * dir, c - dir);

        // left bottom forward
        isLegalMove(position, piece, moves, r + dir, c - 2 * dir);

        // right top backward
        isLegalMove(position, piece, moves, r - dir, c + 2 * dir);

        // right bottom backward
        isLegalMove(position, piece, moves, r - 2 * dir, c + dir);

        // left top backward
        isLegalMove(position, piece, moves, r - dir, c - 2 * dir);

        // left bottom backward
        isLegalMove(position, piece, moves, r - 2 * dir, c - dir);

        return moves;
    }

    private List<Move> getRookMoves(BoardModel position, Piece piece) {
        List<Move> moves = new ArrayList<Move>();
        addRayMoves(position, piece, moves, -1, 0);
        addRayMoves(position, piece, moves, 1, 0);
        addRayMoves(position, piece, moves, 0, -1);
        addRayMoves(position, piece, moves, 0, 1);

        return moves;
    }

    private void addRayMoves(
        BoardModel position,
        Piece piece,
        List<Move> moves,
        int rowStep,
        int colStep
    ) {
        int row = piece.getRow() + rowStep;
        int col = piece.getCol() + colStep;

        while (position.isInside(row, col)) {
            Piece target = position.getPiece(row, col);

            if (target == null) {
                moves.add(new Move(piece.getRow(), piece.getCol(), row, col));
            } else {
                if (target.getType().isWhite() != piece.getType().isWhite()) {
                    moves.add(
                        new Move(piece.getRow(), piece.getCol(), row, col)
                    );
                }
                break;
            }

            row += rowStep;
            col += colStep;
        }
    }

    private List<Move> getPawnMoves(BoardModel position, Piece piece) {
        int r = piece.getRow();
        int c = piece.getCol();
        int dir = piece.getType().isWhite() ? -1 : 1;

        List<Move> moves = new ArrayList<Move>();

        int oneStepRow = r + dir;
        if (
            position.isInside(oneStepRow, c) &&
            position.getPiece(oneStepRow, c) == null
        ) {
            moves.add(new Move(r, c, oneStepRow, c));

            int twoStepRow = r + 2 * dir;
            boolean onStartRank = (r == 1 && dir == 1) || (r == 6 && dir == -1);
            if (
                onStartRank &&
                position.isInside(twoStepRow, c) &&
                position.getPiece(twoStepRow, c) == null
            ) {
                moves.add(new Move(r, c, twoStepRow, c));
            }
        }

        int captureRow = r + dir;

        int leftCol = c - 1;
        if (position.isInside(captureRow, leftCol)) {
            Piece leftTarget = position.getPiece(captureRow, leftCol);
            if (
                leftTarget != null &&
                leftTarget.getType().isWhite() != piece.getType().isWhite()
            ) {
                moves.add(new Move(r, c, captureRow, leftCol));
            }
        }

        int rightCol = c + 1;
        if (position.isInside(captureRow, rightCol)) {
            Piece rightTarget = position.getPiece(captureRow, rightCol);
            if (
                rightTarget != null &&
                rightTarget.getType().isWhite() != piece.getType().isWhite()
            ) {
                moves.add(new Move(r, c, captureRow, rightCol));
            }
        }

        if ((r == 4 && dir == 1) || (r == 3 && dir == -1)) {
            if (
                position.isInside(r, leftCol) &&
                position.isInside(captureRow, leftCol)
            ) {
                Piece leftPawn = position.getPiece(r, leftCol);
                if (
                    leftPawn != null &&
                    leftPawn.getType().name().equalsIgnoreCase("PAWN") &&
                    leftPawn.getType().isWhite() != piece.getType().isWhite() &&
                    (leftPawn.getPrevRow() == 6 ||
                        leftPawn.getPrevRow() == 1) &&
                    leftPawn.getPrevCol() == leftCol &&
                    position.getPiece(captureRow, leftCol) == null
                ) {
                    moves.add(new Move(r, c, captureRow, leftCol));
                }
            }

            if (
                position.isInside(r, rightCol) &&
                position.isInside(captureRow, rightCol)
            ) {
                Piece rightPawn = position.getPiece(r, rightCol);
                if (
                    rightPawn != null &&
                    rightPawn.getType().name().equalsIgnoreCase("PAWN") &&
                    rightPawn.getType().isWhite() !=
                    piece.getType().isWhite() &&
                    (rightPawn.getPrevRow() == 6 ||
                        rightPawn.getPrevRow() == 1) &&
                    rightPawn.getPrevCol() == rightCol &&
                    position.getPiece(captureRow, rightCol) == null
                ) {
                    moves.add(new Move(r, c, captureRow, rightCol));
                }
            }
        }

        return moves;
    }
}
