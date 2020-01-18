package jdbc快速入门;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1.导入驱动jar包
        // 2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 3.获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db5","root","yhw06081036");
        // 4.定义sql语句
        String sql = "update lsdapp_user set authority = 'salesman' where id = 3";
        // 5.获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();
        // 6.执行sql
        int count = stmt.executeUpdate(sql);
        // 7.处理结果
        System.out.println(count);
        // 8.释放资源
        stmt.close();
        conn.close();

    }
}
