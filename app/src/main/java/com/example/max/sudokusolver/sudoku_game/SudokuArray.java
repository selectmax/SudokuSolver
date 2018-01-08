package com.example.max.sudokusolver.sudoku_game;

import java.util.ArrayList;
import java.util.List;

/**
 * singleton который хранит массив на протяжении всей работы приложения в единственном экземпляре
 * TODO: подумать как можно улучшить методы
 */

public class SudokuArray {

    private static SudokuArray sSudokuArray;

    private List<Element> mElements;

    /**
     * метод для получения объекта класса, через new нельзя объект получить
     * только через SudokuArray array = SudokuArray.getInstance();
     * @return экземпляр класса SudokuArray
     */
    public static SudokuArray getInstance(){
        if (sSudokuArray == null){
            sSudokuArray = new SudokuArray();
        }
        return sSudokuArray;
    }

    /**
     * конструкторе при первой инициализации собирается массив
     */
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

    /**
     *
     * @return базовый массив, достает из объекта Element из поля baseElement
     */
    public Integer[] getBaseElementMass(){
        Integer[] baseMass = new Integer[81];
        for (int i = 0; i < mElements.size(); i++){
            baseMass[i] = mElements.get(i).getBaseElement();
        }
        return baseMass;
    }

    /**
     *
     * @param mass - принимает массив который запишется в базовый, пока нужен для алгоритма
     *            TODO: надо подумать как улучшить
     */
    public void setBaseElementMass(Integer[] mass){
        for (int i = 0; i < mass.length; i++) {
            mElements.get(i).setBaseElement(mass[i]);
        }
    }


    /**
     * добавляет эелемент в базовый массив по индексу
     * @param index - индекс для вставки эелемента
     * @param value - значение которое вставляется в базовый массив
     * TODO: подумать как сделать проще и улучшить
     */
    public void setByIndexBaseElement(int index, int value){
       // mElements.get(index).setBaseElement(value);
        Element element = new Element(0, 0, false);
        element.setUserElement(mElements.get(index).getUserElement());
        element.setBlockedElement(mElements.get(index).getBlockedElement());
        element.setBaseElement(value);
        mElements.set(index, element);
    }

    /**
     * устанавливает заблокирован элемент или нет
     * @param index индекс для вставки эелемента
     * @param value значение которое вставляется в массив блокировок
     * TODO: подумать как сделать проще и улучшить
     */
    public void setByIndexBlockElement(int index, boolean value){
        //mElements.get(index).setBlockedElement(value);
        Element element = new Element(0, 0, false);
        element.setBaseElement(mElements.get(index).getBaseElement());
        element.setUserElement(mElements.get(index).getUserElement());
        element.setBlockedElement(value);
        mElements.set(index, element);
    }

    /**
     * добавляет эелемент в пользовательский массив по индексу
     * @param index - индекс для вставки эелемента
     * @param value - значение которое вставляется в пользовательский массив
     * TODO: подумать как сделать проще и улучшить
     */
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
