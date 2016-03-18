package javaFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Generator {

    public void operateDirectory(final File directory) {
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("this is an existing directory");
        }
    }

    public void checkJson(final File directory) throws IOException, ParseException {
        Generator generator = new Generator();
        File[] fList = directory.listFiles();

        for (File file : fList) {
            generator.readJson(file);
            String fileName  = file.getName();
            if (file.isFile() && (fileName.substring(fileName.lastIndexOf(".") + 1)).equals("json")) {
                System.out.println("this is a json file");
            }
        }
    }

    public void readJson(File jsonFile) throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray)parser.parse(new FileReader(jsonFile));

        for (Object o : jsonArray) {
            JSONObject person = (JSONObject) o;
            long id = (Long) person.get("id");
            String name = (String) person.get("name");
            long age = (Long) person.get("age");
            String gender = (String) person.get("gender");
            String email = (String) person.get("email");

            System.out.println("name: " + name + ", age: " + age + ", gender" + gender + ", email" + email);
        }
  }

    public static void main(String[] args) throws IOException, ParseException {
        Generator generator = new Generator();
        File directory = new File("/Users/twer/works/generate-logic-puzzle/seeds");
        generator.operateDirectory(directory);
        generator.checkJson(directory);
    }

}
