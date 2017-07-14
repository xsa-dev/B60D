package ru.b60d.model.languages;

import org.springframework.stereotype.Component;
import ru.b60d.model.ConsoleHelper;
import ru.b60d.model.loging.LogerSituations;
import ru.b60d.model.users.ManagerUserSetings;
import ru.b60d.view.ManagerGUIGame;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

@Component
public class LanguageManager {
    private volatile Properties selectedLanguagePhrases = new Properties();
    private volatile LanguageType languageType = LanguageType.RUSSIAN;
    private ManagerGUIGame managerGUIGame;
    private ManagerUserSetings managerUserSetings;
    private static LogerSituations loger = new LogerSituations(LanguageManager.class);
//    public static final String fileNameRussian = ConsoleHelper.getParentPath(LanguageManager.class) + "/RussianLanguagePackage.properties";
//    public static final String fileNameInglish = ConsoleHelper.getParentPath(LanguageManager.class) + "/InglishLanguagePackage.properties";
    public static final String fileNameRussian = ConsoleHelper.getFile("RussianLanguagePackage.properties");
    public static final String fileNameInglish = ConsoleHelper.getFile("InglishLanguagePackage.properties");


    public static enum LanguageType {
        RUSSIAN,
        INGLISH
    }


    public LanguageManager() {
        managerGUIGame = ManagerGUIGame.getManagerGUIGame();
        managerUserSetings = ManagerGUIGame.getManagerUserSetings();
        System.out.println(managerUserSetings);
//        initialLanguage();
    }

    public void initial(){
        managerUserSetings = ManagerGUIGame.getManagerUserSetings();
        initialLanguage();
    }

    private void initialLanguage() {
        String resaltFileName = "";
        String languageInSetings = ManagerGUIGame.getManagerUserSetings().getSeting("setings.language");
        switch ( LanguageType.valueOf(languageInSetings)) {
            case INGLISH:
                resaltFileName = fileNameInglish;
                languageType = LanguageType.INGLISH;
                break;
            case RUSSIAN:
                resaltFileName = fileNameRussian;
                languageType = LanguageType.RUSSIAN;
                break;
        }
        try {
//            selectedLanguagePhrases.load(new FileReader(resaltFileName));
            selectedLanguagePhrases.load(new InputStreamReader(new FileInputStream(resaltFileName), "UTF-8"));
        } catch (IOException e) {
            loger.logError(e);
        }
    }

    public String getPhrase(String namePhrase) {
        String resalt = null;
//java -jar B60D-1-jar-with-dependencies.jar
        try {
            resalt = selectedLanguagePhrases.getProperty(namePhrase);
            System.out.println(resalt);

        } catch (Exception e) {
            loger.logError(e);
        }
        return resalt;
    }

    public void setLanguageType(LanguageType languageTypeRename) {
        if (languageTypeRename != languageType) {
            languageType = languageTypeRename;;
            managerUserSetings.saveSeting("setings.language", languageTypeRename.name());
            initialLanguage();
        }
    }

    public LanguageType getLanguageType() {
        return languageType;
    }
}
//    public static void main(String[] args) {
//        LanguageManager languageManager = new LanguageManager(null);
//        languageManager.setLanguageType(LanguageType.INGLISH);
//    }
//            resalt = new String(selectedLanguagePhrases.getProperty(namePhrase).getBytes(), "UTF-8");
//            System.out.println(resalt);
//            resalt = new String(selectedLanguagePhrases.getProperty(namePhrase).getBytes("WINDOWS-1252"), "UTF-8");
//            System.out.println(resalt);
//variants:::
//            new String(selectedLanguagePhrases.getProperty(namePhrase).getBytes("WINDOWS-1252"), "UTF-8");
//new String(selectedLanguagePhrases.getProperty(namePhrase).getBytes("WINDOWS-1252"), "utf-8")