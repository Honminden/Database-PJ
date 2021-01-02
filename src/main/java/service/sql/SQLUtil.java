package service.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLUtil
{
    private static final String URL = "jdbc:mysql://localhost:3306/hospital?serverTimezone=UTC&useSSL=false&autoReconnect=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ss991201";
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        if (con.isClosed())
        {
            throw new SQLException("Connection not available.");
        }
        return con;
    }
}
