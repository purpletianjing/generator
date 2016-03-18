package javaFile;

import java.io.*;

import javaFile.bean.User;
import javaFile.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
            User user = new User();
            JSONObject person = (JSONObject) o;
            String password = (String) person.get("password");
            int createDate = Integer.parseInt(String.valueOf(person.get("createDate")));
            String mobilePhone = (String) person.get("mobilePhone");
            String email = (String) person.get("email");
            user.setEmail(email);
            user.setPassword(password);
            user.setMobilePhone(mobilePhone);
            user.setCreateDate(createDate);
            this.insertJsonToDatabase(user);

            System.out.println( "password: " + password + ", createDate: " + createDate + ", mobilePhone" + mobilePhone + ", email" + email);
        }
  }

    public void insertJsonToDatabase(User user) throws IOException{
        String resource = "resources/mybatis/config_mybatis.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Generator generator = new Generator();
        File directory = new File("/Users/twer/works/generate-logic-puzzle/seeds");
        generator.operateDirectory(directory);
        generator.checkJson(directory);

    }

}
