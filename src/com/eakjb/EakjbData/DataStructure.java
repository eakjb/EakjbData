package com.eakjb.EakjbData;

public abstract class DataStructure implements IDataStructure {
	private String splitter="_";
	@Override
	public String genId(String oid) {
		String id =oid;
		int idi = 1;
		while (containsKey(id)) {
			id=oid+splitter+idi;
			idi++;
		}
		return id;
	}
	
	@Override
	public String splitId(String id) {
		int spaceIndex = id.indexOf(splitter);
		if (spaceIndex != -1)
		{
		    id = id.substring(0, spaceIndex);
		}
		return id;
	}
}
