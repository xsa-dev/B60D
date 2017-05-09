import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by xsd on 27.04.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */

public class main implements Serializable {
    //Список заданий Map с ключом и строкой
    //Вывод на экран задания
    //Проверка решения
    //Таймер
    //Сохранение статистики

    public static ArrayList setList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("1+1=?");
        list.add("1*2=?");
        list.add("3/1=?");
        list.add("4-1=?");

        return list;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Timer
        Date sdate;  //start timer val
        long edate;  //end timer val
        long ldate;  //end and start diff
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat ldateFormat = new SimpleDateFormat("mm:ss.SSS");

        //Выводим список вопросы по очереди
        ArrayList<String> list = setList();

        int answ = -1, x, y, z;
        char[] vals;
        char o;

        //Are you ready?
        System.out.println("Are you ready? (y/n)");
        String waiter = reader.readLine();

        if (waiter.equals("y") || waiter.equals("yes")) {

            System.out.println("Time Start!");

            sdate = new Date();
            //для каждого значения в списке выполнить метода anw
            for (int i = 0; i < list.size(); i++) {

                vals = (list.get(i).toCharArray());
                x = Character.getNumericValue(vals[0]);
                y = Character.getNumericValue(vals[2]);

                o = vals[1];
                z = x * y - x - y;

                if (o == '+') {
                    z = x + y;
                }
                if (o == '*') {
                    z = x * y;
                }
                if (o == '/') {
                    z = x / y;
                }
                if (o == '-') {
                    z = x - y;
                }
                System.out.println(list.get(i));
                answ = Integer.parseInt(reader.readLine());
                if (answ == z) {
                    System.out.println("ok, answer is: " + z + " next...");
                }
                if (answ != z) {
                    System.out.println("bad... one more time..");
                    i = i - 1;
                }
            }

            if (answ == -1) {
                System.out.println("e");
            }
            edate = new Date().getTime();
            ldate = edate - sdate.getTime();

            System.out.println("=====-----FINISH-----=====");
            System.out.println("Start at: " + dateFormat.format(sdate.getTime()));
            System.out.println("End at: " + dateFormat.format(edate));
            System.out.println("Test time: " + ldateFormat.format(ldate));
            System.out.println("=====----------------=====");
        }

        if (waiter.equals("n") || waiter.equals("no")) {
            System.out.println("The end");
        }

    }
}

