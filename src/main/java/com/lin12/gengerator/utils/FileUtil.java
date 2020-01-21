package com.lin12.gengerator.utils;

import com.lin12.gengerator.common.Constant;
import com.lin12.gengerator.common.TypeConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

/**
 * @author lin12
 * @date 2019/12/22
 */
public class FileUtil {


    public static void generateToJava(Map<String, String> data,String pathProp)  {
        String path = YmlUtils.readGeneratorFilePath(pathProp);
        if (path == null){
            System.out.println("Warn : 配置文件中没有找到"+ pathProp +"的生成路径");
            return;
        }
        String javaPath = FileUtil.getSourcePath() + StringUtils.package2Path(path);
        String xmlPath = FileUtil.getResourcePath() + StringUtils.package2Path(path);
        if (!"".equals(YmlUtils.generatorConfig(Constant.CREATE_PACKAGE))){
            String packagePath = (String)YmlUtils.generatorConfig(Constant.CREATE_PACKAGE);
            javaPath += packagePath.toLowerCase() + "\\";
        }

        File file;

        if(path.contains(Constant.RESOURCES)){
            file = new File( xmlPath + data.get("className") + ".xml");
            String namespace = data.get("namespace");
            if (namespace == null){
                throw new RuntimeException("配置 dao 路径 才能 生成Mapper");
            }
            File folder = new File(xmlPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }else {
            file = new File( javaPath + data.get("className") + ".java");
            File folder = new File(javaPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        String ftl = TypeConfig.getFtlMap(pathProp);
        try {
            Template template = FreemarkerConfigUtils.getInstance().getTemplate(ftl);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            writer.flush();
            if (file.exists()) {
                System.err.println("Error: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1) + " 已存在，请手动修改");
                return;
            }
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw, 1024);
            template.process(data, bw);
            fos.close();
            System.out.println("Succeed: 已生成" + data.get("className") + ".java");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExceptionInInitializerError e) {
            System.err.println("Error: 没有找到" + ftl + "的模板文件");
        }

    }

    /**
     * 获取基本的项目路径
     * @return String
     */
    public static String getBasicProjectPath() {
        String file = FileUtil.class.getClassLoader().getResource("").getFile();
        String path = new File(file).getPath() + File.separator;
        return path.substring(0, path.indexOf("target")) + "src" + File.separator + "main" + File.separator;
    }

    /**
     * 获取源码路径
     * @return String
     */
    private static String getSourcePath() {
        return getBasicProjectPath() + "java" + File.separator;
    }

    /**
     * 获取资源文件路径
     * @return String
     */
    private static String getResourcePath() {
        return getBasicProjectPath()  ;
    }


}
