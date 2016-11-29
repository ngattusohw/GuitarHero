/*
 * I pledge my honor that I have abided by the steven's honor system
 *	Nick Gattuso
 * 
 * 11-28-2016
 */

package Homework3;
import Homework3.StdDraw;
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
	}
}