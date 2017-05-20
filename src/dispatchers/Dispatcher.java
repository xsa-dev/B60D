package dispatchers;

import model.ConsoleHelper;
import model.record_worker.ConectorToBd;
import model.workers.ExamplesWorkeble;
import model.workers.IntExamplesWorker;
import model.workers.WorkerWithTextExamples;

/**
 * Created by Administrator1 on 18.05.2017.
 */
public class Dispatcher {
    public static void main(String[] args) throws Exception {
        ExamplesWorkeble examplesWorkeble = null;

        ConsoleHelper.writeMessage("integer random examples push 1 \n text examples push 2");
        switch (ConsoleHelper.readInt()){
            case 1: examplesWorkeble = new IntExamplesWorker(); break;
            case 2: examplesWorkeble = new WorkerWithTextExamples(); break;
        }

        int resutLaunc = examplesWorkeble.launching();

        ConsoleHelper.writeMessage("write result press yes");
        if ("yes".equals(ConsoleHelper.readString())) {
            ConectorToBd conectorWriteRecord = new ConectorToBd();
            ConsoleHelper.writeMessage("signIn or  signUp press 1");
            if ((ConsoleHelper.readInt()) == 1) {
                conectorWriteRecord.entranceManager();
                conectorWriteRecord.writeRecords(resutLaunc);
                ConsoleHelper.writeMessage("records suchesfull writing");
            }
            conectorWriteRecord.closeConection();
        }
    }
}
