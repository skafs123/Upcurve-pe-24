package com.targetindia.programs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import com.targetindia.models.Word;
import com.targetindia.models.DupArray;


public class GroupAnagrams {

        // A Java program to group all anagrams together

        // Compare two words. Used in Arrays.sort() for
        // sorting an array of words
        static class compStr implements Comparator<Word> {
            public int compare(Word a, Word b)
            {
                return a.getStr().compareTo(b.getStr());
            }
        }

        // Given a list of words in wordArr[],
        public static ArrayList<ArrayList<String>> groupAnagramsTogether(String wordArr[],
                                          int size)
        {
            // Step 1: Create a copy of all words present
            // in given wordArr. The copy will also have
            // original indexes of words
            DupArray dupArray = new DupArray(wordArr, size);

            // Step 2: Iterate through all words in
            // dupArray and sort individual words.
            int i;
            for (i = 0; i < size; ++i) {
                char[] char_arr
                        = dupArray.getArray()[i].getStr().toCharArray();
                Arrays.sort(char_arr);
                dupArray.getArray()[i].setStr( new String(char_arr));
            }

            // Step 3: Now sort the array of words in
            // dupArray
            Arrays.sort(dupArray.getArray(), new compStr());

            // Step 4: Now all words in dupArray are together,
            // but these words are changed. Use the index
            // member of word struct to get the corresponding
            // original word

            //create List of all the grouped anagrams
            Word[] array = dupArray.getArray();


            ArrayList<ArrayList<String>> outerList = new ArrayList<ArrayList<String>>(5);
            ArrayList<String>  list = new ArrayList<String>(3);

            int k = 0;
            String prevWord  = array[k].getStr();
            int index = array[k].getIndex();
            list.add(wordArr[index]);
            outerList.add(list);

            String nextWord ;
            for (k = 1; k < size; k++)
            {
                nextWord = array[k].getStr();
                index= array[k].getIndex();
                if(prevWord.equals(nextWord))
                {
                    list.add(wordArr[index]);
                }
                else {
                    list = new ArrayList<String>(3) ;
                    outerList.add(list);
                    list.add(wordArr[index]);
                    //initialize previous word
                    prevWord = nextWord;

                }
            }

            return outerList;

        }

        // Driver program to test above functions
        public static void main(String args[])
        {
            String wordArr[]
            //        = { "cat", "dog", "tac", "god", "act","bat","same","xyzpo" };
                    = { "cat", "DOG", "tac", "god", "ACT","bat","same","xyzpo" };
            int size = wordArr.length;
            ArrayList<ArrayList<String>> outerList = groupAnagramsTogether(wordArr, size);
            System.out.print("[");
            for(ArrayList<String> list :outerList ) {
                System.out.print(list);
                System.out.print(" ");
            }
            System.out.println("]");
        }

}
