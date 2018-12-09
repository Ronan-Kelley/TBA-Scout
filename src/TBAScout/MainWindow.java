package TBAScout;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import scoutPojo.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainWindow extends JPanel {
    //
    // instance variables
    //

    private final String[] TBAPaths = {
        "/status",
        "/team/{team_key}"
    };

    private FinalJsonHandler jsonHandler = new FinalJsonHandler();

    private JComboBox<String> paths = new JComboBox<String>(TBAPaths);
    private JButton submitPath = new JButton("submit path");
    private JTextArea output = new JTextArea();
    private JTextField teamKey = new JTextField("frc team number (teamkey)");

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
                    
                    case "/team/{team_key}":
                        String teamNum = "141";
                        try {
                            teamNum = teamKey.getText();
                        } catch (NullPointerException err) { }

                        SimpleTeamPojo simpleTeamPojo = jsonHandler.handleTeamJson(new TBAGetRequest("/team/frc" + teamNum).getJson());

                        output.setText(simpleTeamPojo.getCity());
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
        teamKey.setToolTipText("enter an frc team number here (optional)");
        

        //
        // add objects into main iwndow
        //

        add(paths);
        add(submitPath);
        add(output);
        add(teamKey);
    }
}