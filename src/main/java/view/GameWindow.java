package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ConsoleHelper;
import model.loging.LogerSituations;

import java.io.IOException;


/**
 * Created by Administrator1 on 24.05.2017.
 */
public class GameWindow extends AbstractWindow{
    private volatile TextArea outTextArea;
    private TextField inTextField;
    private String ansver = "";
    private boolean writeRecord = false;

    private Pane gamePane;
    private Scene gameScene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private static LogerSituations loger = new LogerSituations(GameWindow.class);

    public GameWindow(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
    }

    public Scene createGameScene(Stage theStage){
        this.theStage = theStage;
        try {
//            gamePane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML("WindowGameTextFXML"));
            gamePane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("WindowGameTextFXML"));

        } catch (IOException e) {
            loger.logError(e);
        }

        initialElementsOrPanes(gamePane);

        return gameScene = new Scene(gamePane, 600, 500);
    }

    public void appendString(String message) {
        outTextArea.appendText(message + "\n");
    }

    public void clesrOutTextArea(){
        outTextArea.clear();
    }

    public String getString() {
        while (ansver.equals("")) {
            if (writeRecord) {
                ansver = "EXIT";
                break;
            }
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                loger.logError(e);
            }
        }
        String res = ansver;
        ansver = "";
        return res;
    }


    protected void initialElements(Node node) {
        switch (node.getId()) {
            case "1":
                initialTableView((TextArea) node);
                break;
            case "2":
                initialTextField((TextField) node);
                break;
            case "3":
                initialBattonRecord((Button) node);
                break;
        }
    }

    private void initialBattonRecord(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            managerGUIGame.getTextGUIExamples().endGame();
            managerGUIGame.createWriteRecordScene();
            theStage.setScene(managerGUIGame.getWriteRecordScene());
            writeRecord = true;
        });
    }

    private void initialTableView(TextArea node) {
        outTextArea = node;

        outTextArea.textProperty().addListener((observable, oldValue, newValue) ->
                outTextArea.setScrollTop(Double.MAX_VALUE)
        );
    }

    private void initialTextField(TextField node) {
        inTextField = node;
        inTextField.setOnAction(event -> {
            ansver = inTextField.getText();
            inTextField.clear();
        });
        inTextField.requestFocus();
    }

}