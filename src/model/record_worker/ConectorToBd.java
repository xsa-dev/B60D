package model.record_worker;

import model.ConsoleHelper;
import model.loging.LogerSituations;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator1 on 19.05.2017.
 */
public class ConectorToBd {
    private static final LogerSituations log = new LogerSituations( ConectorToBd.class);
    //    static {
////        org.apache.log4j.PropertyConfigurator.configure("D:\\tests2\\B60D\\src\\log4j.properties\\log4j.properties");
////        BasicConfigurator.configure(new NullAppender());
//    }
    private String login;
    private String password;
    private Properties dataForConectToDB;
    private Connection connection;
    private boolean conecting = false;

    public static void main(String[] args) {
        log.logError( new IllegalAccessException());
    }

    public ConectorToBd() {
        try {
            String pathToDataDB = new File(".").getAbsolutePath().toString().
                    replaceAll("\\.$", "src/model/record_worker/dataForConectToDB").
                    replaceAll("\\\\", "/");

            dataForConectToDB = new Properties();
            dataForConectToDB.load(new FileReader(pathToDataDB));
        } catch (IOException e) {
            log.logError(e);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql.alex-savin.myjino.ru:3306/alex-savin_trutak",
                    dataForConectToDB.getProperty("login"),
                    dataForConectToDB.getProperty("password")
            );

        } catch (ClassNotFoundException | SQLException e) {
            log.logError(e);
        }
    }

    public boolean logIn(String login, String password) {
        this.login = login;
        this.password = password;
        try {
            Statement statement = createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LOGIN, PASSWORD FROM users1");
            while (resultSet.next()) {
                if (
                        resultSet.getString("login").equals(login) &
                                password.equals(resultSet.getString("password"))
                        ) {//
                    conecting = true;
                    return true;
                }
            }
            statement.close();
        } catch (SQLException e) {
            log.logError(e);
        }
        return false;
    }


    public boolean signUp(String login1, String password1) {
        this.login = login1;
        this.password = password1;
        try {
            Statement statement = createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LOGIN FROM users1");
            while (resultSet.next()) {
                if (login1.equals(resultSet.getString("login"))) {
                    return false;
                }
            }
            login1 = "\'" + login1 + "\'";

            statement.executeUpdate(
                    "INSERT INTO users1 ( login, password) VALUES ( " + login1 + ", " + password1 + ");");

            statement.close();
        } catch (SQLException e) {
            log.logError(e);
        }
        conecting = true;
        return true;
    }

    public void writeRecords(int points) {
        Statement statement = null;
        try {
            statement = createStatement();
            statement.executeUpdate("UPDATE USERS1 SET POINTS = " + points + " WHERE LOGIN = \'" + login + "\'");
        } catch (SQLException e) {
            log.logError(e);
        }

    }

    public void closeConection() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.logError(e);
        }
    }

    public void entranceManager() {
        while (!conecting) {
            ConsoleHelper.writeMessage("your conect :");
            ConsoleHelper.writeMessage("write login");
            String login = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("write password");
            String password = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("signIn(1) or  signUp(2)");
            switch (ConsoleHelper.readInt()) {
                case 1:
                    logIn(login, password);//conecting =
                    break;
                case 2:
                    signUp(login, password);//conecting =
                    break;
            }
            if (!conecting) {
                ConsoleHelper.writeMessage("try again ?? yes or EXIT");
                if ("exit".equals(ConsoleHelper.readString())) {
                    return;
                }
            }
        }

    }

    public String readRecords() {
        if (!conecting) {
            return null;
        }
        StringBuilder resalt = new StringBuilder();
        Statement statement = createStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT login, name, points  FROM users1");
            while (resultSet.next()) {
                resalt.append(
                        resultSet.getString("login")).append("|||").
//                        append(resultSet.getString("name")).append("|||").
        append(resultSet.getString("points")).append("\n");
            }
        } catch (SQLException e) {
            log.logError(e);
        }
        return resalt.toString();
    }

    public boolean deleteUser(String login, String password) {
        if (!conecting) {
            if (!logIn(login, password)) return false;
        }
        Statement statement = createStatement();
        try {
            statement.executeUpdate("DELETE FROM users1 WHERE login = \'" + login + "\'");
            return true;
        } catch (SQLException e) {
            log.logError(e);
        }
        return false;
    }

    private Statement createStatement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            log.logError(e);
        }
        return null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getDataForConectToDB() {
        return dataForConectToDB;
    }

    public void setDataForConectToDB(Properties dataForConectToDB) {
        this.dataForConectToDB = dataForConectToDB;
    }

    public boolean isConecting() {
        return conecting;
    }
}