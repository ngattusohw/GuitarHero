/*
 * I pledge my honor that I have abided by the steven's honor system
 *	Nick Gattuso
 * 
 * 11-28-2016
 */

package Homework3;

/**
 * The GuitarString class uses the RingBuffer class to simulate guitar strings
 * @author Nick Gattuso
 *
 */
public class GuitarString {

    private RingBuffer buffer; // ring buffer
    private int counter;

    /**
     * Initializes the GuitarString object with the size of 44100/frequency, and then initializes the array
     * @param frequency double
     */
    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
        int n = (int) (44100/frequency);
    	buffer = new RingBuffer((int) (44100/frequency));
        for(int x=0;x<n;x++){
        	buffer.enqueue(0.0);
        }
    }

    /**
     * Initializes the GuitarString object with the given array
     * @param init double[]
     */
    // create a guitar string whose size and initial values are given by the array
    public GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);
        for(int x=0;x<init.length;x++){
        	buffer.enqueue(init[x]);
        }
    }

    /**
     * replaces the buffer with random doubles between -0.5 to 0.5
     */
    // pluck the guitar string by setting the buffer to white noise
    public void pluck() {
    	while(!buffer.isEmpty()){
    		buffer.dequeue();
    	}
    	
        while(!buffer.isFull()){
	    	double N = Math.random();
	        if(N<0.5){
	        	N*=-1;
	        }else{
	        	N-=0.5;
	        }
	        buffer.enqueue(N);
        }
    }

    /**
     * deletes the first element, and then adds the average of the first and second element * 0.966 to the end of the array
     */
    // advance the simulation one time step
    public void tic() {
        double first = buffer.dequeue();
        double second = buffer.peek();
        double todo = (first + second)/2;
        buffer.enqueue(todo * 0.996);
        counter++;
    }

    /**
     * Returns the first element in the array
     * @return double
     */
    // return the current sample
    public double sample() {
        return buffer.peek();
    }

    /**
     * Returns the instance variable counter
     * @return
     */
    // return number of times tic was called
    public int time() {
        return counter;
    }
}
