package dev.jpa.resort.issue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/issue")
public class ISSUECont {
  @Autowired
  private ISSUEService issueService;

  public ISSUECont() {
    System.out.println("-> ISSUECont created.");
  }

  /**
   * 등록, http://localhost:9100/issue/save
   * 
   * @param issueDTO
   * @return
   */
  @PostMapping(path = "/save")
  public ResponseEntity<ISSUE> save(@RequestBody ISSUEDTO issueDTO) {
    ISSUE savedEntity = issueService.save(issueDTO);

    return ResponseEntity.ok(savedEntity);
  }

  /**
   * 전체 목록, http://localhost:9100/issue/find_all
   * 
   * @return
   */
  @GetMapping(path = "/find_all")
  public List<ISSUE> findAll() {
    List<ISSUE> list = issueService.findAll();

    return list;
  }

  /**
   * 전체 목록, http://localhost:9100/issue/find_by_title?title=연휴 휴무 안내
   * 
   * @return
   */
  @GetMapping(path = "/find_by_title")
  public List<ISSUE> findByTitle(@RequestParam(name = "title", defaultValue = "") String title) {
    List<ISSUE> list = issueService.findByTitle(title);

    return list;
  }

  /**
   * 4) WHERE title LIKE '%연휴%'
   * http://localhost:9100/issue/find_by_title_containing?title=연휴
   * 
   * @return
   */
  @GetMapping(path = "/find_by_title_containing")
  public List<ISSUE> findByTitleContaining(@RequestParam(name = "title", defaultValue = "") String title) {
    List<ISSUE> list = issueService.findByTitleContaining(title);

    return list;
  }

  /**
   * 5) WHERE title LIKE '%연휴%' OR content LIKE '%연휴%'; findBy + 컬럼명 +
   * ContainingOr + 컬럼명 + Containing
   * http://localhost:9100/issue/find_by_title_or_content?title=발생&content=Merry
   * 
   * @return
   */
  @GetMapping(path = "/find_by_title_or_content")
  public List<ISSUE> findByTitleContainingOrContentContaining(
      @RequestParam(name = "title", defaultValue = "") String title,
      @RequestParam(name = "content", defaultValue = "") String content) {
    List<ISSUE> list = issueService.findByTitleContainingOrContentContaining(title, content);

    return list;
  }

  /**
   * 6) WHERE title LIKE '%추석%' AND content LIKE '%휴강%'; findBy + 컬럼명 +
   * ContainingAnd + 컬럼명 + Containing
   * http://localhost:9100/issue/find_by_title_and_content?title=휴강&content=추석
   * 
   * @return
   */
  @GetMapping(path = "/find_by_title_and_content")
  public List<ISSUE> findByTitleContainingAndContentContaining(
      @RequestParam(name = "title", defaultValue = "") String title,
      @RequestParam(name = "content", defaultValue = "") String content) {
    List<ISSUE> list = issueService.findByTitleContainingAndContentContaining(title, content);

    return list;
  }

  /**
   * 7) title 대소문자 무시 검색 ContainingAnd + 컬럼명 + ContainingIgnoreCase
   * http://localhost:9100/issue/find_by_title_ignorecase?title=cloud
   * 
   * @return
   */
  @GetMapping(path = "/find_by_title_ignorecase")
  public List<ISSUE> findByTitleContainingIgnoreCase(@RequestParam(name = "title", defaultValue = "") String title) {
    List<ISSUE> list = issueService.findByTitleContainingIgnoreCase(title);

    return list;
  }

  /**
   * 8) rdate 기준 내림 차순 정렬 컬럼명 + Desc
   * http://localhost:9100/issue/find_by_rdate_desc
   * 
   * @return
   */
  @GetMapping(path = "/find_by_rdate_desc")
  public List<ISSUE> findAllByOrderByRdateDesc() {
    List<ISSUE> list = issueService.findAllByOrderByRdateDesc();

    return list;
  }

//  /**
//   * 9) 조회, Primary Key를 이용한 조회
//   * http://localhost:9100/issue/find_by_id?pk=3
//   * @return
//   */
//  @GetMapping(path = "/find_by_id")
//  public ISSUE findById (@RequestParam(name="pk", defaultValue="") long pk) {
//    ISSUE issue = issueService.findById(pk);
//
//    return issue;
//  }
//  
//  /**
//   * 9_1) 조회, Primary Key를 이용한 조회
//   * RequestMapping("/issue") + path = "/{pk}"
//   * http://localhost:9100/issue/3
//   * @return
//   */
//  @GetMapping(path = "/{pk}")
//  public ISSUE findByIdPath (@PathVariable("pk") long pk) {
//    ISSUE issue = issueService.findById(pk);
//
//    return issue;
//  }
//  
  /**
   * 10) 수정, http://localhost:9100/issue/update
   * 
   * @param issueDTO
   * @return
   */
  @PutMapping(path = "/update")
  public ResponseEntity<ISSUE> update(@RequestBody ISSUEDTO issueDTO) {
    ISSUE savedEntity = issueService.update(issueDTO);

    return ResponseEntity.ok(savedEntity);
  }

  /**
   * 11) 삭제, http://localhost:9100/issue/3
   * 
   * @param issueDTO
   * @return
   */
  @DeleteMapping(path = "/{pk}")
  public ResponseEntity<Void> delete(@PathVariable("pk") long pk) {
    boolean sw = issueService.deleteById(pk);

    if (sw) {
      return ResponseEntity.ok().build(); // 성공적으로 삭제시 200
    } else {
      return ResponseEntity.notFound().build(); // 404 에러 발생
    }

  }

  /**
   * 12) 조회 + 조회수 증가, Primary Key를 이용한 조회 RequestMapping("/issue") + path =
   * "/{pk}" http://localhost:9100/issue/5
   * 
   * @return
   */
  @GetMapping(path = "/{pk}")
  public ResponseEntity<ISSUE> findByIdRead(@PathVariable("pk") long pk) {
    if (issueService.increaseCnt(pk) == true) { // 조회수 증가
      // @Modifying(clearAutomatically = true, flushAutomatically = true) 선언해야 새로 레코드를
      // 읽어옴.
      ISSUE issue = issueService.findById(pk); // 조회수 반영,
      System.out.println("-> 조회: " + pk);

      return ResponseEntity.ok(issue);
    } else {
      return ResponseEntity.notFound().build(); // 404
    }
  }

  /**
   * 13) 날짜 구간 검색
   * http://localhost:9100/issue/find_by_rdate_period?start_date=2025-09-26&end_date=2025-09-27
   * SELECT issueno, title, content, cnt, rdate FROM issue WHERE (SUBSTR(rdate, 1,
   * 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <= '2025-09-25');
   * 
   * @return
   */
  @GetMapping(path = "/find_by_rdate_period")
  public List<ISSUE> findByRdatePeriod(@RequestParam(name = "start_date", defaultValue = "") String start_date,
      @RequestParam(name = "end_date", defaultValue = "") String end_date) {
    List<ISSUE> list = issueService.findByRdatePeriod(start_date, end_date);

    return list;
  }

  /**
   * // 14) 날짜 구간 검색하여 날짜 내림 차순 정렬
   * http://localhost:9100/issue/find_by_rdate_period_desc?start_date=2025-09-26&end_date=2025-09-28
   * // SELECT issueno, title, content, cnt, rdate // FROM issue // WHERE
   * (SUBSTR(rdate, 1, 10) >= '2025-09-25') AND (SUBSTR(rdate, 1, 10) <=
   * '2025-09-26') // ORDER BY rdate DESC;
   * 
   * @return
   */
  @GetMapping(path = "/find_by_rdate_period_desc")
  public List<ISSUE> findByRdatePeriodDesc(@RequestParam(name = "start_date", defaultValue = "") String start_date,
      @RequestParam(name = "end_date", defaultValue = "") String end_date) {
    List<ISSUE> list = issueService.findByRdatePeriodDesc(start_date, end_date);

    return list;
  }

  // 15) 페이징 처리
  // http://localhost:9100/issue/find_paging?page_number=0&page_size=3
  // http://localhost:9100/issue/find_paging?page_number=1&page_size=3
  // http://localhost:9100/issue/find_paging?page_number=2&page_size=3
  // SELECT payno, part, sawon, bonbong, tax, bonus
  // FROM pay2
  // ORDER BY sawon ASC
  // OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY;
  @GetMapping(path = "/find_paging")
  public List<ISSUE> find_paging(
      @RequestParam(name = "page_number", defaultValue = "0") int page_number,
      @RequestParam(name = "page_size", defaultValue = "3") int page_size) {
    List<ISSUE> list = issueService.findAllByOrderByIssuenoDesc(page_number, page_size);

    return list;
  }
  
  // 16) 페이징 검색 처리
  // http://localhost:9100/issue/find_paging?page_number=0&page_size=3&word=연휴
  // http://localhost:9100/issue/find_paging?page_number=1&page_size=3&word=연휴
  // http://localhost:9100/issue/find_paging?page_number=2&page_size=3&word=연휴
  //  SELECT payno, part, sawon, bonbong, tax, bonus
  //  FROM pay2
  //  WHERE part LIKE '%개발%'
  //  ORDER BY sawon ASC
  //  OFFSET 0 ROWS FETCH NEXT 3 ROWS ONLY;
  @GetMapping(path = "/find_paging_search")
  public List<ISSUE> find_paging(      
      @RequestParam(name = "page_number", defaultValue = "0") int page_number,
      @RequestParam(name = "page_size", defaultValue = "3") int page_size,
      @RequestParam(name = "word", defaultValue = "") String word) {
    List<ISSUE> list = issueService.find_paging_search(page_number, page_size, word);

    return list;
  }  

}
