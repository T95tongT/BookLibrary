package com.example.demo.config;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

public class FreeMarkerUtils {
    private static Configuration cfg=null;
    public static Configuration buildConfiguration(){
        cfg=new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDefaultEncoding("utf-8");
        String path=FreeMarkerUtils.class.getResource("/").getPath();
        File file=new File(path+File.separator+"templates");
        try {
            cfg.setDirectoryForTemplateLoading(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }
    public static Template getTemplate(String ftiName){
        Template template=null;
        try {
            template=buildConfiguration().getTemplate(ftiName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return template;
    }
    public static void forward(HttpServletResponse response,
                                 String target,
                                 HashMap vmap){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        Template template=getTemplate(target);
        PrintWriter out=null;
        try {
            out=response.getWriter();
            template.process(vmap,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }
}
