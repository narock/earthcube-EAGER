package earthcube.eager.sparql.query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.ArrayList;
import earthcube.eager.sparql.Endpoints;
import earthcube.eager.util.Format;
import earthcube.eager.data.BcoDmoData;;

public class BcoDmo extends Endpoints
{
		
	 private Format format = new Format ();
	 
     public static void main(String[] args) {

    	 BcoDmo endpoint = new BcoDmo ();
    	 endpoint.testEndpoint( endpoint.bcodmo );
    	 ArrayList <BcoDmoData> results = endpoint.submitQuery();
    	
    	 for ( int i=0; i<results.size(); i++ )
    	 {
    		 BcoDmoData data = results.get(i);
    		 System.out.println( data.getCruiseID() + " | " + data.getVesselName() );
    	 }
	
     }

	 public ArrayList <BcoDmoData> submitQuery() {
	        
		 ArrayList <BcoDmoData> queryResults = new ArrayList <BcoDmoData> ();

		 String sparqlQueryString = 
				 		 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
				 		 "SELECT ?cruise ?vessel ?cruiseID ?vesselName " +
						 "WHERE { " +
						 "?cruise rdf:type <http://schema.oceanlink.org/cruise#Cruise> . " +
						 "?cruise <http://schema.oceanlink.org/cruise#isUndertakenBy> ?vessel . " +
						 "?cruise <http://schema.oceanlink.org/cruise#hasCruiseID> ?cruiseID . " +
						 "?vessel <http://schema.oceanlink.org/vessel#isDescribedBy> ?vinfo . " + 
						 "?vinfo <http://schema.oceanlink.org/information-object#hasCanonicalName> ?vesselName . " +
						 "}";

				 
		 ResultSet results = queryEndpoint( this.bcodmo, sparqlQueryString );
		 while ( results.hasNext() ) 
		 {
			 QuerySolution soln = results.nextSolution();
			 BcoDmoData data = new BcoDmoData ();
			 RDFNode cruiseUri = soln.get("?cruise");
			 RDFNode vesselUri = soln.get("?vessel");
		     RDFNode cID = soln.get("?cruiseID");
		     RDFNode vName = soln.get("?vesselName");
		     data.setCruiseUri( cruiseUri.toString() );
		     data.setVesselUri( vesselUri.toString() );
		     data.setCruiseID( format.removeDataType(cID.toString()) );
		     data.setVesselName( format.removeLanguage(vName.toString()) );
		     queryResults.add( data );
		 }
		 return queryResults;

	 }
	 	
}