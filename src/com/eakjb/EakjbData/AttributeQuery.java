package com.eakjb.EakjbData;

public class AttributeQuery implements IQuery {
	private IDataStructure structure;
	private String type;
	private String value;
	public AttributeQuery(IDataStructure structure, String type, String attribute, String value) {
		this.structure=structure;
		this.type=type;
		this.value=value;
	}
	@Override
	public IDataObject execute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataObject execute(boolean tree) {
		// TODO Auto-generated method stub
		return null;
	}

}
