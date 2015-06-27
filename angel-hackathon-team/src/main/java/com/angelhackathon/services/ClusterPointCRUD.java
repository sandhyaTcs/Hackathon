package com.angelhackathon.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.clusterpoint.api.CPSConnection;
import com.clusterpoint.api.request.CPSInsertRequest;
import com.clusterpoint.api.response.CPSModifyResponse;

public class ClusterPointCRUD {
	
	private void insertOrSve()
	{
	try
	{
	  CPSConnection conn = new CPSConnection("tcps://cloud-eu-0.clusterpoint.com:9008", "PassengerReservation", "taksandhya@gmail.com", "Hack@123", "1203", "reservation", "//reservation/id"); 

	  // Inserting test documents.
	  List<String> docs = new ArrayList<String>();
	  docs.add("<reservation><id>id1</id><trainnumber>Test document 1</trainnumber><passengerName>Lorem</passengerName></reservation>");
	  docs.add("<reservation><id>id2</id><trainnumber>Test document 2</trainnumber><passengerName>magna</passengerName></reservation>");
	 
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
}
