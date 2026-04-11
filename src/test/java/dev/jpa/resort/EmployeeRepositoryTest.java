package dev.jpa.resort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import dev.jpa.resort.employee.Employee;
import dev.jpa.resort.employee.EmployeeRepository;
import dev.jpa.resort.issue.ISSUE;
import jakarta.transaction.Transactional;

@SpringBootTest
public class EmployeeRepositoryTest {
  @Autowired
  EmployeeRepository employeeRepository;
  
  @Test
  @Transactional
  @Rollback(false)
  void test() {
    // 객체 생성시 레코드 등록됨, SQL 필요 없음.
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // String now = sdf.format(new Date());

    // 1) 중복 아이디 검사
//    System.out.println("-> " + employeeRepository.countById("user1"));
//    System.out.println("-> " + employeeRepository.countById("user100"));
//    
    
    // 2) 등록, 자동 생성
    // String id, String passwd, String mname, String rdate, int grade    
//    employeeRepository.save(new Employee("admin", "1234", "왕눈이", sdf.format(new Date()), 1));
//    employeeRepository.save(new Employee("user", "1234", "아로미", sdf.format(new Date()), 10));
    
    // 3) 목록
//    List<Employee> list = employeeRepository.findAllByOrderByIdAsc();
//    for(Employee employee : list) {
//      System.out.println(employee.toString());
//    }
    
    // 4) 조회
//    Employee employee = employeeRepository.findByEmployeeno(143);
//    System.out.println(employee.toString());
    
  Employee employee = employeeRepository.findById("user1");
  System.out.println(employee.toString());
    
    // 5) 패스워드 변경
//    int cnt = employeeRepository.updatePassword('user1', "1111");
//    System.out.println("-> cnt: " + cnt);
//    
//    Employee employee = employeeRepository.findByEmployeeno(154);
//    System.out.println(employee.toString());
    
    // 6) 패스워드 검사, 로그인
//    int cnt = employeeRepository.countByIdAndPassword("user1", "2222");
//    System.out.println("-> cnt: " + cnt);
    
    // 참고 로그인
//    Optional<Employee> employeeRepository = repository.findByIdAndPasswd("admin", "1234");
//    if (employeeRepository.isPresent()) {
//      System.out.println("직원이 존재합니다.");
//      System.out.println(employee.get().toString());
//    } else {
//      System.out.println("해당하는 직원이 없습니다.");
//    }
    
    // 7) 수정
//    int cnt = employeeRepository.update("가길동", "user5", 1, 143);
//    System.out.println("-> cnt: " + cnt);
    
    // 8) 삭제
    // employeeRepository.deleteById((long)2); // void
    
  }
}






