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

    public EntityTask(ClassConfigInfo classConfigInfo) {
        this.classConfigInfo = classConfigInfo;
    }

    /**
     * 获取模板预编译文本map
     * @return Map<String, String>
     */
    public Map<String, String> getTemplateData(){
        Map<String, String> dataMap = new HashMap<String, String>(10);
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
    public static void getEntityClassConfig(TableInfo tableInfo){
        List<String> list = new ArrayList<String>();
        if(YmlUtils.isMybatisPlus()){
            list.add("@TableName(value = \"" + tableInfo.getTableName() + "\")");
        }
        if(YmlUtils.isSwagger()){
            list.add("@ApiModel(value=\"" + tableInfo.getClassName() + "\", description=\"\")");
        }
        String classHeadAnnotation = StringUtils.headAnnotationLineFeed(list);
        ClassConfigInfo classConfigInfo = new ClassConfigInfo(tableInfo.getClassName(),
                GeneratorJointUtil.getPackageName(Constant.ENTITY),
                GeneratorJointUtil.getImportList(tableInfo.getColumnInfos()),
                GeneratorJointUtil.getClassHeadRemark(),
                classHeadAnnotation,
                GeneratorJointUtil.getPropertyList(tableInfo.getColumnInfos()));
        FileUtil.generateToJava(new EntityTask(classConfigInfo).getTemplateData(), Constant.ENTITY);
    }
}
