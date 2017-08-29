package com.netease.steam.fastcreate;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by zhangchanglu on 2017/7/12
 * email hzzhangchanglu@corp.netease.com
 */
public class CreateTemplate {

    /**
     * 根据模板创建
     *
     * @param root
     */
    public void create(Project project, Map<String, Object> root, File file) throws Exception {
        if (null == file) {//只有默认的才进行创建转换类
            Messages.showMessageDialog("请在项目resouces 下增加fast-template目录进行自定义配置", "非法的创建", Messages.getWarningIcon());
        } else {
            for (File file1 : file.listFiles()) {
                if (file1.isDirectory()) {
                    continue;
                }
                try {
                    createJava(root, file, file1.getName());
                } catch (Exception e) {
                    Messages.showMessageDialog("模板错误，请核查是否是合法的freeMark模板"+e.getMessage(), "非法的创建", Messages.getWarningIcon());
                }
            }
        }

    }

    private void createForTemplate(Map<String, Object> root, File file, String templateName) throws Exception {
        String createName = root.get("modelName").toString();
        createName += templateName.substring(0, templateName.lastIndexOf("."));
        createForTemplate(root, file, templateName, createName);
    }

    /**
     * 根据模板创建文件
     *
     * @param root         模板所需参数
     * @param file         模板路径
     * @param templateName 模板名称
     * @param createName   生成的文件名称
     */
    private void createForTemplate(Map<String, Object> root, File file, String templateName, String createName) throws Exception {
        if (file.exists()) {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(file);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setTemplateUpdateDelay(1);

            Template daoImplTemplate = cfg.getTemplate(templateName, "UTF-8");
            String path = root.get("path") + File.separator;
            if (null != createName) {
                path += createName + ".java";
                root.put("className", createName);
            }
            daoImplTemplate.process(root, new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8"));
        }
    }

    /**
     * 根据模板创建java文件
     *
     * @param root         模板参数
     * @param file         模板路径
     * @param templateName 模板名称
     */
    private void createJava(Map<String, Object> root, File file, String templateName) throws Exception {
        createForTemplate(root, file, templateName);
    }

}
