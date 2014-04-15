package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class Organization {
	
	public String toRdfXml ( String organizationUri, String agentRoleUri, String informationObjectUri, String[] subOrgsUri ) {

		String output = 
			
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
			"<rdf:RDF xmlns=\"http://schema.ocean-data.org/organization#\"" + Constants.newLine +
			"   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
			"   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
			"   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
			"   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
			"   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			"   xml:base=\"http://schema.ocean-data.org/organization\">" + Constants.newLine +
			"   <rdf:Description rdf:about=\"" + organizationUri +"\">" + Constants.newLine +
			"      <rdf:type rdf:resource=\"Organization\"/>" + Constants.newLine +
			"      <rdfs:subClassOf rdf:resource=\"Agent\"/>" + Constants.newLine +
			"      <performsAgentRole rdf:resource=\"" + agentRoleUri + "\"/>" + Constants.newLine +
			"      <isDescribedBy rdf:resource=\"" + informationObjectUri + "\"/>" + Constants.newLine;
		
		    if ( subOrgsUri != null ) {
		    
		    	for ( int i=0; i<subOrgsUri.length; i++ ) {
		    	
		    		output +=  "      <hasSubOrganization rdf:resource=\"" + subOrgsUri[i] + "\"/>" + Constants.newLine;
		    	}
		    	
		    }
		    
			output += "   </rdf:Description>" + Constants.newLine +
			"</rdf:RDF>";
		
		return output;
			  
	}
	
}