package com.example.bsk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppFrame extends JFrame{
    private final JButton fileButton;
    private File file;
    private String[] methods = {"Rail Fence", "Macierz A", "Macierz B"};
    private final JComboBox<String> methodComboBox;
    private final JLabel label;
    private final JTextField keyTextField;
    private final JRadioButton encodeCheckBox;
    private final JRadioButton decodeCheckBox;

    AppFrame() {
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(new Color(50, 50, 50));
        upperPanel.setBounds(50, 40, 485, 140);
        upperPanel.setLayout(null);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(50, 50, 50));
        bottomPanel.setBounds(160, 190, 280, 30);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));

        JPanel processPanel = new JPanel();
        processPanel.setBackground(new Color(50, 50, 50));
        processPanel.setBounds(160, 240, 280, 30);
        processPanel.setLayout(null);

        label = new JLabel();
        label.setText("File:    Not selected");
        label.setBackground(Color.BLACK);
        label.setForeground(new Color(200, 200, 200));
        label.setBounds(140, 7, 400, 50);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.LEFT);

        fileButton = new JButton();
        fileButton.setBounds(0, 0, 130, 30);
        fileButton.setFocusable(false);
        fileButton.setBackground(new Color(150, 150, 150));
        fileButton.setBorder(BorderFactory.createLineBorder(Color.black));
        fileButton.setText("Select file");
        fileButton.addActionListener(new SelectFile());

        methodComboBox = new JComboBox<>(methods);
        methodComboBox.setBounds(0, 55, 130, 30);
        methodComboBox.setFocusable(false);
        methodComboBox.setBackground(new Color(150, 150, 150));
        methodComboBox.setBorder(BorderFactory.createLineBorder(Color.black));

        keyTextField = new JTextField();
        keyTextField.setBounds(0, 110, 485, 30);
        keyTextField.setBackground(new Color(150, 150, 150));
        keyTextField.setText("KEY");
        keyTextField.setBorder(BorderFactory.createLineBorder(Color.black));

        encodeCheckBox = new JRadioButton();
        encodeCheckBox.setText("Encode");
        encodeCheckBox.setBounds(20, 0, 10, 10);
        encodeCheckBox.setFocusable(false);
        encodeCheckBox.setBackground(new Color(50, 50, 50));
        encodeCheckBox.setForeground(new Color(200, 200, 200));
        encodeCheckBox.setSelected(true);

        decodeCheckBox = new JRadioButton();
        decodeCheckBox.setText("Decode");
        decodeCheckBox.setBounds(200, 0, 10, 10);
        decodeCheckBox.setFocusable(false);
        decodeCheckBox.setBackground(new Color(50, 50, 50));
        decodeCheckBox.setForeground(new Color(200, 200, 200));

        ButtonGroup isEncGroup = new ButtonGroup();
        isEncGroup.add(encodeCheckBox);
        isEncGroup.add(decodeCheckBox);

        JButton processButton = new JButton();
        processButton.setBounds(0, 0, 280, 30);
        processButton.setFocusable(false);
        processButton.setBackground(new Color(150, 150, 150));
        processButton.setBorder(BorderFactory.createLineBorder(Color.black));
        processButton.setText("Process");
        processButton.addActionListener(new ProcessData());

        upperPanel.add(fileButton);
        upperPanel.add(label);
        upperPanel.add(methodComboBox);
        upperPanel.add(keyTextField);
        bottomPanel.add(encodeCheckBox);
        bottomPanel.add(decodeCheckBox);
        processPanel.add(processButton);

        this.setTitle("BSK App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 350);
        this.setLayout(null);
        this.setVisible(true);

        this.add(upperPanel);
        this.add(bottomPanel);
        this.add(processPanel);

        this.getContentPane().setBackground(new Color(50, 50, 50));
    }

    private class SelectFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==fileButton){
                JFileChooser fileChooser = new JFileChooser();

                int response = fileChooser.showOpenDialog(null);

                if(response == JFileChooser.APPROVE_OPTION){
                    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    label.setText("File:    " + file);
                }
            }
        }
    }

    private class ProcessData implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(file==null){
                dialogMSG("You didn't choose the file.", "File not selected");
                return;
            } else if (keyTextField.getText().equals("KEY")){
                dialogMSG("You didn't insert the key.", "Key not inserted");
                return;
            }
            String selectedMethod = methodComboBox.getSelectedItem().toString();

            //file - ścieżka do pliku
            //keyTextField.getText() - klucz
            //encodeCheckBox.isSelected() - encode(false lub true)

            String ciphertext = "";

            switch (selectedMethod) {
                case "Rail Fence" -> {
                    //TODO Rail Fence
                    //Rail Fence(file, key, encode(false lub true));
                    Cipher railFence = new RailFence();
                }
                case "Macierz A" -> {
                    //TODO Macierz A
                    //Macierz A(file, key, encode(false lub true));
                }
                case "Macierz B" -> {
                    //TODO Macierz B
                    //Macierz B(file, key, encode(false lub true));
                }
            }
            System.out.println("File path: " + file);
            System.out.println("Method: " + methodComboBox.getSelectedItem().toString());
            System.out.println("Key: " + keyTextField.getText());
            System.out.println("Encode: " + encodeCheckBox.isSelected());
            System.out.println("Decode: " + decodeCheckBox.isSelected());
        }

        void dialogMSG(String message, String title) {
            JOptionPane.showMessageDialog(null,
                    message,
                    title,
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}