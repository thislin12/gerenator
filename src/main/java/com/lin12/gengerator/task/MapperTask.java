package com.lin12.gengerator.task;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.FtlConfigInfo;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.utils.FileUtil;
import com.lin12.gengerator.utils.Prop2MapUtil;
import com.lin12.gengerator.utils.YmlUtils;

/**
 * @author lin12
 * @date 2020/1/15
 */
public class MapperTask  {

     private static final String FILE_SUFFIX = "Mapper";

    /**
     * 配置实体类 并创建文件
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        FtlConfigInfo ftlConfigInfo = new FtlConfigInfo();
        ftlConfigInfo.setClassName(tableInfo.getClassName() + FILE_SUFFIX);
        ftlConfigInfo.setNamespace(YmlUtils.readGeneratorFilePath(Constant.DAO) + "." + ftlConfigInfo.getClassName() + DaoTask.FILE_SUFFIX);
        FileUtil.generateToJava(Prop2MapUtil.prop2Map(ftlConfigInfo), Constant.MAPPER);
    }
}
