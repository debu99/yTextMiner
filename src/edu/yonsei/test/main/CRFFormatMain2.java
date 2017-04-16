package edu.yonsei.test.main;

import java.lang.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import edu.yonsei.util.Document;
import edu.yonsei.util.Sentence;
import edu.yonsei.util.Token;

public class CRFFormatMain2 {

	private static String[] test_set = {"arthrotec.100","arthrotec.104","arthrotec.106","arthrotec.108","arthrotec.110","arthrotec.111","arthrotec.113","arthrotec.122","arthrotec.124","arthrotec.125","arthrotec.127","arthrotec.128","arthrotec.13","arthrotec.130","arthrotec.134","arthrotec.14","arthrotec.140","arthrotec.17","arthrotec.2","arthrotec.20","arthrotec.21","arthrotec.23","arthrotec.26","arthrotec.27","arthrotec.28","arthrotec.29","arthrotec.33","arthrotec.41","arthrotec.5","arthrotec.50","arthrotec.52","arthrotec.55","arthrotec.6","arthrotec.61","arthrotec.65","arthrotec.67","arthrotec.7","arthrotec.71","arthrotec.73","arthrotec.78","arthrotec.82","arthrotec.83","arthrotec.93","arthrotec.94","arthrotec.96","cambia.4","cataflam.1","cataflam.4","cataflam.9","diclofenac-sodium.1","diclofenac-sodium.2","lipitor.101","lipitor.103","lipitor.104","lipitor.108","lipitor.109","lipitor.110","lipitor.112","lipitor.115","lipitor.116","lipitor.118","lipitor.12","lipitor.124","lipitor.127","lipitor.129","lipitor.13","lipitor.130","lipitor.133","lipitor.138","lipitor.140","lipitor.144","lipitor.149","lipitor.153","lipitor.155","lipitor.157","lipitor.159","lipitor.164","lipitor.165","lipitor.167","lipitor.168","lipitor.17","lipitor.171","lipitor.172","lipitor.173","lipitor.174","lipitor.179","lipitor.18","lipitor.183","lipitor.184","lipitor.186","lipitor.190","lipitor.192","lipitor.193","lipitor.201","lipitor.202","lipitor.203","lipitor.207","lipitor.209","lipitor.210","lipitor.212","lipitor.215","lipitor.216","lipitor.219","lipitor.225","lipitor.227","lipitor.228","lipitor.229","lipitor.23","lipitor.232","lipitor.233","lipitor.234","lipitor.239","lipitor.240","lipitor.241","lipitor.248","lipitor.253","lipitor.258","lipitor.259","lipitor.260","lipitor.261","lipitor.264","lipitor.27","lipitor.270","lipitor.272","lipitor.273","lipitor.274","lipitor.276","lipitor.28","lipitor.285","lipitor.290","lipitor.292","lipitor.295","lipitor.3","lipitor.300","lipitor.304","lipitor.306","lipitor.309","lipitor.310","lipitor.312","lipitor.317","lipitor.318","lipitor.319","lipitor.32","lipitor.326","lipitor.327","lipitor.334","lipitor.338","lipitor.339","lipitor.342","lipitor.343","lipitor.344","lipitor.345","lipitor.349","lipitor.35","lipitor.351","lipitor.355","lipitor.356","lipitor.363","lipitor.368","lipitor.37","lipitor.374","lipitor.379","lipitor.384","lipitor.385","lipitor.388","lipitor.391","lipitor.392","lipitor.393","lipitor.394","lipitor.4","lipitor.400","lipitor.402","lipitor.405","lipitor.406","lipitor.408","lipitor.411","lipitor.418","lipitor.42","lipitor.420","lipitor.424","lipitor.425","lipitor.430","lipitor.431","lipitor.437","lipitor.441","lipitor.443","lipitor.444","lipitor.445","lipitor.446","lipitor.45","lipitor.450","lipitor.459","lipitor.46","lipitor.460","lipitor.463","lipitor.466","lipitor.468","lipitor.469","lipitor.472","lipitor.478","lipitor.481","lipitor.482","lipitor.483","lipitor.484","lipitor.485","lipitor.486","lipitor.487","lipitor.492","lipitor.5","lipitor.50","lipitor.500","lipitor.505","lipitor.506","lipitor.508","lipitor.511","lipitor.514","lipitor.517","lipitor.518","lipitor.52","lipitor.521","lipitor.522","lipitor.524","lipitor.526","lipitor.527","lipitor.528","lipitor.529","lipitor.53","lipitor.530","lipitor.543","lipitor.544","lipitor.545","lipitor.546","lipitor.550","lipitor.555","lipitor.559","lipitor.56","lipitor.560","lipitor.562","lipitor.563","lipitor.565","lipitor.566","lipitor.567","lipitor.568","lipitor.569","lipitor.571","lipitor.575","lipitor.576","lipitor.582","lipitor.587","lipitor.596","lipitor.597","lipitor.598","lipitor.6","lipitor.60","lipitor.601","lipitor.603","lipitor.606","lipitor.609","lipitor.61","lipitor.611","lipitor.613","lipitor.614","lipitor.616","lipitor.622","lipitor.634","lipitor.638","lipitor.639","lipitor.640","lipitor.642","lipitor.643","lipitor.647","lipitor.65","lipitor.651","lipitor.655","lipitor.657","lipitor.661","lipitor.662","lipitor.666","lipitor.668","lipitor.669","lipitor.67","lipitor.674","lipitor.675","lipitor.679","lipitor.68","lipitor.681","lipitor.682","lipitor.683","lipitor.690","lipitor.692","lipitor.695","lipitor.697","lipitor.699","lipitor.70","lipitor.700","lipitor.702","lipitor.707","lipitor.708","lipitor.711","lipitor.714","lipitor.715","lipitor.717","lipitor.720","lipitor.721","lipitor.723","lipitor.724","lipitor.725","lipitor.728","lipitor.736","lipitor.74","lipitor.741","lipitor.742","lipitor.743","lipitor.747","lipitor.750","lipitor.754","lipitor.759","lipitor.761","lipitor.768","lipitor.770","lipitor.773","lipitor.774","lipitor.775","lipitor.780","lipitor.781","lipitor.784","lipitor.786","lipitor.788","lipitor.79","lipitor.791","lipitor.798","lipitor.799","lipitor.80","lipitor.800","lipitor.801","lipitor.804","lipitor.805","lipitor.808","lipitor.813","lipitor.814","lipitor.818","lipitor.819","lipitor.82","lipitor.823","lipitor.824","lipitor.825","lipitor.827","lipitor.828","lipitor.830","lipitor.831","lipitor.834","lipitor.835","lipitor.839","lipitor.841","lipitor.844","lipitor.847","lipitor.85","lipitor.852","lipitor.858","lipitor.861","lipitor.865","lipitor.868","lipitor.869","lipitor.871","lipitor.873","lipitor.876","lipitor.878","lipitor.88","lipitor.881","lipitor.882","lipitor.885","lipitor.889","lipitor.89","lipitor.890","lipitor.891","lipitor.892","lipitor.893","lipitor.894","lipitor.898","lipitor.90","lipitor.901","lipitor.902","lipitor.907","lipitor.91","lipitor.913","lipitor.915","lipitor.916","lipitor.917","lipitor.918","lipitor.92","lipitor.920","lipitor.923","lipitor.924","lipitor.925","lipitor.926","lipitor.927","lipitor.928","lipitor.93","lipitor.931","lipitor.932","lipitor.936","lipitor.937","lipitor.938","lipitor.94","lipitor.940","lipitor.945","lipitor.949","lipitor.95","lipitor.955","lipitor.957","lipitor.959","lipitor.962","lipitor.964","lipitor.965","lipitor.968","lipitor.971","lipitor.975","lipitor.982","lipitor.985","lipitor.987","lipitor.993","lipitor.996","lipitor.999","pennsaid.3","pennsaid.4","voltaren.1","voltaren.12","voltaren.2","voltaren.21","voltaren.24","voltaren.26","voltaren.28","voltaren.30","voltaren.33","voltaren.37","voltaren.39","voltaren.42","voltaren.44","voltaren.5","voltaren-xr.1","voltaren-xr.11","voltaren-xr.12","voltaren-xr.17","voltaren-xr.2","voltaren-xr.20","voltaren-xr.22","voltaren-xr.5","zipsor.1","zipsor.3","zipsor.4"};
	
	public static void main(String[] args) throws Exception {

        long start = Calendar.getInstance().getTimeInMillis();  
        long end = Calendar.getInstance().getTimeInMillis();
        
	
        
		scanFile("ZIPSOR",1,5);
		scanFile("SOLARAZE",1,3);
		scanFile("PENNSAID",1,4);
		scanFile("FLECTOR",1,1);
		scanFile("CAMBIA",1,4);
		scanFile("CATAFLAM",1,10);
		scanFile("DICLOFENAC-POTASSIUM",1,3);
		scanFile("DICLOFENAC-SODIUM",1,7);
		scanFile("VOLTAREN-XR",1,22);
		scanFile("VOLTAREN",1,46);
		
		scanFile("ARTHROTEC",1,145);

        end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
		
		scanFile("LIPITOR",1,1000);	
		end = Calendar.getInstance().getTimeInMillis();
        System.out.println("Time: " + (end - start)/1000+"s"); 
        
		
		System.out.println("---ALL FINISHED---");
		
	}

	
	private static List<Integer> dictExtract(String drugName, int startNumber, int endNumber, int index, String type) throws FileNotFoundException {
		

        List<Integer> integerDictList = new ArrayList<>();
        
		
        String filename = "data/corpus/_dict_"+type+"_"+drugName+"_"+startNumber+"-"+endNumber+".txt";
        //String filename = "data/corpus/_dict_"+type+"_"+drugName+"_1-1000.txt";
		
        
        File f = new File(filename);
		if(!f.exists()) { 
			System.out.println("_dict_"+type+"_"+drugName+"_"+startNumber+"-"+endNumber+".txt is not exist!!!");
			return integerDictList;
		}
		
		Scanner ann_dict = new Scanner(new FileReader(filename));		
        int z = index;
		System.out.println("z="+z);
		int z_len=1;
		if(z>0){
			z_len = (int)(Math.log10(z)+1);			
		}
		
		while(ann_dict.hasNext()){
			String text = ann_dict.nextLine();
			if(!Character.isDigit(text.charAt(0))){
				continue;
			}else if(text.substring(0,z_len+1).equals(z+"|")){
				System.out.println("text="+text+"\n"+text.substring(0,4));
				String[] a = text.split("\\|");
				integerDictList.add(Integer.parseInt(a[1]));
			}else{
				continue;
			}
		}	
			/*if(z<10){
				if(text.substring(0,2).equals(z+"|")){
					//System.out.println(text);
					String[] a = text.split("\\|");
					integerDictList.add(Integer.parseInt(a[1]));
					
				}else{
					continue;
				}
			}else if(z<100){
				if(text.substring(0,3).equals(z+"|")){
					String[] a = text.split("\\|");
					integerDictList.add(Integer.parseInt(a[1]));
				}else{
					break;
				}			
			}else if(z<1000){		
				if(text.substring(0,4).equals(z+"|")){
					String[] a = text.split("\\|");
					integerDictList.add(Integer.parseInt(a[1]));
				}else{
					break;
				}
				
			}else if(z<10000){
				if(text.substring(0,5).equals(z+"|")){
					String[] a = text.split("\\|");
					integerDictList.add(Integer.parseInt(a[1]));
				}else{
					break;
				}
				
			}*/
			

		Collections.sort(integerDictList);
        System.out.println(type+"_Dict="+integerDictList+"\n");
        
        ann_dict.close();
        
		return integerDictList;

		
	}
	

	private static List dict2Extract(String drugName, int startNumber, int endNumber, int index, String type) throws FileNotFoundException {
		

        List integerDictList = new ArrayList<>();
        
		
        String filename = "data/corpus/_dict_"+type+"_"+drugName+"_"+startNumber+"-"+endNumber+".txt";
        //String filename = "data/corpus/_dict_"+type+"_"+drugName+"_1-1000.txt";
		
        
        File f = new File(filename);
		if(!f.exists()) { 
			System.out.println("_dict_"+type+"_"+drugName+"_"+startNumber+"-"+endNumber+".txt is not exist!!!");
			return integerDictList;
		}
		
		Scanner ann_dict = new Scanner(new FileReader(filename));		
        int z = index;
		System.out.println("z="+z);
		int z_len=1;
		if(z>0){
			z_len = (int)(Math.log10(z)+1);			
		}
		
		while(ann_dict.hasNext()){
			String text = ann_dict.nextLine();
			if(!Character.isDigit(text.charAt(0))){
				continue;
			}else if(text.substring(0,z_len+1).equals(z+"|")){
				System.out.println("text="+text+"\n"+text.substring(0,4));
				String[] a = text.split("\\|");
				//integerDictList.add(Integer.parseInt(a[1]));
				int len=Integer.parseInt(a[1])+a[2].length();
				
				integerDictList.add(new ArrayList<Integer>(Arrays.asList(Integer.parseInt(a[1]),len)));
				
				
			}else{
				continue;
			}
		}				



        Collections.sort(integerDictList, new Comparator<ArrayList<Integer>>() {    
				@Override
				public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
					// TODO Auto-generated method stub
                    return o1.get(0).compareTo(o2.get(0));
				}               
        });
        System.out.println(type+"_Dict="+integerDictList+"\n");
        
        ann_dict.close();
        
		return integerDictList;

		
	}
	
	
	private static List annExtract(String drugName, int index, String keyWord) throws FileNotFoundException {
        int z = index;
        
		Scanner ann = new Scanner(new FileReader("data/corpus/"+drugName+"."+z+".ann"));
		
        List integerList = new ArrayList<>();

		while(ann.hasNext()){
			String text = ann.nextLine();
			
			int Disease_index = text.indexOf(keyWord+" ");
			if(Disease_index>0&&(text.substring(0,1)!="#")){
				String a[] = text.split(keyWord+" ");  
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
				//k++;
			}			
		}
        

        Collections.sort(integerList, new Comparator<ArrayList<Integer>>() {    
				@Override
				public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
					// TODO Auto-generated method stub
                    return o1.get(0).compareTo(o2.get(0));
				}               
        });
        System.out.println(keyWord+"="+integerList+"\n");
		ann.close();
		return integerList;
	}
	
	
	
	
	private static void scanFile(String drugName,int startNumber, int endNumber) throws FileNotFoundException, Exception {

		System.out.println("--- "+drugName+" START ---");
		
		for(int z=startNumber;z<=endNumber;z++){

			System.out.println("--- START from file: "+drugName+"-"+z+" ---");
			
			String inFileName = "data/corpus/"+drugName+"."+z+".txt";
			Scanner s = new Scanner(new FileReader(inFileName));
			Scanner s_number = new Scanner(new FileReader(inFileName));
			String outFileName="data/corpus/_crf_"+drugName+"_"+startNumber+"-"+endNumber+"_sent";
			
			String fileNameStr = drugName+"."+z;
			//int isTrainSet = 1;			
			if(Arrays.asList(test_set).contains(fileNameStr.toLowerCase())){
				//isTrainSet=0;
				outFileName=outFileName+"_test.txt";
			}else{
				//System.out.println(Arrays.asList(test_set).toString());
				//System.out.println(fileNameStr);
				outFileName=outFileName+"_train.txt";				
			}

			BufferedWriter out=new BufferedWriter(new FileWriter(outFileName,true));
	

			List<Integer> integerADR1DictList = dictExtract(drugName, startNumber, endNumber, z, "adr1");
			List<Integer> integerADR2DictList = dictExtract(drugName, startNumber, endNumber, z, "adr2");
			//List<Integer> integerADR3DictList = dictExtract(drugName, startNumber, endNumber, z, "adr3");
			List<Integer> integerSymptomDictList = dictExtract(drugName, startNumber, endNumber, z, "symptom");
			//List<Integer> integerDiseaseDictList = dictExtract(drugName, startNumber, endNumber, z, "disease");
			List integerDiseaseDictList = dict2Extract(drugName, startNumber, endNumber, z, "disease");
	        
		    List integerADRList = annExtract(drugName, z, "ADR");
		    List integerDiseaseList = annExtract(drugName, z, "Disease");
		    List integerDrugList = annExtract(drugName, z, "Drug");
		    //List integerFindingList = new ArrayList<>();
		    List integerSymptomList = annExtract(drugName, z, "Symptom");

		    

		    int totalSents = 0;
			while(s_number.hasNext()){
				String text = s_number.nextLine();
				if(text==null||text.isEmpty()){
					continue;
				}
				totalSents++;
			}			

			String src = readFile("data/corpus/"+drugName+"."+z+".txt", Charset.defaultCharset());	
			int si=0;
			int tokenLength=0;
			while(s.hasNext()){
				String text = s.nextLine();
				if(text==null||text.isEmpty()){
					continue;
				}
				//Document doc = new Document(text);
				//doc.preprocess();
				//Sentence count in review
				//int totalSents=;

				Sentence sent = new Sentence(text);
				sent.preprocess();
				/*for(int ss=0; ss<doc.size(); ss++) {

					Sentence sent_splitter = doc.get(ss);
					//Token lastToken = sent_splitter.get(doc.size()-1);
					if(sent_splitter.size()==1){
						totalSents-=1;
					}
				}*/
				
				/*Sentence lastSent = doc.get(totalSents-1);
				//Token lastToken = lastSent.get(lastSent.size()-1);
				if(lastSent.size()==1){
					totalSents-=1;
				}
				if(totalSents<=0){totalSents=1;}*/
				
				//for(int ss=0; ss<doc.size(); ss++) {
					//Sentence sent_splitter = doc.get(ss);
					System.out.println("Sentence # " + (si+1));
					System.out.println(sent.getSentence());	
					
					/*if(ss==(doc.size()-1)&&sent_splitter.size()==1){
						System.out.println("---Sentence only punctuation!!---");
						break;
					}*/
					
					int totalWordCount=0;
					for(Token token : sent){				
						if(!token.getStem().equals("")){
							totalWordCount++;
						}
						
					}


					for(Token token : sent){						
						int m = src.indexOf(token.getToken(),tokenLength);
						
						tokenLength=m+token.getToken().length();

						//Sentence counts in review
						System.out.print(totalSents+"|");
						//Sentence position
						System.out.print((si+1)+"|");
						//Normalized sentence position
						System.out.print(String.format("%.4f",(si+1.0)/totalSents)+"|");						
						//Word count in sentence						
						System.out.println(totalWordCount+"|");
						//token index
						System.out.print(m+"|");
						System.out.print(token.getToken() + "|");
						System.out.print(token.getStem() + "|");
						System.out.print(token.getLemma() + "|");
						String posTag = token.getPOS();	
						System.out.print(posTag + "|");
						System.out.print(token.isStopword()+ "|");			

						//Sentence count in review
						out.write(totalSents+"|");
						//Sentence position
						out.write((si+1)+"|");
						//Normalized sentence position
						out.write(String.format("%.4f",(si+1.0)/totalSents)+"|");						
						//Word count in sentence
						//out.write(sent_splitter.size()+"|");				
						out.write(totalWordCount+"|");
						out.write(m+"|");
						
						//remove space and replace "|" to adjust with crf++
						out.write(token.getToken().replaceAll("\\s+", "").replace("|", "]")+ "|");						

						//Stem, Lemma, POS, stopWord					
						if(token.getStem().equals("")){
							out.write("PUNCTUATION|PUNCTUATION|PUNCTUATION|PUNCTUATION|");						
						}else{
							out.write(token.getStem() + "|"+token.getLemma() + "|");
							if(posTag.length()>2){
								switch(posTag.substring(0, 2)){
									case "NN":
										out.write(posTag + "|NOUN|");
										break;
									case "JJ":
										out.write(posTag + "|ADJ|");
										break;
									case "PR":
										out.write(posTag + "|PRON|");
										break;
									case "RB":
										out.write(posTag + "|ADV|");
										break;
									case "VB":
										out.write(posTag + "|VERB|");
										break;
									case "WP":
										out.write(posTag + "|WH-PRON|");
										break;
									default:
										out.write(posTag + "|" + posTag + "|");
										break;										
								}								
							}else{
								out.write(posTag + "|" + posTag + "|");
							}							
						}
												
						//isNumber
						if(posTag.equals("CD")){
							out.write("1|");							
						}else{
							out.write("0|");
						}						
						
						//isStopword
						if(token.isStopword()){
							out.write("1|");							
						}else{
							out.write("0|");
						}						
				        	
						
			        	//System.out.println((integerList.get(p))[0]);
			        	//System.out.println((integerList.get(p))[1]);
						//IO,BIO,BEIO,    IEO
						if(integerADRList.size()>0){
					        for(int p=0;p<integerADRList.size();p++){
					        	//if(m<(integerList.get(p))[0]){
					        	if(m<((int)((ArrayList)integerADRList.get(p)).get(0))){
									System.out.print("O-ADR|O-ADR|O-ADR|");	
									out.write("O-ADR|O-ADR|O-ADR|");
									break;
					        	//}else if(m==(integerList.get(p))[0]){
					        	}else if(m==((int)((ArrayList)integerADRList.get(p)).get(0))){
									System.out.print("I-ADR|B-ADR|B-ADR|");
									out.write("I-ADR|B-ADR|B-ADR|");
									//indexFound+=1;
									break;
					        	//}else if((m>(integerList.get(p))[0])&&(m<(integerList.get(p))[1])){
					        	}else if(m>((int)((ArrayList)integerADRList.get(p)).get(0))&&(m<((int)((ArrayList)integerADRList.get(p)).get(1)))){
									//change Intermediate to B bcos useless to determinate the boundary of side-effect in unstructural text
					        		if(tokenLength<((int)((ArrayList)integerADRList.get(p)).get(1))){	
						        		System.out.print("I-ADR|I-ADR|I-ADR|");
										out.write("I-ADR|I-ADR|I-ADR|");
										break;	
					        		}else{
						        		System.out.print("I-ADR|I-ADR|E-ADR|");
										out.write("I-ADR|I-ADR|E-ADR|");
										break;	
					        		}
					        	//}else if(m>=(integerList.get(p))[1]){
					        	}else if(m>=((int)((ArrayList)integerADRList.get(p)).get(1))){
					        		continue;
					        	}
					        }
					        if(m>=((int)((ArrayList)integerADRList.get(integerADRList.size()-1)).get(1))){
								System.out.print("O-ADR|O-ADR|O-ADR|");	
								out.write("O-ADR|O-ADR|O-ADR|");      	
					        }		        
						}else{
							System.out.print("O-ADR|O-ADR|O-ADR|");	
							out.write("O-ADR|O-ADR|O-ADR|");
						}
						

						if(integerDiseaseList.size()>0){
					        for(int p=0;p<integerDiseaseList.size();p++){
					        	//if(m<(integerList.get(p))[0]){
					        	if(m<((int)((ArrayList)integerDiseaseList.get(p)).get(0))){
									System.out.print("O-DIZ|O-DIZ|O-DIZ|");	
									out.write("O-DIZ|O-DIZ|O-DIZ|");
									break;
					        	//}else if(m==(integerList.get(p))[0]){
					        	}else if(m==((int)((ArrayList)integerDiseaseList.get(p)).get(0))){
									System.out.print("I-DIZ|B-DIZ|B-DIZ|");
									out.write("I-DIZ|B-DIZ|B-DIZ|");
									//indexFound+=1;
									break;
					        	//}else if((m>(integerList.get(p))[0])&&(m<(integerList.get(p))[1])){
					        	}else if(m>((int)((ArrayList)integerDiseaseList.get(p)).get(0))&&(m<((int)((ArrayList)integerDiseaseList.get(p)).get(1)))){
									//change Intermediate to B bcos useless to determinate the boundary of side-effect in unstructural text
					        		if(tokenLength<((int)((ArrayList)integerDiseaseList.get(p)).get(1))){
						        		System.out.print("I-DIZ|I-DIZ|I-DIZ|");
										out.write("I-DIZ|I-DIZ|I-DIZ|");
										break;	
					        		}else{
						        		System.out.print("I-DIZ|I-DIZ|E-DIZ|");
										out.write("I-DIZ|I-DIZ|E-DIZ|");
										break;	
					        		}
					        	//}else if(m>=(integerList.get(p))[1]){
					        	}else if(m>=((int)((ArrayList)integerDiseaseList.get(p)).get(1))){
					        		continue;
					        	}
					        }
					        if(m>=((int)((ArrayList)integerDiseaseList.get(integerDiseaseList.size()-1)).get(1))){
								System.out.print("O-DIZ|O-DIZ|O-DIZ|");	
								out.write("O-DIZ|O-DIZ|O-DIZ|");      	
					        }		        
						}else{
							System.out.print("O-DIZ|O-DIZ|O-DIZ|");	
							out.write("O-DIZ|O-DIZ|O-DIZ|");
						}
						

						if(integerDrugList.size()>0){
					        for(int p=0;p<integerDrugList.size();p++){
					        	//if(m<(integerList.get(p))[0]){
					        	if(m<((int)((ArrayList)integerDrugList.get(p)).get(0))){
									System.out.print("O-DRUG|O-DRUG|O-DRUG|");	
									out.write("O-DRUG|O-DRUG|O-DRUG|");
									break;
					        	//}else if(m==(integerList.get(p))[0]){
					        	}else if(m==((int)((ArrayList)integerDrugList.get(p)).get(0))){
									System.out.print("I-DRUG|B-DRUG|B-DRUG|");
									out.write("I-DRUG|B-DRUG|B-DRUG|");
									//indexFound+=1;
									break;
					        	//}else if((m>(integerList.get(p))[0])&&(m<(integerList.get(p))[1])){
					        	}else if(m>((int)((ArrayList)integerDrugList.get(p)).get(0))&&(m<((int)((ArrayList)integerDrugList.get(p)).get(1)))){
									//change Intermediate to B bcos useless to determinate the boundary of side-effect in unstructural text
					        		if(tokenLength<((int)((ArrayList)integerDrugList.get(p)).get(1))){
						        		System.out.print("I-DRUG|I-DRUG|I-DRUG|");
										out.write("I-DRUG|I-DRUG|I-DRUG|");
										break;	
					        		}else{
						        		System.out.print("I-DRUG|I-DRUG|E-DRUG|");
										out.write("I-DRUG|I-DRUG|E-DRUG|");
										break;	
					        		}
					        	//}else if(m>=(integerList.get(p))[1]){
					        	}else if(m>=((int)((ArrayList)integerDrugList.get(p)).get(1))){
					        		continue;
					        	}
					        }
					        if(m>=((int)((ArrayList)integerDrugList.get(integerDrugList.size()-1)).get(1))){
								System.out.print("O-DRUG|O-DRUG|O-DRUG|");	
								out.write("O-DRUG|O-DRUG|O-DRUG|");      	
					        }		        
						}else{
							System.out.print("O-DRUG|O-DRUG|O-DRUG|");	
							out.write("O-DRUG|O-DRUG|O-DRUG|");
						}
						

						if(integerSymptomList.size()>0){
					        for(int p=0;p<integerSymptomList.size();p++){
					        	//if(m<(integerList.get(p))[0]){
					        	if(m<((int)((ArrayList)integerSymptomList.get(p)).get(0))){
									System.out.print("O-SYMP|O-SYMP|O-SYMP|");	
									out.write("O-SYMP|O-SYMP|O-SYMP|");
									break;
					        	//}else if(m==(integerList.get(p))[0]){
					        	}else if(m==((int)((ArrayList)integerSymptomList.get(p)).get(0))){
									System.out.print("I-SYMP|B-SYMP|B-SYMP|");
									out.write("I-SYMP|B-SYMP|B-SYMP|");
									//indexFound+=1;
									break;
					        	//}else if((m>(integerList.get(p))[0])&&(m<(integerList.get(p))[1])){
					        	}else if(m>((int)((ArrayList)integerSymptomList.get(p)).get(0))&&(m<((int)((ArrayList)integerSymptomList.get(p)).get(1)))){
									//change Intermediate to B bcos useless to determinate the boundary of side-effect in unstructural text
					        		if(tokenLength<((int)((ArrayList)integerSymptomList.get(p)).get(1))){
						        		System.out.print("I-SYMP|I-SYMP|I-SYMP|");
										out.write("I-SYMP|I-SYMP|I-SYMP|");
										break;	
					        		}else{
						        		System.out.print("I-SYMP|I-SYMP|E-SYMP|");
										out.write("I-SYMP|I-SYMP|E-SYMP|");
										break;	
					        		}
					        	//}else if(m>=(integerList.get(p))[1]){
					        	}else if(m>=((int)((ArrayList)integerSymptomList.get(p)).get(1))){
					        		continue;
					        	}
					        }
					        if(m>=((int)((ArrayList)integerSymptomList.get(integerSymptomList.size()-1)).get(1))){
								System.out.print("O-SYMP|O-SYMP|O-SYMP|");	
								out.write("O-SYMP|O-SYMP|O-SYMP|");      	
					        }		        
						}else{
							System.out.print("O-SYMP|O-SYMP|O-SYMP|");	
							out.write("O-SYMP|O-SYMP|O-SYMP|");
						}
						
						
						
						
						
						
						int writeFlag=0;
						if(integerADR1DictList.size()>0){
					        for(int dict_i : integerADR1DictList){
					        	if(m==dict_i){
									System.out.print("1|");		
									out.write("1|");
									writeFlag=1;
									break;
					        	}else{
					        		continue;
					        	}
					        }
					        if(writeFlag==0){
								System.out.print("0|");		
								out.write("0|");
					        }
						}else{
							System.out.print("0|");		
							out.write("0|");	
						}
						

						writeFlag=0;
						if(integerADR2DictList.size()>0){
					        for(int dict_i : integerADR2DictList){
					        	if(m==dict_i){
									System.out.print("1|");		
									out.write("1|");
									writeFlag=1;
									break;
					        	}else{
					        		continue;
					        	}
					        }
					        if(writeFlag==0){
								System.out.print("0|");		
								out.write("0|");
					        }
						}else{
							System.out.print("0|");		
							out.write("0|");
						}
						

						/*writeFlag=0;
						if(integerDiseaseDictList.size()>0){
					        for(int dict_i : integerDiseaseDictList){
					        	if(m==dict_i){
									System.out.print("1|");		
									out.write("1|");
									writeFlag=1;
									break;
					        	}else{
					        		continue;
					        	}
					        }
					        if(writeFlag==0){
								System.out.print("0|");		
								out.write("0|");
					        }
						}else{
							System.out.print("0|");		
							out.write("0|");
						}*/
						
						//integerDiseaseList=null;
						if(integerDiseaseDictList.size()>0){
					        for(int p=0;p<integerDiseaseDictList.size();p++){
					        	//if(m<(integerList.get(p))[0]){
					        	if(m<((int)((ArrayList)integerDiseaseDictList.get(p)).get(0))){
									System.out.print("0|");		
									out.write("0|");
									break;
					        	//}else if(m==(integerList.get(p))[0]){
					        	}else if(m==((int)((ArrayList)integerDiseaseDictList.get(p)).get(0))){
									System.out.print("1|");		
									out.write("1|");
									//indexFound+=1;
									break;
					        	//}else if((m>(integerList.get(p))[0])&&(m<(integerList.get(p))[1])){
					        	}else if(m>((int)((ArrayList)integerDiseaseDictList.get(p)).get(0))&&(m<((int)((ArrayList)integerDiseaseDictList.get(p)).get(1)))){
									//change Intermediate to B bcos useless to determinate the boundary of side-effect in unstructural text
					        		if(tokenLength<((int)((ArrayList)integerDiseaseDictList.get(p)).get(1))){
										System.out.print("1|");		
										out.write("1|");
										break;	
					        		}else{
										System.out.print("1|");		
										out.write("1|");
										break;	
					        		}
					        	//}else if(m>=(integerList.get(p))[1]){
					        	}else if(m>=((int)((ArrayList)integerDiseaseDictList.get(p)).get(1))){
					        		continue;
					        	}
					        }
					        if(m>=((int)((ArrayList)integerDiseaseDictList.get(integerDiseaseDictList.size()-1)).get(1))){
								System.out.print("0|");		
								out.write("0|"); 	
					        }		        
						}else{
							System.out.print("0|");		
							out.write("0|");
						}
						

						
						
						
						writeFlag=0;
						if(integerSymptomDictList.size()>0){
					        for(int dict_i : integerSymptomDictList){
					        	if(m==dict_i){
									System.out.print("1|");		
									out.write("1|");
									writeFlag=1;
									break;
					        	}else{
					        		continue;
					        	}
					        }
					        if(writeFlag==0){
								System.out.print("0|");		
								out.write("0|");;
					        }
						}else{
							System.out.print("0|");		
							out.write("0|");
						}
						

						System.out.print(drugName+"."+z+".txt|");	
						out.write(drugName+"."+z+".txt|");
						
						//************
						//dict3 if need
						//************
						/*writeFlag=0;
						if(integerADR3DictList.size()>0){
					        for(int dict_i : integerADR3DictList){
					        	if(m==dict_i){
									System.out.print("1|");		
									out.write("1|");
									writeFlag=1;
									break;
					        	}else{
					        		continue;
					        	}
					        }
					        if(writeFlag==0){
								System.out.print("0|");		
								out.write("0|");
					        }
						}else{
							System.out.print("0|");		
							out.write("0|");
						}*/
						
						
						System.out.println();
						out.newLine();
					}
					
				//}
				
				
	
				//System.out.println("Sentence # " + (i+1));
				//System.out.println(sent.getSentence());
				//System.out.println("---Token/Stem/Lemma/POS/isStopword/NER---");
				

				
				System.out.println();
				System.out.println();
				si+=1;
			}	
			out.newLine();
			s.close();
			out.close();

			
			System.out.println("Total Sentence-totalSents=:"+totalSents+", si="+si);
			System.out.println("-----End of File: "+drugName+"-"+z+" ---------");
		}
		System.out.println("--- "+drugName+" END ---");
	}

	private static String readFile(String path, Charset encoding) throws IOException {

		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
	}

}
