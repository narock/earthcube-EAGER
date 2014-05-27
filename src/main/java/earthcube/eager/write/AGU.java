package earthcube.eager.write;

import earthcube.eager.util.FileWrite;
import earthcube.eager.data.AguData;
import earthcube.eager.read.QueryAguTurtle;
import earthcube.eager.write.oceanlink.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hp.hpl.jena.query.ResultSet;

public class AGU {
	
	private File file = null;
	private FileWrite fw = new FileWrite();
	private AguData aguData = new AguData();
	private ArrayList <String> personUris = new ArrayList <String> ();
	
	// Counters to ensure unique IDs
	private int totalPeople = 0;
	private int unknownPeople = 0;
	private int totalAbstracts = 0;
	private int abstractCount = 0;
	private long idCounter = 0;
	private long personRoleCount = 0;
	public String getPersonRoleCount () { personRoleCount++; return String.valueOf(personRoleCount); }
	public void incrementID() { idCounter++; }
	public String getID() { return String.valueOf(idCounter); }
	
	// Patterns
	private AgentRole agentRole = new AgentRole ();
	private InformationObject informationObject = new InformationObject ();
	private Person person = new Person ();
	private PersonalInfoItem personInfoItem = new PersonalInfoItem ();
	private PersonName personName = new PersonName ();
	private Event event = new Event ();
	private Place place = new Place ();
	private RepositoryObject repositoryObject = new RepositoryObject ();
	
	private boolean personUriExists ( String uri ) {
		if ( personUris.contains(uri) ) { return true; } else { return false; }
	}
	
	private String getOriginatorUri () {
		
		String uri = "http://www.oceanlink.org/Originator/" + String.valueOf(abstractCount);
		abstractCount++;
		return uri;
		
	}
	
	private String getOriginatorListUri () {
		
		String uri = "http://www.oceanlink.org/OriginatorList/" + String.valueOf(abstractCount);
		abstractCount++;
		return uri;
		
	}
	
    private ArrayList <String> getCoAuthorOriginatorUris ( ArrayList <String> peopleUris ) {
		
    	int count = peopleUris.size();
    	ArrayList <String> results = new ArrayList <String> ();
    	for ( int i=0; i<count; i++ ) {
    		results.add( "http://www.oceanlink.org/Originator/" + String.valueOf(abstractCount) );
    		abstractCount++;
    	}
		return results;
		
	}
			
	public static void main (String[] args) {
						
		// Inputs
		String inputDir = args[0];	
		String outputDir = args[1];
		
		String rdf;
		AGU agu = new AGU ();

		// Create San Francisco as a place
		String placeUri = agu.aguData.getPlaceUri("SanFrancisco");
		String informationObjectUri = agu.aguData.getInformationObjectUri("AGUFallMeeting");
		rdf = agu.place.toRdfXml(placeUri, informationObjectUri);
		agu.fw.newFile(outputDir + "\\" + "AGU_Place.rdf", rdf);
		rdf = agu.informationObject.toRdfXml(informationObjectUri, "San Francisco, CA, USA", 
				"AGU Fall Meeting - annually in San Francisco, CA, USA");
		agu.fw.newFile(outputDir + "\\" + "AGU_InformationObject.rdf", rdf);
		
		// Create Ocean Science meetings as places
		String[] places = {
				"SanAntonio", "Honolulu", "Portland", "Honolulu", "Portland"
		};
		String[] oceanPlaceUris = {
				agu.aguData.getPlaceUri(places[0]),
				agu.aguData.getPlaceUri(places[1]),
				agu.aguData.getPlaceUri(places[2]),
				agu.aguData.getPlaceUri(places[3]),
				agu.aguData.getPlaceUri(places[4])
		};
		String[] oceanInformationObjectUris = {
				agu.aguData.getInformationObjectUri("OceanSciencesMeeting2000"),
				agu.aguData.getInformationObjectUri("OceanSciencesMeeting2002"),
				agu.aguData.getInformationObjectUri("OceanSciencesMeeting2004"),
				agu.aguData.getInformationObjectUri("OceanSciencesMeeting2006"),
				agu.aguData.getInformationObjectUri("OceanSciencesMeeting2010")
		};
		for ( int i=0; i<oceanPlaceUris.length; i++ ) {
		   rdf = agu.place.toRdfXml( oceanPlaceUris[i], oceanInformationObjectUris[i] );
		   agu.fw.newFile(outputDir + "\\" + "OceanScience_Place_" + String.valueOf(i) + ".rdf", rdf);
		   rdf = agu.informationObject.toRdfXml(oceanInformationObjectUris[i], places[i], 
				   "AGU Ocean Sciences Meeting");
		   agu.fw.newFile(outputDir + "\\" + "OceanScience_InformationObject_" + String.valueOf(i) + ".rdf", rdf);
		}
				
        // Create AGU Meetings as Events
		String[] fallEventUris = agu.aguData.getFallEventUris();
		String[] oceanEventUris = agu.aguData.getOceanScienceEventUris();
		String[] fallTimeUris = agu.aguData.getFallTimeUris();
		String[] oceanTimeUris = agu.aguData.getOceanTimeUris();
		String[] fallBeginUris = agu.aguData.getFallTimeBeginEndUris("Beginning");
		String[] fallEndUris = agu.aguData.getFallTimeBeginEndUris("End");
		String[] oceanBeginUris = agu.aguData.getOceanTimeBeginEndUris("Beginning");
		String[] oceanEndUris = agu.aguData.getOceanTimeBeginEndUris("End");
		String[] fallTimes = agu.aguData.getFallMeetingTimes();
		String[] oceanTimes = agu.aguData.getOceanMeetingTimes();
		
		// Combine Fall and Ocean science event uris into one array
		int size = fallEventUris.length + oceanEventUris.length;
		String[] eventUris = new String[size];
		for ( int i=0; i<fallEventUris.length; i++ ) { eventUris[i] = fallEventUris[i]; }
		for ( int i=fallEventUris.length; i<oceanEventUris.length; i++ ) { eventUris[i] = oceanEventUris[i]; }
		
		// Fall Meetings
		String times;
		String[] parts;
		for ( int i=0; i<fallEventUris.length; i++ ) {
			times = fallTimes[i];
			parts = times.split(":");
			rdf = agu.event.toRdfXml(fallEventUris[i], placeUri, fallTimeUris[i], 
					fallBeginUris[i], fallEndUris[i], parts[0], parts[1]);
			agu.fw.newFile(outputDir + "\\" + "AGU_Event_" + String.valueOf(i) + ".rdf", rdf);
		}
			
		// Ocean Science Meetings
		for ( int i=0; i<oceanEventUris.length; i++ ) {
			times = oceanTimes[i];
			parts = times.split(":");
			rdf = agu.event.toRdfXml(oceanEventUris[i], oceanPlaceUris[i], oceanTimeUris[i], 
					oceanBeginUris[i], oceanEndUris[i], parts[0], parts[1]);
			agu.fw.newFile(outputDir + "\\" + "OceanScience_Event_" + String.valueOf(i) + ".rdf", rdf);
		}
		
		// Years for which there is AGU data
		String[] dirs = {"fall2000", "fall2001", "fall2002", "fall2003", "fall2004", "fall2005", "fall2006",
				"fall2007", "fall2008", "fall2009", "fall2010", "fall2011", "fall2012", "fall2013", "os2000", 
				"os2002", "os2004", "os2006", "os2010"};
		String[] turtleFiles = {"fm00.ttl", "fm01.ttl", "fm02.ttl", "fm03.ttl", "fm04.ttl", "fm05.ttl", "fm06.ttl",
					"fm07.ttl", "fm08.ttl", "fm09.ttl", "fm10.ttl", "fm11.ttl", "fm12.ttl", "fm13.ttl", "os00.ttl", 
					"os02.ttl", "os04.ttl", "os06.ttl", "os10.ttl"};
		  
		// Variables 
		String authorName;
		AguData aguData = new AguData ();
		ArrayList <AguData> aguAbstracts = null;
		QueryAguTurtle aguQuery = new QueryAguTurtle ();
		HashMap <String,String> authors = new HashMap <String,String> ();
		HashMap <String,String> authorIndex = new HashMap <String,String> ();
		String personUri, personAgentRoleUri, personalInfoItemUri, personNameUri, repositoryObjectUri, oDir;
		String firstAuthorPersonUri = null;
		
		// Read the AGU people file
		//aguQuery.readPeopleFile( inputDir + "\\" + "people.ttl");
				
		// Loop over all the AGU data
		for ( int i=0; i<turtleFiles.length; i++ ) {
		  
			  // remove all agu abstracts from the previous file
			  // this will speed up searching if a particular abstract already exists
			  long startTime = System.currentTimeMillis();
			  aguQuery.clearAguAbstracts();
			  oDir = outputDir + "\\" + dirs[i];
			  System.out.println("Working on: " + turtleFiles[i]);
			  
			  // Create the subdirectory to write into
			  
			  File subDir = new File (outputDir + "\\" + dirs[i]);
			  subDir.mkdir();
			  
			  // read the turtle data
			  aguQuery.readMeetingFile( inputDir + "\\" + turtleFiles[i] );
			  
			  // execute the SPARQL query and get the abstract texts
			  ResultSet rs = aguQuery.sparqlQuery( QueryAguTurtle.abstractQuery );
			  aguQuery.getSparqlQueryResultsAbstract(rs);
			  aguAbstracts = aguQuery.getAbstracts();
			  
			  // status variables
			  int twentyPercent = (int) (.2 * aguAbstracts.size());
			  int fourtyPercent = (int) (.4 * aguAbstracts.size());
			  int sixtyPercent = (int) (.6 * aguAbstracts.size());
			  int eightyPercent = (int) (.8 * aguAbstracts.size());
			  System.out.println("  Number of AGU Abstracts for this year: " + aguAbstracts.size());
			
			  // Loop over all the abstracts for this year
			  for ( int j=0; j<aguAbstracts.size(); j++ ) {
			
				  AguData currentAbstract = aguAbstracts.get(j);
						
				  if ( j == twentyPercent ) { System.out.println("  Twenty Percent Complete..."); }
				  if ( j == fourtyPercent ) { System.out.println("  Forty Percent Complete..."); }
				  if ( j == sixtyPercent ) { System.out.println("  Sixty Percent Complete..."); }
				  if ( j == eightyPercent ) { System.out.println("  Eighty Percent Complete..."); }
				   
				  // Loop over all the authors for this abstract
				  ArrayList <String> authorUris = new ArrayList <String> ();
				  authors = currentAbstract.getAuthorsData();
				  authorIndex = currentAbstract.getAuthors();
				  Iterator <Map.Entry<String, String>> it = authors.entrySet().iterator();
				  while (it.hasNext()) {
				      Map.Entry <String,String> pairs = (Map.Entry <String,String>) it.next();
				     
				      // Create Uris
				      agu.totalPeople++;
				      authorName = pairs.getValue().trim();
				      parts = authorName.split(":"); // authorName is actually name:email
				      authorName = parts[0].replace(" ", "_");
				      authorName = authorName.replace(",", "");
				      authorName = authorName.toUpperCase();
					  personUri = currentAbstract.getPersonUri( agu.getID(), authorName );
				      personAgentRoleUri = currentAbstract.getPersonAgentRoleUri( agu.getID(), authorName );
				      personalInfoItemUri = currentAbstract.getPersonalInfoItemUri( agu.getID(), authorName );
				      personNameUri = currentAbstract.getPersonNameUri( agu.getID(), authorName );
				      repositoryObjectUri = currentAbstract.getRepositoryObjectUri( agu.getID() );
				      
				      // sometimes we don't have any person data
				      if ( authorName.contains("Unknown") ) {
				    	  personUri = "http://www.oceanlink.org/Person/Unknown_Person";
				    	  agu.unknownPeople++;
				      }
				      
				      // find the first author
				      String thisAuthorIndex = authorIndex.get( pairs.getKey() );
				      if ( thisAuthorIndex.equals("1") ) { 
				    	  firstAuthorPersonUri = personUri; 
				      } else { authorUris.add( personUri ); }
				      
					  // Create authors rdf
					  // does person and already exist? if so then don't create 
				      if ( !agu.personUriExists(personUri) ) {
				    	
				    	 agu.personUris.add( personUri );
				    	
						 // Person 
						 rdf = agu.person.toRdfXml( personUri, personAgentRoleUri, null, personalInfoItemUri );
						 agu.fw.newFile( oDir + "\\" + currentAbstract.getAguPersonFileName( agu.getID(), 
								 authorName ), rdf );
						 //Personal Info
						 rdf = agu.personInfoItem.toRdfXml( personalInfoItemUri, personUri, personNameUri );
						 agu.fw.newFile( oDir + "\\" + currentAbstract.getAguPersonInfoFileName( agu.getID(), 
								 authorName ), rdf );
						 // Person Name
						 rdf = agu.personName.toRdfXml( personNameUri, currentAbstract.getFullName( authorName ), 
								 currentAbstract.getFirstName( authorName ), 
								 currentAbstract.getLastName( authorName ) );
						 agu.fw.newFile( oDir + "\\" + currentAbstract.getAguPersonNameFileName( agu.getID(), 
								 authorName ), rdf );
						
				      }
				    
				      // a person may be the author of more than one abstract
				      // check if the file already exists
				      String fileName = oDir + "\\" + agu.aguData.getAguPersonRoleFileName( agu.getID(), 
				    		  authorName );
				      agu.file = new File ( oDir + "\\" + 
				           currentAbstract.getAguPersonRoleFileName( agu.getID(), authorName ) );
				      if ( agu.file.exists() ) { 
				    	 personAgentRoleUri += agu.getPersonRoleCount();
				    	 fileName = oDir + "\\" + currentAbstract.getAguPersonRoleFileName( agu.getID(), 
				    			 agu.getPersonRoleCount() );
				      }
				      // Person Agent Role
					  rdf = agu.agentRole.toRdfXml( personAgentRoleUri, personUri, repositoryObjectUri, 
							  aguData.getAuthorRoleTypeUri() );
					  agu.fw.newFile( fileName, rdf );
							  
				      it.remove(); // avoids a ConcurrentModificationException
					  
				  } // end loop over all authors

				  // Create RepositoryObject
				  rdf = agu.repositoryObject.toRdfXml( agu.aguData.getRepositoryObjectUri( agu.getID() ),
						  currentAbstract.getAbstractText(), 
						  currentAbstract.getKeywords(), currentAbstract.getAbstractTitle(), 
						  eventUris[i], agu.getOriginatorListUri(), agu.getOriginatorUri(), 
						  firstAuthorPersonUri, agu.getCoAuthorOriginatorUris(authorUris) , authorUris);
			      agu.fw.newFile(oDir + "\\" + "AGU_RepositoryObject_" + 
						  String.valueOf(agu.totalAbstracts) + ".rdf", rdf);
			      
			      agu.totalAbstracts++;
			      agu.incrementID();

			  } // end loop over year
			  
			  long endTime = System.currentTimeMillis();
			  long duration = ( endTime-startTime )/1000;
			  System.out.println("    Computation time: " + duration + " seconds");
			  System.out.println("    Number of times AGU Queried: " + aguQuery.getAguQueryCount());
			  System.out.println("    Total people so far: " + agu.totalPeople);
			  System.out.println("    Total unknown people so far: " + agu.unknownPeople);
			  System.out.println("    Total abstracts so far: " + agu.totalAbstracts);
			  aguQuery.clearQueriesToAgu();
			  
		} // end loop over all agu files
		
	}
	
}