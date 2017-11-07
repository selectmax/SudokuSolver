package com.example.max.sudokusolver;

import android.util.Log;

public class Algorithm {

    private Integer[] massInt;
    public boolean solve(Integer[] puzzle) {

        int N = (int) Math.round(Math.pow(puzzle.length, 0.25d)); // length ^ 0.25
        int SIZE = N * N;
        int CELLS = SIZE * SIZE;
        boolean noEmptyCells = true;
        int myRow = 0, myCol = 0;
        for (int i = 0; i < CELLS; i++) {
            if (puzzle[i] == 0) {
                myRow = i / SIZE;
                myCol = i % SIZE;
                noEmptyCells = false;
                break;
            }
        }
        if (noEmptyCells) {
            massInt = puzzle;
            return true;
        }

        for (int choice = 1; choice <= SIZE; choice++) {
            boolean isValid = true;
            int gridRow = myRow / N;
            int gridCol = myCol / N;
            for (int row = N * gridRow; row < N * gridRow + N; row++)
                for (int col = N * gridCol; col < N * gridCol + N; col++)
                    if (puzzle[row * SIZE + col] == choice)
                        isValid = false;
            for (int j = 0; j < SIZE; j++)
                if (puzzle[SIZE * j + myCol] == choice || puzzle[myRow * SIZE + j] == choice) {
                    isValid = false;
                    break;
                }


            if (isValid) {
                puzzle[myRow * SIZE + myCol] = choice;
                boolean solved = solve(puzzle);
                if (solved) {
                    massInt = puzzle;
                    return true;
                } else puzzle[myRow * SIZE + myCol] = 0;
            }
        }
        massInt = puzzle;
        return false;
    }

    public boolean IsEnterValid(Integer[] mass) {

        for (int i = 0; i < 81; i++) { //Для каждого элемента
            if (mass[i] == 0) continue; //Если это 0 - пропустить, ищем только цифры >= 1

            for (int j = ((i / 9) * 9); j <= ((i / 9) * 9) + 8; j++) { //Проходим по строке с элементом i
                if ((mass[j] == mass[i]) && (j != i))
                    return false; //Если элемент в строке равен анализируемому, причем это не один и тот же элемент (j!=i) - вернуть false
            }

            for (int j = (i % 9); j <= (i % 9) + 72; j = j + 9) { //Проходим по столбу с элементом i
                if ((mass[j] == mass[i]) && (j != i))
                    return false;//Если элемент в столбе равен анализируемому, причем это не один и тот же элемент (j!=i) - вернуть false
            }
            //Дальше хардкод идет, но по-другому не придумал - выкинем false, если в квадрате есть такой же элемент
            int startOfSquare = whereIsStartOfSquare(i);
            for (int j = 0; j <= 2; j++) {
                for (int j2 = 1; j2 <= 3; j2++) {
                    if ((mass[startOfSquare + j2 + j * 9].equals(mass[i])) && (i != (startOfSquare + j2 + j * 9)))
                        return false;
                }
            }
        }
        return true;
    }


    private int whereIsStartOfSquare(int start) {
        int startOfSquare = (start / 3) * 3;
        if (startOfSquare == 18 || startOfSquare == 9 || startOfSquare == 0) return 0;
        else if (startOfSquare == 3 || startOfSquare == 12 || startOfSquare == 21) return 3;
        else if (startOfSquare == 6 || startOfSquare == 15 || startOfSquare == 24) return 6;
        else if (startOfSquare == 27 || startOfSquare == 36 || startOfSquare == 45) return 27;
        else if (startOfSquare == 30 || startOfSquare == 39 || startOfSquare == 48) return 30;
        else if (startOfSquare == 33 || startOfSquare == 42 || startOfSquare == 51) return 33;
        else if (startOfSquare == 54 || startOfSquare == 63 || startOfSquare == 72) return 54;
        else if (startOfSquare == 57 || startOfSquare == 66 || startOfSquare == 75) return 57;
        else if (startOfSquare == 60 || startOfSquare == 69 || startOfSquare == 78) return 60;
        return 0;
    }


    public Integer[] getMassInt() {
        return massInt;
    }

    public void setMassInt(Integer[] massInt) {
        this.massInt = massInt;
    }


}
