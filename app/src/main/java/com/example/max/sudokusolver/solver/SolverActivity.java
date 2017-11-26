package com.example.max.sudokusolver.solver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.max.sudokusolver.Algorithm;
import com.example.max.sudokusolver.R;
import com.example.max.sudokusolver.solver.adapters.AdapterSolverFirst;
import com.example.max.sudokusolver.solver.adapters.AdapterSolverSecond;
import com.example.max.sudokusolver.solver.adapters.AdapterSolverThird;

public class SolverActivity extends AppCompatActivity implements View.OnClickListener {

    private final int mFaultSecond = 27;
    private final int mFaultThird = 54;

    private GridView mGridViewFirst;
    private GridView mGridViewSecond;
    private GridView mGridViewThird;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button pushButton;
    private Algorithm mAlgorithm;
    private Integer[] massSolved; //массив который передается и возвращается алгоритмом
    private AdapterSolverFirst mAdapterSolverFirst;
    private AdapterSolverSecond mAdapterSolverSecond;
    private AdapterSolverThird mAdapterSolverThird;
    private int positionSelected = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);
        getUIItems();
        setOnClickListener();
        initArray();

        mAlgorithm = new Algorithm();
        mAdapterSolverFirst = new AdapterSolverFirst(this, massSolved);
        mAdapterSolverSecond = new AdapterSolverSecond(this, massSolved);
        mAdapterSolverThird = new AdapterSolverThird(this, massSolved);

        initGridView();

        mGridViewFirst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
            }
        });

        mGridViewFirst.setOnTouchListener(new AdapterView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                }

                return false;
            }
        });

        mGridViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                positionSelected = position + mFaultSecond;
            }
        });

        mGridViewSecond.setOnTouchListener(new AdapterView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                }

                return false;
            }
        });

        mGridViewThird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                positionSelected = position + mFaultThird;
            }
        });

        mGridViewThird.setOnTouchListener(new AdapterView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true;
                }

                return false;
            }
        });

        pushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 27; i++) {
                    massSolved[i] = mAdapterSolverFirst.getItem(i);
                }
                for (int i = 27; i < 54; i++){
                    massSolved[i] = mAdapterSolverSecond.getItem(i - 27);
                }
                for (int i = 54; i < 81; i++){
                    massSolved[i] = mAdapterSolverThird.getItem(i - 54);
                }
                if (mAlgorithm.IsEnterValid(massSolved)) {
                    mAlgorithm.solve(massSolved);
                    for (int i = 0; i < 27; i++) {
                        mAdapterSolverFirst.setItem(i, massSolved[i]);
                    }
                    for (int i = 27; i < 54; i++){
                        mAdapterSolverSecond.setItem(i - 27, massSolved[i]);
                    }
                    for (int i = 54; i < 81; i++){
                        mAdapterSolverThird.setItem(i - 54, massSolved[i]);
                    }
                    mAdapterSolverFirst.setBaseMass(mAlgorithm.getMassInt());
                } else
                    Toast.makeText(SolverActivity.this, "Invalid input1", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUIItems() {
        pushButton = (Button) findViewById(R.id.push_button);
        mGridViewFirst = (GridView) findViewById(R.id.field_first_line);
        mGridViewSecond = (GridView) findViewById(R.id.field_seconf_line);
        mGridViewThird = (GridView) findViewById(R.id.field_third_line);
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

    private void initGridView(){
        mGridViewFirst.setNumColumns(9);
        mGridViewFirst.setEnabled(true);
        mGridViewFirst.setAdapter(mAdapterSolverFirst);

        mGridViewSecond.setNumColumns(9);
        mGridViewSecond.setEnabled(true);
        mGridViewSecond.setAdapter(mAdapterSolverSecond);

        mGridViewThird.setNumColumns(9);
        mGridViewThird.setEnabled(true);
        mGridViewThird.setAdapter(mAdapterSolverThird);
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
            case R.id.btn1:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 1);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 1);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 1);
                }
                break;
            case R.id.btn2:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 2);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 2);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 2);
                }
                break;
            case R.id.btn3:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 3);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 3);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 3);
                }
                break;
            case R.id.btn4:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 4);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 4);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 4);
                }
                break;
            case R.id.btn5:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 5);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 5);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 5);
                }
                break;
            case R.id.btn6:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 6);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 6);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 6);
                }
                break;
            case R.id.btn7:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 7);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 7);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 7);
                }
                break;
            case R.id.btn8:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 8);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 8);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 8);
                }
                break;
            case R.id.btn9:
                if (positionSelected < 27) {
                    mAdapterSolverFirst.setItem(positionSelected, 9);
                }
                if (positionSelected >= 27 && positionSelected < 54){
                    mAdapterSolverSecond.setItem(positionSelected - mFaultSecond, 9);
                }
                if (positionSelected >= 54 && positionSelected < 81){
                    mAdapterSolverThird.setItem(positionSelected - mFaultThird, 9);
                }
                break;
        }
    }

    private void initArray() {
        massSolved = new Integer[81];
        for (int i = 0; i < massSolved.length; i++) {
            massSolved[i] = 0;
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
                mAdapterSolverFirst.cleanMassInt();
                mAdapterSolverSecond.cleanMassInt();
                mAdapterSolverThird.cleanMassInt();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
