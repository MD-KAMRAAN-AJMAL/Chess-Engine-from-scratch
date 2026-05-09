package engine;

import java.util.ArrayList;
import java.util.List;
import models.BoardModel;
import models.Move;
import models.Piece;
import models.PieceType;

public class MoveGenerator {

    public boolean hasAnyLegalMoves(BoardModel position, boolean isWhite) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = position.getPiece(row, col);
                if (piece == null || piece.getType().isWhite() != isWhite) {
                    continue;
                }

                piece.setPos(row, col);
                if (!getLegalMoves(position, piece).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isCheckmate(BoardModel position, boolean isWhite) {
        return (
            isKingInCheck(position, isWhite) &&
            !hasAnyLegalMoves(position, isWhite)
        );
    }

    public boolean isStalemate(BoardModel position, boolean isWhite) {
        return (
            !isKingInCheck(position, isWhite) &&
            !hasAnyLegalMoves(position, isWhite)
        );
    }

    public List<Move> getLegalMoves(BoardModel position, Piece piece) {
        List<Move> pseudoMoves = getPseudoLegalMoves(position, piece);
        List<Move> legalMoves = new ArrayList<>();

        for (Move move : pseudoMoves) {
            BoardModel copy = position.copy();
            copy.makeMove(move);

            if (!isKingInCheck(copy, piece.getType().isWhite())) {
                legalMoves.add(move);
            }
        }

        return legalMoves;
    }

    private List<Move> getPseudoLegalMoves(BoardModel position, Piece piece) {
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

    public Piece findKing(BoardModel board, boolean isWhite) {
        PieceType kingType = isWhite ? PieceType.king : PieceType.KING;

        for (Piece[] row : board.getBoard()) {
            for (Piece piece : row) {
                if (piece != null && piece.getType() == kingType) {
                    return piece;
                }
            }
        }

        return null;
    }

    public boolean isKingInCheck(BoardModel board, boolean isWhite) {
        Piece king = findKing(board, isWhite);
        if (king == null) {
            return false;
        }

        return isSquareAttacked(board, king.getRow(), king.getCol(), !isWhite);
    }

    private boolean isSquareAttacked(
        BoardModel board,
        int targetRow,
        int targetCol,
        boolean byWhite
    ) {
        int pawnRow = byWhite ? targetRow + 1 : targetRow - 1;
        if (board.isInside(pawnRow, targetCol - 1)) {
            Piece piece = board.getPiece(pawnRow, targetCol - 1);
            if (
                piece != null &&
                piece.getType().isWhite() == byWhite &&
                isPawn(piece)
            ) {
                return true;
            }
        }
        if (board.isInside(pawnRow, targetCol + 1)) {
            Piece piece = board.getPiece(pawnRow, targetCol + 1);
            if (
                piece != null &&
                piece.getType().isWhite() == byWhite &&
                isPawn(piece)
            ) {
                return true;
            }
        }

        int[][] knightOffsets = {
            { -2, -1 },
            { -2, 1 },
            { -1, -2 },
            { -1, 2 },
            { 1, -2 },
            { 1, 2 },
            { 2, -1 },
            { 2, 1 },
        };

        for (int[] offset : knightOffsets) {
            int row = targetRow + offset[0];
            int col = targetCol + offset[1];
            if (!board.isInside(row, col)) {
                continue;
            }

            Piece piece = board.getPiece(row, col);
            if (
                piece != null &&
                piece.getType().isWhite() == byWhite &&
                isKnight(piece)
            ) {
                return true;
            }
        }

        int[][] bishopDirections = {
            { -1, -1 },
            { -1, 1 },
            { 1, -1 },
            { 1, 1 },
        };
        for (int[] direction : bishopDirections) {
            if (
                isAttackedAlongRay(
                    board,
                    targetRow,
                    targetCol,
                    byWhite,
                    direction[0],
                    direction[1],
                    true,
                    false
                )
            ) {
                return true;
            }
        }

        int[][] rookDirections = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int[] direction : rookDirections) {
            if (
                isAttackedAlongRay(
                    board,
                    targetRow,
                    targetCol,
                    byWhite,
                    direction[0],
                    direction[1],
                    false,
                    true
                )
            ) {
                return true;
            }
        }

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }

                int row = targetRow + rowOffset;
                int col = targetCol + colOffset;
                if (!board.isInside(row, col)) {
                    continue;
                }

                Piece piece = board.getPiece(row, col);
                if (
                    piece != null &&
                    piece.getType().isWhite() == byWhite &&
                    isKing(piece)
                ) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isAttackedAlongRay(
        BoardModel board,
        int startRow,
        int startCol,
        boolean byWhite,
        int rowStep,
        int colStep,
        boolean allowBishop,
        boolean allowRook
    ) {
        int row = startRow + rowStep;
        int col = startCol + colStep;

        while (board.isInside(row, col)) {
            Piece piece = board.getPiece(row, col);
            if (piece != null) {
                if (piece.getType().isWhite() != byWhite) {
                    return false;
                }

                if (isQueen(piece)) {
                    return true;
                }
                if (allowBishop && isBishop(piece)) {
                    return true;
                }
                if (allowRook && isRook(piece)) {
                    return true;
                }

                return false;
            }

            row += rowStep;
            col += colStep;
        }

        return false;
    }

    private boolean isPawn(Piece piece) {
        return (
            piece.getType() == PieceType.PAWN ||
            piece.getType() == PieceType.pawn
        );
    }

    private boolean isKnight(Piece piece) {
        return (
            piece.getType() == PieceType.KNIGHT ||
            piece.getType() == PieceType.knight
        );
    }

    private boolean isBishop(Piece piece) {
        return (
            piece.getType() == PieceType.BISHOP ||
            piece.getType() == PieceType.bishop
        );
    }

    private boolean isRook(Piece piece) {
        return (
            piece.getType() == PieceType.ROOK ||
            piece.getType() == PieceType.rook
        );
    }

    private boolean isQueen(Piece piece) {
        return (
            piece.getType() == PieceType.QUEEN ||
            piece.getType() == PieceType.queen
        );
    }

    private boolean isKing(Piece piece) {
        return (
            piece.getType() == PieceType.KING ||
            piece.getType() == PieceType.king
        );
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

        addCastleMoves(position, piece, moves);

        return moves;
    }

    private void addCastleMoves(
        BoardModel position,
        Piece king,
        List<Move> moves
    ) {
        if (king.hasMoved()) {
            return;
        }

        boolean isWhite = king.getType().isWhite();
        int row = king.getRow();
        int col = king.getCol();

        if (isKingInCheck(position, isWhite)) {
            return;
        }

        addCastleMove(position, king, moves, row, col, 1);
        addCastleMove(position, king, moves, row, col, -1);
    }

    private void addCastleMove(
        BoardModel position,
        Piece king,
        List<Move> moves,
        int row,
        int col,
        int direction
    ) {
        int rookCol = direction > 0 ? 7 : 0;
        Piece rook = position.getPiece(row, rookCol);

        if (
            rook == null ||
            !isRook(rook) ||
            rook.getType().isWhite() != king.getType().isWhite() ||
            rook.hasMoved()
        ) {
            return;
        }

        for (int pathCol = col + direction; pathCol != rookCol; pathCol += direction) {
            if (position.getPiece(row, pathCol) != null) {
                return;
            }
        }

        for (int pathCol = col + direction; pathCol != col + (2 * direction) + direction; pathCol += direction) {
            if (isSquareAttacked(position, row, pathCol, !king.getType().isWhite())) {
                return;
            }
        }

        moves.add(new Move(row, col, row, col + (2 * direction)));
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
            addPawnMove(moves, piece, r, c, oneStepRow, c);

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
                addPawnMove(moves, piece, r, c, captureRow, leftCol);
            }
        }

        int rightCol = c + 1;
        if (position.isInside(captureRow, rightCol)) {
            Piece rightTarget = position.getPiece(captureRow, rightCol);
            if (
                rightTarget != null &&
                rightTarget.getType().isWhite() != piece.getType().isWhite()
            ) {
                addPawnMove(moves, piece, r, c, captureRow, rightCol);
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
                    addPawnMove(moves, piece, r, c, captureRow, leftCol);
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
                    addPawnMove(moves, piece, r, c, captureRow, rightCol);
                }
            }
        }

        return moves;
    }

    private void addPawnMove(
        List<Move> moves,
        Piece piece,
        int fromRow,
        int fromCol,
        int toRow,
        int toCol
    ) {
        if (!isPromotionRow(piece, toRow)) {
            moves.add(new Move(fromRow, fromCol, toRow, toCol));
            return;
        }

        for (PieceType promotionType : getPromotionPieceTypes(piece.getType().isWhite())) {
            moves.add(new Move(fromRow, fromCol, toRow, toCol, promotionType));
        }
    }

    private boolean isPromotionRow(Piece piece, int toRow) {
        return (piece.getType().isWhite() && toRow == 0) ||
        (!piece.getType().isWhite() && toRow == 7);
    }

    private PieceType[] getPromotionPieceTypes(boolean isWhite) {
        if (isWhite) {
            return new PieceType[] {
                PieceType.queen,
                PieceType.rook,
                PieceType.bishop,
                PieceType.knight,
            };
        }

        return new PieceType[] {
            PieceType.QUEEN,
            PieceType.ROOK,
            PieceType.BISHOP,
            PieceType.KNIGHT,
        };
    }
}
