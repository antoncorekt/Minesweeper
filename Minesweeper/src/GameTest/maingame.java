package GameTest;

import kozlovsky_board.GUI;

import java.awt.*;

/**
 * Created by ����� on 31.03.2016.
 */
public class maingame {

    public static void main (String [] args) {
        EventQueue.invokeLater(new Runnable() {   // ��� �������� ����
            public void run() {        // ������ �����������
                try {            // ��������� �����
                    GUI window = new GUI();   // ������� ����
                    //window.getFrame().setVisible(true);  // ���������� ���
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
