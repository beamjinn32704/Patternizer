/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindblown.patternizer;

/**
 *
 * @author beamj
 */
public class Sequence {
    
    /**
     * This array contains the elements of the sequence
     */
    private int[] array;
    
    /**
     * Create a Sequence object from an integer array where each integer
     * is an element of the sequence in the same order of the array
     * @param array the array that the sequence is based off of
     */
    public Sequence(int[] array) {
        this.array = array;
    }

    /**
     * Returns the element of the sequence corresponding to the index i.
     * @param i the index of the element to get
     * @return the element corresponding to i
     */
    public int get(int i){
        return array[i];
    }
    
    /**
     * Return the size of the sequence (how many elements are in sequence)
     * @return how many elements are in sequence
     */
    public int size(){
        return array.length;
    }
}
