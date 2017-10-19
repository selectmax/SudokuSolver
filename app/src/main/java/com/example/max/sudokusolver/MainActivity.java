package com.example.max.sudokusolver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView mGridView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Byte[][] mass;
    private EditText[][] massEditText;
    private Button pushButton, clearButton;
    private Byte[][] sortMass;
    private Algorithm3 algorithm3;
    private int[] massOdn;
    private int z;
    public String selectedButton;
    private Game mGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        getUIItems();

        mGridView.setNumColumns(9);
        mGridView.setEnabled(true);
        mGridView.setAdapter(mGame);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Сделать поле выделенным

            // Ждать клика на одну из цифр btn1-btn9


            }
        });

        algorithm3 = new Algorithm3();
        mass = new Byte[10][10];
        massOdn = new int[81];


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

    private void getUIItems(){
        pushButton = (Button) findViewById(R.id.push_button);
        mGridView = (GridView) findViewById(R.id.field);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

    }

    public void setOnClickListener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    @Override
    public void onClick (View view){
        switch(view.getId()){
            case R.id.btn1: selectedButton = "n1";
                break;
            case R.id.btn2: selectedButton = "n2";
                break;
            case R.id.btn3: selectedButton = "n3";
                break;
            case R.id.btn4: selectedButton = "n4";
                break;
            case R.id.btn5: selectedButton = "n5";
                break;
            case R.id.btn6: selectedButton = "n6";
                break;
            case R.id.btn7: selectedButton = "n7";
                break;
            case R.id.btn8: selectedButton = "n8";
                break;
            case R.id.btn9: selectedButton = "n9";
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ButtonClear:
            {
                for (byte i = 1; i <= 9; i++ ) {
                    for (byte j = 1; j <= 9; j++) {
                        massEditText[i][j].setText("");
                    }}

            }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
