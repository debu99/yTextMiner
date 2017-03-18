package edu.yonsei.test.main;


import java.util.*;

public class Test
{
    public static void main(String args[])
    {
        long start = 0;
        int size = 100000;
        //String[] strings = new String[size];
        //Random random = new Random();
        
        String[] strings = {"abc","ccc","who","lose"};
        
        System.out.println(Arrays.asList(strings).indexOf("who"));
        
/*

        for (int i = 0; i < size; i++)
            strings[i] = "" + random.nextInt( size );

        start = System.currentTimeMillis();
        Arrays.sort(strings);
        System.out.println(Arrays.binarySearch(strings, "" + (size - 1) ));
        System.out.println("Sort & Search : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(Arrays.binarySearch(strings, "" + (size - 1) ));
        System.out.println("Search        : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(Arrays.asList(strings).contains( "" + (size - 1) ));
        System.out.println("Contains      : " + (System.currentTimeMillis() - start));*/
        
        
        
    }
}