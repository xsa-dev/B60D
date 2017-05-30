package model.users;

import model.ConsoleHelper;
import view.ManagerGUIGame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ManagerUserSetings {
    private Properties fileUserSetings;
    private  String pathToSetingFile;
    private ManagerGUIGame managerGUIGame;

    public ManagerUserSetings(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
        this.fileUserSetings = new Properties();
        pathToSetingFile = ConsoleHelper.getParentPath(ManagerUserSetings.class) + "/setings.properties";
        try {
            fileUserSetings.load(new FileReader(pathToSetingFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSeting(String nameSeting){
        return fileUserSetings.getProperty(nameSeting);
    }

    public void saveSeting(String nameSeting, String newValue){
        fileUserSetings.setProperty(nameSeting, newValue);
        try {
            fileUserSetings.save(new FileOutputStream(pathToSetingFile),null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
