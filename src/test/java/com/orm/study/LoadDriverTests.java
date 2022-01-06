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
class LoadDriverTests {


    @Test
    void contextLoads() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // （1）最原始的注册
            //SmallDriver driver = new SmallDriver();
            //DriverManager.registerDriver(driver);

            // （2）通过系统变量注册
                        System.setProperty("jdbc.drivers","com.smalljdbc.smalljdbc.SmallDriver");

            // （3）加载自己的驱动
            conn = DriverManager.getConnection("jdbc:myapp://127.0.0.1:3306/test", "root", "root");

            stmt = conn.createStatement();

            String sql = "query 1";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.print(rs.getString("name")+"\n");
            }


            // （4）通过SPI机制加载mysql
//            conn = DriverManager
//                    .getConnection("jdbc:mysql://127.0.0.1:3306/test", "root",
//                            "Abcdef@123456");
//
//            stmt = conn.createStatement();
//
//            String sql = "select  * from user";
//
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                System.out.print(rs.getString("name"));
//            }


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
