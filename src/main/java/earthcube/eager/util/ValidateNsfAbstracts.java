package earthcube.eager.util;

import earthcube.eager.data.NsfData;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import earthcube.eager.util.GetListOfFiles;
import earthcube.eager.read.NsfRdfData;
import earthcube.eager.util.FileWrite;

public class ValidateNsfAbstracts
{

  public static void main (String[] args)
  {

	  long startTime = System.currentTimeMillis();
	  
	  // Inputs
	  String rdfDir = args[0];
	  String outputDir = args[1];
	  FileWrite fw = new FileWrite ();
	  
	  // Find all the relevant NSF data
	  GetListOfFiles getFileList = new GetListOfFiles ();
	  ArrayList <String> rdfFiles = getFileList.Process( new File (rdfDir) );
	  System.out.println( "There are " + rdfFiles.size() + " RDF files");
	  
	  // Try to parse each of the files
	  String[] parts;
	  String line;
	  String rdfFile;
	  NsfRdfData rdf = new NsfRdfData ();
	  for ( int i=0; i<rdfFiles.size(); i++ )
	  {
		  
		 parts = rdfFiles.get(i).split("/");
		 rdfFile = parts[parts.length-1];
		 
		 try {
			 NsfData nsfData = rdf.parse( rdfFiles.get(i) );
		 } catch ( Exception e ) { 

			 // if it doesn't parse then try to read it line by line
			 try{
				  FileInputStream fstream = new FileInputStream( rdfFiles.get(i) );
				  DataInputStream in = new DataInputStream(fstream);
				  BufferedReader br = new BufferedReader(new InputStreamReader(in));
				  
				  // Read File Line By Line
				  while ((line = br.readLine()) != null)   {
					
					// Check if its the line we want
					if ( line.contains("projectFunding:statedPurpose") ) {					 
					  if (line.contains(">") || line.contains("<"))
					  {
						 line = line.replaceAll(">", "greater than");
						 line = line.replaceAll("<", "less than");
						 line = line.replace("less thanprojectFunding:statedPurpose", "<projectFunding:statedPurpose");
						 line = line.replace("less than/projectFunding:statedPurposegreater than", 
								 "</projectFunding:statedPurpose>");
						 line = line.replace("Schema#string\"greater than", "Schema#string\">");
					  }
					} 

					// write the line to the new file
					fw.append( outputDir+rdfFile, line);
					
				  } // end while
				  
				  //Close the input stream
				  in.close();
				} catch (Exception ex) { System.err.println("Read Error: " + ex.getMessage()); }
			 
		 } // end catch
	  } // end for
	  
	  long stopTime = System.currentTimeMillis();
	  long duration = ( stopTime - startTime )/1000;
	  System.out.println( "Execution time: " + duration + " seconds");
  
  }
  
}