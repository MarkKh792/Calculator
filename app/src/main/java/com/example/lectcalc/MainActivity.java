package com.example.lectcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button erase, back, percent, bracket1, bracket2, divide, multiply, minus, plus, dot, equals,
            delx, fact, root,
            btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0,
            sin, cos, tg,
            log, ln,
            pi, e;
    TextView result;
    String expression = " ", strAnswer = " ";
    int c, n, i, rbr, lbr = 0;
    //double answer;
    BigDecimal answer;

    ListView listView;
    static ArrayList<String> answers = new ArrayList<>();
    static ArrayAdapter<String> adapter;

    EditText text1;

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
        delx = findViewById(R.id.delx);
        fact = findViewById(R.id.fact);
        equals = findViewById(R.id.equals);
        root = findViewById(R.id.root);
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

        //text1 = findViewById(R.id.text1);

        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, answers);


    }

    public void onClick (View v) {
        Solution solution = new Solution();
        History history = new History();

        if (c == 1) {
            result.setText("");
            c = 0;
        }

        switch (v.getId()) {

            case R.id.btn0: result.append("0"); i = 0; break;
            case R.id.btn1: result.append("1"); i = 0; break;
            case R.id.btn2: result.append("2"); i = 0; break;
            case R.id.btn3: result.append("3"); i = 0; break;
            case R.id.btn4: result.append("4"); i = 0; break;
            case R.id.btn5: result.append("5"); i = 0; break;
            case R.id.btn6: result.append("6"); i = 0; break;
            case R.id.btn7: result.append("7"); i = 0; break;
            case R.id.btn8: result.append("8"); i = 0; break;
            case R.id.btn9: result.append("9"); i = 0; break;

            case R.id.dot: result.append("."); i = 0; break;
            case R.id.bracket1: result.append("("); i = 0; lbr++; break;
            case R.id.bracket2: result.append(")"); i = 0; rbr++; break;
            case R.id.divide: result.append("/"); i = 0; break;
            case R.id.multiply: result.append("*"); i = 0; break;
            case R.id.minus: result.append("-"); i = 0; break;
            case R.id.plus: result.append("+"); i = 0; break;
            case R.id.percent: result.append("%"); i = 0; break;
            case R.id.delx: result.append("1/"); i = 0; break;
            case R.id.fact: result.append("!"); i = 0; break;
            case R.id.sin: result.append("sin("); i = 0; lbr++; break;
            case R.id.cos: result.append("cos("); i = 0; lbr++; break;
            case R.id.tg: result.append("tan("); i = 0; lbr++; break;
            case R.id.log: result.append("log("); i = 0; lbr++; break;
            case R.id.ln: result.append("ln("); i = 0; lbr++; break;
            case R.id.pow: result.append("^"); i = 0; break;
            case R.id.pi: result.append("π"); i = 0; break;
            case R.id.e: result.append("e"); i = 0; break;
            case R.id.root: result.append("√"); i = 0; break;
            case R.id.erase:
                //expression = result.getText().toString();
                //test.setText(expression);
                c = 0;
                result.setText("");
                if (i == 1) {
                    adapter.clear(); //очистка истории
                    i--;
                }
                i++;
                break;
            case R.id.back:
                expression = result.getText().toString();
                if (expression.length() > 0) {
                    expression = expression.substring(0, expression.length() - 1);
                    result.setText(expression);
                    i = 0;
                } break;
            case R.id.equals:
                if (rbr != lbr) {

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Неверно проставлены скобки", Toast.LENGTH_SHORT);
                    toast.show();
                    break;

                }
                c = 1;
                answer = solution.calculate(result.getText().toString(), 0);
                result.append(" = "+ answer.toString());
                listView.setAdapter(adapter);
                adapter.setNotifyOnChange(true);
                adapter.add(result.getText().toString());
                listView.setSelection(n); //прокрутка до позиции n
                n++;
                i = 0;
                break;
        }

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {

        expression = result.getText().toString();
        //strAnswer = test.getText().toString();
        savedInstanceState.putString("answ", strAnswer);
        savedInstanceState.putString("expr", expression);
        savedInstanceState.putInt("num", n);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listView.setAdapter(adapter); //при повороте экрана lisView не исчезает
        expression = savedInstanceState.getString("expr", " ");
        strAnswer = savedInstanceState.getString("answ", " ");
        n = savedInstanceState.getInt("num", 0);
        listView.setSelection(n); //прокрутка до позиции n
        result.setText(expression);
        //test.setText(strAnswer);
    }

}