package com.example.msirig.calculator_poole_jayce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String str1,str2,str3,result,str,sign;
    private Double a,b;


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

    /*public void onClickSigns(View v)
    {
        Button button = (Button) v;
        sign = button.getText().toString();
        screen.setText(sign);
        if(str == ""){
            screen.setText("Need a value before the operator");
        }
        else{
            a = Double.parseDouble(str);
            str="";
        }
    }*/
    public ArrayList<String> tokenize()
    {
        int length = str.length();
        String num = "";
        ArrayList<String> tokens = new ArrayList<>();
        for(int i = 0;i<length; i++)
        {
            Character temp = str.charAt(i);
            if(temp == '(')
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }
                tokens.add(temp.toString());
            }
            else if(temp == ')')
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }
                tokens.add(temp.toString());
            }
            else if(temp == '+' || temp == '-' || temp == '*' || temp == '/' || temp == '^')
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }
                tokens.add(temp.toString());
            }
            else
            {
                if(Character.isDigit(temp) || temp == '.')
                {
                    num += temp.toString();
                }
                if(i==length-1)
                {
                    tokens.add(num);
                }
            }
         }
        screen.setText(tokens.toString());
        return tokens;
    }

    public void parseInput(ArrayList<String> input)
    {

    }

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
        Button button = (Button) v;
        //str2 = screen.getText().toString();
        //b = Double.parseDouble(str2);
        tokenized = tokenize();
        parseInput(tokenized);
        //getResult(sign);

        //screen.setText(result);
        //sign = "";
        //str="";
    }
}
