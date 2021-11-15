package com.eleonoralion.ConsoleSeaBattle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Game {
    // Поле противника для пользователя
    private int width;
    private int height;
    private int[][] userEnemyField;
    // Лист координат для стрельбы
    private List<String> userCoords;

    // Печатать поле противника в начале игры
    private boolean printEnemyField;

    public Game(boolean printEnemyField){
        width = 10;
        height = 10;
        userEnemyField = new int[height][width];
        userCoords = new LinkedList<>();
        this.printEnemyField = printEnemyField;
    }

    public void start(){
        // Инициализация противника
        Enemy enemy = new Enemy();
        enemy.init(printEnemyField);

        // Заполняем лист
        for (int i = 0; i < userEnemyField.length; i++) {
            for (int j = 0; j < userEnemyField[i].length; j++) {
                userCoords.add(i+" "+j);
            }
        }

        // Пользовательский ввод
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                // Печатаем игровое поле
                printGame(userEnemyField);
                // Ваш ход: 0 0
                System.out.print("Your move (0 0): ");
                String input = bufferedReader.readLine();
                // Если уже стреляли в эту точку,
                // так же предотвращает некорректные данные
                if(!userCoords.remove(input)){
                    System.out.println("Such a step has already been taken or incorrect input!");
                    continue;
                }
                int y = Integer.parseInt(input.split(" ")[0]);
                int x = Integer.parseInt(input.split(" ")[1]);
                // Флажок для выхода из цикла, если всё удачно
                boolean success = false;
                // Если попали
                if (enemy.getEnemyField()[y][x] == 1) {
                    // Берем все корабли
                    for (Ship ship : enemy.getEnemyShips()){
                        // Ищем нужный по координатам
                        for (String coord : ship.getCoords()) {

                            if(coord.equals(input)){
                                // Удаляем элемент корабля
                                ship.getCoords().remove(input);
                                // Если был последний элемент корабля
                                if(ship.getCoords().size() == 0){
                                    // Ставим точки вокруг мёртвого корабля и удаляем возможные координаты из нашего списка
                                    for (String deadCoord : ship.getCoordsAround()){
                                        userCoords.remove(deadCoord);
                                        int deadY = Integer.parseInt(deadCoord.split(" ")[0]);
                                        int deadX = Integer.parseInt(deadCoord.split(" ")[1]);
                                        userEnemyField[deadY][deadX] = 1;
                                    }
                                    // Удаляем корабль из списка
                                    enemy.getEnemyShips().remove(ship);
                                    // Включаем флаг, чтобы выйти из цикла
                                    success = true;
                                    System.out.println("Enemy ship destroyed!");
                                    break;
                                }else{
                                    System.out.println("Enemy ship hit!");
                                    break;
                                }
                            }
                        }
                        // Выходим из цикла
                        if(success){ break; }
                    }
                    userEnemyField[y][x] = 2;
                } else {
                    System.out.println("Didn't hit");
                    userEnemyField[y][x] = 1;
                }
                // Если у противника не осталось кораблей
                if(enemy.getEnemyShips().size() == 0){
                    System.out.println("You win!");
                    break;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Функция, выводящая игровое поле
    public void printGame(int[][] field){
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


    public int[][] getUserEnemyField() {
        return userEnemyField;
    }
}