package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class Place {
	
	public String toRdfXml ( String placeUri, String informationObjectUri ) {
	
		String output = 
			
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
			"<rdf:RDF xmlns=\"http://schema.oceanlink.org/place#\"" + Constants.newLine +
			"   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
			"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
			"   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
			"   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
			"   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			"   xml:base=\"http://schema.oceanlink.org/place\">" + Constants.newLine +
			"   <rdf:Description rdf:about=\"" + placeUri + "\">" + Constants.newLine +
			"      <rdf:type rdf:resource=\"Place\"/>" + Constants.newLine +
			"	   <isDescribedBy rdf:resource=\"" + informationObjectUri + "\"/>" + Constants.newLine +
			"   </rdf:Description>" + Constants.newLine +
			"</rdf:RDF>";
		
		return output;
			  
	}
	
}