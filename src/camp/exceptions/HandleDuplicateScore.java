package camp.exceptions;

// 시험정수 중복으로 등록할때
public class HandleDuplicateScore extends Exception{
    public HandleDuplicateScore(){
        super("해당 과목의 점수는 이미 등록 되었습니다 !");
    }
}
