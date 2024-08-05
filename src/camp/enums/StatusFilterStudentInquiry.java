package camp.enums;

import camp.model.Student;

import java.util.List;

public class StatusFilterStudentInquiry implements StudentInquiry{
    private List<Student> studentStore;
    private String statusFilter;

    public StatusFilterStudentInquiry(List<Student> studentStore,String statusFilter){
        this.studentStore=studentStore;
        this.statusFilter=statusFilter;
    }

    @Override
    public void inquire(){
        System.out.println("================================== \n" + statusFilter + " 상태의 수강생 목록을 조회합니다...");
        boolean found = false;
        for(Student stu_inquiry : studentStore){
            if(stu_inquiry.getStudentStatus().equals(statusFilter)){
                System.out.println(stu_inquiry);
                found =true;
            }
        }
        if(!found){
            System.out.println("해당 상태의 수강생이 없습니다.");
        }
    }
}
