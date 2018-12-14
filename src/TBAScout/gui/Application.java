package TBAScout.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Application extends JFrame {
    /**
     * class dedicated to initializing the main window of the program
     */
    
    public Application() {
        initUI();
    }

    private void initUI() {
        add(new MainWindow());

        setSize(1000, 700);

		setTitle("TBA Scouting Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
    }
}