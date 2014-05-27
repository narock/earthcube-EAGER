package earthcube.eager.analysis;

import earthcube.eager.data.R2rData;
import earthcube.eager.data.BcoDmoData;
import earthcube.eager.data.NsfData;
import earthcube.eager.sparql.query.R2r;
import earthcube.eager.sparql.query.BcoDmo;
import earthcube.eager.write.oceanlink.Annotation;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import earthcube.eager.read.NsfRdfData;
import earthcube.eager.util.FileWrite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FindCruiseVesselinNSF
{

  public static void main (String[] args)
  {
	  FindCruiseVesselinNSF ids = new FindCruiseVesselinNSF ();
	  
	  // Directory where RDF data is stored and output files should be written
	  String rdfDir = args[0];
	  String outputDir = args[1];
		  
	  ids.search( rdfDir, outputDir );
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
  
  public void search ( String rdfDir, String outputDir ) 
  {
	  
	  long startTime = System.currentTimeMillis();
	  int cruiseIdCount = 0;
	  int vesselNameCount = 0;
	  
	  // File writter 
	  FileWrite writter = new FileWrite ();
	  
	  // Hashmaps to keep track of matches
	  HashMap <String,String> cruiseIDs = new HashMap <String,String> ();
	  HashMap <String,String> vesselNames = new HashMap <String,String> ();

	  // Years for which there is NSF data
	  String[] years = {"1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985",
		"1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", 
		"1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007",
		"2008", "2009", "2010", "2011", "2012"};
	  
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
	  
	  // Create the filter
	  FilenameFilter infoObjectFilter = new FilenameFilter() {
	      public boolean accept(File fdir, String name) {
	          return name.contains("NSF_FundingAwardInfoObject_");
	      }
	  };
	  
	  // Loop over all the NSF data
	  String dir;
	  String outputRdf;
	  NsfRdfData rdf = new NsfRdfData ();
	  Annotation ann = new Annotation ();
	  for ( int i=0; i<years.length; i++ ) {
	  
		  dir = rdfDir + "\\" + years[i]; 
		  File fdir = new File( dir );
		  System.out.println("Working on: " + dir);
		  
		  // keep only the information objects - this is where the abstract is
		  File [] files = fdir.listFiles( infoObjectFilter );

 		  // Loop over all the files for this year
		  int count = -1;
		  int twentyPercent = (int) (.2 * files.length);
		  int fourtyPercent = (int) (.4 * files.length);
		  int sixtyPercent = (int) (.6 * files.length);
		  int eightyPercent = (int) (.8 * files.length);
		  System.out.println("  Number of files: " + files.length);
		  for (File rdfFile : files) {
		    
			  count++;
			  if ( count == twentyPercent ) { System.out.println("    20% Complete..."); }
			  if ( count == fourtyPercent ) { System.out.println("    40% Complete..."); }
			  if ( count == sixtyPercent ) { System.out.println("    60% Complete..."); }
			  if ( count == eightyPercent ) { System.out.println("    80% Complete..."); }
			  
			  try {
				
				  NsfData nsfData = rdf.parse( rdfFile.toString() );

				  // Not all NSF data has an abstract
				  if ( (nsfData.getAbstract() != null) && (nsfData.getAbstract() != "") ) {
				
					  // Look for R2R data in the abstract
					  for ( int l=0; l<r2rResults.size(); l++ ) {
					 
						  R2rData r2rData = r2rResults.get(l);
					 
						  // Cruise ID
						  if ( nsfData.getAbstract().contains( r2rData.getCruiseID()  ) ) { 
							  if ( !checkHashMap(cruiseIDs, rdfFile.toString(), r2rData.getCruiseID()) )
							  {
								  cruiseIdCount++; 
								  outputRdf = ann.toRdfXml( nsfData.getUri(), r2rData.getCruiseUri(), r2rData.getCruiseID() );
								  writter.newFile( outputDir + "\\" + "annotation_cruise_" + cruiseIdCount + ".rdf", outputRdf );
								  cruiseIDs.put(rdfFile.toString(), r2rData.getCruiseID());
							  } 
						  }
				
						  // Vessel Name
						  if ( nsfData.getAbstract().contains( r2rData.getVesselName()) ) {
							  if ( !checkHashMap(vesselNames, rdfFile.toString(), r2rData.getVesselName()) )
							  {
								  vesselNameCount++; 
								  outputRdf = ann.toRdfXml( nsfData.getUri(), r2rData.getVesselUri(), r2rData.getVesselName() );
								  writter.newFile( outputDir + "\\" + "annotation_vessel_" + vesselNameCount + ".rdf", outputRdf );
								  vesselNames.put(rdfFile.toString(), r2rData.getVesselName());
							  } 
						  }
		 
				      } // end loop over R2R
					  
					  // Look for BCO-DMO data in the abstract
					  for ( int l=0; l<bdResults.size(); l++ ) {
					 
						  BcoDmoData bdData = bdResults.get(l);
					 
						  // Cruise ID
						  if ( nsfData.getAbstract().contains( bdData.getCruiseID()  ) ) { 
							  if ( !checkHashMap(cruiseIDs, rdfFile.toString(), bdData.getCruiseID()) )
							  {
								  cruiseIdCount++; 
								  outputRdf = ann.toRdfXml( nsfData.getUri(), bdData.getCruiseUri(), bdData.getCruiseID() );
								  writter.newFile( outputDir + "\\" + "annotation_cruise_" + cruiseIdCount + ".rdf", outputRdf );
								  cruiseIDs.put(rdfFile.toString(), bdData.getCruiseID());
							  } 
						  }
				
						  // Vessel Name
						  if ( nsfData.getAbstract().contains( bdData.getVesselName()) ) {
							  if ( !checkHashMap(vesselNames, rdfFile.toString(), bdData.getVesselName()) )
							  {
								  vesselNameCount++; 
								  outputRdf = ann.toRdfXml( nsfData.getUri(), bdData.getVesselUri(), bdData.getVesselName() );
								  writter.newFile( outputDir + "\\" + "annotation_vessel_" + vesselNameCount + ".rdf", outputRdf );
								  vesselNames.put(rdfFile.toString(), bdData.getVesselName());
							  } 
						  }
		 
				      } // end loop over BCO-DMO
				 
				  } // end if abstract not null or empty
				  
		      } catch ( Exception e ) { System.out.println(e); System.out.println( rdfFile.toString() ); }

		  } // end loop over all files for this year
		  
	  } // end loop over all years
	  
	  long stopTime = System.currentTimeMillis();
	  long duration = ( stopTime - startTime )/1000/60;
	  System.out.println("Abstracts with cruise ID: " + cruiseIdCount);
	  System.out.println("Abstracts with vessel name: " + vesselNameCount);
	  System.out.println( "Execution time: " + duration + " minutes");
  
  }
  
}