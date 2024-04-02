package com.targetindia.tests;
import com.targetindia.programs.ReverseWords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReverseWordsRWTests {
    @Test
    void shouldGetRWEmptyString(){
        String expected = "";
        String actual = ReverseWords.reverseWords("");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldGetRWOneWord(){
        String expected = "Hello";
        String actual = ReverseWords.reverseWords("Hello");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldGetRWMultiWord(){
        String expected = "World Hello";
        String actual = ReverseWords.reverseWords("Hello World");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldGetRWMLeadTrialSpaces(){
        String expected = " World Hello ";
        String actual = ReverseWords.reverseWords(" Hello World ");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldGetRWMLeadTrialSpaces2(){
        String expected = "  World   Hello ";
        String actual = ReverseWords.reverseWords(" Hello   World  ");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldGetRWMMultiSpaces(){
        String expected = "World   Hello";
        String actual = ReverseWords.reverseWords("Hello   World");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldGetRWMWithSpecialChars(){
        String expected = "Welcome... Hello,";
        String actual = ReverseWords.reverseWords("Hello, Welcome...");
        Assertions.assertEquals(expected, actual);
    }

}
