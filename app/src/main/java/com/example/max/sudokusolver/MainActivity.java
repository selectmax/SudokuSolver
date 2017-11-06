package com.example.max.sudokusolver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView mGridView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Integer[][] mass;
    private EditText[][] massEditText;
    private Button pushButton, clearButton;
    private Algorithm mAlgorithm;
    private Integer[] massSolved; //массив который передается и возвращается алгоритмом
    private int z;
    public Integer selectedButton = 1;
    private Game mGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        getUIItems();
        setOnClickListener();

        // пока Integer, но хочется перевести в String и убрать нули
        massSolved = new Integer[81];
        for (int i = 0; i < massSolved.length; i ++) {
            massSolved[i] = 0;
        }
        mGame = new Game(this, massSolved);

        mGridView.setNumColumns(9);
        mGridView.setEnabled(true);
        mGridView.setAdapter(mGame);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * Мне пока не нравится как работает, но работает так:
                 * сначала нажимаешь цифру которую хочешь вставить, потом нажимаешь поле и цифра вставляется
                 */
                mGame.setItem(position, selectedButton);
            }
        });

        mAlgorithm = new Algorithm();
        mass = new Integer[10][10];


        pushButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                for (int i = 0; i < 81; i++){
                    massSolved[i] = mGame.getItem(i);
                }

                /**
                 * Можешь прикинуть проверки эти? что-то я в них утонул
                 * после этого for в massSolved лежит массив считанный с экрана
                 * может и не надо его в матрицу превращать? а просто в методе проверки его в матрицу превратить
                 * если сильно нужно?
                 */

                /*

                for (byte i = 1; i <= 9; i++ ) {
                    for (byte j = 1; j <= 9; j++) {
                        if (!massEditText[i][j].getText().toString().equals("")) {
                            mass[i][j] = Integer.valueOf(massEditText[i][j].getText().toString());
                        } else mass[i][j] = 0;

                    }
                }

                if (mAlgorithm.IsEnterValid(mass)) {
                    z = 0;
                    for (byte i = 1; i <= 9; i++) {
                        for (byte j = 1; j <= 9; j++) {
                            massSolved[z] = mass[i][j];
                            z++;
                        }
                    }

                    boolean issolved = mAlgorithm.solve(massSolved);
                    if (issolved) {
                        massSolved = mAlgorithm.getMassInt();
                        for (int i = 0; i < 81; i++) {
                            mass[i / 9 + 1][i % 9 + 1] = Integer.valueOf(Byte.valueOf(String.valueOf(massSolved[i])));
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
                else Toast.makeText(MainActivity.this, "Invalid input2", Toast.LENGTH_SHORT).show(); */

                mAlgorithm.solve(massSolved);
                mGame.setBaseMass(mAlgorithm.getMassInt());
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
            case R.id.btn1: selectedButton = 1;
                break;
            case R.id.btn2: selectedButton = 2;
                break;
            case R.id.btn3: selectedButton = 3;
                break;
            case R.id.btn4: selectedButton = 4;
                break;
            case R.id.btn5: selectedButton = 5;
                break;
            case R.id.btn6: selectedButton = 6;
                break;
            case R.id.btn7: selectedButton = 7;
                break;
            case R.id.btn8: selectedButton = 8;
                break;
            case R.id.btn9: selectedButton = 9;
                break;

        }
    }

    /**
     *
     * @param menu
     * @return
     *
     * это задатки меню?)
     */

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
