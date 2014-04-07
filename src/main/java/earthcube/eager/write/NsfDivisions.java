package earthcube.eager.write;

import earthcube.eager.util.LongestCommonSubString;

public class NsfDivisions {
	
	private String[] divisionUri = {
			
			"DivisionMolecularAndCellularBiosciences",	 
			"DivisionBiologicalInfrastructure",	
			"DivisionIntegrativeOrganismalSystem",	
			"DivisionOfEnvironmentalBiology",	 
			"DivisionOfAdvancedCyberinfrastructure",	 
			"DivisionOfComputingAndCommunicationFoundations",	
			"DivisionOfComputerAndNetworkSystems",	 
			"DivisionOfInformationAndIntelligentSystems",	 
			"DivisionOfResearchOnLearningInFormalAndInformalSettings",	
			"DivisionOfGraduateEducation",	 
			"DivisionOfHumanResourceDevelopment",	 
			"DivisionOfUndergraduateEducation",	 
			"DivisionOfChemicalBioengineeringEnvironmentalAndTransportSystems",	 
			"DivisionOfCivilMechanicalAndManufacturingInnovation",	 
			"DivisionOfElectricalCommunicationsAndCyberSystems",	 
			"DivisionOfEngineeringEducationAndCenters",	 
			"DivisionOfIndustrialInnovationAndPartnerships",	 
			"DivisionOfAtmosphericAndGeospaceSciences",	 
			"DivisionOfEarthSciences",	
			"DivisionOfOceanSciences",	
			"DivisionOfPolarPrograms",	
			"DivisionOfAstronomicalSciences",
			"DivisionOfChemistry",
			"DivisionOfMaterialsResearch",
			"DivisionOfMathematicalSciences",
			"DivisionOfPhysics",	
			"DivisionOfSocialAndEconomicSciences",	 
			"DivisionOfBehavioralAndCognitiveSciences",	

		};
	
	private String[] divisionFullName = {
	
			"Division of Molecular and Cellular Biosciences",	 
			"Division of Biological Infrastructure",	
			"Division of Integrative Organismal System",	
			"Division of Environmental Biology",	 
			"Division of Advanced Cyberinfrastructure",	 
			"Division of Computing and Communication Foundations",	
			"Division of Computer and Network Systems",	 
			"Division of Information and Intelligent Systems",	 
			"Division of Research on Learning in Formal and Informal Settings",	
			"Division of Graduate Education",	 
			"Division of Human Resource Development",	 
			"Division of Undergraduate Education",	 
			"Division of Chemical, Bioengineering, Environmental, and Transport Systems",	 
			"Division of Civil, Mechanical and Manufacturing Innovation",	 
			"Division of Electrical, Communications and Cyber Systems",	 
			"Division of Engineering Education and Centers",	 
			"Division of Industrial Innovation and Partnerships",	 
			"Division of Atmospheric and Geospace Sciences",	 
			"Division of Earth Sciences",	
			"Division of Ocean Sciences",	
			"Division of Polar Programs",	
			"Division of Astronomical Sciences",
			"Division of Chemistry",
			"Division of Materials Research",
			"Division of Mathematical Sciences",
			"Division of Physics",	
			"Division of Social and Economic Sciences",	 
			"Division of Behavioral and Cognitive Sciences",	

		};
	
	private int findDivision ( String xmlText ) {
		
		 int bestLen = 0;
		 int currentLen;
		 int index = -1;
		 for ( int i=0; i<divisionFullName.length; i++ ) {
			currentLen = LongestCommonSubString.longestSubstr( xmlText, divisionFullName[i] );
		    if ( currentLen > bestLen ) { 
		    	bestLen = currentLen;
		    	index = i;
		    }
		 }
		 return index;
	}
	
}