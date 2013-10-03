package earthcube.eager.data;

public class NsfData
{
	
	private String abstrct = null;
    private String uri;
    
    public void setUri ( String s ) { uri = s; }
    public void setAbstract ( String s ) { abstrct = s; }
    
    public boolean hasAbstract () { if ( abstrct == null ) { return false; } else { return true; } }
    public String getUri () { return uri; }
    public String getAbstract () { return abstrct; }
    
}