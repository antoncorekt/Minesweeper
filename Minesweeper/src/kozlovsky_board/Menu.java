package kozlovsky_board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Антон on 01.04.2016.
 */
public class Menu extends JMenuBar {

    public Menu(GUI frame, Board board) {
        Font font = new Font("Verdana", Font.PLAIN, 11);


        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);

        JMenu newMenu = new JMenu("New");
        newMenu.setFont(font);
        fileMenu.add(newMenu);

        JMenuItem Mlev1 = new JMenuItem("5x5");
        Mlev1.setFont(font);
        newMenu.add(Mlev1);

        Mlev1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.newLevel(5);
            }
        });

        JMenuItem Mlev2 = new JMenuItem("10x10");
        Mlev2.setFont(font);
        newMenu.add(Mlev2);

        Mlev2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.newLevel(10);
            }
        });

        JMenuItem Mlev3 = new JMenuItem("15x15");
        Mlev3.setFont(font);
        newMenu.add(Mlev3);

        Mlev3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.newLevel(15);
            }
        });

        JMenuItem Mlev4 = new JMenuItem("Custom");
        Mlev4.setFont(font);
        newMenu.add(Mlev4);

        Mlev4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame.newLevel(15);
                String sCol,sRow,sMine;
                do {

                    sCol = (String) JOptionPane.showInputDialog(
                            frame,
                            "Enter:\n"
                                    + "\"Count of column\"",
                            "Custom board",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            "");
                }
                while(!sCol.matches("\\d+"));
                do {

                    sRow = (String) JOptionPane.showInputDialog(
                            frame,
                            "Enter:\n"
                                    + "\"Count of rows \"",
                            "Custom board",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            sCol);
                }
                while(!sRow.matches("\\d+"));


                do {

                    sMine = (String) JOptionPane.showInputDialog(
                            frame,
                            "Enter:\n"
                                    + "\"Count of mine \"",
                            "Custom board",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            String.valueOf((int)(Integer.valueOf(sRow)*Integer.valueOf(sCol)*0.4)));
                }
                while(!sMine.matches("\\d+"));

                frame.newLevel(Integer.valueOf(sCol), Integer.valueOf(sRow), Integer.valueOf(sMine));

            }
        });

        JMenuItem closeItem = new JMenuItem("Open");
        closeItem.setFont(font);
        fileMenu.add(closeItem);

        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    ObjectInputStream ois = new ObjectInputStream(   // Создаем поток
                            new FileInputStream("C:\\res\\out"));
                    Logic bb = (Logic) ois.readObject();    // Счм=итываем объект
                    // Преобразование типа обязательно
                    ois.close();
                    board.newBoardFromLogic(bb);
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getStackTrace());
                }


            }
        });


        JMenuItem Save = new JMenuItem("Save");
        Save.setFont(font);
        fileMenu.add(Save);
        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Logic b = board.getLogic();
                    FileOutputStream fos =          // Создаем поток вывода в файл
                            new FileOutputStream("C:\\res\\out");
                    ObjectOutputStream oos =         // Связываем с ним поток серализации
                            new ObjectOutputStream(fos);

                    oos.writeObject(b);          // Записываем объект в поток
                    oos.close();
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getStackTrace());
                }
            }
        });

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(fileMenu);

        frame.setJMenuBar(this);

        frame.setPreferredSize(new Dimension(270, 225));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
