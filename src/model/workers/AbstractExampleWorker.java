package model.workers;

import model.ConsoleHelper;

import java.util.Date;
import java.util.logging.ConsoleHandler;

/**
 * Created by Administrator1 on 20.05.2017.
 */
public abstract class AbstractExampleWorker implements ExamplesWorkeble {
    protected int considersPoints(int points, Date startDate, Date endDate ){

        long differentsDates = endDate.getTime() - startDate.getTime();
        return (int) ((double)(points) / (differentsDates / 1000) * 100);
    }
}
