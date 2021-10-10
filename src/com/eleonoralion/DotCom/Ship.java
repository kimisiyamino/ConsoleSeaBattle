package com.eleonoralion.DotCom;

import java.util.List;

public class Ship {
    boolean isAlive;
    int length;
    List<ShipElement> shipElements;

    public Ship(int length){
        this.length = length;
        isAlive = true;

    }
}
