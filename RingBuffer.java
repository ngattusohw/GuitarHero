/*
 * I pledge my honor that I have abided by the steven's honor system
 *	Nick Gattuso
 * 
 * 11-28-2016
 */

package Homework3;

/**
 *  This class sets up an array of doubles, and it allows the user
 *  to add items, get the size, and so on. This class will be used with the GuitarString class
 *  
 * @author Nick Gattuso
 *
 */
public class RingBuffer {
    private int first;            // index of first item in buffer
    private int last;             // index of last item in buffer
    private int size=0;             // current number of items of buffer
    private double[] buffer;

    /**
     * Instantiates the array with length capacity
     * @param capacity int
     */
    // create an empty buffer, with given max capacity
    public RingBuffer(int capacity) {
        buffer = new double[capacity]; 
    }
    
    /**
     * Returns the size
     * @return int
     */
    // return number of items currently in the buffer
    public int getSize() {
    	return size;
    }

    /**
     * Checks to see if the array is empty
     * @return boolean
     */
    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        if(getSize()==0){
        	return true;
        }else{
        	return false;
        }
    }

    /**
     * Checks the size of the array to see if its at its capacity
     * @return boolean
     */
    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
        if(getSize()==buffer.length){
        	return true;
        }else{
        	return false;
        }
    }

    /**
     * Adds the value x to the last element in the array
     * @param x double
     */
    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) { throw new RuntimeException("Ring buffer overflow"); }
        buffer[last] = x;
        last++;
        if(last==buffer.length){
        	last=0;
        }
        size++;
    }

    /**
     * Deletes the first element in the array, and returns that value
     * @return double
     */
    // delete and return item from the front
    public double dequeue() {
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }
        double thereturn = buffer[first];
        buffer[first] = 0.0;
        first++;
        if(first==buffer.length){
        	first=0;
        }
        size--;
        return thereturn;
    }

    /**
     * Returns the first element in the array
     * @return double
     */
    // return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) { throw new RuntimeException("Ring buffer underflow"); }
        return buffer[first];
    }
}



