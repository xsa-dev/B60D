package model.workers;

import model.ConsoleHelper;
import model.example_generators.IntExamlesGenerator;
import view.ManagerGUIGame;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator1 on 26.05.2017.
 */
public class IntGUIExamplesWorker extends AbstractGUIWorker{
    private static List<String> list = IntExamlesGenerator.genList(16);

    public IntGUIExamplesWorker(ManagerGUIGame view) {
            setGameWindow(view);
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

        managerGUIGame.appendString("Time Start!");

        Date startDate = new Date();
        int points = 0;
        //для каждого значения в списке выполнить метода anw
        managerGUIGame.appendString("To exit press EXIT\n");
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
            String rSuserAnswer = managerGUIGame.getString();
            if ("EXIT".equals(rSuserAnswer)) {
                return considersPoints(points, startDate, new Date());
            }
            answ = Integer.parseInt( rSuserAnswer);
            if (answ == z) {
                managerGUIGame.appendString("ok, answer is: " + z + " next...");
                points++;
            }
            if (answ != z) {
                managerGUIGame.appendString("bad... one more time..");
                e++;
                i = i - 1;
            }
        }
        if (answ == -1) {
            managerGUIGame.appendString("e");
        }

        Date endDate = new Date();
        ConsoleHelper.printWin(startDate, endDate.getTime(), endDate.getTime() - startDate.getTime(), e);
        return considersPoints(points, startDate, endDate);
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
