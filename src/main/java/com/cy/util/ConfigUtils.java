/**
 * CopyRight (c) 2016 北京瑰柏科技有限公司 保留所有权利
 */

package com.cy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigUtils {

    private static Logger logger = LogManager.getLogger(ConfigUtils.class);

    /**
     * Description: 加载属性文件，基于类路径下的.properties文件.
     * 
     * @param location
     *        基于类路径下的.properties文件
     * @throws FileException
     *         文件找不到或文件不可读
     * @return 属性
     */
    public static Properties loadProperties(String location)  {
        Properties ps = new Properties();
        if (location != null && !"".equals(location)) {
            ClassLoader cl = getClassLoader();
            InputStream inStream = cl.getResourceAsStream(location);
            if (inStream == null) {
//                throw new FileException(560501, "类路径下找不到文件:" + location);
                logger.error("类路径下找不到文件"+location);
            }
            try {
                ps.load(inStream);
            } catch (IOException e) {
                logger.error("读取文件错误", e);
//                throw new FileException(500501, "读取文件错误");
            }
        }
        return ps;
    }

    /**
     * Description: 取得类加载器
     * 
     * @return
     */
    private static ClassLoader getClassLoader() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = ConfigUtils.class.getClassLoader();
        }
        return cl;
    }

}
