package com.angelhackathon.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;







import org.w3c.dom.Element;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSInsertRequest;
import com.clusterpoint.api.request.CPSLookupRequest;
import com.clusterpoint.api.response.CPSLookupResponse;
import com.clusterpoint.api.response.CPSModifyResponse;



public class ClusterPointCRUD {
	
	public void insertOrSve(String pnr, String trainNo, String phoneNumber)
	{
	try
	{
	  CPSConnection conn = new CPSConnection("tcps://cloud-eu-0.clusterpoint.com:9008", "PassengerReservation", "taksandhya@gmail.com", "Hack@123", "1203", "reservation", "//reservation/id"); 

	  // Inserting test documents.
	  List<String> docs = new ArrayList<String>();
	  docs.add("<reservation><id>"+trainNo+"</id><trainnumber>"+pnr+"_"+phoneNumber+"</trainnumber></reservation>");
	 // docs.add("<reservation><id>id5</id><trainnumber>2352</trainnumber><passengerName>magna</passengerName><phoneNo>13421661279</phoneNo></reservation>");
	 
	  //Create Insert request
	  CPSInsertRequest insert_req = new CPSInsertRequest();
	  //Add documents to request
	  insert_req.setStringDocuments(docs);
	  //Send request
	  CPSModifyResponse insert_resp = (CPSModifyResponse) conn.sendRequest(insert_req);
	  //Print out inserted document ids
	  System.out.println("Inserted ids: " + Arrays.toString(insert_resp.getModifiedIds()));
	 
	  //Close connection
	  conn.close();
	} catch (Exception e)  {
	  e.printStackTrace();
	}
	}
	
	public String lookup(String pnr) throws Exception{
	
		  CPSConnection conn = new CPSConnection("tcp://cloud-eu-0.clusterpoint.com:9007", "PassengerReservation", "taksandhya@gmail.com", "Hack@123", "1203", "reservation", "//reservation/id"); 
		//Retrieve single document specified by document id
		 
		  String[] ids={pnr};
		  //Return just document id and title in found documents
		  Map<String, String> list = new HashMap<String, String>();
		  list.put("reservation/id", "yes");
		  //list.put("reservation/trainnumber", "yes");
		  CPSLookupRequest lookup_req=new CPSLookupRequest(ids,list);
		 CPSLookupResponse lookup_resp=(CPSLookupResponse) conn.sendRequest(lookup_req);
		 System.out.println(lookup_resp.getFound());
		  List<Element> docs = lookup_resp.getDocuments();
		  Iterator<Element> it = docs.iterator();
		  while (it.hasNext())
		  {
		    Element el = it.next();
		    String phoneNo=el.getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNodeValue();
		    System.out.println(el.getFirstChild().getFirstChild().getNextSibling().getFirstChild().getNodeValue());
		    String[] phnos=phoneNo.split("_");
		   return phnos[1];
		    //here goes the code extracting data from DOM Element
		  }
		  
		  return null;
	}
}
