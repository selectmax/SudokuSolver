package com.example.max.sudokusolver.sudoku_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.max.sudokusolver.Algorithm;
import com.example.max.sudokusolver.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gameGridView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Game mGame;
    private Integer[] massTask;
    private int positionSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        byte LevelOfDifficult = intent.getByteExtra("LevelOfDifficult", (byte) 1 ); //в LevelOfDifficult хранится уровень сложности 0, 1 или 2
        setContentView(R.layout.activity_game);
        mGame = new Game(this);
        getUIItems();
        setOnClickListener();
        gameGridView.setNumColumns(9);
        gameGridView.setEnabled(true);
        gameGridView.setAdapter(mGame);
        mGame.initArray();


        gameGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
            }
        });

    }

    private void getUIItems(){
        gameGridView = (GridView) findViewById(R.id.game_field);
        btn1 = (Button) findViewById(R.id.game_btn1);
        btn2 = (Button) findViewById(R.id.game_btn2);
        btn3 = (Button) findViewById(R.id.game_btn3);
        btn4 = (Button) findViewById(R.id.game_btn4);
        btn5 = (Button) findViewById(R.id.game_btn5);
        btn6 = (Button) findViewById(R.id.game_btn6);
        btn7 = (Button) findViewById(R.id.game_btn7);
        btn8 = (Button) findViewById(R.id.game_btn8);
        btn9 = (Button) findViewById(R.id.game_btn9);
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
