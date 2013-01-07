package com.eakjb.EakjbData;

public interface IDataAdapter {
	/**
	 * Converts a raw data string to an IDataStructure object
	 *  
	 * @param o the raw object to convert
	 * @return the processed object to use
	 * @throws Exception 
	 */
	public IDataObject rawToObject(String data) throws Exception;
	/**
	 * Converts a com.grademaster.IDataObject to raw data for export or saving
	 * @param o The object to convert
	 * @return the raw string to export
	 * @throws Exception I was lazy, so a threw everything
	 */
	public String objectToRaw(IDataObject data) throws Exception;
}
