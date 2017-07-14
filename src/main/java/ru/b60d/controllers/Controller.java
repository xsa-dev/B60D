package ru.b60d.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.b60d.model.InitializerResource;
import ru.b60d.model.workers.AbstractGUIWorker;
import ru.b60d.view.ManagerGUIGame;

import java.util.Arrays;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class Controller {

    static {
        InitializerResource.writeResourceToWorkDirectory();
    }

    public static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "context.xml");

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));

        play();

    }

    private static void play() throws Exception {

        ManagerGUIGame.initial();

        System.setProperty("console.encoding", "utf-8");//пусть будет но я думаю что это не нужно

        System.out.println(ManagerGUIGame.getTextGUIExamples() + "text gui worker");


        ManagerGUIGame startWindow = ManagerGUIGame.play();
        System.out.println("play ending");
        AbstractGUIWorker examplesWorkeble = startWindow.getTextGUIExamples();// new TextGUIExamples(startWindow);
        startWindow.setTextGUIExamples(examplesWorkeble);

        int resutLaunc = examplesWorkeble.launching();

        System.out.println("\n\n\n\n" + resutLaunc);
    }


}
