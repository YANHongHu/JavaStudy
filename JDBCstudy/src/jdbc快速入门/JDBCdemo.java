package jdbc快速入门;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCdemo {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2. 定义sql
            String sql = "insert into lsdapp_user values(null,'严鸿炜', 123456789, 18706799606, 'boss', 1, '12BFCJBI1BR1B41B41BD' )";
            String sql1 = "update lsdapp_user set authority='salesman' where user_name = '严鸿炜'";
            String sql2 = "delete from lsdapp_user where user_name = '测试'";
            //3. 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///db5", "root", "yhw06081036");
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            int count = stmt.executeUpdate(sql2);
            //6. 处理结果
            //System.out.println(count);
            if(count>0)
                System.out.println("执行成功！");
            else{
                System.out.println("执行失败！");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.释放资源
            //避免空指针异常
            if(stmt!=null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
