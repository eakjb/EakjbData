package com.eakjb.EakjbData.DataAdapters;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.eakjb.EakjbData.HashMapDataStructure;
import com.eakjb.EakjbData.IDataObject;
import com.eakjb.EakjbData.IDataStructure;
import com.eakjb.EakjbData.DataObjects.TextDataObject;
import com.eakjb.EakjbData.Logging.Logger;

public class XMLAdapter extends DataAdapter {

	public XMLAdapter(Logger log) {
		super(log);
	}

	@Override
	public IDataObject rawToObject(String data) throws Exception {
		data=stripString(data);
		Document dom = loadXMLFromString(data);
		IDataStructure top = processLayer(dom.getChildNodes(),"XMLTree");		
		return top;
	}

	@Override
	public String objectToRaw(IDataObject data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private IDataStructure processLayer(NodeList nodes,String type) {
		HashMapDataStructure top = new HashMapDataStructure(type, logger);
		for (int i=0; i<nodes.getLength(); i++) {
			Node entry = nodes.item(i);
			// Used for debugging logger.log("Processing object: "+entry.getNodeName()+", "+entry.getTextContent());
			String id = top.genId(entry.getNodeName());
			if (entry.hasChildNodes() && entry.getChildNodes().getLength() > 1) {
				top.set(id, processLayer(entry.getChildNodes(),entry.getNodeName()));
			} else {
				top.set(id, new TextDataObject(entry.getNodeName(), entry.getTextContent()));
			}
		}
		return top;
	}
	
	private Document loadXMLFromString(String xml) throws Exception {
		logger.log("Converting String to XML...");
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();

	    return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}

}
