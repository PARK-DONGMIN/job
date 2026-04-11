package dev.jpa.resort;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.jpa.resort.cate.Cate;
import dev.jpa.resort.cate.CateRepository;
import dev.jpa.resort.cate.CateService;

@SpringBootTest
public class CateServiceTest {
  @Autowired
  CateService cateService;
  
  @Test
  void test() {
    Map<String, Object> list = cateService.getGrpName();
//    for(int i=0; i< list.size(); i++) {
//      System.out.println(list.get);
//    }
    System.out.println("--------------------------------------------");
    System.out.println(list);
/*

{
grp_name=[
    {item=[
             Cate(cateno=2, grp=영화, name=SF, cnt=0, seqno=2, visible=Y, rdate=2025-10-30 10:16:08)], 
    grp=영화}, 
    {item=[
             Cate(cateno=5, grp=여행, name=국내, cnt=0, seqno=102, visible=Y, rdate=2025-10-30 10:17:19)], 
    grp=여행}, 
    {item=[
             Cate(cateno=8, grp=캠핑, name=경기도, cnt=0, seqno=202, visible=Y, rdate=2025-10-30 10:18:02), 
             Cate(cateno=9, grp=캠핑, name=강원도, cnt=0, seqno=203, visible=Y, rdate=2025-10-30 10:18:08)], 
     grp=캠핑}
   ]
 }
 
 */
    
  }
}




