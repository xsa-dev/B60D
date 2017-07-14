package ru.b60d.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.b60d.controllers.Controller;
import ru.b60d.model.ConsoleHelper;
import ru.b60d.model.loging.LogerSituations;
import ru.b60d.model.record_worker.ConectorToBd;

import java.io.IOException;

@Component
public class WriteRecordWindow extends AbstractWindow{
    private Pane pane;
    private Label label;
    private Scene writerScene;
    private String login = "";
    private String password = "";
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private ConectorToBd conectorToBd;
    private TextField loginTextField;
    private TextField passwordTextField;
    private static LogerSituations loger = new LogerSituations(WriteRecordWindow.class);

    public WriteRecordWindow() {
        managerGUIGame = ManagerGUIGame.getManagerGUIGame();
    }

    public void initial(){
        conectorToBd = (ConectorToBd) Controller.applicationContext.getBean("conectorToBd");
    }

    @Autowired
    public WriteRecordWindow(ManagerGUIGame managerGUI) {
        this.managerGUIGame = managerGUI;
        conectorToBd = managerGUIGame.getConectorToBd();
    }


    public Scene createScene(Stage theStage) {
        initial();
        this.theStage = theStage;
        try {
            pane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("WriteRecordWindowFXML"));
        } catch (IOException e) {
            loger.logError(e);
        }
        initialElementsOrPanes(pane);

        writerScene = new Scene(pane, 600, 400);
        return writerScene;
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
                    break;
                case "showRecords":
                    initialButtonShowStatistic(button);
                    break;
            }
        }
        if (element instanceof Label) {
            this.label = (Label) element;
            label.setStyle("-fx-background-color: red;-fx-padding: 10px;");
        }
    }

    private void initialButtonShowStatistic(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            theStage.setScene(ManagerGUIGame.getStatisticScene());
        });
    }

    private void initialBackToGameButton(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {//change handling
            closeConetion();
            theStage.setScene(ManagerGUIGame.getGameScene());
            System.out.println("back to game");
        });
    }

    private void initialButtonLogIn(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!"".equals(password) && !"".equals(login)) {
                if (conectorToBd.logIn(login, password)) {
                    System.out.println(conectorToBd.getConnection());
                    conectorToBd.writeRecords(ManagerGUIGame.getTextGUIExamples().getResalt());
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
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!"".equals(password) && !"".equals(login)) {
                if (conectorToBd.signUp(login, password)) {
                    conectorToBd.writeRecords(ManagerGUIGame.getTextGUIExamples().getResalt());
                    suchesfullConect();
                }
            }
        });
    }

    @Override
    protected void initialElements(Node element) {
        for (Node node : pane.getChildren()) {
            initialStartElements(node);
        }
    }
}
