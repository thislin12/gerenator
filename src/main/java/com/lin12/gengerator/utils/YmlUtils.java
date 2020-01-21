package com.lin12.gengerator.utils;


import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.TableInfo;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 读取yml配置文件
 * @author lin12
 * @date 2019/12/21
 */
@SuppressWarnings("unchecked")
public class YmlUtils {

    static Map<String, Object> yml;

    /*
     *
     */
    static {
        Yaml yaml = new Yaml();
        InputStream resourceAsStream = YmlUtils.class.getClassLoader().getResourceAsStream(Constant.YML_CONFIG_PATH);
        yml = (Map<String, Object>) yaml.load(resourceAsStream);
    }

    /**
     *
     * @return 返回 datasourceMap
     */
    public static Map<String, Object> readDbInfo(){
        return (Map<String, Object>)yml.get("datasource");
    }

    /**
     *
     * @return 返回 表信息
     */
    public static List<TableInfo> readTableInfo(){
        Map<String, Object> tableConfig = (Map<String, Object>)yml.get("tableConfig");
        List<Map<String, Object>> tables = (List<Map<String, Object>>)tableConfig.get("tables");
        List<TableInfo> tableInfos = new ArrayList<TableInfo>();
        for (Map<String, Object> table : tables) {
            Map<String, Object> tableProp = (Map<String, Object>)table.get("table");
            String tableName = (String)tableProp.get("tableName");
            String className = (String)tableProp.get("ClassName");
            TableInfo tableInfo = new TableInfo(tableName, className);
            tableInfos.add(tableInfo);
        }
        return tableInfos;
    }

    /**
     * 读取生成文件的路径
     * @param pathProp 传入 配置文件中 path 属性名
     * @return 返回路径
     */
    public static String readGeneratorFilePath(String pathProp){
        Map<String, Object> path = (Map<String, Object>)yml.get("path");
        return path == null ? null : (String)path.get(pathProp);
    }

    /**
     * 获取配置信息
     * @param prop 属性名
     * @return String
     */
    public static Object generatorConfig(String prop){
        Map<String, Object> generatorConfig = (Map<String, Object>)yml.get("generatorConfig");
        return generatorConfig.get(prop);
    }

    /**
     * 是否使用mybatis-plus
     * @return boolean
     */
    public static boolean isMybatisPlus(){
        return (Boolean) YmlUtils.generatorConfig("mybatis-plus");
    }

    /**
     * 是否使用Swagger
     * @return boolean
     */
    public static boolean isSwagger(){
        return (Boolean) YmlUtils.generatorConfig("swagger");
    }

    /**
     * 如果需要指定同一个包路径 则生成pojo类名 全小写的 包
     * @param entityName entityName
     * @return String
     */
    public static String isCreatePackage(String entityName){
        String entityPackage = "";
        if((Boolean) YmlUtils.generatorConfig(Constant.CREATE_PACKAGE)){
            entityPackage += "\\" + entityName.toLowerCase();
        }
        return entityPackage;
    }
}
