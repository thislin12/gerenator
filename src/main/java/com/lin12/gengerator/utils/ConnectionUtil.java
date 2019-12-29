package com.lin12.gengerator.utils;

import com.lin12.gengerator.utils.YmlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * 获取 连接对象
 * @author lin12
 * @date 2019/12/21
 */
public class ConnectionUtil {

    private static String userName;
    private static String password;
    private static String url;
    private static String driverClassName;

    static {
        Map datasourceMap = YmlUtils.readDbInfo();
        if(datasourceMap == null){
            throw new RuntimeException("Error: 配置文件 datasource 没有配置");
        }
        userName = datasourceMap.get("username").toString();
        password = datasourceMap.get("password").toString();
        url = (String) datasourceMap.get("url");
        driverClassName = (String) datasourceMap.get("driverClassName");
    }

    public static Connection getConnection(){
        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
