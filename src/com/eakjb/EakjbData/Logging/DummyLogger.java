package com.eakjb.EakjbData.Logging;
/**
 * @author Jake Billings
 * Date Created: 01-06-2013
 * Desc: Empty logging methods that do nothing.  Mainly used for constructors without args
 *
 */
public class DummyLogger implements ILogger {

	@Override
	/**
	 * Doesn't log a string
	 * @param String s a string to log
	 */
	public void log(String s) {
		//Do nothing
	}

	@Override
	/**
	 * Doesn't log a string with an error level
	 * @param String s the string to log
	 * @param ErrorLevel e the severity of the log message
	 */
	public void log(String s, ErrorLevel e) {
		// Do nothing
	}

	@Override
	/**
	 * Doesn't log a stacktrace of a throwable
	 * @param Throwable t throwable to log
	 */
	public void log(Throwable t) {
		//Do nothing
	}

	@Override
	public String getApp() {
		return "Dummy";
	}

	@Override
	public void setApp(String app) {
		//Do nothing
	}

}
