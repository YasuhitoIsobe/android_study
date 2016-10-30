package jp.co.n_sysdes.y_isobe.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        TextView subTextView = (TextView) findViewById(R.id.sub_text_view);
        subTextView.setText(intent.getStringExtra("SEND_MESSAGE"));

        Button subButton = (Button) findViewById(R.id.sub_button);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText subEditText = (EditText) findViewById(R.id.sub_edit_text);
                Intent data = new Intent();
                data.putExtra("BACK_MESSAGE", subEditText.getText().toString());

                setResult(0, data);
                finish();
            }
        });
    }
}