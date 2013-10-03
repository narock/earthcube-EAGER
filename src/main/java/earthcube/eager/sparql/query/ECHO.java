package earthcube.eager.sparql.query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import earthcube.eager.sparql.Endpoints;

public class ECHO extends Endpoints
{
		
     public static void main(String[] args) {

    	 ECHO endpoint = new ECHO ();
    	 endpoint.testEndpoint( endpoint.echo );
    	 ResultSet results = endpoint.submitQuery();
    	 while (results.hasNext())
    	 {
    		 QuerySolution soln = results.nextSolution();
             System.out.println(soln.get("?name"));  
    	 }
	 
     }

	 public ResultSet submitQuery() {
	        
		 String sparqlQueryString = 
				    "PREFIX echo: <http://esipfed.org/ns/discovery/ECHO/v10/> " +
				    		"select ?name where { " +
				    		" ?s echo:LongName ?name . " +
				    		" ?s echo:DataSetId ?id " +
	 						"} ORDER by ?name";
		 ResultSet results = queryEndpoint( this.echo, sparqlQueryString );
		 return results;

	 }
	 	
}