package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ConsoleHelper;
import model.loging.LogerSituations;
import model.record_worker.ConectorToBd;
import model.record_worker.DBItem;

import java.io.IOException;

public class ShowUsersStatistic extends AbstractWindow{
    private Pane pane;
    private Label label;
    private Scene scene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private ConectorToBd conectorToBd;
    private TableView showerTableView;
    private static LogerSituations loger = new LogerSituations(WriteRecordWindow.class);
    private ObservableList<DBItem> straingsData;//= FXCollections.observableArrayList();

    public ShowUsersStatistic(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
        conectorToBd = managerGUIGame.getConectorToBd();
    }

    public Scene createScene(Stage theStage) {

        this.theStage = theStage;
        try {
            pane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("ShowAllRecordsFXML"));
        } catch (IOException e) {
            loger.logError(e);
        }
        initialElementsOrPanes(pane);
//        scene = new Scene(writerPane, 600, 400);
        return (scene = new Scene(pane, 600, 400));
    }

    @Override
    protected void initialElements(Node element) {
        if (element instanceof TableView) {
            TableView textField = (TableView) element;
            switch (textField.getId()) {
                case "users statistic":
                    showerTableView = textField;//todo initialize this
                    break;
            }
        }
        if (element instanceof Button) {
            Button button = (Button) element;
            switch (button.getId()) {
                case "back":
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        theStage.setScene(managerGUIGame.getWriteRecordScene());//getWriteRecordScene());
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
            straingsData = FXCollections.observableArrayList(conectorToBd.getAllItemsStatistic());

            showerTableView.setItems(straingsData);
            showerTableView.refresh();
            for (DBItem straingsDatum : straingsData) {
                System.out.println(straingsDatum);//тут заменить вывод в тфбое вью
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
