package model;

import java.util.ArrayList;

/**
 * Created by Administrator1 on 24.05.2017.
 */
public class ProcesesCloser {
    private static ArrayList<AutoCloseable> operations = new ArrayList<>();

    public static void putProcess(AutoCloseable ob){
        operations.add(ob);
    }

    public static void deleteOPeration(Object ob){
        operations.remove(ob);
    }

    public static void closeAllPoceses(){
        operations.trimToSize();
        for (AutoCloseable operation : operations) {
            try {
                operation.close();
                System.out.println(operation.getClass().getSimpleName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        operations.clear();
    }

    public static void closeProces(AutoCloseable ob){
        try {
            ob.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
