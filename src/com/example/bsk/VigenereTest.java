package com.example.bsk;

import org.junit.Test;
import static org.junit.Assert.*;

public class VigenereTest {
    @Test
    public void encrypt1(){
        String key = "BREAKBREAKBR";
        String message = "CRYPTOGRAPHY";
        String encryptedMessage = "DICPDPXVAZIP";
        String result = Vigenere.encrypt(message, key);

        assertEquals(encryptedMessage,result);
    }

    @Test
    public void encrypt2(){
        String key = "ABC";
        String message = "CRYPTOGRAPHY";
        String encryptedMessage = "CSAPUQGSCPIA";
        String result = Vigenere.encrypt(message, key);

        assertEquals(encryptedMessage,result);
    }

    @Test
    public void encrypt3(){
        String key = "QWERTY";
        String message = "CRYPTOGRAPHY";
        String encryptedMessage = "SNCGMMWNEGAW";
        String result = Vigenere.encrypt(message, key);

        assertEquals(encryptedMessage,result);
    }

    @Test
    public void decrypt1(){
        String key = "BREAKBREAKBR";
        String message = "DICPDPXVAZIP";
        String decryptedMessage = "CRYPTOGRAPHY";
        String result = Vigenere.decrypt(message, key);

        assertEquals(decryptedMessage,result);
    }

    @Test
    public void decrypt2(){
        String key = "ABC";
        String message = "CSAPUQGSCPIA";
        String decryptedMessage = "CRYPTOGRAPHY";
        String result = Vigenere.decrypt(message, key);

        assertEquals(decryptedMessage,result);
    }

    @Test
    public void decrypt3(){
        String key = "QWERTY";
        String message = "SNCGMMWNEGAW";
        String decryptedMessage = "CRYPTOGRAPHY";
        String result = Vigenere.decrypt(message, key);

        assertEquals(decryptedMessage,result);
    }
}
