package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ConsoleHelper;
import model.users.ManagerUserSetings;

import java.io.IOException;

public class WindowManagerLevels extends AbstractWindow {
    private int selectedLevel = 1;
    private ManagerGUIGame managerGUIGame;
    private Scene thisScene;
    private Stage theStage;
    private Pane thisPane;
    private ManagerUserSetings managerUserSetings;

    public WindowManagerLevels(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
        managerUserSetings = managerGUIGame.getManagerUserSetings();
        selectedLevel = Integer.parseInt(managerUserSetings.getSeting("setings.level"));
    }

    public Scene createScene(Stage theStage) {
        this.theStage = theStage;
        try {
            thisPane = FXMLLoader.load(ConsoleHelper.getParentPathFileFXML1("WindowChangeLevelsFXML"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initialElementsOrPanes(thisPane);

        thisScene = new Scene(thisPane, 600, 400);
        System.out.println(thisPane +""+ thisScene);

        return thisScene;
    }

    @Override
    protected void initialElements(Node element) {
        if (element instanceof Button)
            initialButtons(element);
    }

    private void initialButtons(Node element) {
        Button button = (Button) element;
        String buttonId = button.getId();
        if (buttonId.contains("level")) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                    {
                        selectedLevel = Integer.parseInt(buttonId.substring(6));
                        managerUserSetings.saveSeting("setings.level", selectedLevel + "");
                        System.out.println(selectedLevel);
                    }
            );
        } else if ("BACKButton".equals(buttonId)) {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->
                    theStage.setScene(managerGUIGame.getStartScene()));
        }
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }
}
