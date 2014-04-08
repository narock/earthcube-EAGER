package earthcube.eager.write;

import earthcube.eager.util.FileWrite;
import earthcube.eager.util.GetListOfFiles;
import earthcube.eager.data.NsfData;
import earthcube.eager.read.NsfXmlData;
import earthcube.eager.write.oceanlink.*;
import java.io.File;
import java.util.ArrayList;

public class OceanLink {
	
	private FileWrite fw = new FileWrite();
	private GetListOfFiles getFileList = new GetListOfFiles ();
	private NsfXmlData nsfParser = new NsfXmlData();
	private Person person = new Person ();
	
	public ArrayList <String> getListOfXmlFiles ( String dir, boolean verbose ) {
		
		ArrayList <String> xmlFiles = getFileList.Process( new File (dir) );
		if ( verbose ) { System.out.println( "There are " + xmlFiles.size() + " XML files"); }
		return xmlFiles;
		
	}
	
	// loop over all xml
	
	// parse xml
	
	// write out patterns
	
	public void writeRdfXml () {

	

	}
			
	public static void main (String[] args) {
				
		// Inputs
		String inputDir = args[0];
				
		OceanLink ol = new OceanLink ();
				
		// Find all the XML files
		ArrayList <String> xmlFiles = ol.getListOfXmlFiles( inputDir, true );
			
		// Loop over all the files
		String rdf;
		for ( int i=0; i<xmlFiles.size(); i++ ) {
					
			NsfData nsfData = null;
			try {
				
				// parse the XML file
				nsfData = ol.nsfParser.parse( xmlFiles.get(i) );

				// write out the RDF/XML
				
				// RDF for the Principal Investigator
				//// Person, Person Agent Role, and Personal Info
				rdf = ol.person.toRdfXml( nsfData.getPersonUri(), nsfData.getPersonAgentRoleUri(), null, nsfData.getPersonalInfoItemUri() );
				// write person, create person agent role, create personal info
				
				// don't duplicate uris
				
			} catch ( Exception e ) { System.out.println(e); }
			
				
		} // end for
		
	}
	
}