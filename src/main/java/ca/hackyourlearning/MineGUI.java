package ca.hackyourlearning;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MineGUI extends JFrame implements ActionListener {
    private JTextArea inputDisplay, outputDisplay;
    private JButton analyze, clear;
    private JLabel inputText, outputResult;
    private BTCCalculations btcCalculations;

    public MineGUI(String title) {

        inputDisplay = new JTextArea("", 10, 20);
        inputDisplay.setLineWrap(true);
        JScrollPane scroll1 = new JScrollPane(inputDisplay);

        outputDisplay = new JTextArea("", 10, 20);
        outputDisplay.setLineWrap(true);
        JScrollPane scroll2 = new JScrollPane(outputDisplay);

        analyze = new JButton("Calculate");
        analyze.addActionListener(this);

        clear = new JButton("Clear");
        clear.addActionListener(this);

        inputText = new JLabel("Enter your bitcoin mining volume here:");
        outputResult = new JLabel("Your results are:");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 0));
        inputPanel.add(scroll1);

        inputPanel.add(scroll2);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(0, 2));
        labelPanel.add(inputText);
        labelPanel.add(outputResult);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2));
        buttonPanel.add(analyze);
        buttonPanel.add(clear);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add("North", labelPanel);
        contentPane.add("Center", inputPanel);
        contentPane.add("South", buttonPanel);

        setSize(1100, 500);
        setTitle(title);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == analyze) {
            try {
                btcCalculations = new BTCCalculations(Double.parseDouble(inputDisplay.getText()));
                ;
                outputDisplay.setText(btcCalculations.toString());
            } catch (NumberFormatException e1) {
                outputDisplay.setText(e1.getMessage() + " - please run again with a numerical value.\n");

            }

        }

        if (e.getSource() == clear) {
            inputDisplay.setText(null);
            outputDisplay.setText(null);
        }
    }

    public static void main(String[] args) {
        new MineGUI("BTC Mining Profit Calculator");
    }
}
