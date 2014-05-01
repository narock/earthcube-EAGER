package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class FundingAward {
	
	public String toRdfXml ( String fundingAwardUri, String fundingAgentUri, String agentRoleTypeUri, String fundingDivisionUri, 
			String startDateUri, String endDateUri, String awardAmountUri, String informationObjectUri, String startDate, String endDate ) {
	
		String output = 
				
		  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
		  "<rdf:RDF xmlns=\"http://schema.ocean-data.org/funding-award#\"" + Constants.newLine +
		  "   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
		  "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
		  "   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
		  "   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine + 
		  "   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
		  "   xml:base=\"http://schema.ocean-data.org/funding-award\">" + Constants.newLine +
		  "   <rdf:Description rdf:about=\"" + fundingAwardUri + "\">" + Constants.newLine +
		  "       <rdf:type rdf:resource=\"FundingAward\" />" + Constants.newLine +
		  "       <hasFundingAgent rdf:resource=\"" + fundingAgentUri + "\"/>" + Constants.newLine +
		  "       <providesAgentRoleType rdf:resource=\"" + agentRoleTypeUri + "\" />" + Constants.newLine +
		  "       <isFundedBy rdf:resource=\"" + fundingDivisionUri + "\" />" + Constants.newLine +
		  "       <hasStartDate rdf:nodeID=\"" + startDateUri + "\"/>" + Constants.newLine +
		  "       <hasEndDate rdf:nodeID=\"" + endDateUri + "\"/>" + Constants.newLine +
		  "       <hasAwardAmount rdf:resource=\"" + awardAmountUri + "\"/>" + Constants.newLine +
		  "       <isDescribedBy rdf:resource=\"" + informationObjectUri + "\"/>" + Constants.newLine +
		  "   </rdf:Description>" + Constants.newLine +

		  "<rdf:Description rdf:nodeID=\"" + startDateUri + "\">" + Constants.newLine +
          "   <rdf:type rdf:resource=\"http://www.w3.org/2006/time#Instant\"/>" + Constants.newLine;
		
		  if ( startDate != null ) {
             output += "   <time:inXSDDateTime rdf:datatype=\"xsd:dateTime\">" + startDate + "</time:inXSDDateTime>";
		  }
          
		  output += Constants.newLine + "</rdf:Description>" + Constants.newLine +
          
          "<rdf:Description rdf:nodeID=\"" + endDateUri + "\">" + Constants.newLine +
          "   <rdf:type rdf:resource=\"http://www.w3.org/2006/time#Instant\"/>" + Constants.newLine;
		  
		  if ( endDate != null ) {
            output += "   <time:inXSDDateTime rdf:datatype=\"xsd:dateTime\">" + endDate + "</time:inXSDDateTime>";
		  }
		  
          output += Constants.newLine + "</rdf:Description>";
          
          output += Constants.newLine + "</rdf:RDF>";
		
		return output;
		
	}
	
}	