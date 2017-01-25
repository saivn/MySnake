package model;

import view.GuiSnake;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 02.09.16
 * Time: 10:00
 * To change this template use File | Settings | File Templates.
 */
public class GameScore implements Serializable {
    public static final int SCORE_COUNT_EASY = 10;
    public static final int SCORE_COUNT_TRICKY = 50;
    private int score = -SCORE_COUNT_EASY;
    private static GameScore gameScore;

    public void gameScore() {

        int score = getScore();
        System.out.println("score " + score);
        score = incScore(score);
        setScore(score);
        GuiSnake.setScoreGUI(score);
    }

    int incScore(int score) {
        switch (Delicacies.getTypeDelicacies()) {
            case Easy:
                score += SCORE_COUNT_EASY;
                break;
            case Tricky:
                score += SCORE_COUNT_TRICKY;
                break;
        }
        return score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static GameScore getGameScore() {
        return gameScore;
    }

    public static void setGameScore(GameScore gameScore) {
        GameScore.gameScore = gameScore;
    }
}
