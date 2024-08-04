package camp.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class RoundTest {
    private Map<String, ScoreTest> roundMap;
    private List<Map<String,ScoreTest>> roundMapList = new ArrayList<>();

    public RoundTest() {
    }

    public Map<String, ScoreTest> getRoundMap() {
        return roundMap;
    }

    public void setRoundMap(Map<String, ScoreTest> roundMap) {
        this.roundMap = roundMap;
    }

    public List<Map<String, ScoreTest>> getRoundMapList() {
        return roundMapList;
    }

    public void addRoundMapList(Map<String,ScoreTest> roundMapList) {
        this.roundMapList.add(roundMapList);
    }
}
