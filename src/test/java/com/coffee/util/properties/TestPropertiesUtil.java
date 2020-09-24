package com.coffee.util.properties;

import com.coffee.util.properties.PropertiesUtil;
import org.junit.Before;
import org.junit.Test;

public class TestPropertiesUtil {
    private PropertiesUtil propertiesUtil=PropertiesUtil.getInstance("excel");
    @Before
    public void init(){

    }

    @Test
    public void testGetPropertyValue() {
        System.out.println(propertiesUtil.getPropertyValue("tissueBackupNo"));
        System.out.println(propertiesUtil.getPropertyValue("tissueGroup"));
    }

    @Test
    public void testSetPropertyValue() {
        propertiesUtil.setPropertyValue("tissueGroup","1");
    }
}
