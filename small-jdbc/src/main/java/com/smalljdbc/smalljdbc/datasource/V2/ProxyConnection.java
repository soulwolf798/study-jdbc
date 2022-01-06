package com.smalljdbc.smalljdbc.datasource.V2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-03
 */
public class ProxyConnection implements InvocationHandler {
    private static final String CLOSE = "close";
    private static final Class<?>[] IFACES = new Class<?>[] {Connection.class};


    private final SmallDataSourceV2 dataSource;
    private final Connection realConnection;
    private final Connection proxyConnection;

    public ProxyConnection(Connection connection, SmallDataSourceV2 dataSource) {
        this.realConnection = connection;
        this.dataSource = dataSource;
        this.proxyConnection = (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(), IFACES, this);
    }

    public Connection getProxyConnection() {
        return proxyConnection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (CLOSE.equals(methodName)) {
            dataSource.pushConnection(this);
            return null;
        }

        return method.invoke(realConnection, args);
    }
}
