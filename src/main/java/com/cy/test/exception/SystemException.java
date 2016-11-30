package com.cy.test.exception;

public class SystemException extends BasicException {

	private static final long serialVersionUID = -5383757341406403295L;

	public SystemException(int errorCode) {
		super(errorCode);
	}

	public SystemException(int errorCode, String msg) {
		super(errorCode, msg);
	}

}
