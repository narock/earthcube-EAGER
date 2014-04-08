package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;
import earthcube.eager.data.NsfData;

public class FundingAward {
	
	public String toRdfXml ( NsfData d ) {
	
		String output = 
				
		  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
		  "<rdf:RDF xmlns=\"http://schema.ocean-data.org/funding-award#\"" + Constants.newLine +
		  "   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
		  "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
		  "   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
		  "   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine + 
		  "   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
		  "   xml:base=\"http://schema.ocean-data.org/funding-award\">" + Constants.newLine +
		  "   <rdf:Description rdf:about=\"http://www.oceanlink.org/lod/FundingAward/" + d.getFundingAwardUri() + "\">" + Constants.newLine +
		  "       <rdf:type rdf:resource=\"FundingAward\" />" + Constants.newLine +
		  "       <hasFundingAgent rdf:resource=\"http://www.oceanlink.org/lod/Agent/" + d.getFundingAgentUri() + "\"/>" + Constants.newLine +
		  "       <providesAgentRoleType rdf:resource=\"http://www.oceanlink.org/lod/AgentRoleType/" + fundingAward.getAgentRoleType + "\" />" + Constants.newLine +
		  "       <isFundedBy rdf:resource=\"http://www.oceanlink.org/lod/Organization/" + d.getFundingDivisionUri() + "\" />" + Constants.newLine +
		  "       <hasStartDate rdf:nodeID=\"" + d.getStartDateUri() + "\"/>" + Constants.newLine +
		  "       <hasEndDate rdf:nodeID=\"" + d.getEndDateUri() + "\"/>" + Constants.newLine +
		  "       <hasAwardAmount rdf:resource=\"http://www.oceanlink.org/lod/AwardAmount/" + d.getFundingAmountUri() + "\"/>" + Constants.newLine +
		  "       <isDescribedBy rdf:resource=\"http://www.oceanlink.org/lod/InformationObject/" + d.getFundingAwardInformationObjectUri() + "\"/>" + Constants.newLine +
		  "   </rdf:Description>" + Constants.newLine +
		  "</rdf:RDF>" + Constants.newLine +

		  "<rdf:Description rdf:nodeID=\"" + d.getStartDateUri() + "\">" + Constants.newLine +
          "   <rdf:type rdf:resource=\"http://www.w3.org/2006/time#Instant\"/>" + Constants.newLine +
          "   <time:inXSDDateTime rdf:datatype=\"xsd:dateTime\">" + d.getStartDate() + "</time:inXSDDateTime>" + Constants.newLine +
          "</rdf:Description>" + Constant.newLine +
          
          "<rdf:Description rdf:nodeID=\"" + d.getEndDateUri() + "\">" + Constants.newLine +
          "   <rdf:type rdf:resource=\"http://www.w3.org/2006/time#Instant\"/>" + Constants.newLine +
          "   <time:inXSDDateTime rdf:datatype=\"xsd:dateTime\">" + d.getEndDate() + "</time:inXSDDateTime>" + Constants.newLine +
          "</rdf:Description>";
		
		return output;
		
	}
	
}	