package displayViews;

import camp.studentInquiry.BasicStudentInquiry;
import camp.studentInquiry.DetaileStudentInquiry;
import camp.studentInquiry.StatusFilterStudentInquiry;
import camp.studentInquiry.StudentInquiry;
import camp.model.Round;
import camp.model.Student;
import camp.model.Subject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

import static camp.CampManagementApplication.*;
import static camp.util.Util.sequence;

public class DisplayStudentView {
    // 수강생 등록
    public static void createStudent() {
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

    public static void inquireStudent() {
        boolean flag = true; //메인화면 돌아가기
        if (studentStore.isEmpty()) { //등록된 수강생이 없을때
            System.out.println("==================================");
            System.out.println("등록된 수강생이 없습니다.");
        } else {
            while (flag) { //메인화면으로 돌아갈 수 있도록
                try {//목록 선택-필수,선택 조회
                    System.out.print("================================== \n수강생 조회 목록을 선택해주세요\n 1. 기본조회(고유번호,이름) \n 2. 상세조회(고유번호,이름,상태,과목) \n 3. 상태별 목록조회  \n 4. 수강생 관리 이동 \n 관리항목을 선택하세요...");
                    int selectInquiry = sc.nextInt();

                    //객체 초기화
                    StudentInquiry inquiry = null;

                    switch (selectInquiry) {
                        case 1: //필수 요구사항
                            inquiry = new BasicStudentInquiry(studentStore); //Stdent 클래스에서 toSTring을 통해 기본조회 가져옴
                            break;
                        case 2: //선택 요구사항 상태+과목
                            inquiry = new DetaileStudentInquiry(studentStore); // Stdent 클래스에서 상태를 Subject 클래스에서 과목이름 가져옴
                            break;
                        case 3: //선택요구사항 상태별 조회
                            System.out.print("================================== \n 원하는 상태를 번호로 입력해주세요 \n1. Green \n2. Yellow \n3. Red \n 관리항목을 선택하세요...");
                            int colorStatus = sc.nextInt();
                            String statusFilter; //조회할 상태 이름
                            switch (colorStatus) {
                                case 1:
                                    statusFilter = "GREEN";
                                    break;
                                case 2:
                                    statusFilter = "YELLOW";
                                    break;
                                case 3:
                                    statusFilter = "RED";
                                    break;
                                default:
                                    System.out.println("잘못된 번호입니다. 다시 선택해주세요.");
                                    continue;
                            }
                            inquiry = new StatusFilterStudentInquiry(studentStore, statusFilter); //상태별로 기본조회 불러옴
                            break;
                        case 4:
                            System.out.println("수강생 관리로 이동됩니다."); //관리로 이동
                            flag = false;
                            break;
                        default:
                            System.out.println("잘못된 번호 입니다 다시 선택해주세요."); // 그외 번호 입력 시 재입력창
                    }
                    if (inquiry != null) {//inquiry 가 비어있지않으면 StudentInquiry 에서 inquire매소드를 가져옴 ( NullPointerException 방지)
                        inquiry.inquire();
                    }
                } catch (InputMismatchException e) { //입력창에 정수를 입력해야하는데 잘못입력할 경우
                    System.out.println("잘못된 입력 형식입니다. 숫자를 입력해주세요.");
                } catch (Exception e) {//포괄 예외를 방지(디테일은 부족)
                    System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage()); //예외 주소를 알려줌
                }
            }
        }
    }

    // 수강생 수정
    public static void correctionStudent() {
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

    public static void deleteStudent() {
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
}
