package camp.exceptions;

public class HandleMisMatchNotNumber extends Exception{
    public HandleMisMatchNotNumber(){
        super("숫자타입은 '정수'만 들어올 수 있습니다 !");
    }
}
