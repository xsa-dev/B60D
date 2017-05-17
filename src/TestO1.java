import java.sql.*;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class TestO1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://mysql.alex-savin.myjino.ru:3306","риои", "июри");
    }//
}//
/*

*/
