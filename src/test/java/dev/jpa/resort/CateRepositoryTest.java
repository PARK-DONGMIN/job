package dev.jpa.resort;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.jpa.resort.cate.Cate;
import dev.jpa.resort.cate.CateRepository;

@SpringBootTest
public class CateRepositoryTest {
  @Autowired
  CateRepository cateRepository;
  
  @Test
  void test() {
//    List<Cate> list = cateRepository.getGrp();
    List<Cate> list = cateRepository.getGrpName("영화");
    
    for(Cate cate: list) {
      System.out.println(cate.toString());
    }
  }
}





