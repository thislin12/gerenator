package com.lin12.gengerator.test;

import com.lin12.gengerator.utils.FileUtil;
import com.lin12.gengerator.utils.FreemarkerConfigUtils;
import com.lin12.gengerator.utils.StringUtils;
import com.lin12.gengerator.utils.YmlUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.StringUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * @author lin12
 * @date 2019/12/21
 */
public class test1 {
    public static void main(String[] args) throws IOException, TemplateException {

    }

    @Test
    public void test() throws IOException {
        System.out.println(FileUtil.getBasicProjectPath());
    }

    @Test
    public void test1() throws IOException {
        String s = StringUtils.package2Path("com.lin12.gengerator.pojo");
        System.out.println(s);
    }
}
