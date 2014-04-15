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
	
	public ArrayList <String> getListOfXmlFiles ( String dir, boolean verbose ) {
		
		ArrayList <String> xmlFiles = getFileList.Process( new File (dir) );
		if ( verbose ) { System.out.println( "There are " + xmlFiles.size() + " XML files"); }
		return xmlFiles;
		
	}
			
	public static void main (String[] args) {
				
		// Inputs
		String inputDir = args[0];
				
		String rdf;
		OceanLink ol = new OceanLink ();

		// Create NSF as an Organization and NSF Divisions as Organizations
		NsfDivisions nsfDivisions = new NsfDivisions ();
		String[] divisions = nsfDivisions.getNsfDivisions();
		String[] divisionUri = nsfDivisions.getNsfDivsionUris();
		String[] divisionInfoObjectUri = nsfDivisions.getNsfDivsionInfoObjectUris();
		//// NSF
		rdf = ol.organization.toRdfXml( "http://www.oceanlink.org/Organization/National_Science_Foundation", 
				funderRoleUri, "http://www.oceanlink.org/InformationObject/InformationObject_National_Science_Foundation",
			    divisionUri, null);
		rdf = ol.informationObject.toRdfXml( "http://www.oceanlink.org/InformationObject/InformationObject_National_Science_Foundation", 
				"The National Science Foundation", "The National Science Foundation");		
        //// NSF Divisions
		for ( int i=0; i<divisionUri.length; i++ ) {
			rdf = ol.organization.toRdfXml( divisionUri[i], funderRoleUri, divisionInfoObjectUri[i], null );
			// Create associated Information Object
			rdf = ol.informationObject.toRdfXml( divisionInfoObjectUri[i], divisions[i], divisions[i] );			
		}
		
		// Find all the XML files
		ArrayList <String> xmlFiles = ol.getListOfXmlFiles( inputDir, true );
			
		// Loop over all the files
		String personUri, personAgentRoleUri, personalInfoItemUri, personNameUri, fundingAwardUri, fundingAwardInformationObjectFunderUri,
		fundingAwardInformationObjectInstitutionUri, organizationUri, awardAmountUri, startDateUri, endDateUri, fundingAwardInformationObjectUri;
		
		for ( int i=0; i<xmlFiles.size(); i++ ) {
					
			NsfData nsfData = null;
			try {
				
				// parse the XML file
				nsfData = ol.nsfParser.parse( xmlFiles.get(i) );

				// find the NSF Division
				String[] nsfDivision = nsfDivisions.findDivision( nsfData.getFundingDivision() );
				
				// create URIs
				personUri = nsfData.getPersonUri();
			    personAgentRoleUri = nsfData.getPersonAgentRoleUri();
			    personalInfoItemUri = nsfData.getPersonalInfoItemUri();
			    personNameUri = nsfData.getPersonNameUri();
			    fundingAwardUri = nsfData.getFundingAwardUri();
			    fundingAwardInformationObjectFunderUri = nsfData.getFundingAwardInformationObjectFunderUri();
			    fundingAwardInformationObjectInstitutionUri = nsfData.getFundingAwardInformationObjectInstitutionUri();
			    fundingAwardInformationObjectUri = nsfData.getFundingAwardInformationObjectUri();
			    organizationUri = nsfData.getOrganizationUri();
			    awardAmountUri = nsfData.getAwardAmountUri();
			    startDateUri = nsfData.getStartDateUri();
			    endDateUri = nsfData.getEndDateUri();
			    
			    does person and org uri already exist, if so then don't create person and org
			    
				// write out the RDF/XML
				
				// RDF for the Principal Investigator
					// Person 
					rdf = ol.person.toRdfXml( personUri, personAgentRoleUri, null, personalInfoItemUri );
					// Person Agent Role
					rdf = ol.agentRole.toRdfXml( personAgentRoleUri, personUri, fundingAwardUri, nsfData.getPiRoleTypeUri() );
					//Personal Info
					rdf = ol.personInfoItem.toRdfXml( personalInfoItemUri, personUri, personNameUri );
					// Person Name
					rdf = ol.personName.toRdfXml( personNameUri, nsfData.getFullName(), nsfData.getPiFirstName(), nsfData.getPiLastName() );
					
				// PI's Institution as an organization
				rdf = ol.organization.toRdfXml( organizationUri, personAgentRoleUri, fundingAwardInformationObjectInstitutionUri, null );
				
				// Funding Award
				rdf = ol.fundingAward.toRdfXml( fundingAwardUri, personUri, personAgentRoleUri, nsfDivision[0], startDateUri, endDateUri, 
						awardAmountUri, fundingAwardInformationObjectUri, nsfData.getStartDate(), nsfData.getEndDate() );
					
				// Funding Award Info Object
				rdf = ol.fundingAwardInfoObject.toRdfXml( fundingAwardInformationObjectUri, nsfData.getAwardID(), nsfData.getTitle(), nsfData.getAbstract() );
				
				// Award Amount
				rdf = ol.awardAmount.toRdfXml( awardAmountUri, nsfData.getAwardAmount() );
				
				// Information Object for PI institution
				rdf = ol.informationObject.toRdfXml( fundingAwardInformationObjectFunderUri, nsfDivision[1], nsfDivision[1] );
								
				// increment the URI counter
				nsfData.createID();
				
			} catch ( Exception e ) { System.out.println(e); }
			
				
		} // end for
		
	}
	
}