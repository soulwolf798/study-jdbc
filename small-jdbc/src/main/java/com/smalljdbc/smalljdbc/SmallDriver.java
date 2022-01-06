package com.smalljdbc.smalljdbc;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2021-12-27
 */
public class SmallDriver implements Driver, Closeable {

    static final String DRIVER_NAME = "my JDBC Driver";
    static final String DRIVER_VERSION;
    static final int DRIVER_VERSION_MAJOR;
    static final int DRIVER_VERSION_MINOR;
    private static final String DRIVER_URL_START = "jdbc:myapp:";
    static   {
        // 我们通过静态构造函数注册我们的驱动，如果了解golang 的sql 驱动的话，也是一样的，支持golang 基于了 init 函数进行驱动的注册
        try {
            DRIVER_VERSION_MAJOR= 3;
            DRIVER_VERSION_MINOR =1;
            DRIVER_VERSION = "1.0.0";
            SmallDriver driver = new SmallDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() throws IOException {
        // http connect demo
    }

    // implements connect   manage connect
    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        // first check url is validate
        if (acceptsURL(url)){
            return  new SmallConnection(url,info);
        }
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url.startsWith(DRIVER_URL_START);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return DRIVER_VERSION_MAJOR;
    }

    @Override
    public int getMinorVersion() {
        return DRIVER_VERSION_MINOR;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

}
