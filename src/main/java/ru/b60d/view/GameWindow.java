package ru.b60d.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.b60d.model.ConsoleHelper;
import ru.b60d.model.loging.LogerSituations;

import java.io.IOException;

@Component
public class GameWindow extends GameWindowAbstract{
    private volatile TextArea outTextArea;
    private TextField inTextField;
    private String ansver = "";
    private boolean writeRecord = false;

    private Pane pane;
    private Scene gameScene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private static LogerSituations loger = new LogerSituations(GameWindow.class);

    public GameWindow() {
        managerGUIGame = ManagerGUIGame.getManagerGUIGame();
    }

    public GameWindow(ManagerGUIGame managerGUI) {
        this.managerGUIGame = managerGUI;
    }

    public Scene createScene(Stage theStage){
        this.theStage = theStage;
        try {
            pane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("WindowGameTextFXML"));
        } catch (IOException e) {
            loger.logError(e);
        }

        initialElementsOrPanes(pane);

        return gameScene = new Scene(pane, 600, 500);
    }

    @Override
    protected void initial() {

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
            ManagerGUIGame.getTextGUIExamples().endGame();
            theStage.setScene(ManagerGUIGame.getWriteRecordScene());
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

    public ManagerGUIGame getManagerGUIGame() {
        return managerGUIGame;
    }

    public void setManagerGUIGame(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
    }
}