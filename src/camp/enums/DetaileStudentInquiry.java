package camp.enums;

import java.util.List;
import camp.model.Student;
import camp.model.Subject;


public class DetaileStudentInquiry implements StudentInquiry {
    private List<Student> studentStore;

    public DetaileStudentInquiry(List<Student> studentStore){
        this.studentStore=studentStore;
    }
    @Override
    public void inquire(){ //수강생의 상세정보 조회
        System.out.println("================================== \n 수강생 목록을 상세 조회합니다...");
        for(Student stu_inquiry : studentStore){
            System.out.println(stu_inquiry);

            System.out.println("상태: "+stu_inquiry.getStudentStatus());
            for(Subject subject : stu_inquiry.getSubjects()){
                System.out.println(subject.toString());
            }
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }





}
