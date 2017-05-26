package model.workers;

import model.examles.TextExampl;
import model.example_generators.TextExamplesGenerator;
import view.ManagerGUIGame;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class TextGUIExamples extends AbstractGUIWorker {
//    private ManagerGUIGame gameWindow;
//    private boolean wriwRecord = false;
//    private int resalt;

    public TextGUIExamples(ManagerGUIGame view) {
        setGameWindow(view);
    }

    public int launching() throws Exception {
        ManagerGUIGame managerGUIGame = getGameWindow();
        //эта срока находит этот файл на любой машине
        String str = new File(".").getAbsolutePath().toString().
                replaceAll("\\.$", "src/ExamplesTest").
                replaceAll("\\\\", "/");
        TextExamplesGenerator generator = new TextExamplesGenerator(str);
        List<TextExampl> list = generator.getReadingExamples();

        while (!getGameWindow().isStartGame()){
            Thread.sleep(600);
        }

        getGameWindow().appendString("To exit press EXIT\n");
        System.out.println("To exit press EXIT\n");
        int points = 0;
        Date startDate = new Date();

        for (TextExampl textExampl : list) {
            if (isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            managerGUIGame.appendString("\nQestion: ");
            System.out.println("Qestion: ");
            managerGUIGame.appendString(textExampl.getQuestion() + "\n your answer:");
            System.out.println(textExampl.getQuestion() + "\n your answer:");

            String userAncwer = managerGUIGame.getString();
            managerGUIGame.appendString(userAncwer);
            if ("EXIT".equals(userAncwer) || isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            //потом фразы брать тз пропперти файлов
            managerGUIGame.appendString(textExampl.testAnswer(userAncwer) ?
                    "ok, resalt " + ++points :
                    "bad, resalt " + points);
            System.out.println(textExampl.testAnswer(userAncwer) ?
                    "ok, resalt " + ++points :
                    "bad, resalt " + points);

        }

        return endResalt(points, startDate, new Date());
    }
//
//    private int endResalt(int points, Date startDate, Date endDate ){
//        resalt = considersPoints(points, startDate, endDate);
//        return resalt;
//    }

//    public void setWriwRecord(boolean wriwRecord) {
//        this.wriwRecord = wriwRecord;
//    }
}
