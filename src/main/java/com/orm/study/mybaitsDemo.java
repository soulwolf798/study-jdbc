package com.orm.study;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-26
 */
public class mybaitsDemo {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        Connection conn2 = null;
        Statement stmt2 = null;
        try {

            PooledDataSource dataSource =
                    new PooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/test", "root",
                            "root");

            PooledDataSource dataSource2 =
                    new PooledDataSource("com.mysql.cj.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/test2", "root",
                            "root");


            conn = dataSource.getConnection();

            conn2  = dataSource2.getConnection();

            stmt = conn.createStatement();

            stmt2 = conn2.createStatement();

            String sql = "SELECT * FROM user where id =1";

            ResultSet rs = stmt.executeQuery(sql);
            ResultSet rs2 = stmt2.executeQuery(sql);

            while (rs2.next()) {
                System.out.print(rs2.getString("name"));
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
