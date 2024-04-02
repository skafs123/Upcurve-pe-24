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

        ArrayList<ArrayList<String>>  expectedList = new ArrayList<ArrayList<String>>();
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(arr[0]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[1]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[2]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[3]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[4]));
        expectedList.add(list);


        String wordArr[]
                = { "cat", "dog", "tac", "god", "act","bat","same","xyzpo" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void shouldGetGAWithNoAnagrams(){
        String[][] arr = {{"bat"},{"cat"},{"same"},{"dog"},{"xyzpo"}};

        ArrayList<ArrayList<String>>  expectedList = new ArrayList<ArrayList<String>>();
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(arr[0]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[1]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[2]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[3]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[4]));
        expectedList.add(list);


        String wordArr[]
                = { "cat", "dog", "bat","same","xyzpo" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }
    @Test
    void shouldGetGAWithDiffLength(){
        String[][] arr = {{"bat"},{"catch"},{"same"},{"digging"},{"xyzpogh"}};

        ArrayList<ArrayList<String>>  expectedList = new ArrayList<ArrayList<String>>();
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(arr[0]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[1]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[2]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[3]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[4]));
        expectedList.add(list);


        String wordArr[]
                = { "catch", "digging", "bat","same","xyzpogh" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }
    @Test
    void shouldGetGAWithSpecChar(){
        String[][] arr = {{"dog!"},{"cat?", "tac?", "act?"},{"xyzpo@@"},{"bat"},{"same"},{"god"}};

        ArrayList<ArrayList<String>>  expectedList = new ArrayList<ArrayList<String>>();
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(arr[0]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[1]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[2]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[3]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[4]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[5]));
        expectedList.add(list);


        String wordArr[]
                = { "cat?", "dog!", "tac?", "god", "act?","bat","same","xyzpo@@" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void shouldGetGAWithUpperLower(){
        String[][] arr = {{"ACT"},{"DOG"},{"bat"},{"cat","tac"},{"same"},{"god"},{"xyzpo"}};

        ArrayList<ArrayList<String>>  expectedList = new ArrayList<ArrayList<String>>();
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(arr[0]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[1]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[2]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[3]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[4]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[5]));
        expectedList.add(list);
        list = new ArrayList<String>(Arrays.asList(arr[6]));
        expectedList.add(list);

        String wordArr[]
                = { "cat", "DOG", "tac", "god", "ACT","bat","same","xyzpo" };
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void shouldGetGAWithEmptyArray(){
        String[][] arr = {{""}};

        ArrayList<ArrayList<String>>  expectedList = new ArrayList<ArrayList<String>>();
        ArrayList<String> list =
                new ArrayList<String>(Arrays.asList(arr[0]));
        expectedList.add(list);

        String wordArr[]
                = {""};
        int size = wordArr.length;
        ArrayList<ArrayList<String>> actualList = GroupAnagrams.groupAnagramsTogether(wordArr, size);

        Assertions.assertEquals(expectedList, actualList);
    }

}
