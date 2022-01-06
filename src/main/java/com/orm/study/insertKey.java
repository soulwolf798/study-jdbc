package com.orm.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-21
 */
public class insertKey {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1:3306/test", "root",
                            "root");

            stmt = conn.createStatement();

            String sql = "insert user set name='oyc', phone='123'";

            System.out.print("Statement = "+ stmt.getResultSetType() +"\n");


            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet set = stmt.getGeneratedKeys();
            while (set.next())
                System.out.print("key = "+ set.getString(1) +"\n");

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
