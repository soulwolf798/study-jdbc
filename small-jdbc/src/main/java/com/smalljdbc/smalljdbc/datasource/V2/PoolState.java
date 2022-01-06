package com.smalljdbc.smalljdbc.datasource.V2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ouyangchao <ouyangchao@kuaishou.com>
 * Created on 2022-01-03
 */
public class PoolState {


    protected final List<ProxyConnection> idleConnections = new ArrayList<>();
    protected final List<ProxyConnection> activeConnections = new ArrayList<>();

}
