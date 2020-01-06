package com.lin12.gengerator.utils;


import com.lin12.gengerator.entity.ColumnInfo;
import com.lin12.gengerator.entity.TableInfo;


import java.text.SimpleDateFormat;
import java.util.Date;
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
     * 获取头部注解
     * @return String
     */
    public static String getClassHeadRemark(String tableRemark){
        return "/**\n" + "* "+ tableRemark +"\n" + "* @author " + getAuthorName() + "\n" + getCreateDate() + "*/";
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

    /**
     * 获取注解 作者名称
     * @return String
     */
    public static String getAuthorName(){
        return (String)YmlUtils.generatorConfig("author");
    }

    /**
     * 获取注解 创建时间
     * @return String
     */
    public static String getCreateDate(){
        Boolean isDate = (Boolean)YmlUtils.generatorConfig("date");
        if (isDate){
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            return "* @date " + format + "\n";
        }
        return "";
    }





}
