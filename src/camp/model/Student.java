package camp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private String studentId;
    private String studentName;
    private List<Subject> subjects;
    private Map<Integer, Round> roundSubjectsMap;

    private String studentState;

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

    public void setStudentName(String studentName){
        this.studentName = studentName;
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
    public void setSubjects(String seq, String subjectName, String subjectType ) {
        Subject newSubject = new Subject(seq, subjectName, subjectType);
        this.subjects.add(newSubject);
    }
    @Override
    public String toString() {
        return "Student{" + "studentId='" + this.studentId + '\'' + ", studentName='" + this.studentName + '\'' + ", subjects=" + this.subjects;
    }

    public void setStudentState(String state){
        this.studentState = state;
    }

    public String getStudentState(){
        return this.studentState;
    }

}
