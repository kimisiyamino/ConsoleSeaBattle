package com.eleonoralion.ConsoleSeaBattle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Ship {
    // Местоположение корабля
    private List<String> coords;
    // Точки вокруг корабля
    private Set<String> coordsAround;

    public Ship(){
        coords = new LinkedList<>();
        coordsAround = new HashSet<>();
    }

    // Удаляем из списка координат вокруг корабля, координаты местоположения
    public void fixCoords(){
        coordsAround.removeAll(coords);
    }

    public List<String> getCoords() {
        return coords;
    }

    public Set<String> getCoordsAround() {
        return coordsAround;
    }
}
