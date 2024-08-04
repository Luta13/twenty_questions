package camp.Test;

import java.util.List;
import java.util.Map;

class StudentTest {
    private final String studentId;
    private final String studentName;
    private final List<SubjectTest> subjects;

    public StudentTest(String studentId, String studentName, List<SubjectTest> subjects) {
        this.subjects = subjects;
        this.studentName = studentName;
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<SubjectTest> getSubjects() {
        return subjects;
    }


    @Override
    public String toString() {
        return "StudentTest{" + "studentId='" + studentId + '\'' + ", studentName='" + studentName + '\'' + ", subjects=" + subjects + '}';
    }
}
