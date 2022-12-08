package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the file path:");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        List<String> lines = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNext()) {
                lines.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        System.out.println(mirrorLines(lines));
    }

    private static String mirrorLines(List<String> lines) {
        int longestLine = lines.stream().max(Comparator.comparing(String::length)).get().length();

        StringBuilder result = new StringBuilder();
        for (String line: lines) {
            String currentLine = line;
            if (line.length() < longestLine) {
                currentLine = currentLine.concat(" ".repeat(longestLine - line.length()));
            }
            result.append(String.format("%s | %s%n", currentLine, reverseLine(currentLine)));
        }

        return result.toString();
    }

    private static String reverseLine(String line) {
        StringBuilder result = new StringBuilder();

        for (int i = line.length() - 1; i >= 0; i--) {
            char currentChar = line.charAt(i);
            switch (currentChar) {
                case '<' -> currentChar = '>';
                case '>' -> currentChar = '<';
                case '/' -> currentChar = '\\';
                case '\\' -> currentChar = '/';
                case '(' -> currentChar = ')';
                case ')' -> currentChar = '(';
                case '[' -> currentChar = ']';
                case ']' -> currentChar = '[';
                case '{' -> currentChar = '}';
                case '}' -> currentChar = '{';
                default -> {
                }
            }
            result.append(currentChar);
        }

        return result.toString();
    }
}