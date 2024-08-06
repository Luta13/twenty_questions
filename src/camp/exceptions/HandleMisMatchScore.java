package camp.exceptions;

//잘못된 점수를 입력하였을때
public class HandleMisMatchScore extends Exception{
    public HandleMisMatchScore(){
        super("점수는 0 ~ 100 숫자만 입력 가능합니다 !");
    }
}
