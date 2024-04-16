package test.DP.numbers;

import java.util.Arrays;

//basic factorial.
public class Factorial {

    public static void main(String[] args) {
        new Factorial().printUpto(7);
    }

    private void printUpto(int index) {
        int[] fact = new int[index+1];
        fact[0] = 1;
        for(int i =1;i<=index;i++){
            fact[i] = i*fact[i-1];
        }
        Arrays.stream(fact).forEach(i->System.out.print(i+" "));
    }
}
