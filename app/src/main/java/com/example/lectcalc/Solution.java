package com.example.lectcalc;

import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    MainActivity main = new MainActivity();

    public BigDecimal calculate (String expression, int countOperation) {

        MathContext context = new MathContext(9,RoundingMode.HALF_UP);

        expression = expression.replace("π", "3.14159265");
        expression = expression.replace("e", "2.71828183");
        expression = expression.replace("√", "1√");
        expression = expression.replace(".(", "(");
        expression = expression.replace("(.", "(");
        expression = expression.replace(").", ")");
        expression = expression.replace(".)", ")");
        expression = expression.replace("tan(.", "tan(");
        expression = expression.replace(".tan(", "tan(");
        expression = expression.replace("tan(.", "tan(");
        expression = expression.replace("1(", "1*(");
        expression = expression.replace("2(", "2*(");
        expression = expression.replace("3(", "3*(");
        expression = expression.replace("4(", "4*(");
        expression = expression.replace("5(", "5*(");
        expression = expression.replace("6(", "6*(");
        expression = expression.replace("7(", "7*(");
        expression = expression.replace("8(", "8*(");
        expression = expression.replace("9(", "9*(");
        expression = expression.replace("0(", "0*(");
        expression = expression.replace(")(", ")*(");

        Solution solution = new Solution();
        //solution.recurse(expression, 0); //expected output 0.5 6
        //implement
        List<Token> tokens = new ArrayList<>();
        countOperation = parseExpr(tokens, expression);

        // получение и вывод результата
        TokenList tokenList = new TokenList(tokens);
        double result = expression(tokenList);

        BigDecimal res = new BigDecimal(result, context);
        return res;

    }

    // лексический анализ - разбор выражения на лексемы (токены)
    public static int parseExpr(List<Token> tokens, String expression) {
        int countOperation = 0;

//        List<Token> tokens = new ArrayList<>();
        char[] expr = expression.toCharArray();
        int i = 0;
        while (i < expr.length) {
            switch (expr[i]) {
                case ' ':
                    i++;
                    break;
                case '+':
                    tokens.add(new Token(TokenType.ADD, expr[i]));
                    i++;
                    countOperation++;
                    break;
                case '-':
                    tokens.add(new Token(TokenType.SUB, expr[i]));
                    i++;
                    countOperation++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MUL, expr[i]));
                    i++;
                    countOperation++;
                    break;
                case '/':
                    tokens.add(new Token(TokenType.DIV, expr[i]));
                    i++;
                    countOperation++;
                    break;
                    // процент
                case '%':
                    tokens.add(new Token(TokenType.PER, expr[i]));
                    i++;
                    countOperation++;
                    break;
                    //факториал
                case '!':
                    tokens.add(new Token(TokenType.FACT, expr[i]));
                    i++;
                    countOperation++;
                    break;
                case '√':
                    tokens.add(new Token(TokenType.ROOT, expr[i]));
                    i++;
                    countOperation++;
                    break;
                    ////
                case '^':
                    tokens.add(new Token(TokenType.POW, expr[i]));
                    i++;
                    countOperation++;
                    break;
                case '(':
                    tokens.add(new Token(TokenType.L_BR, expr[i]));
                    i++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.R_BR, expr[i]));
                    i++;
                    break;
                case '.':
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                    if (expr[i] == '.' &&                                       // если первый символ десятичный разделитель и
                            (i + 1 >= expr.length ||                            // это последний символ выражения или
                                    expr[i + 1] < '0' || expr[i + 1] > '9'))    // следующий символ не цифра
                        throw new RuntimeException("Unexpected character: " + expr[i] + " at position " + i);
                    double value = 0.0;
                    for ( ; i < expr.length && expr[i] >= '0' && expr[i] <= '9'; i++)
                        value = 10 * value + (expr[i] - '0');                   // накопление целой части
                    if (i < expr.length && expr[i] == '.') {
                        i++;
                        double factor = 1.0;                                    // множитель для десятичных разрядов
                        for ( ; i < expr.length && expr[i] >= '0' && expr[i] <= '9'; i++) {
                            factor *= 0.1;                                      // уменьшение множителя в 10 раз
                            value += (expr[i] - '0') * factor;                  // добавление десятичной позиции
                        }
                    }
                    tokens.add(new Token(TokenType.NUM, value));
                    break;
                default:
                    if (i + 3 < expr.length &&
                            expr[i] == 'c' && expr[i + 1] == 'o' && expr[i + 2] == 's' && expr[i + 3] == '(') {
                        tokens.add(new Token(TokenType.COS, "cos("));
                        i += 4;
                        countOperation++;
                    }
                    else if (i + 3 < expr.length &&
                            expr[i] == 's' && expr[i + 1] == 'i' && expr[i + 2] == 'n' && expr[i + 3] == '(') {
                        tokens.add(new Token(TokenType.SIN, "sin("));
                        i += 4;
                        countOperation++;
                    }
                    else if (i + 3 < expr.length &&
                            expr[i] == 't' && expr[i + 1] == 'a' && expr[i + 2] == 'n' && expr[i + 3] == '(') {
                        tokens.add(new Token(TokenType.TAN, "tan("));
                        i += 4;
                        countOperation++;
                    }
                    //логарифмы
                    else if (i + 3 < expr.length &&
                            expr[i] == 'l' && expr[i + 1] == 'o' && expr[i + 2] == 'g' && expr[i + 3] == '(') {
                        tokens.add(new Token(TokenType.LOG, "log("));
                        i += 4;
                        countOperation++;
                    }

                    else if (i + 2 < expr.length &&
                            expr[i] == 'l' && expr[i + 1] == 'n' && expr[i + 2] == '(') {
                        tokens.add(new Token(TokenType.LN, "ln("));
                        i += 3;
                        countOperation++;
                    }

                    else if (i + 1 < expr.length &&
                            expr[i] == '1' && expr[i + 1] == '/' ) {
                        tokens.add(new Token(TokenType.LN, "1/"));
                        i += 2;
                        countOperation++;
                    }

                    else
                        throw new RuntimeException("Unexpected character: " + expr[i] + " at position " + i);
                    break;
            }
        }
        if (tokens.size() == 0)                                                 // если выражение пустое
            tokens.add(new Token(TokenType.NUM, 0.0));                    // оно равно нулю
        tokens.add(new Token(TokenType.EOF, ""));
//        return tokens;

        return countOperation;
    }

    // Грамматика:
    // expression : add_sub EOF
    // add_sub : mul_div ( ( '+' | '-' ) mul_div )*
    // mul_div : pow ( ( '*' | '/' ) pow )*
    // pow : primary ( '^' primary )*
    // primary : NUM | '-' pow | ( '(' | 'cos(' | 'sin(' | 'tan(' ) add_sub ')'
    public static double expression(TokenList tokenList) {
        double value = add_sub(tokenList);
        Token token = tokenList.next();
        if (token.type != TokenType.EOF)
            throw new RuntimeException("Unexpected token: " + token.title + " at position " + tokenList.getIndex());
        return value;
    }

    public static double add_sub(TokenList tokenList) {
        double value = mul_div(tokenList);
        while (true) {
            Token token = tokenList.next();
            switch (token.type) {
                case ADD:
                    value += mul_div(tokenList);
                    break;
                case SUB:
                    value -= mul_div(tokenList);
                    break;
                default:
                    tokenList.back();
                    return value;
            }
        }
    }

    public static double mul_div(TokenList tokenList) {
        double value = pow(tokenList);
        while (true) {
            Token token = tokenList.next();
            switch (token.type) {
                case MUL:
                    value *= pow(tokenList);
                    break;
                case DIV:
                        value /= pow(tokenList);
                    break;
                case PER:
                    value = value / 100;
                    break;
                case ROOT:
                    value = 0;
                    value = Math.sqrt(pow(tokenList));
                    break;
                case FACT:
                    int tmp = (int) value;
                    if (((double) tmp) == value)
                        value = factorial(tmp);
                    else
                        value = Math.exp(Math.log(factorial(tmp)) + (value - tmp) * Math.log(tmp+1));
                    break;
                default:
                    tokenList.back();
                    return value;
            }
        }
    }

    public static double pow(TokenList tokenList) {
        double value = primary(tokenList);
        while (true) {
            Token token = tokenList.next();
            switch (token.type) {
                case POW:
                    value = Math.pow(value, primary(tokenList));
                    break;
                default:
                    tokenList.back();
                    return value;
            }
        }
    }

    public static double primary(TokenList tokenList) {
        Token token = tokenList.next();
        switch (token.type) {
            case NUM:
                return token.value;
            case SUB:
                return - pow(tokenList);
            case L_BR:
            case COS: case SIN: case TAN: case LOG: case LN: case DELX:
                double value = 0.0;
                if (token.type == TokenType.L_BR)
                    value = add_sub(tokenList);
                else if (token.type == TokenType.COS)
                    value = Math.cos(Math.toRadians(add_sub(tokenList)));
                else if (token.type == TokenType.SIN)
                    value = Math.sin(Math.toRadians(add_sub(tokenList)));
                else if (token.type == TokenType.TAN)
                    value = Math.tan(Math.toRadians(add_sub(tokenList)));
                else if (token.type == TokenType.LOG)
                    value = Math.log10(add_sub(tokenList));
                else if (token.type == TokenType.LN)
                    value = Math.log(add_sub(tokenList));
                else if (token.type == TokenType.DELX)
                    value = 1 / add_sub(tokenList);
                token = tokenList.next();
                if (token.type != TokenType.R_BR)
                    throw new RuntimeException("Unexpected token: " + token.title + " at position " + tokenList.getIndex());
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + token.title + " at position " + tokenList.getIndex());
        }
    }

    public void Main() {
        //don't delete
    }

    private enum TokenType {
        ADD, SUB,           // сложение, вычитание
        MUL, DIV, PER, DELX, FACT,          // умножение, деление, процент, 1/x, факториал
        POW, ROOT,                // степень, корень
        NUM,                // число
        L_BR, R_BR,         // левая скобка, правая скобка
        COS, SIN, TAN, LOG, LN,      // тригонометрические функции и логарифмы
        EOF                 // конец выражения
    }

    private static class Token {
        private TokenType type;
        private double value;
        private String title;

        public Token(TokenType type, char title) {
            this.type = type;
            value = Double.NaN;
            this.title = String.valueOf(title);
        }

        public Token(TokenType type, double value) {
            this.type = type;
            this.value = value;
            title = String.valueOf(value);
        }

        public Token(TokenType type, String title) {
            this.type = type;
            value = Double.NaN;
            this.title = title;
        }
    }

    // класс обертка для перемещения по списку токенов
    private static class TokenList {
        private List<Token> list;
        private int i;

        public TokenList(List<Token> list) {
            this.list = list;
        }

        public Token next() {
            return list.get(i++);
        }

        public void back() {
            i--;
        }

        public Token getBack() {
            return list.get(i--);
        }

        public int getIndex() {
            return i;
        }
    }

    public static double factorial(int n) {

        double ret = 1;
        for (int i = 1; i <= n; ++i) ret *=i;
        return ret;

    }
}

