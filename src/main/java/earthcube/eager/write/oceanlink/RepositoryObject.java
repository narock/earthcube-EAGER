package earthcube.eager.write.oceanlink;

import earthcube.eager.util.Constants;

public class RepositoryObject {
	
	public String toRdfXml ( String repositoryObjectUri, String abstct, String[] keywords, String title,
			String eventUri, String originatorListUri, String[] authorsUri, String[] peopleUri ) {
		  
		  String output = 
		
		     "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + Constants.newLine +
		     "<rdf:RDF xmlns=\"http://schema.ocean-data.org/repository-object#\"" + Constants.newLine +
		     "   xmlns:owl=\"http://www.w3.org/2002/07/owl#\"" + Constants.newLine +
		     "   xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"" + Constants.newLine +
		     "   xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"" + Constants.newLine +
		     "   xmlns:time=\"http://www.w3.org/2006/time#\"" + Constants.newLine +
		     "   xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"" + Constants.newLine +
			 "   xml:base=\"http://schema.ocean-data.org/repository-object#\">" + Constants.newLine +
		     "   <rdf:Description rdf:about=\"" + repositoryObjectUri + "\">" + Constants.newLine +
		     "      <rdf:type rdf:resource=\"RepositoryObject\"/>" + Constants.newLine +
		     "      <rdfs:subClassOf rdf:resource=\"InformationObject\"/>" + Constants.newLine +
		     "	   <hasAbstract rdf:datatype=\"xsd:string\"" + abstct + "\"/>" + Constants.newLine;
		     
		     for ( int i=0; i<keywords.length; i++ ) {
		       output += 
		           "	   <hasKeyword rdf:datatype=\"xsd:string\"" + keywords[i] + "\"/>" + Constants.newLine;
		     }
		     
		     output +=
		     "	   <hasRepositoryObjectTitle rdf:datatype=\"xsd:string\"" + title + "\"/>" + Constants.newLine +
		     "     <originatesFrom rdf:resource=\"" + eventUri + "\"/>" + Constants.newLine +
 		     "     <hasOriginatorList rdf:resource=\"" + originatorListUri + "\"/>" + Constants.newLine +
		     "   </rdf:Description>" + Constants.newLine +
		  
			 // Originator List
			 "   <rdf:Description rdf:about=\"" + originatorListUri + "\">" + Constants.newLine +
			 "      <rdf:type rdf:resource=\"OriginatorList\"/>" + Constants.newLine +
			 "	    <hasFirstMember rdf:resource=\"" + authorsUri[0] + "\"/>" + Constants.newLine;
		     
		     if ( authorsUri.length > 1 ) {
		    	 for ( int i=1; i<authorsUri.length; i++ ) {	
		    		 output +=
						"	   <hasMember rdf:resource=\"" + authorsUri[i] + "\"/>" + Constants.newLine;
		    	 }
		     }
		     
		     output += "   </rdf:Description>" + Constants.newLine;
						
			 // Originator
			 for ( int i=0; i<authorsUri.length; i++ ) {
				 
				 output +=
						"   <rdf:Description rdf:about=\"" + authorsUri[i] + "\">" + Constants.newLine +
						"      <rdf:type rdf:resource=\"Originator\"/>" + Constants.newLine +
						"	   <isActedBy rdf:resource=\"" + peopleUri[i] + "\"/>" + Constants.newLine +
						"   </rdf:Description>" + Constants.newLine;
						
			 }
				
			 output += "</rdf:RDF>";
			 
		  return output;
			
	}
	
}