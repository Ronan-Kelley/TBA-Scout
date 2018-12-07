package TBAScout;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import scoutPojo.StatusPojo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainWindow extends JPanel {
    //
    // instance variables
    //

    private final String[] TBAPaths = {
        "/status",
        "/team/%teamKey/events/simple"
    };

    private FinalJsonHandler jsonHandler = new FinalJsonHandler();

    private JComboBox<String> paths = new JComboBox<String>(TBAPaths);
    private JButton submitPath = new JButton("submit path");
    private JTextArea output = new JTextArea();

    //
    // constructors
    //

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
        // add event listeners
        //

        submitPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = (String) paths.getSelectedItem();
                switch (path) {
                    case "/status":
                        StatusPojo statusOut = jsonHandler.handleStatusJson(new TBAGetRequest().getJson());
                        try {
                        output.setText(statusOut.getCurrent_season());
                        } catch (NullPointerException err) {
                            output.setText("an error has occured!");
                        }
                        break;
                
                    default:
                        break;
                }
            }
        });
        
        //
        // initialize objects in main window
        //

        output.setEditable(false);

        //
        // add objects into main iwndow
        //

        add(paths);
        add(submitPath);
        add(output);
    }
}