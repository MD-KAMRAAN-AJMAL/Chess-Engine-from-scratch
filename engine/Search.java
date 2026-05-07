package engine;

import java.util.ArrayList;
import java.util.List;
import models.BoardModel;
import models.Move;
import models.Piece;

public class Search {

    public Move bestMove(BoardModel position) {
        List<Move> legalMoves = getAllMoves(position, position.isWhiteTurn);

        Move bestMove = null;
        int bestEval = position.isWhiteTurn
            ? Integer.MIN_VALUE
            : Integer.MAX_VALUE;

        for (Move move : legalMoves) {
            BoardModel child = position.copy();
            child.makeMove(move);

            int eval = alphaBeta(
                child,
                6,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                !position.isWhiteTurn
            );

            if (position.isWhiteTurn && eval > bestEval) {
                bestEval = eval;
                bestMove = move;
            } else if (!position.isWhiteTurn && eval < bestEval) {
                bestEval = eval;
                bestMove = move;
            }
        }

        return bestMove;
    }

    public int alphaBeta(
        BoardModel position,
        int depth,
        int alpha,
        int beta,
        boolean maximizingPlayer
    ) {
        if (depth == 0) {
            // add game over case here too
            return Evaluation.evaluate(position);
        }

        List<Move> moves = getAllMoves(position, maximizingPlayer);

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (Move move : moves) {
                BoardModel child = position.copy();
                child.isWhiteTurn = true;
                child.makeMove(move);

                int eval = alphaBeta(child, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, maxEval);
                if (alpha >= beta) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;

            for (Move move : moves) {
                BoardModel child = position.copy();
                child.isWhiteTurn = false;
                child.makeMove(move);

                int eval = alphaBeta(child, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, minEval);

                if (alpha >= beta) break;
            }

            return minEval;
        }
    }

    public int miniMax(
        BoardModel position,
        int depth,
        boolean maximizingPlayer
    ) {
        if (depth == 0) {
            // add game over case here too
            return Evaluation.evaluate(position);
        }

        List<Move> moves = getAllMoves(position, maximizingPlayer);

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;

            for (Move move : moves) {
                BoardModel child = position.copy();

                child.isWhiteTurn = true;

                child.makeMove(move);

                int eval = miniMax(child, depth - 1, false);

                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;

            for (Move move : moves) {
                BoardModel child = position.copy();

                child.isWhiteTurn = false;

                child.makeMove(move);
                int eval = miniMax(child, depth - 1, true);

                minEval = Math.min(minEval, eval);
            }

            return minEval;
        }
    }

    public List<Move> getAllMoves(BoardModel position, boolean isWhite) {
        List<Move> allMoves = new ArrayList<>();
        MoveGenerator generator = new MoveGenerator();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = position.getPiece(row, col);
                if (piece != null && piece.getType().isWhite() == isWhite) {
                    piece.setPos(row, col);
                    allMoves.addAll(generator.getLegalMoves(position, piece));
                }
            }
        }

        return allMoves;
    }
}
