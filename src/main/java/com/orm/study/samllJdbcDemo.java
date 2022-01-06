package com.orm.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-26
 */
public class samllJdbcDemo {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
             // 最原始的注册
            //SmallDriver driver = new SmallDriver();
            //DriverManager.registerDriver(driver);

            //通过系统变量注册
            //System.setProperty("jdbc.drivers","com.smalljdbc.smalljdbc.SmallDriver");

            conn = DriverManager
                    .getConnection("jdbc:myapp://127.0.0.1:3306/test", "root",
                            "root");

            stmt = conn.createStatement();

            String sql = "query 1";

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
