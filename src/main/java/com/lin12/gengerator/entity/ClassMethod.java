package com.lin12.gengerator.entity;

import lombok.Data;

/**
 * @author lin12
 * @date 2020/1/16
 */
@Data
public class ClassMethod {


    /**
     * 返回类型
     */
    private String returnType;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数名 多个参数拼接的字符串
     */
    private String parameter;

    /**
     * 内容体
     */
    private String methodContent;

    /**
     * 方法注解
     */
    private String remark;

    public ClassMethod(String returnType, String methodName, String parameter, String methodContent, String remark) {
        this.returnType = returnType;
        this.methodName = methodName;
        this.parameter = parameter;
        this.methodContent = methodContent;
        this.remark = remark;
    }
}
