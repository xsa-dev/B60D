package dispatchers;

import model.ConsoleHelper;
import model.examles.TextExampl;
import model.example_generators.TextExamplesGenerator;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator1 on 18.05.2017.
 */
public class WorkerWithTextExamples {
    public static void main(String[] args) throws IOException {

        TextExamplesGenerator generator = new TextExamplesGenerator("D:\\tests2\\B60D\\src\\ExamplesTest");
        List<TextExampl> list = generator.getReadingExamples();

        for (TextExampl textExampl : list) {
            System.out.println(textExampl.getQuestion()+"\n");
            System.out.println(textExampl.testAnswer(ConsoleHelper.readWords()));
        }
    }
}
