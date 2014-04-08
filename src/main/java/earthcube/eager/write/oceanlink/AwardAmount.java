package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class AwardAmount {
	
	public String toRdfXml ( String awardAmountUri, String awardAmount ) {
	
		String output =
		
		  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
		  "<rdf:RDF xmlns=\"http://schema.ocean-data.org/funding-award#\"" + Constants.newLine +
		  "   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
		  "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
		  "   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
		  "   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
		  "   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
		  "   xml:base=\"http://schema.ocean-data.org/funding-award\">" + Constants.newLine +
		  "   <rdf:Description rdf:about=\"" + awardAmountUri + "\">" + Constants.newLine +
		  "      <rdf:type rdf:resource=\"AwardAmount\"/>" + Constants.newLine +
		  "      <hasCurrencyCode rdf:resource=\"http://www.oceanlink.org/lod/CurrencyCode/USD\"/>" + Constants.newLine;
		
		  if ( awardAmount != null ) {
		    output += "      <hasCurrencyValue rdf:datatype=\"xsd:decimal\">" + awardAmount + "</hasCurrencyValue>";
		  }
		  
		  output += Constants.newLine + "   </rdf:Description>" + Constants.newLine + "</rdf:RDF>";
		
		return output;
		  		
	}
	
}