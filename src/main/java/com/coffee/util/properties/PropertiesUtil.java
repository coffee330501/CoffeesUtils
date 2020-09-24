package com.coffee.util.properties;

import com.coffee.util.io.RandomAccessFileExt;

import java.io.File;
import java.util.ResourceBundle;

/**
 * properties配置文件工具类
 * 单例
 *
 * @author objcfeng
 */
public class PropertiesUtil {
    private String propertiesName;
    private static volatile PropertiesUtil propertiesUtil;

    private PropertiesUtil(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    /**
     * 获取工具类实例
     *
     * @param propertiesName propertiesName文件名，无需后缀
     * @return 工具类实例
     */
    public static PropertiesUtil getInstance(String propertiesName) {
        if (propertiesUtil == null) {
            synchronized (PropertiesUtil.class) {
                if (propertiesUtil == null) {
                    propertiesUtil = new PropertiesUtil(propertiesName);
                }
            }
        }
        return propertiesUtil;
    }

    /**
     * 根据键获取值，若值为空，则返回空字符串""
     *
     * @param key 键
     * @return 值
     */
    public String getPropertyValue(String key) {
        //properties文件名
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertiesName);
        return resourceBundle.getString(key);
    }

    /**
     * 修改属性，若value长度小于原值的长度，则
     *
     * @param key   键
     * @param value 值
     */
    public void setPropertyValue(String key, String value) {
        //使用随机访问流
        RandomAccessFileExt randomAccessFile = null;
        //文件路径
        final String path = "src/main/resources/" + propertiesName + ".properties";
        try {
            //采用读写模式
            randomAccessFile = new RandomAccessFileExt(new File(path), "rw");
            //读取到的行
            String line;
            //记录上一次
            long filePointer = 0;
            while ((line = randomAccessFile.readLine()) != null) {
                //将读取到的字符串以=分隔，得到的数组有如下情况 [key,value]、[comment]、[key]
                String[] kv = line.split("=");
                //获取key
                String k = kv[0];
                if (k.trim().equals(key)) {
                    randomAccessFile.seek(filePointer);
                    String writeKV = k + "=" + value;
                    //替换这一行
                    randomAccessFile.replace(writeKV.getBytes(), (int) filePointer, line.getBytes().length);
                    break;
                }
                filePointer = randomAccessFile.getFilePointer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
