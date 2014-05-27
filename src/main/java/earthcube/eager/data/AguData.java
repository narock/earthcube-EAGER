package earthcube.eager.data;

import java.util.ArrayList;
import java.util.HashMap;

public class AguData
{
	
	private String uriBase = "http://www.oceanlink.org/";
	
	private String title = null;
	private String text = null;
	private ArrayList <String> keywords = new ArrayList <String> ();
	private String abstractUri = null;
	private HashMap <String, String> authors = new HashMap <String, String> ();
	private HashMap <String, String> authorData = new HashMap <String, String> ();
	
	public void setAbstractTitle ( String t ) { title = t; }
	public void setAbstractText ( String t ) { text = t; }
	public void setAbstractUri ( String t ) { abstractUri = t; }
	public void addKeyword ( String k ) { keywords.add( k ); }
	public void addAuthor ( String uri, String index ) { authors.put( uri, index ); }
	public void addAuthorData ( String uri, String data ) { authorData.put( uri, data ); }	

	public String getAbstractTitle () { return title; }
	public String getAbstractText () { return text; }
	public String getAbstractUri () { return abstractUri; }
	public ArrayList <String> getKeywords () { return keywords; }
	public HashMap <String,String> getAuthors () { return authors; }
	public HashMap <String,String> getAuthorsData () { return authorData; }
	
	public boolean authorDataExists ( String uri ) {
		if ( authorData.containsKey(uri) ) { return true; } else { return false; }
	}
	
	public boolean authorExists ( String uri ) {
		if ( authors.containsValue( uri ) ) { return true; } else { return false; }
	}
	
	public boolean keywordExists ( String k ) {
		if ( keywords.contains(k) ) { return true; } else { return false; }
	}
	
	public String getPlaceUri( String place ) { return uriBase + "Place/" + place; }
	
	public String getInformationObjectUri( String meeting ) { return uriBase + "InformationObject/" + meeting; }
	
	public String getFirstName( String authorName ) {
		String[] parts = authorName.split("_");
		if ( parts.length > 1 ) { return parts[1].trim(); } else { return authorName; }
	}
	
	public String getLastName( String authorName ) {
		String[] parts = authorName.split("_");
		return parts[0];
	}
	
	public String getFullName( String authorName ) {
		// authorName is lastName_FirstInitial_MiddleInitial
		String fullName = "";
		String[] parts = authorName.split("_");
		if ( parts.length > 1 ) { fullName = parts[1] + " "; }
		if ( parts.length == 3 ) { fullName += parts[2] + " "; }
		if ( parts.length > 0 ) { fullName += parts[0]; } else { fullName = "Unknown"; }
		return fullName.trim();
	}
	 
	public String getAguPersonRoleFileName( String id, String authorName ) {
        
		String fileName = "AGU_PersonRole_";
    	if ( (authorName != null) && (authorName != "") ) {
      	  fileName += authorName; 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
	      	
	}
	
	public String getAguPersonRoleFileName( String id, String count, String authorName ) {
	        
	    String fileName = "AGU_PersonRole_";
	    if ( (authorName != null) && (authorName != "") ) {
	      	  fileName += authorName; 
	    } else {
	      	  fileName += id;
	    }
	    return fileName += "_" + count + ".rdf";
	      	
	}
	 
	public String getPersonalInfoItemUri( String id, String authorName ) { 
    	
    	String uri = uriBase + "PersonalInfoItem/PersonalInfoItem_";
    	if ( (authorName != null) && (authorName != "") ) {
    	  uri += authorName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
    
	public String getPersonNameUri( String id, String authorName ) { 
    	
    	String uri = uriBase + "PersonName/PersonName_";
    	if ( (authorName != null) && (authorName != "") ) {
    	  uri += authorName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }
	
	public String getAguPersonFileName( String id, String authorName ) {
        
    	String fileName = "AGU_Person_";
    	if ( (authorName != null) && (authorName != "") ) {
      	  fileName += authorName; 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
      	
    }

    public String getAguPersonNameFileName( String id, String authorName ) {
		    
	    	String fileName = "AGU_PersonName_";
	    	if ( (authorName != null) && (authorName != "") ) {
	      	  fileName += authorName; 
	      	} else {
	      	  fileName += id;
	      	}
	      	return fileName += ".rdf";
	      	
	}
    
    public String getAguPersonInfoFileName( String id, String authorName ) {
        
    	String fileName = "AGU_PersonInfo_";
    	if ( (authorName != null) && (authorName != "") ) {
      	  fileName += authorName; 
      	} else {
      	  fileName += id;
      	}
      	return fileName += ".rdf";
      	
    }

	public String getPersonAgentRoleUri( String id, String authorName ) { 
    	
    	String uri = uriBase + "AgentRole/AgentRole_";
    	if ( (authorName != null) && (authorName != "") ) {
    	  uri += authorName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }

	public String getPersonUri( String id, String authorName ) { 
    	
    	String uri = uriBase + "Person/Person_";
    	if ( (authorName != null) && (authorName != "") ) {
    	  uri += authorName.trim(); 
    	} else {
    	  uri += id;
    	}
    	return uri;
    	
    }

	public String[] getOceanMeetingTimes() {
		
		String[] times = {
				
				"2000-01-24:2000-01-28",
				"2002-02-11:2002-02-15",
				"2004-02-26:2004-02-30",
				"2006-02-20:2006-02-24",
				"2010-02-22:2010-02-26",
				
		};
		return times;
		
	}
	
	public String[] getFallMeetingTimes() {
	
		String[] times = {
				
				"2000-12-15:2000-12-19",
				"2001-12-10:2001-12-14",
				"2002-12-06:2002-12-10",
				"2003-12-08:2003-12-12",
				"2004-12-13:2004-12-17",
				"2005-12-05:2005-12-09",
				"2006-12-11:2006-12-15",
				"2007-12-10:2007-12-14",
				"2008-12-15:2008-12-19",
				"2009-12-14:2009-12-18",
				"2010-12-13:2010-12-17",
				"2011-12-05:2011-12-09",
				"2012-12-03:2012-12-07",
				"2013-12-09:2013-12-13"
				
		};
		return times;
		
	}
	
    public String[] getOceanTimeBeginEndUris( String instant ) {
			
		String[] eventUris = {
				uriBase + "Time/" + instant + "/AguOceanSciencesMeeting2000",
				uriBase + "Time/" + instant + "/AguOceanSciencesMeeting2002",
				uriBase + "Time/" + instant + "/AguOceanSciencesMeeting2004",
				uriBase + "Time/" + instant + "/AguOceanSciencesMeeting2006",
				uriBase + "Time/" + instant + "/AguOceanSciencesMeeting2010"
		};
		return eventUris;
	
    }
	 
    public String[] getFallTimeBeginEndUris( String instant ) {
		
		String[] fallEventUris = {
				uriBase + "Time/" +  instant + "/AguFallMeeting2000",
				uriBase + "Time/" +  instant + "/AguFallMeeting2001",
				uriBase + "Time/" +  instant + "/AguFallMeeting2002",
				uriBase + "Time/" +  instant + "/AguFallMeeting2003",
				uriBase + "Time/" +  instant + "/AguFallMeeting2004",
				uriBase + "Time/" +  instant + "/AguFallMeeting2005",
				uriBase + "Time/" +  instant + "/AguFallMeeting2006",
				uriBase + "Time/" +  instant + "/AguFallMeeting2007",
				uriBase + "Time/" +  instant + "/AguFallMeeting2008",
				uriBase + "Time/" +  instant + "/AguFallMeeting2009",
				uriBase + "Time/" +  instant + "/AguFallMeeting2010",
				uriBase + "Time/" +  instant + "/AguFallMeeting2011",
				uriBase + "Time/" +  instant + "/AguFallMeeting2012",
				uriBase + "Time/" +  instant + "/AguFallMeeting2013"
		};
		return fallEventUris;
	}

    public String[] getFallTimeUris() {
		
		String[] fallEventUris = {
				uriBase + "Time/AguFallMeeting2000",
				uriBase + "Time/AguFallMeeting2001",
				uriBase + "Time/AguFallMeeting2002",
				uriBase + "Time/AguFallMeeting2003",
				uriBase + "Time/AguFallMeeting2004",
				uriBase + "Time/AguFallMeeting2005",
				uriBase + "Time/AguFallMeeting2006",
				uriBase + "Time/AguFallMeeting2007",
				uriBase + "Time/AguFallMeeting2008",
				uriBase + "Time/AguFallMeeting2009",
				uriBase + "Time/AguFallMeeting2010",
				uriBase + "Time/AguFallMeeting2011",
				uriBase + "Time/AguFallMeeting2012",
				uriBase + "Time/AguFallMeeting2013"
		};
		return fallEventUris;
	}
    
    public String[] getOceanTimeUris() {
		
		String[] eventUris = {
				uriBase + "Time/AguOceanSciencesMeeting2000",
				uriBase + "Time/AguOceanSciencesMeeting2002",
				uriBase + "Time/AguOceanSciencesMeeting2004",
				uriBase + "Time/AguOceanSciencesMeeting2006",
				uriBase + "Time/AguOceanSciencesMeeting2010",
		};
		return eventUris;
	}

	public String[] getFallEventUris() {
		
		String[] fallEventUris = {
				uriBase + "Event/AguFallMeeting2000",
				uriBase + "Event/AguFallMeeting2001",
				uriBase + "Event/AguFallMeeting2002",
				uriBase + "Event/AguFallMeeting2003",
				uriBase + "Event/AguFallMeeting2004",
				uriBase + "Event/AguFallMeeting2005",
				uriBase + "Event/AguFallMeeting2006",
				uriBase + "Event/AguFallMeeting2007",
				uriBase + "Event/AguFallMeeting2008",
				uriBase + "Event/AguFallMeeting2009",
				uriBase + "Event/AguFallMeeting2010",
				uriBase + "Event/AguFallMeeting2011",
				uriBase + "Event/AguFallMeeting2012",
				uriBase + "Event/AguFallMeeting2013"
		};
		return fallEventUris;
	}
	
	public String[] getOceanScienceEventUris() {
		
		String[] eventUris = {
				uriBase + "Event/AguOceanSciencesMeeting2000",
				uriBase + "Event/AguOceanSciencesMeeting2002",
				uriBase + "Event/AguOceanSciencesMeeting2004",
				uriBase + "Event/AguOceanSciencesMeeting2006",
				uriBase + "Event/AguOceanSciencesMeeting2010",
		};
		return eventUris;
	}
	
	public String getRepositoryObjectUri( String id ) { 
    	
    	String uri = uriBase + "RepositoryObject/RepositoryObject_";
    	uri += id;
    	return uri;
    	
    }
    
    public String getAuthorRoleTypeUri() { return "http://schema.oceanlink.org/agent-role#Author"; }

}