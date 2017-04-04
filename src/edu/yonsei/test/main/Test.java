package edu.yonsei.test.main;


import java.util.*;
import java.util.regex.Pattern;

public class Test
{
    public static void main(String args[])
    {
        long start = 0;
        int size = 100000;
        
        int n = 1;
        int length = (int)(Math.log10(n)+1);
        
        System.out.println("len="+length);
        
        /*
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("key1", "value1");
            map1.put("key2", "value2");
            map1.put("key3", "value3");
            map1.put(null, null);

            Map<String, String> map2 = new HashMap<String, String>();
            map2.put("key4", "value4");
            map2.put("key5", "value5");
            map2.put("key6", "value6");
            map2.put("key3", "replaced-value-of-key3-in-map2");
            // used only if map1 can be changes/updates with the same keys present in map2.
            map1.putAll(map2);*/

            // use below if you are not supposed to modify the map1.
           /* for (Map.Entry e : map2.entrySet())
                if (!map1.containsKey(e.getKey()))
                    map1.put(e.getKey().toString(), e.getValue().toString());*/
          /*  System.out.println(map1);
            
            map1.values().remove(null);
            System.out.println(map1);*/
        //String[] strings = new String[size];
        //Random random = new Random();
        /*
        String[] strings = {"abc","ccc","who","lose"};
        
        System.out.println(Arrays.asList(strings).indexOf("who"));*/
        
        /*ArrayList<Integer> list =new ArrayList();
        
        System.out.println(list.size());*/
        
        
       /* String str="burn of unspecified degree of single digit \\(finger \\(nail\\) other";
        Pattern HEYPATTERN = Pattern.compile(".*\\b"+str+"\\b.*");
        String text = "nausea. some pain relief. ";
        
        if (HEYPATTERN.matcher(text).matches()) {
            System.out.println("OUTPUT: SUCCESS!");
        } else {
        	System.out.println("OUTPUT: FAIL!");  
        }*/
        
        
        
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