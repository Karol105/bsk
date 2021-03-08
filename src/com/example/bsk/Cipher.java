package com.example.bsk;

public interface Cipher {
    String encode(String message, Key key);
    String decode(String message, Key key);
}
