package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import models.BoardModel;
import models.Piece;

public class BoardPanel extends JPanel {

    private final int squareSize = 75;

    private Piece selectedPiece;

    public BoardPanel() {
        setPreferredSize(new Dimension(600, 600));
        addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = (Integer) (e.getX() / squareSize);
                    int row = (Integer) (e.getY() / squareSize);

                    selectedPiece = BoardModel.board[row][col];

                    repaint();
                }
            }
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
        drawHightlight(g);
        drawPieces(g);
    }

    private void drawHightlight(Graphics g) {
        if (selectedPiece != null) {
            int selectedCol = selectedPiece.getPosX();
            int selectedRow = selectedPiece.getPosY();

            g.setColor(new Color(255, 255, 0, 120));
            g.fillRect(selectedCol, selectedRow, squareSize, squareSize);
        }
    }

    private void drawBoard(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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
            }
        }
    }

    private void drawPieces(Graphics g) {
        var board = BoardModel.board;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                var piece = board[i][j];
                if (piece != null) {
                    piece.setPos(squareSize * j, squareSize * i);
                    g.drawImage(
                        piece.getType().getImage(),
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
