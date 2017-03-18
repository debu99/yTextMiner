package edu.yonsei.test.main;

import java.io.FileReader;
import java.util.Scanner;

import edu.yonsei.util.Sentence;
import edu.yonsei.util.Token;

public class NormalizationDict {
	
	public static String Sentence2Lemma(String s) throws Exception {
		String newStr="";

		Sentence sent = new Sentence(s);
		sent.preprocess();

		for(Token token : sent){
			//System.out.print(token.getToken() + "|");
			if(token.isStopword()){
				continue;
			}else{
				newStr=newStr+token.getLemma() + " ";	
			}
		}
		return newStr.trim();
	}
	
	public static void main(String[] args) throws Exception {
		//Scanner s = new Scanner(new FileReader("data/corpus/twitter_stream.txt"));
		//Scanner s = new Scanner(new FileReader("data/corpus/all_cataflam_original.txt"));
		Scanner s = new Scanner(new FileReader("data/corpus/test_corpus.txt"));
		
		
		int i=0;
		//for(i=0; i<10; i++) {
		while(s.hasNext()){
			String text = s.nextLine();
			
			String lemmaSent=Sentence2Lemma(text);
			System.out.print("\nOriginal Sentence: "+text);
			System.out.print("\nLemma Sentence: "+lemmaSent);
			
			int first_position=-1;
			int last_position=-1;
			String first_word="";
			String last_word="";

			Scanner d = new Scanner(new FileReader("data/corpus/Side_Effects_Dictionary_full.txt"));
			while(d.hasNext()){
				String dline = d.nextLine();
				//System.out.println("\nNow dict:"+dline);
				Sentence ds = new Sentence(dline);
				ds.preprocess();
				
				for(Token dtoken : ds){
					if(dtoken.isStopword()){
						continue;
					}
					else
					{
						int word_position = lemmaSent.indexOf(dtoken.getLemma());
						if(word_position==-1){
							break;
						}else{
							//System.out.println("\nFound dict word:"+dtoken.getToken());
							if((first_position==-1)&&(last_position==-1)){
								first_position=word_position;
								last_position=word_position;
								first_word=dtoken.getToken();
								last_word=dtoken.getToken();
							}else if((word_position<first_position)){
								first_position=word_position;		
								first_word=dtoken.getToken();					
							}else if(word_position>last_position){
								last_position=word_position;	
								last_word=dtoken.getToken();
							}							
						}							
					}					
				}							
			}

			d.close();
			System.out.println("\nfirst_word:"+first_word+" last_word:"+last_word);
			//System.out.println("\nfirst_pos:"+first_position+" last_pos:"+last_position);
			i+=1;
		}
		
		s.close();

	}

}
