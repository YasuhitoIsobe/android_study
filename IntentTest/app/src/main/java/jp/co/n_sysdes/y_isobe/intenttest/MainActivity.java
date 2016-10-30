package jp.co.n_sysdes.y_isobe.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mainEditText = (EditText) findViewById(R.id.main_edit_text);
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("SEND_MESSAGE", mainEditText.getText().toString());

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (intent != null) {
            TextView mainTextView = (TextView) findViewById(R.id.main_text_view);
            mainTextView.setText(intent.getStringExtra("BACK_MESSAGE"));
        }
    }
}
