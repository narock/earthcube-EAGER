package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class FundingAward {
	
	public String toRdfXml ( FundingAwardData fundingAward ) {
	
		String output = 
				
		  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
		  "<rdf:RDF xmlns=\"http://schema.ocean-data.org/funding-award#\"" + Constants.newLine +
		  "   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
		  "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
		  "   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
		  "   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine + 
		  "   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
		  "   xml:base=\"http://schema.ocean-data.org/funding-award\">" + Constants.newLine +
		  "   <rdf:Description rdf:about=\"http://www.oceanlink.org/lod/FundingAward/" + fundingAward.getAward() + "\">" + Constants.newLine +
		  "       <rdf:type rdf:resource=\"FundingAward\" />" + Constants.newLine +
		  "       <hasFundingAgent rdf:resource=\"http://www.oceanlink.org/lod/Agent/" + fundingAward.getFundingAgent() + "\"/>" + Constants.newLine +
		  "       <providesAgentRoleType rdf:resource=\"http://www.oceanlink.org/lod/AgentRoleType/" + fundingAward.getAgentRoleType + "\" />" + Constants.newLine +
		  "       <isFundedBy rdf:resource=\"http://www.oceanlink.org/lod/Organization/" + fundingAward.getFundingOrganization() + "\" />" + Constants.newLine +
		  "       <startsAt rdf:datatype=\"time:Instant\">" + fundingAward.getStartInstant() + "</startsAt>" + Constants.newLine +
		  "       <endsAt rdf:datatype=\"time:Instant\">" + fundingAward.getEndInstant() + "</endsAt>" + Constants.newLine +
		  "       <hasAwardAmount rdf:resource=\"http://www.oceanlink.org/lod/AwardAmount/" + fundingAward.getAwardAmount() + "\"/>" + Constants.newLine +
		  "       <isDescribedBy rdf:resource=\"http://www.oceanlink.org/lod/InformationObject/" + fundingAward.getInformationObject() + "\"/>" + Constants.newLine +
		  "   </rdf:Description>" + Constants.newLine +
		  "</rdf:RDF>";
		
		return output;
		
	}
	
}	