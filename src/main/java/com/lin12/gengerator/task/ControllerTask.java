package com.lin12.gengerator.task;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.FtlConfigInfo;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.utils.*;

/**
 * @author lin12
 * @date 2020/1/16
 */
public class ControllerTask {

    private FtlConfigInfo ftlConfigInfo;

    public void setFtlConfigInfo(FtlConfigInfo ftlConfigInfo) {
        this.ftlConfigInfo = ftlConfigInfo;
    }

    private static final String FILE_SUFFIX = "Controller";

    /**
     * 配置实体类 并创建文件
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        FtlConfigInfo ftlConfigInfo = new FtlConfigInfo();
        ftlConfigInfo.setAuthor((String) YmlUtils.generatorConfig("author"));
        ftlConfigInfo.setDate(StringUtils.getNowDateString());
        ftlConfigInfo.setRemark(tableInfo.getTableRemark());
        ftlConfigInfo.setClassName(tableInfo.getClassName() + FILE_SUFFIX);
        ftlConfigInfo.setTableName(tableInfo.getTableName());
        ftlConfigInfo.setEntityName(StringUtils.firstToLowerCase(tableInfo.getClassName()));
        ftlConfigInfo.setEntityClassName(StringUtils.firstToUpperCase(tableInfo.getClassName()));
        ftlConfigInfo.setPackageName(GeneratorJointUtil.getPackageName(Constant.CONTROLLER));

        FreemarkerConfigUtils.setFtlMVCPath(ftlConfigInfo);
        FileUtil.generateToJava(Prop2MapUtil.prop2Map(ftlConfigInfo), Constant.CONTROLLER);
    }
}
