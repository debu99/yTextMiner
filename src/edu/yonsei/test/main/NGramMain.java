package edu.yonsei.test.main;

import java.io.FileReader;
import java.util.Scanner;

import edu.yonsei.util.Document;
import edu.yonsei.util.Sentence;

public class NGramMain {
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new FileReader("data/corpus/ARTHROTEC.4.txt"));
		
		while(s.hasNext()){
			String text = s.nextLine();
			Document doc = new Document(text);
			doc.preprocess();

			for(int ss=0; ss<doc.size(); ss++) {
				Sentence sent = doc.get(ss);
				int n = 2;

				System.out.println("Sentence # " + (ss+1));
				System.out.println(sent.getSentence());
				System.out.println(n + "-grams");
				System.out.println(sent.getNGrams(n));
				System.out.println();
				System.out.println(n + "-grams");
				System.out.println(sent.getNGrams_Original(n));
				System.out.println();
			}
		}
		
		s.close();
	}

}
