package model;

import model.loging.LogerSituations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class ConsoleHelper {
    private final static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static {
        ProcesesCloser.putProcess(reader);
    }

    private static final LogerSituations log = new LogerSituations(ConsoleHelper.class);
    public static final String WORK_DIRECTORY = "C:\\B60D Project\\B60D";

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

    public static String getParentPath(Class clazz) {
//        String resalt = new File(".").getAbsolutePath().
//                replaceAll("\\.$",  "src/main/java/" + clazz.getName()).
//                replaceAll("[\\\\,\\.]", "/").
//                replaceAll("/" + clazz.getSimpleName() + "$", "");
//
//        return resalt;
        return WORK_DIRECTORY + "/";
    }

    public static String getFile(String name) {
//        return ConsoleHelper.class.getClassLoader().getResource(name).toString().
//                replaceAll("^file:/", "");
        return WORK_DIRECTORY + "/" + name;
    }


    public static String getFileForWritingInWorkerHolder(String name) {
        String resalt = ConsoleHelper.class.getClassLoader().getResource(name).toString().
                replaceAll("^file:/", "");
        if (resalt == null) {
            resalt = new File(ConsoleHelper.class.getClassLoader().getResource("fileTest.txt").getFile()).getAbsolutePath();
        }

        return resalt;

    }

    public static BufferedReader getBufferedReaderForIntoResources(String path) {
        System.out.println("/" + path.replaceAll("\\..+$", ""));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(
                ConsoleHelper.class.getResourceAsStream("/" + path)));//.replaceAll("\\..+$", ""))));
        ProcesesCloser.putProcess(buffer);
        return buffer;
    }

    public static String getParentPathClass(Class clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().toString().replaceAll("^file:/", "");
    }

    public static URL getParentPathFileFXML(String name) {
        System.out.println(ConsoleHelper.class.getClassLoader().getResource("fxml/" + name + ".fxml"));
        URL resalt = ConsoleHelper.class.getClassLoader().getResource("fxml/" + name + ".fxml");

        return resalt;
    }

    public static URL getParentPathFileFXML1(String name) {
        URL resalt = null;
        try {
            resalt = new URL("file:/" + WORK_DIRECTORY + "/fxml/" + name + ".fxml");
            System.out.println(resalt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return resalt;
    }
}
