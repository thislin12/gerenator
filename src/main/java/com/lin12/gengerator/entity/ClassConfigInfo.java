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

    private String methodList;

    public ClassConfigInfo() {
    }
}
