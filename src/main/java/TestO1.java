import model.ConsoleHelper;
import model.examles.TextExampl;
import model.example_generators.TextExamplesGenerator;
import model.record_worker.ConectorToBd;
import model.workers.WorkerWithTextExamples;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class TestO1 {

    public static void main8(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader("D:\\tests2\\B60D\\src\\main\\resources\\fxml\\WindowChangeLevelsFXML1.fxml"))){
            while (reader.ready()) sb.append(reader.readLine()).append("\n");
        }
        int currentIndex = 0;
        int buttonNumber = 1;
        while (0 < (currentIndex = sb.indexOf("<Button", currentIndex + 2))){
//            if((sb.indexOf("id=\"", currentIndex) - sb.indexOf("/>", currentIndex + 2)) > 20) {
            int temp = sb.indexOf("id=\"", currentIndex);
                if ((temp > sb.indexOf("/>", currentIndex + 2)) | temp == -1)
                    sb.insert(currentIndex + 7, " id=\"level " + buttonNumber + "\"");
//            }
            currentIndex++;
            buttonNumber++;
        }
        try(PrintWriter writer = new PrintWriter("D:\\tests2\\B60D\\src\\main\\resources\\fxml\\WindowChangeLevelsFXML1.fxml")){
            writer.println(sb);
        }
        System.out.println(buttonNumber + "--button index");
        System.out.println(currentIndex + "--current Index");
    }


    public static void main7(String[] args) throws IOException {

//        System.out.println(TestO1.class.getClassLoader().getResourceAsStream("log4j2.xml"));
//        System.out.println(TestO1.class.getClassLoader().getResource("log4j2.xml"));
//        System.out.println(TestO1.class.getProtectionDomain().getCodeSource().getLocation());
        System.out.println(ConsoleHelper.getBufferedReader("log4j2.xml"));
        System.out.println(ConsoleHelper.getBufferedWriter("log4j2.xml"));


    }

    public static void main6(String[] args) {
//        System.out.println(System.getProperty("java.class.path"));
        System.out.println(new TestO1().getClass().getResource("TestO1.java"));
    }
    public static void main5(String[] args) throws IOException {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println( ConsoleHelper.getParentPath( ConectorToBd.class));
        System.out.println("D:\\tests2\\B60D\\src\\model1\\record_worker\\ConectorToBd.java".replaceAll("\\\\", "/"));
        System.out.println(new File(".").getCanonicalPath());
    }

    public static void main4(String[] args) throws InterruptedException {
        WorkerWithTextExamples workerWithTextExamples = new WorkerWithTextExamples();
        Date startDate = new Date();
        Thread.sleep(15000);
//        System.out.println(workerWithTextExamples.considersPoints(6, startDate, new Date()));
    }
    public static void main3(String[] args) {
        ConectorToBd  conectorToBd = new ConectorToBd();
        System.out.println(conectorToBd.signUp("dir3","1234"));
        System.out.println(conectorToBd.logIn("dir3", "1234"));
        conectorToBd.writeRecords(1024);
        conectorToBd.closeConection();
    }//


    public static void main2(String[] args) throws IOException {
        TextExamplesGenerator generator = new TextExamplesGenerator("D:\\tests2\\B60D\\src\\ExamplesTest");
        List<TextExampl> list = generator.getReadingExamples();
        for (TextExampl textExampl : list) {
            System.out.println(textExampl + " \n\n");
        }
        for (TextExampl textExampl : list) {
            System.out.println(textExampl.testAnswer(ConsoleHelper.readString()));
        }
    }
    public static void main1(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://mysql.alex-savin.myjino.ru:3306/alex-savin_trutak","046470945_RutaK", "2017!RutaK"
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users1");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getInt("id") + "\n");
        }
        resultSet.close();

//        statement.executeUpdate(
//                "INSERT INTO users1(name, date) VALUES ('grgr', CURRENT_DATE);");

        statement.executeUpdate("UPDATE users1 SET date = CURRENT_DATE WHERE id > 3 AND id <= 7");
        statement.executeUpdate("DELETE FROM users1 WHERE id > 8");

        resultSet = statement.executeQuery("SELECT * FROM users1");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getInt("id") + "\n");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
