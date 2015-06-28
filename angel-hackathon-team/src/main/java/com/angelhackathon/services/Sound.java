package com.angelhackathon.services;

import java.io.File;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

public class Sound {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		searchForPorts();
		
		
	}
	
	public static void searchForPorts()
    {
		 Enumeration ports = CommPortIdentifier.getPortIdentifiers();

        while (ports.hasMoreElements())
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

            //get only serial ports
            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
            	curPort.getName();
            	System.out.println(curPort.getName());
            }
        }
    }

}
