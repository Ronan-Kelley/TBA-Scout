package TBAScout.restrequests;

public class TBAGetRequest {
    /**
     * simple front end for GetRequest, since all of these should be requests for
     * TBA
     */

    private final String baseURL = "https://www.thebluealliance.com/api/v3"; //baseURL will always be blueAlliance
    private String path = "/status"; //default path is status
    private String key = "";
    private GetRequest gr;

    public TBAGetRequest(String path) {
        /**
         * check for key, if it isn't present the get request wont work anyways
         */
        if (Options.getTBAKey() == null) {
            System.out.println("no TBAKey found!");
        } else if (Options.getTBAKey() != null) {
            key = Options.getTBAKey();
            this.path = path;
            doRequest();
        }
    }

    public TBAGetRequest() {
        /**
         * check for key, if it isn't present the get request wont work anyways
         */
        if (Options.getTBAKey() == null) {
            System.out.println("no TBAKey found!");
        } else if (Options.getTBAKey() != null) {
            key = Options.getTBAKey();
            doRequest();
        }
    }

    private void doRequest() {
        gr = new GetRequest(baseURL, path, new String[] {"X-TBA-Auth-Key", key});
    }

    public String getPath() {
        return path;
    }

    public String getJson() {
        try {
        return gr.getFinalResponse();
        } catch (NullPointerException e) {
            return "error";
        }
    }
}