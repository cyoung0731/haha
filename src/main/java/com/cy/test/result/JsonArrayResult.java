package com.cy.test.result;

import net.sf.json.JSONArray;

public class JsonArrayResult extends BasicResult{
	
	private JSONArray result;
	
	public JsonArrayResult(int status, JSONArray jsonArray){
		super(status);
		this.setResult(jsonArray);
	}

	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}

}
