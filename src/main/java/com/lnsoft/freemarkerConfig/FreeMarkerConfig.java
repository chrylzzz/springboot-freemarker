package com.lnsoft.freemarkerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by chr on 2018/12/18/0018.
 */
@Configuration
public class FreeMarkerConfig {

    @Qualifier("freeMarkerConfiguration")
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected RoleDirectiveModel roleDirectiveModel;
    @PostConstruct
    public void setSharedVariable() {
        // role即为页面上调用的标签名
        configuration.setSharedVariable("role", roleDirectiveModel);
    }
}