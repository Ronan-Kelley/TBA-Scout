package TBAScout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scoutPojo.*;

@SuppressWarnings("serial")
public class InputWindow extends JPanel {
    //
    // instance variables
    //

    private final String[] TBAPaths = {
        "/status",
        "/team/{team_key}/simple",
        "/team/{team_key}/events"
    };

    private String requestOutput = "";
    private Boolean newRequest = false;

    private FinalJsonHandler jsonHandler = new FinalJsonHandler();

    private JComboBox<String> paths = new JComboBox<String>(TBAPaths);
    private JButton submitPath = new JButton("submit path");
    private JTextField teamKey = new JTextField("frc team number (teamkey)");

    public InputWindow() {
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		setFocusable(true);
        setDoubleBuffered(true);

        //
        // initialize swing objects
        //

        teamKey.setToolTipText("enter an frc team number here (optional)");
        
        //
        // add objects into main iwndow
        //

        add(paths);
        add(submitPath);
        add(teamKey);

        //
        // add actionListener
        //

        submitPath.addActionListener(new ActionListener(){
            int teamNum;

            @Override
            public void actionPerformed(ActionEvent e) {
                String path = (String) paths.getSelectedItem();
                switch (path) {
                    case "/status":
                        StatusPojo statusOut = jsonHandler.handleStatusJson(new TBAGetRequest().getJson());
                        try {
                        requestOutput = "current season: " + statusOut.getCurrent_season();
                        } catch (NullPointerException err) {
                            requestOutput = "an error has occured!";
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

                        requestOutput = "team's home city: " + simpleTeamPojo.getCity();
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

                        requestOutput = eventsPojo[eventsPojo.length-1].getYear();
                
                    default:
                        break;
                }
                newRequest = true;
            }
        });
    }

    public String getRequestOutput() {
        return requestOutput;
    }

    public Boolean getNewRequest() {
        if (newRequest) {
            newRequest = false;
            return true;
        } else {
            return false;
        }
    }
}