package com.eakjb.EakjbData;

public interface IQuery {
	public IDataObject execute();
	public IDataObject execute(boolean tree);
}
