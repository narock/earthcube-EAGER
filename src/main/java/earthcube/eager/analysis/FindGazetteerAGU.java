package earthcube.eager.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import earthcube.eager.util.FileWrite;
import earthcube.eager.data.AguData;
import earthcube.eager.read.AguRdfData;
import earthcube.eager.services.RestService;
import earthcube.eager.util.GetListOfFiles;

public class FindGazetteerAGU
{
	
	public static void main (String[] args)
	{
		// Inputs
		String rdfDir = args[0];
		String outputFile = args[1];
		String featureFile = args[2];
		FindGazetteerAGU g = new FindGazetteerAGU ();
		g.search( rdfDir, outputFile, featureFile );
	}
	
	private boolean checkHashMap ( HashMap <String,String> map, String key, String value ) 
	{
	
		Iterator <Map.Entry<String, String>> it = map.entrySet().iterator();
		boolean found = false;
		while (it.hasNext()) {
		  Map.Entry <String,String> pairs = (Map.Entry <String,String>) it.next();
		  if ( (pairs.getKey().equals(key)) && (pairs.getValue().equals(value)) ) { found = true; }
		}
		return found;	   
	
	}
	
	private void search (String rdfDir, String outputFile, String featureFile) 
	{
		
		// Variables
		long startTime = System.currentTimeMillis();
		int featureCount = 0;
		
		// List of gazetteer names
		HashMap <String,String> features = new HashMap <String,String> ();
		
		// Results
		FileWrite writter = new FileWrite ();
		HashMap <String,String> results = new HashMap <String,String> ();
		
		// Call the Gazetteer Web Service to get the list of sources
		String source;
		RestService service = new RestService ();
		String request = "http://marineregions.org/rest/getGazetteerSources.json/";
		JSONArray response = service.getJsonResponse(request);
		int length = response.length();
		for (int i = 0; i < response.length(); i++) {
		   try {
			     
			   	 int iteration = i+1;
			     System.out.println("Querying source " + iteration + " of " + length);
			     JSONObject jo = (JSONObject) response.get(i);
			     source = jo.getString("source");
			     
			     // get the features associated with this source
			     String uri = service.encodeURIComponent(source);
			     String request2 = "http://marineregions.org/rest/getGazetteerRecordsBySource.json/";
			     request2 = request2 + uri + "/";
			     
			     JSONArray response2 = service.getJsonResponse( request2 );
			     System.out.println("   Source has " + response2.length() + " features");
			     for (int j=0; j<response2.length(); j++ )
			     {
			    	 JSONObject jo2 = (JSONObject) response2.get(j);
			    	 features.put( source, jo2.getString("preferredGazetteerName").trim() );
			    	 writter.append(featureFile, source + "," + jo2.getString("preferredGazetteerName").trim());
			     }
			     
		   } catch (Exception e) { System.out.println(e); }
		}	  
		
		// Find all the relevant AGU data
		GetListOfFiles getFileList = new GetListOfFiles ();
		File dir = new File ( rdfDir );
		ArrayList <String> tmpFiles = getFileList.Process( dir );
		ArrayList <String> rdfFiles = new ArrayList <String> ();
		for ( int i=0; i<tmpFiles.size(); i++ ) 
		{
		   // keep only the presentations - ignore session, people, and section data for this analysis
		   if ( tmpFiles.get(i).contains("_Meeting_") ) { rdfFiles.add( tmpFiles.get(i) ); }
		}
		System.out.println( "There are " + rdfFiles.size() + " RDF files");
	
		// Read AGU data
		AguRdfData rdf = new AguRdfData ();
		for ( int i=0; i<rdfFiles.size(); i++ )
		{
			 try {
				 AguData aguData = rdf.parse( rdfFiles.get(i) );
				 
				 // there are over 100,000 abstracts so print out period messages
				 // indicating where we are in the analysis
				 if ( i == 100 ) { System.out.println("Abstract 100 of " + rdfFiles.size()); }
				 if ( i == 500 ) { System.out.println("Abstract 500 of " + rdfFiles.size()); }
				 if ( i == 1000 ) { System.out.println("Abstract 1000 of " + rdfFiles.size()); }
				 if ( i == 5000 ) { System.out.println("Abstract 5000 of " + rdfFiles.size()); }
				 if ( i == 10000 ) { System.out.println("Abstract 10000 of " + rdfFiles.size()); }
				 if ( i == 50000 ) { System.out.println("Abstract 50000 of " + rdfFiles.size()); }
				 if ( i == 100000 ) { System.out.println("Abstract 100000 of " + rdfFiles.size()); }
				 
				 // Look for features in the abstract
				 Iterator <Map.Entry<String,String>> it = features.entrySet().iterator();
				 while (it.hasNext()) {
				    Map.Entry <String,String> pairs = (Map.Entry<String,String>) it.next();
				    if ( aguData.getAbstract().contains( pairs.getValue()  ) ) { 
						if ( !checkHashMap(results, rdfFiles.get(i), pairs.getValue()) )
						{
						  featureCount++; 
						  writter.append( outputFile, rdfFiles.get(i) + "," + pairs.getValue() + "," + pairs.getKey());
						  results.put(rdfFiles.get(i), pairs.getValue());
						} 
					}
				    it.remove(); // avoids a ConcurrentModificationException
				 }
				 
			 } catch ( Exception e ) { System.out.println(e); }

		  }
		  
		  long stopTime = System.currentTimeMillis();
		  long duration = ( stopTime - startTime )/1000/60;
		  System.out.println("Abstracts with features: " + featureCount);
		  System.out.println( "Execution time: " + duration + " minutes");
		
	}
	
}