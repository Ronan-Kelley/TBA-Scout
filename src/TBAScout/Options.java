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

    public static void handleArgs(String[] args) {
        if (args != null) {
            for (String arg : args) {
                switch (arg) {
                case "-cli":
                    Options.setCli(true);
                    System.out.println("found argument: " + arg);
                    break;
                case "-key":
                    Boolean foundDashKey = false;
                    Boolean foundKey = false;
                    String arg1 = "";
                    for (int i = 0; i < args.length; i++) { //loop back through the array of arguments
                        if (!foundDashKey) {
                            if (args[i].equals("-key")) { //find the argument dictating a key
                                foundDashKey = true;
                            }
                        } else if (foundDashKey && !foundKey) {
                            if (args[i].contains("-") == false) {
                                foundKey = false; //once the argument dictating a key is found, everything after it that comes before the next key should be the actual key
                                arg1 += args[i];
                            } else if (args[i].contains("-")) {
                                foundKey = true;
                            }
                        }
                    }
                    Options.setTBAKey(arg1);
                    System.out.println("found argument pair: " + arg + ": " + arg1);
                    break;
                }
            }
        } else if (args == null) {
            System.out.println("no arguments found!");
        }
    }
}