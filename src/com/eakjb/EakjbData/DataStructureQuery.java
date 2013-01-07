package com.eakjb.EakjbData;

import java.util.Iterator;

public class DataStructureQuery implements IQuery {
	
	private IDataStructure structure;
	private String type;
	private String value;
	private boolean multiple;
	
	public DataStructureQuery(IDataStructure s,String type,String value,boolean multiple) {
		this.structure=s;
		this.type=type;
		this.value=value;
		this.multiple=multiple;
	}
	
	public DataStructureQuery(IDataStructure s, String type,boolean multiple) {
		this(s,type,"", multiple);
	}
	
	public DataStructureQuery(IDataStructure s, String type) {
		this(s,type,false);
	}
	
	private IDataStructure queryLevel(IDataStructure s) {
		IDataStructure result = null;
		Iterator<String> i = s.getKeys().iterator();
		while (i.hasNext()) {
			String key = i.next();
			IDataObject o = s.get(key);
			if (key.equals(type)) {
				if (o.getTextValue().equals(value)||value==null||value=="") {
					//TODO Unique ID, add to result structure, handle IDataStructures, handle multiples, return
				}
			if (o.isStructure()) {
				queryLevel((IDataStructure) o);
			} else {
				
			}
			}
		}
		return result;
	}
	
	@Override
	public IDataObject execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
