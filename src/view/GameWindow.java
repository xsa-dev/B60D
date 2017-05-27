package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by Administrator1 on 24.05.2017.
 */
public class GameWindow {
    private volatile TextArea outTextArea;
    private TextField inTextField;
    private String ansver = "";
    private boolean writeRecord = false;

    private Pane gamePane;
    private Scene gameScene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;

    public GameWindow(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
//        theStage = managerGUIGame.getTheStage();
    }

    public Scene createGameScene(Stage theStage){
        this.theStage = theStage;
        try {
            gamePane = FXMLLoader.load(ManagerGUIGame.class.getResource("WindowGameTextFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Node node : gamePane.getChildren()) {
            initialNodesForTexts(node);
        }

        return gameScene = new Scene(gamePane, 600, 500);
    }

    public void appendString(String message) {
        outTextArea.appendText(message + "\n");
        outTextArea.setScrollTop(message.length() + 2); //this will scroll to the bottom
//        outTextArea.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
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
                e.printStackTrace();
            }
        }
        String res = ansver;
        ansver = "";
        return res;
    }


    private void initialNodesForTexts(Node node) {
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
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                managerGUIGame.getTextGUIExamples().endGame();
                System.out.println(theStage);
                managerGUIGame.createWriteRecordScene();
                theStage.setScene(managerGUIGame.getWriteRecordScene());
                writeRecord = true;
            }
        });
    }

    private void initialTableView(TextArea node) {
        outTextArea = node;

//        outTextArea.textProperty().addListener(new ChangeListener<Object>() {
//            @Override
//            public void changed(ObservableValue<?> observable, Object oldValue,
//                                Object newValue) {
//                outTextArea.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
//                //use Double.MIN_VALUE to scroll to the top
//            }
//        });
    }

    private void initialTextField(TextField node) {
        inTextField = node;
        inTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                ansver = inTextField.getText();
                inTextField.clear();
            }
        });
        inTextField.requestFocus();
    }

}