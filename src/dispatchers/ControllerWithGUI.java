package dispatchers;

import model.workers.TextGUIExamples;
import view.ManagerGUIGame;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class ControllerWithGUI {
    public static void main(String[] args) throws Exception {

        ManagerGUIGame startWindow = ManagerGUIGame.play();
        TextGUIExamples examplesWorkeble = new TextGUIExamples(startWindow);
        startWindow.setTextGUIExamples(examplesWorkeble);

        int resutLaunc = examplesWorkeble.launching();

        startWindow.appendString("\n-------YOUR RESALT: "  + resutLaunc + "-----------\n");
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
