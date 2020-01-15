package com.lin12.gengerator.task;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.ClassConfigInfo;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.utils.FileUtil;
import com.lin12.gengerator.utils.GeneratorJointUtil;
import com.lin12.gengerator.utils.StringUtils;
import com.lin12.gengerator.utils.YmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lin12
 * @date 2020/1/15
 */
public class MapperTask  {

    private ClassConfigInfo classConfigInfo;

    public MapperTask(ClassConfigInfo classConfigInfo) {
        this.classConfigInfo = classConfigInfo;
    }

    public static final String FILE_SUFFIX = "Mapper";

    /**
     * 获取模板预编译文本map
     * @return Map<String, String>
     */
    public Map<String, String> getTemplateData(){
        Map<String, String> dataMap = new HashMap<String, String>(6);
        dataMap.put("ClassName", classConfigInfo.getClassName() + FILE_SUFFIX);
        dataMap.put("Namespace", YmlUtils.readGeneratorFilePath(Constant.DAO) + "." + classConfigInfo.getClassName() + DaoTask.FILE_SUFFIX);
        return dataMap;
    }

    /**
     * 获取mapper配置实体类
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        ClassConfigInfo classConfigInfo = new ClassConfigInfo(tableInfo.getClassName(),
                GeneratorJointUtil.getPackageName(Constant.MAPPER),
                GeneratorJointUtil.getImportList(tableInfo.getColumnInfos()),
                GeneratorJointUtil.getClassHeadRemark(tableInfo.getTableRemark()),
                null,
                GeneratorJointUtil.getPropertyList(tableInfo.getColumnInfos()));
        FileUtil.generateToJava(new MapperTask(classConfigInfo).getTemplateData(), Constant.MAPPER);
    }
}
