package ru.b60d.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Administrator1 on 28.05.2017.
 */
public abstract class AbstractWindow {
    protected void initialElementsOrPanes(Node node) {
        if (node instanceof Pane) {
            for (Node nod : ((Pane) node).getChildren()) {
                initialElementsOrPanes(nod);
            }
        } else if (node instanceof ButtonBar) {
            for (Node nod : ((ButtonBar) node).getButtons()) {
                initialElementsOrPanes(nod);
            }
        } else {
            initialElements(node);
        }
    }


    protected abstract void initialElements(Node element);

    protected abstract Scene createScene(Stage theStage);

    protected abstract void initial();
}