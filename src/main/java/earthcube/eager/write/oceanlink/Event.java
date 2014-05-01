package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class Event {
	
	public String toRdfXml ( String eventUri, String placeUri, String eventTypeUri, String timeUri,
			String meetingDate ) {
	
		String output = 
			
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
			"<rdf:RDF xmlns=\"http://schema.ocean-data.org/event#\"" + Constants.newLine +
			"   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
			"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
			"   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
			"   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
			"   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			"   xml:base=\"http://schema.ocean-data.org/event\">" + Constants.newLine +
			"   <rdf:Description rdf:about=\"" + eventUri + "\">" + Constants.newLine +
			"      <rdf:type rdf:resource=\"Event\"/>" + Constants.newLine +
			"	   <occursAtPlace rdf:resource=\"" + placeUri + "\"/>" + Constants.newLine +
			"	   <hasEventType rdf:resource=\"" + eventTypeUri + "\"/>" + Constants.newLine +
			"	   <occursAtTime rdf:resource=\"" + timeUri + "\"/>" + Constants.newLine +
			"   </rdf:Description>" + Constants.newLine +
			"</rdf:RDF>" + Constants.newLine +
			
			"<rdf:Description rdf:nodeID=\"" + timeUri + "\">" + Constants.newLine +
            "   <rdf:type rdf:resource=\"http://www.w3.org/2006/time#Instant\"/>" + Constants.newLine +		  
		    "   <time:inXSDDateTime rdf:datatype=\"xsd:dateTime\">" + meetingDate + "</time:inXSDDateTime>" +
		     Constants.newLine + 
		    "</rdf:Description>";
		
		return output;
			  
	}
	
}