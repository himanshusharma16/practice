package test;

import java.util.ArrayList;

public class Airtel extends Thread {
    public Printer p;

    Airtel(Printer p){
        this.p = p;
    }
    public static void main(String[] args) {
        Printer p = new Printer();
        Airtel t1 = new Airtel(p);
        Airtel t2 = new Airtel(p);
        Airtel t3 = new Airtel(p);
        Airtel t4 = new Airtel(p);
        Airtel t5 = new Airtel(p);
        Airtel t6 = new Airtel(p);
        Airtel t7 = new Airtel(p);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
    }

    @Override
    public void run() {
        p.print();
        p.write();
        p.read();
    }
}

class Printer{
    ArrayList<Integer> list = new ArrayList<>();

    public void read(){
        for(int i : list)
            System.out.println(Thread.currentThread() + " - " +i);
    }

    public void write(){
        list.add(10);
        list.add(20);
    }
    public void print(){
        int a = 40;
        a = a+10;
        System.out.println(a);
    }
}
