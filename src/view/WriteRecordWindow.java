package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.record_worker.ConectorToBd;

import java.io.IOException;

/**
 * Created by Administrator1 on 25.05.2017.
 */
public class WriteRecordWindow {
    private Pane writerPane;
    private Label label;
    private Scene writerScene;
    private String login = "";
    private String password = "";
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private ConectorToBd conectorToBd;
    private TextField loginTextField;
    private TextField passwordTextField;

    public WriteRecordWindow(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
        conectorToBd = managerGUIGame.getConectorToBd();
    }


    public Scene createWriterToDBScene(Stage theStage) {
        this.theStage = theStage;
        try {
            writerPane = FXMLLoader.load(ManagerGUIGame.class.getResource("WriteRecordWindowFXML.fxml"));
        } catch (IOException e) {
            System.out.println("jncokmserjnvicnefknvejfnv");
            e.printStackTrace();
        }
        initialElements();


        writerScene = new Scene(writerPane, 500, 400);
        return writerScene;
    }

    private void initialElements() {
        for (Node node : writerPane.getChildren()) {
            initialStartElements(node);
        }
    }

    private void initialStartElements(Node element) {
        if (element instanceof TextField) {
            TextField textField = (TextField) element;

            switch (textField.getId()) {
                case "1":
                    loginTextField = textField;
                    textField.onActionProperty().setValue(new EventHandler<ActionEvent>() {
//                    textField.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(javafx.event.ActionEvent event) {
                            login = loginTextField.getText();
                        }
                    });
                    break;
                case "2":
                    passwordTextField = textField;
                    textField.onActionProperty().setValue(new EventHandler<ActionEvent>() {
//                    textField.addEventHandler(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent mouseEvent) {
                            password = passwordTextField.getText();
                        }
                    });
                    break;
            }
        }
        if (element instanceof Button) {
            Button button = (Button) element;
            switch (button.getId()) {
                case "buttonLogIn":
                    initialButtonLogIn(button);
                    break;
                case "buttonSignUp":
                    initialButtonSignUp(button);
                    break;
                case "buttonBackToGame":
                    initialBackToGameButton(button);
            }
        }
        if (element instanceof Label) {
            this.label = (Label) element;
            label.setStyle("-fx-background-color: red;-fx-padding: 10px;");
        }
    }

    private void initialBackToGameButton(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                theStage.setScene(managerGUIGame.getGameScene());
            }
        });
    }

    private void initialButtonLogIn(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("vkmsdlc;LOGIN");
                if (!"".equals(password) && !"".equals(login)) {
                    System.out.println("testLOGIN   " + login + password);
                    if (conectorToBd.logIn(login, password)) {
                        System.out.println(conectorToBd.getConnection());
                        conectorToBd.writeRecords(managerGUIGame.getTextGUIExamples().getResalt());
                        closeConetion();
                    }
                }
            }
        });
    }

    private void closeConetion(){
        conectorToBd.closeConection();
        label.setText("write resalt suchesfull");
        label.setStyle("-fx-background-color: green;-fx-padding: 10px;");
    }

    private void initialButtonSignUp(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("vkmsdlc;SIGNUP");

                if (!"".equals(password) && !"".equals(login)) {
                    System.out.println("signUP such");
                    if (conectorToBd.signUp(login, password)) {
                        conectorToBd.writeRecords(managerGUIGame.getTextGUIExamples().getResalt());
                        closeConetion();
                    }
                }
            }
        });
    }

    public ConectorToBd getConectorToBd() {
        return conectorToBd;
    }

    public void setConectorToBd(ConectorToBd conectorToBd) {
        this.conectorToBd = conectorToBd;
    }
}
