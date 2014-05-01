package earthcube.eager.util;

import earthcube.eager.data.NsfData;

import java.io.File;
import java.util.ArrayList;
import earthcube.eager.util.GetListOfFiles;
import earthcube.eager.read.NsfRdfData;
import earthcube.eager.util.FileWrite;
import earthcube.eager.util.Constants;

public class InvalidRdfXml
{

  public static void main (String[] args)
  {

	  long startTime = System.currentTimeMillis();
	  
	  // Inputs
	  String rdfDir = args[0];
	  String outputFile = args[1];
	  FileWrite fw = new FileWrite ();
	  
	  // Find all the relevant AGU data
	  GetListOfFiles getFileList = new GetListOfFiles ();
	  ArrayList <String> rdfFiles = getFileList.Process( new File (rdfDir) );
	  System.out.println( "There are " + rdfFiles.size() + " RDF files");
	  
	  // Try to parse each of the files
	  NsfRdfData rdf = new NsfRdfData ();
	  for ( int i=0; i<rdfFiles.size(); i++ )
	  {
		 try {
			 NsfData nsfData = rdf.parse( rdfFiles.get(i) );
		 } catch ( Exception e ) { fw.append(outputFile, rdfFiles.get(i) + ": " + e + Constants.newLine); }

	  }
	  
	  long stopTime = System.currentTimeMillis();
	  long duration = ( stopTime - startTime )/1000;
	  System.out.println( "Execution time: " + duration + " seconds");
  
  }
  
}