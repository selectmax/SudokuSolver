package com.example.max.sudokusolver;

/**
 * Created by max on 11.10.2017.
 */

public class Algorithm3 {
    private int[] massInt;

    public boolean solve(int[] puzzle){

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
        if (noEmptyCells){
            massInt = puzzle;
            return true;
        }

        for (int choice = 1; choice <= SIZE; choice++) {
            boolean isValid = true;
            int gridRow = myRow / N;
            int gridCol = myCol / N;
            // check grid for duplicates
            for (int row = N * gridRow; row < N * gridRow + N; row++)
                for (int col = N * gridCol; col < N * gridCol + N; col++)
                    if (puzzle[row * SIZE + col] == choice)
                        isValid = false;

            // row & column
            for (int j = 0; j < SIZE; j++)
                if (puzzle[SIZE * j + myCol] == choice || puzzle[myRow * SIZE + j] == choice) {
                    isValid = false;
                    break;
                }


            if (isValid) {
                puzzle[myRow * SIZE + myCol] = choice;
                boolean solved = solve(puzzle);
                if (solved){
                    massInt = puzzle;
                    return true;}
                else puzzle[myRow * SIZE + myCol] = 0;
            }
        }
        massInt = puzzle;
        return false;
    }

    public boolean IsEnterValid (Byte[][] mass) {
        for (byte i = 1; i <= 9; i++ ) {
            for (byte j = 1; j <= 9; j++) {
                if (mass[i][j] != 0) {
                    for (byte j2 = 1; j2 <= 9; j2++) {
                        if (mass[i][j2] == mass[i][j] && j2 != j) return false;
                    }
                    for (byte i2 = 1; i2 <= 9; i2++) {
                        if (mass[i2][j] == mass[i][j] && i2 != i) return false;
                        }
                    if ((i <= 3) && (j <= 3)) {
                        for (byte i3 = 1; i3 <= 3; i3++) {
                            for (byte j3 = 1; j3 <= 3; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                            }
                        }
                    } else if ((j >= 4) && (j <= 6) && (i <= 3)) {
                        for (byte i3 = 1; i3 <= 3; i3++) {
                            for (byte j3 = 4; j3 <= 6; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                                }
                            }
                        }
                     else if ((j >= 7) && (i <= 3)) {
                        for (byte i3 = 1; i3 <= 3; i3++) {
                            for (byte j3 = 7; j3 <= 9; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                                }
                            }
                        }
                     else if ((i >= 4) && (i <= 6) && (j <= 3)) {
                        for (byte i3 = 4; i3 <= 6; i3++) {
                            for (byte j3 = 1; j3 <= 3; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                                }
                            }
                        }
                     else if ((i >= 4) && (i <= 6) && (j <= 6) && (j >= 4)) {
                        for (byte i3 = 4; i3 <= 6; i3++) {
                            for (byte j3 = 4; j3 <= 6; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                                }
                            }
                        }
                     else if ((i >= 4) && (i <= 6) && (j >= 7)) {
                        for (byte i3 = 4; i3 <= 6; i3++) {
                            for (byte j3 = 7; j3 <= 9; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                                }
                            }
                        }
                     else if ((i >= 7) && (j <= 3)) {
                        for (byte i3 = 7; i3 <= 9; i3++) {
                            for (byte j3 = 1; j3 <= 3; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                            }
                        }
                    } else if ((i >= 7) && (j <= 6) && (j >= 4)) {
                        for (byte i3 = 7; i3 <= 9; i3++) {
                            for (byte j3 = 4; j3 <= 6; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                            }
                        }
                    } else {
                        for (byte i3 = 7; i3 <= 9; i3++) {
                            for (byte j3 = 7; j3 <= 9; j3++) {
                                if (mass[i3][j3] == mass[i][j] && i3!=i && j3 != j) return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }


    public int[] getMassInt() {
        return massInt;
    }

    public void setMassInt(int[] massInt) {
        this.massInt = massInt;
    }
}
