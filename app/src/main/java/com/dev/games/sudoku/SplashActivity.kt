package com.dev.games.sudoku

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.dev.games.sudoku.sudoku_game.SudokuArray

class SplashActivity : AppCompatActivity() {

    private var mSudokuArray: SudokuArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSudokuArray = SudokuArray.getInstance(this)
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

}
