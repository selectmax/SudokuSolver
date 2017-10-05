package com.example.max.sudokusolver;

import java.util.Arrays;

/**
 * Created by max on 03.10.2017.
 */

public class Algoritm {

    private Byte[][] byteMass;
    private StringBuffer[][] stringVariants;
    private Byte chiselIzmeneno;
    private Byte u; //Циклов прошло


    public Byte[][] getSortMass() {

        StringBuffer[][] stringVariants = new StringBuffer[10][10];

                for (byte i = 1; i <= 9; i++ ) {
                    for (byte j = 1; j <= 9; j++) stringVariants[i][j] = new StringBuffer("321456789");
                    }
        chiselIzmeneno = 1;
        u = 1;
        while (chiselIzmeneno != 0)
        {
            chiselIzmeneno = 0;
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
                            chiselIzmeneno++;
                        }
                    }
                }
            }
            System.out.println("ПРОШЕЛ ЦИКЛ НОМЕР" + u);
            u++;
        }
                return byteMass;
            }





    public void setByteMass(Byte[][] byteMass) {
        this.byteMass = byteMass;
    }

}
