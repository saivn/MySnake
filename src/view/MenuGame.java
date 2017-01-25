package view;

import controller.Player;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 31.08.16
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class MenuGame extends JFrame {


    public JMenuBar createMenuGame() {

        final FileDialog fileDialogSave = new FileDialog(this, "Записать как", FileDialog.SAVE);
        final FileDialog fileDialogLoad = new FileDialog(this, "Загрузить как", FileDialog.LOAD);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Игра");
        JMenu menuSettings = new JMenu("Настройки");
        JMenu menuRecords = new JMenu("Рекорды");
        JMenu menuHelp = new JMenu("Помощь");
        //---------------menuGame-----------------
        JMenuItem itemGameNew = new JMenuItem("Новая игра");
        JMenuItem itemGameLoad = new JMenuItem("Загрузить");
        JMenuItem itemGameSave = new JMenuItem("Сохранить");
        JMenuItem itemGameClose = new JMenuItem("Закрыть");
        menuGame.add(itemGameNew);
        menuGame.add(new JSeparator());
        menuGame.add(itemGameLoad);
        menuGame.add(itemGameSave);
        menuGame.add(new JSeparator());
        menuGame.add(itemGameClose);
        //----------------menuSettings-----------------
        JMenu itemSettingsGame = new JMenu("Скорость игры");
        menuSettings.add(itemSettingsGame);
        //---------------popmenu Settings
        JMenuItem speed1 = new JRadioButtonMenuItem("x1");
        JMenuItem speed2 = new JRadioButtonMenuItem("x2");
        JMenuItem speed3 = new JRadioButtonMenuItem("x3");
        JMenuItem speed4 = new JRadioButtonMenuItem("x4");
        JMenuItem speed5 = new JRadioButtonMenuItem("x5");

        ButtonGroup bg = new ButtonGroup();
        bg.add(speed1);
        bg.add(speed2);
        bg.add(speed3);
        bg.add(speed4);
        bg.add(speed5);

        speed4.setSelected(true);
        itemSettingsGame.add(speed1);
        itemSettingsGame.add(speed2);
        itemSettingsGame.add(speed3);
        itemSettingsGame.add(speed4);
        itemSettingsGame.add(speed5);
        //---------------menuRecords------------------
        JMenuItem itemTableRecordsOpen = new JMenuItem("Открыть таблицу ");
        JMenuItem itemTableRecordsClear = new JMenuItem("Закрыть таблицу");
        menuRecords.add(itemTableRecordsOpen);
        menuRecords.add(itemTableRecordsClear);
        //---------------menuHelp---------------------
        JMenuItem itemMenuHelpDocumens = new JMenuItem("Управление");
        JMenuItem itemMenuHelpAbout = new JMenuItem("О программе");
        menuHelp.add(itemMenuHelpDocumens);
        menuHelp.add(itemMenuHelpAbout);
        //--------------------------------------------
        menuBar.add(menuGame);
        menuBar.add(menuSettings);
        menuBar.add(menuRecords);
        menuBar.add(menuHelp);


        itemGameNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restart");
                try {
                    synchronized (Game.restartMutex) {
                        Game.restartMutex.notify();
                    }
                    Game.gameInit();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        itemGameSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Запись игры");
                fileDialogSave.show();
                saveGame(fileDialogSave);

            }
        });


        itemGameLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Загрузка игры");

                fileDialogLoad.show();
                loadGame(fileDialogLoad);
            }
        });


        itemGameClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                System.exit(0);
            }
        });

        speed1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake.getSnake().setSpeedSnake(1000);
            }
        });
        speed2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake.getSnake().setSpeedSnake(1000 / 2);
            }
        });
        speed3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake.getSnake().setSpeedSnake(1000 / 3);
            }
        });
        speed4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake.getSnake().setSpeedSnake(1000 / 4);
            }
        });

        speed5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake.getSnake().setSpeedSnake(1000 / 5);
            }
        });

        itemTableRecordsOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<PersonFromTableRecords> list = TableRecords.readTable();
                TableRecordsView.viewTable(list);
            }
        });
        itemTableRecordsClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                TableRecords.clearTable();
                ArrayList<PersonFromTableRecords> list = TableRecords.readTable();
                TableRecordsView.viewTable(list);
            }
        });

        itemMenuHelpDocumens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setBounds(200, 200, 350, 280);
                frame.setLayout(null);

                String string = "Управление стрелками:\n\n";
                String string1 = "вверх\nвниз\nвлево\nвправо\n";
                JTextArea multi = new JTextArea(string + string1);
                multi.setEditable(false);
                multi.setBackground(frame.getBackground());
                multi.setForeground(Color.black);
                multi.setBounds(10, 10, 200, 150);

                frame.add(multi);
                frame.setVisible(true);

            }
        });


        itemMenuHelpAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setBounds(200, 200, 200, 100);
                frame.setLayout(null);

                String string = "e-mail: saivn@yandex.ru";

                JTextArea multi = new JTextArea(string);
                multi.setEditable(false);
                multi.setBackground(frame.getBackground());
                multi.setForeground(Color.black);
                multi.setBounds(10, 10, 200, 150);

                frame.add(multi);
                frame.setVisible(true);
            }
        });

        return menuBar;
    }

    void saveGame(FileDialog fileDialog) {
        ObjectOutputStream oos;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    fileDialog.getDirectory() + fileDialog.getFile());

            oos = new ObjectOutputStream(fileOutputStream);

            oos.writeObject(GameField.getGameField().cells);
            oos.writeObject(Player.getPlayer());
            oos.writeObject(Snake.getSnake());
            oos.writeObject(GameScore.getGameScore());
            oos.writeObject(RealTimerGame.getRealTimerGame());
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }


    void loadGame(FileDialog fileDialog) {
        ObjectInputStream ois;

        try {
            FileInputStream fileInputStream = new FileInputStream(
                    fileDialog.getDirectory() + fileDialog.getFile());

            ois = new ObjectInputStream(fileInputStream);

            GameField gameField = new GameField();
            gameField.cells = (int[][]) ois.readObject();
            GameField.setGameField(gameField);

            Player player = (Player) ois.readObject();
            Player.setPlayer(player);

            Snake snake = (Snake) ois.readObject();
            Snake.setSnake(snake);

            GameScore.setGameScore((GameScore) ois.readObject());  //read score
            GameScore.getGameScore().gameScore();                 //visible

            RealTimerGame timeGame = (RealTimerGame) ois.readObject();
            long serializableTime = timeGame.getRealTime();

            //re_init timer
            timeGame.setStartTime(Calendar.getInstance().getTimeInMillis());
            timeGame.setSerializableTime(serializableTime);
            RealTimerGame.setRealTimerGame(timeGame);

            System.out.println("Счет " + GameScore.getGameScore().getScore());
            System.out.println("Загрузка ячеек");

            gameField.printCells();

            System.out.println("направление" + player.getKeyPressedPlayer());
            player.setKeyPressedPlayer(player.getKeyPressedPlayer());
            ois.close();

        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("эээ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("ттт");
        }
    }
}
