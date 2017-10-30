package com.example.msirig.calculator_poole_jayce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String str1,str2,str3,result,str,sign;
    private Double a,b;
    private int mult,div = 5;
    private int add, sub = 0;
    private int exp = 10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.input);
        str = "";
    }

    public void onClick(View v)
    {
        Button button =(Button) v;
        str += button.getText().toString();
        screen.setText(str);
    }

    public int getOperatorPrecedence(String operator)
    {
        if(operator.equals("+") || operator.equals("-")){
            return 0;
        }
        else if(operator.equals("*") || operator.equals("/"))
        {
            return 5;
        }
        else{
            return 10;
        }
    }

    public boolean isOperator(String token)
    {
        if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public int getAssociativity(String token)
    {
        int associativity = 0;
        if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/"))
        {
            associativity = 0;
        }
        else
        {
            associativity = 1;
        }
        return associativity;
    }
    // Test associativity of operator token
    public boolean isAssociative(String token, int type)
    {
        if (!isOperator(token))
        {
            throw new IllegalArgumentException("Invalid token: " + token);
        }

        if (getAssociativity(token) == type) {
            return true;
        }
        return false;
    }

    public final int precedence(String token1, String token2)
    {
        int toke1 = 0;
        int toke2 = 0;
        if(!isOperator(token1) || !isOperator(token2))
        {
            throw new IllegalArgumentException("Invalid operator");
        }
        toke1 = getOperatorPrecedence(token1);
        toke2 = getOperatorPrecedence(token2);
        return toke1 - toke2;
    }


    public ArrayList<String> tokenize()
    {
        int length = str.length();
        if(length == 0)
        {
            screen.setText("0");
            onPause();
        }

        int lp = 0, rp = 0;
        String num = "";
        ArrayList<String> tokens = new ArrayList<>();
        for(int i = 0;i<length; i++)
        {

            Character temp = str.charAt(i);

            /*if(i == 0)
            {
                if(temp == ')')
                {
                    screen.setText("Incorrect Format");
                    break;
                    //onPause();
                }
            }*/

            /*if(isOperator(Character.toString(str.charAt(length-1))) || isOperator(Character.toString(str.charAt(0))))
            {
                screen.setText("Incorrect Format");
                break;
                //onPause();
            }*/

            if(temp.equals('('))
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }
                if(i!=0)
                {
                    /*if(!isOperator(Character.toString(str.charAt(i-1))) || str.charAt(i-1) != '(')
                    {
                        screen.setText("Incorrect Format");
                        break;
                    }*/
                }
                /*if(i == length-1)
                {
                    screen.setText("Incorrect Format");
                    break;
                }*/
                lp++;
                tokens.add(temp.toString());
            }
            else if(temp.equals(')'))
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }
                /*if(str.charAt(i-1) == '+' || str.charAt(i-1) == '-' || str.charAt(i-1) == '*' || str.charAt(i-1) == '/' || str.charAt(i-1) == '^')
                {
                    screen.setText("Incorrect Format");
                    break;
                    //onPause();
                }*/
                rp++;
                tokens.add(temp.toString());
            }
            else if(temp.equals('+') || temp.equals('-') || temp.equals('*') || temp.equals('/') || temp.equals('^'))
            {
                /*if(isOperator(Character.toString(str.charAt(i-1))) || isOperator(Character.toString(str.charAt(i+1))))
                {
                    screen.setText("Incorrect Format");
                }*/
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }
                tokens.add(temp.toString());
            }
            else
            {
                if(Character.isDigit(temp) || temp.equals('.'))
                {
                    num += temp.toString();
                }
                if(i==length-1)
                {
                    tokens.add(num);
                }
            }
         }
         if(lp!=rp)
         {
             screen.setText("Incorrect Format");
             // onPause();
         }
        //screen.setText(tokens.toString());
        return tokens;
    }

    public String[] parseInput(ArrayList<String> input)
    {
        ArrayList<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<String>();

        for( String token : input)
        {
            if(isOperator(token))
            {
                // While stack not empty AND stack top element
                // is an operator
                while (!stack.empty() && isOperator(stack.peek()))
                {
                    if((isAssociative(token,0) && precedence(token, stack.peek()) <=0) ||
                    (isAssociative(token,1) && precedence(token, stack.peek()) < 0))
                    {
                        out.add(stack.pop());
                        continue;
                    }
                    break;
                }
                // Push the new operator on the stack
                stack.push(token);
            }
            else if(token.equals("("))
            {
                stack.push(token);
            }
            else if(token.equals(")"))
            {
                while(!stack.empty() && !stack.peek().equals("("))
                {
                    out.add(stack.pop());
                }
                stack.pop();
            }
            else
            {
                out.add(token);
            }
        }
        while(!stack.empty())
        {
            out.add(stack.pop());
        }
        String[] output = new String[out.size()];
        return out.toArray(output);
    }

    public double solve(String[] tokens)
    {
        Stack<String> stack = new Stack<String>();

        for(String token : tokens)
        {
            if(!isOperator(token))
            {
                stack.push(token);
            }
            else
            {
                Double d2 = Double.valueOf(stack.pop());
                Double d1 = Double.valueOf(stack.pop());
                Double result = 0.0;

                if(token.compareTo("+") == 0)
                {
                    result = d1+d2;
                }
                else if(token.compareTo("-") == 0)
                {
                    result = d1-d2;
                }
                else if(token.compareTo("*") == 0)
                {
                    result = d1*d2;
                }
                else if(token.compareTo("/") == 0)
                {
                    result = d1/d2;
                }
                else
                {
                    result = Math.pow(d1,d2);
                }
                stack.push(String.valueOf(result));
            }
        }
        return Double.valueOf((stack.pop()));
    }
    /*public void parse(ArrayList<String> tokens)
    {
        ArrayList<String> signs = new ArrayList<>();
        //ArrayList<Integer> associative = new ArrayList<>();
        ArrayList<Double> input = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        int index;
        //ArrayList<ArrayList<String>> equations = new ArrayList<ArrayList<String>>();
        int leftP = 0, rightP = 0;
        boolean left,right;

        for(int i = 0; i<tokens.size(); i++)
        {
            /*if(tokens.get(i) == "(")
            {
                leftP++;
            }
            else if(tokens.get(i) == ")")
            {
                leftP--;
            }
            else if(tokens.get(i) == "+")
            {
                signs.add("+");
                associative.add(0);
            }
            else if(tokens.get(i) == "-")
            {
                signs.add("-");
                associative.add(0);
            }
            else if(tokens.get(i) == "*")
            {
                signs.add("*");
                associative.add(1);
            }
            else if(tokens.get(i) == "/")
            {
                signs.add("/");
                associative.add(1);
            }
            else if(tokens.get(i) == "^")
            {
                signs.add("^");
                associative.add(2);
            }
            else
            {
                input.add(Integer.valueOf(tokens.get(i)));
            }
            if(tokens.get(i) == "(")
            {
                indices.add(i);
            }
            if(i == 0)
            {
                if(isOperator(tokens.get(i)) || tokens.get(i) == ")")
                {
                    screen.setText("Invalid Format");
                    break;
                    // onPause();
                }
                else if(tokens.get(i) == "(")
                {
                    left = true;
                    leftP++;
                }
                else
                {
                    input.add(Double.valueOf(tokens.get(i)));
                }
            }
            else
            {

            }

        }
    }*/

    /*public int nestedParenthesis(ArrayList<String> tokens, int index)
    {
        int value = 0;

        for(int i = index+1; i<tokens.size(); i++)
        {
            if(tokens.get(i) == ")")
            {
                return i;
            }
            else if(tokens.get(i) == "(")
            {
                return i;
            }
        }
    }*/


    public void clear(View v)
    {
        Button button = (Button) v;
        a = null;
        b = null;
        str = "";
        str2 = "";
        sign = "";
        result = "";
        screen.setText("0");
    }

    public void getResult(String sign)
    {
        double temp = 0.0;
        DecimalFormat df = new DecimalFormat("#########.######");

        if(sign.equals("+"))
        {
            temp = Double.valueOf(df.format(a+b));

        }
        else if(sign.equals("-"))
        {
            temp = Double.valueOf(df.format(a-b));

        }
        else if(sign.equals("*"))
        {
            temp = Double.valueOf(df.format(a*b));
        }
        else if(sign.equals("/"))
        {
            if(b == 0.0)
            {
                result = "Can't divide by 0";
            }
            else
            {
                temp = Double.valueOf(df.format(a/b));
            }

        }
        else if(sign.equals("^"))
        {
            temp = Double.valueOf(df.format(Math.pow(a,b)));
        }
        else
        {
            screen.setText("k");
        }
        result = temp + "";
    }

    public void calculate(View v)
    {
        ArrayList<String> tokenized;
        String[] tokens;
        double solution;
        Button button = (Button) v;
        //str2 = screen.getText().toString();
        //b = Double.parseDouble(str2);
        tokenized = tokenize();
        //parse(tokenized);
        tokens = parseInput(tokenized);
        screen.setText(Double.toString(solve(tokens)));


        //getResult(sign);

        //screen.setText(result);
        //sign = "";
        //str="";
    }
}
