package com.eakjb.EakjbData;

public class StringReplacer implements IRawProcessor {
	private String old;
	private String iNew;
	public StringReplacer(String old,String iNew) {
		this.old=old;
		this.iNew=iNew;
	}
	@Override
	public String process(String raw) {
		return raw.replaceAll(old,iNew);
	}

}
