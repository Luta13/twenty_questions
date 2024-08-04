package camp.Test;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        StudentTest[] studentTests = Util.initData();
        StudentTest firstStudent = studentTests[0];
        StudentTest secondStudent = studentTests[1];
        StudentTest thirdStudent = studentTests[2];

        //1회차 과정
        SubjectTest subject1 = new SubjectTest("1",firstStudent.getSubjects().getFirst().getSubjectName(),firstStudent.getSubjects().getFirst().getSubjectType());
        ScoreTest score1 = new ScoreTest(firstStudent.getSubjects().getFirst().getSubjectName(),90,subject1);
        Map<String,ScoreTest> roundMap1 = new HashMap<>();
        roundMap1.put(score1.getSubjectName(),score1);
        RoundTest roundTest1 = new RoundTest();
        roundTest1.addRoundMapList(roundMap1);














    }
}





