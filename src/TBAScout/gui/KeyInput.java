package TBAScout.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import TBAScout.Options;

@SuppressWarnings("serial")
public class KeyInput extends JFrame implements ActionListener {
    JLabel lblHeader = new JLabel("No TBA Key detected! Please paste your TBA Key.");
    JTextArea txtTBAKey = new JTextArea(1, 10);
    JButton btnSubmit = new JButton("Submit");
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
                Options.setTBAKey(txtTBAKey.getText());
                dispose();
			}

        });

        JPanel holder = new JPanel();

        holder.add(lblHeader);
        holder.add(txtTBAKey);
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