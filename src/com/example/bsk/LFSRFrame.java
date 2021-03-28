package com.example.bsk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LFSRFrame extends JFrame {
    private LFSR lfsr;
    private String filePath;

    private final JLabel polynomialLabel;
    private final JLabel registerLabel;
    private final JLabel numberOfBitsLabel;
    private final JTextField registerTextField;
    private final JTextField numberOfBitsField;
    private final JButton enterFirstRegister;

    private final JLabel filePathLabel;
    private final JLabel szyfrStrumieniowyLabel;
    private final JButton szyfrStrumieniowyButton;
    private final JButton exitButton;

    LFSRFrame(LFSR lfsr, String filepath){
        this.lfsr = lfsr;
        this.filePath = filepath;

        JPanel upperPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        upperPanel.setBackground(new Color(30, 30, 30));
        upperPanel.setBounds(50, 40, 485, 140);
        upperPanel.setLayout(null);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(40, 40, 40));
        bottomPanel.setBounds(50, 190, 485, 220);
        bottomPanel.setLayout(null);

        polynomialLabel = new JLabel();
        polynomialLabel.setText("Entered Polynomial: " + lfsr.getPolynomial().getTxtPolynomial());
        polynomialLabel.setForeground(new Color(200, 200, 200));
        polynomialLabel.setBounds(0, 0, 400, 50);
        polynomialLabel.setVerticalAlignment(JLabel.TOP);
        polynomialLabel.setHorizontalAlignment(JLabel.LEFT);

        registerLabel = new JLabel();
        registerLabel.setText("Enter initial " + lfsr.getPolynomial().getMaxDegree() + "-bit register:");
        registerLabel.setForeground(new Color(200, 200, 200));
        registerLabel.setBounds(0, 30, 400, 20);
        registerLabel.setVerticalAlignment(JLabel.TOP);
        registerLabel.setHorizontalAlignment(JLabel.LEFT);

        registerTextField = new JTextField();
        registerTextField.setBounds(0, 50, 485, 30);
        registerTextField.setBackground(new Color(150, 150, 150));
        registerTextField.setBorder(BorderFactory.createLineBorder(Color.black));

        numberOfBitsLabel = new JLabel();
        numberOfBitsLabel.setText("Enter number of bits you want to generate (or use STOP button)");
        numberOfBitsLabel.setForeground(new Color(200, 200, 200));
        numberOfBitsLabel.setBounds(0, 85, 400, 20);
        numberOfBitsLabel.setVerticalAlignment(JLabel.TOP);
        numberOfBitsLabel.setHorizontalAlignment(JLabel.LEFT);

        numberOfBitsField = new JTextField();
        numberOfBitsField.setBounds(0, 105, 220, 30);
        numberOfBitsField.setBackground(new Color(150, 150, 150));
        numberOfBitsField.setBorder(BorderFactory.createLineBorder(Color.black));

        enterFirstRegister = new JButton();
        enterFirstRegister.setBounds(300, 105, 185, 30);
        enterFirstRegister.setFocusable(false);
        enterFirstRegister.setBackground(new Color(150, 150, 150));
        enterFirstRegister.setBorder(BorderFactory.createLineBorder(Color.black));
        enterFirstRegister.setText("Generate chain");
        enterFirstRegister.addActionListener(new enterFirstRegister());

        filePathLabel = new JLabel();
        if(!filepath.equals("")){
            filePathLabel.setText("Selected file: " + filepath);
            filePathLabel.setForeground(new Color(200, 200, 200));
        } else {
            filePathLabel.setText("You must back to main menu and select file to use this method");
            filePathLabel.setForeground(new Color(168, 1, 1));
        }

        filePathLabel.setBounds(0, 5, 500, 20);
        filePathLabel.setVerticalAlignment(JLabel.TOP);
        filePathLabel.setHorizontalAlignment(JLabel.LEFT);

        szyfrStrumieniowyButton = new JButton();
        szyfrStrumieniowyButton.setBounds(0, 25, 185, 30);
        szyfrStrumieniowyButton.setFocusable(false);
        szyfrStrumieniowyButton.setBackground(new Color(150, 150, 150));
        szyfrStrumieniowyButton.setBorder(BorderFactory.createLineBorder(Color.black));
        szyfrStrumieniowyButton.setText("Szyfr strumieniowy");
        szyfrStrumieniowyButton.addActionListener(new gotoSzyfrStrumieniowy());

        szyfrStrumieniowyButton.setEnabled(false);
        szyfrStrumieniowyButton.setBackground(new Color(100, 100, 100));

        szyfrStrumieniowyLabel = new JLabel();
        szyfrStrumieniowyLabel.setText("Use generated chain in szyfr strumieniowy");
        szyfrStrumieniowyLabel.setForeground(new Color(150, 150, 150));
        szyfrStrumieniowyLabel.setBounds(190, 33, 400, 20);
        szyfrStrumieniowyLabel.setVerticalAlignment(JLabel.TOP);
        szyfrStrumieniowyLabel.setHorizontalAlignment(JLabel.LEFT);

        exitButton = new JButton();
        exitButton.setBounds(0, 140, 185, 30);
        exitButton.setFocusable(false);
        exitButton.setBackground(new Color(150, 150, 150));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
        exitButton.setText("Exit");
        exitButton.addActionListener(new exitToMainMenu());

        upperPanel.add(polynomialLabel);
        upperPanel.add(registerLabel);
        upperPanel.add(registerTextField);
        upperPanel.add(numberOfBitsLabel);
        upperPanel.add(numberOfBitsField);
        upperPanel.add(enterFirstRegister);

        bottomPanel.add(szyfrStrumieniowyButton);
        bottomPanel.add(szyfrStrumieniowyLabel);
        bottomPanel.add(filePathLabel);
        bottomPanel.add(exitButton);

        this.setTitle("LFSR Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.add(upperPanel);
        this.add(bottomPanel);
    }

    private class enterFirstRegister implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (firstRegisterValidate(registerTextField.getText())){
                AppFrame.dialogMSG("Entered good register", "Entered register");
                lfsr.setSeed(registerTextField.getText());
                try{
                    lfsr.setLoops(Integer.parseInt(numberOfBitsField.getText()));
                    lfsr.generateChain();
                    if(!filePath.equals("")){
                        szyfrStrumieniowyButton.setEnabled(true);
                        szyfrStrumieniowyButton.setBackground(new Color(150, 150, 150));
                        szyfrStrumieniowyLabel.setForeground(new Color(200, 200, 200));
                    }
                } catch (NumberFormatException exception){
                    AppFrame.dialogMSG("Invalid number of bits to generate", "Number of generated bits");
                }
            }
        }
    }

    private class exitToMainMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Exit to menu");
        }
    }

    private class gotoSzyfrStrumieniowy implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO Dodać szyfr strumieniowy
            //Wykorzystanie szyfru lfsr.getGeneratedChain() - zwraca ArrayList<Boolean>
            System.out.println("Go to szyfr strumieniowy with that chain: ");
            lfsr.showGeneratedChain();
        }
    }

    private boolean firstRegisterValidate(String register){
        char[] registerChars = register.toCharArray();
        if(registerChars.length!=lfsr.getPolynomial().getMaxDegree()){
            AppFrame.dialogMSG("Invalid number of register bits", "Invalid register");
            return false;
        }
        for (char c:registerChars
             ) {
            if (c!='0'&&c!='1') {
                AppFrame.dialogMSG("You can only enter 0 or 1", "Invalid register");
                return false;
            }
        }
        return true;
    }
}
