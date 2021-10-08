package com.eleonoralion.DotCom;

public class DotCom {

    public static DotCom dotCom;
    public static GameField gameField;

    public static void main(String[] args) {
        DotCom.dotCom = new DotCom();

     //   System.out.println((char) 64);

        DotCom.gameField = new GameField(10, 10);
        gameField.printGameFieldCoords();
        gameField.printGameField();
    }



}
