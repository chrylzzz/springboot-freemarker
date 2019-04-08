package com.lnsoft.freemarkerConfig;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chr on 2018/12/18/0018.
 * 自定义指令
 */
@Component
public class RoleDirectiveModel implements TemplateDirectiveModel {
    /**
     * 除了map其他都可以为null
     *
     * @param env      环境变量
     * @param params   指令参数（存储你所需要的值，key-value）
     * @param loopVars 循环遍历
     * @param body     指令内容
     * @throws TemplateException
     * @throws IOException
     */
    @Override
    public void execute(Environment env,//
                        Map params,//入参，都是fm的类型
                        TemplateModel[] loopVars, //返回值
                        TemplateDirectiveBody body) throws TemplateException, IOException {

        System.out.println("===================");


        TemplateScalarModel user = (TemplateScalarModel) params.get("user");
        TemplateScalarModel role = (TemplateScalarModel) params.get("role");

        //boolean
        if ("123456".equals(user.getAsString()) && "admin".equals(role.getAsString())) {
        loopVars[0]=TemplateBooleanModel.TRUE;
        }
        //list
        List<String> otherRights=new ArrayList<>();
        otherRights.add("add");
        otherRights.add("delete");
        loopVars[1]=new SimpleSequence(otherRights);
        //输出
        body.render(env.getOut());

    }
}
