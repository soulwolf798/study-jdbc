package com.orm.study;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import com.smalljdbc.smalljdbc.datasource.V2.SmallDataSourceV2;


/**
 * 加载驱动的示例
 */
class SamllDataSourceV2Tests {


    @Test
    void contextLoads() {
        SmallDataSourceV2 dataSource =
                new SmallDataSourceV2("com.smalljdbc.smalljdbc.SmallDriver", "jdbc:myapp://127.0.0.1:3306/test", "root",
                        "root");
        Connection conn = null;
        Statement stmt = null;
        try {

            dataSource.printInfo();

            // （3）加载自己的驱动
            conn = dataSource.getConnection();

            dataSource.printInfo();

            stmt = conn.createStatement();

            String sql = "query 1";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.print(rs.getString("name") + "\n");
            }


            rs.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                    //关闭连接
                    dataSource.printInfo();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
