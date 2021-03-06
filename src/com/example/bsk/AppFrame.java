package com.example.bsk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
//import java.io.FileFilter;
import javax.swing.filechooser.FileFilter;

public class AppFrame extends JFrame{
    private final JButton fileButton;
    private File file;
    private String[] methods = {"Rail Fence", "Macierz A", "Macierz B", "Vigenere", "Caesar", "LFSR", "Szyfr strumieniowy"};
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
                    fileChooser.addChoosableFileFilter(new FileFilter() {
                    public String getDescription() {
                        return "MP3 Files (*.mp3)";

                    }
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        } else {
                            return file.getName().toLowerCase().endsWith(".mp3");
                        }
                    }
                });
                fileChooser.addChoosableFileFilter(new FileFilter() {
                    public String getDescription() {
                        return "Text Files (*.txt)";

                    }
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        } else {
                            return file.getName().toLowerCase().endsWith(".txt");
                        }
                    }
                });
                fileChooser.addChoosableFileFilter(new FileFilter() {
                    public String getDescription() {
                        return "JPG Files (*.jpg)";

                    }
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        } else {
                            return file.getName().toLowerCase().endsWith(".jpg");
                        }
                    }
                });
                fileChooser.addChoosableFileFilter(new FileFilter() {
                    public String getDescription() {
                        return "PNG Files (*.png)";

                    }
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        } else {
                            return file.getName().toLowerCase().endsWith(".png");
                        }
                    }
                });
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
            String selectedMethod = Objects.requireNonNull(methodComboBox.getSelectedItem()).toString();

            if(file==null&&(!selectedMethod.equals("LFSR"))){
                dialogMSG("You didn't choose the file.", "File not selected");
                return;
            } else if (keyTextField.getText().equals("KEY")){
                dialogMSG("You didn't insert the key.", "Key not inserted");
                return;
            }

            //file - ścieżka do pliku
            //keyTextField.getText() - klucz
            //encodeCheckBox.isSelected() - encode(false lub true)

            DataFile dataFile;
            ArrayList<String> dataList;
            ArrayList<String> newDataList;

            switch (selectedMethod) {
                case "Rail Fence" -> {
                    dataFile = new DataFile();
                    dataList = dataFile.openFile(file);
                    newDataList = new ArrayList<>();

                    int keyRF = Integer.parseInt(keyTextField.getText());
                    RailFence railFenceCipher = new RailFence(keyRF);

                    if (encodeCheckBox.isSelected()) {
                        for (String word : dataList) {
                            newDataList.add(railFenceCipher.getEncryptedData(word));
                            System.out.println(railFenceCipher.getEncryptedData(word));
                        }
                    } else if (!encodeCheckBox.isSelected()) {
                        for (String word : dataList) {
                            newDataList.add(railFenceCipher.getDecryptedData(word));
                            System.out.println(railFenceCipher.getDecryptedData(word));
                        }
                    }
                    dataFile.saveFile(newDataList);
                }
                case "Macierz A" -> {
                    //TODO Macierz A
                    //Macierz A(file, key, encode(false lub true));
                }
                case "Macierz B" -> {
                    //TODO Macierz B
                    //Macierz B(file, key, encode(false lub true));
                    dataFile = new DataFile();
                    dataList = dataFile.openFile(file);
                    newDataList = new ArrayList<>();

                    Key key = null;
                    try {
                        key = new Key.Builder()
                                .setX(4)
                                .setColumn(1)
                                .setColumn(2)
                                .setColumn(3)
                                .setColumn(4)
                                .build();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                    com.example.bsk.Cipher transpositionCipher = new TranspositionCipherStrategy();

                    String message;
                    if (encodeCheckBox.isSelected()) {

                        for (String word : dataList
                        ) {
                            message = transpositionCipher.encode(word, key);
                            newDataList.add(message);
                            System.out.println(message);
                        }
                    } else if (!encodeCheckBox.isSelected()) {
                        for (String word : dataList
                        ) {
                            message = transpositionCipher.decode(word, key);
                            newDataList.add(message);
                            System.out.println(message);
                        }
                    }
                    dataFile.saveFile(newDataList);
                }
                case "Vigenere" -> {
                    dataFile = new DataFile();
                    dataList = dataFile.openFile(file);
                    newDataList = new ArrayList<>();

                    String keyV = keyTextField.getText();
                    if (encodeCheckBox.isSelected()) {
                        for (String word : dataList) {
                            newDataList.add(Vigenere.encrypt(word, keyV));
                            System.out.println(Vigenere.encrypt(word, keyV));
                        }
                    } else if (!encodeCheckBox.isSelected()) {
                        for (String word : dataList) {
                            newDataList.add(Vigenere.decrypt(word, keyV));
                            System.out.println(Vigenere.decrypt(word, keyV));
                        }
                    }
                    dataFile.saveFile(newDataList);
                }
                case "Caesar" -> {
                    dataFile = new DataFile();
                    dataList = dataFile.openFile(file);
                    newDataList = new ArrayList<>();

                    int keyC = Integer.parseInt(keyTextField.getText());
                    if (encodeCheckBox.isSelected()) {
                        for (String word : dataList) {
                            newDataList.add(Caesar.encrypt(word, keyC));
                            System.out.println(Caesar.encrypt(word, keyC));
                        }
                    } else if (!encodeCheckBox.isSelected()) {
                        for (String word : dataList) {
                            newDataList.add(Caesar.decrypt(word, keyC));
                            System.out.println(Caesar.decrypt(word, keyC));
                        }
                    }
                    dataFile.saveFile(newDataList);
                }
                case "LFSR" -> {
                    Polynomial polynomial = new Polynomial();
                    if (polynomial.polynomialValidate(keyTextField.getText())){
                        LFSRFrame lfsrFrame;
                        try {
                            lfsrFrame = new LFSRFrame(new LFSR(polynomial), file.toString());
                        } catch (NullPointerException exception){
                            lfsrFrame = new LFSRFrame(new LFSR(polynomial), "");
                        }
                        lfsrFrame.setVisible(true);
                    }
                }
                case "Szyfr strumieniowy" -> {
                    Polynomial polynomial = new Polynomial();
                    if (polynomial.polynomialValidate(keyTextField.getText())){
                        LFSR lfsr = new LFSR(polynomial);
                        LFSRFrame lfsrFrame = new LFSRFrame(lfsr,file.toString());
                        lfsrFrame.setVisible(true);
                    }
                }
            }
        }
    }

    public static void dialogMSG(String message, String title) {
        JOptionPane.showMessageDialog(null,
                message,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }
}
