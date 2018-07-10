package com.rhwayfun.spring.boot.custom.schema.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author rhwayfun
 * @since 0.0.1
 */
public class PeopleNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
