package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ProcesesCloser;
import model.languages.LanguageManager;
import model.loging.LogerSituations;
import model.record_worker.ConectorToBd;
import model.users.ManagerUserSetings;
import model.workers.AbstractGUIWorker;


/**
 * Created by Administrator1 on 24.05.2017.
 */
public class ManagerGUIGame extends Application {
    private static ManagerGUIGame managerGUIGame;
    private GameWindowAbstract gameWindow;
    private AbstractWindow writeRecordWindow;
    private AbstractGUIWorker textGUIExamples;
    private WindowManagerLevels windowManagerLevels;
    private ManagerUserSetings managerUserSetings;
    private AbstractWindow showUsersStatistic;
    private AbstractWindow startWindow;
    private ConectorToBd conectorToBd;
    private static boolean runingWindow = false;
    private static boolean startGame = false;
//    private LanguageManager languageManager;
    private volatile LanguageManager languageManager;

    private Scene startScene, gameScene, languageScene, writeRecordScene, statisticScene;
    private Stage theStage;
    private static LogerSituations loger = new LogerSituations(ManagerGUIGame.class);

    public static ManagerGUIGame play() {
        Thread thread = new Thread(() -> launch());
        thread.start();
//        while (!runingWindow) {
        while (!(startGame & runingWindow)) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                loger.logError(e);
            }
        }
        return managerGUIGame;
    }

    public void start(Stage primaryStage) {
        managerUserSetings = new ManagerUserSetings(this);
        languageManager = new LanguageManager(this);
        startWindow = new StartWindow(this);
        gameWindow = new GameWindow(this);
        windowManagerLevels = new WindowManagerLevels(this);
        managerGUIGame = this;
        theStage = primaryStage;
        theStage.setOnCloseRequest(event -> {
            ProcesesCloser.closeAllPoceses();
            System.exit(0);
        });

        startScene = startWindow.createScene(theStage);
        gameScene = gameWindow.createScene(theStage);

        primaryStage.setTitle("D60B");
        primaryStage.setScene(startScene);

        primaryStage.show();
        runingWindow = true;
    }

    public Scene createWriteRecordScene(){
        conectorToBd = new ConectorToBd();
        writeRecordWindow = new WriteRecordWindow(this);
        writeRecordScene = writeRecordWindow.createScene(theStage);
        showUsersStatistic = new ShowUsersStatistic(this);
        statisticScene = showUsersStatistic.createScene(theStage);

        return writeRecordScene;
    }

    public void appendString(String message) {
        gameWindow.appendString(message);
    }

    public String getStringNumber(){
        String temp;
        while (true){
            temp = getString();
            if (temp.equals("EXIT") || temp.matches("^([-+]?\\d+)$")) {
                break;
            }
            appendString(languageManager.getPhrase("game.repeat_operation"));
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                loger.logError(e);
            }
        }
        return temp;
    }

    public String getString() {
        return gameWindow.getString();
    }

    public void setTextGUIExamples(AbstractGUIWorker textGUIExamples) {
        this.textGUIExamples = textGUIExamples;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public Scene getWriteRecordScene() {
        return writeRecordScene;
    }

    public static boolean isStartGame() {
        return startGame;
    }

    public static void setStartGame(boolean startGame) {
        ManagerGUIGame.startGame = startGame;
    }

    public AbstractGUIWorker getTextGUIExamples() {
        return textGUIExamples;
    }

    public ConectorToBd getConectorToBd() {
        return conectorToBd;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public Scene getLanguageScene() {
        return languageScene;
    }

    public void setLanguageScene(Scene languageScene) {
        this.languageScene = languageScene;
    }

    public WindowManagerLevels getWindowManagerLevels() {
        return windowManagerLevels;
    }

    public Scene getStartScene() {
        return startScene;
    }

    public ManagerUserSetings getManagerUserSetings() {
        return managerUserSetings;
    }

    public Scene getStatisticScene() {
        return statisticScene;
    }
}