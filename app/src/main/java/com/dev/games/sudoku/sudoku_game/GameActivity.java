package com.dev.games.sudoku.sudoku_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.max.sudoku.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView gameGridView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button mDeleteItemButton, mHelpButton, mBackwordBtn;
    private Game mGame;
    private int positionSelected = 0;
    private SudokuArray mSudokuArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Byte LevelOfDifficult = intent.getByteExtra("LevelOfDifficult", (byte) 1); //в LevelOfDifficult хранится уровень сложности 0, 1 или 2
        Byte[] LevelOfDiff = {LevelOfDifficult};
        boolean IsContinuation = intent.getBooleanExtra("Continue", false);
        setContentView(R.layout.activity_game);
        mGame = new Game(this);
        mSudokuArray = SudokuArray.getInstance(this);
        getUIItems();
        setOnClickListener();
        gameGridView.setNumColumns(9);
        gameGridView.setEnabled(true);
        gameGridView.setAdapter(mGame);

        if (IsContinuation) {
            mSudokuArray.loadDB();
        } else { //new game
           // mGame.initArray();
           // for (Byte LevelOfDiffa : LevelOfDiff) {
           //     mGame.initUserBaseMass(LevelOfDiffa);
           // }

            /**
             * TODO: те цифры какие ставит пользователь подсвечивать другим цветом, именно цифру
             */

            gameGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    positionSelected = position;
                    mSudokuArray.clearCheckedElement();
                    mGame.isCheckedElement(position);
                    mGame.notifyDataSetChanged();
                }
            });
        }

        mDeleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSudokuArray.getElements().get(positionSelected).getBlockedElement()) {
                    mSudokuArray.getElements().get(positionSelected).setUserElement(0);
                    mGame.notifyDataSetChanged();
                }
            }
        });

        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positionSelected == 0 || mSudokuArray.getElements().get(positionSelected).getUserElement() != 0){
                    Toast.makeText(GameActivity.this, R.string.text_for_hepl_toast, Toast.LENGTH_SHORT).show();
                    return;
                }
                mSudokuArray.getElements().get(positionSelected)
                        .setUserElement(mSudokuArray.getElements().get(positionSelected).getBaseElement());
                mGame.notifyDataSetChanged();
            }
        });

    }

    private void getUIItems() {
        gameGridView = (GridView) findViewById(R.id.game_field);
        mDeleteItemButton = (Button) findViewById(R.id.delete_item_btn);
        mHelpButton = (Button) findViewById(R.id.help_button);
        mBackwordBtn = (Button) findViewById(R.id.backward_button);
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

    private void setOnClickListener() {
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
        switch (view.getId()) {
            case R.id.game_btn1:
                mGame.setItem(positionSelected, 1);
                break;
            case R.id.game_btn2:
                mGame.setItem(positionSelected, 2);
                break;
            case R.id.game_btn3:
                mGame.setItem(positionSelected, 3);
                break;
            case R.id.game_btn4:
                mGame.setItem(positionSelected, 4);
                break;
            case R.id.game_btn5:
                mGame.setItem(positionSelected, 5);
                break;
            case R.id.game_btn6:
                mGame.setItem(positionSelected, 6);
                break;
            case R.id.game_btn7:
                mGame.setItem(positionSelected, 7);
                break;
            case R.id.game_btn8:
                mGame.setItem(positionSelected, 8);
                break;
            case R.id.game_btn9:
                mGame.setItem(positionSelected, 9);
                break;
        }
    }

    @Override
    protected void onStop() {
        mSudokuArray.saveDB();
        super.onStop();
    }
}