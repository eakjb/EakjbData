package com.eakjb.EakjbData;


import com.eakjb.EakjbData.DataObjects.TextDataObject;
import com.eakjb.EakjbData.Logging.DummyLogger;
import com.eakjb.EakjbData.Logging.ILogger;

public class AttributeQuery extends Query {
	
	private String attr;
	
	public AttributeQuery(IDataStructure s,String type,String attr,IDataObject value,ILogger logger) {
		super(s,type,value,logger);
		this.attr=attr;
	}
	public AttributeQuery(IDataStructure s,String type,String attr,IDataObject value) {
		this(s,type,attr,value,new DummyLogger());
	}
	public AttributeQuery(IDataStructure s,String type,String attr,String value,ILogger logger) {
		this(s,type,attr,new TextDataObject("AttrQuery",value),logger);
	}
	public AttributeQuery(IDataStructure s,String type,String attr,String value) {
		this(s,type,attr,value,new DummyLogger());
	}
	@Override
	protected boolean isMatch(String key, IDataObject o) {
		return key.equals(type)&&o.isStructure()&&((IDataStructure) o).get(attr).getTextValue().equals(value.getTextValue());
	}
}
