package ui;

import engine.MoveGenerator;
import engine.Search;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import models.BoardModel;
import models.Move;
import models.Piece;

public class BoardPanel extends JPanel {

    private final int squareSize = 75;
    private final Timer updateLoop;
    public BoardModel board = new BoardModel();
    private engine.MoveGenerator moveGenerator = new MoveGenerator();
    private Search search = new Search();
    private Piece selectedPiece;

    public BoardPanel() {
        setPreferredSize(new Dimension(600, 600));

        updateLoop = new Timer(16, e -> {
            updateFun();

            repaint();
        });

        updateLoop.start();

        addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = (Integer) (e.getX() / squareSize);
                    int row = (Integer) (e.getY() / squareSize);
                    Piece clickedPiece = board.getPiece(row, col);

                    if (selectedPiece != null && board.isWhiteTurn) {
                        List<Move> legalMoves = moveGenerator.getLegalMoves(
                            board,
                            selectedPiece
                        );

                        for (Move move : legalMoves) {
                            if (
                                move.getToRow() == row && move.getToCol() == col
                            ) {
                                board.makeMove(move);
                                selectedPiece = null;

                                repaint();
                                return;
                            }
                        }
                    }

                    if (
                        clickedPiece != null &&
                        clickedPiece.getType().isWhite() &&
                        board.isWhiteTurn
                    ) {
                        selectedPiece = clickedPiece;
                    } else {
                        selectedPiece = null;
                    }

                    repaint();
                }
            }
        );
    }

    private void updateFun() {
        if (!board.isWhiteTurn) {
            Move blackMove = search.bestMove(board);
            if (blackMove != null) {
                board.makeMove(blackMove);
            }
        }
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
            int selectedRow = selectedPiece.getRow();
            int selectedCol = selectedPiece.getCol();

            g.setColor(new Color(255, 255, 0, 120));
            g.fillRect(
                selectedCol * squareSize,
                selectedRow * squareSize,
                squareSize,
                squareSize
            );

            List<Move> moves = moveGenerator.getLegalMoves(
                board,
                selectedPiece
            );

            for (Move move : moves) {
                g.setColor(new Color(255, 255, 0, 120));
                g.fillRect(
                    move.getToCol() * squareSize,
                    move.getToRow() * squareSize,
                    squareSize,
                    squareSize
                );
            }
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                var piece = board.getPiece(i, j);
                if (piece != null) {
                    piece.setPos(i, j);
                    g.drawImage(
                        piece.getType().getImage(),
                        piece.getCol() * squareSize,
                        piece.getRow() * squareSize,
                        squareSize,
                        squareSize,
                        null
                    );
                }
            }
        }
    }
}
