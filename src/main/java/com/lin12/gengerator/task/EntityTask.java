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
 * @date 2019/12/23
 */
public class EntityTask {

    private ClassConfigInfo classConfigInfo;

    private EntityTask(ClassConfigInfo classConfigInfo) {
        this.classConfigInfo = classConfigInfo;
    }

    /**
     * 获取模板预编译文本map
     * @return Map<String, String>
     */
    private Map<String, String> getTemplateData(){
        Map<String, String> dataMap = new HashMap<String, String>(6);
        dataMap.put("ClassName", classConfigInfo.getClassName());
        dataMap.put("PackageName", classConfigInfo.getPackageName());
        dataMap.put("ImportPackageList", classConfigInfo.getImportPackageList());
        dataMap.put("ClassHeadRemark", classConfigInfo.getClassHeadRemark());
        dataMap.put("PropertyList", classConfigInfo.getPropertyList());
        dataMap.put("ClassHeadAnnotation", classConfigInfo.getClassHeadAnnotation());
        return dataMap;
    }

    /**
     * 获取entity配置实体类
     * @param tableInfo tableInfo
     */
    public static void getClassConfig(TableInfo tableInfo){
        List<String> list = new ArrayList<String>(2);
        if(YmlUtils.isMybatisPlus()){
            list.add("@TableName(value = \"" + tableInfo.getTableName() + "\")");
        }
        if(YmlUtils.isSwagger()){
            list.add("@ApiModel(value=\"" + tableInfo.getClassName() + "\", description=\"\")");
        }
        String classHeadAnnotation = StringUtils.headAnnotationLineFeed(list);
        //设置 配置 ftl模板 预编译文本属性
        ClassConfigInfo classConfigInfo = new ClassConfigInfo();
        classConfigInfo.setClassName(tableInfo.getClassName());
        classConfigInfo.setPackageName(GeneratorJointUtil.getPackageName(Constant.ENTITY));
        classConfigInfo.setImportPackageList(GeneratorJointUtil.getImportList(tableInfo.getColumnInfos()));
        classConfigInfo.setClassHeadRemark(GeneratorJointUtil.getClassHeadRemark(tableInfo.getTableRemark()));
        classConfigInfo.setClassHeadAnnotation(classHeadAnnotation);
        classConfigInfo.setPropertyList(GeneratorJointUtil.getPropertyList(tableInfo.getColumnInfos()));
        FileUtil.generateToJava(new EntityTask(classConfigInfo).getTemplateData(), Constant.ENTITY);
    }
}
