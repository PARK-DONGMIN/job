package dev.jpa.resort.contents;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentsRepository extends JpaRepository<Contents, Long>{
  // 1) 목록, Entity에 선언된 변수가 모두 SELECT문에 있어야함.
  //SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
  //LOWER(file1) as file1, file1saved, thumb1, size1, map, youtube, visible, price, dc, saleprice, point, salecnt
  //FROM contents
  //WHERE cateno=12
  //ORDER BY contentsno DESC;

  //  @Query(value=""
  //      + "SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, passwd, word, rdate, "
  //      + "        LOWER(file1) as file1, file1saved, thumb1, size1, map, youtube, mp4, visible, price, dc, saleprice, point, salecnt "
  //      + "FROM contents "
  //      + "WHERE cateno=:cateno "
  //      + "ORDER BY contentsno ASC", nativeQuery = true)
  //   public List<Contents> listByCateno(@Param("cateno") int cateno);
  
  /**
   * 전체 목록
   * @param cateno
   * @return
   */
  public List<Contents> findByCatenoOrderByContentsnoDesc(long cateno);

  /**
   * 전체 목록, pageable을 전달받아 offset paging sql을 자동 실행
   * @param cateno
   * @return
   */
  public Page<Contents> findByCatenoOrderByContentsnoDesc(long cateno, Pageable pageable);

  /**
   * 전체 목록, pageable을 전달받아 offset paging sql을 자동 실행
   * @param cateno
   * @return
   */
  @Query(value="""
          SELECT contentsno, employeeno, cateno, title, content, recom, cnt, replycnt, password, word, rdate,
                     map, youtube, mp4, visible, file1, file1saved, thumb1, size1, price, dc, saleprice, point, salecnt,
                     emotion, summary
          FROM contents
          WHERE cateno=:cateno AND (title LIKE %:word% OR content LIKE %:word% OR word LIKE %:word%)
          ORDER BY contentsno DESC
      """, nativeQuery = true)
  public Page<Contents> list_all_paging_search(@Param("cateno") long cateno, @Param("word") String word, Pageable pageable);
  
  // 2) 등록 자동 지원
  
  /**
   * 조회수 증가
   * @param contentsno
   * @return
   */
  @Modifying
  @Query(value="UPDATE contents SET cnt = cnt + 1 WHERE contentsno =:contentsno", nativeQuery = true)
  public int increaseCnt(@Param("contentsno") long contentsno);
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public Contents findByContentsno(long contentsno);
  
  /**
   * 글 수정
   * @param title
   * @param content
   * @param word
   * @param contentsno
   * @return
   */
  @Modifying
  @Query(value="UPDATE contents SET title=:title, content=:content,  word=:word WHERE contentsno =:contentsno", nativeQuery = true)
  public int update_text(@Param("title") String title,
                                 @Param("content") String content,
                                 @Param("word") String word, 
                                 @Param("contentsno") long contentsno);
  
  /**
   * 패스워드 검사
   * @param contentsno
   * @param password
   * @return
   */
  @Query(value="SELECT COUNT(*) as cnt FROM contents WHERE contentsno=:contentsno AND password=:password", nativeQuery = true)
  public int password_check(@Param("contentsno") long contentsno,
                                       @Param("password") String password);
  
  /**
   * 파일 수정
   * @param file1
   * @param file1saved
   * @param thumb1
   * @param size1
   * @return
   */
  @Modifying
  @Query(value="UPDATE contents SET file1=:file1, file1saved=:file1saved, thumb1=:thumb1, size1=:size1 WHERE contentsno =:contentsno", nativeQuery = true)
  public int update_file1(@Param("file1") String file1,
                                 @Param("file1saved") String file1saved,
                                 @Param("thumb1") String thumb1, 
                                 @Param("size1") long size1,
                                 @Param("contentsno") long contentsno);
  
  /** 글 삭제
   * @param contentsno
   * @param password
   * @return
   */
  @Modifying
  @Query(value="DELETE FROM contents WHERE contentsno =:contentsno AND password=:password", nativeQuery = true)
  public int delete(@Param("contentsno") long contentsno,
                          @Param("password") String password);

  /**
   * 유튜브
   * @param youtube
   * @param contentsno
   * @param password
   * @return
   */
    @Modifying
    @Query(value="UPDATE contents SET youtube=:youtube WHERE contentsno=:contentsno AND password=:password", nativeQuery = true)
    public int youtube(@Param("youtube") String youtube,
                               @Param("contentsno") long contentsno,
                               @Param("password") String password);
    
  /*
   * 지도 
   */
  @Modifying
  @Query(value="UPDATE contents SET map=:map WHERE contentsno =:contentsno AND password =:password", nativeQuery = true)
  public int map(@Param("map") String map,
                       @Param("contentsno") long contentsno,
                       @Param("password") String password);
  
  
}






