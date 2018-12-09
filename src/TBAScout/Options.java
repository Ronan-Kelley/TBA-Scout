package TBAScout;

public class Options {
    /**
     * this class exists entirely to keep track of command line options
     */
    private static Boolean cli = false;
    private static String TBAKey = null; // put your key here, or pass it to the program as an argument
    private static String path = "none";

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

    public static void setPath(String path) {
        Options.path = path;
    }

    public static String getPath() {
        return path;
    }

    public static void handleArgs(String[] args) {
        if (args != null) {
            for (String arg : args) {
                switch (arg) {
                case "-cli": // check for "-cli" argument, disable graphics if present
                    Options.setCli(true);
                    // System.out.println("found argument: " + arg);
                    break;
                case "-key": // make sure "-key" is specified before looping through all this
                    Boolean foundDashKey = false;
                    Boolean foundKey = false;
                    String keyArg = "";
                    for (int i = 0; i < args.length; i++) { // loop back through the array of arguments
                        if (!foundDashKey) {
                            if (args[i].equals("-key")) { // find the argument dictating a key
                                foundDashKey = true;
                            }
                        } else if (foundDashKey && !foundKey) {
                            if (args[i].contains("-") == false) {
                                foundKey = false; // once the argument dictating a key is found, everything after it
                                                  // that comes before the next key should be the actual key
                                keyArg += args[i];
                            } else if (args[i].contains("-")) {
                                foundKey = true;
                            }
                        }
                    }
                    Options.setTBAKey(keyArg);
                    // System.out.println("found argument pair: " + arg + ": " + keyArg);
                    break;

                case "-path":
                    /**
                     * logic here is identical to the above case, if there is confusion check those
                     * comments
                     */
                    Boolean foundDashPath = false;
                    Boolean foundPath = false;
                    String pathArg = "";

                    for (int i = 0; i < args.length; i++) {
                        if (!foundDashPath) {
                            if (args[i].equals("-path")) {
                                foundDashPath = true;
                            }
                        } else if (foundDashPath && !foundPath) {
                            if (args[i].contains("-") == false) {
                                foundPath = false;
                                pathArg += args[i];
                            } else if (args[i].contains("-")) {
                                foundPath = true;
                            }
                        }
                    }
                    // System.out.println("found argument pair: " + arg + ": " + pathArg);
                    Options.setPath(pathArg.replace(" ", "")); // make sure the path doesn't include spaces, since none
                                                               // of the actual paths on TBA do
                    break;
                }
            }
        } else if (args == null) {
            System.out.println("no arguments found!");
        }

        if (Options.getCli() && !Options.getPath().equals("none")) {
            System.out.println(new TBAGetRequest(Options.getPath()).getJson());
        }
    }
}