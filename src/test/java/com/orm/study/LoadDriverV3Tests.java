package com.orm.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;


/**
 * 加载驱动的示例
 */
class LoadDriverV3Tests {


    @Test
    void contextLoads() {
        Connection conn = null;
        Statement stmt = null;
        try {

            conn = DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1:3306/test", "root",
                            "Abcdef@123456");

            stmt = conn.createStatement();

            String sql = "select  * from user";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.print(rs.getString("name"));
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
