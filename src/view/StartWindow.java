package view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.languages.LanguageManager;
import model.workers.IntGUIExamplesWorker;
import model.workers.TextGUIExamples;

import java.io.IOException;

/**
 * Created by Administrator1 on 24.05.2017.
 */
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
            startPane = FXMLLoader.load(StartWindow.class.getResource("WindowStartFXML.fxml"));
        } catch (IOException e) {
            System.out.println("jncokmserjnvicnefknvejfnv");
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
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        managerGUIGame.setTextGUIExamples(new TextGUIExamples(managerGUIGame));
                        theStage.setScene(managerGUIGame.getGameScene());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        managerGUIGame.setStartGame(true);
                    }
                });
                break;
            case "1":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                    managerGUIGame.setTextGUIExamples(new IntGUIExamplesWorker(managerGUIGame));
                    theStage.setScene(managerGUIGame.getGameScene());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    managerGUIGame.setStartGame(true);
                });
                break;
            case "LanguageInglish":
                System.out.println("gfhjdkslkdjfh");
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) ->
                    languageManager.setLanguageType(LanguageManager.LanguageType.INGLISH));
                break;
            case "LanguageRussian":
                System.out.println("gfhjdkslkdjfh");
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                        languageManager.setLanguageType(LanguageManager.LanguageType.RUSSIAN));
                break;
        }
    }
}
