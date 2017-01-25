package model;

import view.GuiSnake;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 06.09.16
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */
public class RealTimerGame implements Serializable {

    private  long realTime;
    private  long startTime;
    private  long serializableTime;

    public static RealTimerGame realTimerGame;

    private static Timer timer = new Timer();

    public RealTimerGame() {
        startTime =Calendar.getInstance().getTimeInMillis();
        serializableTime =0;
    }



    public void startTimer(){
        //Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                RealTimerGame realTimerGame1 = getRealTimerGame();
                Calendar nowTime = Calendar.getInstance();
                long msec = nowTime.getTimeInMillis() - realTimerGame1.getStartTime() + realTimerGame1.getSerializableTime();
                GuiSnake.gameTimer(msec);
                setRealTime(msec);

            }
        }, 0, 100);
    }

    public static void stopTimer(){
        timer.cancel();
    }
    public static void reStartTimer(){
        RealTimerGame realTimerGame1 = getRealTimerGame();
        realTimerGame.setSerializableTime(0);
        realTimerGame.setStartTime(Calendar.getInstance().getTimeInMillis());
    }

    public long getRealTime() {
        return realTime;
    }

    public void setRealTime(long realTime) {
        this.realTime = realTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getSerializableTime() {
        return serializableTime;
    }

    public void setSerializableTime(long serializableTime) {
        this.serializableTime = serializableTime;
    }

    public static RealTimerGame getRealTimerGame() {
        return realTimerGame;
    }

    public static void setRealTimerGame(RealTimerGame realTimerGame) {
        RealTimerGame.realTimerGame = realTimerGame;
    }

}
