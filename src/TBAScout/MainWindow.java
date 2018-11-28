package TBAScout;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MainWindow extends JPanel {
    //
    // instance variables
    //
    // private final String[] TBAPaths = { //maybe not worth putting every single path?
    //     "/status",
    //     "/teams/%pagenum",
    //     "/teams/%pagenum/simple",
    //     "/teams/%pagenum/keys",
    //     "/teams/%year/%pagenum",
    //     "/teams/%year/%pagenum/simple",
    //     "/teams/%year/%pagenum/keys",
    //     "/team/%teamKey/events/%year/statuses",
    //     "/events/%year",
    //     "/events/%year/simple",
    //     "/events/%year/keys",
    //     "/event/%eventKey/teams",
    //     "/event/%eventKey/teams/simple",
    //     ""
    // };

    private final String[] shortTBAPaths = {
        "/team/%teamKey/simple",
        "/team/%teamKey/events/simple"
    };

    private JComboBox<String> paths = new JComboBox<String>(shortTBAPaths);
    private JButton submitPath = new JButton("submit path");

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
                
            }
        });
        
        //
        // initialize objects in main window
        //

        add(paths);
        add(submitPath);
    }
}