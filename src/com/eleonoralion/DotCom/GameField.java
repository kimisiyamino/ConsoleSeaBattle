package com.eleonoralion.DotCom;

import java.util.LinkedList;
import java.util.List;

public class GameField {

    List<Cell> cells;
    int width, height;

    public GameField(int width, int height){
        this.width = width;
        this.height = height;
        cells = new LinkedList<>();

        for(int i = 1; i <= height; i++){
            for(int j = 1; j <= width; j++){
                cells.add(new Cell((char)('@' + i), j));
            }
        }
    }


    public void printGameFieldCoords(){
        for(int i = 0; i < height * width; i++){
            System.out.print(cells.get(i) + " ");

            if(cells.get(i).getCoordNum() == width){
                System.out.print(System.lineSeparator());
            }
        }
    }

    public void printGameField(){
        for(int i = 0; i < height * width; i++){

            switch (cells.get(i).getState()){
                case empty: System.out.print('0'); break;
                case point: System.out.print('*'); break;
                case ship: System.out.print('S'); break;
            }
            
            if(cells.get(i).getCoordNum() == width){
                System.out.print(System.lineSeparator());
            }
        }
    }
}

