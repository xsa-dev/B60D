package model.workers;

import model.languages.LanguageManager;
import view.ManagerGUIGame;

import java.util.Date;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public abstract class AbstractGUIWorker {
    protected ManagerGUIGame gameWindow;
    protected boolean wriwRecord = false;
    protected int resalt;
    protected LanguageManager languageMP;

    public AbstractGUIWorker() {

    }

    public AbstractGUIWorker(ManagerGUIGame gameWindow) {
        this.gameWindow = gameWindow;
        this.languageMP = gameWindow.getLanguageManager();
    }

    protected int considersPoints(int points, Date startDate, Date endDate) {

        long differentsDates = endDate.getTime() - startDate.getTime();
        return (int) (((double) (points)) / (differentsDates / 1000) * 100 * 100) ;
    }

    public abstract int launching() throws Exception;

    public void endGame() {
        setWriwRecord(true);
    }

    protected int endResalt(int points, Date startDate, Date endDate) {
        resalt = considersPoints(points, startDate, endDate);
        gameWindow.appendString(languageMP.getPhrase("game.end") + resalt);
        return resalt;
    }

    public ManagerGUIGame getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(ManagerGUIGame gameWindow) {
        this.gameWindow = gameWindow;
    }

    public boolean isWriwRecord() {
        return wriwRecord;
    }

    public void setWriwRecord(boolean wriwRecord) {
        this.wriwRecord = wriwRecord;
    }

    public int getResalt() {
        return resalt;
    }

    public void setResalt(int resalt) {
        this.resalt = resalt;
    }
}
