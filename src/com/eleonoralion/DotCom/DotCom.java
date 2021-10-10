package com.eleonoralion.DotCom;

import java.util.Random;

public class DotCom {

    public static DotCom dotCom;
    public static GameField gameField;

    public static void main(String[] args) {
        DotCom.dotCom = new DotCom();

     //   System.out.println((char) 64);

        DotCom.gameField = new GameField(10, 10);

        // Test
        gameField.getField()[0][1] = 1;
        gameField.getField()[1][2] = 1;


        gameField.printGameFieldAllCoords();
        //gameField.printGameField();





        Enemy enemy = new Enemy();
        //boolean result1 = enemy.checkNeighbours1(gameField.getField(), 9,9);
       // boolean result2 = enemy.checkNeighbours2(gameField.getField(), 9,9);
       // System.out.println(result1);
       // System.out.println(result2);
        enemy.createShips();

        gameField.printGameFieldArray();

        int[][] testArray =
        {
            {1,2,3,4}, {5,6,7,8}, {9,10,11,12}
        }; // = new int[3][4]; length = 3;


        //System.out.println(testArray.length);

        for(int x = 0; x < testArray.length; x++){
            for (int y = 0; y < testArray[x].length; y++) {

            }
        }
    }



}
