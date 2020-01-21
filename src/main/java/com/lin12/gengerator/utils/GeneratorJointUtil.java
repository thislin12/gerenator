package com.lin12.gengerator.utils;


import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.ColumnInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建字符串
 * @author lin12
 * @date 2019/12/22
 */
public class GeneratorJointUtil {

    /**
     * 获取导包 路径
     * @param pathProp 传入 模块属性
     * @return String
     */
    public static String getPackageName(String pathProp){
        String path = YmlUtils.readGeneratorFilePath(pathProp);
        if(path == null){
            return null;
        }
        if (!"".equals(YmlUtils.generatorConfig(Constant.CREATE_PACKAGE))){
            String packagePath = (String)YmlUtils.generatorConfig(Constant.CREATE_PACKAGE);
            path += "." + packagePath.toLowerCase();
        }
        return "package " + path + ";";
    }

    /**
     * 获取需要导入的包
     * @param columnInfos 列集合
     * @return String
     */
    public static String getImportList(List<ColumnInfo> columnInfos){
        Map<String, String> map = new HashMap<String, String>(10);
        for (ColumnInfo columnInfo : columnInfos) {
            if(map.get(columnInfo.getColumnType()) == null){
                map.put(columnInfo.getColumnType(),columnInfo.getColumnType());
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String lineFeed = "\n";
        for (String key : map.keySet()) {
            sb.append("import ").append(key).append(";");
            if (i != map.size() - 1){
                sb.append(lineFeed);
            }
            i++;
        }
        return sb.toString();
    }




    /**
     * 获取全属性信息
     * @param columnInfos columnInfos
     * @return String
     */
    public static String getPropertyList(List<ColumnInfo> columnInfos){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String lineFeed = "\n";
        for (ColumnInfo columnInfo : columnInfos) {
            String annotation = getAnnotation(columnInfo.getColumnRemark());
            sb.append(annotation);
            if (columnInfo.isPrimaryKey() && YmlUtils.isMybatisPlus()){
                sb.append("    @TableId\n");
            }
            if (YmlUtils.isSwagger()){
                sb.append("    @ApiModelProperty(value=\""+ columnInfo.getColumnRemark() +"\",name=\""+ columnInfo.getColumnName()  +"\")\n");
            }
            sb.append("    private ").append(StringUtils.getType(columnInfo.getColumnType())).append(" ").append(columnInfo.getColumnName()).append(";").append(lineFeed);
            if(i != columnInfos.size() - 1){
                sb.append(lineFeed);
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * 获取列注释文本
     * @param annotationName 列对象
     * @return String
     */
    public static String getAnnotation(String annotationName){
        if(annotationName == null){
            annotationName = "";
        }
        return "    /**\n" + "     * " + annotationName + "\n" + "     */\n";
    }


}
