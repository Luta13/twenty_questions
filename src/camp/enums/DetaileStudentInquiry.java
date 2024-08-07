package camp.enums;

import java.util.ArrayList;
import java.util.List;
import camp.model.*;



public class DetaileStudentInquiry implements StudentInquiry {
    private List<Student> studentStore;
    private List<Score> subjects = new ArrayList<>();

    public DetaileStudentInquiry(List<Student> studentStore){
        this.studentStore=studentStore;
    }
    @Override
    public void inquire(){ //수강생의 상세정보 조회
        System.out.println("================================== \n 수강생 목록을 상세 조회합니다...");
        for(Student stu_inquiry : studentStore){
            System.out.println(stu_inquiry); //ㅏㄱ생 기본 정보와 상태 출력

            System.out.println("상태: "+stu_inquiry.getStudentState());//학생 상태 출력
            System.out.print("과목 목록:");
            for(Subject subject : stu_inquiry.getSubjects()) {//과목 화확인,,
                System.out.print(" "+ subject.getSubjectName());
            }
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }
}
