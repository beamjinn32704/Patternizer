/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindblown.patternizer;

import com.mindblown.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author beamj
 */
public class Patternizer {

    /**
     * This function goes through the Sequence object given and returns an array
     * that contains the bounding integers of each group in the Sequence. A
     * group is defined as a series of consecutive elements whose individual
     * values are equal to or one more than the previous element in the group.
     * For example, the series 1, 2, 2, 3 is considered a group while 1, 2, 4, 3
     * is not. A single value 5 or 1 or 8923423 is also considered a group. If a
     * sequence's elements are {1, 3, 5, 6, 6, 7, 8, 0, 9}, then the returned
     * array will be: [[0], [1], [2, 6] [7], [8]] (the integer values are the
     * indexes of the groups. A single integer in a sub-array represents a group
     * containing a element (like 1 or 0 or 9) where the integer is the index of
     * that element while a sub-array containing two integers represents a group
     * with multiple elements (like 5, 6, 6, 7, 8) where the first integer in
     * the sub-array is the index of the first element in the group and the
     * second integer in the sub-array is the index of the last element in the
     * group.
     *
     * @param sequence the sequence whose elements will be used to find groups
     * @return an array containing the bounds (or index values) of the groups in
     * the sequence
     */
    public static int[][] findGroups(Sequence sequence) {
        
        //If the sequence only has one element, just return the first's index as a solo group
        if (sequence.size() == 1) {
            return new int[][]{{0}};
        }

        //The groups list contains the groups of elements
        ArrayList<Integer[]> groupsList = new ArrayList<>();
        boolean grouping = false;
        int startGroup = -1;
        int endGroup;

        //Only have i go to sequence.size() - 2 since in each iteration, sequence.get(i)
        //and sequence.get(i+1) will be checked
        for (int i = 0; i < sequence.size() - 1; i++) {
            //Get the current element and the next element
            int currentInt = sequence.get(i);
            int nextInt = sequence.get(i+1);

            //If we are still grouping
            if (grouping) {
                //If the next element is equal to or is one more than the current element
                if (currentInt + 1 == nextInt || currentInt == nextInt) {
                    //If the next element is the last element, that means the group is ended, so end the group.
                    //If not, then don't do anything - let the group continue!
                    if (i + 1 == sequence.size() - 1) {
                        endGroup = i + 1;
                        //Add to the groups list the array containing the index bounds of the group found
                        Integer[] boundsArray = {startGroup, endGroup};
                        groupsList.add(boundsArray);
                    }
                } else {
                    //If we are grouping but the group doesn't continue after this element, then add the group's bounds to
                    //the groups list and set grouping to false
                    endGroup = i;
                    Integer[] boundsArray = {startGroup, endGroup};
                    groupsList.add(boundsArray);
                    grouping = false;

                    //If the next element is the last element and isn't part of this element's group, then that last element will be alone,
                    //so just add the last element's index as a group as well.
                    if (i + 1 == sequence.size() - 1) {
                        groupsList.add(new Integer[]{i+1});
                    }
                }
            } else {
                //If there is no grouping currently happening but this element is the same as or is one less than the next integer,
                //then this element is the start of a group
                if (currentInt + 1 == nextInt || currentInt == nextInt) {
                    //Turn on grouping and set the group's starting index to i
                    grouping = true;
                    startGroup = i;
                    //If the next element is the last element in the list, then this element and the next element will be the only
                    //elements in the group, so just return this index and the last index as a group
                    if (i + 1 == sequence.size() - 1) {
                        endGroup = i + 1;
                        Integer[] boundsArray = new Integer[]{startGroup, endGroup};
                        groupsList.add(boundsArray);
                    }
                } else {
                    //If there is no grouping happening and this element isn't the start of a group, then add the element's index
                    //by itself to groups
                    groupsList.add(new Integer[]{i});

                    //If the next element is the last one, it won't be checked by this for loop,
                    //and since it isn't part of this element's group, it isn't part of any group,
                    //so add the last element's index by itself to the groups list
                    if (i + 1 == sequence.size() - 1) {
                        groupsList.add(new Integer[]{i+1});
                    }
                }
            }
        }
        
        //Create a new Integer tempGroup array from the groupsList.
        //Then iterate through the tempGroup array and turn it from an Integer double array
        //into an int double array. We turn the groups list to an Integer array since ArrayLists
        //can't have int values but must have Integer values.
        
        //In getting tempGroups, the "new Integer[][]{{1}}" simply creates an object that
        //ArrayList can use to make an array from. Without that object, I would get back an
        //Object[][] instead of an int[][]
        Integer[][] tempGroups = groupsList.toArray(new Integer[][]{{1}});
        int[][] groups = new int[tempGroups.length][];
        for(int i = 0; i < tempGroups.length; i++){
            //Get the individual group bounds array from tempGround and convert 
            //it to an int array. The store it in the groups array
            Integer[] groupBounds = tempGroups[i];
            groups[i] = ArrayUtil.toIntArray(groupBounds);
        }
        return groups;
    }
}
