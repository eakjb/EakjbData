package com.eakjb.EakjbData.Logging;

public interface ILogger {
	public void log(String s);
	public void log(String s, ErrorLevel e);
	public void log(Throwable t);
}
