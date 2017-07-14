package view;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ConsoleHelper;
import model.loging.LogerSituations;
import model.record_worker.ConectorToBd;

import java.io.IOException;

public class ShowUsersStatistic extends AbstractWindow{
    private Pane pane;
    private Scene scene;
    private Stage theStage;
    private ManagerGUIGame managerGUIGame;
    private ConectorToBd conectorToBd;
    private TableView showerTableView;
    private static LogerSituations loger = new LogerSituations(WriteRecordWindow.class);
//    private ObservableList<DBItem> straingsData;

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
                        theStage.setScene(managerGUIGame.getWriteRecordScene());
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
