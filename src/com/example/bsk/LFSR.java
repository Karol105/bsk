package com.example.bsk;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LFSR {
    private final Polynomial polynomial;
    private String seedTxt;
    private boolean[] seed;
    private ArrayList<String> generatedChain = new ArrayList<>();
    private boolean status = true;
    private int loops = 0;

    private Timer timer;
    private TimerTask task;

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

    public ArrayList<String> getGeneratedChain() {
        return generatedChain;
    }

    public void generateChain(LFSRFrame  lfsrFrame){
        status = true;
        lfsrFrame.clearBitTable();
        generatedChain.clear();
        int[] iterations = {1};

        boolean[] xor = new boolean[1];
        boolean[] firstBit = new boolean[1];
        boolean[] secBit = new boolean[1];
        ArrayList<Integer> powers = polynomial.getPowers();

        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                firstBit[0] = seed[powers.get(0)-1];
                secBit[0] = seed[powers.get(1)-1];
                xor[0] = firstBit[0] != secBit[0];
                for (int i=2;i<powers.size();i++){
                    secBit[0] = seed[powers.get(i)-1];
                    xor[0] = xor[0] != secBit[0];
                }

                if(seed[seed.length-1]){
                    generatedChain.add("1");
                } else {
                    generatedChain.add("0");
                }

                lfsrFrame.addBitToTable(seed[seed.length-1]);

                step(xor[0]);

                iterations[0]++;

                if(!status){
                    loops = 0;
                    task.cancel();
                } else if(loops!=0&&loops<iterations[0]){
                    loops = 0;
                    task.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task,400,400);

    }

    public void showGeneratedChain() {
        for (String b: generatedChain
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
