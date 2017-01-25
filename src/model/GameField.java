package model;

import javax.swing.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 29.08.16
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class GameField extends JPanel implements Serializable {
    public static final int SIZE_FIELD_X = 10;//20;
    public static final int SIZE_FIELD_Y = 10;//15;
    public static int[][] cells;
    private static GameField gameField;

    public GameField() {
        cells = new int[SIZE_FIELD_X][SIZE_FIELD_Y];

        for (int i = 0; i < SIZE_FIELD_X; i++)
            for (int j = 0; j < SIZE_FIELD_Y; j++) {
                cells[i][j] = -1;
            }
    }

    public void fillingCell() {
        Snake snake = Snake.getSnake();
        int x = (int) snake.getHeadSnakePoint().getX();
        int y = (int) snake.getHeadSnakePoint().getY();

        cells[x][y] = snake.getLength();
    }

    public void printCells() {
        System.out.println("**********");
        for (int y = 0; y < SIZE_FIELD_Y; y++) {
            for (int x = 0; x < SIZE_FIELD_X; x++) {
                System.out.print(cells[x][y]);
            }
            System.out.println();
        }
    }

    public static GameField getGameField() {
        return gameField;
    }

    public static void setGameField(GameField gameField) {
        GameField.gameField = gameField;
    }
}
