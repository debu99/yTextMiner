/*package edu.yonsei.test.main;

import java.io.*; 
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.mit.jwi.Dictionary; 
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.IRAMDictionary;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.*;
import edu.mit.jwi.morph.WordnetStemmer; 

public class  HiWordnet {  
  public static void main(String[] args) throws IOException, InterruptedException  { 

    //通过环境变量，建立指向WordNet词典目录的URL。   
    //String wnhome = System.getenv("WNHOME");    
    //String path = wnhome + File.separator + "dict";   
    String path = "c:/WordNet3.0/dict";
    URL url = new URL("file",  null, path);   

    //建立词典对象并打开它。   
    IRAMDictionary dict = new  RAMDictionary(url,ILoadPolicy.NO_LOAD); 
    dict.open();
    //dict.load(true);
    

    //查询money这个词的第一种意思，注意get函数的参数表示第#种意思。
    //POS后面的参数表示要选哪种词性的含义   

	
	WordnetStemmer stem =  new WordnetStemmer(dict);
	System.out.println("feet：" + stem.findStems("feet", POS.NOUN));
	List<String> test = stem.findStems("feet", POS.NOUN);
	for (int i = 0; i < test.size(); i++) {
	    System.out.println("feet :"+test.get(i));
	}
    

    //IIndexWord idxWord = dict.getIndexWord("go", POS.VERB);
    IIndexWord idxWord = dict.getIndexWord("dog", POS.NOUN);
    if(idxWord==null){
    	System.out.println("idxWord is null");
    	return ;
    }
    IWordID wordID = idxWord.getWordIDs().get(0);
    IWord word = dict.getWord(wordID);
    //Adding Related Words to List of Realted Words
    ISynset synset = word.getSynset();
    System.out.println("synset size="+synset.getWords().size());
    for (IWord w : synset.getWords()) {
        System.out.println(w.getLemma());
    }

	

    //IIndexWord  idxWord = dict.getIndexWord("go", POS.VERB);   
    
    
    
    
    //IWordID wordID =  (IWordID)idxWord.getWordIDs().get(1);   
    //IWord word =  dict.getWord(wordID);   
    //ISynset synset = word.getSynset();
    
    
    List<IWordID> wordIDList = (List<IWordID>)idxWord.getWordIDs();
    for(int idIndex = 0; idIndex < wordIDList.size(); idIndex++)
    {
    	
    	IWordID wordID = wordIDList.get(idIndex);
    	IWord word = dict.getWord(wordID);
		System.out.println("Id = " + wordID);		
		System.out.println(" Lemma = " + word.getLemma());		
		//System.out.println(" Gloss = " + word.getSynset().getGloss());
		
		ISynset synset = word.getSynset();		
		String LexFileName = synset.getLexicalFile().getName();		
		System.out.println("Lexical Name : " + LexFileName);

		*//** Finding stem for the word. *//*
		WordnetStemmer stem = new WordnetStemmer(dict);
		//System.out.println("test" + stem.findStems(key, POS.NOUN));
		ArrayList<String> antonymsList = new ArrayList<String>();
		List<IWordID> relatedWords = word.getRelatedWords();
		Map<IPointer, List<IWordID>> map = word.getRelatedMap();
		AdjMarker marker = word.getAdjectiveMarker();
		for (IWordID antonym : word.getRelatedWords()) {	
			String meaning = dict.getWord(antonym).getLemma();
			antonymsList.add(meaning);
			System.out.println("Antonym: " + meaning);
			System.out.println("Antonym POS: " + dict.getWord(antonym).getPOS());
		}
		
    }
		

    String LexFileName = synset.getLexicalFile().getName();
    System.out.println("Lexical Name : "+ LexFileName);

    System.out.println("----------");
    
    System.out.println("Id = " + wordID);    
    System.out.println("Lemma = " + word.getLemma());   
    System.out.println("----------");
    for(IWord w: synset.getWords()){
    	System.out.println(w.getLemma());
    }
    System.out.println("----------");
    
    System.out.println("Gloss  = " + word.getSynset().getGloss()+"\n");     

    //第二种意思   
    IWordID wordID2 =  (IWordID)idxWord.getWordIDs().get(1);   
    IWord word2 =  dict.getWord(wordID2);    
    System.out.println(word2.getSynset().getGloss());   

    //第三种意思   
    IWordID  wordID3 = (IWordID)idxWord.getWordIDs().get(2);   
    IWord word3 =  dict.getWord(wordID3);    
    System.out.println(word3.getSynset().getGloss()); 
    
    
    
  }
}*/