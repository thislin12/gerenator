package com.lin12.gengerator.utils;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.entity.FtlConfigInfo;
import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * @author lin12
 * @date 2019/12/22
 */
public class FreemarkerConfigUtils {
    private static String path = new File(FreemarkerConfigUtils.class.getClassLoader().getResource("generator").getFile()).getPath();
    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            try {
                if (path.contains("jar")){
                    configuration.setClassForTemplateLoading(FreemarkerConfigUtils.class, "/generator");
                } else {
                    configuration.setDirectoryForTemplateLoading(new File(path));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setEncoding(Locale.CHINA, "utf-8");
        }
        return configuration;
    }

    public static void setFtlMVCPath(FtlConfigInfo ftlConfigInfo){
        ftlConfigInfo.setEntityPath(YmlUtils.readGeneratorFilePath(Constant.ENTITY));
        ftlConfigInfo.setControllerPath(YmlUtils.readGeneratorFilePath(Constant.CONTROLLER));
        ftlConfigInfo.setServicePath(YmlUtils.readGeneratorFilePath(Constant.SERVICE));
        ftlConfigInfo.setServiceImplPath(YmlUtils.readGeneratorFilePath(Constant.SERVICE_IMPL));
        ftlConfigInfo.setDaoPath(YmlUtils.readGeneratorFilePath(Constant.DAO));
    }
}
