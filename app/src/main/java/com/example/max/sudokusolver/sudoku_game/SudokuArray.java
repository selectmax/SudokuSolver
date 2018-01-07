package com.example.max.sudokusolver.sudoku_game;

import java.util.ArrayList;
import java.util.List;

/**
 * singleton
 */

public class SudokuArray {

    private static SudokuArray sSudokuArray;

    private List<Element> mElements;

    public static SudokuArray getInstance(){
        if (sSudokuArray == null){
            sSudokuArray = new SudokuArray();
        }
        return sSudokuArray;
    }

    private SudokuArray(){
        mElements = new ArrayList<>(81);
        Element element = new Element(0, 0, false);
        for (int i = 0; i < 81; i++){
            mElements.add(i, element);
        }
    }

    public List<Element> getElements() {
        return mElements;
    }

    public void setElements(List<Element> elements) {
        mElements = elements;
    }

    public Element getByIndex(int index){
        return mElements.get(index);
    }

    public Integer[] getBaseElementMass(){
        Integer[] baseMass = new Integer[81];
        for (int i = 0; i < mElements.size(); i++){
            baseMass[i] = mElements.get(i).getBaseElement();
        }
        return baseMass;
    }

    public void setByIndexBaseElement(int index, int value){
       // mElements.get(index).setBaseElement(value);
        Element element = new Element(0, 0, false);
        element.setUserElement(mElements.get(index).getUserElement());
        element.setBlockedElement(mElements.get(index).getBlockedElement());
        element.setBaseElement(value);
        mElements.set(index, element);
    }

    public void setByIndexBlockElement(int index, boolean value){
        //mElements.get(index).setBlockedElement(value);
        Element element = new Element(0, 0, false);
        element.setBaseElement(mElements.get(index).getBaseElement());
        element.setUserElement(mElements.get(index).getUserElement());
        element.setBlockedElement(value);
        mElements.set(index, element);
    }

    public void setByIndexUserElement(int index, int value){
        //mElements.get(index).setUserElement(value);
        Element element = new Element(0, 0, false);
        element.setBaseElement(mElements.get(index).getBaseElement());
        element.setBlockedElement(mElements.get(index).getBlockedElement());
        element.setUserElement(value);
        mElements.set(index, element);
    }

    public int getByIndexBaseElement(int index){
        return mElements.get(index).getBaseElement();
    }

    public boolean getByIndexBlockElement(int index){
        return mElements.get(index).getBlockedElement();
    }

    public int getByIndexUserElement(int index){
        return mElements.get(index).getUserElement();
    }
}
