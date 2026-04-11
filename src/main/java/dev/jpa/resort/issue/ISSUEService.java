package dev.jpa.resort.issue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.jpa.resort.tool.Tool;

// @Service
public class ISSUEService {
  @Autowired
  ISSUERepository repository;
  
  public ISSUEService() {
    System.out.println("-> ISSUEService created.");
  }
  
  /** 등록 */
  public ISSUE save(ISSUEDTO issueDTO) {
    issueDTO.setRdate(Tool.getDate());
    System.out.println("-> issueno: " + issueDTO.getIssueno()); // 0
    
    ISSUE savedEntity = repository.save(issueDTO.toEntity()); // dto -> entity
    System.out.println("-> issueno: " + savedEntity.getIssueno());
    
    return savedEntity;
  }

  /** 목록 
   * @return
   */
  public List<ISSUE> findAll() {
    List<ISSUE> list = repository.findAll();
    return list;
  }
  
  // 3) title 컬럼으로 검색, WHERE title = '연휴 휴무 안내';
  // findBy + 컬럼명
  public List<ISSUE> findByTitle(String title) {
    List<ISSUE> list = repository.findByTitle(title);
    
    return list;
  }
  
  // 4) WHERE title LIKE '%연휴%'
  // findBy + 컬럼명 + Containing
  public List<ISSUE> findByTitleContaining(String title) {
    List<ISSUE> list = repository.findByTitleContaining(title);
    
    return list;
  }

  // 5) WHERE title LIKE '%연휴%' OR content LIKE '%연휴%';
  // findBy + 컬럼명 + ContainingOr + 컬럼명 + Containing
  public List<ISSUE> findByTitleContainingOrContentContaining(String title, String content) {
    List<ISSUE> list = repository.findByTitleContainingOrContentContaining(title, content);
    
    return list;
  }
  

  // 6) WHERE title LIKE '%추석%' AND content LIKE '%휴강%';
  // findBy + 컬럼명 + ContainingAnd + 컬럼명 + Containing
  public List<ISSUE> findByTitleContainingAndContentContaining(String title, String content) {
    List<ISSUE> list = repository.findByTitleContainingAndContentContaining(title, content);
    
    return list;
  }
  
  // 7) title 대소문자 무시 검색
  public List<ISSUE> findByTitleContainingIgnoreCase(String title) {
    List<ISSUE> list = repository.findByTitleContainingIgnoreCase(title);
    
    return list;
  }
  
  // 8) rdate 기준 내림 차순 정렬
  public List<ISSUE> findAllByOrderByRdateDesc() {
    List<ISSUE> list = repository.findAllByOrderByRdateDesc();
    
    return list;
  }
  
  // 9) 조회, Primary Key를 이용한 조회
  public ISSUE findById(long pk) {
    Optional<ISSUE> optional = repository.findById(pk);
    
    if (optional.isPresent()) {
      ISSUE issue = optional.get();
      return issue;
    } else {
      return null;
    }
  }
  
  // 9_1) 조회, issueno를 이용한 조회
  public ISSUE findByIssueno(long pk) {
    ISSUE issue = repository.findByIssueno(pk);

    return issue;
  }
  
  // 10) 수정 조회 + 수정 처리
  public ISSUE update(ISSUEDTO issueDTO) {
    ISSUE issue = repository.findByIssueno((long)issueDTO.getIssueno()); // 수정할 레코드를 로딩
    System.out.println(issue.toString());
    
    issue.setTitle(issueDTO.getTitle()); // DTO -> Entity 이동
    issue.setContent(issueDTO.getContent());
   
    ISSUE savedEntity = repository.save(issue); // 기존에 등록된 Entity를 로딩했음으로 update가 발생함.
    
    return savedEntity;
  }
  
  // 11) 삭제, Primary Key를 삭제
  public boolean deleteById(long pk) {
    Optional<ISSUE> optional = repository.findById(pk);
    
    if (optional.isPresent()) {
      repository.deleteById(pk);
      return true;
    } else {
      return false;
    }
  }
  
  // 12) 조회수 증가
  public boolean increaseCnt(long pk) {
    Optional<ISSUE> optional = repository.findById(pk);
    
    if (optional.isPresent()) {
      repository.increaseCnt(pk);
      System.out.println("-> 조회수 증가: " + pk);
      return true;
    } else {
      return false;
    }
  }

  // 13) 날짜 구간 검색
//SELECT issueno, title, content, cnt, rdate 
//FROM issue 
//WHERE (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-25');
  public List<ISSUE> findByRdatePeriod(String start_date, String end_date) {
    List<ISSUE> list = repository.findByRdatePeriod(start_date, end_date);
    
    return list;
  }

  // 14) 날짜 구간 검색하여 날짜 내림 차순 정렬
  //  SELECT issueno, title, content, cnt, rdate 
  //  FROM issue 
  //  WHERE (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-26')
  //  ORDER BY rdate DESC;  public List<ISSUE> findByRdatePeriod(String start_date, String end_date) {
  public List<ISSUE> findByRdatePeriodDesc(String start_date, String end_date) {
    List<ISSUE> list = repository.findByRdatePeriodDesc(start_date, end_date);
    
    return list;
  }
  
  // 15) 페이징 처리, Oracle 12C+
  public List<ISSUE> findAllByOrderByIssuenoDesc(int page_number, int page_size) {
    Pageable pageable = PageRequest.of(page_number, page_size); // 페이징 번호, 페이지당 레코드 수 

    Page<ISSUE> pages = repository.findAllByOrderByIssuenoDesc(pageable);
    List<ISSUE> list = pages.getContent(); // 페이징 목록 추출
    return list;
  }  
  
  // 16) 페이징 처리 + 검색
  public List<ISSUE> find_paging_search(int page_number, int page_size, String word) {
    Pageable pageable = PageRequest.of(page_number, page_size); // 페이징 번호, 페이지당 레코드 수 

    Page<ISSUE> pages = repository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByIssuenoDesc(pageable,word, word);
    List<ISSUE> list = pages.getContent(); // 페이징 목록 추출
    return list;
  }  
  
}




