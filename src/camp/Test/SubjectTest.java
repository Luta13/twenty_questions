package camp.Test;

class SubjectTest {
    private String subjectId;
    private String subjectName;
    private String subjectType;

    public SubjectTest(String subjectId, String subjectName, String subjectType) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    @Override
    public String toString() {
        return "Subject1{" + "subjectId='" + subjectId + '\'' + ", subjectName='" + subjectName + '\'' + ", subjectType='" + subjectType + '\'' + '}';
    }
}
