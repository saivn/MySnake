package model;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 29.08.16
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class Snake implements Serializable {

    public static enum Course {Left, Right, Down, Up}

    ;

    public static enum Eat {Empty, Delicacies, NipOneself}

    ;
    private Point headSnakePoint;
    private String course;
    private int length;
    private int speedSnake;
    private static Snake snake;
    Point zeroPoint;

    public Snake(GameField gameField, Point zeroPoint, String course, int length, int speed) {

        this.zeroPoint = zeroPoint;
        this.headSnakePoint = this.zeroPoint;
        this.course = course;
        this.length = length;
        this.speedSnake = speed;

        int x = (int) zeroPoint.getX();
        int y = (int) zeroPoint.getY();

        for (int i = 0; i < length; i++) {
            gameField.cells[x - i][y] = length - i;
        }
    }

    public Eat whatEatHead() {
        GameField gameField = GameField.getGameField();

        double x = this.headSnakePoint.getX();
        double y = this.headSnakePoint.getY();
        switch (gameField.cells[((int) x)][(int) y]) {
            case -3:
                return Eat.Delicacies;
            case -2:
                return Eat.Delicacies;
            case -1:
                return Eat.Empty;
            default:
                return Eat.NipOneself;
        }
    }

    public void headRemove(int indexCourse) {
        double pointSnakeX = this.headSnakePoint.getX();
        double pointSnakeY = this.headSnakePoint.getY();
        Course[] courses = Course.values();

        switch (courses[indexCourse]) {
            case Left:
                if (pointSnakeX == 0) pointSnakeX = GameField.SIZE_FIELD_X;//2
                pointSnakeX--;
                break;
            case Right:
                if (pointSnakeX == GameField.SIZE_FIELD_X - 1) pointSnakeX = -1;
                pointSnakeX++;
                break;
            case Down:
                if (pointSnakeY == GameField.SIZE_FIELD_Y - 1) pointSnakeY = -1;
                pointSnakeY++;
                break;
            case Up:
                if (pointSnakeY == 0) pointSnakeY = GameField.SIZE_FIELD_Y;
                pointSnakeY--;
                break;
        }
        headSnakePoint.setLocation(pointSnakeX, pointSnakeY);
    }

    public void remove() {
        Snake snake = Snake.getSnake();
        tailRemove();

        int indexCourse = Course.valueOf(snake.getCourse()).ordinal();
        headRemove(indexCourse);
    }

    void tailRemove() {
        GameField gameField = GameField.getGameField();
        for (int i = 0; i < GameField.SIZE_FIELD_X; i++) {
            for (int j = 0; j < GameField.SIZE_FIELD_Y; j++) {
                {
                    if (gameField.cells[i][j] >= 0) gameField.cells[i][j]--;
                }
            }
        }
    }


    void tailIncrease() {
        GameField gameField = GameField.getGameField();
        for (int i = 0; i < GameField.SIZE_FIELD_X; i++) {
            for (int j = 0; j < GameField.SIZE_FIELD_Y; j++) {
                if (gameField.cells[i][j] >= 0) {
                    gameField.cells[i][j]++;
                }
            }
        }
    }

    void eatDelicacy() {
        length++;
        tailIncrease();
    }

    public Point getHeadSnakePoint() {
        return headSnakePoint;
    }

    public void setHeadSnakePoint(Point headSnakePoint) {
        this.headSnakePoint = headSnakePoint;
    }

    public static Snake getSnake() {
        return snake;
    }

    public static void setSnake(Snake snake) {
        Snake.snake = snake;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSpeedSnake() {
        return speedSnake;
    }

    public void setSpeedSnake(int speedSnake) {
        this.speedSnake = speedSnake;
    }

}
