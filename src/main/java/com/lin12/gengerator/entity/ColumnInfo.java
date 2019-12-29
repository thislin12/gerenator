package com.lin12.gengerator.entity;

import lombok.Data;

/**
 * 列信息
 * @author lin12
 * @date 2019/12/22
 */
@Data
public class ColumnInfo {

    private String columnName;
    private String columnType;
    private String columnRemark;
    private boolean isPrimaryKey;


    public ColumnInfo(String columnName, String columnType, String columnRemark, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnRemark = columnRemark;
        this.isPrimaryKey = isPrimaryKey;
    }

}
