package com.targetindia.programs;

import com.targetindia.utils.KeyboardUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentGradeAnalyzer {


    public static  Map<String, Integer> getMapFromCSV( String filePath) throws IOException {

        Path path = Paths.get(filePath);
        if(Files.exists(path)) {
            try (Stream<String> lines = Files.lines(path)) {
                return lines
                        .map(line -> line.split(","))
                        .collect(Collectors.toMap(line -> line[0], line -> Integer.parseInt(line[1])));
            }
        }
        System.out.println("File does not exist");
        return null;
    }

    public static void calcAverage(Map<String, Integer> map)
    {
        // Calculate the average using streams
        double average =  map.entrySet().stream()
                .map(e->e.getValue())
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);
        System.out.printf("Average grade %.2f",average);
        System.out.println();
    }

    public static void calcHighestGrade(Map<String, Integer> map)
    {
        int highestGrade =  map.entrySet().stream()
                .map(e->e.getValue())
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(0);
        System.out.printf("Highest grade %d",highestGrade);
        System.out.println();
    }
    public static void calcLowestGrade(Map<String, Integer> map)
    {
        int lowestGrade =  map.entrySet().stream()
                .map(e->e.getValue())
                .sorted()
                .findFirst()
                .orElse(0);
        System.out.printf("Lowest grade %d",lowestGrade);
        System.out.println();
    }

    public static void calcNumStdPassed(Map<String, Integer> map)
    {
        long numStdPassed =  map.entrySet().stream()
                .map(e->e.getValue())
                .filter(n->n>=60)
                .count();
        System.out.printf("Number of students passed %d",numStdPassed);
        System.out.println();
    }

    public static void calcNumStdFailed(Map<String, Integer> map) {
        long numStdFailed =  map.entrySet().stream()
                .map(e->e.getValue())
                .filter(n->n<60)
                .count();
        System.out.printf("Number of students failed %d",numStdFailed);
        System.out.println();
    }

    public static void main(String[] args) {

        var filename = KeyboardUtil.getString("enter filename to read: ");
        System.out.printf("filename : %s",filename);
        System.out.println();

        try {
                //Map<String, Integer> map = Map.of("John",80, "kavitha", 100,"Rosy",30,"Raj",70);
                Map<String, Integer> map = getMapFromCSV(filename);
                if(map!= null)
                {
                    calcAverage(map);
                    calcHighestGrade(map);
                    calcLowestGrade(map);
                    calcNumStdPassed(map);
                    calcNumStdFailed(map);
                }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
