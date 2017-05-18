package model.example_generators;

import model.examles.TextExampl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Administrator1 on 18.05.2017.
 */

/**
 * тесты находяться в одном файле разделенные "-----------"
 * в  них есть вопрос и ответ разделенные "************"
 */
public class TextExamplesGenerator {
    private String path;

    public TextExamplesGenerator(String path) throws FileNotFoundException {
        this.path = path;
    }

    public List getReadingExamples() throws IOException {
        List<TextExampl> result = new ArrayList<>();

        String  allText = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
        allText = allText.replaceAll("[\\n\\r]", "");
        String[] examples = allText.toString().split("-{5,15}");

        for (String example : examples) {
            String[] temp = example.split("\\*{5,15}");
            result.add(new TextExampl(temp[0], temp[1]));
        }
        return result;
    }
}
//        readerExamplesFromFile = new BufferedReader(new FileReader(path));

//        StringBuilder allText = new StringBuilder();
//        while (readerExamplesFromFile.ready()) {
//            allText.append(readerExamplesFromFile.readLine());
//        }