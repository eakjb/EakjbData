package com.eakjb.EakjbData.test;

import com.eakjb.EakjbData.DataInterface;
import com.eakjb.EakjbData.DataStructureQuery;
import com.eakjb.EakjbData.HashMapDataStructure;
import com.eakjb.EakjbData.IDataObject;
import com.eakjb.EakjbData.IDataStructure;
import com.eakjb.EakjbData.RawLocalLoader;
import com.eakjb.EakjbData.DataAdapters.XMLAdapter;
import com.eakjb.EakjbData.Logging.ErrorLevel;
import com.eakjb.EakjbData.Logging.Logger;

public class Tester {

	/**
	 * @param args Not used
	 */
	public static void main(String[] args) {
		try {
			Logger log = new Logger();
			log.log("Program running...");
			DataInterface i = new DataInterface(new RawLocalLoader("test.xml",log), new XMLAdapter(log),true,log);
			IDataObject o = i.getData();
			log.log("Object text value: "+o.getTextValue());
			log.log("Trying structure...",ErrorLevel.WARNING);
			HashMapDataStructure doc = (HashMapDataStructure) o;
			log.log("Cast done.");
			log.log("HashMap: "+doc.getMap().toString());
			log.log("Manuel Value Retrieval: " + ((HashMapDataStructure) ((HashMapDataStructure) doc.get("test")).get("e")).get("h").getTextValue(), ErrorLevel.INFO);
			log.log("Using query retrieval...");
			DataStructureQuery q = new DataStructureQuery(doc, "a");
			IDataStructure result = q.execute();
			log.log(result.get("a").getTextValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
