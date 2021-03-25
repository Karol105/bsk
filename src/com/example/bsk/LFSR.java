package com.example.bsk;

import java.util.ArrayList;

public class LFSR {
    String seed;

    int degree;
    // Define instance variables here.
    // Creates an LFSR with the specified seed and tap.
    public LFSR(String seed) {
        if (polynomialValidate(seed)){
            this.seed = seed;
        }
        else {
            System.out.println("Inserted polynomial is invalid");
        }
    }

    private boolean polynomialValidate(String polynomial){
        char [] pc = polynomial.toCharArray();
        int maxDegree=0;
        StringBuilder sbDegree;
        char previousChar='!';
        for (int i=0;i<pc.length;i++){
            if((pc[i]>='0')&&(pc[i]<='9')){
                ArrayList<Integer> fullValue = numberValidate(pc,pc[i], i);
                if(previousChar=='^'&&pc[i-2]=='x') {
                    sbDegree = new StringBuilder();
                    for (int number : fullValue
                    ) {
                        sbDegree.append(number);
                    }
                    int checkIfMaxDegree = Integer.parseInt(sbDegree.toString());
                    if (maxDegree < checkIfMaxDegree) {
                        maxDegree = checkIfMaxDegree;
                    }
                }
                i+=fullValue.size()-1;
                previousChar = pc[i];
                System.out.println("Actual maxDegree: "+ maxDegree);
            } else if (pc[i]=='x'){
                if(previousChar=='x'){
                    System.out.println("You can't write double x!");
                    return false;
                } else if (previousChar=='^'){
                    System.out.println("It's not a polynomial!");
                    return false;
                }
                previousChar=pc[i];
            } else if (pc[i]=='+'||pc[i]=='-'||pc[i]=='*'||pc[i]=='/'||pc[i]=='^'){
                if (previousChar=='+'||previousChar=='-'||previousChar=='*'||previousChar=='/'||previousChar=='^'){
                    System.out.println("You can't write two special signs next to each other!");
                    return false;
                }
                previousChar=pc[i];
            } else {
                System.out.println("You used invalid character!");
                return false;
            }
        }
        return maxDegree >= 2;
    }

    private ArrayList<Integer> numberValidate(char[] pc, char value, int index){
        ArrayList<Integer> fullValue = new ArrayList<>();

        int charToInt = Integer.parseInt(String.valueOf(value));
        System.out.println(charToInt);
        fullValue.add(charToInt);

        for (int i=index+1;i<pc.length;i++){
            if ((pc[i]>='0')&&(pc[i]<='9')){
                charToInt = Integer.parseInt(String.valueOf(pc[i]));
                System.out.println(charToInt);
                fullValue.add(charToInt);
            } else {
                break;
            }
        }
        return fullValue;
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
