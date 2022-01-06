package com.orm.study;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.smalljdbc.smalljdbc.datasource.V1.SmallDataSourceV1;


/**
 * 加载驱动的示例
 */
class SamllDataSourceV1Tests {


    @Test
    void contextLoads() {
        Connection conn = null;
        Statement stmt = null;
        try {

            DataSource dataSource =
                    new SmallDataSourceV1("com.smalljdbc.smalljdbc.SmallDriver", "jdbc:myapp://127.0.0.1:3306/test", "root",
                            "root");
            // （3）加载自己的驱动
            conn = dataSource.getConnection();

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
