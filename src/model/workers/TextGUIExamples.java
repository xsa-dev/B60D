package model.workers;

import model.ConsoleHelper;
import model.examles.TextExampl;
import model.example_generators.TextExamplesGenerator;
import view.ManagerGUIGame;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class TextGUIExamples extends AbstractGUIWorker {

    public TextGUIExamples(ManagerGUIGame managerGUIGame) {
//        setGameWindow(managerGUIGame);
//        languageMP = gameWindow.getLanguageManager();
        super(managerGUIGame);
    }

    private String getPathForSelectedLanguage() {
        String resalt = ConsoleHelper.getParentPath(TextExamplesGenerator.class);
        System.out.println(resalt);

        switch (gameWindow.getLanguageManager().getLanguageType()) {
            case RUSSIAN:
                resalt += "/Russian_Examples";
                break;
            case INGLISH:
                resalt +="/Inglish_Examples";
                break;
        }
        System.out.println(resalt);
        return resalt;
    }

    public int launching() throws Exception {
        ManagerGUIGame managerGUIGame = getGameWindow();
//        //эта срока находит этот файл на любой машине
//        String str = new File(".").getAbsolutePath().
//                replaceAll("\\.$", "src/model/example_generators/ExamplesTest").
//                replaceAll("\\\\", "/");
//        TextExamplesGenerator generator = new TextExamplesGenerator(str);
        List<TextExampl> list = TextExamplesGenerator.getReadingExamples(getPathForSelectedLanguage());

        while (!getGameWindow().isStartGame()) {
            Thread.sleep(600);
        }

//        gameWindow.appendString("To exit press EXIT\n");
        gameWindow.appendString(languageMP.getPhrase("game.start"));
        System.out.println("To exit press EXIT\n");
        int points = 0;
        Date startDate = new Date();

        for (TextExampl textExampl : list) {
            if (isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
//            managerGUIGame.appendString("\nQestion: ");
            managerGUIGame.appendString(languageMP.getPhrase("game.question"));
            System.out.println("Qestion: ");
            managerGUIGame.appendString(textExampl.getQuestion() + languageMP.getPhrase("game.your_ansver"));
//            managerGUIGame.appendString(textExampl.getQuestion() + "\n your answer:");
            System.out.println(textExampl.getQuestion() + "\n your answer:");

            String userAncwer = managerGUIGame.getString();
            managerGUIGame.appendString(userAncwer);
            if ("EXIT".equals(userAncwer) || isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            //потом фразы брать тз пропперти файлов
            managerGUIGame.appendString(textExampl.testAnswer(userAncwer) ?
                    languageMP.getPhrase("game.good_resalt") + ++points :
                    languageMP.getPhrase("game.bad_resalt") + points);
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
