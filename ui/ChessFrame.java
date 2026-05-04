package ui;

import javax.swing.JFrame;

public class ChessFrame {

    private JFrame frame;

    public JFrame createFrame(String title) {
        frame = new JFrame(title);

        BoardPanel board = new BoardPanel();

        frame.add(board);

        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }
}
