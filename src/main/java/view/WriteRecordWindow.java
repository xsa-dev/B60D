package view;

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
import model.ConsoleHelper;
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
            writerPane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML("WriteRecordWindowFXML"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialElements();

        writerScene = new Scene(writerPane, 600, 400);
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
                    textField.onActionProperty().setValue(event ->
                            login = loginTextField.getText()
                    );
                    break;
                case "2":
                    passwordTextField = textField;
                    textField.onActionProperty().setValue(mouseEvent ->
                            password = passwordTextField.getText()
                    );
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

    private void initialBackToGameButton(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            closeConetion();
            theStage.setScene(managerGUIGame.getGameScene());
        });
    }

    private void initialButtonLogIn(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!"".equals(password) && !"".equals(login)) {
                if (conectorToBd.logIn(login, password)) {
                    System.out.println(conectorToBd.getConnection());
                    conectorToBd.writeRecords(managerGUIGame.getTextGUIExamples().getResalt());
                    suchesfullConect();
                }
            }
        });
    }

    private void closeConetion() {
        conectorToBd.closeConection();
    }

    private void suchesfullConect() {
        label.setText("write resalt suchesfull");
        label.setStyle("-fx-background-color: green;-fx-padding: 10px;");
    }

    private void initialButtonSignUp(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!"".equals(password) && !"".equals(login)) {
                    if (conectorToBd.signUp(login, password)) {
                        conectorToBd.writeRecords(managerGUIGame.getTextGUIExamples().getResalt());
                        suchesfullConect();//                        closeConetion();
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
