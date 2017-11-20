package com.example.max.sudokusolver.sudoku_game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.max.sudokusolver.Algorithm;
import com.example.max.sudokusolver.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView mGridView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Game mGame;
    private Integer[] massTask;
    private int positionSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mGame = new Game(this);
        getUIItems();
        setOnClickListener();
        mGridView.setNumColumns(9);
        mGridView.setEnabled(true);
        mGridView.setAdapter(mGame);
        mGame.initArray();


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
            }
        });

    }

    private void getUIItems(){
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
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn1:
                mGame.setItem(positionSelected, 1);
                break;
            case R.id.btn2:
                mGame.setItem(positionSelected, 2);
                break;
            case R.id.btn3:
                mGame.setItem(positionSelected, 3);
                break;
            case R.id.btn4:
                mGame.setItem(positionSelected, 4);
                break;
            case R.id.btn5:
                mGame.setItem(positionSelected, 5);
                break;
            case R.id.btn6:
                mGame.setItem(positionSelected, 6);
                break;
            case R.id.btn7:
                mGame.setItem(positionSelected, 7);
                break;
            case R.id.btn8:
                mGame.setItem(positionSelected, 8);
                break;
            case R.id.btn9:
                mGame.setItem(positionSelected, 9);
                break;
        }
    }
}
