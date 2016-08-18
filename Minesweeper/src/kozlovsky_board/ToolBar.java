package kozlovsky_board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Антон on 31.03.2016.
 */
public class ToolBar extends JPanel {

    JTextField lMine;
    public JFrame main_frame;
    public ToolBar(GUI g)
    {
        main_frame = g;
        lMine = new JTextField("" + 10, 3);
        lMine.setEditable(false);
        lMine.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        lMine.setBackground(Color.BLACK);
        lMine.setForeground(Color.RED);
        lMine.setBorder(BorderFactory.createLoweredBevelBorder());
        lMine = new JTextField("000", 3);
        lMine.setEditable(false);
        lMine.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        lMine.setBackground(Color.BLACK);
        lMine.setForeground(Color.RED);
        lMine.setBorder(BorderFactory.createLoweredBevelBorder());

        this.setLayout(new BorderLayout());
        this.add(lMine, BorderLayout.WEST);

        this.setBorder(BorderFactory.createLoweredBevelBorder());

    }

    public void setMText(String s)
    {
        lMine.setText(s);
    }
}
