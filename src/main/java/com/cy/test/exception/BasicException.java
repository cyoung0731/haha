package com.cy.test.exception;

import com.cy.test.result.ResultCode;

/**
 * 动吖业务异常基类
 * 
 * @author zhangzhan
 *
 */
public class BasicException extends Exception {

	private static final long serialVersionUID = -8954757564796420359L;

	// 错误编码
	private int errorCode;

	public BasicException(int errorCode) {
		super(ResultCode.getMsg(errorCode));
		this.errorCode = errorCode;
	}

	public BasicException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
