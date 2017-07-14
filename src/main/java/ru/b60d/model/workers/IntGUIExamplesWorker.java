package ru.b60d.model.workers;

import ru.b60d.model.example_generators.IntExamlesGenerator;
import ru.b60d.view.ManagerGUIGame;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator1 on 26.05.2017.
 */
public class IntGUIExamplesWorker extends AbstractGUIWorker{
    private static List<String> list = IntExamlesGenerator.genList(16);

    public IntGUIExamplesWorker(ManagerGUIGame managerGUIGame) {
        super(managerGUIGame);
    }

    public int launching() throws Exception {
        setResalt(runGame(list));
        return getResalt();
    }

    private int runGame(List<String> list) throws IOException {
        //Статистика
        ManagerGUIGame managerGUIGame = getGameWindow();
        int answ = -1, x, y, z, e = 0;
        char[] vals;
        char o;

//        managerGUIGame.appendString("Time Start!");
        gameWindow.appendString(languageMP.getPhrase("game.start"));

        Date startDate = new Date();
        int points = 0;
        //для каждого значения в списке выполнить метода anw
//        managerGUIGame.appendString("To exit press EXIT\n");
        for (int i = 0; i < list.size(); i++) {
//            managerGUIGame.clearOutTextArea();

            vals = (list.get(i).toCharArray());
            x = Character.getNumericValue(vals[0]);
            y = Character.getNumericValue(vals[2]);

            o = vals[1];
            z = x * y - x - y;

            switch (o) {
                case '+':
                    z = x + y;
                    break;
                case '*':
                    z = x * y;
                    break;
                case '/':
                    z = x / y;
                    break;
                case '-':
                    z = x - y;
                    break;
            }

            managerGUIGame.appendString(list.get(i));
            String rSuserAnswer = managerGUIGame.getStringNumber();//getLogin();
            if ("EXIT".equals(rSuserAnswer)) {
                return endResalt(points, startDate, new Date());
            }
            answ = Integer.parseInt( rSuserAnswer);
            if (answ == z) {
                managerGUIGame.appendString(languageMP.getPhrase("game.your_ansver") + answ);
                managerGUIGame.appendString(languageMP.getPhrase("game.good_resalt") + points++);
            }
            if (answ != z) {
                managerGUIGame.appendString(languageMP.getPhrase("game.your_ansver") + answ);
                managerGUIGame.appendString(languageMP.getPhrase("game.bad_resalt") + points);
                e++;
                --i;

            }
        }
        if (answ == -1) {
            managerGUIGame.appendString("e");
        }

        Date endDate = new Date();
        return endResalt(points, startDate, endDate);
    }

    private void ifExit(String waiter) throws Exception {
        ManagerGUIGame managerGUIGame = getGameWindow();
        managerGUIGame.appendString("What you want?");
        waiter = managerGUIGame.getString();
        if (waiter.equals("generate")) {
            IntExamlesGenerator.saveList();
        }
        if (waiter.equals("read")) {
            list = IntExamlesGenerator.readList();

            for (String str : list) {

                managerGUIGame.appendString(str);
            }
        }
        if (waiter.equals("stat")) {
            managerGUIGame.appendString("What you want do?[last, best, top]");
            if (waiter.equals("read")) {
                managerGUIGame.appendString("This is stat");
            }
        }
    }
}
