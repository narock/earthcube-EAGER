package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class Annotation {
	
	public String toRdfXml ( String abstractUri, String boatUri, String name ) {
	
		String output = 
			
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
			"<rdf:RDF xmlns=\"http://schema.oceanlink.org/annotation#\"" + Constants.newLine +
			"   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
			"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
			"   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
			"   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
			"   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			"   xml:base=\"http://schema.oceanlink.org/annotation\">" + Constants.newLine +
			"   <rdf:Description rdf:about=\"" + boatUri + "\">" + Constants.newLine +
			"      <rdf:type rdf:resource=\"Annotation\"/>" + Constants.newLine +
			"      <isMentionedIn rdf:resource=\"" + abstractUri + "\"/>" + Constants.newLine +
			"      <hasCononicalName rdf:datatype=\"xsd:string\">" + name + "</hasCononicalName>" + Constants.newLine +
			"   </rdf:Description>" + Constants.newLine +
			"</rdf:RDF>";
		
		return output;
			  
	}
	
}