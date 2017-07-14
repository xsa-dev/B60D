package ru.b60d.model.workers;

import org.springframework.stereotype.Component;
import ru.b60d.model.ConsoleHelper;
import ru.b60d.model.examles.TextExampl;
import ru.b60d.model.example_generators.TextExamplesGenerator;
import ru.b60d.view.ManagerGUIGame;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

@Component
public class TextGUIExamples extends AbstractGUIWorker {

    public TextGUIExamples() {
        super(ManagerGUIGame.getManagerGUIGame());
    }

    public TextGUIExamples(ManagerGUIGame managerGUIGame) {
        super(managerGUIGame);
    }

    private String getPathForSelectedLanguage() {

        String resalt = "";
        switch (gameWindow.getLanguageManager().getLanguageType()) {
            case RUSSIAN:
                resalt = ConsoleHelper.getFile("russian_examples/Russian_Examples" + ManagerGUIGame.getWindowManagerLevels().getSelectedLevel());
                break;
            case INGLISH:
                resalt = ConsoleHelper.getFile("inglish_examples/Inglish_Examples" + ManagerGUIGame.getWindowManagerLevels().getSelectedLevel());
                break;
        }
        System.out.println(resalt);
        return resalt;
    }

    public int launching() throws Exception {
        ManagerGUIGame managerGUIGame = ManagerGUIGame.getManagerGUIGame();

        List<TextExampl> list = TextExamplesGenerator.getReadingExamples(getPathForSelectedLanguage());
        System.out.println(Charset.defaultCharset());

        while (!gameWindow.isStartGame()) {
            Thread.sleep(600);
        }

        System.out.println("GET PHRASE&^^&*:::::::::::: " + languageMP.getPhrase("game.start"));
        ManagerGUIGame.appendString(languageMP.getPhrase("game.start"));
        int points = 0;
        Date startDate = new Date();

        for (TextExampl textExampl : list) {
            if (isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            ManagerGUIGame.appendString(languageMP.getPhrase("game.question"));
            ManagerGUIGame.appendString(textExampl.getQuestion() + languageMP.getPhrase("game.your_ansver"));

            String userAncwer = managerGUIGame.getString();
            ManagerGUIGame.appendString(userAncwer);
            if ("EXIT".equals(userAncwer) || isWriwRecord()) {
                return endResalt(points, startDate, new Date());
            }
            ManagerGUIGame.appendString(textExampl.testAnswer(userAncwer) ?
                    languageMP.getPhrase("game.good_resalt") + ++points :
                    languageMP.getPhrase("game.bad_resalt") + points);

        }
        return endResalt(points, startDate, new Date());
    }
}
//        //эта срока находит этот файл на любой машине
//        String str = new File(".").getAbsolutePath().
//                replaceAll("\\.$", "src/ru.b60d.model/example_generators/ExamplesTest").
//                replaceAll("\\\\", "/");
//        TextExamplesGenerator generator = new TextExamplesGenerator(str);