package com.dev.games.sudoku.sudoku_game;

/**
 * Created by leonidtiskevic on 03.02.2018.
 */

public class ElementForCache {

    private Integer index;

    private Integer value;

    public ElementForCache(Integer index, Integer value) {
        this.index = index;
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
