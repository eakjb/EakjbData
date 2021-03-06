package com.eakjb.EakjbData.Logging;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

//TODO Update javadoc comments

//Author: Jake Billings
//Date created: 12-13-2012
//Desc: Logs messages.  It currently does little more than println(), but may play a larger role as logging can be adapted to new forms.

public class Logger implements ILogger{
	private PrintStream out;
	private PrintStream err;
	public final String path;
	public final PrintStream fileOut;
	private String app;
	//Creates a logger with default console output
	public Logger() throws FileNotFoundException {
		this("EakjbData.log");
	}
	public Logger(String path) throws FileNotFoundException {
		this(path,"UnknownApp");
	}
	//Creates logger with given output path
	public Logger(String path, String app) throws FileNotFoundException {
		this(System.out,System.err,path,app);
	}
	//Creates a logger with adaptable output streams
	public Logger(PrintStream out, PrintStream err, String path, String app) throws FileNotFoundException {
		this.out=out;
		this.err=err;
		this.path=path;
		this.app=app;
		this.fileOut=new PrintStream(new FileOutputStream(path));
	}
	public void log(Throwable t) {
		log("Throwable detected.  Printing stack trace...", ErrorLevel.ERROR);
		log("Message: "+t.getMessage(),ErrorLevel.ERROR);
		t.printStackTrace(err);
		t.printStackTrace(fileOut);
	}
	public void log(String message) {
		log(message,ErrorLevel.DEBUG);
	}
	//Log a message to console or other source later in deveolopment
	public void log(String message, ErrorLevel level) {
		String output="["+getDate()+"] ["+app+"] ["+level+"] "+message;
		if (level.error_level > 3) {
			err.println(output);
		} else {
			out.println(output);
		}
		fileOut.println(output);
		fileOut.flush();
	}
	public String getDate() {
		Date date = new Date();
		return date.toString();
	}
	@Override
	public String getApp() {
		return app;
	}
	@Override
	public void setApp(String app) {
		this.app = app;
	}
}
