package com.lin12.gengerator.task;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.FtlConfigInfo;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.utils.*;

/**
 * @author lin12
 * @date 2019/12/23
 */
public class EntityTask {

    /**
     * 配置实体类 并创建文件
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        //设置 配置 ftl模板 预编译文本属性
        FtlConfigInfo ftlConfigInfo = new FtlConfigInfo();
        ftlConfigInfo.setAuthor((String) YmlUtils.generatorConfig("author"));
        ftlConfigInfo.setDate(StringUtils.getNowDateString());
        ftlConfigInfo.setRemark(tableInfo.getTableRemark());
        ftlConfigInfo.setClassName(tableInfo.getClassName());
        ftlConfigInfo.setTableName(tableInfo.getTableName());
        ftlConfigInfo.setPackageName(GeneratorJointUtil.getPackageName(Constant.ENTITY));
        ftlConfigInfo.setEntityName(StringUtils.firstToLowerCase(tableInfo.getClassName()));
        ftlConfigInfo.setPropertyList(GeneratorJointUtil.getPropertyList(tableInfo.getColumnInfos()));

        FileUtil.generateToJava(Prop2MapUtil.prop2Map(ftlConfigInfo), Constant.ENTITY);
    }
}
