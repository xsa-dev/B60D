package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ConsoleHelper;
import model.languages.LanguageManager;
import model.loging.LogerSituations;
import model.workers.IntGUIExamplesWorker;
import model.workers.TextGUIExamples;

import java.io.IOException;

public class StartWindow extends AbstractWindow {
    private Pane pane;
    private Scene startScene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private LanguageManager languageManager;
    private static LogerSituations loger = new LogerSituations(StartWindow.class);

    public StartWindow(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
    }


    public Scene createScene(Stage theStage) {
        this.theStage = theStage;
        languageManager = managerGUIGame.getLanguageManager();
        try {
            pane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("WindowStartFXML"));//new File(ConsoleHelper.getParentPath(ControllerConsole.class) + "/WindowStartFXML.fxml").toURL());
        } catch (IOException e) {
            loger.logError(e);
        }
        initialElementsOrPanes(pane);
        startScene = new Scene(pane, 400, 400);

        return startScene;
    }

    protected void initialElements(Node element) {
        if (!(element instanceof Button)) return;
        Button button = (Button) element;

        switch (button.getId()) {
            case "0":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    managerGUIGame.setTextGUIExamples(new TextGUIExamples(managerGUIGame));
                    theStage.setScene(managerGUIGame.getGameScene());

                    managerGUIGame.setStartGame(true);
                });
                break;
            case "1":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    managerGUIGame.setTextGUIExamples(new IntGUIExamplesWorker(managerGUIGame));
                    theStage.setScene(managerGUIGame.getGameScene());

                    managerGUIGame.setStartGame(true);
                });
                break;
            case "LanguageInglish":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) ->
                        languageManager.setLanguageType(LanguageManager.LanguageType.INGLISH)
                );
                break;
            case "LanguageRussian":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                        languageManager.setLanguageType(LanguageManager.LanguageType.RUSSIAN)
                );
                break;
            case "ButtonChangeLevel":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                        {
                            managerGUIGame.setLanguageScene(managerGUIGame.getWindowManagerLevels().createScene(theStage));
                            theStage.setScene(managerGUIGame.getLanguageScene());
                        }
                );
                break;
        }
    }
}
