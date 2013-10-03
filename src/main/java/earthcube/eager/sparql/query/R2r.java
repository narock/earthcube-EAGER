package earthcube.eager.sparql.query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.ArrayList;
import earthcube.eager.sparql.Endpoints;
import earthcube.eager.util.Format;
import earthcube.eager.data.R2rData;

public class R2r extends Endpoints
{
		
	 private Format format = new Format ();
	 
     public static void main(String[] args) {

    	 R2r endpoint = new R2r ();
    	 endpoint.testEndpoint( endpoint.r2r );
    	 ArrayList <R2rData> results = endpoint.submitQuery();
    	
    	 for ( int i=0; i<results.size(); i++ )
    	 {
    		 R2rData data = results.get(i);
    		 System.out.println( data.getUri() + " | " + data.getCruiseTitle() + " | " +
    					 	     data.getCruiseID() + " | " + data.getVesselName() );
    	 }
	
     }

	 public ArrayList <R2rData> submitQuery() {
	        
		 ArrayList <R2rData> queryResults = new ArrayList <R2rData> ();

		 String sparqlQueryString = 
				 		    " PREFIX r2r: <http://linked.rvdata.us/vocab/resource/class/> " + 
						    " PREFIX dcterms: <http://purl.org/dc/terms/> " +
			        		" SELECT ?s ?title ?v WHERE { " +
			        		"   ?s ?p r2r:Cruise . " +
			        		"   ?s dcterms:title ?title . " +
			        		"   ?s r2r:VesselName ?v " +
			        		" } " +
			        		" ORDER BY ?v ";
				 
		 ResultSet results = queryEndpoint( this.r2r, sparqlQueryString );
		 while ( results.hasNext() ) 
		 {
			 QuerySolution soln = results.nextSolution();
			 R2rData r2rData = new R2rData ();
		     RDFNode cID = soln.get("?s");
		     RDFNode cTitle = soln.get("?title");
		     RDFNode vessel = soln.get("?v");
		     r2rData.setUri( cID.toString() );
		     r2rData.setCruiseID( format.getR2rCruiseId(cID.toString()) );
		     
		     // db:cruise/PS1208A has no cruise title
		     if ( cTitle.toString().equals("") ) { 
		       r2rData.setCruiseTitle("NO_CRUISE_TITLE"); 
		     } else { r2rData.setCruiseTitle( cTitle.toString() ); }
		     
		     r2rData.setVesselName( vessel.toString() );
		     queryResults.add( r2rData );
		 }
		 return queryResults;

	 }
	 	
}