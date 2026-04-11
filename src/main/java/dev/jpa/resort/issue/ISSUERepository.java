package dev.jpa.resort.issue;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

// <ISSUE, Long>: Entity class, PK type
public interface ISSUERepository extends JpaRepository<ISSUE, Long>{
  // 1) 등록
  
  // 2) 목록
  
  // 3) title 컬럼으로 검색, WHERE title = '연휴 휴무 안내';
  // findBy + 컬럼명
  List<ISSUE> findByTitle(String title);
  
  // 4) WHERE title LIKE '%연휴%'
  // findBy + 컬럼명 + Containing
  List<ISSUE> findByTitleContaining(String title);
  
  // 5) WHERE title LIKE '%연휴%' OR content LIKE '%연휴%';
  // findBy + 컬럼명 + ContainingOr + 컬럼명 + Containing
  List<ISSUE> findByTitleContainingOrContentContaining(String title, String content);

  // 6) WHERE title LIKE '%연휴%' AND content LIKE '%연휴%';
  // findBy + 컬럼명 + ContainingAnd + 컬럼명 + Containing
  List<ISSUE> findByTitleContainingAndContentContaining(String title, String content);
  
  // 7) title 대소문자 무시 검색
  // ContainingAnd + 컬럼명 + ContainingIgnoreCase
  List<ISSUE> findByTitleContainingIgnoreCase(String title);
  
  // 8) rdate 기준 내림 차순 정렬
  List<ISSUE> findAllByOrderByRdateDesc();
  
  // 9) 조회
  
  // 10) 수정 조회
  ISSUE findByIssueno(long issueno);
 
  // 11) 삭제
  
  // 12) 조회수 증가
  @Transactional
  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(value="UPDATE issue SET cnt = cnt + 1 WHERE issueno=:issueno", nativeQuery = true)
  void increaseCnt(@Param("issueno") Long issueno);
  
  // 13) 날짜 구간 검색
//  SELECT issueno, title, content, cnt, rdate 
//  FROM issue 
//  WHERE (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-25');
  @Query(value=""
      + "SELECT issueno, title, content, cnt, rdate "
      + "FROM issue "
      + "WHERE (SUBSTR(rdate, 1, 10) >= :start_date) AND (SUBSTR(rdate, 1, 10) <= :end_date)", nativeQuery = true)
  List<ISSUE> findByRdatePeriod(@Param("start_date") String start_date, @Param("end_date") String end_date);
  
  // 14) 날짜 구간 검색하여 날짜 내림 차순 정렬
  //  SELECT issueno, title, content, cnt, rdate 
  //  FROM issue 
  //  WHERE (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-26')
  //  ORDER BY rdate DESC;
  @Query(value=""
              + "SELECT issueno, title, content, cnt, rdate " 
              + "FROM issue " 
              + "WHERE (SUBSTR(rdate, 1, 10) >= :start_date) AND (SUBSTR(rdate, 1, 10) <= :end_date) "
              + "ORDER BY rdate DESC", nativeQuery = true)
  List<ISSUE> findByRdatePeriodDesc(@Param("start_date") String start_date, @Param("end_date") String end_date);
  
  // 15) 페이징 처리
  Page<ISSUE> findAllByOrderByIssuenoDesc(Pageable pageble);
  
  // 16) 페이징 검색 처리
  Page<ISSUE> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByIssuenoDesc(Pageable pageble, String title, String content);

  
  
  
  
}






