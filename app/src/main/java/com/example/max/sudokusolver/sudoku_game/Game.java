package com.example.max.sudokusolver.sudoku_game;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.max.sudokusolver.Algorithm;

import java.util.Objects;
import java.util.Random;

class Game extends BaseAdapter {

    private Integer[] baseMass = new Integer[81]; //Массив хранения актуальных игровых цифр
    public Integer[] userBaseMass = new Integer[81]; //Массив хранения пользовательской догадки
    private Boolean[] blockedElements = new Boolean[81]; //Массив хранения меток блокирования элементов. 1 - элемент задания, недоступен для редактирования, 0 - элемент доступный для изменения
    private Context mContext;
    private Algorithm mGameAlgorithm;
    private final int mRows = 9, mCols = 9;
    private String number = " ";
    public int HowManyTimesRunned = 0;


    public Game(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mRows * mCols;
    }

    @Override
    public Object getItem(int position) {
        return baseMass[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView;
        if (view == null) {
            textView = new TextView(mContext);
            textView.setPadding(12, 6, 6, 12);
            textView.setTextSize(22);
            textView.setTextScaleX((float) 1.4);
        } else {
            textView = (TextView) view;
        }

        if (userBaseMass[position] == 0) {
            number = " ";
        } else {
            number = String.valueOf(userBaseMass[position]);
        }

        textView.setText(number);

        return textView;
    }

    /**
     * При клике на клавиатуру из цифр вызывается setItem.
     * @param positionSelected - выбранная позиция в GridView - от 0 до 80
     * @param i - выбранная на клавиатуре цифра для установки
     *  Если число не заблокировано, то userBaseMass, отображаемый юзеру, изменяется. Причем если он верный - то все ок, а если
     *          не совпадает с загаданным элементом baseMass - он устанавливается, но должен быть подсвечен. Причем, скорее всего,
     *          вместе с элементами, которые имеют такое же значение. Тут же проверяется является ли судоку решенным - если да,
     *          то выдается поздравление!
     */
    public void setItem(int positionSelected, int i) {
        if (!blockedElements[positionSelected]) {
            userBaseMass[positionSelected] = i;
            Log.i("MyTag", "Число изменено");
            if (userBaseMass[positionSelected] != baseMass[positionSelected]) {
                //показывать или не показывать подсказку, что элемент неверный? Цветом, выделением?
                Log.i("MyTag", "Элемент не валидный");
            }
            if (sudokuIsSolved()) {
                //Выкинуть поздравление - судоку решено!
                Log.i("Congrats", "Судоку решено");
            }
        } else {
            Log.i("MyTag", "Попытка изменить число, но найден blockedElement == true");
        }
        notifyDataSetChanged();
    }

    private boolean sudokuIsSolved() {
        for (int i = 0; i < 81; i++) {
            if (!userBaseMass[i].equals(baseMass[i]))
                return false;
        }
        return true;
    }

    /**
     * Метод инициализирует массив baseMass рандомно, но стопудово решаемо. Время работы - не ограничено,
     * работает до тех пор пока не решится или не вылетит.
     * HowManyTimesRunned - просто считает сколько раз запущен сам метод.
     * Присутствует рекурсия, метод может быть вызван очень много раз.
     * Сначала массив заполняется нулями. Чтобы во время дальнейших проверок не было ошибок с null;
     * Далее пока FilledCounter (счетчик заполненных элементов) не сравнялся с заданным параметром COUNTER_FIRST_RANDOM_FILL
     * рандомно загадывается поле (от 0 до 80), рандомно загадывается значение (от 1 до 9),
     * далее если элемент baseMass с рандомно загаданным номером еще пока равен нулю - он заполняется загаданным числом randomValue;
     * Далее с помощью usElementValid проверяется валидно ли поставленное значение, если нет - оно стирается в 0 снова.
     *
     * Если заполняется COUNTER_FIRST_RANDOM_FILL полей, то цикл заканчивается и .solve решает это поле. Если решить не удается
     * - initArray вызывается снова, поле снова заполняется нулями и COUNTER_FIRST_RANDOM_FILL полей заполняется рандомно
     */
    public void initArray() {
        final int COUNTER_FIRST_RANDOM_FILL = 30; //Показатель степени рандомности исходного поля. 0-80
        int FilledCounter = 0;
        HowManyTimesRunned++;
        mGameAlgorithm = new Algorithm();
        for (int i = 0; i < baseMass.length; i++) {
            baseMass[i] = 0;
        }

        while (FilledCounter <= COUNTER_FIRST_RANDOM_FILL) {
            int randomField;
            int randomValue;
            Random random = new Random();
            randomField = random.nextInt(81);
            randomValue = (random.nextInt(9) + 1);
            if (baseMass[randomField] == 0) baseMass[randomField] = randomValue;
            if (!mGameAlgorithm.IsElementValid(baseMass, randomField)) {
                baseMass[randomField] = 0;
            } else FilledCounter++;
        }
        if (!mGameAlgorithm.solve(baseMass)) {
            initArray();
        }

        notifyDataSetChanged();
    }

    /**
     * @param levelOfDifficult - выбранная сложность 0, 1 или 2
     *   В switch сложность переводится в HowManyElementsNeedToOpen (сколько элементов нужно открыть)
     *   Все элементы userBaseMass из null переводятся в 0, блокировка снимается (все поля доступны к редактированию юзером)
     *   в while загадывается рандомное поле и проверяется, если это поле пока еще не заполнено - оно копируется из baseMass,
     *                        после чего устанавливается блокировка поля - пользователь его редактировать не может,
     *                         вычитается единица из кол-ва элементов для открытия - для работы цикла
     */
    public void initUserBaseMass(byte levelOfDifficult) {
        byte HowManyElementsNeedToOpen = 60;
        switch (levelOfDifficult) {
            case 0:
                HowManyElementsNeedToOpen = 70;
                break;
            case 1:
                HowManyElementsNeedToOpen = 60;
                break;
            case 2:
                HowManyElementsNeedToOpen = 50;
                break;
        }
        for (int i = 0; i < 81; i++) {
            userBaseMass[i] = 0;
            blockedElements[i] = false;
        }
        while (HowManyElementsNeedToOpen != 0) {
            int randomField;
            Random random = new Random();
            randomField = random.nextInt(81);
            if (userBaseMass[randomField] == 0) {
                userBaseMass[randomField] = baseMass[randomField];
                blockedElements[randomField] = true;
                HowManyElementsNeedToOpen--;
            }
        }
    }
}
