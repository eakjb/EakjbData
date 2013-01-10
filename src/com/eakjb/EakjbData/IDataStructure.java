package com.eakjb.EakjbData;

public interface IDataStructure extends IDataObject,Iterable<IDataObject> {
	public Iterable<String> getKeys();
	public Iterable<IDataObject> getObjects();
	public void set(String key, IDataObject o);
	public void set(String key, String value);
	public IDataObject get(String key);
	public void remove(String key);
	public boolean containsObject(IDataObject o);
	public boolean containsKey(String key);
	public int size();
	public String genId(String start);
	public String splitId(String id);
}
