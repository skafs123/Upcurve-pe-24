package com.targetindia.programs;
import com.targetindia.models.LinesInFile;
import com.targetindia.utils.KeyboardUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;


public class TextFileAnalyzer {
    public static int numOfLines = 0;
    public static LinesInFile[] lines=null;

    public static void readFile() {

    /* The first part of this function gets the file name from the user.
       Reads the file using scanner to get the number of lines in the file.
       An array of objects ( LinesInFile ) is initialized using this numOfLines.
       In the second parse of the input file, this array is filled with
       objects(LinesInFile) created using the Strings obtained from the input file.
     */

        var filename = KeyboardUtil.getString("enter filename to read: ");
        Scanner sc = null;
        try  {
            // read each line and
            // count number of lines
            File file = new File(filename);
            sc = new Scanner(file);

            System.out.println("Contents of the file:");

            while(sc.hasNextLine()) {
                 String str = sc.nextLine();
                 System.out.println(str);
                 numOfLines++;
            }
            System.out.println();
            System.out.println("Total Number of Lines: " + numOfLines );
            sc.close();
            sc = new Scanner(file);

            lines = new LinesInFile[numOfLines];
            int lineNo = 0;

            while(sc.hasNextLine()) {
                int wordCount =0;

                String str = sc.nextLine();
                lineNo++;
                LinesInFile lineObj= new LinesInFile(lineNo,str);
                lines[lineNo-1] = lineObj;


            }
            /*if( numOfLines > 0 ) // this means the file is not empty
                System.out.println(Arrays.toString(lines));*/

        }
        catch (FileNotFoundException e) {
            System.out.println("sorry, the given file doesn't exist");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (sc  != null) {
                try {
                    sc.close();
                } catch (Exception e) {
                    System.out.println("something wrong while closing the file");
                }
            }
        }

    }

    public static void getTheLongestLine()
    {
        //Print the longest line along with line no

        int maxLen = 0;
        LinesInFile finalLineObj = lines[0];
        for(LinesInFile lineObj  : lines )
        {
            int len = lineObj.getSLine().length();
            if( maxLen <= len ) {
               maxLen = len;
               finalLineObj = lineObj;

            }
        }

        System.out.println("Longest Line:");
        System.out.printf("Line No %d : %s\n",finalLineObj.getLineNo(), finalLineObj.getSLine());

    }
    public static void getTheShortestLine()
    {
        //Print the shortest line along with line no
        int minLen = 0;
        LinesInFile finalLineObj = lines[0];
        for(LinesInFile lineObj  : lines )
        {
            int len = lineObj.getSLine().length();
            if( minLen >= len ) {
                minLen = len;
                finalLineObj = lineObj;
            }
        }

        System.out.println("Shortest Line:");
        System.out.printf("Line No %d : %s\n",finalLineObj.getLineNo(), finalLineObj.getSLine());

    }

    public static void getWordCount() {
        //Get the word count for each line
        System.out.println("Word Count for each Line:");
        for(LinesInFile lineObj  : lines )
            System.out.printf("Line No %d : %d words\n",lineObj.getLineNo(), lineObj.getWordCount());
    }
    public static void sortWordCount() {
        //Sort the lines in a Desc order based on word count

        List<LinesInFile> list = Arrays.asList(lines);
        list.sort((o1, o2) -> o1.compareTo(o2));

        System.out.println("Sorted Word Count :");
        for(LinesInFile lineObj  : list )
            System.out.printf("Line No %d : %d words\n",lineObj.getLineNo(), lineObj.getWordCount());
    }
    public static void sortWordCountUseArray() {
        //This is same as sortWordCount except array is used in place of a list

        //Sort the lines in a Desc order based on word count
        int tempArr[] = new int[numOfLines];

        for(int i=0; i< lines.length; i++  ) {
            tempArr[i] = lines[i].getWordCount();
        }

        System.out.println("Sorted Word Count :");
        for(int wordCount : tempArr )
            System.out.printf("%d words\n",wordCount );
    }


    public static void main(String[] args) {

        try {

            readFile();

            // if the file is not empty
            if (numOfLines > 0) {
                getTheLongestLine();
                getTheShortestLine();
                getWordCount();
                sortWordCount();
                sortWordCountUseArray();

            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}

