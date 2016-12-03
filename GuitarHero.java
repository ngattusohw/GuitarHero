/*
 * I pledge my honor that I have abided by the steven's honor system
 *	Nick Gattuso
 * 
 * 11-28-2016
 */

package Homework3;
import Homework3.StdDraw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import Homework3.StdAudio;

/**
 * The GuitarHero class sets up GuitarString and RingBuffer to work properly.
 * 	It establishes an array of GuitarStrings for 37 possible notes. 
 * @author Nick Gattuso
 *
 */
public class GuitarHero {
	public static void main(String[] args) {
		double hertz;
		int i;
		GuitarString string;
		double CONCERT_A = 440.0;
		double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0); 
		GuitarString stringA = new GuitarString(CONCERT_A); 
		GuitarString stringC = new GuitarString(CONCERT_C);
		String keyboard = "1234567890qwertyuiopasdfghjklzxcvbnm,";
		GuitarString[] theStrings = new GuitarString[keyboard.length()];
		
		if(args[0].equals("0")){
			while (true) {
				char key;
				if(StdDraw.hasNextKeyTyped()){
					key = StdDraw.nextKeyTyped();
					i = keyboard.indexOf(key);
					if(i==-1){
						continue;
					}
					if(theStrings[i]==null){
						hertz = 440.0 * Math.pow(2,(i-24)/12.0);
						theStrings[i] = new GuitarString(hertz);
					}
					theStrings[i].pluck();
				}	
				
				//compute the superposition of samples
				double sample=0;
				for(int x=0;x<theStrings.length;x++){
					if(theStrings[x]!=null)
						sample+=theStrings[x].sample();
				}
	
				// send the result to the sound card 
				StdAudio.play(sample);
				// advance the simulation of each guitar string by one step 
				for(int x=0;x<theStrings.length;x++){
					if(theStrings[x]!=null)
						theStrings[x].tic();
				}
			}
		}else if(args[0].equals("1")){
			
			FileReader file;
			try {
				file = new FileReader("src/Homework3/Final_Countdown.txt");
				Scanner scan = new Scanner(file);
				String it = "";
				while(scan.hasNext()){
					it+=scan.nextLine();
				}
				int time=0;
				int N=1;
				boolean doneOnce = false;
				boolean setBack = false;
				while(true){
					//System.out.println(time + " " + N + " " + it.substring(0,1));
					if(it.length()==0){
						break;
					}
					
					if(doneOnce && time==N){
						setBack = true;
						doneOnce = false;
					}
					
					if(time==N){
						//System.out.println("WHY ARE YOU GETTING CALLED " + N + " asdasD " + time );
						String hold = String.valueOf(it.charAt(0));
						switch(hold){
						case "@":
							int yy=2;
							int y=1;
							while(!(it.substring(y,yy).equals(" "))){
								y++;
								yy++;
							}
							System.out.println(it.substring(1,y));
							it = it.substring(y+1);
							//System.out.println(it);
							break;
						case "#":
							//System.out.println("HERE");
							int newNum;
							int x=1;
							while(true){
								if(!isNumeric(String.valueOf(it.charAt(x)))){
									newNum=Integer.parseInt(it.substring(1, x));
									break;
								}else{
									x++;
								}
							}
							N = 1;
							//System.out.println( " ////// " + N);
							it = it.substring(x);
							break;
						case ".":
							doneOnce = true;
							N=1;
							it = it.substring(1);
							break;
						case "/":
							it = it.substring(1);
							break;
						default:
							int z=0;
							while(!String.valueOf(it.charAt(z)).equals(" ")){
								z++;
							}
							String ntp = it.substring(0,z);
							if(ntp.length()==1){
								//System.out.println(ntp);
								i = keyboard.indexOf(ntp);
								if(i==-1){
									break;
								}else{
									if(theStrings[i]==null){
										hertz = 440.0 * Math.pow(2,(i-24)/12.0);
										theStrings[i] = new GuitarString(hertz);
									}
									theStrings[i].pluck();
									
									//compute the superposition of samples
									double sample=0;
									for(int xx=0;xx<theStrings.length;xx++){
										if(theStrings[xx]!=null)
											sample+=theStrings[xx].sample();
									}
						
									// send the result to the sound card 
									StdAudio.play(sample);
									
									//System.out.println("/////////////////////////////////// " + ntp);
									// advance the simulation of each guitar string by one step 
									for(int xx=0;xx<theStrings.length;xx++){
										if(theStrings[xx]!=null)
											theStrings[xx].tic();
									}
								}
									
							}else if(ntp.length()!=0){
								//System.out.println(ntp);
								for(int l=0;l<ntp.length();l++){
									//System.out.println(ntp.charAt(l) + " ntp.charAt(l)");
									i = keyboard.indexOf(ntp.charAt(l));
									if(i==-1){
										continue;
									}else{
										if(theStrings[i]==null){
											hertz = 440.0 * Math.pow(2,(i-24)/12.0);
											theStrings[i] = new GuitarString(hertz);
										}
										theStrings[i].pluck();
									}
								}
								
								//compute the superposition of samples
								double sample=0;
								for(int xx=0;xx<theStrings.length;xx++){
									if(theStrings[xx]!=null)
										sample+=theStrings[xx].sample();
								}
					
								// send the result to the sound card 
								//System.out.println("********************************");
								StdAudio.play(sample);
								// advance the simulation of each guitar string by one step 
								for(int xx=0;xx<theStrings.length;xx++){
									if(theStrings[xx]!=null)
										theStrings[xx].tic();
								}
							}
							if(ntp.length()==0){
								z++;
							}
							it = it.substring(z);
							
						}
						time=0;
						//System.out.println(" Here " + N  + " time:" + time);
						
						//N = 7199;
						//System.out.println(it);
						if(setBack){
							N = 7200;
							setBack = false;
						}
						double sample=0;
						for(int x=0;x<theStrings.length;x++){
							if(theStrings[x]!=null)
								sample+=theStrings[x].sample();
						}
			
						// send the result to the sound card 
						StdAudio.play(sample);
						
						for(int xx=0;xx<theStrings.length;xx++){
							if(theStrings[xx]!=null)
								theStrings[xx].tic();
						}
					}
					
					//System.out.println(" Not HEre " + N  + " time:" + time);
					
						time++;
						
						double sample=0;
						for(int x=0;x<theStrings.length;x++){
							if(theStrings[x]!=null)
								sample+=theStrings[x].sample();
						}
			
						// send the result to the sound card 
						StdAudio.play(sample);
						
						for(int xx=0;xx<theStrings.length;xx++){
							if(theStrings[xx]!=null)
								theStrings[xx].tic();
						}
					
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	} 
}