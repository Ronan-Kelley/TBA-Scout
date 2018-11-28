package TBAScout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
// import javax.script.ScriptException;
import jdk.nashorn.api.scripting.ScriptObjectMirror;


public class GetRequest {
    /**
     * self explanatory name, yeah? Sends a get request,
     * requires a base URL, path, and headers.
     */
    private final String USER_AGENT = "Mozilla/5.0";
    private String baseURL, path;
    private String[] headers;
    private String finalResponse;
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    private ScriptObjectMirror json;
    
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

		int responseCode = connection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + baseURL);
        System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		setFinalResponse(response.toString());

    }
    
    private void setFinalResponse(String finalResponse) {
        this.finalResponse = finalResponse;
    }

    public String getFinalResponse() {
        return finalResponse;
    }

    public String getDataPoint(String key) {
        String dataPoint = "";
        String jsonData = finalResponse;

        try {
            json = (ScriptObjectMirror) engine.eval("JSON");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        json.callMember("parse", jsonData);

        dataPoint = jsonData;

        return dataPoint;
    }
}