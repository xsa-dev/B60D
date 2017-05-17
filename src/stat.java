import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by xsd on 12.05.2017 with love.
 * :)
 *
 * @author xsd
 * @author alekseysavin.com
 * @version 0.1
 */
public class stat {

    public static String todaytd, starttd, endtd;
    public static int e;

    public stat(String tdd, String std, String etd, int e) {
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
