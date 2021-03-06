package com.example.bsk;

import com.example.bsk.Key;

public interface Cipher {
    String encode(String message, src.com.example.bsk.Key key);
    String decode(String message, src.com.example.bsk.Key key);
}
