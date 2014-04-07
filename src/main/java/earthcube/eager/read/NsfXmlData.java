package earthcube.eager.read;

import earthcube.eager.data.NsfData;

import java.io.FileReader;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class NsfXmlData extends DefaultHandler {
 
	NsfData nsfData = new NsfData ();
    StringBuffer accumulator = new StringBuffer();
    boolean investigator = false;
    boolean institution = false;
    boolean division = false;
    
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
        
      String d = accumulator.toString().trim();
  	  if ( name.equals("AwardTitle") ) this.nsfData.setTitle(d);
  	  if ( name.equals("AwardID") ) this.nsfData.setAwardID(d);
  	  if ( name.equals("AwardEffectiveDate") ) this.nsfData.setStartDate(d);
  	  if ( name.equals("AwardExpirationDate") ) this.nsfData.setEndDate(d);
  	  if ( name.equals("AwardAmount") ) this.nsfData.setAwardAmount(d);
      if ( name.equals("AbstractNarration") ) this.nsfData.setAbstract(d);
  	  
  	  if ( division ) {
  	     if ( name.equals("LongName") ) this.nsfData.setFundingDivision(d);
  	  }
  	  if ( name.equals("Division") ) { division = false; }
  	  
  	  if ( investigator ) {
  	     if ( name.equals("FirstName") ) this.piFirstName = d;
  	     if ( name.equals("LastName") ) this.piLastName = d;
  	     if ( name.equals("EmailAddress") ) this.piEmail = d;
  	     if ( name.equals("RoleCode") ) if = 1 then set FirstName LastName
  	  }
  	  if ( name.equals("Investigator") ) { investigator = false; }
  	  
  	  if ( institution ) {
  	    if (name.equals("Name") ) this.institution = d;
  	  }
  	  if ( name.equals("Institution") ) { institution = false; }
  			 
  	
    }
    
    public void characters (char ch[], int start, int length) { accumulator.append(ch, start, length); }

}
