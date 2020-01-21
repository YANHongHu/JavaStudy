package jdbc快速入门;

import java.net.ConnectException;
import java.sql.*;

public class JDBCSelect {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 定义sql
            String sql = "select * from lsdapp_user";
            //3. 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///db5", "root", "yhw06081036");
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            //6. 处理结果
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                String telephone = rs.getString("telephone");
                String authority = rs.getString("authority");

                System.out.println(id+" "+name+" "+password+" "+telephone+" "+authority);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //7.释放资源
            //避免空指针异常
            if(rs!=null){
                try{
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }


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
