package com.lin12.gengerator.utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 字符串工具
 * @author lin12
 * @date 2019/12/21
 */
public class StringUtils {

    /**
     * 把字符串转驼峰
     * @param className 列名
     * @return String
     */
    public static String nomenclatureOfHump(String className){
        String[] s = className.split("_");
        StringBuilder columnName = new StringBuilder(s[0]);
        for (int i = 1; i < s.length; i++) {
            char[] chars = s[i].toCharArray();
            chars[0] -= 32;
            String upper = String.valueOf(chars);
            columnName.append(upper);
        }
        return columnName.toString();
    }

    /**
     * 把字符串转驼峰
     * @param className 列名
     * @return String
     */
    public static String becomeClassName(String className){
        String[] s = className.split("_");
        StringBuilder columnName = new StringBuilder();
        for (String value : s) {
            char[] chars = value.toCharArray();
            chars[0] -= 32;
            String upper = String.valueOf(chars);
            columnName.append(upper);
        }
        return columnName.toString();
    }

    /**
     * 获取java类型
     * @param fullPath 类型包名
     * @return String
     */
    public static String getType(String fullPath){
        int i = fullPath.lastIndexOf(".") + 1;
        return fullPath.substring(i);
    }

    /**
     * 判断字符串是否为空或者Null
     * @param string 字符串
     * @return boolean
     */
    public static boolean isBlank(String string) {
        if (string == null || "".equals(string.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 把点切换成 \
     * @param packageName
     * @return
     */
    public static String package2Path(String packageName) {
        if (isBlank(packageName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] packages = packageName.split("\\.");
        for (String str : packages) {
            sb.append(str).append(File.separator);
        }
        return sb.toString();
    }

    /**
     * 表头注解是否换行
     * @param list 每一列注解
     * @return String
     */
    public static String headAnnotationLineFeed(List<String> list){
        StringBuilder classHeadAnnotation = new StringBuilder();
        //遍历 只有一条 不加换行, 两条或两条以上 最后一条不加换行
        for (int i = 0; i < list.size(); i++) {
            classHeadAnnotation.append(list.get(i));
            if (list.size() != 1 && i != list.size() - 1){
                classHeadAnnotation.append("\n");
            }
        }
        return classHeadAnnotation.toString();
    }

    /**
     * 第一个单词小写
     * @param src 字符串
     * @return String
     */
    public static String firstToLowerCase(String src){
        return src.substring(0,1).toLowerCase() + src.substring(1);
    }

    /**
     * 第一个单词大写
     * @param src 字符串
     * @return String
     */
    public static String firstToUpperCase(String src){
        return src.substring(0,1).toUpperCase() + src.substring(1);
    }
    /**
     * 返回当前时间字符串
     * @return String
     */
    public static String getNowDateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
