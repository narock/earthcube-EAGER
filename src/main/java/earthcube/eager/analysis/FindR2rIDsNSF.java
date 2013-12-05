package earthcube.eager.analysis;

import earthcube.eager.data.R2rData;
import earthcube.eager.data.NsfData;
import earthcube.eager.sparql.query.R2r;

import java.io.File;
import java.util.ArrayList;
import earthcube.eager.util.GetListOfFiles;
import earthcube.eager.read.NsfRdfData;
import earthcube.eager.util.FileWrite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FindR2rIDsNSF
{

  public static void main (String[] args)
  {
	  FindR2rIDsNSF ids = new FindR2rIDsNSF ();
	  
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

	  // Find all the relevant NSF data
	  GetListOfFiles getFileList = new GetListOfFiles ();
	  ArrayList <String> rdfFiles = getFileList.Process( new File (rdfDir) );
	  System.out.println( "There are " + rdfFiles.size() + " RDF files");
	  
	  // Query R2R for all vessel data
	  R2r r2r = new R2r ();
	  ArrayList <R2rData> r2rResults = r2r.submitQuery();
	  
	  // Read NSF data
	  NsfRdfData rdf = new NsfRdfData ();
	  for ( int i=0; i<rdfFiles.size(); i++ )
	  {
		 try {
			 NsfData nsfData = rdf.parse( rdfFiles.get(i) );
			 if ( i == 100 ) { System.out.println("Abstract 100 of " + rdfFiles.size()); }
			 if ( i == 500 ) { System.out.println("Abstract 500 of " + rdfFiles.size()); }
			 if ( i == 1000 ) { System.out.println("Abstract 1000 of " + rdfFiles.size()); }
			 if ( i == 5000 ) { System.out.println("Abstract 5000 of " + rdfFiles.size()); }
			 if ( i == 10000 ) { System.out.println("Abstract 10000 of " + rdfFiles.size()); }
			 if ( i == 50000 ) { System.out.println("Abstract 50000 of " + rdfFiles.size()); }
			 if ( i == 100000 ) { System.out.println("Abstract 100000 of " + rdfFiles.size()); }
			 if ( i == 150000 ) { System.out.println("Abstract 150000 of " + rdfFiles.size()); }
			 if ( i == 200000 ) { System.out.println("Abstract 200000 of " + rdfFiles.size()); }
			 if ( i == 250000 ) { System.out.println("Abstract 250000 of " + rdfFiles.size()); }

			 // Not all NSF data has an abstract
			 if ( nsfData.hasAbstract() ) {
				 
				 // Look for R2R data in the abstract
				 for ( int j=0; j<r2rResults.size(); j++ )
				 {
					 
					 R2rData r2rData = r2rResults.get(j);
					 // Cruise ID
					 if ( nsfData.getAbstract().contains( r2rData.getCruiseID()  ) ) { 
						 if ( !checkHashMap(cruiseIDs, rdfFiles.get(i), r2rData.getCruiseID()) )
						 {
							 cruiseIdCount++; 
							 writter.append( cruiseIdFile, rdfFiles.get(i) + "," + r2rData.getCruiseID());
							 cruiseIDs.put(rdfFiles.get(i), r2rData.getCruiseID());
						 } 
					 }
				
					 // Cruise Title
					 //if ( nsfData.getAbstract().contains( r2rData.getCruiseTitle() ) ) {
					 //	 System.out.println("Cruise Title: " + r2rData.getCruiseTitle());
					 //	 if ( !checkHashMap(cruiseTitles, rdfFiles.get(i), r2rData.getCruiseTitle()) )
					 //	 {
					 //		 cruiseTitleCount++; 
					 //		 writter.append( cruiseTitleFile, rdfFiles.get(i) + "," + r2rData.getCruiseTitle());
					 //		 cruiseTitles.put(rdfFiles.get(i), r2rData.getCruiseTitle());
					 //	 } 
					 //}
				
					 // Vessel Name
					 if ( nsfData.getAbstract().contains( r2rData.getVesselName()) ) {
						 if ( !checkHashMap(vesselNames, rdfFiles.get(i), r2rData.getVesselName()) )
						 {
							 vesselNameCount++; 
							 writter.append( vesselNameFile, rdfFiles.get(i) + "," + r2rData.getVesselName());
							 vesselNames.put(rdfFiles.get(i), r2rData.getVesselName());
						 } 
					 }
		 
				 } // end for
				 
			 } // end if has abstract	 
			 
		 } catch ( Exception e ) { System.out.println(e); System.out.println( rdfFiles.get(i) ); }

	  }
	  
	  long stopTime = System.currentTimeMillis();
	  long duration = ( stopTime - startTime )/1000/60;
	  System.out.println("Abstracts with cruise ID: " + cruiseIdCount);
	  System.out.println("Abstracts with cruise title: " + cruiseTitleCount);
	  System.out.println("Abstracts with vessel name: " + vesselNameCount);
	  System.out.println( "Execution time: " + duration + " minutes");
  
  }
  
}