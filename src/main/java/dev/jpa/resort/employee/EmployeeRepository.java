package dev.jpa.resort.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// Long: 식별자(PK) 필드의 타입, MyBATIS+DAO 역활
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  // public Employee findByIdAndPassword(String uid, String password);
  
  /** 1) 중복 아이디 검사 */
  public int countById(String id);
  
  /** 2) 회원 가입 자동 지원, save() */
  
  /** 3) 전체 목록 */
  public List<Employee> findAllByOrderByIdAsc();
  
  /** 4) 조회 */
  public Employee findByEmployeeno(long employeeno);
  public Employee findById(String id);
  
  /** 4_1) PK 컬럼 조회 자동 지원 */
  
  /**
   * 5) 패스워드 변경
   * @param id
   * @param password
   * @return 변경된 레코드 수
   */
  @Modifying
  @Query(value="UPDATE employee SET password=:password WHERE id=:id", nativeQuery = true)
  public int updatePassword(@Param("id") String id, @Param("password") String password);

  /**
   * 패스워드 검사, 로그인
   * @param id
   * @param password
   * @return 1: 일치, 0: 불일치
   */
  public int countByIdAndPassword(String id, String password);
 
  /**
   * 회원 정보 수정
   * @param mname
   * @param id
   * @param grade
   * @param employeeno
   * @return
   */
  @Modifying
  @Query(value="UPDATE employee SET mname=:mname, id=:id, grade=:grade WHERE employeeno=:employeeno", nativeQuery = true)
  public int update(@Param("mname") String mname, 
                           @Param("id") String id,
                           @Param("grade") int grade,
                           @Param("employeeno") long employeeno);


  /** 삭제, 자동 지원 */
  
  
}







