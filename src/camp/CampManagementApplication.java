package camp;

import camp.enums.ChoiceRankEnum;
import camp.enums.MandatoryRankEnum;
import camp.exceptions.*;
import camp.model.*;
import java.util.*;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex = 0;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex = 0;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex = 0;
    private static final String INDEX_TYPE_SCORE = "SC";


    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = new ArrayList<>();
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
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

    private static void displayMainView() throws InterruptedException {
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
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요: ");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");

        // 수강생 입력
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName, subjectStore);
        studentStore.add(student);

        // 기능 구현 (필수 과목, 선택 과목)
        // 필수 과목 3개 이상 입력하기
        while (true) {
            System.out.println("필수 과목 목록(최소 3개 이상)");
            System.out.println("1. Java");
            System.out.println("2. 객체지향");
            System.out.println("3. Spring");
            System.out.println("4. JPA");
            System.out.println("5. MySQL");
            System.out.println("필수 과목 입력 예시 -> Java 객체지향 Spring");
            System.out.print("필수 과목 입력: ");
            String subjectString = sc.nextLine();
            String[] SubjectsInput = subjectString.split(" ");
            if (SubjectsInput.length < 3) {
                System.out.println("최소 3개 이상 입력해주세요.");
            } else {
                for (int i = 0; i < SubjectsInput.length; i++) {
                    subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectsInput[i], SUBJECT_TYPE_MANDATORY));
                }
                break;
            }
        }

        // 선택 과목 2개 이상 입력
        while (true) {
            System.out.println("선택 과목 목록(최소 2개 이상)");
            System.out.println("1. 디자인 패턴");
            System.out.println("2. Spring Security");
            System.out.println("3. Redis");
            System.out.println("4. MongoDB");
            System.out.println("선택 과목 입력 예시 -> 디자인 패턴 Spring Security");
            System.out.print("선택 과목 입력: ");
            String subjectString2 = sc.nextLine();
            String[] SubjectsInput2 = subjectString2.split(" ");
            String subjectString = sc.nextLine();

            if (SubjectsInput2.length < 2) {
                System.out.println("최소 2개 이상 입력해주세요.");
            } else {
                for (int i = 0; i < SubjectsInput2.length; i++) {
                    subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectsInput2[i], SUBJECT_TYPE_CHOICE));
                }
                break;
            }
        }

        for (int i = 0; i < subjectStore.size(); i++) {
            System.out.println(subjectStore.get(i).getSubjectName() + " - " + subjectStore.get(i).getSubjectType());
        }
        //        현재 학생의 모든 회차에 대한 (과목 및 시험점수등) 디폴트값 적용 시키기
        for (int i = 1; i <= 10; i++) {
            Round round = new Round(i);
            for (Subject subject : student.getSubjects()) {
                round.addSubject(subject);
            }
            student.addRoundSubjectsMap(round);
        }
        subjectStore = new ArrayList<>();
    }


    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        if (studentStore.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            for (Student stu_inquiry : studentStore) {
                System.out.println(stu_inquiry.getStudentId() + " - " + stu_inquiry.getStudentName());
            }
        }
        System.out.println("\n수강생 목록 조회 성공!");
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore()  {
        while (true) {
            try {
                System.out.println("어떤 수강생의 과목별 시험 회차 및 점수 등록하시겠습니까? //이름을 입력해주세요!");
                String name = sc.nextLine();
                Student student = studentStore.stream().filter(stu -> stu.getStudentName().equals(name)).findFirst().orElseThrow(HandleMisMatchStudent::new);
                System.out.println("'" + student.getStudentName() + "'의 점수를 등록할 선택지 입니다. //번호를 입력해주세요!");
                System.out.println("1. 점수등록 2. 나가기");
                int input = sc.nextInt();
                //잘못된 번호를 골랐을때
                if (input == 1) {
                } else if (input == 2) {
                    return;
                } else {
                    throw new HandleMisMatchSelect();
                }

                //사용자가 번호를 눌렀을때 해당 필수과목으로 찾게하기위한 List
                List<String> subjectList = new ArrayList<>();

                System.out.println("'몇 회차' 과목의 점수를 등록 하시겠습니까?");
                int count = sc.nextInt();
                // 1 ~ 10 사이의 숫자가 아닌회차의 경우 exception 처리
                if (!(count >= 1 && count <= 10) ){
                    throw new HandleMisMatchRound();
                }
                sc.nextLine();
                System.out.println("현재 " + student.getStudentName() + " 학생의 " + count + "회차 필수,선택 과목 점수 등록 현황 상태입니다.");
                //현재 학생의 n 회차까지 데이터 저장 값들
                int i = 1;
                for (Map.Entry<String, Score> subject : student.getSubjectsMap(count).getSubjects().entrySet()) {
                    subjectList.add(subject.getKey());
                    if (subject.getValue().getScore() == -1) {
                        //점수 등록이 안됐다면
                        System.out.print(i + "." + subject.getKey() + " : " + "[점수미등록] ");
                    }
                    //점수 등록이 됐다면
                    else System.out.print(i + "." + subject.getKey() + " : " + subject.getValue().getScore() + "점 ");
                    ++i;
                }
                System.out.println();
                Round round = new Round(count);
                System.out.println(count + "회차의 어떤 '과목'의 점수를 등록 하시겠습니까? //번호를 입력해주세요!");
                int subjectNumber = sc.nextInt();
                // 1 ~ 필수과목의 size 이외의 숫자라면 exception 처리
                if (!(subjectNumber >= 1 && subjectNumber <= subjectList.size())) throw new HandleMisMatchSelect();
                // subjectList 에서 사용자가 고른 idx 의 글자를 가져옴
                String subject = subjectList.get(subjectNumber - 1);

                System.out.println(count + "회차의 " + subject + " 과목에 대해 '점수'를 등록 해주세요");
                int score = sc.nextInt();
                // 0 ~ 100 숫자가 아니라면 exception 처리
                if (!(score >= 0 && score <= 100)) throw new HandleMisMatchScore();

                //이미 점수가 등록되어있다면
                if ( student.getSubjectsMap(count).getSubject(subject).getScore() != -1 ) throw new HandleDuplicateScore();
                // n 회차에대한 과목,점수 저장
                round.setSubject(student.getSubjectsMap(count).getSubject(subject), score);
                student.getSubjectsMap(count).getSubject(subject).setMandatoryRank(score);
                System.out.println("등록이 정상적으로 마무리 되었습니다 !");
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Error : " +  new HandleMisMatchNotNumber().getMessage());
                return;
            } catch (Exception e) {
                System.out.println("Error : " +  e.getMessage());
                return;
            }
        }
    }





    // 수강생의 과목별 회차 점수 수정 메서드
    private static void updateRoundScoreBySubject() {
        System.out.print("점수를 수정할 수강생의 이름을 입력하세요: ");
        String studentName = sc.nextLine();

        // 점수 수정할 수강생 이름 찾기
        Student student = studentStore.stream().filter(stu -> stu.getStudentName().equalsIgnoreCase(studentName)) // equalsIgnoreCase : 비교 시 대소문자 구분 X
                .findFirst().orElse(null);

        // 해당 수강생이 존재하지 않는다면
        if (student == null) {
            System.out.println("해당 이름을 가진 수강생이 존재하지 않습니다. 점수 관리 화면으로 재이동합니다.");
            return;
        }

        // 점수 수정할 회차 입력
        System.out.print("점수를 수정할 회차를 입력하세요: ");
        int roundNumber = sc.nextInt();
        sc.nextLine(); // 개행문자 처리


        Round round = student.getSubjectsMap(roundNumber);

        // 수정할 과목 선택
        System.out.println(roundNumber + "회차의 수정할 과목을 선택하세요:");
        List<String> subjectList = new ArrayList<>();
        int i = 1;
        for (Map.Entry<String, Score> entry : round.getSubjects().entrySet()) {
            System.out.println(i + ". " + entry.getKey() + " - 현재 점수: " + (entry.getValue().getScore() == -1 ? "[점수미등록]" : entry.getValue().getScore() + "점"));
            subjectList.add(entry.getKey());
            i++;
        }

        // 수정할 과목 번호 입력
        System.out.print("수정할 과목 번호를 입력하세요: ");
        int subjectNumber = sc.nextInt();
        sc.nextLine(); // 개행문자 처리

        // 잘못된 번호 입력시
        if (subjectNumber < 1 || subjectNumber > subjectList.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }


        // 수정할 점수 입력
        String subjectName = subjectList.get(subjectNumber - 1);
        int newScore = -1;
        boolean validScore = false;

        // 입력된 점수 검증 로직 -> 0 ~ 100 범위 밖이거나 숫자가 아닌 경우 재입력 요구
        while (!validScore) {
            System.out.print("새 점수를 입력하세요 (0 ~ 100): ");
            if (sc.hasNextInt()) {
                newScore = sc.nextInt();
                sc.nextLine(); // 개행문자 처리

                if (0 <= newScore && newScore <= 100) {
                    validScore = true;
                } else {
                    System.out.println("점수는 0 ~ 100 점 사이로 입력해주세요.");
                }
            } else {
                System.out.println("유효한 점수를 입력해주세요.");
                sc.next(); // 잘못된 입력을 소비하여 무한 루프 방지
            }
        }

        // 점수 수정
        round.setSubject(student.getSubjectsMap(roundNumber).getSubject(subjectName), newScore);


        // 점수 반영 처리 작업
        Score score = student.getSubjectsMap(roundNumber).getSubject(subjectName);
        if (score.getSubject().getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
            score.setMandatoryRank(newScore);
        } else if (score.getSubject().getSubjectType().equals(SUBJECT_TYPE_CHOICE)) {
            score.setChoiceRank(newScore);
        }

        System.out.println("점수 수정이 완료되었습니다.");
    }


    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        System.out.println("관리할 수강생의 이름을 입력해주세요!");
        String studentName = sc.nextLine();
        Student foundStudent = studentStore.stream().filter(stu -> stu.getStudentName().equalsIgnoreCase(studentName)).findFirst().orElseThrow(() -> new RuntimeException("학생 이름 " + studentName + "을 찾지 못했습니다."));
        System.out.println("조회할 과목을 입력해주세요.");
        String subjectName = sc.nextLine();


        for (int i = 1; i <= 10; i++) {
            Score score = foundStudent.getSubjectsMap(i).getSubject(subjectName);
            int sc = score.getScore();

            if (score.getSubject().getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                MandatoryRankEnum Rank = MandatoryRankEnum.getRank(sc);
                System.out.println((i) + "회차의 등급은 " + Rank + "입니다.");

            } else {
                ChoiceRankEnum Rank = ChoiceRankEnum.getRank(sc);
                System.out.println((i) + "회차의 등급은 " + Rank + "입니다.");

            }
        }

        // 기능 구현
        System.out.println("\n등급 조회 성공!");
    }

    public static List<Student> getStudentStore() {
        return studentStore;
    }

    public static void setStudentStore(List<Student> studentStore) {
        CampManagementApplication.studentStore = studentStore;
    }

    public static List<Subject> getSubjectStore() {
        return subjectStore;
    }

    public static void setSubjectStore(List<Subject> subjectStore) {
        CampManagementApplication.subjectStore = subjectStore;
    }

    public static List<Score> getScoreStore() {
        return scoreStore;
    }

    public static void setScoreStore(List<Score> scoreStore) {
        CampManagementApplication.scoreStore = scoreStore;
    }

    public static String getSubjectTypeMandatory() {
        return SUBJECT_TYPE_MANDATORY;
    }

    public static void setSubjectTypeMandatory(String subjectTypeMandatory) {
        SUBJECT_TYPE_MANDATORY = subjectTypeMandatory;
    }

    public static String getSubjectTypeChoice() {
        return SUBJECT_TYPE_CHOICE;
    }

    public static void setSubjectTypeChoice(String subjectTypeChoice) {
        SUBJECT_TYPE_CHOICE = subjectTypeChoice;
    }

    public static int getStudentIndex() {
        return studentIndex;
    }

    public static void setStudentIndex(int studentIndex) {
        CampManagementApplication.studentIndex = studentIndex;
    }

    public static int getSubjectIndex() {
        return subjectIndex;
    }

    public static void setSubjectIndex(int subjectIndex) {
        CampManagementApplication.subjectIndex = subjectIndex;
    }

    public static int getScoreIndex() {
        return scoreIndex;
    }

    public static void setScoreIndex(int scoreIndex) {
        CampManagementApplication.scoreIndex = scoreIndex;
    }

    public static Scanner getSc() {
        return sc;
    }

    public static void setSc(Scanner sc) {
        CampManagementApplication.sc = sc;
    }
}
