/**
 * CopyRight (c) 2016 北京瑰柏科技有限公司 保留所有权利
 */
package com.cy.test.exception;


/**
 * 
 * @author  luandy
 * @version 1.0.0.2016年3月23日
 */
public class FileException extends SystemException {

    /**  */
    private static final long serialVersionUID = -6914981011600398586L;
    /**
     * @param errorCode
     */
    public FileException(int errorCode) {
        super(errorCode);
    }
    public FileException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}
