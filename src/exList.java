import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 09.05.2017.
 */
public class exList implements Serializable {
    public static ArrayList setList() {
        ArrayList<String> list = new ArrayList<>();

        list.add("1+1=?");
        list.add("1*2=?");
        list.add("3/1=?");
        list.add("4-1=?");

        return list;
    }

    public static void main (String[] args) {
    }
}
