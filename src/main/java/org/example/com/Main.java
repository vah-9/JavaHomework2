package org.example.com;

import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        MyArrayList<Integer> a = new MyArrayList<>();
        MyArrayList<String> b = new MyArrayList<>();

        for (int i = 0; i < 1000; i++) {
            a.add(random.nextInt(10000));
        }

        for (int i = 0; i < 100; i++) {
            a.remove(random.nextInt(100));
        }

        for (int i = 0; i < 50; i++) {
            a.add(random.nextInt(100),random.nextInt(100));
        }

        a.sort(Comparator.comparing(Integer::intValue));
        a.printAll();
        a.clear();
        a.printAll();
        for (int i = 0; i < 1000; i++) {

            b.add( Integer.toString(random.nextInt(10000)));
        }

        for (int i = 0; i < 100; i++) {
            b.remove(random.nextInt(100));
        }


        for (int i = 0; i < 50; i++) {
            b.add(random.nextInt(100),Integer.toString(random.nextInt(100)));
        }
        b.sort(Comparator.comparing(String::toString));

        b.printAll();
        System.out.println(b.get(2));

    }
}
