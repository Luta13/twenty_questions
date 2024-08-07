package displayViews;

import camp.enums.ChoiceRankEnum;
import camp.enums.MandatoryRankEnum;
import camp.exceptions.*;
import camp.model.Round;
import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import static camp.CampManagementApplication.*;

public class DisplayScoreView {

    // 수강생의 과목별 시험 회차 및 점수 등록
    public static void createScore() {
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
    public static void updateRoundScoreBySubject() {
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
    public static void inquireRoundGradeBySubject() {
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

        System.out.println("\n등급 조회 성공!");
    }

    public static void inquireAverageGradeBySubject() {

        System.out.println("관리할 수강생의 이름을 입력해주세요!");
        String studentName = sc.nextLine();
        Student foundStudent = studentStore.stream().filter(stu -> stu.getStudentName().equalsIgnoreCase(studentName)).findFirst().orElseThrow(() -> new RuntimeException("학생 이름 " + studentName + "을 찾지 못했습니다."));
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
