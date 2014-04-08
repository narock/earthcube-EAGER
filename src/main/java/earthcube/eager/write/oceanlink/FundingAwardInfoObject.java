package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;
import earthcube.eager.data.NsfData;

public class FundingAwardInfoObject {
	
	public String toRdfXml ( NsfData d ) {

		String output =
		
		  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine + 
		  "<rdf:RDF xmlns=\"http://schema.ocean-data.org/information-object#\"" + Constants.newLine + 
		  "   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine + 
		  "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine + 
		  "   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine + 
		  "   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine + 
		  "   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine + 
		  "   xml:base=\"http://schema.ocean-data.org/information-object#\">" + Constants.newLine + 
		  "   <rdf:Description rdf:about=\"http://www.oceanlink.org/lod/InformationObject/" + d.getFundingAwardInformationObjectUri() + "\">" + Constants.newLine + 
		  "      <rdf:type rdf:resource=\"FundingAwardInfoObj\"/>" + Constants.newLine + 
		  "      <rdfs:subClassOf rdf:resource=\"InformationObject\"/>" + Constants.newLine + 
		  "      <hasAwardID rdf:datatype=\"xsd:string\">" + d.getAwardID() + "</hasAwardID>" + Constants.newLine + 
		  "      <hasCononicalName rdf:datatype=\"xsd:string\">" + d.getTitle() + "</hasCononicalName>" + Constants.newLine + 
		  "      <hasDescription rdf:datatype=\"xsd:string\">" + d.getAbstract() + "</hasDescription>" + Constants.newLine + 
		  "      <hasDescriptionDocument rdf:resource=\"http://www.oceanlink.org/lod/DocumentSet/" + d.getFundingAwardDocumentSetUri() + "\"/>" + Constants.newLine + 
		  "   </rdf:Description>" + Constants.newLine + 
		  "</rdf:RDF>";
		
		return output;
		
	}

}	