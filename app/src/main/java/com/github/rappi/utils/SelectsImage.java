package com.github.rappi.utils;

import android.util.Log;

import java.util.Random;

/**
 * Created by Roger Patiño on 06/09/2016.
 */

public class SelectsImage {

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String selectImg() {
        int num = getRandomNumberInRange(1, 6);
        Log.d("SelectsImage", "selectImg: " + Integer.toString(num));
        switch (num) {
            case 1:
                return "http://goo.gl/wKHNkP";
            case 2:
                return "http://goo.gl/M2lvnh";
            case 3:
                return "http://goo.gl/6Vq5nK";
            case 4:
                return "http://goo.gl/zqngk7";
            case 5:
                return "http://goo.gl/XPSMzV";
            case 6:
                return "http://goo.gl/RBjCaa";
        }
        return "http://goo.gl/rwPD9n";
    }

}