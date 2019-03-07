package TBAScout.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import TBAScout.restrequests.*;

@SuppressWarnings("serial")
public class KeyInput extends JFrame implements ActionListener {
    JLabel lblHeader = new JLabel("No FIRST or TBA API Key detected! Please paste your FIRST or TBA Key here.");
    JTextArea txtKey = new JTextArea(1, 10);
    JButton btnSubmit = new JButton("Submit");
    JComboBox<String> apiSelector = new JComboBox<String>(new String[] {"FIRST", "TBA"});
    Timer packLoop = new Timer(50, this);

    public KeyInput() {

        setTitle("input key");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(0, 0, 300, 400);

        packLoop.start();

        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (apiSelector.getSelectedItem().equals("FIRST")) {
                    Options.setFIRSTKey(txtKey.getText());
                } else if (apiSelector.getSelectedItem().equals("TBA")) {
                    Options.setTBAKey(txtKey.getText());
                }
                dispose();
			}

        });

        JPanel holder = new JPanel();

        holder.add(lblHeader);
        holder.add(txtKey);
        holder.add(apiSelector);
        holder.add(btnSubmit);

        add(holder);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == packLoop) {
            pack();
        }
    }


}