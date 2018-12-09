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
        "/team/{team_key}/simple",
        "/team/{team_key}/events"
    };

    private FinalJsonHandler jsonHandler = new FinalJsonHandler();

    private JComboBox<String> paths = new JComboBox<String>(TBAPaths);
    private JButton submitPath = new JButton("submit path");
    private JTextArea output = new JTextArea(30, 58);
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
            int teamNum;

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = (String) paths.getSelectedItem();
                switch (path) {
                    case "/status":
                        StatusPojo statusOut = jsonHandler.handleStatusJson(new TBAGetRequest().getJson());
                        try {
                        output.setText("current season: " + statusOut.getCurrent_season());
                        } catch (NullPointerException err) {
                            output.setText("an error has occured!");
                        }
                        break;
                    
                    case "/team/{team_key}/simple":
                        teamNum = 141;
                        
                        try {
                            teamNum = Integer.parseInt(teamKey.getText());
                        } catch (NullPointerException err) {
                            teamNum = 141;
                        } catch (NumberFormatException err) {
                            teamNum = 141;
                        }

                        SimpleTeamPojo simpleTeamPojo = jsonHandler.handleTeamJson(new TBAGetRequest("/team/frc" + teamNum + "/simple").getJson());

                        output.setText("team's home city: " + simpleTeamPojo.getCity());
                        break;

                    case "/team/{team_key}/events":
                        teamNum = 141;
                        
                        try {
                            teamNum = Integer.parseInt(teamKey.getText());
                        } catch (NullPointerException err) {
                            teamNum = 141;
                        } catch (NumberFormatException err) {
                            teamNum = 141;
                        }

                        EventsPojo[] eventsPojo = jsonHandler.handleEventsPojo(new TBAGetRequest("/team/frc" + teamNum + "/events").getJson());

                        output.setText(eventsPojo[2].getYear());
                
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
        add(teamKey);
        add(output);
    }
}