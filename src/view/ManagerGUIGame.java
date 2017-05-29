package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.ProcesesCloser;
import model.languages.LanguageManager;
import model.record_worker.ConectorToBd;
import model.workers.AbstractGUIWorker;


/**
 * Created by Administrator1 on 24.05.2017.
 */
public class ManagerGUIGame extends Application {
    private static ManagerGUIGame managerGUIGame;
    private GameWindow gameWindow;
    private WriteRecordWindow writeRecordWindow;
    private AbstractGUIWorker textGUIExamples;
    private ConectorToBd conectorToBd;
    private static boolean runingWindow = false;
    private static boolean startGame = false;
    private volatile LanguageManager languageManager;

    private Scene startScene, gameScene, writeRecordScene;// старт сцене сделать менеджером приложения
    private Stage theStage;
    private StartWindow startWindow;

    public static ManagerGUIGame play() {
        Thread thread = new Thread(() -> launch());
        thread.start();
//        while (!runingWindow) {
        while (!(startGame & runingWindow)) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return managerGUIGame;
    }

    public void start(Stage primaryStage) {
        languageManager = new LanguageManager();
        startWindow = new StartWindow(this);
        gameWindow = new GameWindow(this);
        writeRecordWindow = new WriteRecordWindow(this);
        managerGUIGame = this;
        theStage = primaryStage;
        theStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                ProcesesCloser.closeAllPoceses();
                System.exit(0);
            }
        });

        startScene = startWindow.createStartWindowScene(theStage);
        gameScene = gameWindow.createGameScene(theStage);


        primaryStage.setTitle("D60B");
        primaryStage.setScene(startScene);
        primaryStage.show();
        runingWindow = true;
    }

    public Scene createWriteRecordScene(){
        conectorToBd = new ConectorToBd();
        writeRecordWindow = new WriteRecordWindow(this);
        writeRecordScene = writeRecordWindow.createWriterToDBScene(theStage);

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
                e.printStackTrace();
            }
        }
        return temp;
    }

    public void clearOutTextArea(){
        gameWindow.clesrOutTextArea();
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
}