package com.example.max.sudokusolver.sudoku_game;

/**
 * Created by leonidtiskevic on 17.12.2017.
 * Модель эелмента пользователя
 * TODO: связать логику солвера с этой моделью
 */

public class Element {

    private Integer baseElement;

    private Integer userElement;

    private boolean blockedElement;

    private boolean isCheckedElement;

    public Element() {
    }

    public Element(Integer baseElement, Integer userElement, boolean blockedElement, boolean isCheckedElement) {
        this.baseElement = baseElement;
        this.userElement = userElement;
        this.blockedElement = blockedElement;
        this.isCheckedElement = isCheckedElement;
    }

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

    public boolean getIsCheckedElement() {
        return isCheckedElement;
    }

    public void setIsCheckedElement(boolean checkedElement) {
        isCheckedElement = checkedElement;
    }
}
