package com.example.max.sudokusolver.sudoku_game;

/**
 * Created by leonidtiskevic on 17.12.2017.
 */

public class Element {

    private Integer baseElement;

    private Integer userElement;

    private boolean blockedElement;

    public Integer getBaseElement() {
        return baseElement;
    }

    public void setBaseElement(Integer baseElement) {
        this.baseElement = baseElement;
    }

    public Integer getUserElement() {
        return userElement;
    }

    public void setUserElement(Integer userElement) {
        this.userElement = userElement;
    }

    public boolean getBlockedElement() {
        return blockedElement;
    }

    public void setBlockedElement(boolean blockedElement) {
        this.blockedElement = blockedElement;
    }
}
