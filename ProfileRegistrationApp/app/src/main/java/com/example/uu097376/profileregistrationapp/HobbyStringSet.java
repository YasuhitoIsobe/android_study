package com.example.uu097376.profileregistrationapp;

/**
 * Created by yosuke on 2016/12/25.
 */

public class HobbyStringSet {
    private static final String DIVIDER_STRING = ", ";

    public static StringBuilder hobbySet(boolean[] booleanArray,String[] stringArray){
        StringBuilder hobbyDispString = new StringBuilder();
        String[] hobbyArray = stringArray;
        boolean[] hobbyChecked = booleanArray;

        for(int i = 0;i < hobbyChecked.length;i++) {
            if (hobbyChecked[i] == true) {
                if (!("".equals(hobbyDispString.toString()))) {
                    hobbyDispString.append(DIVIDER_STRING);
                }
                hobbyDispString.append(hobbyArray[i]);
            }
        }

        System.out.print("hobbySet通過");
        return hobbyDispString;

    }
}
