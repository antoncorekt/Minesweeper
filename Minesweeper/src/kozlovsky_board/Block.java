package kozlovsky_board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Антон on 31.03.2016.
 */
public class Block extends JButton  {
    final public int Opened=0, CheckedMina=1;

    public boolean haveMina;
    private int X,Y;

    public Block(String s)
    {
        super(s);

        this.setIcon(new ImageIcon("C:\\res\\StaticBlock.jpg"));
        this.setRolloverIcon(new ImageIcon("C:\\res\\RollBlock.jpg"));

        this.setVisible(true);
        haveMina=false;
    }

    public int getX(){return X;}
    public int getY(){return Y;}

  public void checkOpened()
    {
        /*if (haveMina) {
            this.setEnabled(false);
            this.setDisabledIcon(new ImageIcon("C:\\res\\DisBlock.jpg"));
            this.setText("");
        }
        else
        {
            this.setEnabled(false);
            this.setDisabledIcon(new ImageIcon("C:\\res\\PressBlock.jpg"));
        }*/

    }
}
