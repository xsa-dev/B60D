package model.example_generators;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class IntExamlesGenerator implements Serializable{
    public static ArrayList setList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("1+1=?");
        list.add("1*2=?");
        list.add("3/1=?");
        list.add("4-1=?");

        return list;
    }

    public static ArrayList genList(int i) {
        ArrayList<String> list = new ArrayList<>();
        Random r = new Random();
        int f,s,dr;
        char d = 'x';

        for (int j = 0; j <= i ; j++) {
            f = r.nextInt(9);
            s = r.nextInt(9);
            dr = r.nextInt(4);
            ///////
            switch (dr){
                case 1: d = '*'; break;
                case 2: d = '/'; break;
                case 3: d = '+'; break;
                default: d = '-';
            }

            if (s > f) {
                int t = s;
                s = f;
                f = t;
            }

            if (d == '/') {
                if (f == 0) {
                    f++;
                }
                if (s == 0) {
                    s++;
                }
            }

            String o = f + Character.toString(d) + s + "=?";
            list.add(o);
        }
        return list;
    }


    public static ArrayList saveList() throws Exception {
        ArrayList<String> list = new ArrayList<>();

        list.add("1+1=?");
        list.add("1*2=?");
        list.add("3/1=?");
        list.add("4-1=?");

        FileOutputStream fileOutputStream = new FileOutputStream("01.dat");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(list);
        fileOutputStream.close();
        outputStream.close();
        return null;
    }

    public static ArrayList readList() throws Exception {
        ArrayList<String> list = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream("01.dat");
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        Object object = inputStream.readObject();
        inputStream.close();
        fileInputStream.close();

        return (ArrayList) object;
    }///////////////
    /////////
    public static void main (String[] args) {
        genList(10);
    }
}
