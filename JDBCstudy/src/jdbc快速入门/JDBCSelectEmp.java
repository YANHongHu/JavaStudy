package jdbc快速入门;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectEmp {

    public static void main(String[] args) {
        List<Emp> list = new JDBCSelectEmp().findAll();
        System.out.println(list);
    }

    /**
     *查询所有的emp对象
     * @return
     */
    public List<Emp> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db5","root", "yhw06081036");
            //3. 定义sql
            String sql = "select * from lsdapp_user";
            //4. 获取执行sql的对象
            stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            //6. 遍历结果集，封装对象，装载集合
            Emp emp = null;
            list = new ArrayList<>();
            while (rs.next()) {
                //获取数据
                int id = rs.getInt(1);
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                String telephone = rs.getString("telephone");
                String authority = rs.getString("authority");
                //创建emp对象
                emp = new Emp();
                emp.setId(id);
                emp.setName(name);
                emp.setPassword(password);
                emp.setAuthority(authority);
                emp.setTelephone(telephone);
                // 装载集合
                list.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //7.释放资源
            //避免空指针异常
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
        return list;
    }
}
