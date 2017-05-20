package model.record_worker;

import model.ConsoleHelper;

import java.io.File;
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
    private Properties dataForConectToDB;
    private Connection connection;
    private boolean conecting = false;


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
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql.alex-savin.myjino.ru:3306/alex-savin_trutak",
                    dataForConectToDB.getProperty("login"),
                    dataForConectToDB.getProperty("password")
            );

        } catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean logIn(String login, String password) {
        this.login = login;
        this.password = password;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LOGIN, PASSWORD FROM users1");
            while (resultSet.next()) {
                if (
                        resultSet.getString("login").equals(login) &
                                password.equals(resultSet.getString("password"))
                        ) {//
                    return true;
                }
            }
            statement.close();
        } catch (SQLException e) {e.printStackTrace();}
        return false;
    }


    public boolean signUp(String login1, String password1) {
        this.login = login1;
        this.password = password1;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT LOGIN FROM users1");
            while (resultSet.next()) {
                if (login1.equals(resultSet.getString("login"))) {
                    return false;
                }
            }
            login1 = "\'" + login1 +  "\'";

        statement.executeUpdate(
                "INSERT INTO users1 ( login, password) VALUES ( " + login1 + ", " + password1 + ");");

        statement.close();
        } catch (SQLException e) {e.printStackTrace();}
        return true;
    }

    public void writeRecords(int points) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE USERS1 SET POINTS = " + points + " WHERE LOGIN = \'" + login + "\'");
        } catch (SQLException e) {e.printStackTrace();}

    }

    public void closeConection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void entranceManager(){
        while (!conecting) {
            ConsoleHelper.writeMessage("your conect :");
            ConsoleHelper.writeMessage("write login");
            String login = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("write password");
            String password = ConsoleHelper.readString();
            ConsoleHelper.writeMessage("signIn(1) or  signUp(2)");
            switch (ConsoleHelper.readInt()) {
                case 1:
                    conecting = logIn(login, password);
                    break;
                case 2:
                    conecting = signUp(login, password);
                    break;
            }
            if (!conecting) {
                ConsoleHelper.writeMessage("try again ?? yes or EXIT");
                if ("exit".equals(ConsoleHelper.readString())) {
                    return;
                }//
            }
        }

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
