package jp.co.n_sysdes.y_isobe.parcelabletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YASUHITO on 2016/12/10.
 */
public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 前画面から渡ってきたParcelableObjを取得する
        Intent intent = getIntent();
        ParcelableObj parcelableObj = intent.getParcelableExtra("parcelableObj");

        // ParcelableObjから値を取得して各レイアウト要素に埋め込む
        TextView nameView = (TextView) findViewById(R.id.name);
        TextView ageView = (TextView) findViewById(R.id.age);
        TextView belongingsView = (TextView) findViewById(R.id.belongings);

        nameView.setText(parcelableObj.getName());
        ageView.setText(String.valueOf(parcelableObj.getAge()));

        // String Listは一度取得してからViewにセットする
        List<String> belongingsList = parcelableObj.getBelongings();
        StringBuilder belongingsString = new StringBuilder();

        for (int i = 0; i < belongingsList.size(); i++) {
            if (i > 0) {
                belongingsString.append("、");
            }

            belongingsString.append(belongingsList.get(i));
        }

        belongingsView.setText(belongingsString.toString());
    }
}
