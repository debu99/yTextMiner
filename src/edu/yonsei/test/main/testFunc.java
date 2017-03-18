package edu.yonsei.test.main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class testFunc {



		public static ArrayList<Integer> getIndexOf( String[] strings, String str  ){
		     ArrayList<Integer> res = new ArrayList<Integer>();
		     for(int i=0;i<strings.length;i++){
		    	 if(str.equals(strings[i])){
		    		 res.add(i);
		    	 }
		     }
		    	 return res;
	     }

    	public static Object getElementAt(LinkedHashMap map, int index) {
    		return map.get( (map.keySet().toArray())[ index ] );    
    	}
    	
    	
	
	public static void main(String[] args) throws Exception {
		

        String[] strings = {"abc","lose","who","lose"};
        //System.out.println(Arrays.toString(strings));
        
        String str="hi hello everyone, the show is begining right now, enjoy it";
        
        if(str.contains("hello everyone")){
        	System.out.println("cotains");
        }else{
        	System.out.println("NOFOUND!");
        }
        return;
        
        /*
       LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("juan", 2);
        map.put("pedro", 3);
        map.put("pablo", 5);
        map.put("iphoncio",9);*/
        
        //System.out.println(getElementAt(map,1));
        /*

		HashMap<String,ArrayList<Integer>> wordFound = new HashMap<String,ArrayList<Integer>>();
		
		ArrayList<Integer> arr1=new ArrayList<Integer>();
		ArrayList<Integer> arr2=new ArrayList<Integer>();
		ArrayList<Integer> arr3=new ArrayList<Integer>();
		arr1.add(3);
		arr1.add(4);
		arr1.add(5);
		arr2.add(6);
		arr2.add(7);
		arr2.add(8);
		arr3.add(9);
		arr3.add(2);
		arr3.add(11);
		wordFound.put("A", arr1);
		wordFound.put("B", arr2);
		wordFound.put("C", arr3);
		

		ArrayList<Integer> resList = new ArrayList<Integer>();*/

        //List<Integer> values2 = new ArrayList<Integer>(wordFound.values());
        //wordFound.values()
		
		/*for(Map.Entry entry:wordFound.entrySet()){
		    System.out.print(entry.getKey() + " : " + entry.getValue());
		}*/
		
		/*Set<String> keys = wordFound.keySet();
		System.out.println("All keys are: " + keys);
		// To get all key: value
		for(String key: keys){
		    System.out.println(key + ": " + wordFound.get(key));
		}*/
		/*
		
		for(Map.Entry entry:wordFound.entrySet()){
		    System.out.print(entry.getKey() + " : " + entry.getValue());

		    System.out.println("val="+entry.getValue().toString());
		    resList.addAll((ArrayList<Integer>) entry.getValue());
		}*/
		
		/*
		for (ArrayList<Integer> entry : wordFound.values()) {
		    // do something with tab

		    System.out.println("val="+entry.toString());
		    resList.addAll(entry);
			
		}*/
        
		/*for ( Map.Entry<String, ArrayList<Integer>> entry : wordFound.entrySet()) {
		    String key = wordFound.getKey();
		    ArrayList<Integer> val = ((Entry<String, ArrayList<Integer>>) wordFound).getValue();
		    System.out.println("key="+key);
		    System.out.println("val="+val.toString());
		    resList.addAll(val);
		}*/
		
		
        
		
		/*
		System.out.println("resList="+resList);
		*/
        
        
        /*
		List<String> indexes = new ArrayList<String>(map.keySet()); // <== Parse
        List<Integer> values = new ArrayList<Integer>(map.values());*/
        
		//System.out.println(indexes.get(0));	// ==> juan
		//System.out.println(indexes.indexOf("juan"));     // ==> 0
		//System.out.println(indexes.indexOf("iphoncio"));      // ==> 3

		//System.out.println(values.get(0));	// ==> juan

        
        //ArrayList<Integer> res = getIndexOf(strings, "lose");
        
        //System.out.println(res.toString());
        
/*        ArrayList<String> src = new ArrayList<String>();
        src.add("test string1");
        src.add("test string2");
        ArrayList<String> dest= new ArrayList<String>();
        dest.add("test in dest");
        src.clear();
        dest.addAll(src);
        dest.add("test in dest2");
        System.out.println(dest.toString());*/
        

    	
    	
        
/*		long start = 0;
        int size = 100000;
        String[] strings = new String[size];
        Random random = new Random();


        for (int i = 0; i < size; i++)
            strings[i] = "" + random.nextInt( size );
        System.out.println(strings.toString());

        start = System.currentTimeMillis();
        Arrays.sort(strings);
        System.out.println(Arrays.binarySearch(strings, "" + (size - 1) ));
        System.out.println("Sort & Search : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(Arrays.binarySearch(strings, "" + (size - 1) ));
        System.out.println("Search        : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(Arrays.asList(strings).contains( "" + (size - 1) ));
        System.out.println("Contains      : " + (System.currentTimeMillis() - start));
        //Arrays.asList(strings).
*/	}

}
