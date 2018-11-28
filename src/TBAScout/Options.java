package TBAScout;

public class Options {
    /**
     * this class exists entirely to keep track of command line options
     */
    private static Boolean cli = false;
    private static String TBAKey = null; //put your key here, or pass it to the program via command line

    //
    // getters and setters
    //

    public static void setCli(Boolean cli) {
        Options.cli = cli;
    }

    public static Boolean getCli() {
        return cli;
    }

    public static void setTBAKey(String TBAKey) {
        Options.TBAKey = TBAKey;
    }

    public static String getTBAKey() {
        return TBAKey;
    }
}