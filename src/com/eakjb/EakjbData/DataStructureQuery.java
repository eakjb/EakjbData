package com.eakjb.EakjbData;

import java.util.Iterator;

public class DataStructureQuery implements IQuery {

	private IDataStructure structure;
	private String type;
	private String value;

	public DataStructureQuery(IDataStructure s,String type,String value) {
		this.structure=s;
		this.type=type;
		this.value=value;
	}

	public DataStructureQuery(IDataStructure s, String type) {
		this(s,type,"");
	}

	private IDataStructure queryLevel(IDataStructure s) {
		IDataStructure result = null;
		Iterator<String> i = s.getKeys().iterator();
		while (i.hasNext()) {
			String key = i.next();
			IDataObject o = s.get(key);
			if (key.equals(type)) {
				if (o.getTextValue().equals(value)||value==null||value=="") {
					if (result==null) {
						result=new HashMapDataStructure();
					}
					String id=key;
					int idi = 1;
					while (result.containsKey(id)) {
						id=key+idi;
						idi++;
					}
					result.set(id, o);
				}
			} else {
				if (o.isStructure()) {
					IDataStructure sub = queryLevel((IDataStructure) o);
					if (sub!=null) {
						if (result==null) {
							result=new HashMapDataStructure();
						}
						String id=key;
						int idi = 1;
						while (result.containsKey(id)) {
							id=key+idi;
							idi++;
						}
						result.set(id, sub);						
					}
				}
			}
		}
		return result;
	}

	@Override
	public IDataStructure execute() {
		return queryLevel(structure);
	}

}
