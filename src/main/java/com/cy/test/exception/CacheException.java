package com.cy.test.exception;

public class CacheException extends BusinessException {

	private static final long serialVersionUID = -4724493929362505520L;

	public CacheException(int errorCode) {
		super(errorCode);
	}
	
	public CacheException(int errorCode, String msg) {
		super(errorCode, msg);
	}

}
