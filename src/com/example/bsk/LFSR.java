package com.example.bsk;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class LFSR {
    private String seed;
    private Polynomial polynomial;

    // Define instance variables here.
    // Creates an LFSR with the specified seed and tap.
    public LFSR(Polynomial polynomial) {
        this.polynomial = polynomial;
        System.out.println("seed: not entered yet");
        System.out.println("maxDegree: " + polynomial.getMaxDegree());
        System.out.print("Powers: ");
        for (int power: polynomial.getPowers()
             ) {
            System.out.print(power + " ");
        }
    }

    public Polynomial getPolynomial() {
        return polynomial;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getSeed() {
        return seed;
    }

    // Returns the length of the LFSR.
    public int length() {
        return 0;
    }

    // Returns bit i of this LFSR as 0 or 1.
    public int bitAt(int i) {
        return 0;
    }

    // Returns a string representation of this LFSR.
    public String toString() {
        return null;
    }

    // Simulates one step of this LFSR; returns next bit as 0 or 1.
    public int step() {
        return 0;
    }

    // Simulates k steps of this LFSR; returns next k bits as a k-bit integer.
    public int generate(int k) {
        return 0;
    }

    // Tests every method in this class.
    public static void main(String[] args) {

    }
}
