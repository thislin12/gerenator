package com.lin12.gengerator.invoke;

import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.task.*;

import java.util.List;

/**
 * @author lin12
 * @date 2019/12/23
 */
public class Invoke {

    public static void run(){
        List<TableInfo> tableInfoAll = new GeneratorInit().getTableInfoAll();
        for (TableInfo tableInfo : tableInfoAll) {
            EntityTask.getClassConfig(tableInfo);
            DaoTask.getClassConfig(tableInfo);
            MapperTask.getClassConfig(tableInfo);
            ServiceTask.getClassConfig(tableInfo);
            ServiceImplTask.getClassConfig(tableInfo);
            ControllerTask.getClassConfig(tableInfo);
        }
    }
}
