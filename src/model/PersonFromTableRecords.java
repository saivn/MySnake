package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: sasha
 * Date: 08.09.16
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */
public class PersonFromTableRecords extends TableRecords implements Serializable {
    private String name;
    private Integer age;
    private Integer amount;
    private String date;
    private long timerGame;

    public PersonFromTableRecords(String name, Integer age, Integer amount,
                                  long timerGame) {
        this.name = name;
        this.amount = amount;
        this.age = age;
        this.date = setCurrentDate();
        this.timerGame = timerGame;
    }

    public String setCurrentDate(){

        SimpleDateFormat formating = new SimpleDateFormat("dd MMM, yyyy");
        Calendar currentTime = Calendar.getInstance();
        long mls = currentTime.getTimeInMillis();

        System.out.println(formating.format(mls));
        return formating.format(mls);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTimerGame() {
        return timerGame;
    }

    public void setTimerGame(long timerGame) {
        this.timerGame = timerGame;
    }

}
