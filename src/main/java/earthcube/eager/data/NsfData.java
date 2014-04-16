package earthcube.eager.data;

public class NsfData
{	
	
	private String uriBase = "http://www.oceanlink.org/";

	private long idCounter = 0;
	public void createID() { idCounter++; }
	public String getID() { return String.valueOf(idCounter); }
	
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
    
    public String getPersonUri() { 
    	
    	String uri = uriBase + "Person/Person_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getPersonAgentRoleUri() { 
    	
    	String uri = uriBase + "AgentRole/AgentRole_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getPersonalInfoItemUri() { 
    	
    	String uri = uriBase + "PersonalInfoItem/PersonalInfoItem_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getPersonNameUri() { 
    	
    	String uri = uriBase + "PersonName/PersonName_";
    	if ( (piFirstName != null) && (piLastName != null) ) {
    	  uri += piFirstName.trim() + "_" + piLastName.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getFundingAwardUri() { 
    	
    	String uri = uriBase + "FundingAward/FundingAward_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getFundingAwardInformationObjectFunderUri() { 
    	
    	String uri = uriBase + "InformationObject/FundingAward_Funder_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getFundingAwardInformationObjectInstitutionUri() { 
    	
    	String uri = uriBase + "InformationObject/FundingAward_Institution_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getFundingAwardInformationObjectUri() { 
    	
    	String uri = uriBase + "InformationObject/FundingAwardInformationObject_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getAwardAmountUri() { 
    	
    	String uri = uriBase + "AwardAmount/AwardAmount_";
    	if ( (awardID != null) && ( awardID.trim() != "") ) {
    	  uri += awardID.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getStartDateUri() { 
    	
    	String uri = uriBase + "StartDate/";
    	if ( (startDate != null) && ( startDate.trim() != "") ) {
    	  uri += startDate.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getEndDateUri() { 
    	
    	String uri = uriBase + "EndDate/";
    	if ( (endDate != null) && ( endDate.trim() != "") ) {
    	  uri += endDate.trim(); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getOrganizationUri() { 
    	
    	String uri = uriBase + "Agent/Organization_";
    	if ( (institution != null) && ( institution.trim() != "") ) {
    	    institution = institution.trim();
    		uri += institution.replace(" ", "_"); 
    	} else {
    	  uri += getID();
    	}
    	return uri;
    	
    }
    
    public String getPiRoleTypeUri() { return "http://www.oceanlink.org/AgentRoleType/PrincipalInvestigator"; }
    
    public String getFunderRoleTypeUri() { return "http://www.oceanlink.org/AgentRoleType/FundingAgency"; }
    
}