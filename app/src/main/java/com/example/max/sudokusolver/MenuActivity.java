package com.example.max.sudokusolver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.max.sudokusolver.solver.SolverActivity;
import com.example.max.sudokusolver.sudoku_game.Game;
import com.example.max.sudokusolver.sudoku_game.GameActivity;

public class MenuActivity extends AppCompatActivity {

    private Button mGameBtn, mContinueBtn;
    private Button mSolveBtn;
    private Spinner spinner;
    private byte levelOfDifficult;
    private ProgressBar mProgressBar;
    private Game mGame;
    private LayoutInflater mLayoutInflater;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String[] diff = {getString(R.string.difficulty_level_easy), getString(R.string.difficulty_level_normal), getString(R.string.difficulty_level_hard)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diff);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.difficulty_level_spinner);
        mProgressBar = (ProgressBar) findViewById(R.id.indicator);
        mProgressBar.setVisibility(View.INVISIBLE);
        spinner.setAdapter(adapter);
        spinner.setPrompt(getString(R.string.difficulty_level));
        spinner.setSelection(1);
        levelOfDifficult = 1;

        mLayoutInflater = LayoutInflater.from(this);
        mGame = new Game();
        mProgressDialog = new ProgressDialog(this, R.style.AsyncTheme);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelOfDifficult = (byte) position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGameBtn = (Button) findViewById(R.id.game_btn);
        mGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InitializeArray().execute();
               /* Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra("levelOfDifficult", levelOfDifficult);
                intent.putExtra("Continue", false);
                startActivity(intent); */
            }
        });

        mContinueBtn = (Button) findViewById(R.id.continue_btn);
        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra("Continue", true);
                startActivity(intent);
            }
        });


        mSolveBtn = (Button) findViewById(R.id.solve_btn);
        mSolveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SolverActivity.class);
                startActivity(intent);
            }
        });
    }

    public class InitializeArray extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            mGame.initArray();
            mGame.initUserBaseMass(levelOfDifficult);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.cancel();
            Toast.makeText(MenuActivity.this, "Задача завершена", Toast.LENGTH_LONG).show();
        }
    }
}
