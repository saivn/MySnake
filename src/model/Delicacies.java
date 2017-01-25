package model;

import java.awt.*;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 29.08.16
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class Delicacies {
    final static int TIMER_TYPE_DELICACY = 5; //через каждые 5 съеденных выставляем арбуз
    final static int TIMER_SHOW_DELICACY_TRICKY = 10; // GameField.SIZE_FIELD_X*2;
    //10 ходов подсветка арбуза
    public static double pointX;
    public static double pointY;
    private static TypeDelicacies typeDelicacies; //-2 || -3
    public static enum TypeDelicacies {Easy, Tricky};

    void setRandomPlaceDelicacy() {
        GameField gameField = GameField.getGameField();
        Point freePointsForDelicacies[] = new Point[GameField.SIZE_FIELD_X * GameField.SIZE_FIELD_Y];
        int countFreePoints = 0;
        for (int i = 0; i < GameField.SIZE_FIELD_X; i++)
            for (int j = 0; j < GameField.SIZE_FIELD_Y; j++) {
                if (gameField.cells[i][j] == -1) {
                    freePointsForDelicacies[countFreePoints] = new Point();
                    freePointsForDelicacies[countFreePoints].setLocation(i, j);
                    countFreePoints++;
                }
            }
        System.out.println("countFreePoints" + countFreePoints);

        Random rand = new Random();
        int indexRandom = rand.nextInt(countFreePoints - 1);

        System.out.println("indexRandom" + indexRandom);
        Point pointDelicacy = freePointsForDelicacies[indexRandom];
        System.out.println("pointDelicacy " + pointDelicacy);

        pointX = pointDelicacy.getX();
        pointY = pointDelicacy.getY();
    }

    public static void eatDelicacy() {
        Snake snake = Snake.getSnake();
        setDelicacy();
        snake.eatDelicacy();
    }

    public static void setDelicacy() {
        Delicacies delicacies = new Delicacies();
        delicacies.setRandomPlaceDelicacy();
        delicacies.setDelicacyInCells();
    }

    public static void setDelicacyInCells() {
        int x = (int) pointX;
        int y = (int) pointY;
        GameField gameField = GameField.getGameField();

        System.out.println("x " + pointX + "y " + pointY);
        switch (getTypeDelicacies()) {
            case Easy:
                gameField.cells[x][y] = -2;
                break;
            case Tricky:
                gameField.cells[x][y] = -3;
                System.out.println("arbuzzz!!!!");
                break;
        }

        GameField.getGameField().printCells();
    }


    public static TypeDelicacies getTypeDelicacies() {
        return typeDelicacies;
    }

    public static void setTypeDelicacies(TypeDelicacies typeDelicacies1) {

        Delicacies.typeDelicacies = typeDelicacies1;
    }

    public static int checkChangeTypeDelicacies(int count) {
        if (Delicacies.getTypeDelicacies().equals(Delicacies.TypeDelicacies.Tricky)) {
            count++;
            if (count == TIMER_SHOW_DELICACY_TRICKY) {
                Delicacies.setTypeDelicacies(Delicacies.TypeDelicacies.Easy);
                Delicacies.setDelicacyInCells();
                count = 1;
            }
            GameField.getGameField().printCells();
        }
        return count;
    }
}
