package jp.co.n_sysdes.y_isobe.parcelabletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDisp = (Button)findViewById(R.id.btnDisp);
        btnDisp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // ParcelableObjにセットするString List
                List<String> belongings = new ArrayList<String>();

                belongings.add("財布");
                belongings.add("ハンカチ");
                belongings.add("携帯電話");
                belongings.add("腕時計");

                // ParcelableObj生成
                ParcelableObj parcelableObj = new ParcelableObj("ほげ ほげ太郎", 30, belongings);

                // intent生成＆ParcelableObjを詰める
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("parcelableObj", parcelableObj);

                // 次画面遷移
                startActivity(intent);
            }

        });
    }
}
