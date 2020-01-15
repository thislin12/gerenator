package com.lin12.gengerator.task;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.ClassConfigInfo;
import com.lin12.gengerator.entity.TableInfo;
import com.lin12.gengerator.utils.FileUtil;
import com.lin12.gengerator.utils.GeneratorJointUtil;
import com.lin12.gengerator.utils.MethodUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lin12
 * @date 2020/1/15
 */
public class ServiceTask {

    private ClassConfigInfo classConfigInfo;

    private ServiceTask(ClassConfigInfo classConfigInfo) {
        this.classConfigInfo = classConfigInfo;
    }

    private static final String FILE_SUFFIX = "Service";


    /**
     * 获取模板预编译文本map
     * @return Map<String, String>
     */
    private Map<String, String> getTemplateData(){
        Map<String, String> dataMap = new HashMap<String, String>(6);
        dataMap.put("ClassName", classConfigInfo.getClassName() + FILE_SUFFIX);
        dataMap.put("PackageName", classConfigInfo.getPackageName());
        dataMap.put("ClassHeadRemark", classConfigInfo.getClassHeadRemark());
        dataMap.put("Methods",classConfigInfo.getMethodList());
        return dataMap;
    }

    /**
     * 获取dao配置实体类
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        ClassConfigInfo classConfigInfo = new ClassConfigInfo();
        classConfigInfo.setMethodList(MethodUtils.getServiceMethod(tableInfo));
        classConfigInfo.setClassName(tableInfo.getClassName() );
        classConfigInfo.setPackageName(GeneratorJointUtil.getPackageName(Constant.SERVICE));
        classConfigInfo.setClassHeadRemark(GeneratorJointUtil.getClassHeadRemark(tableInfo.getTableRemark() + FILE_SUFFIX));
        FileUtil.generateToJava(new ServiceTask(classConfigInfo).getTemplateData(), Constant.SERVICE);
    }
}
