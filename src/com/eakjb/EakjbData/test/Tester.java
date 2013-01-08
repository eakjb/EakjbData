package com.eakjb.EakjbData.test;

import java.util.Iterator;

import com.eakjb.EakjbData.DataInterface;
import com.eakjb.EakjbData.DataStructureQuery;
import com.eakjb.EakjbData.IDataObject;
import com.eakjb.EakjbData.IDataStructure;
import com.eakjb.EakjbData.IQuery;
import com.eakjb.EakjbData.RawLocalLoader;
import com.eakjb.EakjbData.DataAdapters.XMLAdapter;
import com.eakjb.EakjbData.Logging.Logger;

public class Tester {

	/**
	 * @param args [xml path] [tag name to query]
	 */
	public static void main(String[] args) {
		try {
			Logger log = new Logger();
			System.out.println("Welcome to scanner app.");
			System.out.println("-----------------------");
			String path = "test.xml";
			if (args.length>0) {
				path=args[0];
			}
			String query = "test";
			if (args.length>1) {
				query=args[1];
			}
			DataInterface i = new DataInterface(new RawLocalLoader(path, log), new XMLAdapter(log));
			IDataObject s = i.getData();
			IQuery q = new DataStructureQuery((IDataStructure) s, query);
			i.dumpData(q.execute());
			scanLevel(q.execute(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void scanLevel(IDataObject d, int indent) {
		if (d.isStructure()) {
			IDataStructure s = (IDataStructure) d;
			Iterator<IDataObject> i = s.iterator();
			while (i.hasNext()) {
				IDataObject o = i.next();
				System.out.println();
				for (int it=1;it<=indent;it++) {
					System.out.print("    ");
				}
				if (o.isStructure()) {
					System.out.print(o.getType()+": ");
					scanLevel((IDataStructure) o, indent+1);
				} else {
					System.out.print(o.getType()+": "+o.getTextValue());
				}
			}
		} else {
			System.out.println(d.getTextValue());
		}
	}

}
