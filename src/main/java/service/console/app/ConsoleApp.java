package service.console.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        String[][] hideResultMap = new String[10 + 2][10 + 2];
        String[][] userMinerMap = new String[10][10];
        String[][] work = out(hideResultMap);

        int min = findCountMin(work);

        outConsole(work);
        System.out.println();
        getMines(userMinerMap);
        int x;
        int y;
        Scanner sc = new Scanner(System.in);
        boolean b;
        boolean c;
        do {
            do {
                System.out.println("Введите x координату число от 1 до 10");
                x = sc.nextInt() - 1;
            } while (x < 10 && x > 1);
            do {
                System.out.println("Введите y координату число от 1 до 10");
                y = sc.nextInt() - 1;
            } while (y < 10 && y > 1);

            b = getMove(y, x, userMinerMap, work);
            c = isFinish(userMinerMap, min);
            outConsole(userMinerMap);
        } while (b || c);


    }

    private static int findCountMin(String[][] arr) {
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j].equals("x")) {
                    c++;
                }
            }
        }
        return c;
    }

    private static boolean isFinish(String[][] arr, int min) {
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j].equals("o")) {
                    c++;
                }
            }
        }
        if (c + min == 100) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean getMove(int x, int y, String[][] userMinerMap, String[][] hideResultMap) {
        if (hideResultMap[x][y].equals("x")) {
            System.out.println("Вы проиграли!!!");
            userMinerMap[x][y] = hideResultMap[x][y];
            return false;
        }
        userMinerMap[x][y] = hideResultMap[x][y];
        return true;
    }

    private static void getMapMiner(String[][] array) {
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - 1; j++) {
                if (!array[i][j].equals("x"))
                    array[i][j] = isNearBomb(i, j, array);
            }
        }
    }

    private static String isNearBomb(int a, int b, String[][] array) {
        int c = 0;
        if (array[a + 1][b + 1].equals("x"))
            c++;
        if (array[a + 1][b].equals("x"))
            c++;
        if (array[a + 1][b - 1].equals("x"))
            c++;

        if (array[a][b + 1].equals("x"))
            c++;
        if (array[a][b - 1].equals("x"))
            c++;
        if (array[a - 1][b + 1].equals("x"))
            c++;
        if (array[a - 1][b].equals("x"))
            c++;
        if (array[a - 1][b - 1].equals("x"))
            c++;
        if (c == 0) return " ";
        return String.valueOf(c);
    }

    private static void getRandomMines(String[][] array) {
        Random random = new Random();
        int[] ox = new int[10];
        int[] oy = new int[10];
        for (int i = 0; i < 10; i++) {
            ox[i] = random.nextInt(10) + 1;
        }
        for (int i = 0; i < 10; i++) {
            oy[i] = random.nextInt(10) + 1;
        }

        for (int i = 0; i < 10; i++) {
            array[ox[i]][oy[i]] = "x";
        }
    }

    private static String[][] out(String[][] array) {
        getMines(array);
        getRandomMines(array);
        getMapMiner(array);
        String[][] result = new String[10][10];
        for (int i = 1; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - 1; j++) {
                result[i - 1][j - 1] = array[i][j];
            }
        }
        return result;
    }

    private static void outConsole(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void getMines(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = "o";
            }
        }
    }

}
//public class ConsoleApp {
//    public static void main(String[] args) {
//        //chessGame();
//        String[][] arr = new String[8][8];
//        fillChess(arr);
//        fillBoardStartPosition(arr);
//        int count_move = 0;
//        do {
//            count_move++;
//            findMoveForWhite(arr);
//            outChess(arr);
//            doMove(arr);
//            System.out.println();
//            findMoveForBlack(arr);
//            outChess(arr);
//            doMove(arr);
//            System.out.println();
//            if (count_move>2){
//                break;
//            }
//        } while (true);
//
//    }
//
//    private static void doMove(String[][] arr) {
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        String from = line.split("/")[0];
//        String to = line.split("/")[1];
//        move(arr, from, to);
//    }
//
//    private static void move(String[][] arr, String from, String to){
//        int x = Integer.parseInt(from.split("")[0])-1;
//        int y = Integer.parseInt(from.split("")[1])-1;
//        int x2 = Integer.parseInt(to.split("")[0])-1;
//        int y2 = Integer.parseInt(to.split("")[1])-1;
//        String temp = arr[x2][x2];
//        arr[x2][y2] = arr[x][y];
//        arr[x][y] = temp;
//    }
//
//    private static void findMoveForWhite(String[][] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = 1; j < arr.length; j++) {
//                if ((i + j) % 2 == 0 && (arr[i][j].equalsIgnoreCase("#")) && ((arr[i - 1][j - 1].equalsIgnoreCase(
//                        "c")))) {
//                    arr[i][j] = "x";
//                }
//            }
//        }
//    }
//
//    private static void findMoveForBlack(String[][] arr) {
//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1; j++) {
//                if ((i + j) % 2 == 0 && (arr[i][j].equalsIgnoreCase("#")) && ((arr[i + 1][j + 1].equalsIgnoreCase(
//                        "o")))) {
//                    arr[i][j] = "z";
//                }
//            }
//        }
//
//    }
//
//    private static void fillBoardStartPosition(String[][] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                if ((i == 0 || i == 1 || i == 2) & ((i + j) % 2 == 0)) {
//                    arr[i][j] = "c";
//                }
//                if ((i == 7 || i == 6 || i == 5) & ((i + j) % 2 == 0)) {
//                    arr[i][j] = "o";
//                }
//            }
//        }
//    }
//
//    private static void chessGame() {
//        //test();
//        //if ((Math.abs(i - a) == Math.abs(j - b) ) || (Math.abs(i - a) == 0 || Math.abs(j - b) == 0)) { ферзь
//        //if ((Math.abs(i - a) == 1 && Math.abs(j - b) == 1) || (Math.abs(i - a) == 1 && Math.abs(j - b) == 0) || (Math.abs(i - a) == 0 && Math.abs(j - b) == 1)) { король
//        //if (Math.abs(i - a) == 0 || Math.abs(j - b) == 0) { //ладья
//        //if (((Math.abs(i - a) == (Math.abs(j - b))))) { //слон
//        //if (Math.abs(i - a) == 2 && Math.abs(j - b) == 1 || Math.abs(i - a) == 1 && Math.abs(j - b) == 2) //конь
//        String[][] arr = new String[8][8];
//        fillChess(arr);
//
//        int a = 2;
//        int b = 1;
//
//        String[][] test = new String[5][5];
//        fillChess1(test, a, b);
//
//        for (int i = 0; i < arr.length; i++)
//            for (int j = 0; j < arr.length; j++)
//                if ((Math.abs(i - a) == Math.abs(j - b)) || (Math.abs(i - a) == 0 || Math.abs(j - b) == 0)) {
//                    if (arr[i][j] != "K")
//                        arr[i][j] = "0";
//                }
//        arr[a][b] = "K";
//
//        a = 5;
//        b = 5;
//        for (int i = 0; i < arr.length; i++)
//            for (int j = 0; j < arr.length; j++)
//                if ((Math.abs(i - a) == Math.abs(j - b)) || (Math.abs(i - a) == 0 || Math.abs(j - b) == 0)) {
//                    if (arr[i][j] != "K")
//                        arr[i][j] = "0";
//                }
//
//        arr[a][b] = "K";
//
//        //outChess(test);
//        outChess(arr);
//    }
//
//    private static void fillChess(String[][] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                arr[i][j] = "#";
//            }
//        }
//    }
//
//    private static void fillChess1(String[][] arr, int a, int b) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                arr[i][j] = String.valueOf((i - a - arr.length / 2) + (j - b - arr.length / 2));
//            }
//        }
//    }
//
//    private static void outChess(String[][] arr) {
//        for (String[] strings : arr) {
//            for (int j = 0; j < arr.length; j++) {
//                System.out.print(strings[j] + "  ");
//            }
//            System.out.println();
//        }
//    }
//
//    private static void test() {
//        int[] array0 = new int[3];
//        int[] array1 = new int[3];
//        if (isOne(array0) & isTwo(array1)) {
//            System.out.println("Как заполнены массивы?");
//            System.out.println(Arrays.toString(array0));
//            System.out.println(Arrays.toString(array1));
//        } else {
//            System.out.println(Arrays.toString(array0));
//            System.out.println(Arrays.toString(array1));
//        }
//    }
//
//    private static boolean isTwo(int[] array) {
//        for (int i = 0; i < array.length; i++) {
//            array[i] = i + 10;
//        }
//        return true;
//    }
//
//    private static boolean isOne(int[] array) {
//        for (int i = 0; i < array.length; i++) {
//            array[i] = (i + 1) * 2;
//        }
//        return false;
//    }
//}
