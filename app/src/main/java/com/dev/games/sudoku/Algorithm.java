package com.dev.games.sudoku;

import android.content.Context;

import com.dev.games.sudoku.sudoku_game.SudokuArray;

import java.util.Random;

public class Algorithm {

    private Integer[] massInt;
    private SudokuArray mSudokuArray;

    public Algorithm(Context context){
        mSudokuArray = SudokuArray.getInstance(context);
    }

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
            mSudokuArray.setBaseElementMass(puzzle);
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
                    mSudokuArray.setBaseElementMass(puzzle);
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
                for (int j2 = 0; j2 <= 2; j2++) {
                    if ((mass[startOfSquare + j2 + (j * 9)].equals(mass[i])) && (i != (startOfSquare + j2 + (j * 9))))
                        return false;
                }
            }
        }
        return true;
    }

    public boolean IsElementValid(Integer[] mass, int i) {
        if (mass[i] == 0) return true;

        for (int j = ((i / 9) * 9); j <= ((i / 9) * 9) + 8; j++) {
            if ((mass[j] == mass[i]) && (j != i))
                return false;
        }

        for (int j = (i % 9); j <= (i % 9) + 72; j = j + 9) {
            if ((mass[j] == mass[i]) && (j != i))
                return false;
        }
        int startOfSquare = whereIsStartOfSquare(i);
        for (int j = 0; j <= 2; j++) {
            for (int j2 = 0; j2 <= 2; j2++) {
                if ((mass[startOfSquare + j2 + (j * 9)].equals(mass[i])) && (i != (startOfSquare + j2 + (j * 9))))
                    return false;
            }
        }
        return true;
    }


    public int whereIsStartOfSquare(int start) {
        int startOfSquare = (start / 3) * 3;
        start = startOfSquare / 9;
        if (start == 2 || start == 5 || start == 8) return (startOfSquare-18);
        else if (start == 1 || start == 4 || start == 7) return (startOfSquare-9);
        else return startOfSquare;
    }

    public void initArray() {
        final int COUNTER_FIRST_RANDOM_FILL = 10; //Показатель степени рандомности исходного поля. 0-80
        int filledCounter = 0;
        for (int i = 0; i < 81; i++){
            mSudokuArray.setByIndexBaseElement(i, 0);
        }

        while (filledCounter <= COUNTER_FIRST_RANDOM_FILL) {
            int randomField;
            int randomValue;
            Random random = new Random();
            randomField = random.nextInt(81);
            randomValue = (random.nextInt(9) + 1);
            if (mSudokuArray.getByIndexBaseElement(randomField) == 0) mSudokuArray.setByIndexBaseElement(randomField, randomValue);
            boolean a = this.IsElementValid(mSudokuArray.getBaseElementMass(), randomField);
            if (!a){
                mSudokuArray.setByIndexBaseElement(randomField, 0);
            } else filledCounter++;
        }
        if (!this.solve(mSudokuArray.getBaseElementMass())) {
            initArray();
        }
    }

    public void initUserBaseMass(Byte levelOfDifficult) {
        // lvl = levelOfDifficult;
        byte HowManyElementsNeedToOpen = 51;
        switch (levelOfDifficult) {
            case 0:
                HowManyElementsNeedToOpen = 51; //51
                break;
            case 1:
                HowManyElementsNeedToOpen = 36; //36
                break;
            case 2:
                HowManyElementsNeedToOpen = 31; //31
                break;
        }
        for (int i = 0; i < 81; i++) {
            mSudokuArray.setByIndexUserElement(i, 0);
            mSudokuArray.setByIndexBlockElement(i, false);
        }
        while (HowManyElementsNeedToOpen != 0) {
            int randomField;
            Random random = new Random();
            randomField = random.nextInt(81);
            if (mSudokuArray.getByIndexUserElement(randomField) == 0) {
                mSudokuArray.setByIndexUserElement(randomField, mSudokuArray.getByIndexBaseElement(randomField));
                mSudokuArray.setByIndexBlockElement(randomField, true);
                HowManyElementsNeedToOpen--;
            }
        }
    }

    public Integer[] getMassInt() {
        return massInt;
    }

    public void setMassInt(Integer[] massInt) {
        this.massInt = massInt;
    }


}
