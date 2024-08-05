package camp.exceptions;

public class HandleMisMatchScore extends Exception{
    public HandleMisMatchScore(){
        super("점수는 0 ~ 100 숫자만 입력 가능합니다 !");
    }
}
