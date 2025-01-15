package org.example.com;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {


        MyArrayList<Integer> a = new MyArrayList<Integer>();
        MyArrayList<Integer> b = new MyArrayList<Integer>();

        a.add(9); a.printAll();
        a.add(2); a.printAll();
        a.add(6); a.printAll();
        b.add(2); b.printAll();
        b.add(4); b.printAll();
        b.add(1); b.printAll();

        a.add(b); a.printAll();

        a.add(6,-12); a.printAll();

        a.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }); a.printAll();
        a.remove(2); a.printAll();
        a.clear(); a.printAll();



    }
}
