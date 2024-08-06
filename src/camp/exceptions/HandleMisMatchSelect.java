package camp.exceptions;

// 산택지의 번호를 초과한 다른번호를 입력하였을때
public class HandleMisMatchSelect extends Exception{
    public HandleMisMatchSelect(){
        super("보기중에 알맞은 선택지를 골라주세요 !");
    }
}
