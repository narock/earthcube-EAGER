package earthcube.eager.analysis;

import earthcube.eager.data.R2rData;
import earthcube.eager.data.AguData;
import earthcube.eager.sparql.query.R2r;

import java.io.File;
import java.util.ArrayList;
import earthcube.eager.util.GetListOfFiles;
import earthcube.eager.read.AguRdfData;
import earthcube.eager.util.FileWrite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FindR2rIDsAGU
{

	// At the time of writing, R2R data was available via a sparql endpoint
	// but AGU data was not. Thus, this code accesses R2R vessel information
	// via SPARQL and the AGU data is read locally from RDF/XML files.
	// A simple XML parser is used to extract values from AGU RDF/XML files.
	//
	// This code looks for various naming properties of research vessels,
	// namely cruise ID, cruise title, and vessel name, in the AGU abstracts.
	//
	// At present, no semantic links (RDF) is generated. The output is
	// common separated text files with AGU abstract, research vessel
	// 
	// There are 4 inputs - 1.) the AGU RDF/XML directory, the code will parse the
	// directory and subdirectories for appropriate files, 2.) the cruise ID output file,
	// in which will be written abstracts in which a cruise's ID was found 3.) the cruise title
	// output file, in which will be written abstracts in which a cruise's title was found and 
	// 4.) the vessel name output file, in which will be written the abstracts in which a 
	// vessel name was found
	
  public static void main (String[] args)
  {
	  FindR2rIDsAGU ids = new FindR2rIDsAGU ();
	  
	  // Directory where RDF data is stored and output file
	  String rdfDir = args[0];
	  String cruiseIdFile = args[1];
	  String cruiseTitleFile = args[2];
	  String vesselNameFile = args[3];
		  
	  ids.search( rdfDir, cruiseIdFile, cruiseTitleFile, vesselNameFile );
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
  
  public void search ( String rdfDir, String cruiseIdFile, String cruiseTitleFile, String vesselNameFile ) 
  {
	  
	  long startTime = System.currentTimeMillis();
	  int cruiseIdCount = 0;
	  int cruiseTitleCount = 0;
	  int vesselNameCount = 0;
	  
	  // File writter 
	  FileWrite writter = new FileWrite ();
	  
	  // Hashmaps to keep track of matches
	  HashMap <String,String> cruiseIDs = new HashMap <String,String> ();
	  HashMap <String,String> cruiseTitles = new HashMap <String,String> ();
	  HashMap <String,String> vesselNames = new HashMap <String,String> ();

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
	  
	  // Query R2R for all vessel data
	  R2r r2r = new R2r ();
	  ArrayList <R2rData> r2rResults = r2r.submitQuery();
	  
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
			 
			 // Look for R2R data in the abstract
			 for ( int j=0; j<r2rResults.size(); j++ )
			 {
				R2rData r2rData = r2rResults.get(j);
				
				// Cruise ID
				if ( aguData.getAbstract().contains( r2rData.getCruiseID()  ) ) { 
					if ( !checkHashMap(cruiseIDs, rdfFiles.get(i), r2rData.getCruiseID()) )
					{
					  cruiseIdCount++; 
					  writter.append( cruiseIdFile, rdfFiles.get(i) + "," + r2rData.getCruiseID());
					  cruiseIDs.put(rdfFiles.get(i), r2rData.getCruiseID());
					} 
				}
				
				// Cruise Title
				//if ( aguData.getAbstract().contains( r2rData.getCruiseTitle() ) ) {
				//	if ( !checkHashMap(cruiseTitles, rdfFiles.get(i), r2rData.getCruiseTitle()) )
				//	{
				//	  cruiseTitleCount++; 
				//	  writter.append( cruiseTitleFile, rdfFiles.get(i) + "," + r2rData.getCruiseTitle());
				//	  cruiseTitles.put(rdfFiles.get(i), r2rData.getCruiseTitle());
				//	} 
				//}
				
			 	// Vessel Name
				if ( aguData.getAbstract().contains( r2rData.getVesselName()) ) {
					if ( !checkHashMap(vesselNames, rdfFiles.get(i), r2rData.getVesselName()) )
					{
					  vesselNameCount++; 
					  writter.append( vesselNameFile, rdfFiles.get(i) + "," + r2rData.getVesselName());
					  vesselNames.put(rdfFiles.get(i), r2rData.getVesselName());
					} 
				}
		 
			 }
			 
		 } catch ( Exception e ) { System.out.println(e); }

	  }
	  
	  long stopTime = System.currentTimeMillis();
	  long duration = ( stopTime - startTime )/1000/60;
	  System.out.println("Abstracts with cruise ID: " + cruiseIdCount);
	  System.out.println("Abstracts with cruise title: " + cruiseTitleCount);
	  System.out.println("Abstracts with vessel name: " + vesselNameCount);
	  System.out.println( "Execution time: " + duration + " minutes");
  
  }
  
}