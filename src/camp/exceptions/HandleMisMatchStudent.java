package camp.exceptions;

//잘못된 학생의 정보를 기재하였을 경우
public class HandleMisMatchStudent extends Exception{
    public HandleMisMatchStudent(){
        super("학생의 정보가 일치하지 않습니다 !");
    }
}
