package com.coffee.util.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *@description 测试类
 *@author cf
 *@date 2020/9/22
 */
public class TestRandomAccessFileExt {
    private RandomAccessFileExt randomAccessFileExt=null;
    @Before
    public void init() throws FileNotFoundException {
         randomAccessFileExt = new RandomAccessFileExt(new File("src/main/resources/excel.properties"), "rw");
    }
    @Test
    public void testLength() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("src/main/resources/test.properties"),"rw");
        byte[] bytes = new byte[28];
        randomAccessFile.readFully(bytes);
        System.out.println(new String(bytes,"utf8"));
        System.out.println(randomAccessFile.length());
        randomAccessFile.close();
    }

    @Test
    public void testInsert() throws Exception{

        byte[] bytes = "你好啊".getBytes();
        randomAccessFileExt.insert(bytes,10);
    }

    @Test
    public void testDelete() throws IOException {
        randomAccessFileExt.delete(0,10);
    }
    @Test
    public void testDelete2() throws IOException {
//        System.out.println(randomAccessFileExt.readLine().getBytes().length);
//        randomAccessFileExt.delete(5);
    }

    @Test
    public void testReplace() throws IOException {
//        randomAccessFileExt.replace("哈喽啊".getBytes(),1);
    }

    @Test
    public void testGetTheRest() throws IOException {
//        File testTmp = File.createTempFile("testTmp", ".properties", new File(System.getProperty("user.dir")));
    }
    @After
    public void close() throws IOException {
        if (randomAccessFileExt!=null){
            randomAccessFileExt.close();
        }
    }
}
