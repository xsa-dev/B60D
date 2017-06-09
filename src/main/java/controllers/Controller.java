package controllers;

import model.InitializerResource;
import model.workers.AbstractGUIWorker;
import view.ManagerGUIGame;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class Controller {
//    static{
//        LoadClases.startLoadClases();
//    }
    public static void main(String[] args) throws Exception {
        System.setProperty("console.encoding","utf-8");//пусть будет но я думаю что это не нужно

        InitializerResource.writeResourceToWorkDirectory();

        ManagerGUIGame startWindow = ManagerGUIGame.play();
        AbstractGUIWorker examplesWorkeble = startWindow.getTextGUIExamples();// new TextGUIExamples(startWindow);
        startWindow.setTextGUIExamples(examplesWorkeble);

        int resutLaunc = examplesWorkeble.launching();

//        startWindow.appendString("\n-------YOUR RESALT: "  + resutLaunc + "-----------\n");
//        startWindow.appendString(startWindow.getLanguageManager().getPhrase( "game.end") + resutLaunc);
        System.out.println("\n\n\n\n" + resutLaunc);

////        ConsoleHelper.appendString("write result to DB press yes");
//        if (startWindow.isWriteRecord()) {
//            ConectorToBd conectorWriteRecord = new ConectorToBd();
//        startWindow.setConectorToBd(conectorWriteRecord);
//        startWindow.showWriteRecordWindow();
//
//        conectorWriteRecord.entranceManager(startWindow);
//            conectorWriteRecord.writeRecords(resutLaunc);
//            ConsoleHelper.writeMessage("records suchesfull writing");
//            conectorWriteRecord.closeConection();
//        }
    }
}
