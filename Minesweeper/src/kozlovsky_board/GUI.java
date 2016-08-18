package kozlovsky_board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Антон on 31.03.2016.
 */
public class GUI extends JFrame {

    final private int CONST_SIZE = 80;

    Board board;
    ToolBar toolBar;
    Menu menu;
    public GUI()
    {
        super("Minesweeper");


        toolBar = new ToolBar(this);
        board = new Board(toolBar);

        menu = new Menu(this , board);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize((board.getBlockSize() + 1) * 40, (board.getBlockSize() + 1) * 40 + CONST_SIZE);

        this.setVisible(true);

        this.setResizable(false);
        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.NORTH);

    }

    public void newLevel(int t)
    {
        board.deleteBoard();
        board.createLevel(t, t, -1);
        this.setSize((board.getBlockSize1() + 1) * 40, (board.getBlockSize1() + 1) * 40 + CONST_SIZE);
    }

    public void newLevel(int t, int t1, int m)
    {
        board.deleteBoard();
        board.createLevel(t,t1,m);
        this.setSize((board.getBlockSize1() + 1) * 40, (board.getBlockSize() + 1) * 40 + CONST_SIZE);
    }
}
