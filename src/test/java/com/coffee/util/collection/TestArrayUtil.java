package com.coffee.util.collection;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *@description 测试数组工具类
 *@author cf
 *@date 2020/9/23
 */
public class TestArrayUtil {
    @Test
    public void testMapMethods() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");
        map.put("e","5");
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("A","A");
        map2.put("B","B");
        map2.put("C","C");
        map2.put("D","D");
        map.putAll(map2);
        Collection<String> values = map.values();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
