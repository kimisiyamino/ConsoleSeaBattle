package com.eleonoralion.DotCom;

public class Cell {
    CellState state;

    char coordLetter;
    int coordNum;
    String coord;

    ShipElement shipElement;

    public Cell(char coordLetter, int coordNum){
        this.coordLetter = coordLetter;
        this.coordNum = coordNum;
        coord = coordLetter + "" + coordNum;

        state = CellState.empty;
    }

    public int getCoordX(){
        return coordLetter-'A';
    }

    public int getCoordY(){
        return coordNum-1;
    }


    @Override
    public String toString() {
        return coord;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public char getCoordLetter() {
        return coordLetter;
    }

    public void setCoordLetter(char coordLetter) {
        this.coordLetter = coordLetter;
    }

    public int getCoordNum() {
        return coordNum;
    }

    public void setCoordNum(int coordNum) {
        this.coordNum = coordNum;
    }
}
