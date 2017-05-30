package model.languages;

import model.ConsoleHelper;
import model.users.ManagerUserSetings;
import view.ManagerGUIGame;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * теперь тут создать обьект проперти и при смене языка будет загружать его из нового файла соотвецтвующего языку
 */
public class LanguageManager {
    private volatile Properties selectedLanguagePhrases = new Properties();
    private volatile LanguageType languageType = LanguageType.RUSSIAN;
    private ManagerGUIGame managerGUIGame;
    private ManagerUserSetings managerUserSetings;

    public static final String fileNameRussian = ConsoleHelper.getParentPath(LanguageManager.class) + "/RussianLanguagePackage.properties";
    public static final String fileNameInglish = ConsoleHelper.getParentPath(LanguageManager.class) + "/InglishLanguagePackage.properties";

    public static enum LanguageType {
        RUSSIAN,
        INGLISH
    }

    public LanguageManager(ManagerGUIGame managerGUIGame) {
        this.managerGUIGame = managerGUIGame;
        managerUserSetings = managerGUIGame.getManagerUserSetings();
        initialLanguage();
    }

    private void initialLanguage() {
        String resaltFileName = "";
        String languageInSetings = managerUserSetings.getSeting("setings.language");
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
            selectedLanguagePhrases.load(new FileReader(resaltFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPhrase(String namePhrase) {
        return selectedLanguagePhrases.getProperty(namePhrase);
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

    public static void main(String[] args) {
        LanguageManager languageManager = new LanguageManager(null);
        languageManager.setLanguageType(LanguageType.INGLISH);
    }
}
