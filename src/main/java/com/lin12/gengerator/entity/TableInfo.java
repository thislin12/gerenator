package com.lin12.gengerator.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin12
 * @date 2019/12/22
 */
@Data
public class TableInfo {

    private String tableName;
    private String className;
    private String tableRemark;
    private List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();


    public TableInfo(String tableName, String className) {
        this.tableName = tableName;
        this.className = className;
    }
}
