package jdbc快速入门;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCshiWu {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            // 1.获取连接
            conn = JDBCUtilS.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            // 2.定义sql
            String sql1 = "update lsdapp_user set authority=? where user_name = ?";
            String sql2 = "update lsdapp_user set authority=? where user_name = ?";
            // 3.获取执行sql的对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            // 4.设置参数
            pstmt1.setString(1,"salesman");
            pstmt1.setString(2,"黄国华");
            pstmt2.setString(1,"boss");
            pstmt2.setString(2,"严鸿炜");
            // 5.执行sql
            pstmt1.executeUpdate();
            // 手动制造异常
            int i = 3/0;
            pstmt2.executeUpdate();
            //提交事务
            conn.commit();
        } catch (Exception e) {
            try{
                //事务回滚
                if(conn!=null)
                    conn.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtilS.close(pstmt1,conn);
            JDBCUtilS.close(pstmt2,null);
        }
    }
}
