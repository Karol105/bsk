package com.example.bsk;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public byte[] openAudioFile(File file) {
        byte[] bytes = null;
        int size = (int) file.length();
        bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(
                    new FileInputStream(file));
            try {
                buf.read(bytes);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bytes;
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
    public void saveAudioFile(byte [] encodedBytes, File file ) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(encodedBytes);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
