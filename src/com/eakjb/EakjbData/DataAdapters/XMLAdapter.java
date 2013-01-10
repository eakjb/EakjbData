package com.eakjb.EakjbData.DataAdapters;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		logger.log("Converting xml to object...");
		data=stripString(data);
		Document dom = loadXMLFromString(data);
		IDataStructure top = processLayer(dom.getChildNodes(),"root");		
		return top;
	}

	@Override
	public String objectToRaw(IDataObject data) throws Exception {
		logger.log("Converting object to xml...");
		if (!data.isStructure()) {
			return data.getTextValue();
		}
		
		IDataStructure structure = (IDataStructure) data;
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		
		if (structure.getType().equals("root")) {
			writeLayerToDOM(structure, doc.getDocumentElement(), doc);
		} else {
			Element root = doc.createElement(structure.getType());
			doc.appendChild(root);
			writeLayerToDOM(structure, root, doc);
		}
		
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		
		StringWriter buffer = new StringWriter();
		transformer.transform(new DOMSource(doc),
		      new StreamResult(buffer));
		String str = buffer.toString().replace("<", "\n<");
		
		return str;
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
	
	private void writeLayerToDOM(IDataStructure s, Element root, Document doc) {
		Iterator<IDataObject> i = s.getObjects().iterator();
		
		while (i.hasNext()) {
			IDataObject o = i.next();
			
			Element e = doc.createElement(o.getType());
			
			if (o.isStructure()) {
				writeLayerToDOM((IDataStructure) o, e, doc);
			} else {
				e.appendChild(doc.createTextNode(o.getTextValue()));
			}
			
			root.appendChild(e);
		}
	}
	
	private Document loadXMLFromString(String xml) throws Exception {
		logger.log("Converting String to XML...");
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();

	    return builder.parse(new ByteArrayInputStream(xml.getBytes()));
	}

}
