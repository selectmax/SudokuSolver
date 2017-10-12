package com.example.max.sudokusolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText e11, e12, e13, e14, e15, e16, e17, e18, e19,
                     e21, e22, e23, e24, e25, e26, e27, e28, e29,
                     e31, e32, e33, e34, e35, e36, e37, e38, e39,
                     e41, e42, e43, e44, e45, e46, e47, e48, e49,
                     e51, e52, e53, e54, e55, e56, e57, e58, e59,
                     e61, e62, e63, e64, e65, e66, e67, e68, e69,
                     e71, e72, e73, e74, e75, e76, e77, e78, e79,
                     e81, e82, e83, e84, e85, e86, e87, e88, e89,
                     e91, e92, e93, e94, e95, e96, e97, e98, e99;
    private Byte[][] mass;
    private EditText[][] massEditText;
    private Button pushButton;
  //  private Algoritm algoritm;
    private Byte[][] sortMass;
    private Algoritm2 algoritm2;
    private Algorithm3 algorithm3;
    private int[] massOdn;
    private int z;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pushButton = (Button) findViewById(R.id.push_button);
     //   algoritm = new Algoritm();
        algoritm2 = new Algoritm2();
        algorithm3 = new Algorithm3();
        mass = new Byte[10][10];
        massOdn = new int[81];
        {
            massEditText = new EditText[10][10];
            massEditText[0][0] = null;
            massEditText[1][1] = (EditText) findViewById(R.id.id11);
            massEditText[1][2] = (EditText) findViewById(R.id.id12);
            massEditText[1][3] = (EditText) findViewById(R.id.id13);
            massEditText[1][4] = (EditText) findViewById(R.id.id14);
            massEditText[1][5] = (EditText) findViewById(R.id.id15);
            massEditText[1][6] = (EditText) findViewById(R.id.id16);
            massEditText[1][7] = (EditText) findViewById(R.id.id17);
            massEditText[1][8] = (EditText) findViewById(R.id.id18);
            massEditText[1][9] = (EditText) findViewById(R.id.id19);
            massEditText[2][1] = (EditText) findViewById(R.id.id21);
            massEditText[2][2] = (EditText) findViewById(R.id.id22);
            massEditText[2][3] = (EditText) findViewById(R.id.id23);
            massEditText[2][4] = (EditText) findViewById(R.id.id24);
            massEditText[2][5] = (EditText) findViewById(R.id.id25);
            massEditText[2][6] = (EditText) findViewById(R.id.id26);
            massEditText[2][7] = (EditText) findViewById(R.id.id27);
            massEditText[2][8] = (EditText) findViewById(R.id.id28);
            massEditText[2][9] = (EditText) findViewById(R.id.id29);
            massEditText[3][1] = (EditText) findViewById(R.id.id31);
            massEditText[3][2] = (EditText) findViewById(R.id.id32);
            massEditText[3][3] = (EditText) findViewById(R.id.id33);
            massEditText[3][4] = (EditText) findViewById(R.id.id34);
            massEditText[3][5] = (EditText) findViewById(R.id.id35);
            massEditText[3][6] = (EditText) findViewById(R.id.id36);
            massEditText[3][7] = (EditText) findViewById(R.id.id37);
            massEditText[3][8] = (EditText) findViewById(R.id.id38);
            massEditText[3][9] = (EditText) findViewById(R.id.id39);
            massEditText[4][1] = (EditText) findViewById(R.id.id41);
            massEditText[4][2] = (EditText) findViewById(R.id.id42);
            massEditText[4][3] = (EditText) findViewById(R.id.id43);
            massEditText[4][4] = (EditText) findViewById(R.id.id44);
            massEditText[4][5] = (EditText) findViewById(R.id.id45);
            massEditText[4][6] = (EditText) findViewById(R.id.id46);
            massEditText[4][7] = (EditText) findViewById(R.id.id47);
            massEditText[4][8] = (EditText) findViewById(R.id.id48);
            massEditText[4][9] = (EditText) findViewById(R.id.id49);
            massEditText[5][1] = (EditText) findViewById(R.id.id51);
            massEditText[5][2] = (EditText) findViewById(R.id.id52);
            massEditText[5][3] = (EditText) findViewById(R.id.id53);
            massEditText[5][4] = (EditText) findViewById(R.id.id54);
            massEditText[5][5] = (EditText) findViewById(R.id.id55);
            massEditText[5][6] = (EditText) findViewById(R.id.id56);
            massEditText[5][7] = (EditText) findViewById(R.id.id57);
            massEditText[5][8] = (EditText) findViewById(R.id.id58);
            massEditText[5][9] = (EditText) findViewById(R.id.id59);
            massEditText[6][1] = (EditText) findViewById(R.id.id61);
            massEditText[6][2] = (EditText) findViewById(R.id.id62);
            massEditText[6][3] = (EditText) findViewById(R.id.id63);
            massEditText[6][4] = (EditText) findViewById(R.id.id64);
            massEditText[6][5] = (EditText) findViewById(R.id.id65);
            massEditText[6][6] = (EditText) findViewById(R.id.id66);
            massEditText[6][7] = (EditText) findViewById(R.id.id67);
            massEditText[6][8] = (EditText) findViewById(R.id.id68);
            massEditText[6][9] = (EditText) findViewById(R.id.id69);
            massEditText[7][1] = (EditText) findViewById(R.id.id71);
            massEditText[7][2] = (EditText) findViewById(R.id.id72);
            massEditText[7][3] = (EditText) findViewById(R.id.id73);
            massEditText[7][4] = (EditText) findViewById(R.id.id74);
            massEditText[7][5] = (EditText) findViewById(R.id.id75);
            massEditText[7][6] = (EditText) findViewById(R.id.id76);
            massEditText[7][7] = (EditText) findViewById(R.id.id77);
            massEditText[7][8] = (EditText) findViewById(R.id.id78);
            massEditText[7][9] = (EditText) findViewById(R.id.id79);
            massEditText[8][1] = (EditText) findViewById(R.id.id81);
            massEditText[8][2] = (EditText) findViewById(R.id.id82);
            massEditText[8][3] = (EditText) findViewById(R.id.id83);
            massEditText[8][4] = (EditText) findViewById(R.id.id84);
            massEditText[8][5] = (EditText) findViewById(R.id.id85);
            massEditText[8][6] = (EditText) findViewById(R.id.id86);
            massEditText[8][7] = (EditText) findViewById(R.id.id87);
            massEditText[8][8] = (EditText) findViewById(R.id.id88);
            massEditText[8][9] = (EditText) findViewById(R.id.id89);
            massEditText[9][1] = (EditText) findViewById(R.id.id91);
            massEditText[9][2] = (EditText) findViewById(R.id.id92);
            massEditText[9][3] = (EditText) findViewById(R.id.id93);
            massEditText[9][4] = (EditText) findViewById(R.id.id94);
            massEditText[9][5] = (EditText) findViewById(R.id.id95);
            massEditText[9][6] = (EditText) findViewById(R.id.id96);
            massEditText[9][7] = (EditText) findViewById(R.id.id97);
            massEditText[9][8] = (EditText) findViewById(R.id.id98);
            massEditText[9][9] = (EditText) findViewById(R.id.id99);
        }
        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (byte i = 1; i <= 9; i++ ) {
                    for (byte j = 1; j <= 9; j++) {
                        if (!massEditText[i][j].getText().toString().equals("")) {
                            mass[i][j] = Byte.valueOf(massEditText[i][j].getText().toString());
                        } else mass[i][j] = 0;

                    }
                }

                //mass = algoritm.solveMass(mass);
                //mass = algoritm2.solveSudoku(mass);

                if (algorithm3.IsEnterValid(mass)) {
                    z = 0;
                    for (byte i = 1; i <= 9; i++) {
                        for (byte j = 1; j <= 9; j++) {
                            massOdn[z] = mass[i][j];
                            z++;
                        }
                    }

                    boolean issolved = algorithm3.solve(massOdn);
                    if (issolved) {
                        massOdn = algorithm3.getMassInt();
                        for (int i = 0; i < 81; i++) {
                            mass[i / 9 + 1][i % 9 + 1] = Byte.valueOf(String.valueOf(massOdn[i]));
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid input1", Toast.LENGTH_SHORT).show();
                    }

                    //вывод полученной матрицы на экран
                    for (byte i = 1; i <= 9; i++) {
                        for (byte j = 1; j <= 9; j++)
                            massEditText[i][j].setText(mass[i][j].toString());
                    }
                }
                else Toast.makeText(MainActivity.this, "Invalid input2", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
