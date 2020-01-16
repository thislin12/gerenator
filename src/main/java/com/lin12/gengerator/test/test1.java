package com.lin12.gengerator.test;

import com.lin12.gengerator.entity.FtlConfigInfo;
import com.lin12.gengerator.utils.FileUtil;
import com.lin12.gengerator.utils.Prop2MapUtil;
import com.lin12.gengerator.utils.StringUtils;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

    @Test
    public void test3() throws IOException {
        FtlConfigInfo ftlConfigInfo = new FtlConfigInfo();
        ftlConfigInfo.setClassName("LinUser");
        ftlConfigInfo.setPackageName("com.lin12.gengerator.pojo");
        Map<String, String> map = Prop2MapUtil.prop2Map(ftlConfigInfo);
        System.out.println(map);
    }
}
