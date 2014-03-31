package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class PersonalInfoItem {
	
	public String toRdfXml ( PersonalInfoItemData p ) {
		
		String output = 
			
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
			"<rdf:RDF xmlns=\"http://schema.ocean-data.org/personal-info-item#\"" + Constants.newLine +
			"   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
			"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
			"   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
			"   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
			"   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			"   xml:base=\"http://schema.ocean-data.org/personal-info-item\">" + Constants.newLine +
			"   <rdf:Description rdf:about=\"http://www.oceanlink.org/lod/PersonalInfoItem/" + p.getPersonalInfoItem() +"\">" + Constants.newLine +
			"      <rdf:type rdf:resource=\"PersonalInfoItem\"/>" + Constants.newLine +
			"      <isPersonalInfoItemOf rdf:resource=\"http://www.oceanlink.org/lod/Person/" + p.getPerson() + "\"/>" + Constants.newLine +
			"      <hasPersonalInfoValue rdf:resource=\"http://www.oceanlink.org/lod/PersonName/" + p.getPersonName() + "\"/>" + Constants.newLine +
			"   </rdf:Description>" + Constants.newLine +
			"</rdf:RDF>";
		
		return output;
			  
	}
	
}