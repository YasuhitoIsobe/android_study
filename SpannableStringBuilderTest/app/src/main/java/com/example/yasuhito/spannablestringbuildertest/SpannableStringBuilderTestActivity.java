package com.example.yasuhito.spannablestringbuildertest;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class SpannableStringBuilderTestActivity extends Activity {

    private static final String fairEventNm = "試食会";
    private static final String fullSizeSpace = "　";
    private static final String chargeMsg = "1名3000(単位：円)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_builder_test);

        // イベント名をSpannableStringBuilderにセットする
        SpannableStringBuilder ssb = new SpannableStringBuilder(fairEventNm);

        // TextViewのフォント変更を行う開始位置を設定する
        int textViewChangeFontStartIndex = ssb.length();

        // イベント名称部分をBOLD指定する
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        ssb.setSpan(styleSpan, 0, textViewChangeFontStartIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // SpannableStringBuilderに全角スペースと有料メッセージを追加する
        ssb.append(fullSizeSpace + chargeMsg);

        // 料金メッセージ部分に変更したいフォントを設定する
        // AbsoluteSizeSpanでは文字サイズのsp指定ができないので相対値で設定する
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.6f);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);

        ssb.setSpan(relativeSizeSpan, textViewChangeFontStartIndex, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(foregroundColorSpan, textViewChangeFontStartIndex, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // TextViewにSpannableStringBuilderでフォント変更した内容をセットする
        TextView fairEventAndChargeMsgTextView = (TextView) findViewById(R.id.FAIR_EVENT_AND_CHARGE_MSG);

        fairEventAndChargeMsgTextView.setText(ssb);
    }
}
