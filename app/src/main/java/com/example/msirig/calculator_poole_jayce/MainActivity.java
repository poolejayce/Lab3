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
    private int leftParen = 0, rightParen = 0;
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

    //base onclick for when an input button is slected
    public void onClick(View v)
    {
        Button button =(Button) v;
        String temp = button.getText().toString();
        if(canAdd(temp))
        {
            str += temp;

        }
        screen.setText(str);
    }

    public boolean divideByZero()
    {
        boolean zero = true;

        for(int i = 0; i<str.length(); i++)
        {
            if(i <= str.length() - 3) {
                if (Character.toString(str.charAt(i)).equals("/")) {
                    if (Character.toString(str.charAt(i + 1)).equals("0")) {
                        if (!Character.toString(str.charAt(i + 2)).equals(".")) {
                            zero = false;
                        }
                    }
                }
            }
            else
            {
                if (Character.toString(str.charAt(i)).equals("/")) {
                    if (Character.toString(str.charAt(i + 1)).equals("0")) {
                        zero = false;
                    }
                }
            }
        }

        return zero;

    }

    //a boolean function that checks just before  parsing and calculating
    //to make sure that the input is in correct format
    public boolean isCorrectFormat()
    {
        boolean correct = false;

        if (leftParen != rightParen)
        {
            clear1(0);
            correct = false;
        }
        else if(isOperator(Character.toString(str.charAt(str.length() - 1))))
        {
            clear1(0);
            correct = false;
        }
        else if(Character.toString(str.charAt(str.length() - 1)).equals("."))
        {
            str+= "0";
            correct = true;
        }
        else if(!divideByZero())
        {
            clear1(2);
            correct = false;
        }
        else
        {
            correct = true;
        }

        return correct;
    }

    //a boolean function that checks if the button you clicked for input is valid or not
    //if it is valid then the button.text will be appended to the input
    //if it is false nothing will happen meaning it would be an error if it was added
    public boolean canAdd(String input)
    {
        boolean canAdd = false;

        switch (input){
            case "1":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "2":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "3":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "4":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "5":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "6":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "7":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "8":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "9":
                if (str.length() == 0)
                {

                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")") && str.length()!=0)
                {
                    str+="*";
                }
                canAdd = true;
                break;
            case "0":
                if (str.equals(""))
                {
                    canAdd = true;
                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals(")"))
                {
                    canAdd = true;
                    str += "*";
                }
                else if(Character.toString(str.charAt(str.length() - 1)).equals("0"))
                {
                    canAdd = false;
                }else
                {
                    canAdd = true;
                }
                break;
            case ".":
                if(str.equals(""))
                {
                    canAdd = true;
                }
                else if(Character.isDigit((str.charAt(str.length() - 1))))
                {
                    canAdd = true;
                }
                else if (Character.toString((str.charAt(str.length() - 1))).equals(")"))
                {
                    str+="*0";
                    canAdd = true;
                }
                else
                {
                    str+= "0";
                    canAdd = true;
                }
                break;
            case "+":
                if (str.equals(""))
                {

                }
                else if (isOperator(Character.toString(str.charAt(str.length() - 1))) || Character.toString(str.charAt(str.length() - 1)).equals("("))
                {
                    canAdd = false;
                }
                else
                {
                    canAdd = true;
                }
                break;
            case "-":
                if (str.equals("") || Character.toString(str.charAt(str.length() - 1)).equals("(") || Character.toString(str.charAt(str.length() - 1)).equals("*")
                        || Character.toString(str.charAt(str.length() - 1)).equals("/") || Character.toString(str.charAt(str.length() - 1)).equals("^"))
                {
                    str += "(";
                    leftParen++;
                    canAdd = true;
                }
                else if ((Character.toString(str.charAt(str.length() - 1)).equals("+")) || Character.toString(str.charAt(str.length() - 1)).equals("-") || Character.toString(str.charAt(str.length() - 1)).equals("."))
                {
                    canAdd = false;
                }
                else
                {
                    canAdd = true;
                }
                break;
            case "/":
                if (str.equals(""))
                {

                }
                else if (isOperator(Character.toString(str.charAt(str.length() - 1))) || Character.toString(str.charAt(str.length() - 1)).equals("("))
                {
                    canAdd = false;
                }
                else
                {
                    canAdd = true;
                }
                break;
            case "*":
                if (str.equals(""))
                {

                }
                else if (isOperator(Character.toString(str.charAt(str.length() - 1))) || Character.toString(str.charAt(str.length() - 1)).equals("("))
                {
                    canAdd = false;
                }
                else
                {
                    canAdd = true;
                }
                break;
            case "^":
                if (str.equals(""))
                {

                }
                else if (isOperator(Character.toString(str.charAt(str.length() - 1))) || Character.toString(str.charAt(str.length() - 1)).equals("("))
                {
                    canAdd = false;
                }
                else
                {
                    canAdd = true;
                }
                break;
            case "(":
                if (str.equals(""))
                {
                    canAdd = true;
                }
                else if (Character.isDigit(str.charAt(str.length() - 1)) || Character.toString(str.charAt(str.length() - 1)).equals(".") || Character.toString(str.charAt(str.length() - 1)).equals(")"))
                {
                    str+= "*";
                    canAdd = true;
                }
                else
                {
                    canAdd = true;
                }
                leftParen++;
                break;
            case ")":
                if (rightParen<leftParen && !Character.toString(str.charAt(str.length() - 1)).equals("(") && !Character.toString(str.charAt(str.length() - 1)).equals("-"))
                {
                    rightParen++;
                    canAdd = true;
                }
                else
                {
                    canAdd = false;
                }
                break;
        }
        return canAdd;
    }

    //return the precedence value of an operator
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

    //boolean check to see if token is in fact an operator
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
    //return the integer associativity of an operator
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

    //returns the precedence of two operators
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

    //gather all the input into a tokenized arraylist
    //example input: 7+8.8-(3^2)-5
    //arraylist = [7,+,8.8,-,(,3,^,2,),-,5]
    public ArrayList<String> tokenize()
    {
        int length = str.length();

        String num = "";
        ArrayList<String> tokens = new ArrayList<>();
        for(int i = 0;i<length; i++)
        {

            Character temp = str.charAt(i);

            if(temp.equals('('))
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }

                tokens.add(temp.toString());
            }
            else if(temp.equals(')'))
            {
                if(!num.isEmpty())
                {
                    tokens.add(num);
                    num = "";
                }

                tokens.add(temp.toString());
            }
            else if(temp.equals('-'))
            {
                if(Character.toString(str.charAt(i-1)).equals("(") || isOperator(Character.toString(str.charAt(i-1))))
                {
                    num += "-";
                }

                if(!num.isEmpty() && !num.equals("-"))
                {
                    tokens.add(num);
                    num = "";
                }
                tokens.add(temp.toString());


            }
            else if(temp.equals('+') || temp.equals('*') || temp.equals('/') || temp.equals('^'))
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

        return tokens;
    }

    //put the tokenized arraylist and this will now switch it from infix notation to postfix for calculation
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

    //this will take the tokenized postfix array of input and solve it and return the result
    public double solve(String[] tokens)
    {
        Stack<String> stack = new Stack<String>();

        boolean negative = false;
        int counter = 1,neg = 0;

        for(String token : tokens)
        {
            if(!isOperator(token))
            {
                if(token.charAt(0) == '-' && token.length() > 1)
                {
                    if(Character.isDigit(token.charAt(1)))
                    {
                        negative = true;
                        neg += counter;
                    }
                }
                stack.push(token);
                counter++;
            }
            else
            {

               if(negative)
               {
                   if(neg == 1)
                   {
                       Double d2 = Double.valueOf(stack.pop());
                       Double d1 = negative(stack.pop());
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
                   else if(neg == 2)
                   {
                       Double d2 = negative(stack.pop());
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
                   else if(neg == 3)
                   {
                       Double d2 = negative(stack.pop());
                       Double d1 = negative(stack.pop());
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
        }
        //DecimalFormat df = new DecimalFormat(".######");

        return Double.valueOf(((stack.pop())));
    }

    public double negative(String input)
    {
        input = input.substring(1,input.length());

        double neg = Double.valueOf(input);

        neg = neg - (neg * 2);

        return neg;
    }

    //clear button function which resets all global fields essentially starting over
    public void clear(View v)
    {
        Button button = (Button) v;
        str = "";
        result = "";
        leftParen = 0;
        rightParen = 0;
        screen.setText("0");
    }

    //clear1 is a function that is called after a result is printed to the screen so that
    //when the user starts to type in new numbers for another function the string is wiped.
    public void clear1(int i)
    {
        if(i == 0)
        {
            result = "";
            leftParen = 0;
            rightParen = 0;
            str = "";
            screen.setText("Invalid Format");
        }
        else if (i == 2)
        {
            result = "";
            leftParen = 0;
            rightParen = 0;
            str = "";
            screen.setText("Can't divide by 0");
        }
        else {
            result = "";
            leftParen = 0;
            rightParen = 0;
            str = "";
        }
    }

    //main run method that drives the program when the equal button is pressed.
    public void calculate(View v)
    {
        ArrayList<String> tokenized;
        String[] tokens;
        tokenized = tokenize();
        if(isCorrectFormat())
        {
            tokens = parseInput(tokenized);
            screen.setText(Double.toString(solve(tokens)));
            clear1(1);
        }
        else{

        }

    }
}
