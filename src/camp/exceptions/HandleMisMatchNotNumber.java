package camp.exceptions;

// input 타입이 숫자(정수)가 아닐때
public class HandleMisMatchNotNumber extends Exception{
    public HandleMisMatchNotNumber(){
        super("숫자타입은 '정수'만 들어올 수 있습니다 !");
    }
}
