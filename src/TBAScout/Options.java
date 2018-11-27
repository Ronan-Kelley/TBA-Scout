package TBAScout;

public class Options {
    /**
     * this class exists entirely to keep track of command line options
     */
    private Boolean cli = true;

    //
    // getters and setters
    //

    public void setCli(Boolean cli) {
        this.cli = cli;
    }

    public Boolean getCli() {
        return cli;
    }
}