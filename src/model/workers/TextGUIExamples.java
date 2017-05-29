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
        super(managerGUIGame);
    }

    private String getPathForSelectedLanguage() {
        String resalt = ConsoleHelper.getParentPath(TextExamplesGenerator.class);

        switch (gameWindow.getLanguageManager().getLanguageType()) {
            case RUSSIAN:
                resalt += "/Russian_Examples" + gameWindow.getWindowManagerLevels().getSelectedLevel();
                break;
            case INGLISH:
                resalt += "/Inglish_Examples" + gameWindow.getWindowManagerLevels().getSelectedLevel();
                break;
        }
        System.out.println(resalt);
        return resalt;
    }

    public int launching() throws Exception {
        ManagerGUIGame managerGUIGame = getGameWindow();

        List<TextExampl> list = TextExamplesGenerator.getReadingExamples(getPathForSelectedLanguage());

        while (!getGameWindow().isStartGame()) {
            Thread.sleep(600);
        }

        gameWindow.appendString(languageMP.getPhrase("game.start"));
        int points = 0;
        Date startDate = new Date();

        for (TextExampl textExampl : list) {
            if (isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            managerGUIGame.appendString(languageMP.getPhrase("game.question"));
            managerGUIGame.appendString(textExampl.getQuestion() + languageMP.getPhrase("game.your_ansver"));

            String userAncwer = managerGUIGame.getString();
            managerGUIGame.appendString(userAncwer);
            if ("EXIT".equals(userAncwer) || isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            managerGUIGame.appendString(textExampl.testAnswer(userAncwer) ?
                    languageMP.getPhrase("game.good_resalt") + ++points :
                    languageMP.getPhrase("game.bad_resalt") + points);

        }
        return endResalt(points, startDate, new Date());
    }
}
//        //эта срока находит этот файл на любой машине
//        String str = new File(".").getAbsolutePath().
//                replaceAll("\\.$", "src/model/example_generators/ExamplesTest").
//                replaceAll("\\\\", "/");
//        TextExamplesGenerator generator = new TextExamplesGenerator(str);