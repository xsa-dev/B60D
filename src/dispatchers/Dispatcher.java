package dispatchers;

import model.ConsoleHelper;
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

        examplesWorkeble.launching();
    }
}
