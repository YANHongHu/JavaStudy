package JDBCTemplate;

import Druid.DruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCtemplateTest {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        //3.调用方法
        String sql = "update lsdapp_user set authority='salesman' where id = ?";
        System.out.println(template.update(sql,3));
    }
}
