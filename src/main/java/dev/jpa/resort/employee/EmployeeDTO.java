package dev.jpa.resort.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class EmployeeDTO {
  /** 사원 번호, 식별자, sequence 자동 생성됨. */
  private Long employeeno;

  /** 아이디 */
  private String id;

  /** 패스워드 */
  private String password;

  /** 사원명 */
  private String mname;

  /** 입사 날짜 */
  private String rdate;

  /** 사원 직책 */
  private int grade;

  /** 새로운 패스워드 */
  private String new_password;
  
  /**
   * DTO(Java) -> Entity(DBMS)
   * @return
   */
  public Employee toEntity() {
    return new Employee(null, this.id, this.password, this.mname, this.rdate, this.grade);
  }
  
}



