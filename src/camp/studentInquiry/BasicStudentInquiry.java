package camp.studentInquiry;

import java.util.List;
import camp.model.Student;

public class BasicStudentInquiry implements StudentInquiry{ //인터페이스의 메서드를 구현

    //기본 조회 기능 구현
    private List<Student> studentStore; //수강생 정보를 저장하는 리스트
    public BasicStudentInquiry(List<Student> studentStore){ //생성자
        this.studentStore = studentStore; //매개변수로 받아 클래스의 필드에 할당
        //수강생 목록을 저장하고 있어 조회 시 사용
    }
    @Override
    //실제 동작을 정의
    public void inquire(){ //인터페이스의 메서드는 수강생 목록을 조회하는 기능을 구현
        System.out.println("================================== \n수강생 목록을 조회합니다...");
        for(Student stu_inquiry : studentStore){
            System.out.println(stu_inquiry.toString());
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }
}
