package db;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null){
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url, properties);
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static Properties loadProperties() {
        try(FileInputStream fileInputStream = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fileInputStream);
            return props;
        }
        catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        try {
            st.close();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    public static void closeResultSet(ResultSet rs) {
        try {
            rs.close();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
