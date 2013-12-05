package earthcube.eager.analysis;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import earthcube.eager.services.RestService;

public class FindGazetteerAGU
{
	
	public static void main (String[] args)
	{
		
		// List of gazetteer names
		ArrayList <String> features = new ArrayList <String> ();
		
		// Call the Gazetteer Web Service to get the list of sources
		String source;
		RestService service = new RestService ();
		String request = "http://marineregions.org/rest/getGazetteerSources.json/";
		JSONArray response = service.getJsonResponse(request);
		for (int i = 0; i < response.length(); i++) {
		   try {
			     
			     JSONObject jo = (JSONObject) response.get(i);
			     source = jo.getString("source");
			     
			     // get the features associated with this source
			     String uri = service.encodeURIComponent(source);
			     String request2 = "http://marineregions.org/rest/getGazetteerRecordsBySource.json/";
			     request2 = request2 + uri + "/";
			     
			     JSONArray response2 = service.getJsonResponse( request2 );
			     for (int j=0; j<response2.length(); j++ )
			     {
			    	 JSONObject jo2 = (JSONObject) response2.get(j);
			    	 features.add( jo2.getString("preferredGazetteerName").trim() );
			     }
			     
		   } catch (Exception e) { System.out.println(e); }
		}	  
	
	}
	
}