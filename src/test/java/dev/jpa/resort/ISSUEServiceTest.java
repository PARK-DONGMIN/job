package dev.jpa.resort;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.jpa.resort.employee.EmployeeService;
import dev.jpa.resort.issue.ISSUEDTO;
import dev.jpa.resort.issue.ISSUEService;

@SpringBootTest
public class ISSUEServiceTest {
  @Autowired
  ISSUEService issueService;
  
  @Autowired
  EmployeeService employeeService;
  
  @Test
  void repositoryTest() {
    // 1) 등록
    // issueno, title, content, cnt, rdate    
    ISSUEDTO issueDTO = new ISSUEDTO(0, "금요일 부터 휴강", "추석 연휴", 0, null);
    issueService.save(issueDTO);   
    
  }
  
  @Test
  void serviceTest() {
    // 1) 등록
    // issueno, title, content, cnt, rdate    
    ISSUEDTO issueDTO = new ISSUEDTO(0, "금요일 부터 휴강", "추석 연휴", 0, null);
    issueService.save(issueDTO);   
  }
  
}







