package com.example.bsk;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        UIManager.put("OptionPane.background", new Color(50,50,50));
        UIManager.put("OptionPane.messageForeground", new Color(200,200,200));
        UIManager.put("Panel.background", new Color(50,50,50));
        UIManager.put("ScrollPane.background", new Color(50,50,50));
        UIManager.put("List.background", new Color(50,50,50));
        UIManager.put("List.foreground", new Color(200,200,200));
        UIManager.put("List.selectionBackground", new Color(150,150,150));
        UIManager.put("List.border", BorderFactory.createLineBorder(Color.black));
        UIManager.put("TextField.background", new Color(150,150,150));
        UIManager.put("TextField.border", BorderFactory.createLineBorder(Color.black));
        UIManager.put("ComboBox.background", new Color(150,150,150));
        UIManager.put("Button.foreground", new Color(50,50,50));
        UIManager.put("Button.background", new Color(150, 150, 150));
        UIManager.put("ScrollBar", Color.BLACK);
        UIManager.put("ScrollBar.border", Color.BLACK);
        UIManager.put("ScrollBar.darkShadow", Color.BLACK);
        UIManager.put("ScrollBar.background", new Color(50,50,50));
        UIManager.put("ScrollBar.highlight", new Color(150, 150, 150));

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

            DataFile dataFile = new DataFile();
            ArrayList<String> dataList = dataFile.openFile(file);
            ArrayList<String> newDataList = new ArrayList<>();

            String ciphertext = "";

            switch (selectedMethod) {
                case "Rail Fence" -> {
                    int keyRF = Integer.parseInt(keyTextField.getText());
                    RailFence railFenceCipher = new RailFence(keyRF);

                    for (String word: dataList){
                        if(encodeCheckBox.isSelected()) {
                            newDataList.add(railFenceCipher.getEncryptedData(word));
                            System.out.println(railFenceCipher.getEncryptedData(word));
                        }
                        else if (!encodeCheckBox.isSelected()) {
                            newDataList.add(railFenceCipher.getDecryptedData(word));
                            System.out.println(railFenceCipher.getDecryptedData(word));
                        }
                    }
                }
                case "Macierz A" -> {
                    //TODO Macierz A
                    //Macierz A(file, key, encode(false lub true));
                }
                case "Macierz B" -> {
                    //TODO Macierz B
                    //Macierz B(file, key, encode(false lub true));
                   /* Key key = new Key.Builder()
                    .setX(4)
                    .setColumn(1)
                    .setColumn(2)
                    .setColumn(3)
                    .setColumn(4)
                    .build();
                    Cipher transpositionCipher = new TranspositionCipherStrategy();
                    String encodeMessage = transpositionCipher.encode("", key);
            */
                    //System.out.println(encodeMessage);
                    //System.out.println(transpositionCipher.decode(encodeMessage, key));
                }
            }
            dataFile.saveFile(newDataList);
        }

        void dialogMSG(String message, String title) {
            JOptionPane.showMessageDialog(null,
                    message,
                    title,
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
