package controller;

import model.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 29.08.16
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class Player extends KeyAdapter implements Serializable {

    private ButtonPressed keyPressedPlayer;
    public static enum ButtonPressed{Left, Right, Down, Up};


    private static Player player;



    @Override
    public void keyReleased(KeyEvent e) {
        int keyPressedButton = e.getKeyCode();
        switch (keyPressedButton) {
            case 40:
                keyPressedPlayer = Player.ButtonPressed.Down;
                break;
            case 38:
                keyPressedPlayer = Player.ButtonPressed.Up;
                break;
            case 37:
                keyPressedPlayer = Player.ButtonPressed.Left;
                break;
            case 39:
                keyPressedPlayer = Player.ButtonPressed.Right;
                break;
        }
        Snake snake = Snake.getSnake();
        snake.setCourse(keyPressedPlayer.toString());
        Snake.setSnake(snake);
        System.out.println("Нажата клавиша "+e.getKeyText(e.getKeyCode()));
    }


    public  ButtonPressed getKeyPressedPlayer() {
        return keyPressedPlayer;
    }

    public void setKeyPressedPlayer(ButtonPressed keyPressedPlayer) {
        this.keyPressedPlayer = keyPressedPlayer;
    }


    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Player.player = player;
    }

}
