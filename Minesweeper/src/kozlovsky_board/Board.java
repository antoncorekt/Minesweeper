package kozlovsky_board;

import javafx.scene.input.MouseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Random;

/**
 * Created by Антон on 31.03.2016.
 */
public class Board extends JPanel{
    final private int Mina=-1, NotMina=0;

    JButton [][]block;
    ToolBar toolBar;


    Logic logic;

    int level,count_mine, cur_flag, level1;
    boolean first_clic;


    public Board(ToolBar t)
    {
        toolBar = t;
        createLevel(5,5,-1);

    }

    public Board(int t)
    {
        createLevel(t,t,-1);
    }

    public Board(int t, int t1)
    {
        createLevel(t,t1,-1);
    }

    public int getBlockSize(){return block.length;}
    public int getBlockSize1(){return block[0].length;}

    public void createLevel(int CountBlock, int CountBlock1, int COUNT_MINE)
    {
        first_clic = false;
        level = CountBlock;
        level1 = CountBlock1;

        if (COUNT_MINE==-1)
        count_mine = (int)(CountBlock*CountBlock1*0.2);
        else
        count_mine = COUNT_MINE;
        cur_flag = 0;
        toolBar.setMText("" + count_mine);

        block = new JButton[CountBlock][CountBlock1];

        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                block[i][j] = new JButton("");

                addEvent(i, j);

                this.add(block[i][j]);
            }
        }

        this.setPreferredSize(new Dimension(CountBlock*40, CountBlock1*40));
        this.setLayout(new GridLayout(CountBlock, CountBlock1));
    }


    public Logic getLogic(){return logic;}

    public void addEvent(int i,int j)
    {
        block[i][j].addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent event) {

            }


            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }

            public void mousePressed(MouseEvent event) {

                if (!first_clic ) {
                    first_clic = true;
                    logic = new Logic(level, level, count_mine, i, j);
                }
                if (block[i][j].isEnabled()) {
                   // block[i][j].getUIClassID(UIManager)
                    if (event.getButton() == MouseEvent.BUTTON3) {
                        if ((block[i][j].getIcon() != null) && (block[i][j].getIcon().toString().equals(new ImageIcon("C:\\res\\MinaUser.png").toString()))) {
                            block[i][j].setIcon(null);
                            toolBar.setMText("" + (count_mine - --cur_flag));
                            logic.setState(i, j, logic.DontTouch);
                        } else {
                            block[i][j].setIcon(new ImageIcon("C:\\res\\MinaUser.png"));
                            logic.setState(i, j, logic.CheckMina);
                            toolBar.setMText("" + (count_mine - ++cur_flag));
                            if (isWin()) {
                                System.out.println("You win");
                                try {
                                    showAllMine(logic);
                                } catch (Exception e) {
                                }
                                int n = JOptionPane.showConfirmDialog(
                                        toolBar.main_frame,
                                        "Congratulations! \n Would you want play a new game?",
                                        "You win=)",
                                        JOptionPane.YES_NO_OPTION);
                                if (n != 0)
                                    System.exit(0);
                                else
                                    clearBoard();
                            }
                        }
                    } else {
                        if ((event.getButton() == MouseEvent.BUTTON1) && (block[i][j].getIcon() == null)) {
                            if (logic.isMine(i, j)) {
                                block[i][j].setIcon(new ImageIcon("C:\\res\\Mina.png"));
                                System.out.println("You lose");
                                try {
                                    showAllMine(logic);
                                } catch (Exception e) {
                                }
                                int n = JOptionPane.showConfirmDialog(
                                        toolBar.main_frame,
                                        "Would you want play a new game?",
                                        "You lose=(",
                                        JOptionPane.YES_NO_OPTION);
                                if (n != 0)
                                    System.exit(0);
                                else
                                    clearBoard();


                            } else {
                                if (logic.canOpenField(i, j)) {
                                    logic.OpenField(i + 1, j + 1);
                                    logic.openedNotEnaeble();
                                    newState(logic);
                                }
                                if (logic.x[i + 1][j + 1]!=0)
                                    block[i][j].setText("<html> <h1>" + Integer.toString(logic.x[i + 1][j + 1]) + "<h1></html>");
                                logic.setState(i, j, logic.Opened);

                            }

                        }
                    }
                }
            }


            public void mouseReleased(MouseEvent event) {
            }
        });
    }

    private boolean isWin()
    {
        int count=0;
        for (int i = 0; i < block.length; i++)
            for (int j = 0; j < block[0].length; j++)
                if ((block[i][j].getIcon()!=null)&&(block[i][j].getIcon().toString().equals(new ImageIcon("C:\\res\\MinaUser.png").toString())) && logic.isMine(i,j))
                    count++;

        return count==count_mine;
    }

    public void deleteBoard()
    {
        for (int i = 0; i < block.length; i++)
            for (int j = 0; j < block[0].length; j++) {
                this.remove(block[i][j]);
                first_clic = false;
                cur_flag = 0;
                toolBar.setMText(String.valueOf(count_mine));
            }
    }

    public void newState(Logic l)
    {
        for (int i = 0; i < block.length; i++)
            for (int j = 0; j < block[0].length; j++) {
                if (l.getState(i,j)==l.DontTouch)
                    block[i][j].setIcon(null);
                if (l.getState(i,j)==l.CheckMina)
                    block[i][j].setIcon(new ImageIcon("C:\\res\\MinaUser.png"));
                if (l.getState(i,j)==l.NotEnaeble) {
                    block[i][j].setEnabled(false);
                    block[i][j].setText("");

                }
                if (l.getState(i, j)==l.Opened)
                    if (l.x[i + 1][j + 1]!=0)
                        block[i][j].setText("<html> <h1>" + Integer.toString(l.x[i + 1][j + 1]) + "<h1></html>");
            }

    }

    public void clearBoard()
    {
        for (int i = 0; i < block.length; i++)
            for (int j = 0; j < block[0].length; j++) {
                    block[i][j].setIcon(null);
                block[i][j].setText("");
                block[i][j].setEnabled(true);
            }
        cur_flag = 0;
        toolBar.setMText(String.valueOf(count_mine));;
        first_clic = false;
    }

    public void showAllMine(Logic l) throws InterruptedException
    {
        for (int i = 0; i < block.length; i++)
            for (int j = 0; j < block[0].length; j++)
                if (logic.isMine(i,j)) {
                    if (logic.getState(i,j)==logic.CheckMina)
                        block[i][j].setIcon(new ImageIcon("C:\\res\\MinaTrue.png"));
                    else
                        block[i][j].setIcon(new ImageIcon("C:\\res\\Mina.png"));

                        //Thread.sleep(40);

                }
    }

    public void newBoardFromLogic(Logic l)
    {
        clearBoard();
        deleteBoard();
        createLevel(l.x.length-2,l.x[0].length-2,l.c_mine);
        logic = l;
        clearBoard();
        newState(logic);
    }
}
