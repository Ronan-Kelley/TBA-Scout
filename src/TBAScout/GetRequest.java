package TBAScout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetRequest {
    /**
     * self explanatory name, yeah? Sends a get request,
     * requires a base URL, path, and headers.
     */
    private final String USER_AGENT = "Mozilla/5.0";
    private String baseURL, path;
    private String[] headers;
    private String finalResponse = null;
    
    public GetRequest(String baseURL, String path, String[] headers) {
        this.baseURL = baseURL;
        this.path = path;
        this.headers = headers;
        try {
            sendGet();
        } catch (Exception e) {
            System.out.println("couldn't send get request!");
        }
    }

    // HTTP GET request
	private void sendGet() throws Exception {
		
		URL obj = new URL(baseURL + path);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        //make get connection, not post
		connection.setRequestMethod("GET");

        //set headers
        connection.setRequestProperty("User-Agent", USER_AGENT);
        for (int i = 0; i < headers.length; i += 2) {
            connection.setRequestProperty(headers[i], headers[i+1]);
        }

		// int responseCode = connection.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + baseURL);
        // System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

        setFinalResponse(response.toString());
        
        // System.out.println(response.toString());

    }
    
    private void setFinalResponse(String finalResponse) {
        this.finalResponse = finalResponse;
    }

    public String getFinalResponse() {
        if (finalResponse != null) {
            return finalResponse;
        } else {
            return "json unavailable";
        }
    }
}