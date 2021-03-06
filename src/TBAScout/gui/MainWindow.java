package TBAScout.gui;

import TBAScout.*;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import scoutPojo.SimpleMatches;

@SuppressWarnings("serial")
public class MainWindow extends JPanel implements ActionListener {
	//
	// swing objects
	//

	private InputWindow inputWindow = new InputWindow();
	private OutputWindow outputWindow = new OutputWindow();
	private GraphWindow graphWindow = new GraphWindow();
	private Timer timer = new Timer(100, this);

	public MainWindow() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("GET request settings", null, inputWindow, "set GET request properties");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		tabbedPane.addTab("GET request output", null, outputWindow, "see GET request returned values");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		tabbedPane.addTab("GET request output (graphs)", null, graphWindow,
				"see GET request returned values in the form of a graph (where applicable)");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			if (inputWindow.getNewRequest()) {
				outputWindow.setOutput(inputWindow.getRequestOutput());
			}

			if (inputWindow.getNewGraphRequest()) {
				graphWindow.redrawGraph((SimpleMatches[]) inputWindow.getJsonObj(), inputWindow.getTeamNum());
				outputWindow.setOutput(curTeamData.toOutputString());
			}
		}

		EventQueue.invokeLater(() -> {
			this.updateUI();
		});
	}
}