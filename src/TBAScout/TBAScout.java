package TBAScout;

import java.awt.EventQueue;

public class TBAScout {

    public static void main(String[] args) {
        /**
         * command line options should mostly be handled here, and should follow the
         * convention of starting with a dash
         */

        System.out.println("launched");

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

        // GetRequest gr = new GetRequest("https://www.thebluealliance.com/api/v3", "/status", new String[] {"X-TBA-Auth_Key", options.getTBAKey()});
        // System.out.println(gr.getDataPoint(""));

        TBAGetRequest gr = new TBAGetRequest();

        initUI();
    }

    private static void initUI() {
        /**
         * not to be confused with the initUI found in Application.java, this method
         * exists exclusively to put the graphical end of things in the event queue.
         */
        if (Options.getCli() == false) {
            System.out.println("program launched");

            EventQueue.invokeLater(() -> {
                Application exe = new Application();
                // uncomment to start maximized
                // exe.setExtendedState(JFrame.MAXIMIZED_BOTH);
                exe.setVisible(true);
                exe.pack();
            });
        }
    }
}