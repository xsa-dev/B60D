package users;

/**
 * Created by Administrator1 on 17.05.2017.
 */
public class User {

    public static String todaytd, starttd, endtd;
    public static int e;

    public User(String tdd, String std, String etd, int e) {
        tdd = todaytd;
        std = starttd;
        etd = endtd;
        this.e = e;

    }

//    public stat saveStat(String key) throws Exception {
//        FileOutputStream fileOutputStream = new FileOutputStream("stat" + key + ".dat");
//        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
//        outputStream.writeObject(stat());
//        fileOutputStream.close();
//        outputStream.close();
//        return;
//    }
}
