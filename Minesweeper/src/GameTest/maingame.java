package GameTest;

import kozlovsky_board.GUI;

import java.awt.*;

/**
 * Created by Антон on 31.03.2016.
 */
public class maingame {

    public static void main (String [] args) {
        EventQueue.invokeLater(new Runnable() {   // Для создания окна
            public void run() {        // обычно запускается
                try {            // отдельный поток
                    GUI window = new GUI();   // создаем окно
                    //window.getFrame().setVisible(true);  // отображаем его
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
