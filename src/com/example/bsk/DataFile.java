package com.example.bsk;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataFile {
    public ArrayList<String> openFile (File file){
        ArrayList<String> dataList = new ArrayList<>();

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        while (true) {
            assert scanner != null;
            if (!scanner.hasNext()) break;

            String line = scanner.nextLine();
            dataList.add(line);
        }

        return dataList;
    }
    public void saveFile (ArrayList<String> dataList) {
        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try{
                FileWriter fw = new FileWriter(fileToSave);

                for (String word: dataList
                     ) {
                    fw.write(word+"\n");
                }
                fw.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }
}
