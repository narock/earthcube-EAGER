package earthcube.eager.data;

public class R2rData {
	
	private String cruiseUri;
	private String cruiseID;
	private String vesselName;
	private String vesselUri;
	
	public String getCruiseUri ( ) { return cruiseUri; }
	public String getCruiseID ( ) { return cruiseID; }
	public String getVesselUri ( ) { return vesselUri; }
	public String getVesselName ( ) { return vesselName; }
	
	public void setCruiseUri ( String u ) { cruiseUri = u; }
	public void setCruiseID ( String a ) { cruiseID = a.trim(); }
	public void setVesselUri ( String s ) { vesselUri = s; }
	public void setVesselName ( String s ) { vesselName = s.trim(); }
	
}