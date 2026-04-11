package dev.jpa.resort.contents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestBody;

import dev.jpa.resort.employee.Employee;
import dev.jpa.resort.tool.LLMRequestConfig;
import dev.jpa.resort.tool.Tool;
import dev.jpa.resort.tool.Upload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/contents")
public class ContentsCont {
  @Autowired
  private ContentsService contentsService;
  
  @Autowired
  private LLMRequestConfig llmRequestConfig;

  public ContentsCont() {
    System.out.println("-> ContentsCont created.");
  }

  /**
   * 전체 목록, http://localhost:9100/contents/list_all/12
   * 
   * @return
   */
  @GetMapping(path = "/list_all/{cateno}")
  public ResponseEntity<List<Contents>> list_all(@PathVariable("cateno") Long cateno) {
    return ResponseEntity.ok(contentsService.findByCatenoOrderByContentsnoDesc(cateno));
  }

  /**
   * 페이징 + 검색, http://localhost:9100/contents/list_all_paging_search?cateno=12&word=컨저링&page=0&size=10
   * @param cateno 카테고리 번호
   * @param word 검색어
   * @param page 페이지 번호, 0~
   * @param size 페이지당 레코드 수
   * @return
   */
  @GetMapping(path="/list_all_paging_search")
  public ResponseEntity<PageResponse<Contents>> list_all_paging(
      @RequestParam(name="cateno", defaultValue = "0") long cateno,
      @RequestParam(name="word", defaultValue = "") String word,
      @RequestParam(name="page", defaultValue = "0") int page,
      @RequestParam(name="size", defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size, Sort.by("contentsno").descending());
    Page<Contents> p = this.contentsService.list_all_paging_search(cateno, word, pageable);

    return ResponseEntity.ok(new PageResponse<>(
        p.getContent(),
        p.getNumber(),
        p.getSize(),
        p.getTotalElements(),
        p.getTotalPages()
    ));
  }  
  
  /**
   * 전체 목록, http://localhost:9100/contents/list_all_paging?cateno=12&page=0&size=10
   * @param cateno 카테고리 번호
   * @param page 페이지 번호, 0~
   * @param size 페이지당 레코드 수
   * @return
   */
  @GetMapping(path="/list_all_paging")
  public ResponseEntity<PageResponse<Contents>> list_all_paging(
      @RequestParam(name="cateno", defaultValue = "0") long cateno,
      @RequestParam(name="page", defaultValue = "0") int page,
      @RequestParam(name="size", defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size, Sort.by("contentsno").descending());
    Page<Contents> p = this.contentsService.findByCatenoOrderByContentsnoDesc(cateno, pageable);

    return ResponseEntity.ok(new PageResponse<>(
        p.getContent(),
        p.getNumber(),
        p.getSize(),
        p.getTotalElements(),
        p.getTotalPages()
    ));
  }  
  

  /**
   * 등록 처리 http://localhost:9100/contents/create
   * 
   * @return
   */
  @PostMapping(value = "/create")
  public ResponseEntity<Contents> create(@ModelAttribute("contentsDTO") ContentsDTO contentsDTO) {
    // ------------------------------------------------------------------------------
    // 파일 전송 코드 시작
    // ------------------------------------------------------------------------------
    String file1 = ""; // 원본 파일명 image
    String file1saved = ""; // 저장된 파일명, image
    String thumb1 = ""; // preview image

    String upDir = Tool.getServerDir("contents"); // 파일을 업로드할 폴더 준비
    // upDir = upDir + "/" + 한글을 제외한 카테고리 이름
    System.out.println("-> upDir: " + upDir);

    // 전송 파일이 없어도 file1MF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF'
    // value='' placeholder="파일 선택">
    MultipartFile mf = contentsDTO.getFile1MF();

    if (mf != null) { // 파일 전송
      file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
      System.out.println("-> 원본 파일명 산출 file1: " + file1);

      long size1 = mf.getSize(); // 파일 크기
      if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
        if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir);

          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
            thumb1 = Tool.preview(upDir, file1saved, 200, 150);
          }

          contentsDTO.setFile1(file1); // 순수 원본 파일명
          contentsDTO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
          contentsDTO.setThumb1(thumb1); // 원본이미지 축소판
          contentsDTO.setSize1(size1); // 파일 크기

        }
      }
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------
    } else { // 글만 등록
      contentsDTO.setFile1("none1.png"); // 순수 원본 파일명
      contentsDTO.setFile1saved("none1.png"); // 저장된 파일명(파일명 중복 처리)
      contentsDTO.setThumb1("none1.png"); // 원본이미지 축소판
      contentsDTO.setSize1((int) (23.1 * 1024)); // 파일 크기
    }

    // ------------------------------------------------------------------------------
    // 감정 분석 코드 시작
    // ------------------------------------------------------------------------------
    // FastAPI 서버 URL (포트는 환경에 맞게 조정)
    String url = "http://localhost:8000/emotion";
    
    // HTTP 헤더 설정 (JSON)
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    // 요청 바디에 담을 데이터
    System.out.println("-> SpringBoot_FastAPI_KEY: " + this.llmRequestConfig.getSpringBoot_FastAPI_KEY());
    
    Map<String, Object> body = new HashMap<>();
    body.put("SpringBoot_FastAPI_KEY", this.llmRequestConfig.getSpringBoot_FastAPI_KEY());
    body.put("content", contentsDTO.getContent());
    
    // HttpEntity로 헤더 + 바디 묶기
    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);
    
    // POST 요청 보내고, 결과를 String으로 받기
    String response = this.llmRequestConfig.getRestTemplate().postForObject(url, requestEntity, String.class);
    System.out.println("-> response: " + response);
    
    // 감정 분석 코드, String -> JSON:  {"res":0}
    Map<String, Object> map = Tool.strToMap(response);
    contentsDTO.setEmotion((int)(map.get("res")));    
    // ------------------------------------------------------------------------------
    // 감정 분석 코드 종료
    // ------------------------------------------------------------------------------
    
    // ------------------------------------------------------------------------------
    // 요약 코드 시작
    // ------------------------------------------------------------------------------
    // FastAPI 서버 URL (포트는 환경에 맞게 조정)
    url = "http://localhost:8000/summary";
    
    // POST 요청 보내고, 결과를 String으로 받기
    response = this.llmRequestConfig.getRestTemplate().postForObject(url, requestEntity, String.class);
    System.out.println("-> response: " + response);
    
    // 감정 분석 코드, String -> JSON:  {"res":0}
    map = Tool.strToMap(response);
    contentsDTO.setSummary((String)(map.get("res")));    
    // ------------------------------------------------------------------------------
    // 요약 코드 종료
    // ------------------------------------------------------------------------------
        
    // System.out.println("-> contentsDTO.title: " + contentsDTO.getTitle());
    Contents contents = this.contentsService.save(contentsDTO);

    return ResponseEntity.ok(contents);
  }

  /**
   * 조회, http://localhost:9100/contents/read/1
   * @param contentsno
   * @return
   */
  @GetMapping(path="/read/{contentsno}")
  public ResponseEntity<ContentsDTO> read(@PathVariable("contentsno") Long contentsno) {
    System.out.println("-> contentsno: " + contentsno);
    this.contentsService.increaseCnt(contentsno); // 조회수 증가
    ContentsDTO  contentsDTO = this.contentsService.findByContentsno(contentsno);
    
    System.out.println("-> contentsDTO: " + contentsDTO.toString());
    
    return ResponseEntity.ok(contentsDTO);
    
  }
  
  /**
   * 글 수정, http://localhost:9100/contents/update_text?title=제목&content=내용&word=영화&contentsno=3&password=123
   * @param title
   * @param content
   * @param word
   * @param contentsno
   * @param password
   * @return 0: 수정 실패, 1: 수정 성공, 2: 패스워드 실패
   */
  @PostMapping(path="/update_text")
  public ResponseEntity<Integer> update_text(
      @RequestParam(name="title", defaultValue = "") String title,
      @RequestParam(name="content", defaultValue = "") String content,
      @RequestParam(name="word", defaultValue = "") String word,
      @RequestParam(name="contentsno", defaultValue = "") int contentsno,
      @RequestParam(name="password", defaultValue = "" ) String password) {
    int sw = 0;
    int cnt = 0;
  
    cnt = this.contentsService.password_check(contentsno, password);
    
    if (cnt == 1) {
      cnt = this.contentsService.update_text(title, content, word, contentsno);
      sw = cnt; // 0 or 1
    } else {
      sw = 2; // 패스워드 불일치
    }
    
    return ResponseEntity.ok(sw);
  }

  /**
   * 파일 수정, http://localhost:9100/contents/update_file1
   * @param file1MF
   * @param contentsno
   * @param password
   * @return
   */
  @PostMapping(path="/update_file1")
  public ResponseEntity<Integer> update_file1(
      @RequestParam(name="file1MF", defaultValue = "") MultipartFile file1MF,
      @RequestParam(name="contentsno", defaultValue = "0" ) long contentsno,
      @RequestParam(name="password", defaultValue = "" ) String password) {
    int sw = 0;
    int cnt = 0;
  
    cnt = this.contentsService.password_check(contentsno, password);
    
    if (cnt == 1) {
      String upDir = Tool.getServerDir("contents");
      
      // 파일 삭제
      ContentsDTO  contentsDTO  = this.contentsService.findByContentsno(contentsno);
      Tool.deleteFile(upDir, contentsDTO.getFile1saved());
      Tool.deleteFile(upDir, contentsDTO.getThumb1());

      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = ""; // 원본 파일명 image
      String file1saved = ""; // 저장된 파일명, image
      String thumb1 = ""; // preview image
      
      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF'
      // value='' placeholder="파일 선택">
      MultipartFile mf = file1MF;

      if (mf != null) { // 파일 전송
        file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
        System.out.println("-> 원본 파일명 산출 file1: " + file1);

        long size1 = mf.getSize(); // 파일 크기
        if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
          if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir);

            if (Tool.isImage(file1saved)) { // 이미지인지 검사
              // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
              thumb1 = Tool.preview(upDir, file1saved, 200, 150);
            }

            contentsDTO.setFile1(file1); // 순수 원본 파일명
            contentsDTO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
            contentsDTO.setThumb1(thumb1); // 원본이미지 축소판
            contentsDTO.setSize1(size1); // 파일 크기

          }
        }
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 종료
        // ------------------------------------------------------------------------------

        cnt = this.contentsService.update_file1(file1, file1saved, thumb1, size1, contentsno);
        sw = cnt; // 0 or 1

      } else { // 전송할 파일이 없음.
        sw = 3;
      }
      
    } else {
      sw = 2; // 패스워드 불일치
    }
    
    return ResponseEntity.ok(sw);
  }
  
  /**
   * 파일 삭제, http://localhost:9100/contents/delete_file1
   * @param file1
   * @param file1saved
   * @param thumb1
   * @param size1
   * @param contentsno
   * @param password
   * @return 0: 이미지 삭제 실패, 1: 이미지 삭제 성공, 2: 패스워드 불일치, 3: 기본 이미지 삭제 실패
   */
  @PostMapping(path="/delete_file1")
  public ResponseEntity<Integer> delete_file1(
      @RequestParam(name="contentsno", defaultValue = "0" ) long contentsno,
      @RequestParam(name="password", defaultValue = "" ) String password) {
    int sw = 0;
    int cnt = 0;
  
    cnt = this.contentsService.password_check(contentsno, password);
    
    if (cnt == 1) {
      String dir = Tool.getServerDir("contents");
      
      // 파일 삭제
      ContentsDTO  contentsDTO  = this.contentsService.findByContentsno(contentsno);
      
      if (contentsDTO.getFile1saved().equals("none1.png") == false) {
        Tool.deleteFile(dir, contentsDTO.getFile1saved());
        Tool.deleteFile(dir, contentsDTO.getThumb1());      
        
        cnt = this.contentsService.update_file1("none1.png", "none1.png", "none1.png", 0, contentsno);
        sw = cnt; // 0 or 1
      } else {
        sw = 3; // 기본 이미지 삭제 시도
      }
    } else {
      sw = 2; // 패스워드 불일치
    }
    
    return ResponseEntity.ok(sw);
  }
  
  /**
   * 한건의 레코드 삭제, http://localhost:9100/contents/delete?contentsno=31&password=1234
   * @param contentsno
   * @param password
   * @return 0: 삭제 실패, 1: 삭제 성공, 2: 패스워드 불일치
   */
  @PostMapping(path="/delete")
  public ResponseEntity<Integer> delete(
      @RequestParam(name="contentsno", defaultValue = "0" ) long contentsno,
      @RequestParam(name="password", defaultValue = "" ) String password) {
    int sw = 0;
    int cnt = 0;
  
    cnt = this.contentsService.password_check(contentsno, password);
    
    if (cnt == 1) {
      String dir = Tool.getServerDir("contents");
      
      ContentsDTO  contentsDTO  = this.contentsService.findByContentsno(contentsno);
      
      if (contentsDTO.getFile1saved().equals("none1.png") == false) {
        Tool.deleteFile(dir, contentsDTO.getFile1saved());
        Tool.deleteFile(dir, contentsDTO.getThumb1());      
      }
      
      cnt = this.contentsService.delete(contentsno, password);
      sw = cnt; // 0 or 1
       
    } else {
      sw = 2; // 패스워드 불일치
    }
    
    return ResponseEntity.ok(sw);
  } 
  
  /**
   * youtube 변경
   * map, http://localhost:9100/contents/youtube?contentsno=31&password=1234&youtube=youtube 스크립트
   * @param contentsno
   * @param password
   * @param youtube
   * @return 0: map 수정 실패, 1: map 수정 성공, 2: 패스워드 불일치
   */
  @PostMapping(path="/youtube")
  public ResponseEntity<Integer> youtube(
      @RequestParam(name="youtube", defaultValue = "" ) String youtube,
      @RequestParam(name="contentsno", defaultValue = "0" ) long contentsno,
      @RequestParam(name="password", defaultValue = "" ) String password) {
    int sw = 0;
    int cnt = 0;
  
    cnt = this.contentsService.password_check(contentsno, password);
    
    if (cnt == 1) {
      cnt = this.contentsService.youtube(youtube, contentsno, password);
      sw = cnt; // 0 or 1
       
    } else {
      sw = 2; // 패스워드 불일치
    }
    
    return ResponseEntity.ok(sw);
  } 

  /**
   * map, http://localhost:9100/contents/map?contentsno=31&password=1234&map=지도 스크립트
   * @param contentsno
   * @param password
   * @return 0: map 수정 실패, 1: map 수정 성공, 2: 패스워드 불일치
   */
  @PostMapping(path="/map")
  public ResponseEntity<Integer> map(
      @RequestParam(name="map", defaultValue = "" ) String map,
      @RequestParam(name="contentsno", defaultValue = "0" ) long contentsno,
      @RequestParam(name="password", defaultValue = "" ) String password) {
    int sw = 0;
    int cnt = 0;
  
    cnt = this.contentsService.password_check(contentsno, password);
    
    if (cnt == 1) {
      cnt = this.contentsService.map(map, contentsno, password);
      sw = cnt; // 0 or 1
       
    } else {
      sw = 2; // 패스워드 불일치
    }
    
    return ResponseEntity.ok(sw);
  } 
  
}









