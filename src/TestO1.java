import java.sql.*;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class TestO1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://mysql.alex-savin.myjino.ru:3306/alex-savin_trutak",
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
