package camp.enums;

public enum ChoiceRankEnum {
    A(90),
    B(80),
    C(70),
    D(60),
    F(50),
    N(0);
    private int score;
    ChoiceRankEnum(int score) {
        this.score = score;
    }

    public static ChoiceRankEnum getRank(int score){
        for ( ChoiceRankEnum m : ChoiceRankEnum.values() ) {
            if (score >= m.score) return m;
        }
        return null;
    }
}
