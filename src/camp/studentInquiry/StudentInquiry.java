package camp.studentInquiry;

public interface StudentInquiry {
    // 인터페이스를 정의하여 공통적인 메서드 시그니처 정의
    // 다양한 조회 기능을 일관되게 관리
    // 구체적인 동작을 수행하지 않으며, 각 구현 클래스에서 이를 구체화
    void inquire();
}
