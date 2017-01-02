package com.example.nguyennam.mockprojectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Nam";
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    TextView txtGotoCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtGotoCal = (TextView) findViewById(R.id.gotoCal);
        txtGotoCal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gotoCal:
                Intent intent = new Intent(this, CalculatorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: " + "moi vao");
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EXAMPLE) {
            if (resultCode == RESULT_OK) {
                final String result = data.getStringExtra(CalculatorActivity.EXTRA_DATA);
                txtGotoCal.setText(result);
                Log.d(TAG, "onActivityResult: " + result);
            } else {
                Log.d(TAG, "onActivityResult: Fail");
                txtGotoCal.setText("Huy roi");
            }

        }

    }
}
