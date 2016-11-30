/**
 * CopyRight (c) 2016 北京瑰柏科技有限公司 保留所有权利
 */
package com.cy.test.exception;


/**
 * 
 * @author  luandy
 * @version 1.0.0.2016年3月23日
 */
public class RedisException extends SystemException {

    /**  */
    private static final long serialVersionUID = 2012222051213155448L;

    /**
     * @param errorCode
     */
    public RedisException(int errorCode) {
        super(errorCode);
    }
    
    public RedisException(int errorCode, String msg) {
        super(errorCode, msg);
    }

}
