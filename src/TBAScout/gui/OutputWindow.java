package TBAScout.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class OutputWindow extends JPanel {
    private JTextArea output = new JTextArea(16, 48);
    private JScrollPane outputScroll = new JScrollPane(output);

    public OutputWindow() {
        add(outputScroll);
    }

    public void setOutput(String outputText) {
        output.setText(outputText);
    }
}