package com.eakjb.EakjbData;

import java.io.IOException;

import com.eakjb.EakjbData.Logging.DummyLogger;
import com.eakjb.EakjbData.Logging.ErrorLevel;
import com.eakjb.EakjbData.Logging.ILogger;

/**
 * DataInterface provides the cache handling and misc. functions of a data interface that would be the same across all IDataInterfaces
 * 
 * Author: Jake Billings
 * Date created: 12-13-2012
 * Desc: A very basic implementation of IDataInterface that leaves most of the code to subclasses.
 * @author jake
 *
 */


public class DataInterface implements IDataInterface {

	//See IRawLoader.java
	/**
	 * The IRawLoader that loads raw data for the DataInterface as a String.
	 * See IRawLoader and RawLocalLoader
	 */
	public IRawLoader loader;
	
	//Data adapter for converting raw input to data
	/**
	 * The IDataAdapter that converts data from its raw data format to a com.grademaster.data.object
	 */
	public IDataAdapter adapter;
	
	//True=Caching on; False = caching off
	/**
	 * True=cached DataInterface; False=non-cached DataInterface
	 * Caching uses slightly more memory, but improves performance.  It may lead to confusing IO errors.
	 * No cachcing increases ease of coding and decreases memory usage, but leads to more IO total.
	 */
	public boolean cached = true;
	
	//True when initial data has been loaded and cached
	/**
	 * For internal use, tells whether or not a cache has been loaded at all
	 */
	protected boolean cacheLoaded = false;
	
	//Stores cached objects if caching is enabled
	/**
	 * Stores the cached objects if caching is enabled
	 */
	IDataObject cache;
	
	/**
	 * The ILogger used to log messages
	 */
	private ILogger logger;
	
	//Initiates class with given path and caching
	/**
	 * Contructs a new DataInterface with a given IRawLoader (see loader), IDataAdapter (see adapter), determines caching, and uses a specific logger
	 * @param l The IRawLoader used
	 * @param a The IDataAdaper used
	 * @param cached If DataInterface uses caching
	 * @param log The ILogger used to log messages
	 */
	public DataInterface(IRawLoader l,IDataAdapter a, boolean cached, ILogger log) {
		this.cached=true;
		this.loader=l;
		this.adapter=a;
		this.setLogger(log);
		logger.log("Created new Data Interface.");
	}
	
	//Initiates class with given path and caching
	/**
	 * Contructs a new DataInterface with a given IRawLoader (see loader), IDataAdapter (see adapter), and determines caching
	 * @param l The IRawLoader used
	 * @param a The IDataAdaper used
	 * @param cached If DataInterface uses caching
	 */
	public DataInterface(IRawLoader l,IDataAdapter a,boolean cached) {
		this(l,a,cached,new DummyLogger());
	}
	
	// Initiates with a certain path to load and caching on
	/**
	 * Contructs a new DataInterface with a given IRawLoader (see loader), IDataAdapter (see adapter), and determines caching
	 * Uses the default value of true for caching
	 * @param l The IRawLoader used
	 * @param a The IDataAdaper used
	 */
	public DataInterface(IRawLoader l,IDataAdapter a) {
		this(l,a,true);
	}
	
	
	@Override
	/**
	 * Not currently used for anything and I'm not sure it ever will be.
	 */
	public void flush() throws IOException {
		logger.log("DataInterface flush called, but is not used.", ErrorLevel.WARNING);
	}
	
	@Override
	/**
	 * Loads and returns data bypassing the cache.  Specific to subclasses.
	 */
	public IDataObject loadData() throws Exception {
		return adapter.rawToObject(loader.loadString());
	}
	
	@Override
	/**
	 * Uses cache (if enabled) to return data.  If data not loaded, then it calls loadData()
	 * @returns IDataObject The data object cached or loaded
	 */
	public IDataObject getData() throws Exception {
		if (cached && cacheLoaded) {
			logger.log("Returned cached data.");
			return cache;
		} else {
			logger.log("Loaded new data.");
			IDataObject data = loadData();
			cacheLoaded=true;
			return data;
		}
	}

	@Override
	/**
	 * Updates the cache with data from loadData()
	 */
	public void updateCache(IDataObject data) {
		logger.log("Updating cache...");
		cache=data;
	}
	
	@Override
	/**
	 * Writes the current cached data using the IRawLoader
	 */
	public void dumpData() throws Exception {
		logger.log("Dumping current cache...");
		writeData(cache);
	}
	
	
	@Override
	/**
	 * Updates the cache (if enabled) with given data, then writes the data
	 * @param IDataObject IDataObject to dump
	 */
	public void dumpData(IDataObject data) throws Exception {
		logger.log("Dumping data...");
		if (cached){
			updateCache(data);
		}
		writeData(data);
	}

	@Override
	/**
	 * Writes data; specific to subclasses
	 */
	public void writeData(IDataObject data) throws Exception {
		loader.writeString(adapter.objectToRaw(data));
	}
	
	/**
	 * Returns the current logger used to log messages.
	 * @return ILogger the logger used by the IDataInterface
	 */
	public ILogger getLogger() {
		return logger;
	}
	
	/**
	 * Sets the current logger used the log messages.
	 * @param logger The new logger to be used
	 */
	public void setLogger(ILogger logger) {
		this.logger = logger;
	}

}
