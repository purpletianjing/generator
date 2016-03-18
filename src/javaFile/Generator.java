package javaFile;

import java.io.File;

public class Generator {

    public void operateDirectory(final File directory) {
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("this is an existing directory");
        }
    }

    public void checkJson(final File directory) {
        File[] fList = directory.listFiles();

        for (File file : fList) {
            String fileName  = file.getName();
            if (file.isFile() && (fileName.substring(fileName.lastIndexOf(".") + 1)).equals("json")) {
                System.out.println("this is a json file");
            }
        }
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        File directory = new File("/Users/twer/works/generate-logic-puzzle/seeds");
        generator.operateDirectory(directory);
        generator.checkJson(directory);
    }

}
