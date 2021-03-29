package com.example.bsk;

import java.util.ArrayList;

public class Polynomial {
    private int maxDegree=0;
    private ArrayList<Integer> powers = new ArrayList<>();
    private String txtPolynomial;

    public int getMaxDegree() {
        return maxDegree;
    }

    public ArrayList<Integer> getPowers() {
        return powers;
    }

    public String getTxtPolynomial() {
        return txtPolynomial;
    }

    public boolean polynomialValidate(String polynomial){
        char [] pc = polynomial.toCharArray();
        StringBuilder sbDegree;
        char previousChar='!';
        if(polynomial.equals("")){
            AppFrame.dialogMSG("You have to enter the polynomial", "Invalid key");
            return false;
        }
        if (pc[pc.length-1]=='+'||pc[pc.length-1]=='-'||pc[pc.length-1]=='^'){
            AppFrame.dialogMSG("Invalid  function ( last char cannot be '" + pc[pc.length-1] + "' )", "Invalid key");
            return false;
        }
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
                    addToPowers(checkIfMaxDegree);
                    if (maxDegree < checkIfMaxDegree) {
                        maxDegree = checkIfMaxDegree;
                    }
                }
                i+=fullValue.size()-1;
                previousChar = pc[i];
            } else if (pc[i]=='x'){
                if (previousChar=='^'){
                    AppFrame.dialogMSG("It's not a polynomial", "Invalid key");
                    return false;
                } else if (pc.length!=(i+1)&&pc[i+1]!='^'){
                    if(!notMultiX(pc, i)){
                        AppFrame.dialogMSG("Cannot multiply x", "Invalid key");
                        return false;
                    } else {
                        addToPowers(1);
                        if (maxDegree < 1) {
                            maxDegree = 1;
                        }
                    }
                } else if (pc.length==(i+1)){
                    addToPowers(1);
                    if (maxDegree < 1) {
                        maxDegree = 1;
                    }
                }
                previousChar=pc[i];
            } else if (pc[i]=='+'||pc[i]=='-'||pc[i]=='^'){
                if (previousChar=='+'||previousChar=='-'||previousChar=='^'){
                    AppFrame.dialogMSG("You can't write two special signs next to each other", "Invalid key");
                    return false;
                } else if(pc[i]=='^'){
                    if (!powerValidate(pc, i)){
                        AppFrame.dialogMSG("It's not a polynomial", "Invalid key");
                        return false;
                    }
                }
                previousChar=pc[i];
            } else {
                AppFrame.dialogMSG("You used invalid character. You can only use: \n'0-9', 'x', '^', '+', '-', \nwithout space ' '" , "Invalid key");
                return false;
            }
        }
        if(maxDegree<2){
            AppFrame.dialogMSG("You must enter at least second degree polynomial", "Invalid key");
            return false;
        } else if(powers.size()<2){
            AppFrame.dialogMSG("You must enter at least two x with power", "Invalid key");
            return false;
        }
        txtPolynomial = polynomial;
        return true;
    }

    private static ArrayList<Integer> numberValidate(char[] pc, char value, int index){
        ArrayList<Integer> fullValue = new ArrayList<>();

        int charToInt = Integer.parseInt(String.valueOf(value));
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

    private static boolean powerValidate(char[] pc, int index){
        for(int i=index+1;i<pc.length;i++){
            if(pc[i]=='+'||pc[i]=='-'){
                break;
            } else if(pc[i]=='x'){
                return false;
            }
        }
        return true;
    }

    private static boolean notMultiX(char[] pc, int index){
        for(int i=index+1;i<pc.length;i++){
            if(pc[i]=='+'||pc[i]=='-'){
                break;
            } else if(pc[i]=='x'){
                return false;
            }
        }
        return true;
    }

    private void addToPowers(int value){
        for (int p: powers
             ) {
            if (p==value){
                return;
            }
        }
        powers.add(value);
        if (maxDegree < value) {
            maxDegree = value;
        }
    }
}
