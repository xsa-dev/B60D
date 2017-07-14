package ru.b60d.model;

import ru.b60d.model.loging.LogerSituations;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class InitializerResource {
    public static final String WORK_DIRECTORY = ConsoleHelper.WORK_DIRECTORY;
    private static LogerSituations loger = new LogerSituations(InitializerResource.class);
    private static final String JAR_NAME = "B60D";

    public static void writeResourceToWorkDirectoryNoJar() {

        File path1 = new File(new File(".").getAbsolutePath().replaceAll("\\\\.$", "/src/main/resources").replaceAll("\\\\", "/"));
        if (new File(WORK_DIRECTORY).exists()) {
            System.out.println(WORK_DIRECTORY + " EXIST");
            return;
        }
                for (String o : path1.list()) {
                    if (testContinueDirectory(o)) {
                        continue;
                    }
                    System.out.println(o);
                    singleWriteResource1(o);
                }

    }

    public static void writeResourceToWorkDirectory() {
        if (new File(WORK_DIRECTORY).exists()) {
            System.out.println(WORK_DIRECTORY + " EXIST");
            return;
        }
        String name = "";
        try {
            File tempFile = new File(new File(".").getAbsolutePath().replaceAll("\\\\.$", "/"+ JAR_NAME+".jar").replaceAll("\\\\", "/"));
            if (!tempFile.exists()) {
                writeResourceToWorkDirectoryNoJar();
                return;
                }
            name = tempFile.getName();//new File(".").getAbsolutePath().replaceAll("\\\\.$", "/B60D.jar").replaceAll("\\\\", "/");


            System.out.println("file name: " + name);
            ZipInputStream zis = new ZipInputStream(new FileInputStream(name));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                if (name.contains("ru/b60d/controllers") | name.contains("ru/b60d/view") | name.contains("model")
                        | name.contains("org") | name.contains("com") | name.contains("junit")
                        | name.contains("META-INF") | name.contains("edu") | name.contains("javafx")
                        | name.contains("licenses") | name.contains("javax") | name.contains("de")) {
                    continue;
                }
                if (name.endsWith("/")) {
                    continue;
                }
                String tempName = ConsoleHelper.WORK_DIRECTORY + "/" + name;

                File res = new File(tempName);
                res.getParentFile().mkdirs();
                System.out.println(res);
                File resF = new File(ConsoleHelper.WORK_DIRECTORY + "/" + name);
//                (resF = new File(ConsoleHelper.WORK_DIRECTORY + "/" + name)).createNewFile();
                resF.createNewFile();
                FileOutputStream writer = new FileOutputStream(tempName);
                byte[] buffer = new byte[2048];
                int count = 0;
                while ((count = zis.read(buffer)) > -1) {
                    writer.write(buffer, 0, count);
                }

                writer.close();
            }
            zis.close();
        } catch (Exception e) {
            loger.logError(e);
        }
    }

    public static void singleWriteResource1(String path) {
        System.out.println(path);
        try {
            File path1 = new File(ConsoleHelper.getFileForWritingInWorkerHolder(path));
            if (path1.isDirectory()) {
                for (String o : path1.list()) {
                    singleWriteResource1(path + "/" + o);
                }
            } else {
                String tempPath = WORK_DIRECTORY + "/" + path;
                if (new File(tempPath).exists()) {
                    System.out.println(tempPath + " EXIST");
                    return;
                }
                new File(tempPath).getParentFile().mkdirs();//.substring(0, tempPath.lastIndexOf("."))).getParentFile().mkdirs());
                BufferedReader reader = ConsoleHelper.getBufferedReaderForIntoResources(path);
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath));
                while (reader.ready()) {
                    writer.write(reader.readLine());
                    writer.newLine();
                }
                reader.close();
                writer.close();
            }
        } catch (Exception e) {
            loger.logError(e);
        }
    }

    public static boolean testContinueDirectory(String str) {
        if (str.contains("ru/b60d/controllers") || str.contains("ru/b60d/view") || str.contains("model")) {
            System.out.println("TESTS RETURNIMG TRUE");
            return true;
        }
        return false;
    }
}