package earthcube.eager.read;

import earthcube.eager.data.AguData;
import java.io.FileReader;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class AguRdfData extends DefaultHandler {
 
	AguData aguData = new AguData ();
    StringBuffer accumulator = new StringBuffer();
    String abstrct;
    String title;
    String identifier;
    String uri;
    String session; 
    int count = 0;
    
    public AguData parse ( String file ) throws Exception {
    	XMLReader xr = XMLReaderFactory.createXMLReader();
        AguRdfData handler = new AguRdfData ();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        FileReader r = new FileReader(file);
        xr.parse(new InputSource(r));
        return handler.aguData;
    }

    public AguRdfData() { super(); }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    public void startDocument () { 
    	
    	this.abstrct = null;
    	this.title = null;
    	this.identifier = null;
    	this.uri = null;
    	this.session = null;
    	
    }
    
    public void endDocument () { 
    	
    	this.aguData.setAbstract( this.abstrct );
    	this.aguData.setAbstractID( this.identifier );
    	this.aguData.setSession( this.session );
    	this.aguData.setTitle( this.title );
    	this.aguData.setURI( this.uri );
    
    }
    
    public void startElement (String uri, String name,
		      String qName, Attributes atts) {
    	
    	accumulator.setLength(0); // Ready to accumulate new text
    	if ( name.equals("Description") ) { 
    		// we only care about the first Description tag, which is the AGU presentation uri
    		if ( count == 0 ) { this.uri = atts.getValue("rdf:about"); }
    		count++;
    	}
    	if ( name.equals("relatedToEvent") ) { this.session = atts.getValue("rdf:resource"); }
    	    	
    }
    
    public void endElement (String uri, String name, String qName) {
        
      String d = accumulator.toString().trim();
  	  if ( name.equals("abstract") ) this.abstrct = d;
   	  if ( name.equals("title") ) this.title = d;
  	  if ( name.equals("identifier") ) this.identifier = d;
  	
    }
    
    public void characters (char ch[], int start, int length) { accumulator.append(ch, start, length); }

}
