package earthcube.eager.read;

import earthcube.eager.util.Format;
import earthcube.eager.data.AguData;

import java.util.ArrayList;
import java.util.HashMap;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.FileManager;

public class QueryAguTurtle {
	
	private int abstractIndex = 0;
	private int queriesToAgu = 0;
	private OntModel people, meeting;
	private Format format = new Format ();
	private final String aguEndpoint = "http://abstractsearch.agu.org:8890/sparql";
	private ArrayList <AguData> aguAbstracts = new ArrayList <AguData> ();
	private HashMap <String, String[]> peopleDetails = 
			new HashMap <String, String[]> (); // authorUri, name,email 
	
	public int getAguQueryCount () { return queriesToAgu; }
	
	public void clearQueriesToAgu () { queriesToAgu = 0; }
			
	public ArrayList <AguData> getAbstracts () { return aguAbstracts; }
	
	public void clearAguAbstracts () { aguAbstracts.clear(); }
	
	private String getPersonData ( String roleUri ) {
		
		String[] personData = peopleDetails.get( roleUri ); 
		String pData = personData[0] + ":" + personData[1];
		return pData;
		
	}
	
	private boolean knownPerson ( String roleUri ) {
		
		if ( peopleDetails.containsKey(roleUri) ) { return true; } else { return false; }
		
	}
	
	private String createAllAuthorRolesQuery ( String personUri ) {
	
		String query = "SELECT ?authorUri WHERE { " +
			"<" + personUri + "> <http://tw.rpi.edu/schema/hasRole> ?authorUri . " +
			"}"; 
		queriesToAgu++;
		return query;
	}
	
	private String createPersonQuery ( String authorUri ) {
			
		String query = "SELECT ?person ?name ?email WHERE { " +
				"?person <http://tw.rpi.edu/schema/hasRole> <" + authorUri + "> . " +
				"?person <http://xmlns.com/foaf/0.1/name> ?name . " + 
				"?person <http://xmlns.com/foaf/0.1/mbox> ?email . " +
				"}";
		return query;
	}
			
	private String[] getSparqlQueryResultsPeople ( ResultSet rs ) {
		
		String[] results = {null, null};
		
		try { 
			QuerySolution soln = rs.nextSolution();
			RDFNode n = soln.get("name");
			RDFNode e = soln.get("email");
			RDFNode p = soln.get("person");
			
			// output the name and email
			results[0] = format.removeDataType( n.toString() );
		    results[1] = format.removeDataType( e.toString() );

		    // find everything else this person has authored
		    String query = createAllAuthorRolesQuery( p.toString() );
		    ResultSet authorResults = sparqlQuery( aguEndpoint, query );		
		    
		    // populate the two hashes
		    while ( authorResults.hasNext() ) {
		    	QuerySolution soln2 = authorResults.nextSolution();
		    	RDFNode authorUri = soln2.get("authorUri");
		    	String aUri = authorUri.toString();
		    	peopleDetails.put( aUri.trim(), results );
		    }
	    
		} catch ( java.util.NoSuchElementException e ) {
			results[0] = "Unknown Unknown";
			results[1] = "Unknown";
		} 
	    	    	
	    return results;
	    
	}
	
	// SPARQL query
	public static String abstractQuery = "SELECT ?abstract ?abstractText ?title ?keyword ?authorUri ?authorIndex WHERE { " +
			"?abstract <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://abstracts.agu.org/ontology#Abstract> . " +
			"?abstract <http://purl.org/dc/terms/title> ?title . " +
			"?abstract <http://data.semanticweb.org/ns/swc/ontology#hasTopic> ?keyword . " +
			"?abstract <http://swrc.ontoware.org/ontology#abstract> ?abstractText . " +
			"?abstract <http://tw.rpi.edu/schema/hasAgentWithRole> ?authorUri . " + 
			"?authorUri <http://tw.rpi.edu/schema/index> ?authorIndex . " +
			"}";
	
	// SPARQL query
	public static String justAbstractQuery = "SELECT ?abstract ?abstractText WHERE { " +
			"?abstract <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://abstracts.agu.org/ontology#Abstract> . " +
			"?abstract <http://swrc.ontoware.org/ontology#abstract> ?abstractText . " +
			"}";
	
	public ResultSet sparqlQuery ( String query, OntModel model ) {
	
		 // Execute the query
	    QueryExecution qe = QueryExecutionFactory.create(query, model);
	    ResultSet rs = qe.execSelect();
        return rs;
        
	}
			
	public ResultSet sparqlQuery ( String endpoint, String query ) {
		
	  // Execute the query
	  Query q = QueryFactory.create(query);
	  QueryExecution qe = QueryExecutionFactory.createServiceRequest( endpoint, q );
	  ResultSet rs = qe.execSelect();
      return rs;
      
	}
	
	public ResultSet sparqlQuery ( String query ) {
		
		 // Execute the query
	    QueryExecution qe = QueryExecutionFactory.create(query, meeting);
	    ResultSet rs = qe.execSelect();
       return rs;
       
	}
	
	private AguData getExistingAbstract ( String uri ) {
		
		AguData result = null;
		for ( int i=0; i<aguAbstracts.size(); i++ ) {
			AguData d = aguAbstracts.get(i);
			if ( d.getAbstractUri().trim().equals( uri.trim() ) ) { result = d; abstractIndex = i; break; }
		}
		return result;
		
	}

	public void getSparqlQueryResultsAbstract ( ResultSet rs ) {
			
	    // Loop through the results
	    while ( rs.hasNext() ) {
	    	    	
	    	QuerySolution soln = rs.nextSolution();
	    	RDFNode absText = soln.get("abstractText");
	    	RDFNode titl = soln.get("title");
	    	RDFNode key = soln.get("keyword");
	    	RDFNode abstct = soln.get("abstract");
	    	RDFNode authUri = soln.get("authorUri");
	    	RDFNode authIndex = soln.get("authorIndex");
	    
	    	String abstractUri = abstct.toString();
	    	String authorUri = authUri.toString();
	    	String authorIndex = format.removeDataType(authIndex.toString());
	    	String abstractText = format.removeLanguage( absText.toString() );
	    	String title = format.removeLanguage( titl.toString() );
	    	String keyword = key.toString().replace("http://abstracts", "http://abstractsearch");
	    	
	    	// if we already have this person then don't query AGU again
	    	String pData;
	    	String[] personData;
	    	if ( !knownPerson(authorUri) ) {
	    		
	    		String personQuery = createPersonQuery ( authorUri );
	    		//ResultSet personResult = sparqlQuery ( personQuery, people );
	    		ResultSet personResult = sparqlQuery ( aguEndpoint, personQuery );
	    	
	    		personData = getSparqlQueryResultsPeople ( personResult );
	    		pData = personData[0] + ":" + personData[1];
	    		
	    	} else { pData = getPersonData( authorUri ); }
	    	
	    	AguData data = getExistingAbstract( abstractUri );
	    	
	    	// if the abstract uri doesn't exist then create a new abstract
	    	// otherwise just add the next author and keyword
	    	if ( data == null ) {
	    		data = new AguData ();
	    		data.setAbstractText( abstractText );
	    		data.setAbstractTitle( title );
	    		data.setAbstractUri( abstractUri );
	    		if ( !data.authorExists(authorUri) ) { data.addAuthor( authorUri, authorIndex ); }
	    		if ( !data.keywordExists(keyword) ) { data.addKeyword( keyword ); }
	    		data.addAuthorData( authorUri, pData );
	    		aguAbstracts.add( data );
	    	} else {
	    		if ( !data.authorExists(authorUri) ) { data.addAuthor( authorUri, authorIndex ); }
	    		if ( !data.keywordExists(keyword) ) { data.addKeyword( keyword ); }
	    		data.addAuthorData( authorUri, pData );
	    		aguAbstracts.set( abstractIndex, data );
	    	}
	    	
	    } // end while
	
	}
	
	public void getSparqlQueryResultsJustAbstract ( ResultSet rs ) {
		
	    // Loop through the results
	    while ( rs.hasNext() ) {
	    	    	
	    	QuerySolution soln = rs.nextSolution();
	    	RDFNode absText = soln.get("abstractText");
	    	RDFNode abs = soln.get("abstract");
	    
	    	AguData data = new AguData ();
	    	data.setAbstractText( format.removeLanguage( absText.toString() ) );
	    	data.setAbstractUri( abs.toString() );
	    	aguAbstracts.add( data );
	    	
	    } // end while
	
	}
	
	public void readPeopleFile ( String file ) {
		
		// Read the people turtle data into a graph
		System.out.println("Reading people turtle data...");
		Model model = ModelFactory.createDefaultModel();	
	    OntModelSpec spec = new OntModelSpec( OntModelSpec.OWL_MEM_RDFS_INF );
		people = ModelFactory.createOntologyModel( spec, model );
		FileManager.get().readModel(people, file) ;
				
	}
	
	public void readMeetingFile ( String file ) {	
				
		// Read the turtle data
		System.out.println("Reading: " + file);
	    Model model = ModelFactory.createDefaultModel();	
	    OntModelSpec spec = new OntModelSpec( OntModelSpec.OWL_MEM_RDFS_INF );
		meeting = ModelFactory.createOntologyModel( spec, model );
		FileManager.get().readModel(meeting, file) ;
	
	}			
	
	public static void main (String[] args) {
		
		String fmFile = args[0] + "\\" + "fm00.ttl";
		//String peopleFile = args[0] + "\\" + "people.ttl";
		QueryAguTurtle agu = new QueryAguTurtle ();
		//agu.readPeopleFile( peopleFile );
		agu.readMeetingFile( fmFile );
		
		// execute the SPARQL query and get the abstract texts
		ResultSet rs = agu.sparqlQuery( QueryAguTurtle.abstractQuery );
		agu.getSparqlQueryResultsJustAbstract(rs);
		//ArrayList <AguData> aguAbstracts = agu.getAbstracts();
		  
	}
	
}