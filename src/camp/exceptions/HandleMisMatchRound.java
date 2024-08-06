package camp.exceptions;

//잘못된 회차를 기재 하였을경우
public class HandleMisMatchRound extends Exception{
    public HandleMisMatchRound(){
        super("회차는 1 ~ 10 숫자만 입력 가능합니다");
    }
}
