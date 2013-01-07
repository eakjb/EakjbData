package com.eakjb.EakjbData.DataAdapters;

import com.eakjb.EakjbData.IDataAdapter;
import com.eakjb.EakjbData.Logging.DummyLogger;
import com.eakjb.EakjbData.Logging.ILogger;

public abstract class DataAdapter implements IDataAdapter {
	public ILogger logger;
	public DataAdapter(ILogger l) {
		this.logger=l;
	}
	public DataAdapter() {
		this(new DummyLogger());
	}
	public String stripString(String s) {
		s=s.replaceAll("\n", "");
		s=s.replaceAll("\t", "");
		return s;
	}
}
