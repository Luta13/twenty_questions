package camp;

import camp.exceptions.HandleMisMatchSelect;
import camp.model.*;
import java.util.*;
import static displayViews.DisplayScoreView.*;
import static displayViews.DisplayStudentView.*;
import static camp.util.Util.setInitData;

public class CampManagementApplication {
    // 데이터 저장소
    public static List<Student> studentStore;

    // 과목 타입
    public static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    public static int studentIndex = 0;
    public static final String INDEX_TYPE_STUDENT = "ST";
    public static int subjectIndex = 0;
    public static final String INDEX_TYPE_SUBJECT = "SU";
    public static int scoreIndex = 0;

    // 스캐너
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        displayMainView();
    }

    public static void displayMainView() {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("\n==================================");
                System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
                System.out.println("1. 수강생 관리");
                System.out.println("2. 점수 관리");
                System.out.println("3. 프로그램 종료");
                System.out.print("관리 항목을 선택하세요: ");
                int input = sc.nextInt();

                switch (input) {
                    case 1 -> displayStudentView(); // 수강생 관리
                    case 2 -> displayScoreView(); // 점수 관리
                    case 3 -> flag = false; // 프로그램 종료
                    default -> {
                        System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    }
                }
            }
            System.out.println("프로그램을 종료합니다.");
        } catch (Exception e) {
            System.out.println(new HandleMisMatchSelect().getMessage());
            sc.nextLine();
            displayMainView();
        }
    }

    public static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 수정");
            System.out.println("4. 수강생 삭제");
            System.out.println("5. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요: ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> correctionStudent(); // 수강생 수정
                case 4 -> deleteStudent(); // 수강생 삭제
                case 5 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }

    public static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생 등급 조회 메뉴이동");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> displayInquiryView(); // 조회뷰로 이동
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                }
            }
        }
    }

    public static void displayInquiryView() {
        System.out.println("==================================");
        System.out.println("수강생의 등급 조회 실행 중...");
        System.out.println("1. 수강생의 특정 과목 회차별 등급 조회");
        System.out.println("2. 수강생의 과목별 평균 등급 조회");
        System.out.print("관리 항목을 선택하세요: ");
        int input = sc.nextInt();
        sc.nextLine();

        switch (input) {
            case 1 -> inquireRoundGradeBySubject();
            case 2 -> inquireAverageGradeBySubject();
            default -> {
                System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
            }
        }
    }
}
