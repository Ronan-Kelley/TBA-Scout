package TBAScout;

import java.awt.EventQueue;

public class TBAScout {
    public static Options options = new Options();

    public static void main(String[] args) {
        /**
         * command line options should mostly be handled here, and should
         * follow the convention of starting with a dash
         */
        for (String arg : args) {
            if (args != null) {
                switch (arg) {
                    case "-cli":
                        options.setCli(true);
                        System.out.println("found argument: " + arg);
                        break;
                }
            } else if (args == null) {
                System.out.println("no arguments found!");
            }
        }

        initUI();
    }

    private static void initUI() {
        /**
         * not to be confused with the initUI found in Application.java,
         * this method exists exclusively to put the graphical end of things
         * in the event queue.
         */
        if (options.getCli() == false) {
            System.out.println("program launched");
            
		    EventQueue.invokeLater(() -> {
			    Application exe = new Application();
			    // uncomment to start maximized
			    // exe.setExtendedState(JFrame.MAXIMIZED_BOTH);
			    exe.setVisible(true);
		    });
        }
    }
}