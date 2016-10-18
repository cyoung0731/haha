/**
 * CopyRight (c) 2016 北京瑰柏科技有限公司 保留所有权利
 */

package com.cy.util;

import java.util.Properties;

public class DeviceConstants {

    // public static final String IP_TEST_DY = "http://192.168.18.25/rest/3.0";
    // public static final String IP_TEST_FZ= "http://192.168.18.25";
    // public static final String IP_TEST_DIS = "http://192.168.18.25/rest/3.0";
    public static String getDomain(String app) {
        Properties deviceProperties;
        deviceProperties = ConfigUtils.loadProperties("haha.properties");
        return deviceProperties.getProperty(app);

    }

    public static final String IP_TEST_DY = getDomain("domain.dongya");
    public static final String IP_TEST_FZ = getDomain("domain.fangzhou");
    public static final String IP_TEST_DIS = getDomain("domain.distribute");
}
