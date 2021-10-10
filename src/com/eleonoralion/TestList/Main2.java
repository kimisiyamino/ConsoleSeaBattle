package com.eleonoralion.TestList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        new Main2().start();
    }

    public void start(){
        MainClass a = new A(10);
        System.out.println(a);
        changeClass(a);
        System.out.println(a);
    }

    public void changeClass(MainClass a){
        a = new A(100);
    }

    public void deleteFirstNum(List<Integer> nums){
        nums.remove(0);
    }

    public void printNums(List<Integer> nums){
        for (int i : nums){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void copyOtherList(List<Integer> nums){
        nums = new LinkedList<>();
        nums.add(6);
        nums.add(6);
        nums.add(6);

        printNums(nums);
    }
}

class MainClass{

}

class A extends MainClass{
    int value;

    public A(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "A{" +
                "value=" + value +
                '}';
    }
}

class B extends MainClass{
    int value;

    public B(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "B{" +
                "value=" + value +
                '}';
    }
}