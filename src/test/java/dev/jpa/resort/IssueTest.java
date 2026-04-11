package dev.jpa.resort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.jpa.resort.issue.ISSUE;
import dev.jpa.resort.issue.ISSUERepository;

@SpringBootTest
public class IssueTest {
  // ISSUERepository repository = new ISSUERepository();
  // interface ISSUERepository 객체 생성 불가 -> Hibernate가 자동 구현
  // 객체 자동 할당됨.
  @Autowired
  ISSUERepository repository;
  
  @Test
  void createTest() {
    // 객체 생성시 레코드 등록됨, SQL 필요 없음.
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String now = sdf.format(new Date());

    // 1) 등록
    // issueno, title, content, cnt, rdate    
//     ISSUE issue = new ISSUE(0, "금요일 다과회 안내", "점심시간 팀별 치킨, 피자 제공", 0, now);
//     repository.save(issue);  // INSERT
    
//    ISSUE issue = new ISSUE(0, "토요일 시스템 정비", "토요일 서버 접속이 제한됩니다.", 0, now);
//    repository.save(issue);
//    
//    issue = new ISSUE(0, "접속자 증가로 인한 오류 발생", "현재 해결됨", 0, now);
//    repository.save(issue);
//    
//    issue = new ISSUE(0, "연휴 휴무 안내", "연휴에 기술지원 제공됩니다.", 0, now);
//    repository.save(issue);
//
//    issue = new ISSUE(0, "10월 연휴 안내", "연휴에 기술지원 제공됩니다.", 0, now);
//    repository.save(issue);
//    
//    issue = new ISSUE(0, "추석 연휴", "추석 당일 장애 대응 긴급 전화 안내", 0, now);
//    repository.save(issue);

  
    // 2) 목록
//    List<ISSUE> list = repository.findAll();
//        
//    for (ISSUE issue: list) {
//      System.out.println("-> " + issue.getIssueno() + " " + issue.getTitle() + " " + issue.getContent() + " " + issue.getRdate());     
//    };
      
    //  3) title 컬럼으로 검색
//    List<ISSUE> list = repository.findByTitle("연휴 휴무 안내");
//    for (ISSUE issue: list) {
//      System.out.println("-> " + issue.getIssueno() + " " + issue.getTitle() + " " + issue.getContent() + " " + issue.getRdate());     
//    };

 // 4) WHERE title LIKE '%연휴%'
//    List<ISSUE> list = repository.findByTitleContaining("연휴");
//    for (ISSUE issue: list) {
//      System.out.println("-> " + issue.getIssueno() + " / " + issue.getTitle() + " / " + issue.getContent() + " " + issue.getRdate());     
//    };
    
    // 5) WHERE title LIKE '%연휴%' OR content LIKE '%연휴%';
    // findBy + 컬럼명 + ContainingOr + 컬럼명 + Containing
//    List<ISSUE> list = repository.findByTitleContainingOrContentContaining("연휴", "연휴");
//    for (ISSUE issue: list) {
//      System.out.println("-> " + issue.getIssueno() + " / " + issue.getTitle() + " / " + issue.getContent() + " " + issue.getRdate());     
//    };
    
    // 6) WHERE title LIKE '%연휴%' AND content LIKE '%연휴%';
    // findBy + 컬럼명 + ContainingOr + 컬럼명 + Containing    List<ISSUE> list = repository.findByTitleContainingOrContentContaining("연휴", "연휴");
//    List<ISSUE> list = repository.findByTitleContainingAndContentContaining("연휴", "연휴");
//    for (ISSUE issue: list) {
//      System.out.println("-> " + issue.getIssueno() + " / " + issue.getTitle() + " / " + issue.getContent() + " " + issue.getRdate());     
//    };
    
    // 7) title 대소문자 무시 검색
//    List<ISSUE> list = repository.findByTitleContainingIgnoreCase("Cloud");
//    for (ISSUE issue: list) {
//      System.out.println(issue.toString());     
//    };
    
    // 8) rdate 기준 내림 차순 정렬
//    List<ISSUE> list = repository.findAllByOrderByRdateDesc();
//    for (ISSUE issue: list) {
//      System.out.println(issue.toString());     
//    };
    
    // 9) 조회
//    ISSUE issue = repository.findById((long)1).get();
//    System.out.println(issue.toString());
    
    // 10) 수정: 조회 -> 변경 내용 setter 호출 -> 저장
//    ISSUE issue = repository.findByIssueno((long)1);
//    System.out.println(issue.toString());
//    
//    issue.setTitle("cloud 배포2");
//    issue.setContent("DevOps 진행2");
//    
    // SELECT하여 변경된 컬럼이 있으면 모든 컬럼 Update 진행, 컬럼값이 동일하면 update 실행 안됨.
//    ISSUE new_issue = repository.save(issue); // UPDATE
//    System.out.println(new_issue.toString());
    
    // 11) 삭제: 조회 -> 삭제
    // repository.deleteById((long)1); // 삭제 결과 확인 안됨, return void.
    
//    Optional<ISSUE> issue = repository.findById((long)2); // 조회되는지 확인
//    
//    if (issue.isPresent()) { // 객체가 있는지 검사
//      ISSUE entity = issue.get();
//      System.out.println(entity.toString());
//      
//      repository.delete(entity); // return void
//      System.out.println("레코드를 삭제했습니다.");
//    } else {
//      System.out.println("존재하지 않는 번호입니다.");
//    }
    
    // 12) 조회수 증가
    // repository.increaseCnt((long)4);
    
    // 13) 날짜 구간 검색
//    List<ISSUE> list = repository.findByRdatePeriod("2025-09-25", "2025-09-25");
//    for (ISSUE issue: list) {
//      System.out.println(issue.toString());     
//    };
    
    // 14) 날짜 구간 검색하여 날짜 내림 차순 정렬
//    List<ISSUE> list = repository.findByRdatePeriodDesc("2025-09-25", "2025-09-26");
//    for (ISSUE issue: list) {
//      System.out.println(issue.toString());     
//    };
    
    // 15) 페이징 처리
//    int pageNumber = 3;  // 3 페이지 해당됨, 0~
//    int pageSize = 3;  // 페이지당 출력할 레코드 갯수
//    Pageable pageable = PageRequest.of(pageNumber, pageSize); // 페이징 객체 생성
//    Page<ISSUE> pages =  repository.findAllByOrderByIssuenoDesc(pageable); // 페이징 실행
//    List<ISSUE> list = pages.getContent(); // 페이징 목록 추출
//    
//    for(ISSUE issue:list) {
//      System.out.println(issue.toString());
//    }   

    // 16) 페이징 처리 + 검색
    int pageNumber = 0;  // 3 페이지 해당됨, 0~
    int pageSize = 3;  // 페이지당 출력할 레코드 갯수
    Pageable pageable = PageRequest.of(pageNumber, pageSize); // 페이징 객체 생성
    Page<ISSUE> pages =  repository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByIssuenoDesc(pageable, "연휴", "연휴"); // 페이징 실행
    List<ISSUE> list = pages.getContent(); // 페이징 목록 추출
    
    for(ISSUE issue:list) {
      System.out.println(issue.toString());
    }   

  }
}










