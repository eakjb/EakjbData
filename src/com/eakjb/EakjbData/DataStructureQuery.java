package com.eakjb.EakjbData;


import com.eakjb.EakjbData.Logging.DummyLogger;
import com.eakjb.EakjbData.Logging.ILogger;

public class DataStructureQuery extends Query {
	
	public DataStructureQuery(IDataStructure s,String type,IDataObject value,ILogger logger) {
		super(s,type,value,logger);
	}
	public DataStructureQuery(IDataStructure s, String type, ILogger logger) {
		this(s,type,null,logger);
	}
	
	public DataStructureQuery(IDataStructure s, String type) {
		this(s,type,new DummyLogger());
	}

	public boolean isMatch(String key, IDataObject o) {
		return (key.equals(type)&&(o.equals(value)||"".equals(value)||value==null));
	}

}
