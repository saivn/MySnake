import model.Game;
import view.GuiSnake;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 30.08.16
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class MySnake {
    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        GuiSnake guiSnake = new GuiSnake();
        Game.gameInit();
        Game.gameCycle();
    }
}


