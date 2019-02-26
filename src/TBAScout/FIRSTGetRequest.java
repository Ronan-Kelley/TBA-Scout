package TBAScout;

import java.util.Base64;

public class FIRSTGetRequest {
    /*
     * easy to use get request template for FIRST api
     */

    private final String baseURL = "https://frc-api.firstinspires.org/v2.0"; //baseURL will always be FIRST/v2.0
    private String[] requestModifiers;
    private String[] headers;
    private String path = "";
    private String key = "";
    private GetRequest gr;

    public FIRSTGetRequest(String path) {
        if (Options.getFIRSTKey() == null) {
            System.out.println("no FIRST key found!");
        } else if (Options.getFIRSTKey() != null) {
            String encKey = Base64.getEncoder().encodeToString(Options.getFIRSTKey().getBytes());
            this.key = "Basic " + encKey;
            this.path = path;
            doRequest();
        }

        headers = new String[] {"Accept", "application/json", "Authorization", this.key};

        doRequest();
    }

    private void doRequest() {
        gr = new GetRequest(baseURL, path, headers);
        // System.out.println("json:\n" + getJson());
        // System.out.println("response code: " + gr.getResponseCode());
    }

    public String getJson() {
        try {
            return gr.getFinalResponse();
        } catch (Exception e) {
            return "error";
        }
    }
}