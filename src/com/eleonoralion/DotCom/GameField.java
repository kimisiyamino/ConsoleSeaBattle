package com.eleonoralion.DotCom;

import java.util.LinkedList;
import java.util.List;

public class GameField {

    private int width, height;
    private int[][] field;

    private List<Cell> cells;

    public GameField(int width, int height){
        this.width = width;
        this.height = height;
        field = new int[height][width];

        cells = new LinkedList<>();

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                cells.add(new Cell((char)('A' + i), j + 1));

                field[i][j] = 0;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }


    public void printGameFieldCoords(){
        for(int i = 0; i < height * width; i++){
            System.out.print(cells.get(i) + " ");

            if(cells.get(i).getCoordNum() == width){
                System.out.print(System.lineSeparator());
            }
        }
    }

    public void printGameFieldAllCoords(){
        for(int i = 0; i < height * width; i++){
            System.out.print(cells.get(i) + " = (" + cells.get(i).getCoordX() + ", " + cells.get(i).getCoordY() + ") ");

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

    public void printGameFieldArray(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}

