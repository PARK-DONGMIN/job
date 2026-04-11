package dev.jpa.resort.contents;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @ToString
public class Contents {
  /** 컨텐츠 번호 */
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTENTS_SEQ")
  @SequenceGenerator(name = "CONTENTS_SEQ", sequenceName = "CONTENTS_SEQ", allocationSize = 1)    
  private long contentsno;
  /** 관리자 권한의 회원 번호 */
  private long employeeno;
  /** 카테고리 번호 */
  private long cateno;
  /** 제목 */
  private String title = "";
  /** 내용 */
  private String content = "";
  /** 추천수 */
  private int recom;
  /** 조회수 */
  private int cnt = 0;
  /** 댓글수 */
  private int replycnt = 0;
  /** 패스워드 */
  private String password = "";
  /** 검색어 */
  private String word = "";
  /** 등록 날짜 */
  private String rdate = "";
  /** 지도 */
  private String map = "";
  /** Youtube */
  private String youtube = "";

  /** mp4 */
  private String mp4 = "";
  
  /** 숨기기 여부 */
  private String visible = "Y";
  
  // 파일 업로드 관련
  // -----------------------------------------------------------------------------------
  /** 메인 이미지 */
  private String file1 = "";
  /** 실제 저장된 메인 이미지 */
  private String file1saved = "";
  /** 메인 이미지 preview */
  private String thumb1 = "";
  /** 메인 이미지 크기 */
  private long size1 = 0;

  // 쇼핑몰 상품 관련
  // -----------------------------------------------------------------------------------
  /** 정가 */
  private int price = 0;
  /** 할인률 */
  private int dc = 0;
  /** 판매가 */
  private int saleprice = 0;
  /** 포인트 */
  private int point = 0;
  /** 재고 수량 */
  private int salecnt = 0;
  
  public Contents() {
    
  }
  
  // LLM 관련
  // -----------------------------------------------------------------------------------  
  /** 감정 분석, 0: 부정, 1: 긍정 */
  private int emotion = 1;
  
  /** 200자 요약 */
  private String summary = "";
  
  /**
   * 최초 글만 등록, contentsno는 자동 생성됨으로 선언 제외
   * @param contentsno
   * @param employeeno
   * @param cateno
   * @param title
   * @param content
   * @param password
   */
  public Contents(long employeeno, long cateno, String title, String content, String password, String word, String rdate) {
    super();
    this.employeeno = employeeno;
    this.cateno = cateno;
    this.title = title;
    this.content = content;
    this.password = password;
    this.word = word;
    this.rdate = rdate;
  }
  
  /**
   * 최초 글 + 파일 등록, contentsno는 자동 생성됨으로 선언 제외
   * @param contentsno
   * @param employeeno
   * @param cateno
   * @param title
   * @param content
   * @param password
   * @param file1
   * @param file1saved
   * @param thumb1
   * @param size1
   * @param emotion
   * @param summary
   */
  public Contents(long employeeno, long cateno, String title, String content, String password, String word, String rdate,
      String file1, String file1saved, String thumb1, long size1, int emotion, String summary) {
    super();
    this.employeeno = employeeno;
    this.cateno = cateno;
    this.title = title;
    this.content = content;
    this.password = password;
    this.word = word;
    this.rdate = rdate;    
    this.file1 = file1;
    this.file1saved = file1saved;
    this.thumb1 = thumb1;
    this.size1 = size1;
    this.emotion = emotion;
    this.summary = summary;
  }
   
  public ContentsDTO toDTO() {
    return new ContentsDTO(
        this.contentsno,
        this.employeeno,
        this.cateno,
        this.title,
        this.content,
        this.cnt, 
        this.password,
        this.word,
        this.rdate,    
        this.youtube,
        this.map,
        this.file1,
        this.file1saved,
        this.thumb1,
        this.size1,
        this.emotion,
        this.summary);
  }
}




