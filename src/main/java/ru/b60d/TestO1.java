package ru.b60d;

import ru.b60d.model.ConsoleHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

//import ru.b60d.model.workers.WorkerWithTextExamples;

/**
 * Created by Administrator1 on 17.05.2017.
 */
@Component()
public class TestO1 {

    @Override
    public String toString() {
        return "ru.b60d.TestO1{}";
    }

    public static void main9(String[] args) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream("D:/tempBackup/B60D-1-jar-with-dependencies.jar"));
        ZipEntry entry;
        String name;
        long size;
        while((entry=zis.getNextEntry())!=null){
            name = entry.getName();
            if (name.contains("ru/b60d/controllers") | name.contains("ru/b60d/view") | name.contains("model")
                    | name.contains("org") | name.contains("com") | name.contains("junit")
                    | name.contains("META-INF")  | name.contains("edu")  | name.contains("javafx")
                    | name.contains("licenses") | name.contains("javax")  | name.contains("de")) {
                continue;
            }
            if (name.endsWith("/")) {
                continue;
            }
            String tempName = ConsoleHelper.WORK_DIRECTORY + "/" + name;
            System.out.println(tempName);

            File res = null;
            (res = new File(tempName)).getParentFile().mkdirs();
            System.out.println(res);
            File resF = null;
            (resF = new File(ConsoleHelper.WORK_DIRECTORY + "/" + name)).createNewFile();
            //делать реадер антри и вритер файла ресФ
            FileOutputStream writer = new FileOutputStream(tempName);
            byte[] buffer = new byte[2048];
            int count = 0;
            while ((count = zis.read(buffer)) > -1){
                writer.write(buffer, 0, count);
            }
            writer.close();
//            System.out.println(resF + "\n");
//
//            name = entry.getName(); // получим название файла
//            size=entry.getSize();  // получим его размер в байтах
//            System.out.printf("Название: %s \t размер: %d \n", name, size);
        }
        zis.close();
    }
//
//
//    public static void main9(String[] args) throws IOException {
//        System.out.println(new File("C:\\Phd njc\\enjdmkc\\bhdjnks").getParentFile().mkdirs());
//        System.out.println(new File("C:\\Phd njc\\enjdmkc\\bhdjnks.txt").createNewFile());
//        System.out.println(new File("C:\\Phd njc\\enjdmkc\\bhdjnks").getParentFile());
//        System.out.println(new File("C:\\Phd njc\\enjdmkc\\bhdjnks.txt"));
//        InitializerResource.singleWriteResource1("dataForConectToDB.properties");
//
//        InitializerResource.singleWriteResource1("russian_examples");
//        InitializerResource.writeResourceToWorkDirectory();
//        new File("C:\\B60D Project\\B60D").delete();
//    }
//
//    public static void main8(String[] args) throws Exception {
//        StringBuilder sb = new StringBuilder();
//        try(BufferedReader reader = new BufferedReader(new FileReader("D:\\tests2\\B60D\\src\\main\\resources\\fxml\\WindowChangeLevelsFXML1.fxml"))){
//            while (reader.ready()) sb.append(reader.readLine()).append("\n");
//        }
//        int currentIndex = 0;
//        int buttonNumber = 1;
//        while (0 < (currentIndex = sb.indexOf("<Button", currentIndex + 2))){
////            if((sb.indexOf("id=\"", currentIndex) - sb.indexOf("/>", currentIndex + 2)) > 20) {
//            int temp = sb.indexOf("id=\"", currentIndex);
//                if ((temp > sb.indexOf("/>", currentIndex + 2)) | temp == -1)
//                    sb.insert(currentIndex + 7, " id=\"level " + buttonNumber + "\"");
////            }
//            currentIndex++;
//            buttonNumber++;
//        }
//        try(PrintWriter writer = new PrintWriter("D:\\tests2\\B60D\\src\\main\\resources\\fxml\\WindowChangeLevelsFXML1.fxml")){
//            writer.println(sb);
//        }
//        System.out.println(buttonNumber + "--button index");
//        System.out.println(currentIndex + "--current Index");
//    }
//
//
//    public static void main7(String[] args) throws IOException {
//
////        System.out.println(ru.b60d.TestO1.class.getClassLoader().getResourceAsStream("log4j2.xml"));
////        System.out.println(ru.b60d.TestO1.class.getClassLoader().getResource("log4j2.xml"));
////        System.out.println(ru.b60d.TestO1.class.getProtectionDomain().getCodeSource().getLocation());
//        System.out.println(ConsoleHelper.getBufferedReaderForIntoResources("log4j2.xml"));
////        System.out.println(ConsoleHelper.getBufferedWriter("log4j2.xml"));
//
//
//    }
//
//    public static void main6(String[] args) {
////        System.out.println(System.getProperty("java.class.path"));
//        System.out.println(new ru.b60d.TestO1().getClass().getResource("ru.b60d.TestO1.java"));
//    }
//    public static void main5(String[] args) throws IOException {
//        System.out.println(System.getProperty("java.class.path"));
//        System.out.println( ConsoleHelper.getParentPath( ConectorToBd.class));
//        System.out.println("D:\\tests2\\B60D\\src\\model1\\record_worker\\ConectorToBd.java".replaceAll("\\\\", "/"));
//        System.out.println(new File(".").getCanonicalPath());
//    }
//
//    public static void main4(String[] args) throws InterruptedException {
//        WorkerWithTextExamples workerWithTextExamples = new WorkerWithTextExamples();
//        Date startDate = new Date();
//        Thread.sleep(15000);
////        System.out.println(workerWithTextExamples.considersPoints(6, startDate, new Date()));
//    }
//    public static void main3(String[] args) {
//        ConectorToBd  conectorToBd = new ConectorToBd();
//        System.out.println(conectorToBd.signUp("dir3","1234"));
//        System.out.println(conectorToBd.logIn("dir3", "1234"));
//        conectorToBd.writeRecords(1024);
//        conectorToBd.closeConection();
//    }//
//
//
//    public static void main2(String[] args) throws IOException {
//        TextExamplesGenerator generator = new TextExamplesGenerator("D:\\tests2\\B60D\\src\\ExamplesTest");
//        List<TextExampl> list = generator.getReadingExamples();
//        for (TextExampl textExampl : list) {
//            System.out.println(textExampl + " \n\n");
//        }
//        for (TextExampl textExampl : list) {
//            System.out.println(textExampl.testAnswer(ConsoleHelper.readString()));
//        }
//    }
//    public static void main1(String[] args) throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://mysql.alex-savin.myjino.ru:3306/alex-savin_trutak","046470945_RutaK", "2017!RutaK"
//        );
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM users1");
//        while (resultSet.next()) {
//            System.out.println(resultSet.getInt(1));
//            System.out.println(resultSet.getInt("id") + "\n");
//        }
//        resultSet.close();
//
////        statement.executeUpdate(
////                "INSERT INTO users1(name, date) VALUES ('grgr', CURRENT_DATE);");
//
//        statement.executeUpdate("UPDATE users1 SET date = CURRENT_DATE WHERE id > 3 AND id <= 7");
//        statement.executeUpdate("DELETE FROM users1 WHERE id > 8");
//
//        resultSet = statement.executeQuery("SELECT * FROM users1");
//        while (resultSet.next()) {
//            System.out.println(resultSet.getInt(1));
//            System.out.println(resultSet.getInt("id") + "\n");
//        }
//
//        resultSet.close();
//        statement.close();
//        connection.close();
//    }
}
