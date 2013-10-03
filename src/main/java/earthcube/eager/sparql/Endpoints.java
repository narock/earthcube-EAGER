package earthcube.eager.sparql;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;

public abstract class Endpoints 
{
	
	// Rolling Deck to Repository public sparql endpoint provided by Bob Arko
	public final String r2r = "http://linked.rvdata.us/sparql";
	
	// DBpedia public sparql endpoint
	public final String dbpedia = "http://DBpedia.org/sparql";
	
	// The Global Change Master Directory (GCMD) holds more than 28,000 
	// Earth science data set and service descriptions, which cover subject 
	// areas within the Earth and environmental sciences. 
	//
	// Brian Wilson of NASA/JPL has provided this LOD interface into the GCMD holdings
	public final String gcmd = "http://sciflo.jpl.nasa.gov:8888/sparql_dif";
	
	// Earth Observing System (EOS) Clearinghouse (ECHO) is a spatial 
	// and temporal metadata registry built by NASA's Earth Science Data 
	// and Information System (ESDIS) that enables the science community
	// to more easily use and exchange NASA's data and services.
	//
	// Brian Wilson of NASA/JPL has provided this LOD interface into the ECHO holdings
	public final String echo = "http://sciflo.jpl.nasa.gov:8888/sparql_echo";
	
	protected ResultSet queryEndpoint ( String endpoint, String sparqlQueryString ) 
    {
    	Query query = QueryFactory.create(sparqlQueryString);
        ARQ.getContext().setTrue(ARQ.useSAX);
    	QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
    	ResultSet results = qexec.execSelect();                                       
    	qexec.close();
    	return results; 
    }
	
	public void testEndpoint ( String endpoint )
	{
		String queryASK = "ASK { }";
    	Query query = QueryFactory.create(queryASK);
    	QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query);
        try {
            if (qe.execAsk()) { System.out.println( endpoint + " is UP" ); } 
        }   catch (QueryExceptionHTTP e) {
            System.out.println( endpoint + " is DOWN");
            System.out.println( e );
        } finally { qe.close(); } 
	}
	
}