package com.lin12.gengerator.utils;

import com.lin12.gengerator.entity.ClassMethod;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.task.DaoTask;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lin12
 * @date 2020/1/16
 */
public class MethodUtils {

    public static String  getServiceMethod(TableInfo tableInfo){
        ClassMethod classMethod = addMethodService(tableInfo);
        return generateInterfaceMethod(classMethod);
    }

    /**
     * Service 添加接口
     *
     * @param tableInfo     tableInfo
     * @return ClassMethod
     */
    private static ClassMethod addMethodService(TableInfo tableInfo){
        String className = tableInfo.getClassName();
        Map<String, String> map = new HashMap<String, String>(2);
        map.put(StringUtils.firstToLowerCase(className), StringUtils.firstToLowerCase(className));
        String remark = getRemark("添加" + tableInfo.getTableRemark(), map , "成功失败");
        String parameter = className + " " + StringUtils.firstToLowerCase(className);
        String content = "         return int insert = "+ StringUtils.firstToLowerCase(className) + DaoTask.FILE_SUFFIX +".insert("+ StringUtils.firstToLowerCase(className) +") > 0;\n";
        return new ClassMethod("boolean","add" + className, parameter,  content , remark);
    }

    /**
     * 方法类
     * @param classMethod       classMethod
     * @return String
     */
    private static String generateInterfaceMethod(ClassMethod classMethod){
        return classMethod.getRemark() + "    " + classMethod.getReturnType() + " " + classMethod.getMethodName() +
                "(" + classMethod.getParameter() + ");\n\n";
    }

    /**
     * 拼接注解
     * @param explain 注解体 包含 注解说明 @param 和 @return
     * @return
     */
    private static String getRemark(String explain, Map<String, String> map, String returnType){
        StringBuilder sb = new StringBuilder();
        sb.append("    /**\n");
        sb.append("     * ").append(explain).append("\n");
        sb.append("     *\n");
        for (String param : map.keySet()) {
            sb.append("     * @param ").append(param).append("      ").append(map.get(param)).append("\n");
        }
        if (returnType != null){
            sb.append("     * @return " + returnType + "\n");
        }

        sb.append("     */\n");
        return sb.toString();
    }
}
