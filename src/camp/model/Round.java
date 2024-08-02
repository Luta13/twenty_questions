package camp.model;

import java.util.HashMap;
import java.util.Map;

//선택과목 회차 저장 클래스
public class Round {
    //회차
    private int roundNumber;
    //아 그럼 Map<String,Integer> 구조로 바꾸어도 되지만
    // 객체지향의 원칙을 지키고, StudnetInputSubject에 추가적으로 요소가 늘어날일이생겼을때를 대비하기위함
    //선택과목
    private Map<String, Score> roundMap;

    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.roundMap = new HashMap<>();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Map<String, Score> getSubjects() {
        return roundMap;
    }

    public Score getSubject(String subjectName) {
        return roundMap.get(subjectName);
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }



    public void addSubject(Subject subject){
        this.roundMap.put(subject.getSubjectName(),new Score(subject.getSubjectName(),-1,subject));
    }

    public void setSubject(Score score, int intScore){
        score.setScore(intScore);

    }


    @Override
    public String toString() {
        return "Round{" + "roundNumber=" + roundNumber + ", roundMap=" + roundMap + '}';
    }
}
