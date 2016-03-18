package javaFile;

import java.io.File;
import java.nio.file.Paths;

public class Generator {

    public void operateDirectory(final File folder) {
        if (folder.exists() && folder.isDirectory()) {
            System.out.println("this is an existing directory");
        }
    }

    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.operateDirectory(new File("/Users/twer/works/generate-logic-puzzle/seeds"));
    }

}
