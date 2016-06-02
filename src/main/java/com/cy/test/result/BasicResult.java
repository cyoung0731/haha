package com.cy.test.result;

public class BasicResult {
	
	public int status;
	
	public String msg;
	
	public BasicResult(int status){
		this.status = status;
//		this.msg = ResultCode.getMsg(status);
		this.msg = "test";
	}
	
	public BasicResult(int status, String msg){
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
