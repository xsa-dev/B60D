package view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class StartWindow {
    private Pane startPane;
    private Scene startScene;
    private Stage theStage;
    private TestFXMLFiles testFXMLFiles;

    public StartWindow(TestFXMLFiles testFXMLFiles) {
        this.testFXMLFiles = testFXMLFiles;
    }


    public Scene createStartWindowScene(Stage theStage) {
        this.theStage = theStage;
        try {
            startPane = FXMLLoader.load(StartWindow.class.getResource("WindowStartFXML.fxml"));
        } catch (IOException e) {
            System.out.println("jncokmserjnvicnefknvejfnv");
            e.printStackTrace();
        }
        initialElements();


        startScene = new Scene(startPane, 400, 400);
        return startScene;
    }

    private void initialElements(){
        for (Node node : startPane.getChildren()) {
            initialStartButtons(node);
        }
    }

    private void initialStartButtons(Node element) {
        if (!(element instanceof Button)) return;
        Button button = (Button) element;

        switch (button.getId()) {
            case "0":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        theStage.setScene(testFXMLFiles.getGameScene());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        testFXMLFiles.setStartGame(true);
                    }
                });
                break;
            case "1":
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
//                        thestage.setScene(scene2);
                        System.out.println("excpecteed");
                    }
                });
                break;
        }
    }
}
