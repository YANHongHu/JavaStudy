package C3P0;

import com.alibaba.druid.support.json.JSONUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Set {
    public static void main(String[] args) throws SQLException {
        //1. 获取DataSource （不指定名称使用默认配置）
        DataSource ds = new ComboPooledDataSource();
        //指定名称后使用指定配置
        DataSource dataSource = new ComboPooledDataSource("otherc3p0");
        //2. 获取连接
        for(int i = 1;i<=11;i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + ":" + conn);

            if (i == 5) {
                conn.close();//归还到连接池中
            }
        }
    }
    
}
