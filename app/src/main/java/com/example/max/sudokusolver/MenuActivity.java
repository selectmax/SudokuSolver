package com.example.max.sudokusolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.max.sudokusolver.solver.SolverActivity;
import com.example.max.sudokusolver.sudoku_game.GameActivity;

public class MenuActivity extends AppCompatActivity {

    private Button mGameBtn;
    private Button mSolveBtn;
    private Spinner spinner;
    private byte LevelOfDifficult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        String[] diff = {getString(R.string.difficulty_level_easy), getString(R.string.difficulty_level_normal), getString(R.string.difficulty_level_hard)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diff);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.difficulty_level_spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt(getString(R.string.difficulty_level));
        spinner.setSelection(1);
        LevelOfDifficult = 1;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LevelOfDifficult = (byte) position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mGameBtn = (Button) findViewById(R.id.game_btn);
        mGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                intent.putExtra("LevelOfDifficult", LevelOfDifficult);
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
}
