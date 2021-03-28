package com.example.bsk;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LFSRFrame extends JFrame {
    private LFSR lfsr;
    private String filePath;

    private static final String[] header = {"Bit"};
    private DefaultTableModel model = new DefaultTableModel(null, header);

    private JTable chainTable;
    private JScrollPane scroll;

    private final JLabel polynomialLabel;
    private final JLabel registerLabel;
    private final JLabel numberOfBitsLabel;
    private final JTextField registerTextField;
    private final JTextField numberOfBitsField;
    private final JButton enterFirstRegister;

    private final JLabel filePathLabel;
    private final JLabel szyfrStrumieniowyLabel;
    private final JButton szyfrStrumieniowyButton;
    private final JButton saveChainButton;
    private final JButton exitButton;
    private final JButton stopButton;

    LFSRFrame(LFSR lfsr, String filepath){
        this.lfsr = lfsr;
        this.filePath = filepath;

        JPanel upperPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        upperPanel.setBounds(50, 40, 485, 140);
        upperPanel.setLayout(null);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(50, 190, 485, 220);
        bottomPanel.setLayout(null);

        JPanel stopPanel = new JPanel();
        stopPanel.setBounds(545, 330, 190, 30);
        stopPanel.setLayout(null);

        chainTable = new JTable(model);
        chainTable.setBackground(new Color(100, 100, 100));
        chainTable.setForeground(Color.BLACK);
        chainTable.setBorder(BorderFactory.createLineBorder(Color.black));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        chainTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        scroll = new JScrollPane(chainTable);
        scroll.setBounds(545, 40, 190, 290);
        scroll.getViewport().setBackground(new Color(40, 40, 40));
        scroll.getViewport().setForeground(new Color(200, 200, 200));

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
        numberOfBitsField.setBounds(0, 105, 185, 30);
        numberOfBitsField.setBackground(new Color(150, 150, 150));
        numberOfBitsField.setBorder(BorderFactory.createLineBorder(Color.black));

        enterFirstRegister = new JButton();
        enterFirstRegister.setBounds(300, 105, 185, 30);
        enterFirstRegister.setFocusable(false);
        enterFirstRegister.setBackground(new Color(150, 150, 150));
        enterFirstRegister.setBorder(BorderFactory.createLineBorder(Color.black));
        enterFirstRegister.setText("Generate chain");
        enterFirstRegister.addActionListener(new startGenerateChainButton());

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

        saveChainButton = new JButton();
        saveChainButton.setBounds(300, 140, 185, 30);
        saveChainButton.setFocusable(false);
        saveChainButton.setBackground(new Color(150, 150, 150));
        saveChainButton.setBorder(BorderFactory.createLineBorder(Color.black));
        saveChainButton.setText("Save chain");
        saveChainButton.addActionListener(new saveChain());

        saveChainButton.setEnabled(false);
        saveChainButton.setBackground(new Color(100, 100, 100));

        exitButton = new JButton();
        exitButton.setBounds(0, 140, 185, 30);
        exitButton.setFocusable(false);
        exitButton.setBackground(new Color(150, 150, 150));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.black));
        exitButton.setText("Exit");
        exitButton.addActionListener(new exitToMainMenu());

        stopButton = new JButton();
        stopButton.setBounds(0, 0, 190, 30);
        stopButton.setFocusable(false);
        stopButton.setBackground(new Color(150, 150, 150));
        stopButton.setBorder(BorderFactory.createLineBorder(Color.black));
        stopButton.setText("Stop");
        stopButton.addActionListener(new stopGenerating());

        stopButton.setEnabled(false);
        stopButton.setBackground(new Color(100, 100, 100));

        upperPanel.add(polynomialLabel);
        upperPanel.add(registerLabel);
        upperPanel.add(registerTextField);
        upperPanel.add(numberOfBitsLabel);
        upperPanel.add(numberOfBitsField);
        upperPanel.add(enterFirstRegister);

        bottomPanel.add(szyfrStrumieniowyButton);
        bottomPanel.add(szyfrStrumieniowyLabel);
        bottomPanel.add(filePathLabel);
        bottomPanel.add(saveChainButton);
        bottomPanel.add(exitButton);

        stopPanel.add(stopButton);

        this.setTitle("LFSR Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.add(upperPanel);
        this.add(bottomPanel);
        this.add(stopPanel);
        this.add(scroll, BorderLayout.CENTER);
    }

    private class startGenerateChainButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (firstRegisterValidate(registerTextField.getText())){
                AppFrame.dialogMSG("Entered good register", "Entered register");
                lfsr.setSeed(registerTextField.getText());
                try{
                    lfsr.setLoops(Integer.parseInt(numberOfBitsField.getText()));
                    startGenerateChain();
                } catch (NumberFormatException exception){
                    if(numberOfBitsField.getText().equals("")){
                        startGenerateChain();
                    } else {
                        AppFrame.dialogMSG("Invalid number of bits to generate", "Number of generated bits");
                    }
                }
            }
        }
    }

    private void startGenerateChain(){
        lfsr.generateChain(LFSRFrame.this);

        stopButton.setEnabled(true);
        stopButton.setBackground(new Color(150, 150, 150));

        if(!filePath.equals("")){
            szyfrStrumieniowyButton.setEnabled(true);
            szyfrStrumieniowyButton.setBackground(new Color(150, 150, 150));
            szyfrStrumieniowyLabel.setForeground(new Color(200, 200, 200));
        }
        saveChainButton.setEnabled(true);
        saveChainButton.setBackground(new Color(150, 150, 150));
    }

    private class exitToMainMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            LFSRFrame.this.dispose();
        }
    }

    private class saveChain implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DataFile dataFile = new DataFile();
            dataFile.saveFile(lfsr.getGeneratedChain());
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

    private class stopGenerating implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            lfsr.setStatus(false);
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

    public void addBitToTable(boolean bit){
        if(bit){
            model.addRow(new Object[] {'1'});
        } else {
            model.addRow(new Object[] {'0'});
        }
        chainTable.changeSelection(chainTable.getRowCount() - 1, 0, false, false);
    }

    public void clearBitTable(){
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
