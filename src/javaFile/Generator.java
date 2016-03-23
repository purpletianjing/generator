package javaFile;

import java.io.*;

import javaFile.bean.QuizItem;
import javaFile.mapper.QuizItemMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
        File[] fList = directory.listFiles();

        for (int i = 0; i < 10; i++) {
            String fileName  = fList[i].getName();
            if (fList[i].isFile() && (fileName.substring(fileName.lastIndexOf(".") + 1)).equals("json")) {
                this.readJson(fList[i], fList[i + 1]);
                System.out.println("this is a json file");
            }
        }
    }

    public void readJson(File jsonFile, File pictureFile) throws IOException, ParseException{
        JSONParser parser = new JSONParser();
        JSONObject logicPuzzle = (JSONObject)parser.parse(new FileReader(jsonFile));

            QuizItem quizItem = new QuizItem();
            String stepsString = (String) logicPuzzle.get("steps_string");
            int count = Integer.parseInt(String.valueOf(logicPuzzle.get("count")));
            String questionZh = (String) logicPuzzle.get("question_zh");
            int stepsLength = Integer.parseInt(String.valueOf(logicPuzzle.get("steps_length")));
            int maxUpdateTimes = Integer.parseInt(String.valueOf(logicPuzzle.get("max_update_times")));
            String answer = String.valueOf(logicPuzzle.get("answer"));
            String descZh = logicPuzzle.get("desc_zh").toString();
            String chartPath = pictureFile.getPath();
            String infoPath = jsonFile.getPath();
            quizItem.setAnswer(answer);
            quizItem.setChartPath(chartPath);
            quizItem.setCount(count);
            quizItem.setDescriptionZh(descZh);
            quizItem.setInfoPath(infoPath);
            quizItem.setMaxUpdateTimes(maxUpdateTimes);
            quizItem.setQuestionZh(questionZh);
            quizItem.setStepsLength(stepsLength);
            quizItem.setStepsString(stepsString);
            this.insertJsonToDatabase(quizItem);
  }

    public void insertJsonToDatabase(QuizItem quizItem) throws IOException{
        String resource = "resources/mybatis/config_mybatis.xml";
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        final QuizItemMapper quizItemMapper = sqlSession
                .getMapper(QuizItemMapper.class);
        quizItemMapper.insertQuizItem(quizItem);
        sqlSession.commit();
        sqlSession.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Generator generator = new Generator();
        File directory = new File("/Users/twer/works/generate-logic-puzzle/logic-puzzle");
        generator.operateDirectory(directory);
        generator.checkJson(directory);
    }

}
