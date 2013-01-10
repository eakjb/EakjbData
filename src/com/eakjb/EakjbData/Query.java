package com.eakjb.EakjbData;

import java.util.Iterator;

import com.eakjb.EakjbData.Logging.ILogger;

public abstract class Query implements IQuery {
	
	protected String type;
	protected IDataObject value;
	protected ILogger logger;
	private IDataStructure structure;
	
	public Query(IDataStructure s,String type,IDataObject value,ILogger logger) {
		this.structure=s;
		this.type=type;
		this.value=value;
		this.logger=logger;
	}
	
	@Override
	public IDataObject execute(boolean tree) {
		logger.log("Executing query...");
		IDataStructure result = queryLevel(structure,tree);
		if (result.size()<1) {
			return null;
		} else if (result.size()<2) {
			return result.getObjects().iterator().next();
		} else {
			return result;
		}
	}
	
	@Override
	public IDataObject execute() {
		return execute(false);
	}
	
	protected IDataStructure queryLevel(IDataStructure s, boolean tree) {
		IDataStructure result = new HashMapDataStructure("DataQueryTree");
		Iterator<String> i = s.getKeys().iterator();
		while (i.hasNext()) {
			String key = i.next();
			IDataObject o = s.get(key);
			key=s.splitId(key);
			if (isMatch(key,o)) {
				result.set(result.genId(key), o);
			} else {
				if (o.isStructure()) {
					IDataStructure sub = queryLevel((IDataStructure) o, tree);
					if (sub!=null&&sub.size()>0) {
						if (tree) {
							result.set(result.genId(key), sub);
						} else {
							result=sub;
						}
					}
				}
			}
		}
		return result;
	}
	protected abstract boolean isMatch(String key,IDataObject o);
}
