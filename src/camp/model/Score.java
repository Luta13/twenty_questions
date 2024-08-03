package camp.model;

public class Score {
    private String scoreId; // 스코어 아이디(스코어 식별)
    private int times; // 회차
    private String subjectId; // 과목 아이디(과목 식별)
    private String studentId; // 수강생 아이디(수강생 식별)
    private int[] scores = new int[10]; // 특정 수강생의 특정 과목의 각 회차 점수 배열
    private String Grade; // 등급


//    public Score(String seq) {
//        this.scoreId = seq;
//    }

    public Score(String scoreId, String studentId, String subjectId, int times, int score) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.times = times;
        if (times >= 1 && times <= scores.length) {
            this.scores[times - 1] = score;
        }

    }

    // 사용
    public String getScoreId() {
        return scoreId;
    }

    // 사용
    public int getTimes() {
        return times;
    }

    // 사용
    public String getSubjectId() {
        return subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getGrade() {
        return Grade;
    }

    public void updateScore(int times, int score) {
        this.scores[times - 1] = score;
    }
}

