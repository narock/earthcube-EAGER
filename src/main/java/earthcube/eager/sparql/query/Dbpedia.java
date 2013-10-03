package earthcube.eager.sparql.query;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import earthcube.eager.sparql.Endpoints;

// submits a test query to ensure that SPARQL queries are working

public class Dbpedia extends Endpoints
{
		 
     public static void main(String[] args) {

    	 Dbpedia endpoint = new Dbpedia ();
    	 endpoint.testEndpoint( endpoint.dbpedia );
    	 ResultSet results = endpoint.submitQuery( );
    	 while (results.hasNext())
    	 {
    		 QuerySolution soln = results.nextSolution();
             System.out.println(soln.get("?abstract"));  
    	 }
	 
     }

	 public ResultSet submitQuery( ) {
	        
		 String sparqlQueryString = 
				   " SELECT ?abstract " +
                   " WHERE {{ " +
                   "   <http://dbpedia.org/resource/Mars> " +
                   "      <http://dbpedia.org/ontology/abstract> " +
                   "          ?abstract }}";
		 ResultSet results = queryEndpoint( this.dbpedia, sparqlQueryString );
		 return results;

	 }

}