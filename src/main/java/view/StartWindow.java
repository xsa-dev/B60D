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
import model.workers.IntGUIExamplesWorker;
import model.workers.TextGUIExamples;

import java.io.IOException;

public class StartWindow extends AbstractWindow {
    private Pane startPane;
    private Scene startScene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private LanguageManager languageManager;

    public StartWindow(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
    }


    public Scene createStartWindowScene(Stage theStage) {
        this.theStage = theStage;
        languageManager = managerGUIGame.getLanguageManager();
        try {
            System.out.println(ConsoleHelper.getParentPathFileFXML("WindowStartFXML"));

            startPane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML("WindowStartFXML"));//new File(ConsoleHelper.getParentPath(ControllerConsole.class) + "/WindowStartFXML.fxml").toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialElementsOrPanes(startPane);
        startScene = new Scene(startPane, 400, 400);

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
