package com.example.max.sudokusolver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.max.sudokusolver.solver.SolverActivity;
import com.example.max.sudokusolver.sudoku_game.GameActivity;

public class MenuActivity extends AppCompatActivity {

    private Button mGameBtn;
    private Button mSolveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mGameBtn = (Button) findViewById(R.id.game_btn);
        mGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, GameActivity.class);
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
