package com.targetindia.programs;

import com.targetindia.utils.KeyboardUtil;

public class CalProg {


      private static void validateInput(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month must be between 1 and 12");
        }
        if (year < 1) {
            throw new IllegalArgumentException("year must be >= 1");
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    private static int processDate(int month,int year,boolean isLeap)
    {

        int[] nTable = {0,31,59,90,120,151,181,212,243,273,304,334};

        //K value  maps for "Fr", "Sa","Su","Mo","Tu","We","Th"
        int[] kTableNonLeap = {0,1,2,3,4,5,6};
        int[] kTableLeap = {1,2,3,4,5,6,0};

        //To convert the K value to DaysTable
        int[] daysIndex = {5,6,0,1,2,3,4};

        if (isLeap)
        {
            for (int i=2; i<12 ; i++) {
                nTable[i] = nTable[i] + 1;
            }
        }

        //k= Remainder{  ( Quotient{Year/4} + year + date + n ) / 7 }

        int nValue = nTable[month-1];

        int kValue = ((year/4) + year + 1 + nValue ) % 7;

        int[] kTable = kTableNonLeap;
        if (isLeap)
        {
            kTable = kTableLeap;
        }

        int index = 0;
        //find the kTable index where the kValue matches
        for(int i=0;i<7;i++)
        {
            if(kTable[i]== kValue) {
                index = i;
                break;
            }
        }

        return daysIndex[index];
    }

    private static void printCal(int month,int offset,boolean isLeap) {
        String[] daysTable = {"Su","Mo","Tu","We","Th","Fr", "Sa",};
        String[] monthsTable = { "JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER" };

        int daysInMonth = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else if (month == 2) {
            if (isLeap) daysInMonth = 29 ;
            else daysInMonth = 28 ;
        }

        System.out.println("MONTH:" + monthsTable[month - 1]);

        for (int i = 0; i < 7; i++) {
            System.out.print("  " + daysTable[i]);
        }

        System.out.println();

        for (int i = 0; i < offset; i++)
            System.out.print("    ");
        for (int i = 1; i <= daysInMonth; i++) {
            System.out.printf("%4d", i);

            if (((i + offset) % 7 == 0)
                    || (i == daysInMonth))
                System.out.println();
        }




    }

    public static void main(String[] args) {

        int m = KeyboardUtil.getInt("enter a value for month: ");
        int y = KeyboardUtil.getInt("enter a value for year: ");
        validateInput(m,y);
        boolean isLeap = isLeapYear(y);

        int offset = processDate(m,y,isLeap);
        printCal(m,offset,isLeap);



    }
}
