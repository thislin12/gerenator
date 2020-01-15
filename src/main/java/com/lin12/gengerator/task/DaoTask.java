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
 * @date 2020/1/7
 */
public class DaoTask {

    private ClassConfigInfo classConfigInfo;

    public DaoTask(ClassConfigInfo classConfigInfo) {
        this.classConfigInfo = classConfigInfo;
    }

    public static final String FILE_SUFFIX = "Dao";

    /**
     * 获取模板预编译文本map
     * @return Map<String, String>
     */
    public Map<String, String> getTemplateData(){
        Map<String, String> dataMap = new HashMap<String, String>(6);
        dataMap.put("ClassName", classConfigInfo.getClassName());
        dataMap.put("PackageName", classConfigInfo.getPackageName());
        dataMap.put("ClassHeadRemark", classConfigInfo.getClassHeadRemark());
        dataMap.put("ImportPackageList", classConfigInfo.getImportPackageList());
        int i = classConfigInfo.getClassName().indexOf(FILE_SUFFIX);
        dataMap.put("BaseMapperType", classConfigInfo.getClassName().substring(0, i));
        return dataMap;
    }

    /**
     * 获取dao配置实体类
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        ClassConfigInfo classConfigInfo = new ClassConfigInfo(tableInfo.getClassName() + FILE_SUFFIX,
                GeneratorJointUtil.getPackageName(Constant.DAO),
                GeneratorJointUtil.getImportList(tableInfo.getColumnInfos()),
                GeneratorJointUtil.getClassHeadRemark(tableInfo.getTableRemark() + FILE_SUFFIX),
                null,
                GeneratorJointUtil.getPropertyList(tableInfo.getColumnInfos()));
        FileUtil.generateToJava(new DaoTask(classConfigInfo).getTemplateData(), Constant.DAO);
    }
}
