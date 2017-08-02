package com.netease.steam.fastcreate;

import java.io.*;

/**
 * Created by zhangchanglu on 2017/7/12
 * email hzzhangchanglu@corp.netease.com
 */
public class Logger {
    private static String path = ".";
    private FileOutputStream fileOutputStream;
    public static File file;
    private Class aClass;

    private Logger(Class aClass) {
        this.aClass=aClass;
    }

    public static Logger getLogger(Class aClass) {
        return new Logger(aClass);
    }

    public void info(String str, Exception e) {
        try {
            str = aClass.getName() + " | " + str;
            str += "\r\n";
            log(str);
            OutputStream outputStream = getLogOutputStream();
            e.printStackTrace(new PrintStream(outputStream, true, "UTF-8"));
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }

    public void info(String str) {
        str = aClass.getName() + " | " + str;
        str += "\r\n";
        log(str);
    }

    private void log(String str) {
        try {
            OutputStream outputStream = getLogOutputStream();
            System.out.println(str);
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OutputStream getLogOutputStream() {
        try {
            File log = getLogPath();
            if (null == fileOutputStream) {
                fileOutputStream = new FileOutputStream(log);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileOutputStream;
    }

    public File getLogPath() throws IOException {
        if (null == file) {
            file = new File(path + File.separator + "fastCreate" + File.separator + "/info.log");
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
