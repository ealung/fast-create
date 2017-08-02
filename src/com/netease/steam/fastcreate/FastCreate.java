package com.netease.steam.fastcreate;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchanglu on 2017/7/11
 * email hzzhangchanglu@corp.netease.com
 */
public class FastCreate extends AnAction {
    private CreateTemplate createJava = new CreateTemplate();
    private Logger logger = Logger.getLogger(FastCreate.class);

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        logger.info("\r\n\r\n--------------------------------start create-------------------------------");
        Project project = anActionEvent.getProject();
        if (null == Logger.file) {
            Logger.file = new File(project.getBasePath());
        }
        Map<String, Object> root = new HashMap<>();
        PsiClass psiClass = getPsiMethodFromContext(anActionEvent);
        String qualifiedName = psiClass.getQualifiedName();
        String packagePath = qualifiedName.substring(0, qualifiedName.indexOf("." + psiClass.getName()));
        root.put("package", packagePath);
        root.put("user", System.getProperty("user.name"));
        root.put("modelName", psiClass.getName());
        root.put("fileds", getAttribute(psiClass));
        VirtualFile data = anActionEvent.getData(PlatformDataKeys.VIRTUAL_FILE);
        String path = data.getPath();
        logger.info("来源类路径:" + data.getPath());
        path = path.substring(0, path.indexOf(psiClass.getName()) - 1);
        root.put("path", path);
        createForTemplate(project, psiClass, root);
        logger.info("\r\n--------------------------------start end-------------------------------");
    }

    private void createForTemplate(Project project, PsiClass psiClass, Map<String, Object> root) {
        //设置模板路径
        //String templatePath = project.getBasePath() + File.separator + "fast-template";
        File file;
        file = new File(project.getBasePath());
        // if (!file.exists()) {
        file = searchTemplate(file);
        //file = new File(project.getBasePath() + File.separator + "resources" + File.separator + "fast-template");
        //}
        boolean isok = true;
        if (null == file) {
            Messages.showMessageDialog("请在项目下增加fast-template目录进行自定义配置", "非法的创建", Messages.getWarningIcon());
            isok = false;
        }
        if (isok && !checkSource(psiClass)) {
            isok = false;
            Messages.showMessageDialog("默认只支持通过ddb Meta进行生成\r\n如果需要自定义请在项目resouces 下增加fast-template目录进行自定义配置", "非法的创建", Messages.getWarningIcon());
        }
        try {
            if (isok) {
                createJava.create(project, root, file);
                Messages.showMessageDialog("创建完成", "创建完成", Messages.getInformationIcon());
            }
        } catch (Exception e) {
            Messages.showMessageDialog(getMessageError(e), "创建失败，请联系作者。", Messages.getErrorIcon());
            logger.info("创建失败", e);
        }
    }

    public File searchTemplate(File file) {

        if (!file.exists()) {
            return null;
        } else {
            if (file.getName().startsWith(".")) {//特殊文件夹不做扫描
                return null;
            }
            if (null == file.listFiles()) {
                return null;
            }
            for (File file1 : file.listFiles()) {
                if (file1.getName().equals("fast-template")) {
                    return file1;
                } else {
                    file1 = searchTemplate(file1);
                    if (null != file1) {
                        return file1;
                    }
                }
            }

        }
        return null;
    }

    private String getMessageError(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            stringBuilder.append(stackTraceElement.toString());
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 格式化名称，使首字母大小写转换
     *
     * @param name
     * @return
     */
    public String formatName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    private boolean checkSource(PsiClass psiClass) {
        return psiClass.getText().contains("@AnnonOfClass");
    }

    /**
     * 获取对应java类型
     *
     * @param psiField
     * @return
     */
    private String getType(PsiField psiField) {
        String text = null;
        try {
            text = psiField.getText();
            if (text.contains("serialVersionUID")) {
                return null;
            }
            text = text.substring(text.lastIndexOf("\n") + 1, text.length());
            String javaKey = "private";
            text = text.substring(text.indexOf(javaKey) + javaKey.length(), text.indexOf(psiField.getName()));
        } catch (Exception e) {
            return text;
        }
        return text.trim();
    }

    private PsiClass getPsiMethodFromContext(AnActionEvent e) {
        PsiElement elementAt = this.getPsiElement(e);
        return elementAt == null ? null : (PsiClass) PsiTreeUtil.getParentOfType(elementAt, PsiClass.class);
    }

    private PsiElement getPsiElement(AnActionEvent e) {
        PsiFile psiFile = (PsiFile) e.getData(LangDataKeys.PSI_FILE);
        Editor editor = (Editor) e.getData(PlatformDataKeys.EDITOR);
        if (psiFile != null && editor != null) {
            int offset = editor.getCaretModel().getOffset();
            return psiFile.findElementAt(offset);
        } else {
            e.getPresentation().setEnabled(false);
            return null;
        }
    }


    private List<Attribute> getAttribute(PsiClass psiClass) {
        List<Attribute> attributes = new ArrayList<>();
        for (PsiField psiField : psiClass.getAllFields()) {
            String type = getType(psiField);
            if (null == type) {
                continue;
            }
            Attribute attribute = new Attribute(psiField.getName(), type);
            attributes.add(attribute);
        }
        return attributes;
    }
}
