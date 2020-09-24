package com.coffee.util.io;

import java.io.*;

/**
 * RandomAccessFile的扩展类
 * 新增了在文件指定位置插入内容、删除指定位置之后自定义大小的内容、替换内容等功能
 * @author objcfeng
 */
public class RandomAccessFileExt extends RandomAccessFile {
    public RandomAccessFileExt(String name, String mode) throws FileNotFoundException {
        super(name, mode);
    }

    public RandomAccessFileExt(File file, String mode) throws FileNotFoundException {
        super(file, mode);
    }

    /**
     * 功能：在文件指定位置插入内容
     * 实现过程：
     * 将添加位置之后的文件内容使用字节数组保存,
     * 向文件写入插入的内容，再写入字节数组中的内容
     * 注意：
     * 调用此方法后后将文件指针修改到文件结尾
     *
     * @param bytes 添加的内容
     * @param off   添加的位置，按字节算而不是字符
     */
    public void insert(byte[] bytes, int off) throws IOException {
        byte[] dst = getTheRest(off);
        write(bytes);
        write(dst);
        seek(off);
    }

    /**
     * 功能：删除指定位置之后自定义大小的内容
     * 实现过程：
     * 使用字节数组记录off+len之后的内容，
     * 再把字节数组写入off开始的位置，
     * 相当于把off+len之后的内容覆盖在off位置上
     * 最后把文件长度设为原长度-len
     *
     * @param off 删除的位置
     * @param len 删除的字节数
     */
    public void delete(int off, int len) throws IOException {
        byte[] theRest = getTheRest(off + len);
        seek(off);
        write(theRest);
        setLength(length() - len);
        seek(off);
    }

    /**
     * 功能：替换内容
     * 实现过程：先在指定位置删除内容再插入内容
     * @param bytes 替换内容
     * @param off 替换起始点
     * @param len 替换长度
     */
    public void replace(byte[] bytes,int off,int len) throws IOException {
        delete(off, len);
        insert(bytes,off);
    }

    /**
     * 功能：获取off之后剩余的内容，存储在byte数组中
     * 注意：
     * 此方法适用于小文件
     *
     * @param off 位置
     * @return 存储文件剩余的内容的字节数组
     */
    private byte[] getTheRest(int off) throws IOException {
        long fileLength = length();
        if (fileLength > Integer.MAX_VALUE) {
            throw new RuntimeException("文件太大，不支持插入数据");
        }
        //将添加位置之后的文件内容使用字节数组保存
        byte[] dst = new byte[(int) fileLength - off];
        //因为readFully方法是从文件指针之后开始读取的，所以先将指针指向off
        seek(off);
        readFully(dst);
        //将指针指回off位置
        seek(off);
        return dst;
    }
}
