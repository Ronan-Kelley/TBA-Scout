package TBAScout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainWindow extends JPanel {
    public MainWindow() {
        initMainWindow();
    }

    private void initMainWindow() {
        //
        // initialize main window
        //

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		setFocusable(true);
        setDoubleBuffered(true);
        
        //
        // initialize objects in main window
        //

        
    }
}