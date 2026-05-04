package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import models.BoardModel;

public class BoardPanel extends JPanel {

    private final int squareSize = 75;

    public BoardPanel() {
        setPreferredSize(new Dimension(600, 600));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final var board = BoardModel.board;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // draw board
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(
                    squareSize * j,
                    squareSize * i,
                    squareSize,
                    squareSize
                );

                var piece = board[i][j];

                // draw pieces
                if (piece != null) {
                    piece.setPos(squareSize * j, squareSize * i);
                    g.drawImage(
                        piece.getImage(),
                        piece.getPosX(),
                        piece.getPosY(),
                        squareSize,
                        squareSize,
                        null
                    );
                }
            }
        }
    }
}
