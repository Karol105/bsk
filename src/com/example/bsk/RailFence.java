package com.example.bsk;

public class RailFence implements Cipher{

    private int getTerm(int iteration, int row, int size){
        if ((size == 0) || (size == 1)) {
            return 1;
        }
        if ((row == 0) || (row == size-1)){
            return (size-1)*2;
        }
        if (iteration % 2 == 0) {
            return (size-1-row)*2;
        }
        return 2*row;
    }

    public String encode(String message, int key) {
        if(key<0) {
            throw new ArithmeticException("key is negative");
        } else if (key==0) {
            key = 1;
        }
        String encodedMessage = "";
        for(int row = 0; row < key; row++) {
            int iter = 0;
            for(int i = row; i < message.length(); i += getTerm(iter++, row, key)) {
                encodedMessage += message.charAt(i);
            }
        }
        return encodedMessage;
    }

    public String decode(String message, int key) {
        if (key < 0) {
            throw new ArithmeticException("Negative key value");
        }
        StringBuilder decodedMessage = new StringBuilder(message);
        int currPosition = 0;
        for(int row = 0; row < key; row++) {
            int iter = 0;
            for(int i = row; i < message.length(); i += getTerm(iter++, row, key)) {
                decodedMessage.setCharAt(i, message.charAt(currPosition++));
            }


        }

        return decodedMessage.toString();
    }

    @Override
    public String encode(String message, String key) {
        return encode(message, Integer.parseInt(key));
    }

    @Override
    public String decode(String message, String key) {
        return decode(message, Integer.parseInt(key));
    }
}
