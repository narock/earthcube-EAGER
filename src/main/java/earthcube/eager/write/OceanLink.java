package earthcube.eager.write;

import earthcube.eager.util.FileWrite;
import earthcube.eager.util.GetListOfFiles;
import earthcube.eager.data.NsfData;
import earthcube.eager.read.NsfXmlData;
import earthcube.eager.write.oceanlink.*;

import java.io.File;
import java.util.ArrayList;

public class OceanLink {
	
	private File file = null;
	private FileWrite fw = new FileWrite();
	private GetListOfFiles getFileList = new GetListOfFiles ();
	private NsfXmlData nsfParser = new NsfXmlData();
	private ArrayList <String> personUris = new ArrayList <String> ();
	//private ArrayList <String> organizationUris = new ArrayList <String> ();
	
	// Counters to ensure unique IDs
	private long idCounter = 0;
	private long personRoleCount = -1;
	public String getPersonRoleCount () { personRoleCount++; return String.valueOf(personRoleCount); }
	public void incrementID() { idCounter++; }
	public String getID() { return String.valueOf(idCounter); }
	
	// Patterns
	private AgentRole agentRole = new AgentRole ();
	private AwardAmount awardAmount = new AwardAmount ();
	private FundingAward fundingAward = new FundingAward ();
	private FundingAwardInfoObject fundingAwardInfoObject = new FundingAwardInfoObject ();
	private InformationObject informationObject = new InformationObject ();
	private Organization organization = new Organization ();
	private Person person = new Person ();
	private PersonalInfoItem personInfoItem = new PersonalInfoItem ();
	private PersonName personName = new PersonName ();
	
	private boolean personUriExists ( String uri ) {
		if ( personUris.contains(uri) ) { return true; } else { return false; }
	}
	
	//private boolean organizationUriExists ( String uri ) {
	//	if ( organizationUris.contains(uri) ) { return true; } else { return false; }
	//}
	
	public ArrayList <String> getListOfXmlFiles ( String dir, boolean verbose ) {
		
		ArrayList <String> xmlFiles = getFileList.Process( new File (dir) );
		if ( verbose ) { System.out.println( "There are " + xmlFiles.size() + " XML files"); }
		return xmlFiles;
		
	}
			
	public static void main (String[] args) {
				
		// Inputs
		String inputDir = args[0];	
		String outputDir = args[1];
		
		String rdf;
		OceanLink ol = new OceanLink ();

		// Create NSF as an Organization and NSF Divisions as Organizations
		NsfData nsfData = new NsfData ();
		NsfDivisions nsfDivisions = new NsfDivisions ();
		String[] divisions = nsfDivisions.getNsfDivisions();
		String[] divisionUri = nsfDivisions.getNsfDivsionUris();
		String[] divisionShortName = nsfDivisions.getNSfDivisionsShortName();
		String[] divisionInfoObjectUri = nsfDivisions.getNsfDivsionInfoObjectUris();
		
		//// NSF
		rdf = ol.organization.toRdfXml( "http://www.oceanlink.org/Organization/National_Science_Foundation", 
				nsfData.getFunderRoleTypeUri(), 
				"http://www.oceanlink.org/InformationObject/InformationObject_National_Science_Foundation",
			    divisionUri);
		ol.fw.newFile( outputDir + "\\NSF.rdf", rdf );
		rdf = ol.informationObject.toRdfXml( 
				"http://www.oceanlink.org/InformationObject/InformationObject_National_Science_Foundation", 
				"The National Science Foundation", "The National Science Foundation");		
		ol.fw.newFile( outputDir + "\\NSF_Information_Object.rdf", rdf );
		
        //// NSF Divisions
		for ( int i=0; i<divisionUri.length; i++ ) {
			rdf = ol.organization.toRdfXml( divisionUri[i], nsfData.getFunderRoleTypeUri(), divisionInfoObjectUri[i], null );
			ol.fw.newFile( outputDir + "\\NSF_" + divisionShortName[i].replace(" ","_") + ".rdf", rdf );
			// Create associated Information Object
			rdf = ol.informationObject.toRdfXml( divisionInfoObjectUri[i], divisions[i], divisions[i] );
			ol.fw.newFile( outputDir + "\\NSF_Information_Object_" + divisionShortName[i].replace(" ","_") + ".rdf", rdf );
		}
		
		// Find all the XML files
		ArrayList <String> xmlFiles = ol.getListOfXmlFiles( inputDir, true );
			
		// Loop over all the files
		String personUri, personAgentRoleUri, personalInfoItemUri, personNameUri, fundingAwardUri, 
		awardAmountUri, startDateUri, endDateUri, fundingAwardInformationObjectUri;
		
		int twentyPercent = (int) (xmlFiles.size() * 0.2);
		int fortyPercent = (int) (xmlFiles.size() * 0.4);
		int sixtyPercent = (int) (xmlFiles.size() * 0.6);
		int eightyPercent = (int) (xmlFiles.size() * 0.8);
		
		for ( int i=0; i<xmlFiles.size(); i++ ) {
					
			if ( i == twentyPercent ) { System.out.println("Twenty Percent Complete"); }
			if ( i == fortyPercent ) { System.out.println("Forty Percent Complete"); }
			if ( i == sixtyPercent ) { System.out.println("Sixty Percent Complete"); }
			if ( i == eightyPercent ) { System.out.println("Eighty Percent Complete"); }
			
			try {
				
				// parse the XML file
				nsfData = ol.nsfParser.parse( xmlFiles.get(i) );

				// find the NSF Division
				String nsfDivisionUri = nsfDivisions.findDivision( nsfData.getFundingDivision() );
				
				// create URIs
				personUri = nsfData.getPersonUri( ol.getID() );
			    personAgentRoleUri = nsfData.getPersonAgentRoleUri( ol.getID() );
			    personalInfoItemUri = nsfData.getPersonalInfoItemUri( ol.getID() );
			    personNameUri = nsfData.getPersonNameUri( ol.getID() );
			    fundingAwardUri = nsfData.getFundingAwardUri( ol.getID() );
			    fundingAwardInformationObjectUri = nsfData.getFundingAwardInformationObjectUri( ol.getID() );
			    awardAmountUri = nsfData.getAwardAmountUri( ol.getID() );
			    startDateUri = nsfData.getStartDateUri( ol.getID() );
			    endDateUri = nsfData.getEndDateUri( ol.getID() );
			    
			    // sometimes we don't have any person data
			    if ( nsfData.getPiFirstName() == null && nsfData.getPiLastName() == null ) {
		    		personUri = "http://www.oceanlink.org/Person/Unknown_Person";
		    		nsfData.setPiFirstName("Unknown");
		    		nsfData.setPiLastName("Unknown");
		    	}
			    
			    // does person and already exist? if so then don't create 
			    if ( !ol.personUriExists(personUri) ) {
			    	
			    	ol.personUris.add( personUri );
			    	
				    // RDF for the Principal Investigator
					  // Person 
					  rdf = ol.person.toRdfXml( personUri, personAgentRoleUri, null, personalInfoItemUri );
					  ol.fw.newFile( outputDir + "\\" + nsfData.getPersonFileName( ol.getID() ), rdf );
					  //Personal Info
					  rdf = ol.personInfoItem.toRdfXml( personalInfoItemUri, personUri, personNameUri );
					  ol.fw.newFile( outputDir + "\\" + nsfData.getPersonInfoFileName( ol.getID() ), rdf );
					  // Person Name
					  rdf = ol.personName.toRdfXml( personNameUri, nsfData.getFullName(), 
							  nsfData.getPiFirstName(), nsfData.getPiLastName() );
					  ol.fw.newFile( outputDir + "\\" + nsfData.getPersonNameFileName( ol.getID() ), rdf );
					
			    }
			    
			    // a person may be the PI of more than one funding award
			    // check if the file already exists
			    String fileName = outputDir + "\\" + nsfData.getPersonRoleFileName( ol.getID() );
			    ol.file = new File ( outputDir + "\\" + nsfData.getPersonRoleFileName( ol.getID() ) );
			    if ( ol.file.exists() ) { 
			    	personAgentRoleUri += ol.getPersonRoleCount();
			    	fileName = outputDir + "\\" + nsfData.getPersonRoleFileName( ol.getID(), ol.getPersonRoleCount() );
			    }
			    // Person Agent Role
				rdf = ol.agentRole.toRdfXml( personAgentRoleUri, personUri, fundingAwardUri, nsfData.getPiRoleTypeUri() );
				ol.fw.newFile( fileName, rdf );
			    
			    /**
			    // does the organization already exist
			    if ( !ol.organizationUriExists(organizationUri) ) {
			    	
			    	ol.organizationUris.add( organizationUri );
			    	
				    // PI's Institution as an organization
				    rdf = ol.organization.toRdfXml( organizationUri, personAgentRoleUri, fundingAwardInformationObjectInstitutionUri, null );
				
				    // Information Object for PI institution
					rdf = ol.informationObject.toRdfXml( fundingAwardInformationObjectFunderUri, nsfData.getInstitution(), nsfData.getInstitution() );
					
			    }
			    **/
			    
				// Funding Award
				rdf = ol.fundingAward.toRdfXml( fundingAwardUri, personUri, personAgentRoleUri, nsfDivisionUri, startDateUri, 
						endDateUri, awardAmountUri, fundingAwardInformationObjectUri, nsfData.getStartDate(), nsfData.getEndDate() );
				ol.fw.newFile( outputDir + "\\" + nsfData.getFundingAwardFileName( ol.getID() ), rdf );
				
				// Funding Award Info Object
				rdf = ol.fundingAwardInfoObject.toRdfXml( fundingAwardInformationObjectUri, nsfData.getAwardID(), nsfData.getTitle(), 
						nsfData.getAbstract() );
				ol.fw.newFile( outputDir + "\\" + nsfData.getFundingAwardInfoObjectFileName( ol.getID() ), rdf );
				
				// Award Amount
				rdf = ol.awardAmount.toRdfXml( awardAmountUri, nsfData.getAwardAmount() );
				ol.fw.newFile( outputDir + "\\" + nsfData.getAwardAmountFileName( ol.getID() ), rdf );
				
				// increment the URI counter
				ol.incrementID();
				
			} catch ( Exception e ) { 
				System.out.println(e); 
			}
			
				
		} // end for
		
	}
	
}