package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class Person {
	
	public String toRdfXml ( PersonData p ) {
	
		String output = 
			
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
			"<rdf:RDF xmlns=\"http://schema.ocean-data.org/person#\"" + Constants.newLine +
			"   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
			"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
			"   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
			"   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
			"   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			"   xml:base=\"http://schema.ocean-data.org/person\">" + Constants.newLine +
			"   <rdf:Description rdf:about=\"http://www.oceanlink.org/lod/Person/" + p.getPerson() +"\">" + Constants.newLine +
			"      <rdf:type rdf:resource=\"Person\"/>" + Constants.newLine +
			"      <rdfs:subClassOf rdf:resource=\"Agent\"/>" + Constants.newLine +
			"      <performsAgentRole rdf:resource=\"http://www.oceanlink.org/lod/AgentRole/" + p.getAgentRole() + "\"/>" + Constants.newLine +
			"      <hasCredential rdf:resource=\"http://www.oceanlink.org/lod/Credential/" + p.getCredential() + "\"/>" + Constants.newLine +
			"      <hasPersonalInfoItem rdf:resource=\"http://www.oceanlink.org/lod/InformationObject/" + p.getPersonalInfoItem() + "\"/>" + Constants.newLine +
			"   </rdf:Description>" + Constants.newLine +
			"</rdf:RDF>";
		
		return output;
			  
	}
	
}