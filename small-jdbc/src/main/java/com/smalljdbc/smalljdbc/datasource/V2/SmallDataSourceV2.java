package com.smalljdbc.smalljdbc.datasource.V2;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.smalljdbc.smalljdbc.datasource.V1.SmallDataSourceV1;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-03
 * 可回收链接的数据源
 */
public class SmallDataSourceV2 implements DataSource {

    // 统一管理Connection
    private final PoolState state = new PoolState();

    // 新建Connection的根源
    private final SmallDataSourceV1 dataSource;

    // 构造函数
    public SmallDataSourceV2(String driver, String url, String username, String password) {
        dataSource = new SmallDataSourceV1(driver, url, username, password);
    }

    // 将数据源放入池中（回收时候用的）
    protected void pushConnection(ProxyConnection conn) throws SQLException {
        //从活跃池中移出
        state.activeConnections.remove(conn);
        //放入空闲池
        state.idleConnections.add(conn);
    }

    // 获取数据源
    protected ProxyConnection popConnection() throws SQLException {
        // 判断空闲池是否为空
        if (!state.idleConnections.isEmpty()) {
            ProxyConnection connection = state.idleConnections.get(0);
            state.activeConnections.add(connection);
            state.idleConnections.remove(connection);
            return connection;
        }

        // 空闲池为空，则新建连接，并放入活跃池
        ProxyConnection newConn = new ProxyConnection(dataSource.getConnection(), this);
        state.activeConnections.add(newConn);
        return newConn;
    }

    public void printInfo() {
        System.out.print("活跃链接个数：" + state.activeConnections.size() + "\n"
                + "空闲链接个数：" + state.idleConnections.size() + "\n");
    }

    @Override
    public Connection getConnection() throws SQLException {
        return popConnection().getProxyConnection();

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
