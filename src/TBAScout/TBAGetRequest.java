package TBAScout;

public class TBAGetRequest {
    /**
     * simple front end for GetRequest, since all of these should be requests for
     * TBA :]
     */
    private final String baseURL = "https://www.thebluealliance.com/api/v3"; //baseURL will always be blueAlliance
    private String path = "/status"; //default path is status
    private String key = "";
    private GetRequest gr;

    public TBAGetRequest(String path) {
        if (Options.getTBAKey() == null) {
            System.out.println("no TBAKey found");
        } else if (Options.getTBAKey() != null) {
            key = Options.getTBAKey();
            System.out.println("key is " + key);
            this.path = path;
            doRequest();
        }
    }

    public TBAGetRequest() {
        if (Options.getTBAKey() == null) {
            System.out.println("no TBAKey found");
        } else if (Options.getTBAKey() != null) {
            key = Options.getTBAKey();
            System.out.println("key is " + key);
            doRequest();
        }
    }

    public void doRequest() {
        gr = new GetRequest(baseURL, path, new String[] {"X-TBA-Auth-Key", key});
    }

    public void getDataPoint(String dataPoint) {
        gr.getDataPoint(dataPoint);
    }
}