package dev.jpa.resort;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.jpa.resort.employee.Employee;
import dev.jpa.resort.employee.EmployeeDTO;
import dev.jpa.resort.employee.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {
  @Autowired
  EmployeeService employeeService;
  
  @Test
  void test() {
    // 1) 중복 아이디 검사
//    System.out.println(employeeService.checkId("user1"));
//    System.out.println(employeeService.checkId("user100"));
    
    // 객체 생성시 레코드 등록됨, SQL 필요 없음.
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // String now = sdf.format(new Date());

    // 2) 등록, 자동 생성
    // String id, String passwd, String mname, String rdate, int grade    
//    employeeService.save(new EmployeeDTO(null, "admin1", "1234", "왕눈이", sdf.format(new Date()), 1));
//    employeeService.save(new EmployeeDTO(null, "user1", "1234", "아로미", sdf.format(new Date()), 10));
//    

    // 패스워드 변경
    int cnt = employeeService.updatePassword("user1", "3333");
    System.out.println("-> cnt: " + cnt);
    
  
  }
  
}


