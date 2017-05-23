package model;

import model.loging.LogerSituations;
import model.record_worker.ConectorToBd;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class ConsoleHelper {
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat ldateFormat = new SimpleDateFormat("mm:ss.SSS");
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final LogerSituations log = new LogerSituations( ConsoleHelper.class);



    public static void printWin(Date sdate, long edate, long ldate, int e) {
        System.out.println("=====-----FINISH-----=====");
        System.out.println("Start at: " + dateFormat.format(sdate.getTime()));
        System.out.println("End at: " + dateFormat.format(edate));
        System.out.println("Test time: " + ldateFormat.format(ldate));
        System.out.println("Err count: " + e);
        System.out.println("=====----------------=====");//
    }

    public static String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            log.logError(e);
        }
        return null;
    }

    public static void writeMessage(String string) {
        System.out.println(string);
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static void closeConection() {
        try {
            reader.close();
        } catch (IOException e) {
            log.logError(e);
        }
    }

    public static String getAbsolutePath(Class  clazz){
        String resalt = new File(".").getAbsolutePath().toString().
                replaceAll("\\.$",  "src/" + clazz.getName()).
                replaceAll("[\\\\,\\.]", "/");
        return resalt;
    }

//    public static void setReader(BufferedReader reader) {
//        ConsoleHelper.reader = reader;
//    }//ахаххах эьл для тестов с консолью импровизрованой мне просто хотелось опробовать оду фичу
}
