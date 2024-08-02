package camp.enums;

public enum MandatoryRankEnum {
    A(95),
    B(90),
    C(80),
    D(70),
    F(60),
    N(0);
    private int score;
    MandatoryRankEnum(int score) {
        this.score = score;
    }

    public static MandatoryRankEnum getRank(int score){
        for ( MandatoryRankEnum m : MandatoryRankEnum.values() ) {
            if (score >= m.score) return m;
        }
        return null;
    }
}
