package com.example.bsk;

import static org.junit.Assert.*;
import org.junit.Test;

public class CaesarTest {
    @Test
    public void encryptKey3() {
        int key = 3;
        String message = "CRYPTOGRAPHY";
        String encryptedMessage = "FUBSWRJUDSKB";
        String result = Caesar.encrypt(message, key);

        assertEquals(encryptedMessage, result);
    }
    
    @Test
    public void encryptKey29() {
        int key = 29;
        String message = "CRYPTOGRAPHY";
        String encryptedMessage = "FUBSWRJUDSKB";
        String result = Caesar.encrypt(message, key);

        assertEquals(encryptedMessage, result);
    }

    @Test
    public void encryptKey_23() {
        int key = -23;
        String message = "CRYPTOGRAPHY";
        String encryptedMessage = "FUBSWRJUDSKB";
        String result = Caesar.encrypt(message, key);

        assertEquals(encryptedMessage, result);
    }


    @Test
    public void decryptKey3() {
        int key = 3;
        String message = "FUBSWRJUDSKB";
        String encryptedMessage = "CRYPTOGRAPHY";
        String result = Caesar.decrypt(message, key);

        assertEquals(encryptedMessage, result);
    }
    
    @Test
    public void decryptKey29() {
        int key = 29;
        String message = "FUBSWRJUDSKB";
        String encryptedMessage = "CRYPTOGRAPHY";
        String result = Caesar.decrypt(message, key);

        assertEquals(encryptedMessage, result);
    }

    @Test
    public void decryptKey_23() {
        int key = -23;
        String message = "FUBSWRJUDSKB";
        String encryptedMessage = "CRYPTOGRAPHY";
        String result = Caesar.decrypt(message, key);

        assertEquals(encryptedMessage, result);
    }
}
