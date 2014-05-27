package earthcube.eager.data;

public class NsfData
{	
	
	private String uriBase = "http://www.oceanlink.org/";

	private String abstrct = null;
    private String uri = null;
    private String title = null;
    private String startDate = null;
    private String endDate = null;
    private String awardAmount = null;
    private String orgCode = null;
    private String piFirstName = null;
    private String piLastName = null;
    private String piEmail = null;
    private String institution = null;
    private String awardID = null;
    private String fundingDivision = null;
    
    public void setAbstract ( String s ) { abstrct = s; }
    public void setUri ( String s ) { uri = s; }
    public void setTitle ( String s ) { title = s; }
    public void setStartDate ( String s ) { startDate = s; }
    public void setEndDate ( String s ) { endDate = s; }
    public void setAwardAmount ( String s ) { awardAmount = s; }
    public void setOrgCode ( String s ) { orgCode = s; }
    public void setPiFirstName ( String s ) { piFirstName = s; }
    public void setPiLastName ( String s ) { piLastName = s; }
    public void setPiEmail ( String s ) { piEmail = s; }
    public void setInstitution ( String s ) { institution = s; }
    public void setAwardID ( String s ) { awardID = s; }
    public void setFundingDivision ( String s ) { fundingDivision = s; }
    
    public String getAbstract () { return abstrct; }
    public String getUri () { return uri; }
    public String getTitle () { return title; }
    public String getStartDate () { return startDate; }
    public String getEndDate () { return endDate; }
    public String getAwardAmount () { return awardAmount; }
    public String getOrgCode () { return orgCode; }
    public String getPiFirstName () { return piFirstName; }
    public String getPiLastName () { return piLastName; }
    public String getPiEmail () { return piEmail; }
    public String getInstitution () { return institution; }
    public String getAwardID () { return awardID; }
    public String getFundingDivision () { return fundingDivision; }
    
    public String getFullName() { return piFirstName.trim() + " " + piLastName.trim(); }
    
    public String getPersonUri( String id ) { 
    	
    	String uri = uriBase + "Person/Person_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfPersonFileName( String id ) {
        
    	String fileName = "NSF_Person_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
      	  fileName += piFirstName.trim() + "_" + piLastName.trim(); 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
      	
    }
 
    public String getPersonAgentRoleUri( String id ) { 
    	
    	String uri = uriBase + "AgentRole/AgentRole_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfPersonRoleFileName( String id, String count ) {
        
    	String fileName = "NSF_PersonRole_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
      	  fileName += piFirstName.trim() + "_" + piLastName.trim(); 
      	} else {
      	  fileName += id;
      	}
      	return fileName += "_" + count + ".rdf";
      	
    }

    public String getNsfPersonRoleFileName( String id ) {
        
    	String fileName = "NSF_PersonRole_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
      	  fileName += piFirstName.trim() + "_" + piLastName.trim(); 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
      	
    }
 
    public String getPersonalInfoItemUri( String id ) { 
    	
    	String uri = uriBase + "PersonalInfoItem/PersonalInfoItem_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfPersonInfoFileName( String id ) {
        
    	String fileName = "NSF_PersonInfo_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
      	  fileName += piFirstName.trim() + "_" + piLastName.trim(); 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
      	
    }
    
    public String getPersonNameUri( String id ) { 
    	
    	String uri = uriBase + "PersonName/PersonName_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfPersonNameFileName( String id ) {
    
    	String fileName = "NSF_PersonName_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
      	  fileName += piFirstName.trim() + "_" + piLastName.trim(); 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
      	
    }
    
    public String getFundingAwardUri( String id ) { 
    	
    	String uri = uriBase + "FundingAward/FundingAward_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfFundingAwardFileName( String id ) {
    	
    	String fileName = "NSF_FundingAward_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
      	  fileName += awardID.trim(); 
      	} else {
      	  fileName += id;
      	}
    	return fileName += ".rdf";
    	
    }
    
    public String getFundingAwardInformationObjectUri( String id ) { 
    	
    	String uri = uriBase + "InformationObject/FundingAwardInformationObject_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfFundingAwardInfoObjectFileName( String id ) {
    	
    	String fileName = "NSF_FundingAwardInfoObject_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
      	  fileName += awardID.trim(); 
      	} else {
      	  fileName += id;
      	}
    	return fileName += ".rdf";
    	
    }
    
    public String getAwardAmountUri( String id ) { 
    	
    	String uri = uriBase + "AwardAmount/AwardAmount_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getNsfAwardAmountFileName( String id ) {
    
    	String fileName = "NSF_AwardAmount_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
      	  fileName += awardID.trim(); 
      	} else {
      	  fileName += id;
      	}
    	return fileName += ".rdf";
    	
    }
    
    public String getStartDateUri( String id ) { 
    	
    	String uri = uriBase + "StartDate/";
    	if ( (startDate != null) && ( startDate.trim() != "") ) {
    	  uri += startDate.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getEndDateUri( String id ) { 
    	
    	String uri = uriBase + "EndDate/";
    	if ( (endDate != null) && ( endDate.trim() != "") ) {
    	  uri += endDate.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
    public String getOrganizationUri( String id ) { 
    	
    	String uri = uriBase + "Agent/Organization_";
    	if ( (institution != null) && ( institution.trim() != "") ) {
    	    institution = institution.trim();
    		uri += institution.replace(" ", "_"); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
   
    public String getPiRoleTypeUri() { return "http://schema.oceanlink.org/agent-role#PrincipalInvestigator"; }
    
    public String getFunderRoleTypeUri() { return "http://schema.oceanlink.org/agent-role#FundingAgency"; }
    
}