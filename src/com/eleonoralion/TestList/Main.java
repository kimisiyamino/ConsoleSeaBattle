package com.eleonoralion.TestList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        addNums(nums);
        printNums(nums);
        deleteFirstNum(nums);
        printNums(nums);
        copyOtherList(nums);
        printNums(nums);
    }

    public static void addNums(List<Integer> nums){
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
    }

    public static void deleteFirstNum(List<Integer> nums){
        nums.remove(0);
    }

    public static void printNums(List<Integer> nums){
        for (int i : nums){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void copyOtherList(List<Integer> nums){
//        nums = new LinkedList<>();
//        nums.add(6);
//        nums.add(6);
//        nums.add(6);

        List<Integer> newList = new LinkedList<>();
        newList.add(6);
        newList.add(6);
        newList.add(6);

        nums.clear();
        nums.addAll(newList);

        printNums(nums);
    }
}
