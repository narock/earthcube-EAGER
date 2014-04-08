package earthcube.eager.data;

public class NsfData
{
	
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
    
    public boolean hasAbstract () { if ( abstrct == null ) { return false; } else { return true; } }
    public boolean hasTitle () { if ( title == null ) { return false; } else { return true; } }
    public boolean hasStartDate () { if ( startDate == null ) { return false; } else { return true; } }
    public boolean hasEndDate () { if ( endDate == null ) { return false; } else { return true; } }
    public boolean hasAwardAmount () { if ( awardAmount == null ) { return false; } else { return true; } }
    public boolean hasOrgCode () { if ( orgCode == null ) { return false; } else { return true; } }
    public boolean hasPiFirstName () { if ( piFirstName == null ) { return false; } else { return true; } }
    public boolean hasPiLastName () { if ( piLastName == null ) { return false; } else { return true; } }
    public boolean hasPiEmail () { if ( piEmail == null ) { return false; } else { return true; } }
    public boolean hasInstitution () { if ( institution == null ) { return false; } else { return true; } }
    public boolean hasAwardID () { if ( awardID == null ) { return false; } else { return true; } }
    public boolean hasFundingDivision () { if ( fundingDivision == null ) { return false; } else { return true; } }
    
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
    
}