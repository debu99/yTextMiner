package edu.yonsei.test.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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

public class ADR_DictSearch {

	public static void main(String[] args) throws Exception {

        long start = Calendar.getInstance().getTimeInMillis();  

		System.out.println("--- START initialize dict---");
		//ArrayList<String> dictList = Dict2Array();
		//int dictSize = dictList.size();
		HashMap dictHashMap = Dict2HashMap();
		int dictSize = dictHashMap.size();
		System.out.println("dictSize="+dictSize);		
		System.out.println("--- END initialize dict---");
        long end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        
        
        start = Calendar.getInstance().getTimeInMillis();  
		searchDict("ZIPSOR",1,5,dictHashMap);
		searchDict("VOLTAREN",1,46,dictHashMap);
		searchDict("VOLTAREN-XR",1,22,dictHashMap);
        searchDict("SOLARAZE",1,3,dictHashMap);
        searchDict("PENNSAID",1,4,dictHashMap);
        searchDict("FLECTOR",1,1,dictHashMap);
        searchDict("CAMBIA",1,4,dictHashMap);
        searchDict("CATAFLAM",1,10,dictHashMap);
        searchDict("DICLOFENAC-POTASSIUM",1,3,dictHashMap);
        searchDict("DICLOFENAC-SODIUM",1,7,dictHashMap);
        
		searchDict("ARTHROTEC",1,145,dictHashMap);
        searchDict("LIPITOR",1,1000,dictHashMap);
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
	
	
	
     /*
	
	public static ArrayList indexOfWord(String originalText, String wordLemma){

    	Token wordToken = new Token();
    	if(wordToken.getLemma().equals(wordLemma )){
            System.out.println("Index:"+entry.getKey());
            System.out.println("Word:"+entry.getValue());
    	}
    	
    	ArrayList resList = new ArrayList();
		int[] indexArray;
		int index = originalText.indexOf(keyWord);
		int n=0;
		while(index >= 0) {
		   //System.out.println(index);
		   indexArray[n]=index;
		   n+=1;
		   index = originalText.indexOf(keyWord, index+1);
		}
		System.out.println("indexArray="+indexArray.toString());
		resList.add(indexArray);
		return resList;
		
    	
	}*/
	
	
	
	/*
	public static HashSet<Object> Text2Lemma(String text,Sentence sent) throws Exception {
		//String newStr="";
		HashSet<Object> wordSets = new HashSet<Object>();  		
		
		int tokenLength=0;		
		
		for(Token token : sent){			
			String wordToken = token.getToken();
			
			int m = text.indexOf(wordToken,tokenLength);
			tokenLength=m+wordToken.length();
			System.out.print(m+"|");			
			//wordIndex.put(m,wordToken);
			
			System.out.print(wordToken + "|");	
			System.out.print(token.getLemma().toLowerCase() + "|");	
			
			if(!isAlpha(wordToken)){
				System.out.println("!!NOT only alphabets, goto Next...");		
				continue;								
			}
			if(wordToken.matches("^[0-9]*$")){
				System.out.println("Numbers, goto Next...");		
				continue;				
			}
			if(wordToken.matches("\\p{Punct}")){
				System.out.println("PUNCTUATION, goto Next...");		
				continue;				
			}
			if(token.getStem().equals("")){
				System.out.println("PUNCTUATION, goto Next...");		
				continue;
			}
			if(token.isStopword()){
				System.out.println("Stopwords, goto Next...");		
				continue;
			}		
			if(!wordSets.add(token.getLemma().toLowerCase())){
				System.out.println("Same Words, goto Next...");		
				continue;				
			}

			System.out.println();
		}
		return wordSets;
	}
	*/


	public static HashMap<Integer, String> Dict2HashMap() throws Exception{
		Scanner dict = new Scanner(new FileReader("data/corpus/_dict_adr.txt"));
				
		HashMap dictList = new HashMap();
		//HashMap dictMap = new HashMap();

		int lineId=1;
		while(dict.hasNext()){
			String text = dict.nextLine();
			//System.out.println("text:"+text);
			Sentence sent = new Sentence(text);
			sent.preprocess();
			String dictItem = "";
			for(Token token : sent){
				//System.out.print(token.getToken() + "|");
				//if(!isAlpha(token.getToken())||token.isStopword()){
				if(token.isStopword()){
					//System.out.println("***removed bcos not alphabet or stopword***");					
					continue;
				}else{
					dictItem=dictItem+" "+token.getLemma().toLowerCase();
				}
			}

			//System.out.println("item="+dictItem.trim()+"\n");	
			dictList.put(lineId, dictItem.trim());
			//dictList.add(dictItem.trim());
			//dictMap.put(lineId, dictItem.trim())
			lineId+=1;
		}	

		dict.close();
		return dictList;
		
	}
	
	
	/*public static ArrayList<String> Dict2Array() throws Exception{
		Scanner dict = new Scanner(new FileReader("data/corpus/_dict_adr.txt"));
				
		ArrayList<String> dictList = new ArrayList<String>();
		//HashMap dictMap = new HashMap();

		//int lineId=1;
		while(dict.hasNext()){
			String text = dict.nextLine();
			//System.out.println("text:"+text);
			Sentence sent = new Sentence(text);
			sent.preprocess();
			String dictItem = "";
			for(Token token : sent){
				//System.out.print(token.getToken() + "|");
				//if(!isAlpha(token.getToken())||token.isStopword()){
				if(token.isStopword()){
					//System.out.println("***removed bcos not alphabet or stopword***");					
					continue;
				}else{
					dictItem=dictItem+" "+token.getLemma().toLowerCase();
				}
			}

			//System.out.println("item="+dictItem.trim()+"\n");	
			dictList.add(dictItem.trim());
			//dictMap.put(lineId, dictItem.trim())
		}	

		dict.close();
		return dictList;
		
	}*/
	

	public static ArrayList<Integer> getIndexOf( String[] strings, String str  ){
	     ArrayList<Integer> res = new ArrayList<Integer>();
	     for(int i=0;i<strings.length;i++){
	    	 if(strings[i]!=null&&str.equals(strings[i])){
	    		 res.add(i);
	    	 }
	     }
	     
	     /*if(res!=null&&res.size()>0){
	    	 System.out.println("res="+res.toString()+" str="+str);
	     }*/
	     return res;
     }
	
	
	private static void searchDict(String drugName,int startNumber, int endNumber,HashMap<Integer, String> dictList) throws FileNotFoundException, Exception {
		//Scanner s = new Scanner(new FileReader("data/corpus/twitter_stream.txt"));
		//Scanner s = new Scanner(new FileReader("data/corpus/all_cataflam_original.txt"));

		System.out.println("--- "+drugName+" START ---");

		for(int z=startNumber;z<=endNumber;z++){

			System.out.println("--- START from file: "+z+" ---");
			
			Scanner src = new Scanner(new FileReader("data/corpus/"+drugName+"."+z+".txt"));
			
			String fileName="data/corpus/_dict_adr_"+drugName+"_"+startNumber+"-"+endNumber+".txt";
			BufferedWriter out=new BufferedWriter(new FileWriter(fileName,true));
			
			
			while(src.hasNext()){
				String text = src.nextLine();


				Document doc = new Document(text);
				doc.preprocess();
				
				for(int ss=0; ss<doc.size(); ss++) {

					Sentence sent_splitter = doc.get(ss);
					System.out.println("Sentence # " + (ss+1));
					System.out.println(sent_splitter.getSentence());	
					
					if(sent_splitter.size()<10&&!isAlpha(sent_splitter.toString())){
						System.out.println("---length<10 and only punctuation and number??---");
						continue;
						
					}
					
					if(ss==(doc.size()-1)&&sent_splitter.size()==1){
						System.out.println("---Last Sentence only punctuation!!---");
						break;
					}


					//all words in one sentence
					//HashSet<Object> wordSets = new HashSet<Object>();  	
					//word and index in every file
					
					LinkedHashMap<Integer,String> wordSetIndex = new LinkedHashMap<Integer, String>();
					String[] lemmaArray=new String[sent_splitter.size()];
					
					String lemmaText="";
					//for every sentence, get all meaningful words in it
					int indexNumber=0;
					int tokenLength=0;
					
					for(Token token : sent_splitter){
						//String[] wordSet = new String[2];
						String wordToken = token.getToken();			
						int m = text.indexOf(wordToken,tokenLength);
						//tokenLength+=token.getToken().length()+1;
						tokenLength=m+wordToken.length();

						System.out.print(m+"|");			
						
						System.out.print(wordToken + "|");						
						
						/*if(!isAlpha(wordToken)){
							System.out.print("!!NOT alphabets word, goto Next...");		
							continue;								
						}*/
						
						if(token.getStem().equals("")){
							System.out.print("Puntuction, goto Next...");		
							continue;				
						}

						if(token.isStopword()){
							System.out.print("Stopwords, goto Next...");		
							continue;
						}		
					
						
						//lemmaText=lemmaText+lemmaWord+" "; 
						/*if(!wordSets.add(token.getLemma().toLowerCase())){
							System.out.print("Same Words, goto Next...");		
							continue;				
						}*/

						//System.out.print(token.getLemma().toLowerCase() + "|");							
						//System.out.println("");	
						
						//wordSet[0]=wordToken;
						//wordSet[1]=lemmaWord;
						String lemmaWord = token.getLemma().toLowerCase();	
						lemmaArray[indexNumber]=lemmaWord;
						wordSetIndex.put(m,wordToken);
						indexNumber+=1;
						
						
					}
					//lemmaText=lemmaText.trim();
					//String[] firstArray = {"test1","","test2","test4",""};
					ArrayList<String> list = new ArrayList<String>();
					for (String s : lemmaArray)
					    if (s!=null){
					        list.add(s);
					    }
					
					System.out.println("\nlemmaArray before="+Arrays.toString(lemmaArray));
					lemmaArray = list.toArray(new String[list.size()]);			
					
			        System.out.println("\nlemmaArray="+Arrays.toString(lemmaArray));
					System.out.println("");
					

					

					ArrayList<Integer> resList = new ArrayList<Integer>();		//all index of words
					//ArrayList<String> wordnetResList = new ArrayList<String>();
					HashMap<String,ArrayList<Integer>> wordFound = new HashMap<String,ArrayList<Integer>>();
					

					//System.out.println("wordFound="+wordFound.toString());
					
					
					
					//for (String dictItem : dictList) {	
					for(Map.Entry<Integer, String> entry : dictList.entrySet()){ 	
						ArrayList tempList = new ArrayList();	//results for all dict words found in text
						ArrayList tempList2 = new ArrayList();	//split dict item(>=2words)
						/*System.out.println("dictItem="+dictItem);
						System.out.println("wordFound="+wordFound.toString());*/
						
						String dictItem=entry.getValue();
						
						if(dictItem.contains(" ")){
							int foundFlag=0;
							//int wordnetFlag=0;
							String[] dictItemSplit = dictItem.split(" ");
							
							for(int jjj=0;jjj<dictItemSplit.length;jjj++){						
								//if found in temp table, then not need to search big wordSets
								if(wordFound.containsKey(dictItemSplit[jjj])){
									//System.out.println("wordFound="+wordSetIndex.keySet().toString());
									foundFlag+=1;
									continue;
								}
								
								tempList2 = getIndexOf(lemmaArray, dictItemSplit[jjj]);
								if(tempList2.size()>0){		
									tempList.addAll(tempList2);
									//wordFound.add(dictItemSplit[jjj]);
									wordFound.put(dictItemSplit[jjj], tempList2);
									foundFlag+=1;
								}else{									
									break;			//one part of dict item not found then quit this item
								}
								/*System.out.println("wordFound="+wordFound.toString());
								System.out.println("foundFlag="+foundFlag);*/
							}
							

							/*for(int jjj=0;jjj<dictItemSplit.length;jjj++){	
								if(wordFound.containsKey(dictItemSplit[jjj])){
									wordnetFlag=+1;
									continue;
								}else{
									ArrayList<String> wordNetGrps=wordsFromWordNet(dictItemSplit[jjj]);
									if(wordNetGrps!=null){
										//System.out.println("wordNetRes="+wordNetGrps.toString());
										for(String word:wordNetGrps){
											tempList2 = getIndexOf(lemmaArray, word);
											if(tempList2.size()>0){
												wordnetResList.addAll(tempList2);
												wordnetFlag=+1;
												break;
											}
										}
									}
								}								
							}*/
							
							
							
							
							if(foundFlag>0&&dictItemSplit.length==foundFlag){
								/*System.out.println("dictItemSplit.length="+dictItemSplit.length);
								System.out.println("dictItem="+dictItem.toString());
								System.out.println("tempList="+tempList.toString());*/
								if(tempList.size()>0){	
									resList.addAll(tempList);	
								}else{					//all words are already found in history, this time not find yet
									//List<Integer> foundIndexes = new ArrayList<Integer>(); 
									//wordSetIndex.values().toArray();"
									for(String word : dictItemSplit){	
										resList.addAll((ArrayList<Integer>) wordFound.get(word));
									}						
								}		
								out.write("dictIndex="+entry.getKey()+" dictItem="+dictItem+"\r\n");
								System.out.println("dictIndex="+entry.getKey()+" dictItem="+dictItem);
							}
							
							/*if(wordnetFlag>0&&dictItemSplit.length==wordnetFlag){
								System.out.println("dictItemSplit.length="+dictItemSplit.length);
								System.out.println("dictItem="+dictItem.toString());
								System.out.println("tempList="+tempList.toString());
								if(tempList.size()>0){
									resList.addAll(tempList);	
								}else{
									//List<Integer> foundIndexes = new ArrayList<Integer>(); 
									//wordSetIndex.values().toArray();"
									for(String word : dictItemSplit){	
										resList.addAll((ArrayList<Integer>) wordFound.get(word));
									}						
								}															
							}*/

							/*System.out.println("dictItem="+dictItem);
							System.out.println("tempList="+tempList.toString());
							System.out.println("tempList.size="+tempList.size());*/
							
						}else{
							if(wordFound.containsKey(dictItem)){
								out.write("dictIndex="+entry.getKey()+" dictItem="+dictItem+"\r\n");
								System.out.println("dictIndex="+entry.getKey()+" dictItem="+dictItem);
								continue;
							}
							tempList = getIndexOf(lemmaArray, dictItem);
							/*if(tempList.size()>0){
								System.out.println("-----tempList="+tempList.toString());
							}*/
							if(tempList.size()>0){		//same word could find more than 1 time

								/*System.out.println("tempList.size()="+tempList.size());
								System.out.println("add to wordFound="+dictItem);*/
								wordFound.put(dictItem, tempList);
								resList.addAll(tempList);
								out.write("dictIndex="+entry.getKey()+" dictItem="+dictItem+"\r\n");
								System.out.println("dictIndex="+entry.getKey()+" dictItem="+dictItem);
							}
						}
					}					
					System.out.println("resList="+resList.toString());
					System.out.println("-------------------");
					
					if(resList.size()>0){						
						//Set<String> s = new HashSet<String>(resList);
						Set<Integer> hs = new HashSet<>();
						hs.addAll(resList);
						//resList.clear();
						//resList.addAll(hs);
						System.out.println("hs="+hs.toString());
						
				        List<Integer> indexes = new ArrayList<Integer>(wordSetIndex.keySet());
						List<String> values = new ArrayList<String>(wordSetIndex.values()); 
						System.out.println("indexes="+indexes.toString());
						System.out.println("values="+values.toString());
						
						for(int indexNum : hs){	
							System.out.println("INDEX: "+indexes.get(indexNum));	// ==> index of Text
							System.out.println("WORD: "+values.get(indexNum));	// ==> original Word
							out.write(z+"|"+indexes.get(indexNum)+"|"+values.get(indexNum)+"\r\n");
						}
						
					}
					System.out.println("-------------------");	
					
					
					
					
					/*
					//temporary HashSet hold word found in sentence
					HashSet<Object> tempWordFound = new HashSet<Object>();
					for (String dictItem : dictList) {		
						String[] dictItemSplit = dictItem.split(" ");
						int foundFlag=0;
						for(int jjj=0;jjj<dictItemSplit.length;jjj++){						
							//if found in temp table, then not need to search big wordSets
							if(tempWordFound.contains(dictItemSplit[jjj])){
								foundFlag+=1;
								continue;
							}
							
							if(wordSets.contains(dictItemSplit[jjj])){
								foundFlag+=1;
								tempWordFound.add(dictItemSplit[jjj]);
							}else{
								//one of word in a dict item not found, jump to next word.
								continue;
							}
							//found at least half of the dict item(for items more than 2 words)
						}
						
						if(foundFlag>dictItemSplit.length*0.7){
							wordFoundList.add(dictItem);
							System.out.println("dictItemLength:"+dictItemSplit.length+" foundFlag="+foundFlag);
							System.out.println("dictID:"+(dictList.indexOf(dictItem)+1)+" dictItem="+dictItem);
							//System.out.println("tempWordfound:"+tempWordFound.toString());
							System.out.println("wordFoundList:"+wordFoundList.toString());						
						}
						
					}			
					
*//*
					//from wordFoundList, get word counts >=2
					ArrayList<String> maxStrings = new ArrayList<String>();
					for(String tempString : wordFoundList){
						int maxLength=2;
						//System.out.println(" tempString="+tempString);
						if(tempString.contains(" ")){
							String[] tempSplit = tempString.split(" ");
							if(tempSplit.length>maxLength){
								maxStrings.clear();
								maxStrings.add(tempString);
								maxLength = tempSplit.length;
							}else if(tempSplit.length==maxLength){
								maxStrings.add(tempString);						
							}
						}
					}
					System.out.println("maxStrings="+maxStrings.toString());
					
					//clean wordFoundList, remove shorter ones that contains in longer words items
					ArrayList<String> cleanedList = (ArrayList<String>)wordFoundList.clone();
					if(!maxStrings.isEmpty()){
						for(String maxItem : maxStrings){
							for(String wordFoundItem : wordFoundList){
								if(!maxItem.equals(wordFoundItem)&&maxItem.contains(wordFoundItem)){
									cleanedList.remove(wordFoundItem);
								}
							}
						}
					}
					System.out.println("cleanedList="+cleanedList.toString());
					*/
					
					//get index of all strings in cleandList
					//wordSetIndex.put(m,wordToken);	
/*	        		System.out.println("-------------------");				
					for(String cleanedItem : cleanedList){
				        for (Entry<Integer, String> entry : wordSetIndex.entrySet()) {
				        	   //System.out.println(entry.getKey() + " : " + entry.getValue());
				        	Token wordToken = new Token(entry.getValue());
				        	if(wordToken.getLemma().equals(cleanedItem)){
				                System.out.println("Index:"+entry.getKey());
				                System.out.println("Word:"+entry.getValue());
				        	}
				        	
				        }
					}

	        		System.out.println("-------------------");*/
					
				}
				
			
			
				//System.out.println("Total Sentence:"+i);
				//end of while for one file
			}

			System.out.println("--- END from file: "+z+" ---");
			out.write("-------------------\r\n");	
			//end of all files
			src.close();
			out.close();

			File file = new File(fileName);
			if (file.length() == 0) {
			    // file empty
				file.delete();
			}
			
		}
		System.out.println("--- "+drugName+" END ---");
		//end of searchDict
	}
}
