package com.eleonoralion.DotCom2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Основное поле
        int width = 5;
        int height = 5;
        int[][] enemyField = new int[height][width];
        /*int[][] enemyField =
                {
                        {0,0,0,0,0},
                        {0,0,0,0,0},
                        {0,0,0,0,0},
                        {0,0,0,0,0},
                        {0,0,0,0,0},
                };*/

        // Основной лист координат
        List<String> coords = new LinkedList<>();

        // Заполняем лист
        for (int i = 0; i < enemyField.length; i++) {
            for (int j = 0; j < enemyField[i].length; j++) {
                coords.add(i+" "+j);
            }
        }

        printField(enemyField);
        printAvailableCoords(coords);

        placementShips(coords, enemyField, height, width);

        printField(enemyField);
        printAvailableCoords(coords);
    }

    // Функция, расставляющая корабли
    public static void placementShips(List<String> coords, int[][] field, int height, int width){

        List<String> coordsCopy = new LinkedList<>();
        int[][] fieldCopy = new int[height][width];

        int shipLength = 2;

        // Количество кораблей
        for (int i = 0; i < 4; i++) {

            while (true) {

                // Если допустимые координаты закончились, а корабли еще остались,
                // то обновляем поля и начинаем алгоритм заново
                if(coordsCopy.size() <= 0){

                    coordsCopy = new LinkedList<>(coords);
                    fieldCopy = new int[height][width];
                    for (int j = 0; j < field.length; j++) {
                        System.arraycopy(field[j], 0, fieldCopy[j], 0, field[j].length);
                    }

                    i = 0;
                }

                // Получаем рандомную клетку из списка возможных координат
                String coord = coordsCopy.get(new Random().nextInt(coordsCopy.size()));
                int y = Integer.parseInt(coord.split(" ")[0]);
                int x = Integer.parseInt(coord.split(" ")[1]);

                // При наличии функции deleteUnusedCoordinates(List<String> coords, int[][] field, int y, int x);
                // мы автоматически проверям занятность клеток и вокруг клеток
                // Занята?
                /*if (enemyField[y][x] == 1) {
                    continue;
                }*/

                // Занято вокруг?
                /*if (!canBuild(enemyField, y, x)) {
                    continue;
                }*/

                // Длинна корабля больше 1 клетки?
                if (shipLength > 1) {

                }else {
                    fieldCopy[y][x] = 1;
                }

                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x);

                break;
            }


        }

        coords.clear();
        coords.addAll(coordsCopy);

        for (int i = 0; i < fieldCopy.length; i++) {
            System.arraycopy(fieldCopy[i], 0, field[i], 0, fieldCopy[i].length);
        }
    }

    // Функция, удаляющая из листа координат все координаты, где невозможно больше поставить корабль
    public static void deleteUnusedCoordinates(List<String> coords, int[][] field, int y, int x){
        List<String> deletedCoords = new ArrayList<>();

        for (int i = y - 1; i < y + 2; i++){
            for (int j = x - 1; j < x + 2; j++){
                if(i < 0 || j < 0 || j >= field[y].length || i >= field.length){
                    continue;
                }
                deletedCoords.add(i+" "+j);
            }
        }

        coords.removeAll(deletedCoords);
    }

    // Функция, проверяющая соседей вокруг единицы
    // true - если вокруг единицы все нули
    // false - если хотя бы один сосед является единицей
    // 0 0 0
    // 0 1 0
    // 0 0 0
    public static boolean canBuild(int[][] field, int y, int x){
        for (int i = y - 1; i < y + 2; i++){
            for (int j = x - 1; j < x + 2; j++){
                if(i < 0 || j < 0 || j >= field[y].length || i >= field.length || (i == y && j == x)){
                    continue;
                }
                if(field[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    // Функция, выводящая массив на экран
    public static void printField(int[][] field){
        for (int[] ints : field) {
            for (int anInt : ints) {
                System.out.print(String.format("%2d ", anInt));
            }
            System.out.println();
        }
    }

    // Функция, выводящая доступные координаты на экран
    public static void printAvailableCoords(List<String> coords){
        for (String coord : coords){
            System.out.print(coord + ", ");
        }
        System.out.println();
    }
}
