package com.eakjb.EakjbData.DataObjects;

import com.eakjb.EakjbData.IDataObject;

public class TextDataObject implements IDataObject {
	private String text;
	public TextDataObject(String t) {
		this.text=t;
	}

	@Override
	public String getTextValue() {
		return text;
	}

	@Override
	public boolean isStructure() {
		return false;
	}

}
