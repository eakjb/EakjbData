package com.eakjb.EakjbData;

public interface IDataStructure extends IDataObject {
	public Iterable<String> getKeys();
	public Iterable<IDataObject> getObjects();
	public void set(String key, IDataObject o);
	public IDataObject get(String key);
	public void remove(String key);
}
