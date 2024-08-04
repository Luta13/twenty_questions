package camp.Test;

import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";
    public static StudentTest[] initData(){
        List<SubjectTest> subjectList = List.of(
                new SubjectTest("1","과학",SUBJECT_TYPE_MANDATORY),
                new SubjectTest("2","지리",SUBJECT_TYPE_CHOICE),
                new SubjectTest("3","수학",SUBJECT_TYPE_MANDATORY),
                new SubjectTest("4","생물",SUBJECT_TYPE_CHOICE),
                new SubjectTest("5","국어",SUBJECT_TYPE_MANDATORY),
                new SubjectTest("6","사회",SUBJECT_TYPE_CHOICE),
                new SubjectTest("7","영어",SUBJECT_TYPE_MANDATORY)
        );

        StudentTest[] student1s = new StudentTest[3];
        for ( int i=0; i<3; i++ ) {
            int randNumber = new Random().nextInt(7) + 1;
            List<SubjectTest> randomList = new ArrayList<>();
            for ( int j=0; j<randNumber; j++ ) {
                randomList.add(subjectList.get(j));
            }
            student1s[i] = new StudentTest(String.valueOf(i+1),"test" + (i+1),randomList);
        }

        return student1s;
    }
}
