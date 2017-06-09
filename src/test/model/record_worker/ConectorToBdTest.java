package model.record_worker;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator1 on 22.05.2017.
 */
public class ConectorToBdTest {
    @Test
    public void logIn() throws Exception {
        ConectorToBd conectorToBd = new ConectorToBd();
        if ( !conectorToBd.logIn("dir31", "1234"))
            conectorToBd.signUp("dir31", "1234");

        assertTrue( conectorToBd.logIn("dir31", "1234"));
        conectorToBd.deleteUser("dir31", "1234");
        conectorToBd.closeConection();
    }

    @Test
    public void signUp() throws Exception {
        ConectorToBd conectorToBd = new ConectorToBd();
        if ( conectorToBd.logIn("dir32", "1234"))
            conectorToBd.deleteUser("dir32", "1234");

        assertTrue( conectorToBd.signUp("dir32", "1234"));
        conectorToBd.closeConection();
    }

    @Test
    public void deleteUser() throws Exception {
        ConectorToBd conectorToBd = new ConectorToBd();
        if ( !conectorToBd.logIn("dir33", "1234")) {
            conectorToBd.signUp("dir33", "1234");
        }

        assertTrue( conectorToBd.deleteUser("dir33", "1234"));
        conectorToBd.signUp("dir33", "1234");
        conectorToBd.closeConection();
    }

    @Test
    public void writeRecords() throws Exception {
        ConectorToBd conectorToBd = new ConectorToBd();
        if ( !conectorToBd.logIn("dir34", "1234")){
            conectorToBd.signUp("dir34", "1234");
        }
        conectorToBd.writeRecords(992);
        assertTrue( conectorToBd.readRecords().contains("dir34|||992"));
        conectorToBd.closeConection();
    }

    @Test
    public void readRecords() throws Exception {
        ConectorToBd conectorToBd = new ConectorToBd();
        if ( ! conectorToBd.logIn("dir36", "1234")){
            conectorToBd.signUp("dir36", "1234");
        }
        assertNotNull( conectorToBd.readRecords());
        conectorToBd.closeConection();
    }

//    @Test
//    public void entranceManager() throws Exception {
//    }//рийдеться в консоле менеджере подменять буфферед риадер( опять) и в него ставить стрингбуилдер

    @Test
    public void isConecting() throws Exception {
        ConectorToBd conectorToBd = new ConectorToBd();
        if ( conectorToBd.logIn("dir3", "1234"))  conectorToBd.signUp("dir3", "1234");
        assertTrue(conectorToBd.isConecting());
        conectorToBd.closeConection();
    }

}