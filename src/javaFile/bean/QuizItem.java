package javaFile.bean;

public class QuizItem {
    String stepsString;
    int count;
    String questionZh;
    int stepsLength;
    int maxUpdateTimes;
    String answer;
    String descriptionZh;
    String chartPath;
    String infoPath;

    public void setInfoPath(String infoPath) {
        this.infoPath = infoPath;
    }

    public void setChartPath(String chartPath) {
        this.chartPath = chartPath;
    }

    public void setDescriptionZh(String descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMaxUpdateTimes(int maxUpdateTimes) {
        this.maxUpdateTimes = maxUpdateTimes;
    }

    public void setStepsLength(int stepsLength) {
        this.stepsLength = stepsLength;
    }

    public void setQuestionZh(String questionZh) {
        this.questionZh = questionZh;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStepsString(String stepsString) {
        this.stepsString = stepsString;
    }
}
