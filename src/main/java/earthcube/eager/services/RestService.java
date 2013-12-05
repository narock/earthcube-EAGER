package earthcube.eager.services;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.*;
import java.net.URLEncoder;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class RestService {

   public static void main (String[] args)
   {
	   RestService r = new RestService ();
	   String request = "http://marineregions.org/rest/getGazetteerSources.json/";
	   JSONArray response = r.getJsonResponse(request);
	   for (int i = 0; i < response.length(); i++) {
	      try {
		     JSONObject jo = (JSONObject) response.get(i);
		     String source = jo.getString("source"); 
		     System.out.println( source );
	      } catch (Exception e) { System.out.println(e); }
	  }	   
   }
   
   public String encodeURIComponent(String s) {
	    String result;

	    try {
	        result = URLEncoder.encode(s, "UTF-8")
	                .replaceAll("\\+", "%20")
	                .replaceAll("\\%21", "!")
	                .replaceAll("\\%27", "'")
	                .replaceAll("\\%28", "(")
	                .replaceAll("\\%29", ")")
	                .replaceAll("\\%7E", "~");
	    } catch (UnsupportedEncodingException e) {
	        result = s;
	    }

	    return result;
	}
   
   public JSONArray getJsonResponse (String request) {
          
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(request);
        
        // Send request
        String line = "";
        final StringBuilder builder = new StringBuilder(2048);
        try {
        	
        	int statusCode = client.executeMethod(method);
        
        	if (statusCode != HttpStatus.SC_OK) {
        		System.err.println("Method failed: " + method.getStatusLine());
        	}
        	InputStream rstream = null;
        
        	// Get the response body
        	rstream = method.getResponseBodyAsStream();
        
        	// Process the response 
        	BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
        	while ((line = br.readLine()) != null) { builder.append(line); }
        	br.close();
        
        } catch (Exception e) { System.out.println(e); }
        
        // convert response to JSON array
        JSONArray jsa = null;
        try {
        	jsa = new JSONArray(builder.toString());
        } catch (Exception e) { System.out.println(e); }
        
        return jsa;
   } 

}