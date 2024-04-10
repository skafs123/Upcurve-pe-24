package com.targetindia.tests;
import com.targetindia.programs.GroupAnagrams;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class GroupAnagramsGATTests {
    @Test
    void shouldGetGAWithAnagrams(){
        String[][] arr = {{"bat"},{"cat","tac","act"},{"same"},{"dog","god"},{"xyzpo"}};

        List<List<String>> expectedList = Arrays.asList(
                Arrays.asList(arr[0]),
                Arrays.asList(arr[1]),
                Arrays.asList(arr[2]),
                Arrays.asList(arr[3]),
                Arrays.asList(arr[4])
        );

        String[] wordArr
                = { "cat", "dog", "tac", "god", "act","bat","same","xyzpo" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void shouldGetGAWithNoAnagrams(){
        String[][] arr = {{"bat"},{"cat"},{"same"},{"dog"},{"xyzpo"}};

        List<List<String>> expectedList = Arrays.asList(
                Arrays.asList(arr[0]),
                Arrays.asList(arr[1]),
                Arrays.asList(arr[2]),
                Arrays.asList(arr[3]),
                Arrays.asList(arr[4])
        );

        String[] wordArr
                = { "cat", "dog", "bat","same","xyzpo" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }
    @Test
    void shouldGetGAWithDiffLength(){
        String[][] arr = {{"bat"},{"catch"},{"same"},{"digging"},{"xyzpogh"}};

        List<List<String>> expectedList = Arrays.asList(
                Arrays.asList(arr[0]),
                Arrays.asList(arr[1]),
                Arrays.asList(arr[2]),
                Arrays.asList(arr[3]),
                Arrays.asList(arr[4])
        );

        String[] wordArr
                = { "catch", "digging", "bat","same","xyzpogh" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }
    @Test
    void shouldGetGAWithSpecChar(){
        String[][] arr = {{"dog!"},{"cat?", "tac?", "act?"},{"xyzpo@@"},{"bat"},{"same"},{"god"}};

        List<List<String>> expectedList = Arrays.asList(
                Arrays.asList(arr[0]),
                Arrays.asList(arr[1]),
                Arrays.asList(arr[2]),
                Arrays.asList(arr[3]),
                Arrays.asList(arr[4]),
                Arrays.asList(arr[5])
        );


        String[] wordArr
                = { "cat?", "dog!", "tac?", "god", "act?","bat","same","xyzpo@@" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void shouldGetGAWithUpperLower(){
        String[][] arr = {{"ACT"},{"DOG"},{"bat"},{"cat","tac"},{"same"},{"god"},{"xyzpo"}};

        List<List<String>> expectedList = Arrays.asList(
                Arrays.asList(arr[0]),
                Arrays.asList(arr[1]),
                Arrays.asList(arr[2]),
                Arrays.asList(arr[3]),
                Arrays.asList(arr[4]),
                Arrays.asList(arr[5]),
                Arrays.asList(arr[6])
        );

        String[] wordArr
                = { "cat", "DOG", "tac", "god", "ACT","bat","same","xyzpo" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void shouldGetGAWithEmptyArray(){

        List<List<String>> expectedList = Arrays.asList(
                Arrays.asList("")
        );

        String[] wordArr
                = {""};
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);
        Assertions.assertEquals(expectedList, actualList);
    }

}
