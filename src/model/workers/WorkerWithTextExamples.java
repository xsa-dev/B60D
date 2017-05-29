package model.workers;

import model.ConsoleHelper;
import model.examles.TextExampl;
import model.example_generators.TextExamplesGenerator;
import view.StartWindow;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator1 on 18.05.2017.
 */
public class WorkerWithTextExamples extends AbstractExampleWorker {
    public int launching() throws Exception {
        //эта срока находит этот файл на любой машине
        String str = new File(".").getAbsolutePath().
                replaceAll("\\.$", "src/ExamplesTest").
                replaceAll("\\\\", "/");
        TextExamplesGenerator generator = new TextExamplesGenerator(str);
        List<TextExampl> list = generator.getReadingExamples();

        ConsoleHelper.writeMessage("To exit press EXIT\n");
        int points = 0;
        Date startDate = new Date();

        for (TextExampl textExampl : list) {
            ConsoleHelper.writeMessage("Qestion: ");
            ConsoleHelper.writeMessage(textExampl.getQuestion() + "\n your answer:");

            String userAncwer = ConsoleHelper.readString();
            if ("EXIT".equals(userAncwer)) {
                return considersPoints(points, startDate, new Date());
            }
            //потом фразы брать тз пропперти файлов
            ConsoleHelper.writeMessage(textExampl.testAnswer( userAncwer) ?
                    "ok, resalt " + ++points :
                    "bad, resalt " + points );
        }

        Date endDate = new Date();
        return considersPoints(points, startDate, endDate);
    }

    public int launching(StartWindow startWindow) throws Exception {
//        //эта срока находит этот файл на любой машине
//        String str = new File(".").getParentPath().toString().
//                replaceAll("\\.$", "src/ExamplesTest").
//                replaceAll("\\\\", "/");
//        TextExamplesGenerator generator = new TextExamplesGenerator(str);
//        List<TextExampl> list = generator.getReadingExamples();
//
//        startWindow.appendString("To exit press EXIT\n");
//        int points = 0;
//        Date startDate = new Date();
//
//        for (TextExampl textExampl : list) {
//            startWindow.appendString("Qestion: ");
//            startWindow.appendString(textExampl.getQuestion() + "\n your answer:");
//
//            String userAncwer = startWindow.getString();
//            if ("EXIT".equals(userAncwer)) {
//                return considersPoints(points, startDate, new Date());
//            }
//            //потом фразы брать тз пропперти файлов
//            startWindow.appendString(textExampl.testAnswer( userAncwer) ?
//                    "ok, resalt " + ++points :
//                    "bad, resalt " + points );
//        }
//
//        Date endDate = new Date();
//        return considersPoints(points, startDate, endDate);
        return 0;
    }
}