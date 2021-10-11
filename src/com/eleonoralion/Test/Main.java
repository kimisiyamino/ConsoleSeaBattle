package com.eleonoralion.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Основное поле
        int width = 10;
        int height = 10;
        int[][] enemyField = new int[height][width];

        // Основной лист координат
        List<String> coords = new LinkedList<>();

        // Заполняем лист
        for (int i = 0; i < enemyField.length; i++) {
            for (int j = 0; j < enemyField[i].length; j++) {
                coords.add(i+" "+j);
            }
        }

        // Ставим корабли противнику
        placementShips(coords, enemyField, height, width, 4, 1);
        placementShips(coords, enemyField, height, width, 3, 2);
        placementShips(coords, enemyField, height, width, 2, 3);
        placementShips(coords, enemyField, height, width, 1, 4);


        //printAll(enemyField, coords);
        /*for (int i = 0; i < 10; i++) {
            placementShips(coords, enemyField, height, width, 4, 1);
            placementShips(coords, enemyField, height, width, 3, 2);
            placementShips(coords, enemyField, height, width, 2, 3);
            placementShips(coords, enemyField, height, width, 1, 4);

            System.out.println("Вариант " + (i + 1));
            printGame(enemyField);
            System.out.println();

            enemyField = new int[height][width];
            coords = new LinkedList<>();
            for (int l = 0; l < enemyField.length; l++) {
                for (int j = 0; j < enemyField[l].length; j++) {
                    coords.add(l+" "+j);
                }
            }
        }*/
        //printAll(enemyField, coords);
    }


    // Функция, расставляющая корабли
    // Алгоритм рассчитан только на 1-4ех палубные корабли
    // Так же алгоритм рассчитан в основном только на классические правила игры
    public static void placementShips(List<String> coords, int[][] field, int height, int width, int shipLength, int shipCount){

        // Создаем копии полей, для тестового расставления кораблей.
        // Если вариант расставления неудачный, то мы не "испортим" основные поля
        List<String> coordsCopy = new LinkedList<>();
        int[][] fieldCopy = new int[height][width];

        // Количество кораблей
        for (int i = 0; i < shipCount; i++) {

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
                // т.к. в List<String> coords у нас содержатся только свободные координаты

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

                    //Рандомное направление
                    int direction = new Random().nextInt(2);

                    // Если направление 0: Строить влево или вправо
                    if(direction == 0) {

                        // Влево
                        // Проверяем возможность постановки последней части корабля 1: существует ли вообще такая ячейка (не выходим ли за границы)
                        if (x - (shipLength - 1) >= 0) {
                            // Проверяем возможность постановки последней части корабля 2: свободно ли и свободно ли вокруг
                            if (canBuild(fieldCopy, y, x - (shipLength - 1))) {
                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x - (shipLength - 1));

                                // Ставим все части корабля
                                for (int _x = x; _x >= x - (shipLength - 1); _x--) {
                                    fieldCopy[y][_x] = 1;
                                }

                                break;
                            }
                        }

                        //Вправо
                        // Проверяем возможность постановки последней части корабля 1: существует ли вообще такая ячейка (не выходим ли за границы)
                        if (x + (shipLength - 1) < width) {
                            // Проверяем возможность постановки последней части корабля 2: свободно ли и свободно ли вокруг
                            if (canBuild(fieldCopy, y, x + (shipLength - 1))) {
                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x + (shipLength - 1));

                                // Ставим все части корабля
                                for (int _x = x; _x <= x + (shipLength - 1); _x++) {
                                    fieldCopy[y][_x] = 1;
                                }

                                break;
                            }
                        }
                        // Если направление 1: Строить вверх или вниз
                    }else {
                        //Вверх
                        // Проверяем возможность постановки последней части корабля 1: существует ли вообще такая ячейка (не выходим ли за границы)
                        if (y - (shipLength - 1) >= 0) {
                            // Проверяем возможность постановки последней части корабля 2: свободно ли и свободно ли вокруг
                            if (canBuild(fieldCopy, y - (shipLength - 1), x)) {
                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y - (shipLength - 1), x);

                                // Ставим все части корабля
                                for (int _y = y; _y >= y - (shipLength - 1); _y--) {
                                    fieldCopy[_y][x] = 1;
                                }

                                break;
                            }
                        }
                        // Вниз
                        // Проверяем возможность постановки последней части корабля 1: существует ли вообще такая ячейка (не выходим ли за границы)
                        if (y + (shipLength - 1) < height) {
                            // Проверяем возможность постановки последней части корабля 2: свободно ли и свободно ли вокруг
                            if (canBuild(fieldCopy, y + (shipLength - 1), x)) {
                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y + (shipLength - 1), x);

                                // Ставим все части корабля
                                for (int _y = y; _y <= y + (shipLength - 1); _y++) {
                                    fieldCopy[_y][x] = 1;
                                }

                                break;
                            }
                        }

                        // Если не одно из условий не выполнилось, мы не выходим из цикла while и повторяем процедуру
                    }

                // Если длинна корабля = 1
                }else {
                    fieldCopy[y][x] = 1;
                    deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x);
                    break;
                }
            }
        }

        // При успешном расставлении заносим данные в основные поля из тестовых
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
                if(field[i][j] == 1){
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

    // Функция, выводящая массив на экран + доступные координаты на экран
    public static void printAll(int[][] field, List<String> coords){
        printField(field);
        printAvailableCoords(coords);
    }

    // Функция, выводящая "картинку" игры
    public static void printGame(int[][] field){
        for (int[] ints : field) {
            for (int anInt : ints) {
                switch (anInt){
                    case 0: System.out.print(String.format("%2s ", ".")); break;
                    case 1: System.out.print(String.format("%2s ", "\u25A0")); break;
                }
            }
            System.out.println();
        }
    }
}