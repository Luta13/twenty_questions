package camp.Test;

import camp.enums.ChoiceRankEnum;
import camp.enums.MandatoryRankEnum;

class ScoreTest {
    private String subjectName;
    private int score;
    private SubjectTest subject;
    private ChoiceRankEnum choiceRank;
    private MandatoryRankEnum mandatoryRank;

    public ScoreTest(String subjectName, int score, SubjectTest subject) {
        this.subjectName = subjectName;
        this.score = score;
        this.subject = subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getScore() {
        return score;
    }

    public SubjectTest getSubject() {
        return subject;
    }

    public ChoiceRankEnum getChoiceRank() {
        return choiceRank;
    }

    public MandatoryRankEnum getMandatoryRank() {
        return mandatoryRank;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSubject(SubjectTest subject) {
        this.subject = subject;
    }

    public void setChoiceRank(ChoiceRankEnum choiceRank) {
        this.choiceRank = choiceRank;
    }

    public void setMandatoryRank(MandatoryRankEnum mandatoryRank) {
        this.mandatoryRank = mandatoryRank;
    }

    @Override
    public String toString() {
        return "Score1{" + "subjectName='" + subjectName + '\'' + ", score=" + score + ", subject=" + subject + ", choiceRank=" + choiceRank + ", mandatoryRank=" + mandatoryRank + '}';
    }
}
