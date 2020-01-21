package com.lin12.gengerator.common;

import java.util.HashMap;
import java.util.Map;

/**
 * MYSQL类型与JAVA类型对应表
 * @author lin12
 * @date 2019/12/22
 */
public class TypeConfig {

    private static Map<String,String> typeMap = new HashMap<String,String>();

    private static Map<String,String> ftlMap = new HashMap<String,String>();
    static {
        typeMap.put("VARCHAR","java.lang.String");
        typeMap.put("CHAR","java.lang.String");
        typeMap.put("BLOB","java.lang.byte[]");
        typeMap.put("TEXT","java.lang.String");
        typeMap.put("INTEGER","INTEGER UNSIGNED");
        typeMap.put("TINYINT","java.lang.Boolean");
        typeMap.put("SMALLINT","java.lang.Integer");
        typeMap.put("MEDIUMINT","java.lang.Integer");
        typeMap.put("BIT","java.lang.Boolean");
        typeMap.put("BIGINT","java.math.BigInteger");
        typeMap.put("FLOAT","java.lang.Float");
        typeMap.put("DOUBLE","java.lang.Double");
        typeMap.put("DECIMAL","java.math.BigDecimal");
        typeMap.put("ID","java.lang.Long");
        typeMap.put("DATE","java.sql.Date");
        typeMap.put("TIME","java.sql.Time");
        typeMap.put("DATETIME","java.sql.Timestamp");
        typeMap.put("TIMESTAMP","java.sql.Timestamp");
        typeMap.put("YEAR","java.sql.Date");
        typeMap.put("INT","java.lang.Integer");
        typeMap.put("BIGINT UNSIGNED","import java.math.BigInteger");


        ftlMap.put(Constant.ENTITY,Constant.ENTITY_FTL);
        ftlMap.put(Constant.DAO,Constant.DAO_FTL);
        ftlMap.put(Constant.MAPPER,Constant.MAPPER_FTL);
        ftlMap.put(Constant.SERVICE,Constant.SERVICE_FTL);
        ftlMap.put(Constant.SERVICE_IMPL,Constant.SERVICE_IMPL_FTL);
        ftlMap.put(Constant.CONTROLLER, Constant.CONTROLLER_FTL);
    }

    /**
     * 返回Java 类型
     * @param columnType 数据库查出来的列类型
     * @return String
     */
    public static String getColumnType(String columnType){
        String s = typeMap.get(columnType);
        if(s == null){
            throw new RuntimeException(columnType + " columnType is empty");
        }
        return s;
    }

    /**
     * 返回Java 类型
     * @param type 数据库查出来的列类型
     * @return String
     */
    public static String getFtlMap(String type){
        return ftlMap.get(type);
    }
}
