package ru.b60d.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.b60d.model.ConsoleHelper;
import ru.b60d.model.languages.LanguageManager;
import ru.b60d.model.loging.LogerSituations;
import ru.b60d.model.workers.IntGUIExamplesWorker;
import ru.b60d.model.workers.TextGUIExamples;

import java.io.IOException;

@Component
public class StartWindow extends AbstractWindow {
    private Pane pane;
    private Scene startScene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private LanguageManager languageManager;
    private static LogerSituations loger = new LogerSituations(StartWindow.class);

    public StartWindow() {
        managerGUIGame = ManagerGUIGame.getManagerGUIGame();
    }

    @Autowired
    public StartWindow(ManagerGUIGame managerGUI) {
        this.managerGUIGame = managerGUI;
    }


    public Scene createScene(Stage theStage) {
        this.theStage = theStage;
        languageManager = ManagerGUIGame.getLanguageManager();
        try {
            pane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("WindowStartFXML"));//new File(ConsoleHelper.getParentPath(ControllerConsole.class) + "/WindowStartFXML.fxml").toURL());
        } catch (IOException e) {
            loger.logError(e);
        }
        initialElementsOrPanes(pane);
        startScene = new Scene(pane, 400, 400);

        return startScene;
    }

    @Override
    protected void initial() {

    }

    protected void initialElements(Node element) {
        if (!(element instanceof Button)) return;
        Button button = (Button) element;

        switch (button.getId()) {
            case "0":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    ManagerGUIGame.setTextGUIExamples(new TextGUIExamples(ManagerGUIGame.getManagerGUIGame()));
                    theStage.setScene(ManagerGUIGame.getGameScene());

                    ManagerGUIGame.setStartGame(true);
                });
                break;
            case "1":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    ManagerGUIGame.setTextGUIExamples(new IntGUIExamplesWorker(managerGUIGame));
                    theStage.setScene(ManagerGUIGame.getGameScene());

                    ManagerGUIGame.setStartGame(true);
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
                            ManagerGUIGame.setLanguageScene(ManagerGUIGame.getWindowManagerLevels().createScene(theStage));
                            theStage.setScene(ManagerGUIGame.getLanguageScene());
                        }
                );
                break;
        }
    }
}
