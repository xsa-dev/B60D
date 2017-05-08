import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by xsd on 27.04.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */

public class main {
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

        //Выводим список вопросы по очереди
        ArrayList<String> list = setList();

        int answ = -1, x, y, z;
        char[] vals;
        char o;

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
    }
}

