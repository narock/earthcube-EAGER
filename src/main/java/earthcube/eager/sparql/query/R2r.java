package earthcube.eager.sparql.query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import java.util.ArrayList;
import earthcube.eager.sparql.Endpoints;
//import earthcube.eager.util.Format;
import earthcube.eager.data.R2rData;

public class R2r extends Endpoints
{
		
	 //private Format format = new Format ();
	 
     public static void main(String[] args) {

    	 R2r endpoint = new R2r ();
    	 endpoint.testEndpoint( endpoint.r2r );
    	 ArrayList <R2rData> results = endpoint.submitQuery();
    	
    	 for ( int i=0; i<results.size(); i++ )
    	 {
    		 R2rData data = results.get(i);
    		 System.out.println( data.getCruiseUri() + " | " + data.getCruiseID() + " | " +
    					 	     data.getVesselUri() + " | " + data.getVesselName() );
    	 }
	
     }

	 public ArrayList <R2rData> submitQuery() {
	        
		 ArrayList <R2rData> queryResults = new ArrayList <R2rData> ();

		 String sparqlQueryString = 
				 		 "PREFIX db: <http://data.rvdata.us/id/> " +
						 "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> " +
						 "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
						 "PREFIX olcruise: <http://schema.oceanlink.org/cruise#> " +
						 "PREFIX r2rmodel: <http://voc.rvdata.us/> " +
						 "PREFIX sf: <http://www.opengis.net/ont/sf#> " +
						 "PREFIX olvessel: <http://schema.oceanlink.org/vessel#> " +	
						 "PREFIX r2r: <http://data.rvdata.us/vocab/id/class/> " +
						 "PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +
						 "PREFIX dcterms: <http://purl.org/dc/terms/> " +
						 "PREFIX gn: <http://www.geonames.org/ontology#> " +
						 "PREFIX geosparql: <http://www.opengis.net/ont/geosparql#> " +
						 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						 "PREFIX d2r: <http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#> " +
						 "PREFIX map: <http://data.rvdata.us/id/#> " +
						 "PREFIX owl: <http://www.w3.org/2002/07/owl#> " +
						 "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> " +
						 "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
						 "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> " +
				 		    "SELECT ?cruise ?cruiseID ?vessel ?vesselName " +
				 		    "WHERE { " +
				 		    "  ?cruise rdf:type <http://schema.oceanlink.org/cruise#Cruise> . " +
				 		    "  ?cruise <http://schema.oceanlink.org/cruise#isUndertakenBy> ?vessel . " + 
				 		    "  ?cruise <http://purl.org/dc/terms/identifier> ?cruiseID . " +
				 		    "  ?vessel <http://www.w3.org/2000/01/rdf-schema#label> ?vesselName . " +
				 		    "}";
				 
		 ResultSet results = queryEndpoint( this.r2r, sparqlQueryString );
		 while ( results.hasNext() ) 
		 {
			 QuerySolution soln = results.nextSolution();
			 R2rData r2rData = new R2rData ();
			 RDFNode cruise = soln.get("?cruise");
		     RDFNode cID = soln.get("?cruiseID");
		     RDFNode vessel = soln.get("?vessel");
		     RDFNode vesselName = soln.get("?vesselName");
		     r2rData.setCruiseUri( cruise.toString() );
		     r2rData.setCruiseID( cID.toString() );
		     r2rData.setVesselName( vesselName.toString() );
		     r2rData.setVesselUri( vessel.toString() );
		     
		     // db:cruise/PS1208A has no cruise title
		     //if ( cTitle.toString().equals("") ) { 
		     //  r2rData.setCruiseTitle("NO_CRUISE_TITLE"); 
		     //} else { r2rData.setCruiseTitle( cTitle.toString() ); }
		     
		     queryResults.add( r2rData );
		 }
		 return queryResults;

	 }
	 	
}