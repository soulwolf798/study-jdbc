package com.orm.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;


/**
 * 加载驱动的示例
 * */
class LoadDriverV2Tests {


    @Test
    void contextLoads() {
        Connection conn = null;
        Statement stmt = null;
        try {

            //通过系统变量注册
            System.setProperty("jdbc.drivers","com.smalljdbc.smalljdbc.SmallDriver");

            //加载自己的驱动
            conn = DriverManager.getConnection("jdbc:myapp://127.0.0.1:3306/test", "root", "root");

            stmt = conn.createStatement();

            String sql = "query 1";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.print(rs.getString("name")+"\n");
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
