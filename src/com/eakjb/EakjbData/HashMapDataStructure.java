package com.eakjb.EakjbData;

import java.util.HashMap;

import com.eakjb.EakjbData.Logging.DummyLogger;
import com.eakjb.EakjbData.Logging.ErrorLevel;
import com.eakjb.EakjbData.Logging.ILogger;

public class HashMapDataStructure implements IDataStructure {
	private ILogger logger;
	private HashMap<String, IDataObject> map;
	public HashMapDataStructure(ILogger l) {
		this.logger=l;
		this.map=new HashMap<String,IDataObject>();
	}
	public HashMapDataStructure() {
		this(new DummyLogger());
	}
	
	@Override
	public String getTextValue() {
		// TODO Auto-generated method stub
		logger.log("HashMapDataStructure getTextValue() not implemented yet!", ErrorLevel.WARNING);
		return "Multiple objects.";
	}

	@Override
	public Iterable<String> getKeys() {
		return map.keySet();
	}

	@Override
	public Iterable<IDataObject> getObjects() {
		return map.values();
	}

	@Override
	public IDataObject get(String key) {
		return map.get(key);
	}

	@Override
	public void remove(String key) {
		map.remove(key);

	}
	
	@Override
	public void set(String key, IDataObject o) {
		map.put(key, o);		
	}
	
	public HashMap<String, IDataObject> getMap() {
		return map;
	}
	@Override
	public boolean isStructure() {
		return true;
	}

}
