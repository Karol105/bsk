package com.example.bsk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LFSRFrame extends JFrame {
    private LFSR lfsr;

    private final JLabel polynomialLabel;
    private final JLabel registerLabel;
    private final JTextField registerTextField;

    LFSRFrame(LFSR lfsr){
        this.lfsr = lfsr;

        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(new Color(30, 30, 30));
        upperPanel.setBounds(50, 40, 485, 140);
        upperPanel.setLayout(null);

        polynomialLabel = new JLabel();
        polynomialLabel.setText("Entered Polynomial: " + lfsr.getPolynomial().getTxtPolynomial());
        polynomialLabel.setForeground(new Color(200, 200, 200));
        polynomialLabel.setBounds(0, 0, 400, 50);
        polynomialLabel.setVerticalAlignment(JLabel.TOP);
        polynomialLabel.setHorizontalAlignment(JLabel.LEFT);

        registerLabel = new JLabel();
        registerLabel.setText("Enter the first " + lfsr.getPolynomial().getMaxDegree() + "-bit register:");
        registerLabel.setForeground(new Color(200, 200, 200));
        registerLabel.setBounds(0, 25, 400, 50);
        registerLabel.setVerticalAlignment(JLabel.TOP);
        registerLabel.setHorizontalAlignment(JLabel.LEFT);

        registerTextField = new JTextField();
        registerTextField.setBounds(0, 50, 485, 30);
        registerTextField.setBackground(new Color(150, 150, 150));
        registerTextField.setBorder(BorderFactory.createLineBorder(Color.black));

        JButton enterFirstRegister = new JButton();
        enterFirstRegister.setBounds(0, 100, 280, 30);
        enterFirstRegister.setFocusable(false);
        enterFirstRegister.setBackground(new Color(150, 150, 150));
        enterFirstRegister.setBorder(BorderFactory.createLineBorder(Color.black));
        enterFirstRegister.setText("Set Register");
        enterFirstRegister.addActionListener(new enterFirstRegister());

        upperPanel.add(polynomialLabel);
        upperPanel.add(registerLabel);
        upperPanel.add(registerTextField);
        upperPanel.add(enterFirstRegister);

        this.setTitle("LFSR Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.add(upperPanel);
    }

    private class enterFirstRegister implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (firstRegisterValidate(registerTextField.getText())){
                AppFrame.dialogMSG("Entered good register", "Entered register");
                lfsr.setSeed(registerTextField.getText());
                System.out.println("Set register: "+lfsr.getSeed());
            }
        }
    }

    private boolean firstRegisterValidate(String register){
        char[] registerChars = register.toCharArray();
        if(registerChars.length!=lfsr.getPolynomial().getMaxDegree()){
            AppFrame.dialogMSG("Invalid number of bits", "Invalid register");
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
