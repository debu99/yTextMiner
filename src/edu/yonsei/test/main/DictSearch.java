package edu.yonsei.test.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

/*import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.IRAMDictionary;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;*/
import edu.yonsei.util.Document;
import edu.yonsei.util.Sentence;
import edu.yonsei.util.Token;

public class DictSearch {
	
	//public static IRAMDictionary dict;

	public static void main(String[] args) throws Exception {

        long start = Calendar.getInstance().getTimeInMillis();  

		System.out.println("--- START initialize dict---");
		//ArrayList<String> dictList = Dict2Array("data/corpus/_dict_disease.txt");
		ArrayList<String> dictList = (ArrayList<String>) Files.readAllLines(Paths.get("data/corpus/_dict_disease.txt"), Charset.defaultCharset());
		
		int dictSize = dictList.size();
		
		
		/*int index=-1;
		int elementLength = 0;
		for(int i=1; i< dictList.size(); i++) {
		    if(dictList.get(i).length > elementLength) {
		        index = i; elementLength = dictList.get(i).length;
		    }
		}
		System.out.println("i="+index+" length="+elementLength+"\n"+Arrays.toString(dictList.get(index)));*/
		
		
		System.out.println("dictSize="+dictSize);		
		
		/*for (String[] dictItem : dictList){
			System.out.println("dictItem.toString()="+String.join(" ", dictItem));			
		}*/
			
		
		
		System.out.println("--- END initialize dict---");

        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        
        
        /*start = Calendar.getInstance().getTimeInMillis();  
		System.out.println("--- START initialize wordnet---");
		//initDict();
		System.out.println("--- END initialize wordnet---");
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); */
		
        
        
        start = Calendar.getInstance().getTimeInMillis();  
		searchDict("ZIPSOR",1,5,dictList);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        
        
        start = Calendar.getInstance().getTimeInMillis();  
		searchDict("VOLTAREN",1,46,dictList);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 

        start = Calendar.getInstance().getTimeInMillis();  
		searchDict("VOLTAREN-XR",1,22,dictList);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 

        start = Calendar.getInstance().getTimeInMillis();  
		searchDict("ARTHROTEC",1,145,dictList);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        

        start = Calendar.getInstance().getTimeInMillis();  
        searchDict("SOLARAZE",1,3,dictList);
        searchDict("PENNSAID",1,4,dictList);
        searchDict("FLECTOR",1,1,dictList);
        searchDict("CAMBIA",1,4,dictList);
        searchDict("CATAFLAM",1,10,dictList);
        searchDict("DICLOFENAC-POTASSIUM",1,3,dictList);
        searchDict("DICLOFENAC-SODIUM",1,7,dictList);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        

        start = Calendar.getInstance().getTimeInMillis();  
        searchDict("LIPITOR",1,1000,dictList);
        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        
        
		//ArrayList dictList = new ArrayList();
		//dictList = Dict2Array();
		
		//System.out.println(dictList.toString());
		
	}
	
	public static boolean isAlpha(String str) {
			String newStr = str.replaceAll("[^A-Za-z]+", "");
			if(newStr.isEmpty()){
				return false;
			}else{
				return true;
			}
	}
	

	public static ArrayList<Integer> getIndexOf( String text, String str  ){
		ArrayList<Integer> res = new ArrayList<Integer>();
		int index = text.indexOf(str);
		while(index >= 0) {
			res.add(index);
			index = text.indexOf(str, index+1); 
		}
		
	     return res;
    }
	
	/*public static ArrayList<Integer> getIndexOf( String[] strings, String str  ){
	     ArrayList<Integer> res = new ArrayList<Integer>();
	     for(int i=0;i<strings.length;i++){
	    	 if(strings[i]!=null&&str.equals(strings[i])){
	    		 res.add(i);
	    	 }
	     }
	     
	     if(res!=null&&res.size()>0){
	    	 System.out.println("res="+res.toString()+" str="+str);
	     }
	     return res;
     }*/
	
	
	
	

	public static ArrayList<String> Dict2Array(String fileName ) throws Exception{
		Scanner dict = new Scanner(new FileReader(fileName));
		
		ArrayList<String> dictList = new ArrayList<String>();
		//HashMap dictMap = new HashMap();
		
		//String [] dictArray = null;

		while(dict.hasNext()){
			String dictItem = dict.nextLine().trim().toLowerCase();
			/*if(dictItem.contains(" ")){
				dictArray = dictItem.split(" ");
			}else{
				dictArray[0]=dictItem;				
			}*/
			
			dictList.add(dictItem);
		}	

		dict.close();
		return dictList;
		
	}
	
	private static HashMap<Integer,String> retIndexFromText(String text, List<String> nGramList, String dictItem){
		HashMap<Integer,String> dictItemIndex = new HashMap<Integer, String>();
		/*String str = dictItem.toString();		
		String dictStr = String.join(" ", dictItem);*/
		
		if(nGramList.contains(dictItem)){
			int index = text.indexOf(dictItem);
			while(index >= 0) {
			   System.out.println(index+"|"+dictItem+"\r\n");
			   dictItemIndex.put(index, dictItem);
			   index = text.indexOf(dictItem, index+1);
			}
		}
		
		
		
		return dictItemIndex;
		
	}
	
	
	
	private static void searchDict(String drugName,int startNumber, int endNumber,ArrayList<String> dictList) throws FileNotFoundException, Exception {
		//Scanner s = new Scanner(new FileReader("data/corpus/twitter_stream.txt"));
		//Scanner s = new Scanner(new FileReader("data/corpus/all_cataflam_original.txt"));

		System.out.println("--- "+drugName+" START ---");

		String fileName="data/corpus/_dict_disease_"+drugName+"_"+startNumber+"-"+endNumber+".txt";
		BufferedWriter out=new BufferedWriter(new FileWriter(fileName,true));
		

		for(int z=startNumber;z<=endNumber;z++){

			System.out.println("--- START from file: "+z+" ---");
			
			//Scanner src = new Scanner(new FileReader("data/corpus/"+drugName+"."+z+".txt"));

			
			String src = readFile("data/corpus/"+drugName+"."+z+".txt", Charset.defaultCharset());	
			String text = src.toLowerCase();
			//System.out.println("text="+text);
			
			
			
			int j=0;
			for (String dictItem : dictList) {
				String dictStr = null;
				if(dictItem.toUpperCase().equals(dictItem)){
					dictStr = dictItem;
					text = src;
				}else{
					dictStr = dictItem.toLowerCase();
				}

				if(dictStr.length()>text.length()) {
					continue;
				}
				
				String regStr=dictStr.replace("(", "\\(").replace(")", "\\)");
				Pattern HEYPATTERN1 = Pattern.compile(".*\\b"+regStr+"\\b.*");

				if(HEYPATTERN1.matcher(text).matches()) {
					ArrayList<Integer> res = getIndexOf(text,dictStr);
					if(!res.isEmpty()){
						for(int i : res){
							System.out.println(z+ "|" + i + "|" + dictStr+"|i="+j);  
							out.write(z+ "|" + i + "|" + dictStr+"|dict_row="+j+"\r\n"); 
							//out.write("\r\n");
						}
					}					
				}
				j++;			
				
			}
			
			System.out.println("--- END from file: "+z+" ---");
			//end of all files
		}
		out.close();
		File file = new File(fileName);
		if (file.length() == 0) {
		    // file empty
			file.delete();
		}
		System.out.println("--- "+drugName+" END ---");
		//end of searchDict
	}

	
	

	private static String readFile(String path, Charset encoding) throws IOException {

		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
	}
}
