package TBAScout;

public class TBAGetRequest {
    /**
     * simple front end for GetRequest, since all of these should be requests for
     * TBA
     */
    private final String baseURL = "https://www.thebluealliance.com/api/v3"; //baseURL will always be blueAlliance
    private String path = "/status"; //default path is status
    private String key = "";
    private GetRequest gr;
    private Object requestObj;

    public TBAGetRequest(String path) {
        /**
         * check for key, if it isn't present the get request wont work anyways
         */
        if (Options.getTBAKey() == null) {
            System.out.println("no TBAKey found!");
        } else if (Options.getTBAKey() != null) {
            key = Options.getTBAKey();
            System.out.println("key found!");
            this.path = path;
            doRequest();
            requestObj = getObject();
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
            System.out.println("key found!");
            doRequest();
            requestObj = getObject();
        }
    }

    private void doRequest() {
        gr = new GetRequest(baseURL, path, new String[] {"X-TBA-Auth-Key", key});
    }

    private Object getObject() {
        return gr.objectifyRequest();
    }

    public Object getRequestObj() {
        return requestObj;
    }
}