package view;

import controller.Player;
import model.GameField;
import model.Snake;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 30.08.16
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class GuiSnake extends JFrame {
    public static final int CELLS_SIZE_X = 32;
    public static final int CELLS_SIZE_Y = 32;
    public static final int sizeX = GameField.SIZE_FIELD_X;
    public static final int sizeY = GameField.SIZE_FIELD_Y;
    public static JLabel[][] jLabels = new JLabel[sizeX][sizeY];   //+1
    public static final String fileBackground = "view/img/fon.jpg";
    public static final String fileBackgroundGame = "src/view/img/pole.jpg";
    public static final String fileDelicacy[][] = {
            {       "view/img/fruit1.jpg",   //easy Delicacy
                    "view/img/fruit2.jpg",
                    "view/img/fruit3.jpg",
                    "view/img/fruit4.jpg"},
            {"view/img/tricky1.jpg",      //tricky
                    "view/img/tricky2.jpg",
                    "view/img/tricky3.jpg"}
    };
    public static final String fileMoveSnake[] = {
            "view/img/tyc_left.jpg",
            "view/img/tyc_right.jpg"};
    public static final String fileEatDelicacy[] = {
            "view/img/vkusno.jpg",
            "view/img/vkusno1.jpg",
            "view/img/vkusno2.jpg",
    };
    public static final String fileGameOver = "src/view/img/gameOver.jpg";
    private static JPanel panel1 = new JPanel();
    public static JLabel jLabelScore = new JLabel();
    public static JLabel jLabelTimer = new JLabel();
    public static JLabel jLabelGameOver = new JLabel();

    public GuiSnake() {
        Player player = new Player();
        player.setKeyPressedPlayer(Player.ButtonPressed.Right);
        Player.setPlayer(player);

        JFrame frame = new JFrame("Змейка");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);

        MenuGame menu = new MenuGame();
        JMenuBar menuBar = menu.createMenuGame();
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
        frame.addKeyListener(player);

        panel1.setLayout(new GridLayout(sizeX + 1, sizeY));     //+1 чтоб не вылезать
        createPanel(panel1);

        frame.setContentPane(panel1);

        frame.pack();
        frame.setBounds(0, 0, CELLS_SIZE_X * sizeX, CELLS_SIZE_Y * sizeY + 32 * 2);
    }

    void createPanel(JPanel panel) {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                jLabels[x][y] = new JLabel(new ImageIcon(ClassLoader.getSystemResource(fileBackground)));
               // jLabels[x][y] = new JLabel(new ImageIcon(fileBackground));
                jLabels[x][y].setVisible(false);
                panel.add(jLabels[x][y]);
            }
        }

        JLabel jLabels1 = new JLabel("bred");    //Расширил чтоб не вылезать
        jLabels1.setVisible(false);
        panel.add(jLabels1);
    }


    public static void repaintPanel() {
        GameField gameField = GameField.getGameField();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                switch (gameField.cells[x][y]) { //деликатесы анимированные, поэтому random из массива картинок
                    case -3: //Delicacy Tricky
                        jLabels[x][y].setIcon(new ImageIcon(ClassLoader.getSystemResource(
                                fileDelicacy[1][new Random().nextInt(fileDelicacy[1].length)])));
                        jLabels[x][y].setVisible(true);
                        break;
                    case -2: //Delicacy Easy
                        jLabels[x][y].setIcon(new ImageIcon(ClassLoader.getSystemResource(
                                fileDelicacy[0][new Random().nextInt(fileDelicacy[0].length)])));
                        jLabels[x][y].setVisible(true);
                        break;
                    case -1: //Empty
                        jLabels[x][y].setVisible(false);
                        break;
                    default: //Snake
                        jLabels[x][y].setIcon(new ImageIcon(ClassLoader.getSystemResource(
                                fileMoveSnake[new Random().nextInt(fileMoveSnake.length)])));
                        jLabels[x][y].setVisible(true);
                        break;
                }
            }
        }
    }

    public static void eatDelicacy() {
        Snake snake = Snake.getSnake();
        int x = (int) snake.getHeadSnakePoint().getX();
        int y = (int) snake.getHeadSnakePoint().getY();
        jLabels[x][y].setIcon(new ImageIcon(ClassLoader.getSystemResource(
                fileEatDelicacy[new Random().nextInt(fileEatDelicacy.length)])));
        jLabels[x][y].setVisible(true);
        jLabels[x][y].repaint();

    }

    public static void gameBackground() {
        JPanel jPanel = getPanel1();
        jLabelGameOver.setIcon(new ImageIcon(fileBackgroundGame));
                //ClassLoader.getSystemResource(fileBackgroundGame).getFile()));

        jPanel.setLayout(null);
        jLabelGameOver.setBounds(0, 0, 320, 300);
        jLabelGameOver.setVisible(true);
        jPanel.add(jLabelGameOver);
    }


    public static void gameOver() {
        JPanel jPanel = getPanel1();
        jLabelGameOver.setIcon(new ImageIcon(fileGameOver));
        jLabelGameOver.setBounds(0, 0, 332, 280);
        jLabelGameOver.setVisible(true);
        jPanel.add(jLabelGameOver);
    }

    public static JPanel getPanel1() {
        return panel1;
    }

    public static void setScoreGUI(int score) {
        JPanel jPanel = GuiSnake.getPanel1();
        jPanel.setLayout(null);

        jLabelScore.setBounds(10, 290, 100, 40);
        jLabelScore.setText("Очки : " + score);
        jLabelScore.setForeground(Color.black);

        jPanel.add(GuiSnake.jLabelScore);
        jPanel.repaint();

    }

    public static void gameTimer(long msec) {
        SimpleDateFormat formating = new SimpleDateFormat("mm:ss:SSS");

        JPanel jPanel = GuiSnake.getPanel1();
        jPanel.setLayout(null);

        GuiSnake.jLabelTimer.setBounds(200, 290, 150, 40);
        GuiSnake.jLabelTimer.setText("Время : " + formating.format(msec));
        GuiSnake.jLabelTimer.setForeground(Color.black);

        jPanel.add(GuiSnake.jLabelTimer);
        jPanel.repaint();
    }
}
