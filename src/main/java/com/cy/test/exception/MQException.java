/**
 * CopyRight (c) 2016 北京瑰柏科技有限公司 保留所有权利
 */
package com.cy.test.exception;


/**
 * 
 * @author  luandy
 * @version 1.0.0.2016年3月23日
 */
public class MQException extends SystemException {

    /**  */
    private static final long serialVersionUID = -8100067826921194993L;

    /**
     * @param errorCode
     */
    public MQException(int errorCode) {
        super(errorCode);
    }
    
    public MQException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}
