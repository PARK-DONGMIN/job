package dev.jpa.resort.contents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jpa.resort.tool.Tool;

@Service
@Transactional
public class ContentsService {
  @Autowired
  ContentsRepository contentsRepository;
  
  public List<Contents> findByCatenoOrderByContentsnoDesc(long cateno) {
    List<Contents> list = this.contentsRepository.findByCatenoOrderByContentsnoDesc(cateno);
    
    return list;
  }
  
  /**
   * 페이징
   * @param cateno
   * @param pageable
   * @return
   */
  public Page<Contents> findByCatenoOrderByContentsnoDesc(long cateno, Pageable pageable) {
    Page<Contents> list = this.contentsRepository.findByCatenoOrderByContentsnoDesc(cateno, pageable);
    
    return list;
  }  

  /**
   * 페이징 + 검색 
   * @param cateno
   * @param word 검색어
   * @param pageable
   * @return
   */
  public Page<Contents> list_all_paging_search(long cateno, String word, Pageable pageable) {
    Page<Contents> list = null;
    
    System.out.println("-> word: " + word);
    if (word.length() == 0) { // 검색하지 않음.
      list = this.contentsRepository.findByCatenoOrderByContentsnoDesc(cateno, pageable);
    } else { // 검색
      list = this.contentsRepository.list_all_paging_search(cateno, word, pageable);
    }      
    
    return list;
  }  
  
  public Contents save(ContentsDTO contentsDTO) {
    contentsDTO.setRdate(Tool.getDate());
    Contents contents = this.contentsRepository.save(contentsDTO.toEntityWithFile());
    
    return contents;
  }
  
  public int increaseCnt(long contentsno) {
    return this.contentsRepository.increaseCnt(contentsno);
  }
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public ContentsDTO findByContentsno(long contentsno) {
    ContentsDTO contentsDTO = this.contentsRepository.findByContentsno(contentsno).toDTO();
    contentsDTO.setSize1_label(Tool.unit(contentsDTO.getSize1())); // 500 -> 500 byte
    
    return contentsDTO;
  }

  /**
   * 글 수정
   * @param title
   * @param content
   * @param word
   * @param contentsno
   * @return
   */
  public int  update_text(String title, String content, String word,  long contentsno) {
    return this.contentsRepository.update_text(title, content, word, contentsno);
  }
  
  /**
   * 패스워드 검사
   * @param contentsno
   * @param password
   * @return
   */
  public int password_check(long contentsno, String password) {
    return this.contentsRepository.password_check(contentsno, password);
  }
  
  /**
   * 파일 수정
   * @param file1
   * @param file1saved
   * @param thumb1
   * @param size1
   * @param contentsno
   * @return
   */
  public int  update_file1(String file1,String file1saved,String thumb1,long size1,long contentsno) {
    return this.contentsRepository.update_file1(file1, file1saved, thumb1, size1, contentsno);
  }
  
  /**
   * 한건의 레코드 삭제
   * @param contentsno
   * @param password
   * @return
   */
  public int delete(long contentsno, String password) {
    return this.contentsRepository.delete(contentsno, password);
  }

  /**
   * youtube 변경
   * @param youtube
   * @param contentsno
   * @param password
   * @return
   */
  public int youtube(String youtube, long contentsno, String password) {
    return this.contentsRepository.youtube(youtube, contentsno, password);
  } 
  
  /**
   * map 변경
   * @param map
   * @param contentsno
   * @param password
   * @return
   */
  public int map(String map, long contentsno, String password) {
      return this.contentsRepository.map(map, contentsno, password);
  }
  
}









