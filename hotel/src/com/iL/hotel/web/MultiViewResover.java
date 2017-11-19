package com.iL.hotel.web;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;
import java.util.Map;

public class MultiViewResover implements ViewResolver {

    private Map<String, ViewResolver> resolvers;

    public View resolveViewName(String viewName, Locale locale)
            throws Exception {
        int n = viewName.lastIndexOf("."); // 获取
        // viewName(modelAndView中的名字)看其有没有下划线
        String suffix = "jsp";
        if (n != (-1))  {
            // 有的话截取下划线后面的字符串 这里一般是jsp,ftl,vm与配置文件中的<entry key="ftl">的key匹配
            suffix = viewName.substring(n + 1);
            // 取.前面的部分 那时真正的资源名.比如我们要使用hello.jsp 那viewName就应该是hello
            viewName = viewName.substring(0, n);
        }
        // 根据.后面的字符串去获取托管的视图解析类对象
        ViewResolver resolver = resolvers.get(suffix);

        if (resolver != null)
            return resolver.resolveViewName(viewName, locale);
        return null;
    }

    public Map<String, ViewResolver> getResolvers() {
        return resolvers;
    }

    public void setResolvers(Map<String, ViewResolver> resolvers) {
        this.resolvers = resolvers;
    }
}
