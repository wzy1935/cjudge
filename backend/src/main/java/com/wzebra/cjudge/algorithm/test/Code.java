package com.wzebra.cjudge.algorithm.test;


import java.util.Scanner;

public class Code {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(rnd(0,Integer.MAX_VALUE-1) + " " + rnd(0,Integer.MAX_VALUE-1));
    }

    public static int rnd(int from, int to) {
        return (int) (from + Math.random() * (to - from + 1));
    }
}
