package model.workers;

import model.ConsoleHelper;
import model.examles.TextExampl;
import model.example_generators.TextExamplesGenerator;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator1 on 18.05.2017.
 */
public class WorkerWithTextExamples implements ExamplesWorkeble {
    public void launching() throws Exception {
        //эта срока находит этот файл на любой машине
        String str = new File(".").getAbsolutePath().toString().
                replaceAll("\\.$", "src/ExamplesTest").
                replaceAll("\\\\", "/");
//        TextExamplesGenerator generator = new TextExamplesGenerator("D:\\tests2\\B60D\\src\\ExamplesTest");
        TextExamplesGenerator generator = new TextExamplesGenerator(str);
        List<TextExampl> list = generator.getReadingExamples();

        for (TextExampl textExampl : list) {
            ConsoleHelper.writeMessage(textExampl.getQuestion() + "\n");
            //потом фразы брать тз пропперти файлов
            ConsoleHelper.writeMessage(textExampl.testAnswer(ConsoleHelper.readWords()) ? "ok, next question": "bad, new question");
        }
    }
}
