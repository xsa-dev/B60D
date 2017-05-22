package model.workers;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator1 on 22.05.2017.
 */
public class WorkerWithTextExamplesTest {
    @Test
    public void launching() throws Exception {
//        WorkerWithTextExamples workerWithTextExamples = new WorkerWithTextExamples();
//        int res = workerWithTextExamples.launching();
//        assertEquals(800, res);

    }

    @Test
    public void considersPoints() throws Exception {
        WorkerWithTextExamples workerWithTextExamples = new WorkerWithTextExamples();
        Date  startDate = new Date();
        Thread.sleep(1000);
        Date endDate = new Date();
        int res = workerWithTextExamples.considersPoints(8, startDate, endDate);
        assertEquals(800, res);
    }
}