package model;

import view.GuiSnake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 29.08.16
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public class Game extends JFrame {
    public static final Object restartMutex = new Object();

    public static void gameInit() throws InterruptedException {

        GameScore gameScore = new GameScore();
        GameScore.setGameScore(gameScore);

        GameField gameField = new GameField();
        GameField.setGameField(gameField);

        int timer = 1000 / 4;
        int length = 1;
        String course = "Right";
        Snake snake = new Snake(gameField, new Point(2, 2), course, length, timer);
        snake.setSnake(snake);

        System.out.println("head" + snake.getHeadSnakePoint());
        Delicacies.setTypeDelicacies(Delicacies.TypeDelicacies.Easy);
        Delicacies.setDelicacy();

        gameScore.gameScore();
        GuiSnake.gameBackground();
    }

    public static void gameCycle() {

        RealTimerGame realTimerGame = new RealTimerGame();
        RealTimerGame.setRealTimerGame(realTimerGame);
        realTimerGame.startTimer();

        Thread thread = new Thread();                            //поток для скорости змейки
        thread.start();

        Snake.Eat eat;
        Delicacies.TypeDelicacies typeDelicacies = null;

        int countEatDelicacy = 1;
        int countTrickyDelicacy = 1;
        do {

            Snake snake = Snake.getSnake();
            GameScore gameScore = GameScore.getGameScore();

            try {
                Thread.sleep(Snake.getSnake().getSpeedSnake());  //скорость змейки
            } catch (InterruptedException e) {
                e.printStackTrace();                     //To change body of catch statement use File | Settings | File Templates.
            }

            System.out.println("Курс " + Snake.getSnake().getCourse());
            snake.remove();                            //змейка двигаемся
            eat = snake.whatEatHead();                 //что съела
            if (eat.equals(Snake.Eat.Delicacies)) {    //деликатес съела

                GuiSnake.eatDelicacy();
                gameScore.gameScore();                 //increment score
                typeDelicacies = (countEatDelicacy % Delicacies.TIMER_TYPE_DELICACY == 0) ?
                        Delicacies.TypeDelicacies.Tricky :
                        Delicacies.TypeDelicacies.Easy;
                System.out.println("type delicacy " + typeDelicacies + " cd " + countEatDelicacy);
                Delicacies.setTypeDelicacies(typeDelicacies);
                Delicacies.eatDelicacy();
                countEatDelicacy++;
                if (countEatDelicacy == Delicacies.TIMER_TYPE_DELICACY + 1){
                    countEatDelicacy = 1;
                }

            }
            GameField.getGameField().fillingCell();
            countTrickyDelicacy=Delicacies.checkChangeTypeDelicacies(countTrickyDelicacy);

            if (!eat.equals(Snake.Eat.Delicacies)) {
                GuiSnake.repaintPanel();
            }

            if (eat.equals(Snake.Eat.NipOneself)) {       //сама себя съела
                try {
                    GuiSnake.gameOver();
                    int place = TableRecords.manager();    //место в таблице рекордов
                    if (place == 0) {
                        eat = Snake.Eat.Empty;
                    }

                    synchronized (restartMutex) {
                        restartMutex.wait();               //ждем пока не нажмем в меню Новая игра
                    }

                    RealTimerGame.reStartTimer();
                    GuiSnake.jLabelGameOver.setVisible(false);

                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } while (true);
    }
}
