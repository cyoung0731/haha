package com.cy.test.result;

import net.sf.json.JSONObject;

public class JsonObjectResult extends BasicResult {
	
	private JSONObject result;
	
	public JsonObjectResult(int status, JSONObject jsonObject){
		super(status);
		this.result = jsonObject;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	};	
}
