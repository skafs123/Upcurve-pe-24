package com.targetindia.programs;

public class ReverseWords {

       // Method to reverse words of a String
        private static String reverseWords(String str)
        {
            String[] temp = str.split("\\s+");
            String result = "";

            // Iterate over the temp array and store
            // the string in reverse order.
            for (int i = 0; i < temp.length; i++) {
                if (i == temp.length - 1)
                    result = temp[i] + result;
                else
                    result = " " + temp[i] + result;
            }
            return result;
        }


        public static void main(String[] args) {
            String s1 = "Welcome to Java Programming";
            System.out.println(s1);
            System.out.println(reverseWords(s1));

            String s2 = "These example programs helps  you  to  learn java";
            System.out.println(s2);
            System.out.println(reverseWords(s2));

            String s3= "We are in the process of learning java using an IDE";
            System.out.println(s3);
            System.out.println(reverseWords(s3));

            String s4 = "Hopefully   we will become professional programmers by the end of it";
            System.out.println(s4);
            System.out.println(reverseWords(s4));

            String s5 = "Today we have to complete 4 programs";
            System.out.println(s5);
            System.out.println(reverseWords(s5));

        }
}
