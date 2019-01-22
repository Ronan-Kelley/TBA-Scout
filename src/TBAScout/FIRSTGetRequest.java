package TBAScout;

public class FIRSTGetRequest {
    /*
     * easy to use get request template for FIRST api
     */

    private final String baseURL = ""; //baseURL will always be FIRST/v2.0
    private String[] requestModifiers;
    private String[] headers;
    private String path = "";
    private String key = "";
    private GetRequest gr;

    public FIRSTGetRequest(String[] requestModifiers, String[] headers) {
        this.requestModifiers = requestModifiers;
        this.headers = headers;
        if (Options.getFIRSTKey() == null) {
            System.out.println("no FIRST key found!");
        } else if (Options.getFIRSTKey() != null) {
            this.key = Options.getFIRSTKey();
            doRequest();
        }
    }

    private void doRequest() {

    }

    public String getJson() {
        try {
            return gr.getFinalResponse();
        } catch (Exception e) {
            return "error";
        }
    }
}