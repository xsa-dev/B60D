package ru.b60d.model.example_generators;

import ru.b60d.model.examles.TextExampl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<TextExampl> getReadingExamples(String path) {

        List<TextExampl> result = new ArrayList<>();

        String allText = null;
        try {
            allText = new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
            System.out.println(Charset.defaultCharset());
        } catch (IOException e) { e.printStackTrace();}
        allText = allText.replaceAll("[\\n\\r]", "");
        String[] examples = allText.split("-+?\\+");
        System.out.println(Arrays.toString(examples));

        for (String example : examples) {
            String[] temp = example.split("\\*+?\\+");//{5,15}");
            result.add(new TextExampl(temp[0], temp[1]));
        }
        return result;
    }

}