package com.example.msirig.calculator_poole_jayce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

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

    public void onClickSigns(View v)
    {
        Button button = (Button) v;
        sign = button.getText().toString();
        screen.setText(sign);
        a = Double.parseDouble(str);
        str="";
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
        Button button = (Button) v;
        str2 = screen.getText().toString();
        b = Double.parseDouble(str2);

        getResult(sign);

        screen.setText(result);
        sign = "";
        str="";
    }
}
