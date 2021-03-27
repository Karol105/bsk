package com.example.bsk;

import java.util.ArrayList;

public class LFSR {
    private final Polynomial polynomial;
    private String seedTxt;
    private boolean[] seed;
    private ArrayList<Boolean> generatedChain = new ArrayList<>();
    boolean status = false;

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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Polynomial getPolynomial() {
        return polynomial;
    }

    public void setSeed(String seedTxt) {
        this.seedTxt = seedTxt;
        seed = new boolean[seedTxt.length()];
        int i=0;
        for (char c: seedTxt.toCharArray()
             ) {
            seed[i] = c == '1';
            i++;
        }
    }

    public String getSeed() {
        return seedTxt;
    }

    public ArrayList<Boolean> getGeneratedChain() {
        return generatedChain;
    }

    public void generateChain(){
        int testIterations=0;

        boolean xor;
        boolean firstBit;
        boolean secBit;
        ArrayList<Integer> powers = polynomial.getPowers();

        while (testIterations<7){
            firstBit = seed[powers.get(0)-1];
            secBit = seed[powers.get(1)-1];
            xor = firstBit!=secBit;
            for (int i=2;i<powers.size();i++){
                secBit = seed[powers.get(i)-1];
                xor = xor!=secBit;
            }
            generatedChain.add(seed[seed.length-1]);
            step(xor);

            testIterations++;
        }

        showGeneratedChain();

    }

    public void showGeneratedChain() {
        for (boolean b: generatedChain
             ) {
            System.out.println(b);
        }
    }

    public int step(boolean xor) {
        for (int i=seed.length-1; i>0;i--){
            seed[i]=seed[i-1];
        }
        seed[0]=xor;
        return 0;
    }
}
