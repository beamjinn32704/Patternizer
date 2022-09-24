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
public class Tester {
    public static void main(String[] args) {
        testPatternizerFindGroups();
    }
    
    /**
     * Test Patternizer's findGroups function.
     */
    public static void testPatternizerFindGroups(){
        //Create a sequence.
        Sequence seq = new Sequence(new int[]{0, 1, 2, 10, -1, 4, 5, 5, 2, 3, 0, 1, -1, -3, -4, -3, -2, -2, -1});
        //Find the groups in the sequence
        int[][] groups = Patternizer.findGroups(seq);
        //Print the results
        for(int i = 0; i < groups.length; i++){
            int[] group = groups[i];
            //Print the element the group corresponds to (since the arrays in groups contain the indicies of the groups)
            if(group.length == 1){
                System.out.println(seq.get(group[0]) + " - Index " + group[0]);
            } else {
                System.out.println(seq.get(group[0]) + " to " + seq.get(group[1]) + " - Indexes " + group[0] + " and " + group[1]);
            }
        }
    }
}
