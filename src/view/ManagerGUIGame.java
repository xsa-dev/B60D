package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.record_worker.ConectorToBd;
import model.workers.AbstractGUIWorker;


/**
 * Created by Administrator1 on 24.05.2017.
 */
public class ManagerGUIGame extends Application {
    private static ManagerGUIGame managerGUIGame;
    private volatile TextGame textGame;
    private volatile WriteRecordWindow writeRecordWindow;
    private volatile AbstractGUIWorker textGUIExamples;
    private volatile ConectorToBd conectorToBd;
    private static boolean runingWindow = false;
    private static volatile boolean startGame = false;
    private boolean writeRecord = false;

    private Scene startScene, gameScene, writeRecordScene;// старт сцене сделать менеджером приложения
    private Stage theStage;
    private volatile StartWindow startWindow;

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
        conectorToBd = new ConectorToBd();
        startWindow = new StartWindow(this);
        textGame = new TextGame(this);
        writeRecordWindow = new WriteRecordWindow(this);
        managerGUIGame = this;
        theStage = primaryStage;

        startScene = startWindow.createStartWindowScene(theStage);
        gameScene = textGame.createGameScene(theStage);


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
        textGame.appendString(message);
        System.out.println("TESTMESSAGE" + message);
    }

    public String getString() {
        return textGame.getString();
    }

    public static ManagerGUIGame getManagerGUIGame() {
        return managerGUIGame;
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

    ////    public static void main(String[] args) {
////        ManagerGUIGame managerGUIGame = play();
////        managerGUIGame.appendString("write message to program");
////        for (int i = 0; i < 10; i++)
////            System.out.println(managerGUIGame.getString());
////    }
}