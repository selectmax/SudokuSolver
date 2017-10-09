package com.example.max.sudokusolver;

import java.util.Arrays;

/**
 * Created by max on 03.10.2017.
 */

public class Algoritm {

    private Byte[][] byteMass;
    private StringBuffer[][] stringVariants;
    private Byte NumberOfChangedValues;
    private Byte u, minI, minJ, minLength; //Циклов прошло
    private Byte NumberOfFilledCells = 0;


    public Byte[][] solveMass(Byte[][] byteMass) {

        this.byteMass = byteMass;

        StringBuffer[][] stringVariants = new StringBuffer[10][10];

                for (byte i = 1; i <= 9; i++ ) {
                    for (byte j = 1; j <= 9; j++) {stringVariants[i][j] = new StringBuffer("123456789");
                            if (byteMass[i][j] != 0) NumberOfFilledCells++;
                        }
                    }
        System.out.println("Number of filled cells before start = " + NumberOfFilledCells);




//алгоритм 1
        NumberOfChangedValues = 1; //Чтобы цикл работал
        u = 1;
        while (NumberOfChangedValues != 0)
        {
            NumberOfChangedValues = 0;
            for (byte i = 1; i <= 9; i++) {
                for (byte j = 1; j <= 9; j++) //с каждым элементом таблицы
                {
                    if (byteMass[i][j] == 0) {
                        for (byte j2 = 1; j2 <= 9; j2++) {
                            if (byteMass[i][j2] > 0) {
                                if (stringVariants[i][j].indexOf(byteMass[i][j2].toString()) >= 0) {
                                    stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i][j2].toString()));
                                }
                            }
                        }
                        for (byte i2 = 1; i2 <= 9; i2++) {
                            if (byteMass[i2][j] > 0) {
                                if (stringVariants[i][j].indexOf(byteMass[i2][j].toString()) >= 0) {
                                    stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i2][j].toString()));
                                }
                            }
                        }
                        //определить в каком квадранте лежит byteMass[i][j] и все числа из этого квадранта удалить из stringVariants
                        if ((i <= 3) && (j <= 3)) {
                            for (byte i3 = 1; i3 <= 3; i3++) {
                                for (byte j3 = 1; j3 <= 3; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((j >= 4) && (j <= 6) && (i <= 3)) {
                            for (byte i3 = 1; i3 <= 3; i3++) {
                                for (byte j3 = 4; j3 <= 6; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((j >= 7) && (i <= 3)) {
                            for (byte i3 = 1; i3 <= 3; i3++) {
                                for (byte j3 = 7; j3 <= 9; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 4) && (i <= 6) && (j <= 3)) {
                            for (byte i3 = 4; i3 <= 6; i3++) {
                                for (byte j3 = 1; j3 <= 3; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) > 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 4) && (i <= 6) && (j <= 6) && (j >= 4)) {
                            for (byte i3 = 4; i3 <= 6; i3++) {
                                for (byte j3 = 4; j3 <= 6; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 4) && (i <= 6) && (j >= 7)) {
                            for (byte i3 = 4; i3 <= 6; i3++) {
                                for (byte j3 = 7; j3 <= 9; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 7) && (j <= 3)) {
                            for (byte i3 = 7; i3 <= 9; i3++) {
                                for (byte j3 = 1; j3 <= 3; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 7) && (j <= 6) && (j >= 4)) {
                            for (byte i3 = 7; i3 <= 9; i3++) {
                                for (byte j3 = 4; j3 <= 6; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        } else {
                            for (byte i3 = 7; i3 <= 9; i3++) {
                                for (byte j3 = 7; j3 <= 9; j3++) {
                                    if (byteMass[i3][j3] > 0) {
                                        if (stringVariants[i][j].indexOf(byteMass[i3][j3].toString()) >= 0) {
                                            stringVariants[i][j].deleteCharAt(stringVariants[i][j].indexOf(byteMass[i3][j3].toString()));
                                        }
                                    }
                                }
                            }
                        }
                        //System.out.println("Udalaetsya index" + );
                        System.out.println(stringVariants[i][j] + "   " + stringVariants[i][j].length());
                        if (stringVariants[i][j].length() == 0) break;
                        else if (stringVariants[i][j].length() == 1) {
                            byteMass[i][j] = Byte.parseByte(stringVariants[i][j].toString());
                            NumberOfChangedValues++;
                            NumberOfFilledCells++;
                        }
                    }

                }
            }
            System.out.println("ПРОШЕЛ ЦИКЛ АЛГОРИТМА 1 НОМЕР " + u);
            u++;

        }

        //алгоритм 2
        NumberOfChangedValues = 1; //Чтобы цикл работал
        u = 1;
        while (NumberOfChangedValues != 0)
        {
            NumberOfChangedValues = 0;
            for (byte i = 1; i <= 9; i++) {
                for (byte j = 1; j <= 9; j++) //с каждым элементом таблицы
                {
                    if (byteMass[i][j] != 0) {
                        for (byte j2 = 1; j2 <= 9; j2++) {
                            if (byteMass[i][j2] == 0) {
                                if (stringVariants[i][j2].indexOf(byteMass[i][j].toString()) >= 0) {
                                    stringVariants[i][j2].deleteCharAt(stringVariants[i][j2].indexOf(byteMass[i][j].toString()));
                                    System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                }
                            }
                        }
                        for (byte i2 = 1; i2 <= 9; i2++) {
                            if (byteMass[i2][j] == 0) {
                                if (stringVariants[i2][j].indexOf(byteMass[i][j].toString()) >= 0) {
                                    stringVariants[i2][j].deleteCharAt(stringVariants[i2][j].indexOf(byteMass[i][j].toString()));
                                    System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                }
                            }
                        }
                        //определить в каком квадранте лежит byteMass[i][j] и все числа из этого квадранта удалить из stringVariants
                        if ((i <= 3) && (j <= 3)) {
                            for (byte i3 = 1; i3 <= 3; i3++) {
                                for (byte j3 = 1; j3 <= 3; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        } else if ((j >= 4) && (j <= 6) && (i <= 3)) {
                            for (byte i3 = 1; i3 <= 3; i3++) {
                                for (byte j3 = 4; j3 <= 6; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        } else if ((j >= 7) && (i <= 3)) {
                            for (byte i3 = 1; i3 <= 3; i3++) {
                                for (byte j3 = 7; j3 <= 9; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 4) && (i <= 6) && (j <= 3)) {
                            for (byte i3 = 4; i3 <= 6; i3++) {
                                for (byte j3 = 1; j3 <= 3; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 4) && (i <= 6) && (j <= 6) && (j >= 4)) {
                            for (byte i3 = 4; i3 <= 6; i3++) {
                                for (byte j3 = 4; j3 <= 6; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 4) && (i <= 6) && (j >= 7)) {
                            for (byte i3 = 4; i3 <= 6; i3++) {
                                for (byte j3 = 7; j3 <= 9; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 7) && (j <= 3)) {
                            for (byte i3 = 7; i3 <= 9; i3++) {
                                for (byte j3 = 1; j3 <= 3; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                        }
                                    }
                                }
                            }
                        } else if ((i >= 7) && (j <= 6) && (j >= 4)) {
                            for (byte i3 = 7; i3 <= 9; i3++) {
                                for (byte j3 = 4; j3 <= 6; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        } else {
                            for (byte i3 = 7; i3 <= 9; i3++) {
                                for (byte j3 = 7; j3 <= 9; j3++) {
                                    if (byteMass[i3][j3] == 0) {
                                        if (stringVariants[i3][j3].indexOf(byteMass[i][j].toString()) >= 0) {
                                            stringVariants[i3][j3].deleteCharAt(stringVariants[i3][j3].indexOf(byteMass[i][j].toString()));
                                            System.out.println("Произведено удаление варианта по 2 алгоритму в ij" + i + j);
                                        }
                                    }
                                }
                            }
                        }
                        //System.out.println("Udalaetsya index" + );
                        //System.out.println(stringVariants[i][j] + "   " + stringVariants[i][j].length());
                        if (stringVariants[i][j].length() == 0) break;
                        else if (stringVariants[i][j].length() == 1 && byteMass[i][j]==0) {
                            byteMass[i][j] = Byte.parseByte(stringVariants[i][j].toString());
                            System.out.println("Произведена установка значения по 2 алгоритму в ij" + i + j);
                            NumberOfChangedValues++;
                            NumberOfFilledCells++;
                        }
                    }

                }
            }
            System.out.println("ПРОШЕЛ ЦИКЛ АЛГОРИТМА 2 НОМЕР" + u);
            u++;
        }







        Byte minI = 0;
        Byte minJ = 0;
        Byte minLength = 10;
        for (byte i = 1; i <= 9; i++) {
            for (byte j = 1; j <= 9; j++){
                if (stringVariants[i][j].length() < minLength) {
                    minI = i;
                    minJ = j;
                    minLength = (byte) stringVariants[i][j].length();
                }
            }
        }


//        for (byte i = 1; i<=; i++) {
//
//        }


                return byteMass;
            }

}
