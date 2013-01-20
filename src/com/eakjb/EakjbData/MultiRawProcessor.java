package com.eakjb.EakjbData;

import java.util.ArrayList;

public class MultiRawProcessor extends ArrayList<IRawProcessor> implements IRawProcessor {
	
	private static final long serialVersionUID = -4372179354279856863L;

	public String process(String raw) {
		for (IRawProcessor r : this) {
			raw=r.process(raw);
		}
		return raw;
	}
}
