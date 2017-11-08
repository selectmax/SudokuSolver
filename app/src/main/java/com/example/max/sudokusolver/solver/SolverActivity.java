package com.example.max.sudokusolver.solver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.max.sudokusolver.Algorithm;
import com.example.max.sudokusolver.R;

public class SolverActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView mGridView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button pushButton;
    private Algorithm mAlgorithm;
    private Integer[] massSolved; //массив который передается и возвращается алгоритмом
    public Integer selectedButton = 1;
    private AdapterSolver mAdapterSolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        getUIItems();
        setOnClickListener();
        initArray();

        mAlgorithm = new Algorithm();
        mAdapterSolver = new AdapterSolver(this, massSolved);

        mGridView.setNumColumns(9);
        mGridView.setEnabled(true);
        mGridView.setAdapter(mAdapterSolver);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapterSolver.setItem(position, selectedButton);
            }
        });

        pushButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                for (int i = 0; i < 81; i++) massSolved[i] = mAdapterSolver.getItem(i);
                if (mAlgorithm.IsEnterValid(massSolved)) {
                    mAlgorithm.solve(massSolved);
                    mAdapterSolver.setBaseMass(mAlgorithm.getMassInt());}
                else Toast.makeText(SolverActivity.this, "Invalid input1", Toast.LENGTH_SHORT).show();
            }
        });

        //Пасхальный тест он до сих пор нужен?)
        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Algorithm p = new Algorithm();
                for (int i=0; i <=80; i++) {
                    Log.i("MyTag", i + " " + String.valueOf(p.whereIsStartOfSquare(i)));
                }
                return false;
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

    private void setOnClickListener(){
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

    private void initArray(){
        massSolved = new Integer[81];
        for (int i = 0; i < massSolved.length; i ++) {
            massSolved[i] = 0;
        }
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ButtonClear: mAdapterSolver.cleanMassInt();
                return true;
        }
        return super.onOptionsItemSelected(item);
    } */
}
