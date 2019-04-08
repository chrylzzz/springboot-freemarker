package com.lnsoft.freemarkerConfig;

import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chr on 2018/12/18/0018.
 */
public class SortMethod implements TemplateMethodModelEx {
    /**
     * 自定义排序
     * @param arguments
     * @return
     * @throws TemplateModelException
     * arguments多个参数
     * 自定义函数入参出参都是fm的参数类型，先转化为fm的数据模型，
     */
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        //这是fm的类型参数
        SimpleSequence arg0= (SimpleSequence) arguments.get(0);
        List<BigDecimal> list=arg0.toList();
        Collections.sort(list, new Comparator<BigDecimal>() {
            @Override
            public int compare(BigDecimal o1, BigDecimal o2) {
                return o1.intValue()-o2.intValue();//升序
            }
        });
        return list;
    }
}
