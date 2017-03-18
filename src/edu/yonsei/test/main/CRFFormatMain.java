package edu.yonsei.test.main;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.yonsei.util.Sentence;
import edu.yonsei.util.Token;

public class CRFFormatMain {
	

	public static void main(String[] args) throws Exception {
		//scanFile("ZIPSOR",5);
		//scanFile("VOLTAREN-XR",1,22);
		//scanFile("VOLTAREN",1,46);
		//scanFile("SOLARAZE",1,3);
		//scanFile("PENNSAID",1,4);
		//scanFile("FLECTOR",1,1);
		//scanFile("CAMBIA",1,4);
		//scanFile("CATAFLAM",1,10);
		//scanFile("DICLOFENAC-POTASSIUM",1,3);
		//scanFile("DICLOFENAC-SODIUM",1,7);

		//scanFile("ARTHROTEC",1,145);	
		
		//scanFile("LIPITOR",1,545);	
		//scanFile("LIPITOR",546,546);		//546 is too big, WARNING: Parsing of sentence ran out of memory (length=820)
		//scanFile("LIPITOR",547,779);
		//**scanFile("LIPITOR",780,780);		//780 no response
		//scanFile("LIPITOR",781,1000);
		
		scanFile("LIPITOR",321,321);
		System.out.println("---FINISHED---");
		
	}

	private static void scanFile(String drugName,int startNumber, int endNumber) throws FileNotFoundException, Exception {
		//Scanner s = new Scanner(new FileReader("data/corpus/twitter_stream.txt"));
		//Scanner s = new Scanner(new FileReader("data/corpus/all_cataflam_original.txt"));
		for(int z=startNumber;z<=endNumber;z++){
			Scanner s = new Scanner(new FileReader("data/corpus/"+drugName+"."+z+".txt"));
			Scanner ann = new Scanner(new FileReader("data/corpus/"+drugName+"."+z+".ann"));
			
			String fileName="data/corpus/_crf_"+drugName+"_"+startNumber+"-"+endNumber+"_sent.txt";
			BufferedWriter out=new BufferedWriter(new FileWriter(fileName,true));
	
	        List integerList = new ArrayList<>();
	        //List<String> stringList = new ArrayList<>();

	        
	        
			int k=0;
			
			while(ann.hasNext()){
				String text = ann.nextLine();
				int adr_index = text.indexOf("ADR ");
				if(adr_index>0&&(text.substring(0,1)!="#")){
					String a[] = text.split("ADR ");  
					String b[] = a[1].split("	"); 
					if(b[0].contains(";")){
						String c[] = b[0].split(";"); 
						for(int i=0;i<c.length;i++){
							String d[]=c[i].split(" ");
							//stringList.add(d[1]);
					        integerList.add(new ArrayList<Integer>(Arrays.asList(Integer.parseInt(d[0]),Integer.parseInt(d[1]))));
						}					
					}else{
						String c[] = b[0].split(" "); 
						//stringList.add(b[1]);
						integerList.add(new ArrayList<Integer>(Arrays.asList(Integer.parseInt(c[0]),Integer.parseInt(c[1]))));
					}
					//System.out.println("b1="+b[1]+" c0="+c[0]+" c1="+c[1]);
					k++;
				}
			}

	        Collections.sort(integerList, new Comparator<ArrayList<Integer>>() {    
					@Override
					public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
						// TODO Auto-generated method stub
	                    return o1.get(0).compareTo(o2.get(0));
					}               
	        });
	        System.out.println(integerList);
			
			
			
	        /*
	        for(int i=0;i<integerList.size();i++){
	        	//integerArray[i] = (Integer[]) integerList.get(i);
	        	//System.out.println(integerList.get(i));
	        	for(int j = 0 ;j<integerList.get(i).length; j++){  
	            	System.out.println((integerList.get(i))[j]);
	        	}
	
	        }
			*/
			
			int i=0;
			//for(i=0; i<10; i++) {
			while(s.hasNext()){
				String text = s.nextLine();
						
				Sentence sent = new Sentence(text);
				sent.preprocess();
	
				System.out.println("Sentence # " + (i+1));
				System.out.println(sent.getSentence());
				//System.out.println("---Token/Stem/Lemma/POS/isStopword/NER---");
				
				int tokenLength=0;
				for(Token token : sent){
					//int indexFound=0;
					//System.out.println("tokenLeng1="+tokenLength);
/*					if(token.getStem().equals("")||token.getToken().contains("'")){
						tokenLength=tokenLength-1;
					}*/
					//System.out.println("tokenLeng2="+tokenLength);
					
					int m = text.indexOf(token.getToken(),tokenLength);
					//tokenLength+=token.getToken().length()+1;
					tokenLength=m+token.getToken().length();
/*					if(token.getStem().equals("")){
						tokenLength=m+token.getToken().length();
					}else{
						tokenLength=m+token.getToken().length()+1;					
					}*/

					//sent.get(m).e

					System.out.print(sent.size()+"|");
					System.out.print(m+"|");
					System.out.print(token.getToken() + "|");
					System.out.print(token.getStem() + "|");
					System.out.print(token.getLemma() + "|");
					System.out.print(token.getPOS() + "|");
					System.out.print(token.isStopword()+ "|");
					//System.out.print(token.getNER()+ "|");
					
					out.write(m+"|");
					out.write(token.getToken() + "|");
					if(token.getStem().equals("")){
						out.write("PUNCTUATION|");						
					}else{
						out.write(token.getStem() + "|");
					}
					
					out.write(token.getLemma() + "|");
					out.write(token.getPOS() + "|");
					out.write(token.isStopword()+ "|");
					
			        	
		        	//System.out.println((integerList.get(p))[0]);
		        	//System.out.println((integerList.get(p))[1]);
					if(integerList.size()>0){
				        for(int p=0;p<integerList.size();p++){
				        	//if(m<(integerList.get(p))[0]){
				        	if(m<((int)((ArrayList)integerList.get(p)).get(0))){
								System.out.print("O|");	
								out.write("O|");
								break;
				        	//}else if(m==(integerList.get(p))[0]){
				        	}else if(m==((int)((ArrayList)integerList.get(p)).get(0))){
								System.out.print("B|");
								out.write("B|");
								//indexFound+=1;
								break;
				        	//}else if((m>(integerList.get(p))[0])&&(m<(integerList.get(p))[1])){
				        	}else if(m>((int)((ArrayList)integerList.get(p)).get(0))&&(m<((int)((ArrayList)integerList.get(p)).get(1)))){
								System.out.print("I|");
								out.write("I|");
								break;
				        	//}else if(m>=(integerList.get(p))[1]){
				        	}else if(m>=((int)((ArrayList)integerList.get(p)).get(1))){
				        		continue;
				        	}
				        }
				        if(m>=((int)((ArrayList)integerList.get(integerList.size()-1)).get(1))){
							System.out.print("O|");	
							out.write("O|");	        	
				        }		        
					}else{
						System.out.print("O|");		
						out.write("O|");	
					}
			        
					System.out.println();
					out.newLine();
				}
				
				System.out.println();
				System.out.println();
				out.newLine();
				i+=1;
			}
			s.close();
			ann.close();
			out.close();

			//System.out.println("Total Sentence:"+i);
		}
	}
}
