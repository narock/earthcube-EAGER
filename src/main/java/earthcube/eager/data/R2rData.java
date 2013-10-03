package earthcube.eager.data;

public class R2rData {
	
	private String uri;
	private String cruiseID;
	private String cruiseTitle;
	private String vesselName;
		
	public String getUri ( ) { return uri; }
	public String getCruiseID ( ) { return cruiseID; }
	public String getCruiseTitle ( ) { return cruiseTitle; }
	public String getVesselName ( ) { return vesselName; }
	
	public void setUri ( String u ) { uri = u; }
	public void setCruiseID ( String a ) { cruiseID = a.trim(); }
	public void setCruiseTitle ( String s ) { cruiseTitle = s; }
	public void setVesselName ( String s ) { vesselName = s.trim(); }
	
}