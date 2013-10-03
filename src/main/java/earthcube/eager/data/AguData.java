package earthcube.eager.data;

public class AguData
{
	
	private String abstrct;
    private String title;
    private String identifier;
    private String uri;
    private String session; 
    
    public void setAbstract ( String s ) { abstrct = s; }
    public void setTitle ( String s ) { title = s; }
    public void setAbstractID ( String s ) { identifier = s; }
    public void setURI ( String s ) { uri = s; }
    public void setSession ( String s ) { session = s; }
    
    public String getAbstract () { return abstrct; }
    public String getTitle () { return title; }
    public String getIdentifier () { return identifier; }
    public String getUri () { return uri; }
    public String getSession () { return session; }
    
}