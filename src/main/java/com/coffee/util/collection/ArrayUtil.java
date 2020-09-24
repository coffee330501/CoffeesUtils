package com.coffee.util.collection;

/**
 *@description 数组工具类
 *@author objcfeng
 *@date 2020/9/23
 */
public class ArrayUtil {
    /**
     * 功能：合并字节数组
     * @param dest 目标数组
     * @param src1 原数组1
     * @param src2 原数组2
     */
    public static void merge(byte[] dest,byte[] src1,byte[] src2){
        System.arraycopy(src1,0,dest,0,src1.length);
        System.arraycopy(src2,0,dest,src1.length,src2.length);
    }

}
