package dev.jpa.resort.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class ISSUEDTO {
  /** 공지사항 번호 */
  private long issueno;
  
  /** 제목 */
  private String title;
  
  /** 사건, 사고 내용 */
  private String content;
  
  /** 조회수 */
  private int cnt;
  
  /** 등록 날짜 */
  private String rdate;
  
  public ISSUE toEntity() {
    return new ISSUE(0, this.title, this.content, this.cnt, this.rdate);   
    
  }
  
}






