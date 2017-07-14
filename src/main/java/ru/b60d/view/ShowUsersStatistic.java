package ru.b60d.view;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
public class ShowUsersStatistic extends AbstractWindow{
    private Pane pane;
    private Scene scene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private ConectorToBd conectorToBd;
    private TableView showerTableView;
    private static LogerSituations loger = new LogerSituations(WriteRecordWindow.class);


    public ShowUsersStatistic() {
        managerGUIGame = ManagerGUIGame.getManagerGUIGame();
    }

    @Autowired
    public ShowUsersStatistic(ManagerGUIGame managerGUI) {
        this.managerGUIGame = managerGUI;
        conectorToBd = managerGUIGame.getConectorToBd();
    }

    public void initial(){
        conectorToBd = (ConectorToBd) Controller.applicationContext.getBean("conectorToBd");
    }

    public Scene createScene(Stage theStage) {
        initial();

        this.theStage = theStage;
        try {
            pane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("ShowAllRecordsFXML"));
        } catch (IOException e) {
            loger.logError(e);
        }
        initialElementsOrPanes(pane);
        return (scene = new Scene(pane, 600, 400));
    }

    @Override
    protected void initialElements(Node element) {
        if (element instanceof TableView) {
            TableView textField = (TableView) element;
            switch (textField.getId()) {
                case "users statistic":
                    showerTableView = textField;
                    break;
            }
        }
        if (element instanceof Button) {
            Button button = (Button) element;
            switch (button.getId()) {
                case "back":
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        theStage.setScene(ManagerGUIGame.getWriteRecordScene());
                    });
                    break;
                    case "refresh":
                    ibitialRefreshButton(button);
                    break;
            }
        }
    }

    private void ibitialRefreshButton(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            straingsData = FXCollections.observableArrayList(conectorToBd.getAllItemsStatistic());

            showerTableView.setItems( FXCollections.observableArrayList(conectorToBd.getAllItemsStatistic()));
            showerTableView.refresh();
        });
    }
}
