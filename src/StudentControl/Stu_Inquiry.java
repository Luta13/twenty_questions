package StudentControl;

public class Stu_Inquiry {
    private String studentId;
    private String studentName;

    public Stu_Inquiry(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // Getter
    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId=studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName=studentName;
    }

    @Override
    public String toString(){
        return "(수강생)  ID: " + studentId+" / 이름 : "+studentName;
    }

}
