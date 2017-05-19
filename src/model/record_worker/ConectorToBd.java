package model.record_worker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator1 on 19.05.2017.
 */
public class ConectorToBd {
    private String login;
    private String password;
    Properties dataForConectToDB;

    public ConectorToBd() {
        try {
            String pathToDataDB = new File(".").getAbsolutePath().toString().
                    replaceAll("\\.$", "src/model/record_worker/dataForConectToDB").
                    replaceAll("\\\\", "/");

            dataForConectToDB = new Properties();
            dataForConectToDB.load(new FileReader(pathToDataDB));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean logIn(String login, String password) {
        try {
            Connection connection = createConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID, PASSWORD FROM users1");
            while (resultSet.next()) {
                if (
                        resultSet.getString("id").equals(login) &
                                password.equals(resultSet.getString("password"))
                        ) {
                    return true;
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }


    public boolean signUP(int login1, String password1) {
        try {
            Connection connection = createConection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ID FROM users1");
            while (resultSet.next()) {
                if ( resultSet.getInt("id") == login1) {
                    return false;
                }
            }

        statement.execute(
                "INSERT INTO users1( id, password) VALUES ( " + login1 + ", " + password1 + ");");
//            statement.executeUpdate(
//                "Update users1 SET id = this.login;");

        statement.close();
        connection.close();
        } catch (SQLException e) {e.printStackTrace();}


        return true;
    }

    private Connection createConection(){
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://mysql.alex-savin.myjino.ru:3306/alex-savin_trutak",
                    dataForConectToDB.getProperty("login"),
                    dataForConectToDB.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getLogin() {
        return login;
    }
}
