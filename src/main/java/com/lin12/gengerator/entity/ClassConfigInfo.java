package com.lin12.gengerator.entity;

import lombok.Data;

/**
 * @author lin12
 * @date 2019/12/22
 */
@Data
public class ClassConfigInfo {

    private String className;

    private String packageName;

    private String importPackageList;

    private String classHeadRemark;

    private String classHeadAnnotation;

    private String propertyList;


    public ClassConfigInfo(String className, String packageName, String importPackageList, String classHeadRemark, String classHeadAnnotation, String propertyList) {
        this.className = className;
        this.packageName = packageName;
        this.importPackageList = importPackageList;
        this.classHeadRemark = classHeadRemark;
        this.classHeadAnnotation = classHeadAnnotation;
        this.propertyList = propertyList;
    }
}
