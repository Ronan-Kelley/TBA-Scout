package TBAScout;

import TBAScout.gui.*;

import java.awt.EventQueue;

public class TBAScout {

    public static void main(String[] args) {
        /**
         * command line options should mostly be handled here, and should follow the
         * convention of starting with a dash
         */

        Options.handleArgs(args);

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
                exe.setBounds(0, 0, 500, 500);
                exe.pack();
            });
        }
    }
}