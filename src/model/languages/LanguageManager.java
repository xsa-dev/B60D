package model.languages;

import model.ConsoleHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * теперь тут создать обьект проперти и при смене языка будет загружать его из нового файла соотвецтвующего языку
 */
public class LanguageManager {
    private volatile Properties selectedLanguagePhrases = new Properties();
    private volatile LanguageType languageType = LanguageType.RUSSIAN;
    public static final String fileNameRussian = ConsoleHelper.getParentPath(LanguageManager.class) + "/RussianLanguagePackage.properties";
    public static final String fileNameInglish = ConsoleHelper.getParentPath(LanguageManager.class) + "/InglishLanguagePackage.properties";

    public static enum LanguageType {
        RUSSIAN,
        INGLISH
    }

    public LanguageManager() {
        initialLanguage();
        System.out.println("in constructor end");
    }

    private void initialLanguage(){
        String resaltFileName = "";
        switch (languageType){
            case INGLISH: resaltFileName = fileNameInglish;
                System.out.println("INGLISH"); break;
            case RUSSIAN: resaltFileName = fileNameRussian;
                System.out.println("RUSSIAN"); break;
        }
        try {
            selectedLanguagePhrases.load(new FileReader(resaltFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPhrase(String namePhrase){
        return selectedLanguagePhrases.getProperty(namePhrase);
    }

    public void setLanguageType(LanguageType languageTypeRename){
        System.out.println("before change language");
        if (languageTypeRename != languageType) {
            System.out.println("changing... language");
            languageType = languageTypeRename;
            initialLanguage();
            System.out.println("suchesfull change alnguage");
        }
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public static void main(String[] args) {
        LanguageManager languageManager = new LanguageManager();
        System.out.println(languageManager.getPhrase("game.start"));
        languageManager.setLanguageType(LanguageType.INGLISH);
        System.out.println(languageManager.getPhrase("game.start"));
    }
}
