package com.targetindia.programs;

import java.util.Stack;

public class ReverseWords {

       // Method to reverse words of a String
        public static String reverseWords(String str)
        {
            String spaces = "";
            String words="";
            Stack<String> stack = new Stack<String>();


            char[] charArray = str.toCharArray();
            char ch = ' ';
            int iter = 0; int iterEnd = str.length();

            while(iter < iterEnd )
            {
                if( charArray[iter] == ' ')
                {
                    spaces="";
                    while ( iter < iterEnd && charArray[iter] == ' ' )
                    {
                        spaces = spaces + " ";
                        iter++;
                    }
                    //push spaces in to a stack

                    stack.push(spaces);
                }
                else {
                    words = "";
                    while ( iter < iterEnd && charArray[iter] != ' ' )
                    {
                        words = words + charArray[iter] ;
                        iter++;
                    }
                    //push the word into a stack
                    stack.push(words);
                }

            }
            String result = "";
            //pop the stack
            //return result
            while(!stack.isEmpty()) {
                result = result + stack.pop();
            }
            return result;

        }


        public static void main(String[] args) {
            String s1 = "Welcome to Java Programming";
            System.out.println("[" + s1+"]" );
            System.out.println("["+ reverseWords(s1)+ "]");


            String s2 = "Hello";
            System.out.println("[" + s2+"]" );
            System.out.println("["+ reverseWords(s2)+ "]");

            String s3 = "  Hello World  ";
            System.out.println("[" + s3+"]" );
            System.out.println("["+ reverseWords(s3)+ "]");

            String s4 = "Hello   World";
            System.out.println("[" + s4+"]" );
            System.out.println("["+ reverseWords(s4)+ "]");

            String s5 = "";
            System.out.println("[" + s5+"]" );
            System.out.println("["+ reverseWords(s5)+ "]");

            String s6 = "Hello, Welcome...";
            System.out.println("[" + s6+"]" );
            System.out.println("["+ reverseWords(s6)+ "]");


        }
}
