package com.example.lectcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button erase, back, percent, bracket1, bracket2, divide, multiply, minus, plus, dot, equals,
            btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0,
            sin, cos, tg,
            log, ln,
            pi, e;
    TextView result, test;
    public String expression;
    //int answer;
    double answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        erase = findViewById(R.id.erase);
        back = findViewById(R.id.back);
        percent = findViewById(R.id.percent);
        bracket1 = findViewById(R.id.bracket1);
        bracket2 = findViewById(R.id.bracket2);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        dot = findViewById(R.id.dot);
        equals = findViewById(R.id.equals);
        btn9 = findViewById(R.id.btn9);
        btn8 = findViewById(R.id.btn8);
        btn7 = findViewById(R.id.btn7);
        btn6 = findViewById(R.id.btn6);
        btn5 = findViewById(R.id.btn5);
        btn4 = findViewById(R.id.btn4);
        btn3 = findViewById(R.id.btn3);
        btn2 = findViewById(R.id.btn2);
        btn1 = findViewById(R.id.btn1);
        btn0 = findViewById(R.id.btn0);
        result = findViewById(R.id.result);
        sin = findViewById(R.id.sin);
        cos = findViewById(R.id.cos);
        tg = findViewById(R.id.tg);
        log = findViewById(R.id.log);
        ln = findViewById(R.id.ln);

        pi = findViewById(R.id.pi);
        e = findViewById(R.id.e);


        test = findViewById(R.id.test);

    }

    public void onClick (View v) {
        Solution solution = new Solution();
        switch (v.getId()) {

            case R.id.btn0: result.append("0"); break;
            case R.id.btn1: result.append("1"); break;
            case R.id.btn2: result.append("2"); break;
            case R.id.btn3: result.append("3"); break;
            case R.id.btn4: result.append("4"); break;
            case R.id.btn5: result.append("5"); break;
            case R.id.btn6: result.append("6"); break;
            case R.id.btn7: result.append("7"); break;
            case R.id.btn8: result.append("8"); break;
            case R.id.btn9: result.append("9"); break;

            case R.id.dot: result.append("."); break;
            case R.id.bracket1: result.append("("); break;
            case R.id.bracket2: result.append(")"); break;
            case R.id.divide: result.append("/"); break;
            case R.id.multiply: result.append("*"); break;
            case R.id.minus: result.append("-"); break;
            case R.id.plus: result.append("+"); break;
            case R.id.percent: result.append("%"); break;
            case R.id.sin: result.append("sin("); break;
            case R.id.cos: result.append("cos("); break;
            case R.id.tg: result.append("tan("); break;
            case R.id.log: result.append("log("); break;
            case R.id.ln: result.append("ln("); break;
            case R.id.pow: result.append("^"); break;
            case R.id.pi: result.append("π"); break;
            case R.id.erase:
                expression = result.getText().toString();
                test.setText(expression);
                result.setText(""); break;
            case R.id.back:
                expression = result.getText().toString();
                if (expression.length() > 0) {
                    expression = expression.substring(0, expression.length() - 1);
                    result.setText(expression);
                } break;
            case R.id.equals: test.setText("=");
                //answer = solution.calculate(result.getText().toString());
                //test.setText(Integer.toString(answer));
                answer = solution.calculate(result.getText().toString(), 0);
                test.setText(Double.toString(answer));
                break;
        }

    }

}