package dev.jpa.resort.tool;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// 메일 전송 테스트용
@RestController
@RequestMapping("/tool")
public class ToolCont {
    public ToolCont() {
        System.out.println("-> ToolCont created.");
      }
        
    /**
     * 메일 전송 // http://localhost:9100/tool/mail?receiver=luckypdm20@gmail.com&from=luckypdm20@gmail.com&title="아이디 찾기"&content="발견된 아이디: user1"
     * @param reciver 수신 이메일
     * @param from 발신 이메일
     * @param title 제목
     * @param content 내용
     * @return
     */
    @PostMapping(value = "/mail")
    public ResponseEntity<Integer>send(@RequestParam(name="receiver", defaultValue="") String receiver, 
                              @RequestParam(name="from", defaultValue="") String from,
                              @RequestParam(name="title", defaultValue = "") String title,
                              @RequestParam(name="content", defaultValue="") String content) {
      
      int sw = 0;
      try {
        MailTool mailTool = new MailTool();
        mailTool.send(receiver, from, title, content); // 메일 전송
        sw = 1;
      }catch(Exception e) {
        System.out.println(e);
      }
           
      return ResponseEntity.ok(sw);
    }
    
   
//    // http://localhost:9091/mail/send_file.do
//    /**
//     * 메일 전송
//     * @return
//     */
//    @RequestMapping(value = {"/mail/send_file.do"}, method=RequestMethod.POST)
//    public ModelAndView send_file(String receiver, String from, String title, String content,
//                                             MultipartFile file1MF) {
//      ModelAndView mav = new ModelAndView();
//      mav.setViewName("/mail/sended");  // /WEB-INF/views/mail/sended.jsp
//
//      MailTool mailTool = new MailTool();
//      mailTool.send_file(receiver, from, title, content, file1MF, "C:/kd/deploy/mail/images/"); // 메일 전송
//      
//      return mav;
//    }
    
    // http://localhost:9091/mail/send_file
    /**
     * 메일 전송
     * @return
     */
    @PostMapping(value = "/send_file")
    public String send_file(@RequestParam(name="receiver", defaultValue="") String receiver, 
                                    @RequestParam(name="from", defaultValue="") String from,
                                    @RequestParam(name="title", defaultValue = "") String title,
                                    @RequestParam(name="content", defaultValue="") String content,
                                     MultipartFile[] file1MF) {
      // java.io.FileNotFoundException: C:\kd\deploy\mvc_sms_mail\mail\storage\cup01.jpg (지정된 파일을 찾을 수 없습니다)
      // 전송 하려는 파일을 C:/kd/deploy/mvc_sms_mail/mail/storage/ 폴더에 사전에 복사한후 업로드, 다른 폴더 인식안됨. ★★★★★
      MailTool mailTool = new MailTool();
      mailTool.send_file(receiver, from, title, content, file1MF, "C:/kd/deploy/resort/mail/storage/"); // 메일 전송

      return "/mail/sended";
    }
    
}