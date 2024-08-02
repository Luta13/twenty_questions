package camp.model;

import camp.enums.ChoiceRankEnum;
import camp.enums.MandatoryRankEnum;

public class Score {
    private String subjectName;
    private int score;
    private Subject subject;
    private ChoiceRankEnum choiceRank;
    private MandatoryRankEnum mandatoryRank;

    public Score(String subjectName, int score, Subject subject) {
        this.subjectName = subjectName;
        this.score = score;
        this.subject = subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ChoiceRankEnum getChoiceRank() {
        return choiceRank;
    }

    public void setChoiceRank(int score) {
        this.choiceRank = ChoiceRankEnum.getRank(score);
    }

    public MandatoryRankEnum getMandatoryRank() {
        return mandatoryRank;
    }

    public void setMandatoryRank(int score) {
        this.mandatoryRank = MandatoryRankEnum.getRank(score);
    }

    @Override
    public String toString() {
        return "Score{" + "subjectName='" + subjectName + '\'' + ", score=" + score + ", subject=" + subject + ", choiceRank=" + choiceRank + ", mandatoryRank=" + mandatoryRank + '}';
    }
}
