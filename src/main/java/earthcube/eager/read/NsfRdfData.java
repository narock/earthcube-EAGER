package earthcube.eager.read;

import earthcube.eager.data.NsfData;
import java.io.FileReader;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class NsfRdfData extends DefaultHandler {
 
	NsfData nsfData = new NsfData ();
    StringBuffer accumulator = new StringBuffer();
    String abstrct;
    String uri;
    int count = 0;
    
    public NsfData parse ( String file ) throws Exception {
    	XMLReader xr = XMLReaderFactory.createXMLReader();
        NsfRdfData handler = new NsfRdfData ();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        FileReader r = new FileReader(file);
        xr.parse(new InputSource(r));
        return handler.nsfData;
    }

    public NsfRdfData() { super(); }

    ////////////////////////////////////////////////////////////////////
    // Event handlers.
    ////////////////////////////////////////////////////////////////////

    public void startDocument () { 
    	
    	this.abstrct = null;
    	
    }
    
    public void endDocument () { 
    	
    	this.nsfData.setAbstract( this.abstrct );
    	this.nsfData.setUri( this.uri );
    
    }
    
    public void startElement (String uri, String name,
		      String qName, Attributes atts) {
    	
    	accumulator.setLength(0); // Ready to accumulate new text
    	if ( name.equals("Description") ) { 
    		// we only care about the first Description tag, which is the NSF uri
    		if ( count == 0 ) { this.uri = atts.getValue("rdf:about"); }
    		count++;
    	}
    	    	
    }
    
    public void endElement (String uri, String name, String qName) {
        
      String d = accumulator.toString().trim();
  	  if ( name.equals("hasDescription") ) this.abstrct = d;
  	
    }
    
    public void characters (char ch[], int start, int length) { accumulator.append(ch, start, length); }

}
