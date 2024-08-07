package camp.util;

import java.util.ArrayList;

import static camp.CampManagementApplication.*;

public class Util {
    // index 자동 증가
    public static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return String.valueOf(studentIndex);
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return String.valueOf(subjectIndex);
            }
            default -> {
                scoreIndex++;
                return String.valueOf(scoreIndex);
            }
        }
    }

    // 초기 데이터 생성
    public static void setInitData() {
        studentStore = new ArrayList<>();
    }
}
