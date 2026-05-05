package engine;

import models.BoardModel;

public class Evaluation {

    public static int evaluate(BoardModel position) {
        int eval = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                var piece = position.getPiece(i, j);

                if (piece == null) continue;

                var type = piece.getType();

                if (type.isWhite()) {
                    eval += type.value();
                } else {
                    eval -= type.value();
                }
            }
        }

        return eval;
    }
}
