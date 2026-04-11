package dev.jpa.resort;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller
public class HomeCont_old {
  public HomeCont_old() {
    System.out.println("-> HomeCont created.");
  }
  
  // URI Command 패턴 자동 지원, 'msg.seoul' 처럼 실제 존재하는 파일이 아님, 인터넷 주소를 명령어로 사용.
  // http://localhost:9100/ -> 주소 분석 -> GET/POST -> 메소드 실행
  @GetMapping(value={"/"})
  @ResponseBody  // 출력 내용이 html 파일의 내용임으로 브러우저가 바로 출력함.
  public String msg() {
    return "<h2>Resort V3 콘트롤러 정상 작동됨</h2>";
  }

}
