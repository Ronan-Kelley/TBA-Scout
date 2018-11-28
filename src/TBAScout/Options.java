package TBAScout;

public class Options {
    /**
     * this class exists entirely to keep track of command line options
     */
    private Boolean cli = false;
    private String TBAKey = ""; //put your key here, or pass it to the program via command line

    //
    // getters and setters
    //

    public void setCli(Boolean cli) {
        this.cli = cli;
    }

    public Boolean getCli() {
        return cli;
    }

    public void setTBAKey(String TBAKey) {
        this.TBAKey = TBAKey;
    }

    public String getTBAKey() {
        return TBAKey;
    }
}