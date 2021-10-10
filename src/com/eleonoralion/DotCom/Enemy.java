package com.eleonoralion.DotCom;

import java.util.List;
import java.util.Random;

public class Enemy {

    List<Ship> ships;

    public void createShips(){

        int height = DotCom.gameField.getHeight();
        int width = DotCom.gameField.getWidth();

        int[][] tempArray = new int[height][width];


//        for (int i = 0; i < 10000; i++) {
//
//            // Получаем случайные координаты точки
//            int randX = new Random().nextInt(height);
//            int randY = new Random().nextInt(width);
//
//            Ship ship = new Ship(1);
//
//            // Если случайная точка свободна
//            if(tempArray[randX][randY] == 0){
//                if(checkNeighbours1(tempArray, randX, randY)){
//                    if(ship.length > 1){
//                        //3.
//                        //4.
//                        //5.
//                    }
//
//                    tempArray[randX][randY] = 7;
//                }
//            }else{
//
//            }
//        }
        int randX, randY;

        for (int i = 0; i < 10; i++) {
            do {
                randX = new Random().nextInt(height);
                randY = new Random().nextInt(width);

            } while (tempArray[randX][randY] != 0 && checkNeighbours2(tempArray, randX, randY));
            tempArray[randX][randY] = 7;
        }
        DotCom.gameField.setField(tempArray);
    }

    public boolean checkNeighbours1(int[][] arr, int x, int y){

        /*
        if(x - 1 < 0){
            if(y - 1 < 0){
                //                              (x, y + 1) , (x + 1, y + 1)     // 4
            }else if (y + 1 >= arr.length){
                // (x, y - 1) , (x + 1, y - 1)                                  // 5
            }else{
                // (x, y - 1) , (x + 1, y - 1), (x, y + 1) , (x + 1, y + 1)     // 8
            }

            // (x + 1, y)

        }else if (x + 1 >= arr[x].length){
            if(y - 1 < 0){
                // (x - 1, y + 1) , (x, y + 1)                                  // 5
            }else if (y + 1 >= arr.length){
                //                               (x - 1, y - 1) , (x, y - 1)    // 6
            }else{
                // (x - 1, y + 1) , (x, y + 1) , (x - 1, y - 1) , (x, y - 1)    // 9
            }

            // (x - 1, y)
        }else{
            if(y - 1 < 0){
                // (x - 1, y + 1) , (x, y + 1) , (x + 1, y + 1)                                                      // 7
            }else if (y + 1 >= arr.length){
                //                                               (x - 1, y - 1) , (x, y - 1), (x + 1, y - 1)         // 8
            }else{
                // (x - 1, y + 1) , (x, y + 1) , (x + 1, y + 1), (x - 1, y - 1) , (x, y - 1), (x - 1, y - 1)         // 12
            }

            // (x - 1, y), (x + 1, y)
        }
        */
        long startTime = System.currentTimeMillis();
        if(x - 1 < 0){
            if(y - 1 < 0){
                if (arr[x][y + 1] != 0 || arr[x + 1][y + 1] != 0){
                    return false;
                }
            }else if (y + 1 >= arr.length){
                if (arr[x][y - 1] != 0 || arr[x + 1][y - 1] != 0){
                    return false;
                }
            }else{
                if (arr[x][y - 1] != 0 || arr[x + 1][y - 1] != 0 || arr[x][y + 1] != 0 || arr[x + 1][y + 1] != 0){
                    return false;
                }
            }

            if (arr[x + 1][y] != 0){
                return false;
            }
        }else if (x + 1 >= arr[y].length){
            if(y - 1 < 0){
                if (arr[x - 1][y + 1] != 0 || arr[x][y + 1] != 0){
                    return false;
                }
            }else if (y + 1 >= arr.length){
                if (arr[x - 1][y - 1] != 0 || arr[x][y - 1] != 0){
                    return false;
                }
            }else{
                if (arr[x - 1][y + 1] != 0 || arr[x][y + 1] != 0 || arr[x - 1][y - 1] != 0 || arr[x][y - 1] != 0){
                    return false;
                }
            }

            if (arr[x - 1][y] != 0){
                return false;
            }
        }else{
            if(y - 1 < 0){
                if (arr[x - 1][y + 1] != 0 || arr[x][y + 1] != 0 || arr[x + 1][y + 1] != 0){
                    return false;
                }
            }else if (y + 1 >= arr.length){
                if (arr[x - 1][y - 1] != 0 || arr[x][y - 1] != 0 || arr[x + 1][y - 1] != 0){
                    return false;
                }
            }else{
                if (arr[x - 1][y + 1] != 0 || arr[x][y + 1] != 0 || arr[x + 1][y + 1] != 0 || arr[x - 1][y - 1] != 0 || arr[x][y - 1] != 0 || arr[x + 1][y - 1] != 0){
                    return false;
                }
            }

            if (arr[x - 1][y] != 0 || arr[x + 1][y] != 0){
                return false;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return true;
    }

    public boolean checkNeighbours2(int[][] arr, int y, int x) {

        long startTime = System.currentTimeMillis();

        for (int i = y - 1; i < y + 2; i++){
            for (int j = x - 1; j < x + 2; j++){
                if(i < 0 || j < 0 || j >= arr[y].length || i >= arr.length){
                    continue;
                }
                if(arr[i][j] != 0){
                    return false;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return true;
    }

}
