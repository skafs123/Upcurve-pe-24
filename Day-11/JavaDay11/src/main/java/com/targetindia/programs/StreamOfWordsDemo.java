package com.targetindia.programs;

import com.targetindia.utils.KeyboardUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;



public class StreamOfWordsDemo {

    static ArrayList<String> words = new ArrayList<>(10);
    public static void readFile() {

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
                String[] temp = str.split("\\s+");
                words.addAll(List.of(temp));
            }
            sc.close();
            System.out.println();

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

    public static  void processWords()
    {

        //int maxSize = 10;
        int maxSize = KeyboardUtil.getInt("Enter the value of N:");
        System.out.printf("Top %d most frequent words",maxSize);
        System.out.println();


        //String[] words= {"One", "Two", "three", "TWO"};
        //Stream.of(words)

        words.stream()
                .collect(Collectors.groupingBy(str->str.toLowerCase(Locale.ROOT),Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                .limit(maxSize)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        readFile();
        if(words.size()!=0)
            processWords();
    }
}
