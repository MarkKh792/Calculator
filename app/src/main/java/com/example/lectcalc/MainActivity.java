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
    int c, n, i, rbr, lbr, plusCh, minusCh, er, click = 0;
    int noAddBrackets = 0;

    BigDecimal answer;

    ListView listView;
    static ArrayList<String> answers = new ArrayList<>();
    static ArrayAdapter<String> adapter;

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

        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, answers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // по позиции получаем выбранный элемент
                ArrayList<String> selectedItem = answers;
                // установка текста элемента TextView
                result.setText(selectedItem.get(position));
                i = 0;
                click = 1;
                rbr = 0;
                lbr = 0;
            }
        });
    }

    public void onClick (View v) {
        try {
            Solution solution = new Solution();

            /*if (c == 1) {
                result.setText("");
                c = 0;
            }*/
            char lastCh1;
            switch (v.getId()) {

                case R.id.btn0:
                    if (!calculateNineCh()) {
                        result.append("0");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn1:
                    if (!calculateNineCh()) {
                        result.append("1");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn2:
                    if (!calculateNineCh()) {
                        result.append("2");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn3:
                    if (!calculateNineCh()) {
                        result.append("3");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn4:
                    if (!calculateNineCh()) {
                        result.append("4");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn5:
                    if (!calculateNineCh()) {
                        result.append("5");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn6:
                    if (!calculateNineCh()) {
                        result.append("6");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn7:
                    if (!calculateNineCh()) {
                        result.append("7");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn8:
                    if (!calculateNineCh()) {
                        result.append("8");
                        i = 0;
                        er = 0;
                    }
                    break;
                case R.id.btn9:
                    if (!calculateNineCh()) {
                        result.append("9");
                        i = 0;
                        er = 0;
                    }
                    break;

                case R.id.dot:
                    //if (er == 1) {charCheck();}
                    //else {result.append("."); i = 0; er = 1;}
                    expression = result.getText().toString();
                    if (expression.length() > 0) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 != '.' && lastCh1 != '(' && er == 0 && lastCh1 != '-' && lastCh1 != '/' && lastCh1 != '+' && lastCh1 != '*'
                                && lastCh1 != '√' && lastCh1 != '^' && lastCh1 != '%') {

                            result.append(".");
                            i = 0;
                        }
                    }

                    break;
                case R.id.bracket1:
                    result.append("(");
                    i = 0;
                    lbr++;
                    break;
                case R.id.bracket2:
                    result.append(")");
                    i = 0;
                    rbr++;
                    break;
                case R.id.divide:
                    expression = result.getText().toString();
                    if (expression.length() >= 1) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '-' || lastCh1 == '+' || lastCh1 == '^' || lastCh1 == '*'
                                || lastCh1 == '√' || lastCh1 == '!' || lastCh1 == '.') {
                            if (expression.charAt(expression.length() - 2) == '(') er = 1;
                            else {
                                expression = expression.substring(0, expression.length() - 1);
                                expression = expression + "/";
                                result.setText(expression);
                                i = 0;
                            }
                        } else if (lastCh1 != '/' && lastCh1 != '(' && er == 0) {

                            result.append("/");
                            i = 0;

                        }
                    }
                    //else {result.append("/"); i = 0;}
                    break;
                case R.id.multiply:
                /*if (er == 1) {charCheck();}
                else {result.append("*"); i = 0; er = 1;}*/

                    expression = result.getText().toString();
                    if (expression.length() >= 1) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '-' || lastCh1 == '/' || lastCh1 == '^' || lastCh1 == '+'
                                || lastCh1 == '√' || lastCh1 == '!' || lastCh1 == '.') {
                            if (expression.charAt(expression.length() - 2) == '(') er = 1;
                            else {
                                expression = expression.substring(0, expression.length() - 1);
                                expression = expression + "*";
                                result.setText(expression);
                                i = 0;
                            }
                        } else if (lastCh1 != '*' && lastCh1 != '(' && er == 0) {
                            result.append("*");
                            i = 0;
                        }
                    }
                    //else {result.append("*"); i = 0;}
                    break;
                case R.id.minus:
                    expression = result.getText().toString();
                    expression = result.getText().toString();
                    if (expression.length() > 0) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '+' || lastCh1 == '/' || lastCh1 == '^' || lastCh1 == '*'
                                || lastCh1 == '√' || lastCh1 == '!' || lastCh1 == '.') {
                            expression = expression.substring(0, expression.length() - 1);
                            expression = expression + "-";
                            result.setText(expression);

                        } else if (lastCh1 != '-') {

                            result.append("-");
                            i = 0;
                        }
                    } else {
                        result.append("-");
                        i = 0;
                    }
                    break;
                case R.id.plus:
                    expression = result.getText().toString();
                    if (expression.length() >= 1) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '-' || lastCh1 == '/' || lastCh1 == '^' || lastCh1 == '*'
                                || lastCh1 == '√' || lastCh1 == '!' || lastCh1 == '.') {
                            if (expression.charAt(expression.length() - 2) == '(') er = 1;
                            else {
                                expression = expression.substring(0, expression.length() - 1);
                                expression = expression + "+";
                                result.setText(expression);
                            }

                        } else if (lastCh1 != '+' && lastCh1 != '(' && er == 0) {

                            result.append("+");
                            i = 0;
                        }
                    }

                    break;
                case R.id.percent:

                    expression = result.getText().toString();
                    if (expression.length() >= 1) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '-' || lastCh1 == '/' || lastCh1 == '+' || lastCh1 == '*'
                                || lastCh1 == '√' || lastCh1 == '^' || lastCh1 == '.') {
                            if (expression.charAt(expression.length() - 2) == '(') er = 1;
                            else {
                                expression = expression.substring(0, expression.length() - 1);
                                expression = expression + "%";
                                result.setText(expression);
                                i = 0;
                            }
                        } else if (lastCh1 != '%' && lastCh1 != '(' && er == 0) {

                            result.append("%");
                            i = 0;
                        }
                    }
                    //else {result.append("%"); i = 0;}
                    break;
                case R.id.delx:
                    result.append("1/");
                    i = 0;
                    break;
                case R.id.fact:
                    expression = result.getText().toString();
                    if (expression.length() >= 1) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '-' || lastCh1 == '/' || lastCh1 == '+' || lastCh1 == '*'
                                || lastCh1 == '√' || lastCh1 == '^' || lastCh1 == '.') {
                            if (expression.charAt(expression.length() - 2) == '(') er = 1;
                            else {
                                expression = expression.substring(0, expression.length() - 1);
                                expression = expression + "!";
                                result.setText(expression);
                                i = 0;
                            }
                        } else if (lastCh1 != '!' && lastCh1 != '(' && er == 0) {

                            result.append("!");
                            i = 0;
                        }

                    }
                    break;
                case R.id.sin:
                    //if (er == 1) {charCheck();}
                    result.append("sin(");
                    i = 0;
                    lbr++;
                    er = 1;
                    break;
                case R.id.cos:
                    //if (er == 1) {charCheck();}
                    result.append("cos(");
                    i = 0;
                    lbr++;
                    er = 1;
                    break;
                case R.id.tg:
                    //if (er == 1) {charCheck();}
                    result.append("tan(");
                    i = 0;
                    lbr++;
                    er = 1;
                    break;
                case R.id.log:
                    //if (er == 1) {charCheck();}
                    result.append("log(");
                    i = 0;
                    lbr++;
                    er = 1;
                    break;
                case R.id.ln:
                    //if (er == 1) {charCheck();}
                    result.append("ln(");
                    i = 0;
                    lbr++;
                    er = 1;
                    break;
                case R.id.pow:

                    expression = result.getText().toString();
                    if (expression.length() > 0) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 == '-' || lastCh1 == '/' || lastCh1 == '+' || lastCh1 == '*'
                                || lastCh1 == '√' || lastCh1 == '!' || lastCh1 == '.') {
                            if (expression.charAt(expression.length() - 2) == '(') er = 1;
                            else {
                                expression = expression.substring(0, expression.length() - 1);
                                expression = expression + "^";
                                result.setText(expression);
                                i = 0;
                            }
                        } else if (lastCh1 != '^' && lastCh1 != '(' && er == 0) {
                            result.append("^");
                            i = 0;
                        }
                        ;
                    }
                    break;
                case R.id.pi:
                    expression = result.getText().toString();
                    if (expression.length() > 0) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 != 'π' && lastCh1 != '.' && lastCh1 != '1' && lastCh1 != '2'
                                && lastCh1 != '3' && lastCh1 != '4' && lastCh1 != '5' && lastCh1 != '6'
                                && lastCh1 != '7' && lastCh1 != '8' && lastCh1 != '9' && lastCh1 != '0'
                                && lastCh1 != '%' && lastCh1 != '!') {
                            result.append("π");
                        }
                        else result.append("*π");
                    }
                    else {result.append("π"); }
                    i = 0;
                    break;
                case R.id.e:

                    expression = result.getText().toString();
                    if (expression.length() > 0) {
                        lastCh1 = expression.charAt(expression.length() - 1);
                        if (lastCh1 != 'e' && lastCh1 != '.' && lastCh1 != '1' && lastCh1 != '2'
                                && lastCh1 != '3' && lastCh1 != '4' && lastCh1 != '5' && lastCh1 != '6'
                                && lastCh1 != '7' && lastCh1 != '8' && lastCh1 != '9' && lastCh1 != '0'
                                && lastCh1 != '%' && lastCh1 != '!') {
                            result.append("e");
                        }
                        else result.append("*e");
                    }
                    else {result.append("e"); }
                    i = 0;
                    break;
                case R.id.root:
                    expression = result.getText().toString();
                        result.append("√");
                        i = 0;
                    break;
                case R.id.erase:
                    noAddBrackets = 0;
                    c = 0;
                    result.setText("");
                    if (i == 1) {
                        adapter.clear(); //очистка истории
                        i--;
                    }
                    i++;
                    lbr = 0;
                    rbr = 0;
                    break;
                case R.id.back:
                    noAddBrackets = 0;
                    expression = result.getText().toString();

                    if (expression.length() > 0) {
                        char lastCh = expression.charAt(expression.length() - 1);
                        if (lastCh == '*' || lastCh == '/' || lastCh == '.' || lastCh == '^'
                                || lastCh == '%' || lastCh == '+' || lastCh == '-' || lastCh == '√')
                            er = 0;
                        if (lastCh == '(') //чтобы не ругался, если скобок нет, а lbr и rbr !=0
                            lbr--;
                        if (lastCh == ')')
                            rbr--;
                        minusCh = 0;
                        plusCh = 0;
                        expression = expression.substring(0, expression.length() - 1);
                        result.setText(expression);
                        i = 0;
                    }
                    break;
                case R.id.equals:
                    try {
                        expression = result.getText().toString();
                        char lastCh = expression.charAt(expression.length() - 1);
                        char preLastChar = expression.charAt(expression.length() - 2);
                        if (lastCh == ')') {
                            if (preLastChar == '+' || preLastChar == '-' || preLastChar == '*' || preLastChar == '/'
                                    || preLastChar == '^' || preLastChar == '√' || preLastChar == '.') {
                                expression = expression.substring(0, expression.length() - 2) + ')';
                                result.setText(expression);
                            }
                        } else if (lastCh == '+' || lastCh == '-' || lastCh == '*' || lastCh == '/'
                                || lastCh == '^' || lastCh == '√' || lastCh == '.') {
                            expression = expression.substring(0, expression.length() - 1);
                            result.setText(expression);
                        }

                        if (noAddBrackets == 0) {
                            if (click == 0) {
                                if (rbr != lbr) {
                                    if (lbr > rbr) {
                                        addBrackets(rbr, lbr);
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Неверно расставлены скобки", Toast.LENGTH_SHORT);
                                        toast.show();
                                        break;
                                    }
                                }
                            }
                            if (click == 1) {
                                rbr = 0;
                                lbr = 0;
                                for (char element : expression.toCharArray()) {
                                    if (element == '(') lbr++;
                                    if (element == ')') rbr++;
                                }
                                if (lbr != rbr) {
                                    if (lbr > rbr) {
                                        addBrackets(rbr, lbr);
                                        click = 0;
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(),
                                                "Неверно расставлены скобки", Toast.LENGTH_SHORT);
                                        toast.show();
                                        break;
                                    }
                                }
                            }
                        }
                        noAddBrackets = 1;
                        c = 1;
                        answer = solution.calculate(result.getText().toString(), 0);
                        result.append(" = " + answer.toString());
                        listView.setAdapter(adapter);
                        adapter.setNotifyOnChange(true);
                        adapter.add(result.getText().toString());
                        listView.setSelection(n); //прокрутка до позиции n
                        n++;
                        i = 0;
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Ошибка вычисления", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    break;
            }
        } catch (Exception e) {
            er = 1;
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {

        expression = result.getText().toString();
        savedInstanceState.putString("answ", strAnswer);
        savedInstanceState.putString("expr", expression);
        savedInstanceState.putInt("lbr", lbr);
        savedInstanceState.putInt("rbr", rbr);
        savedInstanceState.putInt("click", click);
        savedInstanceState.putInt("num", n);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listView.setAdapter(adapter); //при повороте экрана lisView не исчезает
        expression = savedInstanceState.getString("expr", " ");
        strAnswer = savedInstanceState.getString("answ", " ");
        lbr = savedInstanceState.getInt("lbr");
        rbr = savedInstanceState.getInt("rbr");
        click = savedInstanceState.getInt("click");
        n = savedInstanceState.getInt("num", 0);
        listView.setSelection(n); //прокрутка до позиции n
        result.setText(expression);
    }

    public void charCheck () {

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Ошибка ввода", Toast.LENGTH_SHORT);
            toast.show();
    }

    public void addBrackets (int rbr, int lbr) {

        int br = lbr - rbr;

        while (br >= 1) {
            result.append(")");
            br--;
        }

    }

    public boolean calculateNineCh () {
        expression = result.getText().toString();
        int position = 0, numNUM = 0, ohNo = 0;
        for (char element : expression.toCharArray()){
            if (element == '1' || element == '2' || element == '3' || element == '4'
                    || element == '5'  || element == '6' || element == '7' || element == '8'
                    || element == '9' || element == '0') {

                if (numNUM == 14) {
                    ohNo = 1;
                }
                else {
                    numNUM++;
                    position++;
                }
            }

            if (element == '+'|| element == '-' || element == '/' || element == '*' || element == '%'
                    || element == '√' || element == '^' || element == '(') {
                numNUM = 0;
                ohNo = 0;
            }

            if (element == '.') {

                numNUM = 0;
                ohNo = 0;

            }
        }

        System.out.println("символов: "+ numNUM);
        if (ohNo == 1) {

            System.out.println("ohNO");
            return true;

        } else return false;
    }

}