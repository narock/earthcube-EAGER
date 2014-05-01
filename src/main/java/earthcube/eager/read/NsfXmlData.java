package earthcube.eager.read;

import earthcube.eager.data.NsfData;

import java.io.FileReader;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.jsoup.Jsoup;

public class NsfXmlData extends DefaultHandler {
 
	NsfData nsfData = new NsfData ();
    StringBuffer accumulator = new StringBuffer();
    boolean investigator = false;
    boolean institution = false;
    boolean division = false;
    String piFirstName = null;
    String piLastName = null;
    String piEmail = null;
    
    private String formatText ( String text ) {
    	
    	text = Jsoup.parse(text).text(); 				 // remove HTML markup
  	    text = text.replaceAll("\\\\", ""); 		     // remove \ which are interpreted as escape characters
		text = text.replaceAll("\"",""); 				 // remove " 
		text = text.replaceAll("\\{", ""); 				 // remove {
		text = text.replaceAll("\\}", ""); 				 // remove }
  	    text = text.replaceAll(">", " greater than ");   // remove >
 		text = text.replaceAll("<", " less than "); 	 // remove <
		text = text.replaceAll("&", " and "); 			 // remove &
		return text;
    
    }
    
    public NsfData parse ( String file ) throws Exception {
    	XMLReader xr = XMLReaderFactory.createXMLReader();
        NsfXmlData handler = new NsfXmlData ();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        FileReader r = new FileReader(file);
        xr.parse(new InputSource(r));
        return handler.nsfData;
    }

    public NsfXmlData() { super(); }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    public void startDocument () { }
    
    public void endDocument () { }
    
    public void startElement (String uri, String name, String qName, Attributes atts) {
   
    	accumulator.setLength(0); // Ready to accumulate new text
    	if ( name.equals("Investigator") ) investigator = true;
    	if ( name.equals("Institution") ) institution = true;
    	if ( name.equals("Division") ) division = true;
    	 	    	
    }
    
    public void endElement (String uri, String name, String qName) {
        
      String[] parts;
      String d = accumulator.toString().trim();
  	  if ( name.equals("AwardTitle") ) { this.nsfData.setTitle( this.formatText(d) ); }
  	  if ( name.equals("AwardID") ) this.nsfData.setAwardID(d);
  	  if ( name.equals("AwardEffectiveDate") ) {
  		  
  		  // format the start date to be xsd:date
  		  parts = d.split("/");
  		  if ( parts[0].length() != 2 ) { parts[0] = "0" + parts[0]; }
  		  if ( parts[1].length() != 2 ) { parts[1] = "0" + parts[1]; }
  		  d = parts[2] + "-" + parts[0] + "-" + parts[1];
  		  this.nsfData.setStartDate(d);
  	  }
  	  if ( name.equals("AwardExpirationDate") ) this.nsfData.setEndDate(d);
  	  if ( name.equals("AwardAmount") ) this.nsfData.setAwardAmount(d);
      if ( name.equals("AbstractNarration") ) { this.nsfData.setAbstract( this.formatText(d) ); }
  	  
  	  if ( division ) {
  	     if ( name.equals("LongName") ) this.nsfData.setFundingDivision(d);
  	  }
  	  if ( name.equals("Division") ) { division = false; }
  	  
  	  if ( investigator ) {
  	     if ( name.equals("FirstName") ) piFirstName = d.toUpperCase();
  	     if ( name.equals("LastName") )  piLastName = d.toUpperCase();
  	     if ( name.equals("EmailAddress") ) piEmail = d;
  	     if ( name.equals("RoleCode") ) {
  	    	 if ( d.equals("1") ) { // 1 signifies the PI
  	    		this.nsfData.setPiFirstName( piFirstName );
  	    		this.nsfData.setPiLastName( piLastName );
  	    		this.nsfData.setPiEmail( piEmail );
  	    	 }
  	     }
  	  }
  	  if ( name.equals("Investigator") ) { investigator = false; }
  	  
  	  if ( institution ) {
  	    if (name.equals("Name") ) this.nsfData.setInstitution(d);
  	  }
  	  if ( name.equals("Institution") ) { institution = false; }
  			 
  	
    }
    
    public void characters (char ch[], int start, int length) { accumulator.append(ch, start, length); }

}
