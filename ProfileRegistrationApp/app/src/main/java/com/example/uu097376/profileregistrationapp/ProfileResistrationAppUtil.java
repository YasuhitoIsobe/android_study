package com.example.uu097376.profileregistrationapp;

/**
 * Created by YASUHITO on 2016/12/18.
 */
public class ProfileResistrationAppUtil {
    private static final String dividerString = "、";

    // 趣味項目の表示用文字列を生成するメソッド
    public static String getHobbyDispString(String[] hobbyArray, boolean[] hobbyCheckStateArray) {
        StringBuilder hobbyDispString = new StringBuilder();

        for (int i = 0; i < hobbyCheckStateArray.length; i++) {
            if (hobbyCheckStateArray[i] == true) {
                // 初回は区切り文字「、」を設定しない
                if (!("".equals(hobbyDispString.toString()))) {
                    hobbyDispString.append(dividerString);
                }

                hobbyDispString.append(hobbyArray[i]);
            }
        }

        return hobbyDispString.toString();
    }
}
