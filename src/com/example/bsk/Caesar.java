package com.example.bsk;


public class Caesar {
    public static final int ALPHABET_SIZE = ('Z' - 'A') + 1;
    static String encrypt(String message, int key) {
        StringBuilder encrypted = new StringBuilder();
        char letter;
        if(key <0) {
            key += ALPHABET_SIZE;
        }

        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                if (Character.isLowerCase(character)) {
                    letter = 'a';
                }
                else {
                    letter = 'A';
                }
                int position = character - letter;
                int newPosition = (position + key) % 26;
                char newCharacter = (char) (letter + newPosition);
                encrypted.append(newCharacter); 
            } else 
                encrypted.append(character);
        }
    return encrypted.toString(); 
    }

    static String decrypt(String message, int key) {
        String decrypted;
        decrypted = encrypt(message, 26 - (key % 26));
        return decrypted;
    }

}
