package earthcube.eager.write;

import earthcube.eager.util.LongestCommonSubString;

public class NsfDivisions {
	
	public String[] getNsfDivsionUris () { return divisionUri; }
	public String[] getNsfDivsionInfoObjectUris () { return divisionInfoObjectUri; }
	public String[] getNsfDivisions () { return divisionFullName; }
	public String[] getNSfDivisionsShortName () { return matchName; }
	
	private String[] divisionUri = {
			
			"http://www.oceanlink.org/Organization/NSF_DivisionMolecularAndCellularBiosciences",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionBiologicalInfrastructure",	
			"http://www.oceanlink.org/Organization/NSF_DivisionIntegrativeOrganismalSystem",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfEnvironmentalBiology",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfAdvancedCyberinfrastructure",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfComputingAndCommunicationFoundations",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfComputerAndNetworkSystems",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfInformationAndIntelligentSystems",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfResearchOnLearningInFormalAndInformalSettings",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfGraduateEducation",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfHumanResourceDevelopment",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfUndergraduateEducation",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfChemicalBioengineeringEnvironmentalAndTransportSystems",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfCivilMechanicalAndManufacturingInnovation",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfElectricalCommunicationsAndCyberSystems",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfEngineeringEducationAndCenters",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfIndustrialInnovationAndPartnerships",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfAtmosphericAndGeospaceSciences",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfEarthSciences",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfOceanSciences",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfPolarPrograms",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfAstronomicalSciences",
			"http://www.oceanlink.org/Organization/NSF_DivisionOfChemistry",
			"http://www.oceanlink.org/Organization/NSF_DivisionOfMaterialsResearch",
			"http://www.oceanlink.org/Organization/NSF_DivisionOfMathematicalSciences",
			"http://www.oceanlink.org/Organization/NSF_DivisionOfPhysics",	
			"http://www.oceanlink.org/Organization/NSF_DivisionOfSocialAndEconomicSciences",	 
			"http://www.oceanlink.org/Organization/NSF_DivisionOfBehavioralAndCognitiveSciences",	

		};
	
    private String[] divisionInfoObjectUri = {
			
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionMolecularAndCellularBiosciences",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionBiologicalInfrastructure",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionIntegrativeOrganismalSystem",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfEnvironmentalBiology",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfAdvancedCyberinfrastructure",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfComputingAndCommunicationFoundations",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfComputerAndNetworkSystems",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfInformationAndIntelligentSystems",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfResearchOnLearningInFormalAndInformalSettings",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfGraduateEducation",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfHumanResourceDevelopment",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfUndergraduateEducation",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfChemicalBioengineeringEnvironmentalAndTransportSystems",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfCivilMechanicalAndManufacturingInnovation",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfElectricalCommunicationsAndCyberSystems",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfEngineeringEducationAndCenters",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfIndustrialInnovationAndPartnerships",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfAtmosphericAndGeospaceSciences",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfEarthSciences",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfOceanSciences",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfPolarPrograms",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfAstronomicalSciences",
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfChemistry",
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfMaterialsResearch",
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfMathematicalSciences",
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfPhysics",	
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfSocialAndEconomicSciences",	 
			"http://www.oceanlink.org/InformationObject/InformationObject_NSF_DivisionOfBehavioralAndCognitiveSciences",	

	};
	
	private String[] divisionFullName = {
	
			"National Science Foundation, Division of Molecular and Cellular Biosciences",	 
			"National Science Foundation, Division of Biological Infrastructure",	
			"National Science Foundation, Division of Integrative Organismal System",	
			"National Science Foundation, Division of Environmental Biology",	 
			"National Science Foundation, Division of Advanced Cyberinfrastructure",	 
			"National Science Foundation, Division of Computing and Communication Foundations",	
			"National Science Foundation, Division of Computer and Network Systems",	 
			"National Science Foundation, Division of Information and Intelligent Systems",	 
			"National Science Foundation, Division of Research on Learning in Formal and Informal Settings",	
			"National Science Foundation, Division of Graduate Education",	 
			"National Science Foundation, Division of Human Resource Development",	 
			"National Science Foundation, Division of Undergraduate Education",	 
			"National Science Foundation, Division of Chemical, Bioengineering, Environmental, and Transport Systems",	 
			"National Science Foundation, Division of Civil, Mechanical and Manufacturing Innovation",	 
			"National Science Foundation, Division of Electrical, Communications and Cyber Systems",	 
			"National Science Foundation, Division of Engineering Education and Centers",	 
			"National Science Foundation, Division of Industrial Innovation and Partnerships",	 
			"National Science Foundation, Division of Atmospheric and Geospace Sciences",	 
			"National Science Foundation, Division of Earth Sciences",	
			"National Science Foundation, Division of Ocean Sciences",	
			"National Science Foundation, Division of Polar Programs",	
			"National Science Foundation, Division of Astronomical Sciences",
			"National Science Foundation, Division of Chemistry",
			"National Science Foundation, Division of Materials Research",
			"National Science Foundation, Division of Mathematical Sciences",
			"National Science Foundation, Division of Physics",	
			"National Science Foundation, Division of Social and Economic Sciences",	 
			"National Science Foundation, Division of Behavioral and Cognitive Sciences",	

	};
	
	private String[] matchName = {
			
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
	
	public String findDivision ( String xmlText ) {
		
		 if ( xmlText.equals("") || xmlText.equals(null) ) {
			 return "http://www.oceanlink.org/InformationObject/InformationObject_National_Science_Foundation";
		 } else {
			 int bestLen = 0;
			 int currentLen;
			 int index = -1;
			 for ( int i=0; i<matchName.length; i++ ) {
				 currentLen = LongestCommonSubString.longestSubstr( xmlText, matchName[i] );
				 if ( currentLen > bestLen ) { 
					 bestLen = currentLen;
					 index = i;
				 }
			 }
			 return divisionUri[index];
		 }
		 
	}
	
}