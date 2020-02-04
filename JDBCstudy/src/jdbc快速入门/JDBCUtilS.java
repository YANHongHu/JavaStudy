package jdbc快速入门;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC工具类
 */
public class JDBCUtilS {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    /**
     * 文件的读取，只需读取一次即可拿到这些值。使用静态代码块
     */
    static{
        try { 
            //读取文件资源，获取值
            //1 创建Properties集合类
            Properties pro = new Properties();
            //动态获取src路径下文件的方式--->classLoader 类加载器
            ClassLoader classLoader = JDBCUtilS.class.getClassLoader();
            URL res = classLoader.getResource("jdbcConnectSet.properties");
            String path = res.getPath();
            //2 加载文件
            //pro.load(new FileReader("../../jdbcConnectSet.properties"));
            pro.load(new FileReader(path));
            //3 获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4 注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放资源
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt, Connection conn){
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
