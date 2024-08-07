package camp;

//import camp.enums.ChoiceRankEnum;
//import camp.enums.MandatoryRankEnum;
import camp.exceptions.*;
import camp.model.*;
import java.util.*;
import camp.enums.*;

import javax.print.DocFlavor;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들을 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;

    // 과목 타입
    public static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    public static String SUBJECT_TYPE_CHOICE = "CHOICE";

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
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }


    private static void deleteStudent(){
        System.out.println("\n수강생을 삭제합니다...");
        String studentName;
        while (true) {
            System.out.print("수강생 이름 입력: ");
            studentName = sc.next();

            boolean found = false;

            for (int i = 0; i < studentStore.size(); i++) {
                Student student = studentStore.get(i);
                if (student.getStudentName().equals(studentName)) {
                    found = true;
                    studentStore.remove(i);
                    System.out.println(studentName + "을(를) 삭제했습니다.");
                    break;
                }
            }

            if (!found) {
                System.out.println(studentName + "이(가) 존재하지 않습니다.");
            } else {
                break;
            }
        }
    }
    // 수강생 수정
    private static void correctionStudent() {
        System.out.println("\n수강생을 수정합니다...");
        String studentName;
        while (true) {
            System.out.print("수강생 이름 입력: ");
            studentName = sc.next();

            boolean found = false;

            for (Student student : studentStore) {
                if (student.getStudentName().equals(studentName)) {
                    found = true;
                    System.out.println(studentName + "을(를) 찾았습니다.");
                    break;
                }
            }

            if (!found) {
                System.out.println(studentName + "이(가) 존재하지 않습니다.");
            } else {
                break;
            }
        }

        System.out.println("1. 이름");
        System.out.println("2. 상태");
        System.out.print("수정 할 정보를 입력하세요: ");
        int choice = sc.nextInt();


        switch (choice) {
            case 1:
                System.out.print("수정할 이름을 입력하세요: ");
                String newName = sc.next();
                for (Student student : studentStore) {
                    if (student.getStudentName().equals(studentName)) {
                        student.setStudentName(newName);
                    }
                }
                break;
            case 2:
                System.out.println("1. GREEN");
                System.out.println("2. RED");
                System.out.println("3. YELLOW");
                System.out.print("수정할 상태를 입력하세요: ");
                int newState = sc.nextInt();
                switch (newState) {
                    case 1:
                        for (Student student : studentStore) {
                            if (student.getStudentName().equals(studentName)) {
                                student.setStudentState("GREEN");
                            }
                        }
                        break;
                    case 2:
                        for (Student student : studentStore) {
                            if (student.getStudentName().equals(studentName)) {
                                student.setStudentState("RED");
                            }
                        }
                        break;
                    case 3:
                        for (Student student : studentStore) {
                            if (student.getStudentName().equals(studentName)) {
                                student.setStudentState("YELLOW");
                            }
                        }
                        break;
                }
                break;
            default:
                System.out.println("1번 또는 2번을 입력하세요.");
                break;
        }


        System.out.println("수정을 완료 했습니다.");
    }

    // 수강생 등록
    private static void createStudent() {
        System.out.println("\n수강생을 등록합니다...");

        // 수강생 입력
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName);
        studentStore.add(student);

        // 기능 구현 (필수 과목, 선택 과목)
        subjectIndex = 0;
        // 필수 과목 3개 이상 입력하기
        while (true) {
            Set<String> validSubjects = new HashSet<>(Arrays.asList("Java", "객체지향", "Spring", "JPA", "MySQL"));

            System.out.println("필수 과목 목록(최소 3개 이상)");
            System.out.println("1. Java");
            System.out.println("2. 객체지향");
            System.out.println("3. Spring");
            System.out.println("4. JPA");
            System.out.println("5. MySQL");
            System.out.println("필수 과목 입력 예시 -> Java 객체지향 Spring");
            System.out.print("필수 과목 입력: ");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            String subjectString = sc.nextLine();
            String[] SubjectsInput = subjectString.split(" ");
            if (SubjectsInput.length < 3) {
                System.out.println("최소 3개 이상 입력해주세요.");
            } else {
                boolean allValid = true;
                for (String subject : SubjectsInput) {
                    if (!validSubjects.contains(subject)) {
                        System.out.println("입력한 과목 중 유효하지 않은 과목이 있습니다: " + subject);
                        allValid = false;
                        break;
                    }
                }

                if (allValid) {
                    for (int i = 0; i < SubjectsInput.length; i++) {
                        student.setSubjects(sequence(INDEX_TYPE_SUBJECT), SubjectsInput[i], SUBJECT_TYPE_MANDATORY);
                    }
                    break; // Exit the loop after successful input
                } else {
                    System.out.println("유효한 과목만 입력해주세요. 다시 시도하세요.");
                }
            }

        }

        // 선택 과목 2개 이상 입력
        while (true) {
            Set<String> validSubjects = new HashSet<>(Arrays.asList("디자인패턴", "Spring Security", "Redis", "MongoDB"));
            System.out.println("선택 과목 목록(최소 2개 이상)");
            System.out.println("1. 디자인패턴");
            System.out.println("2. Spring Security");
            System.out.println("3. Redis");
            System.out.println("4. MongoDB");
            System.out.println("선택 과목 입력 예시 -> 디자인 패턴 Spring Security");
            System.out.print("선택 과목 입력: ");
            String subjectString2 = sc.nextLine();
            String[] SubjectsInput2 = subjectString2.split(" ");
            if (SubjectsInput2.length < 2) {
                System.out.println("최소 2개 이상 입력해주세요.");
            } else {
                boolean allValid = true;
                for (String subject : SubjectsInput2) {
                    if (!validSubjects.contains(subject)) {
                        System.out.println("입력한 과목 중 유효하지 않은 과목이 있습니다: " + subject);
                        allValid = false;
                        break;
                    }
                }

                if (allValid) {
                    for (int i = 0; i < SubjectsInput2.length; i++) {
                        student.setSubjects(sequence(INDEX_TYPE_SUBJECT), SubjectsInput2[i], SUBJECT_TYPE_CHOICE);
                    }
                    break; // Exit the loop after successful input
                } else {
                    System.out.println("유효한 과목만 입력해주세요. 다시 시도하세요.");
                }
            }
        }

        System.out.println("수강생 상태");
        System.out.println("1. GREEN");
        System.out.println("2. RED");
        System.out.println("3. YELLOW");
        System.out.print("상태 입력:");
        int studentState = sc.nextInt();
        switch (studentState) {
            case 1:
                student.setStudentState("GREEN");
                System.out.println("상태 입력 성공!");
                break;
            case 2:
                student.setStudentState("RED");
                System.out.println("상태 입력 성공!");
                break;
            case 3:
                student.setStudentState("YELLOW");
                System.out.println("상태 입력 성공!");
                break;
        }

        System.out.println("수강생 등록 완료!");

        //디폴트값 셋팅
        for (int i = 1; i <= 10; i++) {
            Round round = new Round(i);
            for (Subject subject : student.getSubjects()) {
                round.addSubject(subject);
            }
            student.addRoundSubjectsMap(round);
        }
    }

    private static void inquireStudent() {
        boolean flag = true; //메인화면 돌아가기
        if (studentStore.isEmpty()) { //등록된 수강생이 없을때
            System.out.println("==================================");
            System.out.println("등록된 수강생이 없습니다.");
        }else {
            while(flag){ //메인화면으로 돌아갈 수 있도록
                try{//목록 선택-필수,선택 조회
                    System.out.print("================================== \n수강생 조회 목록을 선택해주세요\n 1. 기본조회(고유번호,이름) \n 2. 상세조회(고유번호,이름,상태,과목) \n 3. 상태별 목록조회  \n 4. 수강생 관리 이동 \n 관리항목을 선택하세요...");
                    int selectInquiry=sc.nextInt();

                    //객체 초기화
                    StudentInquiry inquiry = null;

                    switch (selectInquiry) {
                        case 1: //필수 요구사항
                            inquiry = new BasicStudentInquiry(studentStore); //Stdent 클래스에서 toSTring을 통해 기본조회 가져옴
                            break;
                        case 2: //선택 요구사항 상태+과목
                            inquiry = new DetaileStudentInquiry(studentStore); // Stdent 클래스에서 상태를 Subject 클래스에서 과목이름 가져옴
                            break;
                        case 3 : //선택요구사항 상태별 조회
                            System.out.print("================================== \n 원하는 상태를 번호로 입력해주세요 \n1. Green \n2. Yellow \n3. Red \n 관리항목을 선택하세요...");
                            int colorStatus = sc.nextInt();
                            String statusFilter; //조회할 상태 이름
                            switch (colorStatus) {
                                case 1 :
                                    statusFilter = "GREEN";
                                    break;
                                case 2 :
                                    statusFilter = "YELLOW";
                                    break;
                                case 3 :
                                    statusFilter = "RED";
                                    break;
                                default:
                                    System.out.println("잘못된 번호입니다. 다시 선택해주세요.");
                                    continue;
                            }
                            inquiry = new StatusFilterStudentInquiry(studentStore,statusFilter); //상태별로 기본조회 불러옴
                            break;
                        case 4 :
                            System.out.println("수강생 관리로 이동됩니다."); //관리로 이동
                            flag = false;
                            break;
                        default:
                            System.out.println("잘못된 번호 입니다 다시 선택해주세요."); // 그외 번호 입력 시 재입력창
                    }
                    if (inquiry != null){//inquiry 가 비어있지않으면 StudentInquiry 에서 inquire매소드를 가져옴 ( NullPointerException 방지)
                        inquiry.inquire();
                    }
                }catch (InputMismatchException e){ //입력창에 정수를 입력해야하는데 잘못입력할 경우
                    System.out.println("잘못된 입력 형식입니다. 숫자를 입력해주세요.");
                }catch (Exception e){//포괄 예외를 방지(디테일은 부족)
                    System.out.println("예상치 못한 오류가 발생했습니다: "+e.getMessage()); //예외 주소를 알려줌
                }
            }
        }
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();
            sc.nextLine();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> displayinquiryView(); // 조회뷰로 이동
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
    private static void createScore() {
        while (true) {
            try {
                System.out.println("어떤 수강생의 과목별 시험 회차 및 점수 등록하시겠습니까? //이름을 입력해주세요!");
                String name = sc.nextLine();
                Student student = studentStore.stream().filter(stu -> stu.getStudentName().equals(name)).findFirst().orElseThrow(HandleMisMatchStudent::new);
                System.out.println("'" + student.getStudentName() + "'의 점수를 등록할 선택지 입니다. //번호를 입력해주세요!");
                System.out.println("1. 점수등록 2. 나가기");
                int input = sc.nextInt();
                sc.nextLine();
                //잘못된 번호를 골랐을때
                if (input == 1) ;
                else if (input == 2) return;
                else throw new HandleMisMatchSelect();

                //사용자가 번호를 눌렀을때 해당 필수과목으로 찾게하기위한 List
                List<String> subjectList = new ArrayList<>();

                System.out.println("'몇 회차' 과목의 점수를 등록 하시겠습니까? //번호를 입력해 주세요 !");
                int roundNumber = sc.nextInt();
                sc.nextLine();
                // 1 ~ 10 사이의 숫자가 아닌회차의 경우 exception 처리
                if (!(roundNumber >= 1 && roundNumber <= 10)) throw new HandleMisMatchRound();
                System.out.println("현재 " + student.getStudentName() + " 학생의 " + roundNumber + "회차 필수,선택 과목 점수 등록 현황 상태입니다.");
                //현재 학생의 n 회차까지 데이터 저장 값들
                int i = 1;
                for (Map.Entry<String, Score> subject : student.getSubjectsMap(roundNumber).getSubjects().entrySet()) {
                    subjectList.add(subject.getKey());
                    //점수 등록이 안됐다면
                    if (subject.getValue().getScore() == -1)
                        System.out.print(i + "." + subject.getKey() + " : " + "[점수미등록] ");
                        //점수 등록이 됐다면
                    else System.out.print(i + "." + subject.getKey() + " : " + subject.getValue().getScore() + "점 ");
                    ++i;
                }
                System.out.println();
                Round round = new Round(roundNumber);
                System.out.println(roundNumber + "회차의 어떤 '과목'의 점수를 등록 하시겠습니까? //번호를 입력해주세요!");
                //등록할 과목의 index
                int subjectNumber = sc.nextInt();
                sc.nextLine();
                // 1 ~ 필수과목의 size 이외의 숫자라면 exception 처리
                if (!(subjectNumber >= 1 && subjectNumber <= subjectList.size())) throw new HandleMisMatchSelect();
                // subjectList 에서 사용자가 고른 idx 의 글자를 가져옴
                String subject = subjectList.get(subjectNumber - 1);

                System.out.println(roundNumber + "회차의 " + subject + " 과목에 대해 '점수'를 등록 해주세요");
                int score = sc.nextInt();
                sc.nextLine();
                // 0 ~ 100 숫자가 아니라면 exception 처리
                if (!(score >= 0 && score <= 100)) throw new HandleMisMatchScore();

                //이미 점수가 등록되어있다면
                if (student.getSubjectsMap(roundNumber).getSubject(subject).getScore() != -1)
                    throw new HandleDuplicateScore();
                // n 회차에대한 과목,점수 저장
                round.setSubject(student.getSubjectsMap(roundNumber).getSubject(subject), score);
                //필수타입이면 MandatoryEnum 클래스로 랭크 계산
                if (student.getSubjectsMap(roundNumber).getSubject(subject).getSubject().getSubjectType().equals(SUBJECT_TYPE_MANDATORY))
                    student.getSubjectsMap(roundNumber).getSubject(subject).setMandatoryRank(score);
                    //선택타입이면 ChoiceEnum 클래스로 랭크 계산
                else student.getSubjectsMap(roundNumber).getSubject(subject).setChoiceRank(score);
                System.out.println("등록이 정상적으로 마무리 되었습니다 !");
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Error : " + new HandleMisMatchNotNumber().getMessage());
                return;
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
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

    private static void displayinquiryView()
    {
        System.out.println("==================================");
        System.out.println("수강생의 등급 조회 실행 중...");
        System.out.println("1. 수강생의 특정 과목 회차별 등급 조회");
        System.out.println("2. 수강생의 과목별 평균 등급 조회");
        System.out.print("관리 항목을 선택하세요: ");

        switch(sc.nextInt())
        {
            case 1 -> inquireRoundGradeBySubject();
            case 2 -> inquireAverageGradeBySubject();
        }


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

    private static void inquireAverageGradeBySubject() {

        System.out.println("관리할 수강생의 이름을 입력해주세요!");
        String studentName = sc.nextLine();
        Student foundStudent = studentStore.stream()
                .filter(stu -> stu.getStudentName().equalsIgnoreCase(studentName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("학생 이름 " + studentName + "을 찾지 못했습니다."));
        System.out.println("수강생의 과목별 평균 등급을 조회합니다.");


        int sum = 0;
        int roundNumber = 1;
        int valid = 0;
        for (int i = 0; i < foundStudent.getSubjects().size(); i++) {

            Subject subject = foundStudent.getSubjects().get(i);
            String subjectName = foundStudent.getSubjects().get(i).getSubjectName();
            System.out.println("과목명 : " + subjectName);
            for (roundNumber = 1; roundNumber <= 10; roundNumber++) {
                if (foundStudent.getSubjectsMap(roundNumber).getSubject(subjectName).getScore() <= 0) {
                    break;
                }

                Score score = foundStudent.getSubjectsMap(roundNumber).getSubject(subjectName);
                sum += score.getScore();
                valid++;
            }
            if (valid > 0) {
                int average = sum / (roundNumber - 1);
                if (subject.getSubjectType().equals(SUBJECT_TYPE_MANDATORY)) {
                    MandatoryRankEnum Rank = MandatoryRankEnum.getRank(average);
                    System.out.println("평균 등급 : " + Rank);
                } else {
                    ChoiceRankEnum Rank = ChoiceRankEnum.getRank(average);
                    System.out.println("평균 등급 : " + Rank);
                }
                System.out.println("등급 조회 성공!");
                System.out.println();
            } else {
                System.out.println("등록된 점수가 없습니다.");
            }

        }


    }
}
