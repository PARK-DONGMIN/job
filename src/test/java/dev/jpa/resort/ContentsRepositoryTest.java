package dev.jpa.resort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import dev.jpa.resort.contents.Contents;
import dev.jpa.resort.contents.ContentsRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
public class ContentsRepositoryTest {
  @Autowired
  ContentsRepository contentsRepository;
  
  @Test
  @Transactional
  @Rollback(false)  
  void test() {
    // 1) 목록
    // List<Contents> list = contentsRepository.listByCateno(12);
//    List<Contents> list = contentsRepository.findByCatenoOrderByContentsnoDesc(12);
//    
//    for (Contents contents : list) {
//      System.out.println(contents.toString());
//    }

    // 등록
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    // employeeno, cateno, title, content, password, word, rdate
    // contentsRepository.save(new Contents(1, 12, "K등산 안내", "북한산 등산", "1234", "", sdf.format(new Date())));
    
    // employeeno, cateno, title, content, password, word, rdate, file1, file1saved, thumb1, size1
//    contentsRepository.save(new Contents(1, 12, "K등산 안내", "도봉산 등산", "1234", "", 
//                 sdf.format(new Date()), "mt1.jpg", "mt1_1.jpg", "mt1_1_t.jpg", 1000));
    
//    employeeRepository.save(new Employee("admin", "1234", "왕눈이", sdf.format(new Date()), 1));
//    employeeRepository.save(new Employee("user", "1234", "아로미", sdf.format(new Date()), 10));

    // 조회수 증가
//    System.out.println("-> cnt: " + this.contentsRepository.increaseCnt(1));
//    System.out.println(this.contentsRepository.findByContentsno(1));
//    

    // 글 수정
    // System.out.println("-> " + this.contentsRepository.update_text("악마는 살아있다.", "실화 기반", "종교,퇴마", 3));
  
    // 패스워드 검사 
    // System.out.println("-> " + this.contentsRepository.password_check(3, "123"));
    
    // 파일 수정

    // 페이징, 0 페이지, 페이지당 레코드수 2개
    // Pageable pageable = PageRequest.of(0, 2, Sort.by("contentsno").descending()); // 1 페이지
//    Pageable pageable = PageRequest.of(1, 2, Sort.by("contentsno").descending()); // 2 페이지
//    
//    Page<Contents> list = this.contentsRepository.findByCatenoOrderByContentsnoDesc(12, pageable);
//    
//    for (Contents contents : list) {
//      System.out.println(contents.toString());
//    }
    
    // 페이징 + 검색, 0 페이지, 페이지당 레코드수 2개
//    Pageable pageable = PageRequest.of(0, 2, Sort.by("contentsno").descending()); // 1 페이지
//    // Pageable pageable = PageRequest.of(1, 2, Sort.by("contentsno").descending()); // 2 페이지
//    
//    Page<Contents> list = this.contentsRepository.list_all_paging_search(12, "컨저링", pageable);
//    
//    for (Contents contents : list) {
//      System.out.println(contents.toString());
//    }

    // 삭제
    System.out.println("-> " + this.contentsRepository.delete(32, "1234"));
  
  }
}







