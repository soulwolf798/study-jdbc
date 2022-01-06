package com.smalljdbc.smalljdbc.datasource.V1;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.sql.DataSource;

import lombok.Data;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-03
 * 最基础的数据源
 */
@Data
public class SmallDataSourceV1 implements DataSource {

    private static Map<String, Driver> registeredDrivers = new ConcurrentHashMap();
    private String driver;
    private String url;
    private String username;
    private String password;


    // 获取DriverManger已注册的驱动，并放到数据源的registeredDrivers中
    static {
        Enumeration drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = (Driver) drivers.nextElement();
            registeredDrivers.put(driver.getClass().getName(), driver);
        }

    }

    public SmallDataSourceV1(){

    }

    public SmallDataSourceV1(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public Connection getConnection() throws SQLException {
        this.initializeDriver();

        Properties props = new Properties();
        if (username != null) {
            props.setProperty("user", username);
        }

        if (password != null) {
            props.setProperty("password", password);
        }

        Connection connection = DriverManager.getConnection(this.url, props);

        return connection;
    }

    private synchronized void initializeDriver() throws SQLException {
        if (!registeredDrivers.containsKey(this.driver)) {
            try {
                Class driverType = Class.forName(this.driver);

                Driver driverInstance = (Driver) driverType.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(driverInstance);
                registeredDrivers.put(this.driver, driverInstance);
            } catch (Exception var3) {
                throw new SQLException("Error setting driver on UnpooledDataSource. Cause: " + var3);
            }
        }

    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }



}
