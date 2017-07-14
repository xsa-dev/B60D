package controllers;

import model.InitializerResource;
import model.workers.AbstractGUIWorker;
import view.ManagerGUIGame;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class Controller {

    public static void main(String[] args) throws Exception {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("");
        System.setProperty("console.encoding", "utf-8");//пусть будет но я думаю что это не нужно

        InitializerResource.writeResourceToWorkDirectory();

        ManagerGUIGame startWindow = ManagerGUIGame.play();
        AbstractGUIWorker examplesWorkeble = startWindow.getTextGUIExamples();// new TextGUIExamples(startWindow);
        startWindow.setTextGUIExamples(examplesWorkeble);

        int resutLaunc = examplesWorkeble.launching();

        System.out.println("\n\n\n\n" + resutLaunc);

    }
}
