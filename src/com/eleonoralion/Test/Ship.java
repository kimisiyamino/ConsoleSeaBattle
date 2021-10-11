package com.eleonoralion.Test;

import java.util.*;

public class Ship {
    int length;
    List<String> coords;
    Set<String> coordsAround;

    public Ship(){
        coords = new LinkedList<>();
        coordsAround = new HashSet<>();
    }

    public void fixCoords(){
        coordsAround.removeAll(coords);
    }

}
