package com.example.bsk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppFrame extends JFrame implements ActionListener {
    JButton fileButton;
    JComboBox<String> methodComboBox;
    JLabel label;

    AppFrame(){
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(new Color(50,50,50));
        upperPanel.setBounds(50,40,485,140);
        upperPanel.setLayout(null);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(50,50,50));
        bottomPanel.setBounds(160,190,280,30);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));

        JPanel processPanel = new JPanel();
        processPanel.setBackground(new Color(50,50,50));
        processPanel.setBounds(160,240,280,30);
        processPanel.setLayout(null);

        label = new JLabel();
        label.setText("File:    Not selected");
        label.setBackground(Color.BLACK);
        label.setForeground(new Color(200,200,200));
        label.setBounds(140,7,400,50);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);

        fileButton = new JButton();
        fileButton.setBounds(0,0,130,30);
        fileButton.setFocusable(false);
        fileButton.setBackground(new Color(150,150,150));
        fileButton.setBorder(BorderFactory.createLineBorder(Color.black));
        fileButton.setText("Select file");
        fileButton.addActionListener(this);

        String[] methods = {"Method", "Method 1", "Method 2", "Method 3"};
        methodComboBox = new JComboBox<>(methods);
        methodComboBox.setBounds(0,55,130,30);
        methodComboBox.setFocusable(false);
        methodComboBox.setBackground(new Color(150,150,150));
        methodComboBox.setBorder(BorderFactory.createLineBorder(Color.black));

        JTextField keyTextField = new JTextField();
        keyTextField.setBounds(0,110,485,30);
        keyTextField.setBackground(new Color(150,150,150));
        keyTextField.setText("Insert key");
        keyTextField.setBorder(BorderFactory.createLineBorder(Color.black));

        JRadioButton encodeCheckBox = new JRadioButton();
        encodeCheckBox.setText("Encode");
        encodeCheckBox.setBounds(20,0,10,10);
        encodeCheckBox.setFocusable(false);
        encodeCheckBox.setBackground(new Color(50,50,50));
        encodeCheckBox.setForeground(new Color(200,200,200));

        JRadioButton decodeCheckBox = new JRadioButton();
        decodeCheckBox.setText("Decode");
        decodeCheckBox.setBounds(200,0,10,10);
        decodeCheckBox.setFocusable(false);
        decodeCheckBox.setBackground(new Color(50,50,50));
        decodeCheckBox.setForeground(new Color(200,200,200));

        ButtonGroup isEncGroup = new ButtonGroup();
        isEncGroup.add(encodeCheckBox);
        isEncGroup.add(decodeCheckBox);

        JButton processButton = new JButton();
        processButton.setBounds(0,0,280,30);
        processButton.setFocusable(false);
        processButton.setBackground(new Color(150,150,150));
        processButton.setBorder(BorderFactory.createLineBorder(Color.black));
        processButton.setText("Process");

        this.setTitle("BSK App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 350);
        this.setLayout(null);
        this.setVisible(true);

        upperPanel.add(fileButton);
        upperPanel.add(label);
        upperPanel.add(methodComboBox);
        upperPanel.add(keyTextField);
        bottomPanel.add(encodeCheckBox);
        bottomPanel.add(decodeCheckBox);
        processPanel.add(processButton);

        this.add(upperPanel);
        this.add(bottomPanel);
        this.add(processPanel);

        this.getContentPane().setBackground(new Color(50,50,50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==fileButton){
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                label.setText("File:    " + file);
            }
        }
        if(e.getSource()==methodComboBox){
            System.out.println(methodComboBox.getSelectedIndex());
        }
    }
}
