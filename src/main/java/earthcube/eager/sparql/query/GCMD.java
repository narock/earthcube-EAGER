package earthcube.eager.sparql.query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import earthcube.eager.sparql.Endpoints;

public class GCMD extends Endpoints
{
		
     public static void main(String[] args) {

    	 GCMD endpoint = new GCMD ();
    	 endpoint.testEndpoint( endpoint.gcmd );
    	 ResultSet results = endpoint.submitQuery();
    	 while (results.hasNext())
    	 {
    		 QuerySolution soln = results.nextSolution();
             System.out.println(soln.get("?title") + " " + soln.get("?version"));  
    	 }
	 
     }

     // version does not exist for many products and will likely be null
	 public ResultSet submitQuery() {
	        
		 String sparqlQueryString = 
				    "PREFIX gcmd: <http://gcmd.gsfc.nasa.gov/gcmd/v9/> " +
				    		"select distinct ?title ?version where { " +
				    		"?dataset a gcmd:Data_Set_Citation . " +
				    		"?dataset gcmd:Data_Set_Citation-Dataset_Title ?title . " +
				    		"OPTIONAL { ?dataset gcmd:Data_Set_Citation-Version ?version . } " +
	 						"} ORDER by ?title";
		 ResultSet results = queryEndpoint( this.gcmd, sparqlQueryString );
		 return results;

	 }
	 	
}