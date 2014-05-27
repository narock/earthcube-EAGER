package earthcube.eager.analysis;

import earthcube.eager.data.R2rData;
import earthcube.eager.data.BcoDmoData;
import earthcube.eager.sparql.query.R2r;
import earthcube.eager.sparql.query.BcoDmo;
import earthcube.eager.write.oceanlink.Annotation;
import earthcube.eager.read.QueryAguTurtle;
import earthcube.eager.data.AguData;

import com.hp.hpl.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import earthcube.eager.util.FileWrite;

public class FindCruiseVesselinAGU
{
		  
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
	 
  public static void main (String[] args)
  {
	  FindCruiseVesselinAGU ids = new FindCruiseVesselinAGU ();
	  
	  // Directory where RDF data is stored and output files should be written
	  String rdfDir = args[0];
	  String outputDir = args[1];
		  
	  ids.search( rdfDir, outputDir );
  }

  public void search ( String rdfDir, String outputDir ) 
  {
	  
	  long startTime = System.currentTimeMillis();
	  int cruiseIdCount = 0;
	  int vesselNameCount = 0;
	  
	  // Hashmaps to keep track of matches
	  HashMap <String,String> cruiseIDs = new HashMap <String,String> ();
	  HashMap <String,String> vesselNames = new HashMap <String,String> ();
	  
	  // File writter 
	  FileWrite writter = new FileWrite ();
	  
	  // Years for which there is AGU data
	  String[] turtleFiles = {"fm00.ttl", "fm01.ttl", "fm02.ttl", "fm03.ttl", "fm04.ttl", "fm05.ttl", "fm06.ttl",
				"fm07.ttl", "fm08.ttl", "fm09.ttl", "fm10.ttl", "fm11.ttl", "fm12.ttl", "fm13.ttl", "os00.ttl", 
				"os02.ttl", "os04.ttl", "os06.ttl", "os10.ttl"};
	  
	  // Query R2R for cruise/vessel data
	  R2r r2r = new R2r ();
	  System.out.println("Querying R2R...");
	  ArrayList <R2rData> r2rResults = r2r.submitQuery();
	  System.out.println("R2R results size: " + r2rResults.size());
	  
	  // Query BCO-DMO for cruise/vessel data
	  BcoDmo bd = new BcoDmo ();
	  System.out.println("Querying BCO-DMO...");
	  ArrayList <BcoDmoData> bdResults = bd.submitQuery();
	  System.out.println("BCO-DMO results size: " + bdResults.size());
	  
	  // Variables 
	  String outputRdf;
	  AguData aguData = new AguData ();
	  Annotation ann = new Annotation ();
	  ArrayList <AguData> aguAbstracts = null;
	  QueryAguTurtle aguQuery = new QueryAguTurtle ();
	  
	  // Loop over all the AGU Fall Meeting data
	  for ( int i=0; i<turtleFiles.length; i++ ) {
	  
		  // remove all agu abstracts from the previous file
		  // this will speed up searching if a particular abstract already exists
		  aguQuery.clearAguAbstracts();
		  
		  System.out.println("Working on: " + turtleFiles[i]);
		  
		  // read the turtle data
		  aguQuery.readMeetingFile( rdfDir + "\\" + turtleFiles[i] );
		  
		  // execute the SPARQL query and get the abstract texts
		  ResultSet rs = aguQuery.sparqlQuery( QueryAguTurtle.justAbstractQuery );
		  aguQuery.getSparqlQueryResultsJustAbstract(rs);
		  aguAbstracts = aguQuery.getAbstracts();
		  
		  // status variables
		  int twentyPercent = (int) (.2 * aguAbstracts.size());
		  int fourtyPercent = (int) (.4 * aguAbstracts.size());
		  int sixtyPercent = (int) (.6 * aguAbstracts.size());
		  int eightyPercent = (int) (.8 * aguAbstracts.size());
		  System.out.println("  Number of AGU Abstracts for this year: " + aguAbstracts.size());
		
 		  // loop over all the abstracts
		  for ( int j=0; j<aguAbstracts.size(); j++ ) {
				
			  	aguData = aguAbstracts.get(j);
			  	if ( j == twentyPercent ) { System.out.println("    20% Complete..."); }
				if ( j == fourtyPercent ) { System.out.println("    40% Complete..."); }
				if ( j == sixtyPercent ) { System.out.println("    60% Complete..."); }
				if ( j == eightyPercent ) { System.out.println("    80% Complete..."); }
			  	
				// Look for R2R data in the abstract
				for ( int l=0; l<r2rResults.size(); l++ ) {
					 
					R2rData r2rData = r2rResults.get(l);
					 
					// Cruise ID
					if ( aguData.getAbstractText().contains( r2rData.getCruiseID()  ) ) { 
					   if ( !checkHashMap(cruiseIDs, aguData.getAbstractUri(), r2rData.getCruiseID()) )
					   {
						  cruiseIdCount++; 
						  outputRdf = ann.toRdfXml( aguData.getAbstractUri(), r2rData.getCruiseUri(), r2rData.getCruiseID() );
						  writter.newFile( outputDir + "\\" + "agu_annotation_cruise_" + cruiseIdCount + ".rdf", outputRdf );
						  cruiseIDs.put( aguData.getAbstractUri(), r2rData.getCruiseID() );
					   }
					}
				
					// Vessel Name
					if ( aguData.getAbstractText().contains( r2rData.getVesselName()) ) {
					   if ( !checkHashMap(vesselNames, aguData.getAbstractUri(), r2rData.getVesselName()) )
					   {
						  vesselNameCount++; 
						  outputRdf = ann.toRdfXml( aguData.getAbstractUri(), r2rData.getVesselUri(), r2rData.getVesselName() );
						  writter.newFile( outputDir + "\\" + "agu_annotation_vessel_" + vesselNameCount + ".rdf", outputRdf );
						  vesselNames.put(aguData.getAbstractUri(), r2rData.getVesselName());
					   }
					}
		 
				} // end loop over R2R
					  
				// Look for BCO-DMO data in the abstract
				for ( int l=0; l<bdResults.size(); l++ ) {
					 
					BcoDmoData bdData = bdResults.get(l);
					 
					// Cruise ID
					if ( aguData.getAbstractText().contains( bdData.getCruiseID()  ) ) { 
					   if ( !checkHashMap(cruiseIDs, aguData.getAbstractUri(), bdData.getCruiseID()) )
					   {
						  cruiseIdCount++; 
						  outputRdf = ann.toRdfXml( aguData.getAbstractUri(), bdData.getCruiseUri(), bdData.getCruiseID() );
						  writter.newFile( outputDir + "\\" + "agu_annotation_cruise_" + cruiseIdCount + ".rdf", outputRdf );
						  cruiseIDs.put(aguData.getAbstractUri(), bdData.getCruiseID());
					   }
					}
				
					// Vessel Name
					if ( aguData.getAbstractText().contains( bdData.getVesselName()) ) {
					   if ( !checkHashMap(vesselNames, aguData.getAbstractUri(), bdData.getVesselName()) )
					   {
						  vesselNameCount++; 
						  outputRdf = ann.toRdfXml( aguData.getAbstractUri(), bdData.getVesselUri(), bdData.getVesselName() );
						  writter.newFile( outputDir + "\\" + "agu_annotation_vessel_" + vesselNameCount + ".rdf", outputRdf );
						  vesselNames.put(aguData.getAbstractUri(), bdData.getVesselName());
					   }
					}
		 
				} // end loop over BCO-DMO	 

		  } // end loop over all abstracts in this year
		  
	  } // end loop over all years
	  
	  long stopTime = System.currentTimeMillis();
	  long duration = ( stopTime - startTime )/1000/60;
	  System.out.println("Abstracts with cruise ID: " + cruiseIdCount);
	  System.out.println("Abstracts with vessel name: " + vesselNameCount);
	  System.out.println( "Execution time: " + duration + " minutes");
  
  }
  
}