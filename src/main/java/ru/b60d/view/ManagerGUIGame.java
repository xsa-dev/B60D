package ru.b60d.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.b60d.controllers.Controller;
import ru.b60d.model.ProcesesCloser;
import ru.b60d.model.languages.LanguageManager;
import ru.b60d.model.loging.LogerSituations;
import ru.b60d.model.record_worker.ConectorToBd;
import ru.b60d.model.users.ManagerUserSetings;
import ru.b60d.model.workers.AbstractGUIWorker;

@Configuration
public class ManagerGUIGame extends Application {
    private static volatile ManagerGUIGame managerGUIGame;
    private static GameWindowAbstract gameWindow;
    private static AbstractWindow writeRecordWindow;
    private static AbstractGUIWorker textGUIExamples;
    private static WindowManagerLevels windowManagerLevels;
    private static ManagerUserSetings managerUserSetings;
    private static AbstractWindow showUsersStatistic;
    private static AbstractWindow startWindow;
    private static ConectorToBd conectorToBd;
    private static boolean runingWindow = false;
    private static boolean startGame = false;
    private static volatile LanguageManager languageManager;

    private static Scene startScene, gameScene, languageScene, writeRecordScene, statisticScene;
    private static volatile Stage theStage;
    private static LogerSituations loger = new LogerSituations(ManagerGUIGame.class);


    @Bean(name = "managerGUI")
    @Scope(value = "singleton")
    public ManagerGUIGame managerGUIGame(){
        return  ManagerGUIGame.managerGUIGame;
    }


    public static ManagerGUIGame play() {
        Thread thread = new Thread(() -> launch());
        thread.start();
        while (!(startGame & runingWindow)) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                loger.logError(e);
            }
        }
        return managerGUIGame;
    }

    public static void initial(){

        managerUserSetings = (ManagerUserSetings) Controller.applicationContext.getBean("managerUserSetings");

        languageManager = (LanguageManager) Controller.applicationContext.getBean("languageManager");
        languageManager.initial();

        startWindow = (AbstractWindow) Controller.applicationContext.getBean("startWindow");

        gameWindow = (GameWindowAbstract) Controller.applicationContext.getBean("gameWindow");

        windowManagerLevels = (WindowManagerLevels) Controller.applicationContext.getBean("windowManagerLevels");
        windowManagerLevels.initial();

        textGUIExamples = (AbstractGUIWorker) Controller.applicationContext.getBean("textGUIExamples");

        conectorToBd = (ConectorToBd) Controller.applicationContext.getBean("conectorToBd");

        writeRecordWindow = (AbstractWindow) Controller.applicationContext.getBean("writeRecordWindow");

        showUsersStatistic = (AbstractWindow) Controller.applicationContext.getBean("showUsersStatistic");
    }

    @Override
    public void start(Stage primaryStage) {
        ManagerGUIGame.theStage = primaryStage;
        theStage.setOnCloseRequest(event -> {
            ProcesesCloser.closeAllPoceses();
            System.exit(0);
        });

        startScene = startWindow.createScene(theStage);
        gameScene = gameWindow.createScene(theStage);

        primaryStage.setTitle("D60B");
        primaryStage.setScene(startScene);

        writeRecordScene = writeRecordWindow.createScene(theStage);
        statisticScene = showUsersStatistic.createScene(theStage);

        theStage.show();
        runingWindow = true;
    }

    public static void appendString(String message) {
        System.out.println("APPEND STRING");
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

    public static String getString() {
        return gameWindow.getString();
    }

    public static void setTextGUIExamples(AbstractGUIWorker textGUIExamples) {
        ManagerGUIGame.textGUIExamples = textGUIExamples;
    }

    public static Scene getGameScene() {
        return gameScene;
    }

    public static Scene getWriteRecordScene() {
        return writeRecordScene;
    }

    public static boolean isStartGame() {
        return startGame;
    }

    public static void setStartGame(boolean startGame) {
        ManagerGUIGame.startGame = startGame;
    }

    public static AbstractGUIWorker getTextGUIExamples() {
        return textGUIExamples;
    }

    public static ConectorToBd getConectorToBd() {
        return conectorToBd;
    }

    public static LanguageManager getLanguageManager() {
        return languageManager;
    }

    public static Scene getLanguageScene() {
        return languageScene;
    }

    public static void setLanguageScene(Scene languageScene) {
        ManagerGUIGame.languageScene = languageScene;
    }

    public static WindowManagerLevels getWindowManagerLevels() {
        return windowManagerLevels;
    }

    public static Scene getStartScene() {
        return startScene;
    }

    public static ManagerUserSetings getManagerUserSetings() {
        return managerUserSetings;
    }

    public static Scene getStatisticScene() {
        return statisticScene;
    }

    public static ManagerGUIGame getManagerGUIGame(){
        return managerGUIGame;
    }
}