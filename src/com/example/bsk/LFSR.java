package com.example.bsk;

import java.util.ArrayList;
import java.util.Arrays;

public class LFSR {
    private final Polynomial polynomial;
    private String seedTxt;
    private boolean[] seed;
    private ArrayList<Boolean> generatedChain = new ArrayList<>();
    private boolean status = false;
    private int loops = 0;

    public LFSR(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setLoops(int loops) {
        this.loops = loops;
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
        generatedChain.clear();
        int testIterations=0;

        boolean xor;
        boolean firstBit;
        boolean secBit;
        ArrayList<Integer> powers = polynomial.getPowers();

        while (testIterations<loops){
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

//        showGeneratedChain();

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
