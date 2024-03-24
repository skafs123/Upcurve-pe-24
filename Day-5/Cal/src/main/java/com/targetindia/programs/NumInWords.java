package com.targetindia.programs;

import java.text.DecimalFormat;


public class NumInWords {

    private static void validateInput(int number) {
        if (number < 1 || number > 999999999) {
            throw new IllegalArgumentException("number must be between 1 and 99 99 99 999");
        }

    }

    private static String convertUptoThousand(int number) {
        String[] tensNames = {
                "",
                " ten",
                " twenty",
                " thirty",
                " forty",
                " fifty",
                " sixty",
                " seventy",
                " eighty",
                " ninety"
        };

        String[] onesNames = {
                "",
                " one",
                " two",
                " three",
                " four",
                " five",
                " six",
                " seven",
                " eight",
                " nine",
                " ten",
                " eleven",
                " twelve",
                " thirteen",
                " fourteen",
                " fifteen",
                " sixteen",
                " seventeen",
                " eighteen",
                " nineteen"
        };

        String convNum;

        if (number % 100 < 20) {
            convNum = onesNames[number % 100];
            number /= 100;
        } else {
            convNum = onesNames[number % 10];
            number /= 10;

            convNum = tensNames[number % 10] + convNum;
            number /= 10;
        }
        if (number == 0) return convNum;
        return onesNames[number] + " hundred" + convNum;
    }

    private static String convert(int number) {

        // pad with "0"
        String mask = "000000000";
        DecimalFormat df = new DecimalFormat(mask);
        String snumber = df.format(number);

        // XXnnnnnnn
        int crores = Integer.parseInt(snumber.substring(0, 2));
        // nnXXnnnnn
        int lakhs = Integer.parseInt(snumber.substring(2, 4));
        // nnnnXXnnn
        int thousands = Integer.parseInt(snumber.substring(4, 6));
        // nnnnnnXXX
        int hundreds = Integer.parseInt(snumber.substring(6, 9));

        String convCrores;
        switch (crores) {
            case 0:
                convCrores = "";
                break;
            case 1:
                convCrores = " one crore ";
                break;
            default:
                convCrores = convertUptoThousand(crores)
                        + " crore ";
        }
        String result = convCrores;

        String convLakhs;
        switch (lakhs) {
            case 0:
                convLakhs = "";
                break;
            case 1:
                convLakhs = " one lakh ";
                break;
            default:
                convLakhs = convertUptoThousand(lakhs)
                        + " lakh ";
        }
        result = result + convLakhs;

        String convThousands;
        switch (thousands) {
            case 0:
                convThousands = "";
                break;
            case 1:
                convThousands = "one thousand ";
                break;
            default:
                convThousands = convertUptoThousand(thousands)
                        + " thousand ";
        }
        result = result + convThousands + convertUptoThousand(hundreds);
        ;

        return result;
        // remove extra spaces!
        //return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }


    private static void processInput(int number) {
        validateInput(number);

        String convNum = convert(number);
        System.out.printf("%d :", number);
        System.out.println(convNum);
    }

    public static void main(String[] args) {

        //int number = KeyboardUtil.getInt("enter a Numeric value between 1 and 99 99 99 999: ");
        processInput(1);
        processInput(24);
        processInput(100);
        processInput(223);
        processInput(1345);
        processInput(9999);
        processInput(56789);
        processInput(234600);
        processInput(1155788);
        processInput(56766888);
        processInput(336677999);
        processInput(100000001);

    }
}



