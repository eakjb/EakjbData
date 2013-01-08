package com.eakjb.EakjbData.DataObjects;

import com.eakjb.EakjbData.IDataObject;

public class TextDataObject implements IDataObject {
	private String text;
	private String type;
	public TextDataObject(String type,String text) {
		this.setType(type);
		this.text=text;
	}

	@Override
	public String getTextValue() {
		return text;
	}

	@Override
	public boolean isStructure() {
		return false;
	}
	
	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
