package utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by 10742 on 2017/11/17.
 */
public class JDBCUtils {
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=";
    private static String user = "sa";
    private static String password = "sa12345";

    private JDBCUtils() {
    }
    //加载驱动
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    //根据数据库名获取连接
    public static java.sql.Connection getConnection(String name) throws SQLException {
        return DriverManager.getConnection(url + name, user, password);
    }
    //释放连接
    public static void free(ResultSet rs, Statement s, java.sql.Connection c) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (c != null) {
                        c.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
