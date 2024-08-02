package camp.model;

public class Score {
    private String subjectName;
    private int score;
    private Subject subject;

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

    @Override
    public String toString() {
        return "Score{" + "subjectName='" + subjectName + '\'' + ", score=" + score + ", subject=" + subject + '}';
    }
}
