package dev.jpa.resort.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jpa.resort.tool.Tool;

@Service
@Transactional
public class EmployeeService {
  @Autowired
  EmployeeRepository employeeRepository;
  
  public EmployeeService() {
    System.out.println("-> EmployeeService created");
  }
  
  /** 중복 아이디 검사 */
  public int checkId(String id) {
    return employeeRepository.countById(id);
  }
    
  /** 등록 */
  public Employee save(EmployeeDTO employeeDTO) {
    employeeDTO.setRdate(Tool.getDate());
    Employee savedEntity = employeeRepository.save(employeeDTO.toEntity());
    System.out.println("-> employeeno: " + savedEntity.getEmployeeno());
    
    return savedEntity;
  }
  
  /** 전체 목록 */
  public List<Employee> findAllByOrderByIdAsc() {
    List<Employee> list = employeeRepository.findAllByOrderByIdAsc();
    
    return list;
  }
  
  /**
   * 조회
   * @param employeeno
   * @return
   */
  public Employee findByEmployeeno(long employeeno ) {
    Employee employee = employeeRepository.findByEmployeeno(employeeno);
    
    return employee;
  }

  /**
   * id 를 이용한 조회, contents 파일 업로드시 사용
   * @param employeeno
   * @return
   */
  public Employee findById(String id ) {
    Employee employee = employeeRepository.findById(id);
    
    return employee;
  }
  
  /**
   * PK 컬럼 조회 자동 지원
   * @param employeeno
   * @return
   */
  public Optional<Employee> fineById(long employeeno ) {
    return employeeRepository.findById(employeeno); // PK 컬럼을 사용함.
  }
  
  /**
   * 패스워드 변경
   * @param employeeno
   * @param password
   */
  public int updatePassword(String id, String password) {
//    int cnt = 0;
//    
//    try {
//      employeeRepository.updatePassword(employeeno, password);
//      cnt=1;
//    }catch (Exception e) {
//      System.out.println(e.toString());
//    }
//    
//    return sw;
    int cnt = employeeRepository.updatePassword(id, password);
    System.out.println("-> EmployeeService cnt:" + cnt);
    
    return cnt;
    
  }
  
  /**
   * 로그인 처리
   * @param id
   * @param password
   * @return
   */
  public int countByIdAndPassword(String id, String password) {
    int cnt = employeeRepository.countByIdAndPassword(id, password);
    
    return cnt;
  }
  
  /**
   * 수정
   * @param mname
   * @param id
   * @param grade
   * @param employeeno
   * @return
   */
  public int update(EmployeeDTO employeeDTO) {
    int  cnt = employeeRepository.update(employeeDTO.getMname(), 
                                                        employeeDTO.getId(), 
                                                        employeeDTO.getGrade(), 
                                                        employeeDTO.getEmployeeno());
    
    return cnt;
  }
  
  /**
   * 삭제
   * @param employeeno
   */
  public int delete(long employeeno) {
    int cnt = 0;
    
    try {
      employeeRepository.deleteById(employeeno);
      cnt=1;
    }catch (Exception e) {
      System.out.println(e.toString());
    }
    
    return cnt;
    
  }
  
}








