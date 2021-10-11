package com.eleonoralion.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static List<Ship> enemyShips = new ArrayList<>();

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

        // Тест листа и координат
        /*for (Ship ship : enemyShips) {
            System.out.println("Ship " + ship.coords.size());
            System.out.print("coords: ");
            for (String coord : ship.coords){
                System.out.print(coord + ", ");
            }
            System.out.println();

            System.out.print("coordsAround: ");
            for (String coord : ship.coordsAround){
                System.out.print(coord + ", ");
            }
            System.out.println();
        }*/

        printEnemyField(enemyField);

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



        // Поле противника для пользователя
        int[][] myEnemyField = new int[height][width];

        // Лист координат для стрельбы
        List<String> myCoords = new LinkedList<>();

        // Заполняем лист
        for (int i = 0; i < myEnemyField.length; i++) {
            for (int j = 0; j < myEnemyField[i].length; j++) {
                myCoords.add(i+" "+j);
            }
        }

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                // Печатаем игровое поле
                printGame(myEnemyField);
                // Ваш ход: 0 0
                System.out.print("Your move: ");
                String input = bufferedReader.readLine();
                if(!myCoords.remove(input)){
                    System.out.println("Such a step has already been taken!");
                    continue;
                }
                int y = Integer.parseInt(input.split(" ")[0]);
                int x = Integer.parseInt(input.split(" ")[1]);
                boolean success = false;
                // Если попали
                if (enemyField[y][x] == 1) {
                    // Берем все корабли
                    for (Ship ship : enemyShips){
                        // Ищем нужный по координатам
                        for (String coord : ship.coords) {
                            //System.out.println("debug: " + coord + " " + input);
                            if(coord.equals(input)){
                                // Удаляем элемент корабля
                                ship.coords.remove(input);
                                // Если был последний элемент корабля
                                if(ship.coords.size() == 0){
                                    // Ставим точки вокруг мёртвого корабля и удаляем возможные координаты из нашего списка
                                    for (String deadCoord : ship.coordsAround){
                                        myCoords.remove(deadCoord);
                                        int deadY = Integer.parseInt(deadCoord.split(" ")[0]);
                                        int deadX = Integer.parseInt(deadCoord.split(" ")[1]);
                                        myEnemyField[deadY][deadX] = 1;
                                    }
                                    // Удаляем корабль из списка
                                    enemyShips.remove(ship);
                                    success = true;
                                    System.out.println("Enemy ship destroyed!");
                                    break;
                                }else{
                                    System.out.println("Enemy ship hit!");
                                    break;
                                }
                            }
                        }
                        if(success){ break; }
                    }
                    myEnemyField[y][x] = 2;
                } else {
                    System.out.println("Didn't hit");
                    myEnemyField[y][x] = 1;
                }

                if(enemyShips.size() == 0){
                    System.out.println("You win!");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

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

                                Ship ship = new Ship();

                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x, ship);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x - (shipLength - 1), ship);

                                // Ставим все части корабля
                                for (int _x = x; _x >= x - (shipLength - 1); _x--) {
                                    fieldCopy[y][_x] = 1;
                                    ship.coords.add(y+" "+_x);
                                }
                                ship.fixCoords();
                                enemyShips.add(ship);
                                break;
                            }
                        }

                        //Вправо
                        // Проверяем возможность постановки последней части корабля 1: существует ли вообще такая ячейка (не выходим ли за границы)
                        if (x + (shipLength - 1) < width) {
                            // Проверяем возможность постановки последней части корабля 2: свободно ли и свободно ли вокруг
                            if (canBuild(fieldCopy, y, x + (shipLength - 1))) {

                                Ship ship = new Ship();

                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x, ship);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x + (shipLength - 1), ship);


                                // Ставим все части корабля
                                for (int _x = x; _x <= x + (shipLength - 1); _x++) {
                                    fieldCopy[y][_x] = 1;
                                    ship.coords.add(y+" "+_x);
                                }
                                ship.fixCoords();
                                enemyShips.add(ship);

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

                                Ship ship = new Ship();

                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x, ship);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y - (shipLength - 1), x, ship);


                                // Ставим все части корабля
                                for (int _y = y; _y >= y - (shipLength - 1); _y--) {
                                    fieldCopy[_y][x] = 1;
                                    ship.coords.add(_y+" "+x);
                                }
                                ship.fixCoords();
                                enemyShips.add(ship);

                                break;
                            }
                        }
                        // Вниз
                        // Проверяем возможность постановки последней части корабля 1: существует ли вообще такая ячейка (не выходим ли за границы)
                        if (y + (shipLength - 1) < height) {
                            // Проверяем возможность постановки последней части корабля 2: свободно ли и свободно ли вокруг
                            if (canBuild(fieldCopy, y + (shipLength - 1), x)) {

                                Ship ship = new Ship();

                                // Удаляем координаты
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x, ship);
                                deleteUnusedCoordinates(coordsCopy, fieldCopy, y + (shipLength - 1), x, ship);


                                // Ставим все части корабля
                                for (int _y = y; _y <= y + (shipLength - 1); _y++) {
                                    fieldCopy[_y][x] = 1;
                                    ship.coords.add(_y+" "+x);
                                }
                                ship.fixCoords();
                                enemyShips.add(ship);

                                break;
                            }
                        }

                        // Если не одно из условий не выполнилось, мы не выходим из цикла while и повторяем процедуру
                    }

                // Если длинна корабля = 1
                }else {
                    fieldCopy[y][x] = 1;

                    // Добавляем корабль
                    Ship ship = new Ship();
                    ship.coords.add(y+" "+x);
                    ship.fixCoords();
                    enemyShips.add(ship);

                    deleteUnusedCoordinates(coordsCopy, fieldCopy, y, x, ship);
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
    public static void deleteUnusedCoordinates(List<String> coords, int[][] field, int y, int x, Ship ship){
        List<String> deletedCoords = new ArrayList<>();

        for (int i = y - 1; i < y + 2; i++){
            for (int j = x - 1; j < x + 2; j++){
                if(i < 0 || j < 0 || j >= field[y].length || i >= field.length){
                    continue;
                }
                deletedCoords.add(i+" "+j);
            }
        }

        ship.coordsAround.addAll(deletedCoords);
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

    // Функция, выводящая игровое поле противника
    public static void printEnemyField(int[][] field){
        for (int[] ints : field) {
            for (int anInt : ints) {
                switch (anInt){
                    case 0: System.out.print(String.format("%2s ", ".")); break;
                    case 1: System.out.print(String.format("%2s ", "\u25A0")); break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printGame(int[][] field){
        for (int[] ints : field) {
            for (int anInt : ints) {
                switch (anInt){
                    case 0: System.out.print(String.format("%2s ", ".")); break;
                    case 1: System.out.print(String.format("%2s ", "*")); break;
                    case 2: System.out.print(String.format("%2s ", "X")); break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}