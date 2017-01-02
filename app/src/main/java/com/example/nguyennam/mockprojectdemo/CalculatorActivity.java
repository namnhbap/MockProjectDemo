package com.example.nguyennam.mockprojectdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_DATA = "EXTRA_DATA";
    private static final String TAG = "Nam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Button btn0 = (Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        Button btnPhay = (Button) findViewById(R.id.btnPhay);
        btnPhay.setOnClickListener(this);

        Button btn30 = (Button) findViewById(R.id.btn30);
        btn30.setOnClickListener(this);

        Button btnBang = (Button) findViewById(R.id.btnBang);
        btnBang.setOnClickListener(this);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        Button btnTru = (Button) findViewById(R.id.btnTru);
        btnTru.setOnClickListener(this);

        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

        Button btnCong = (Button) findViewById(R.id.btnCong);
        btnCong.setOnClickListener(this);

        Button btnC = (Button) findViewById(R.id.btnC);
        btnC.setOnClickListener(this);

        Button btnNhan = (Button) findViewById(R.id.btnNhan);
        btnNhan.setOnClickListener(this);

        Button btnChia = (Button) findViewById(R.id.btnChia);
        btnChia.setOnClickListener(this);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText edtTinh = (EditText) findViewById(R.id.edtTinh);
        Button btnChangeBang = (Button) findViewById(R.id.btnBang);
        switch (v.getId()){
            case R.id.btn0:
                edtTinh.setText(edtTinh.getText().toString()+"0");
                break;

            case R.id.btn1:
                edtTinh.setText(edtTinh.getText().toString()+"1");
                break;

            case R.id.btn2:
                edtTinh.setText(edtTinh.getText().toString()+"2");
                break;

            case R.id.btn3:
                edtTinh.setText(edtTinh.getText().toString()+"3");
                break;

            case R.id.btn4:
                edtTinh.setText(edtTinh.getText().toString()+"4");
                break;

            case R.id.btn5:
                edtTinh.setText(edtTinh.getText().toString()+"5");
                break;

            case R.id.btn6:
                edtTinh.setText(edtTinh.getText().toString()+"6");
                break;

            case R.id.btn7:
                edtTinh.setText(edtTinh.getText().toString()+"7");
                break;

            case R.id.btn8:
                edtTinh.setText(edtTinh.getText().toString()+"8");
                break;

            case R.id.btn9:
                edtTinh.setText(edtTinh.getText().toString()+"9");
                break;

            case R.id.btn30:
                edtTinh.setText(edtTinh.getText().toString()+"000");
                break;

            case R.id.btnPhay:
                edtTinh.setText(edtTinh.getText().toString()+".");
                break;

            case R.id.btnC:
                edtTinh.setText("");
                break;

            case R.id.btnBack:
                edtTinh.setText(catKyTuCuoi(edtTinh.getText().toString()));
                break;

            case R.id.btnCong:
                edtTinh.setText(edtTinh.getText().toString()+"+");
                break;

            case R.id.btnTru:
                edtTinh.setText(edtTinh.getText().toString()+"-");
                break;

            case R.id.btnNhan:
                edtTinh.setText(edtTinh.getText().toString()+"*");
                break;

            case R.id.btnChia:
                edtTinh.setText(edtTinh.getText().toString()+"/");
                break;

            case R.id.btnBang:
               if (btnChangeBang.getText().toString().equals("=")){
                   String result = Double.toString(eval(edtTinh.getText().toString()));
                   String s;
                   //cat .0
                   for (int i=0; i<result.length(); i++){
                       if(".".equals(String.valueOf(result.charAt(i)))){
                           s = result.substring(i+1,result.length());
                           if("0".equals(s)) {
                               result = result.substring(0, i);
                           }
                       }
                   }

                   btnChangeBang.setText("Xong");
                   edtTinh.setText(result);
               }else {
                   final Intent data = new Intent();
                   String str = edtTinh.getText().toString();
                   data.putExtra(EXTRA_DATA, str);
                   setResult(RESULT_OK, data);
                   finish();
               }

                break;
        }
        //doi phim = thanh chu xong
        String curText = edtTinh.getText().toString();
        if ((curText.indexOf("+")==-1)&&(curText.indexOf("-")==-1)&&(curText.indexOf("*")==-1)&&(curText.indexOf("/")==-1)){
            btnChangeBang.setText("Xong");

        } else {
            btnChangeBang.setText("=");
        }
        edtTinh.setSelection(edtTinh.length()); //set cursor cuoi text

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

    private String catKyTuCuoi(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
