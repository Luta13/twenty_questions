package camp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private String studentId;
    private String studentName;
    private List<Subject> subjects;
    private Map<Integer, Round> roundSubjectsMap;

    public Student(String seq, String studentName, List<Subject> subjects) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjects = subjects;
        this.roundSubjectsMap = new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }



    public String getStudentName() {
        return studentName;
    }

    public Map<Integer, Round> getRoundSubjectsMap() {
        return roundSubjectsMap;
    }

    public void addRoundSubjectsMap(Round round) {
        roundSubjectsMap.put(round.getRoundNumber(), round);
    }

    // 회차에 알맞은, Round 객체 얻기
    public Round getSubjectsMap(int roundNumber) {
        return roundSubjectsMap.get(roundNumber);
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Student{" + "studentId='" + studentId + '\'' + ", studentName='" + studentName + '\'' + ", subjects=" + subjects + ", roundSubjectsMap=" + roundSubjectsMap + '}';
    }
}
