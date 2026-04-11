package dev.jpa.resort.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.boot.model.source.spi.JoinedSubclassEntitySource;
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

import dev.jpa.resort.issue.ISSUE;
import dev.jpa.resort.issue.ISSUEDTO;
import dev.jpa.resort.issue.ISSUEService;

@RestController
@RequestMapping("/employee")
public class EmployeeCont {
  @Autowired
  private EmployeeService employeeService;
  
  public EmployeeCont() {
    System.out.println("-> EmployeeCont created.");
  }
  
  /**
   * 등록, http://localhost:9100/employee/save
   * 
   * @param issueDTO
   * @return
   */
  @PostMapping(path = "/save")
  public ResponseEntity<Employee> save(@RequestBody EmployeeDTO employeeDTO) {
    System.out.println("-> " + employeeDTO.toString());
    
    Employee savedEntity = employeeService.save(employeeDTO);

    return ResponseEntity.ok(savedEntity);
  }

  /**
   * 중복아이디 검사, http://localhost:9100/employee/check_id?id=user1
   * 
   * @param id
   * @return
   */
  @GetMapping(path = "/check_id")
  public ResponseEntity<Integer> check_id(@RequestParam(name="id", defaultValue = "") String id) {
  // public ResponseEntity<Integer> check_id(@RequestBody String id) { // X {"id": "user1" } 
    System.out.println("-> EmployeeCont id: " + id);
    Integer cnt = employeeService.checkId(id);
    System.out.println("-> EmployeeCont cnt: " + cnt);
    
    return ResponseEntity.ok(cnt);
  }
  
  /**
   * 전체 목록, http://localhost:9100/employee/find_all
   * @return
   */
  @GetMapping(path = "/find_all")
  public ResponseEntity<List<Employee>> find_all() {
    return ResponseEntity.ok(employeeService.findAllByOrderByIdAsc());
  }
  
  /**
   * 조회, http://localhost:9100/employee/read/143
   * @param employee
   * @return
   */
  @GetMapping(path="/read/{employeeno}")
  public ResponseEntity<Employee> findByEmployeeno(@PathVariable("employeeno") Long employeeno) {
    Employee employee = employeeService.findByEmployeeno(employeeno);
    
    return ResponseEntity.ok(employee);
    
  }
  
  /**
   * 기존 패스워드 검사 → 패스워드 변경
   * http://localhost:9100/employee/update_password
   * @param employeeno
   * @param password
   * @return
   */
  @PostMapping(path = "/update_password")
  public ResponseEntity<Integer> update_password(@RequestBody EmployeeDTO employeeDTO) {
    
    System.out.println("-> EmployeeCont id: " + employeeDTO.getId());
    System.out.println("-> EmployeeCont password: " + employeeDTO.getPassword());
    System.out.println("-> EmployeeCont new_password: " + employeeDTO.getNew_password());
    
    int sw = 0;
    
    if (employeeService.countByIdAndPassword(employeeDTO.getId(), employeeDTO.getPassword()) !=1) {
      sw = 2; // 현재 패스워드 일치하지 않음.
    } else {
      sw = employeeService.updatePassword(employeeDTO.getId(), employeeDTO.getNew_password()); // 1: 변경 성공
    }
    
//    System.out.println("-> EmployeeCont sw: " + sw);
    return ResponseEntity.ok(sw);
  }
  
  /**
   * 수정, http://localhost:9100/employee/update
   {
     "mname": "나길동",
     "id": "user6",
     "grade": 2,
     "employeeno": 143
   } 
   * @param employeeDTO
   * @return
   */
  @PutMapping(path="/update")
  public ResponseEntity<Integer> update(@RequestBody EmployeeDTO employeeDTO) {
    int cnt = employeeService.update(employeeDTO);
    
    return ResponseEntity.ok(cnt);
  }
  
  /**
   * 삭제, http://localhost:9100/employee/delete/143
   * @param employee
   * @return
   */
  @DeleteMapping(path="/delete/{employeeno}")
  public ResponseEntity<Integer> deleteByEmployeeno(@PathVariable("employeeno") Long employeeno) {
    Optional<Employee> employee = employeeService.fineById(employeeno);
    int cnt = 0;
    
    if (employee.isPresent()) {
      employeeService.delete(employeeno);
      cnt=1;
    } else {
      cnt=2; // Not found
    }
    
    return ResponseEntity.ok(cnt);
    
  }

  /**
   * 로그인, http://localhost:9100/employee/login?id=user1&password=1234
   * @param id
   * @param password
   * @return
   */
  @PostMapping(path="/login")
  public ResponseEntity<Map<String, Object>> login (
      @RequestParam(name="id", defaultValue = "") String id,
      @RequestParam(name="password", defaultValue = "") String password
      ) {
    System.out.println("-> id: " + id);
    System.out.println("-> password: " + password);
    
    Map<String, Object> map = new HashMap<String, Object>();
    
    int cnt = employeeService.countByIdAndPassword(id, password);
    
    if (cnt == 1) {
      Employee employee = this.employeeService.findById(id);
      map.put("employeeno", employee.getEmployeeno());
      map.put("grade", employee.getGrade());
    } else {
      map.put("employeeno", 0);
      map.put("grade", 9999);
    }
    
    System.out.println("-> cnt: " + cnt);
    
    map.put("cnt", cnt);
    
    return ResponseEntity.ok(map);
    
  }
  
}






